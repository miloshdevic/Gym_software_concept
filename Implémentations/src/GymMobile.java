import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * simulation de l'application mobile utilisée
 * par les clients (membres et professionnels)
 */
public class GymMobile extends Accueil {


    /**
     * afficher la simulation de l'application mobile
     */
    public void afficherMobile() throws IOException {
        while (true) {
            System.out.println(
                    "+----------------------------------------------------------+\n" +
                            "|                                                          |\n" +
                            "|         ------- Application Mobile #GYM -------          |\n" +
                            "|                                                          |\n" +
                            "+----------------------------------------------------------+\n\n" +
                            "[Enregistrement]\n" +
                            "1- s'identifier (membre)\n" +
                            "2- s'identifier (professionnel)\n" +
                            "3- revenir en arrière\n");


            int choix = BetterScanner.nextInt();

            switch (choix) {
                case 1:
                    System.out.println("Veuillez saisir votre courriel Facebook: ");
                    String emailMembre = BetterScanner.nextLine();
                    System.out.println("Veuillez saisir votre identifiant: ");

                    if (membreControleur.identifierMembreAvecEmail(emailMembre)) {
                        int id = Integer.parseInt(BetterScanner.nextLine());
                        if (membreControleur.identifierMembreEmailEtId(emailMembre, id)) {
                            System.out.println("Validé");
                            membreControleur.afficherInfos(emailMembre);
                            menuMembreEnregistre(id);
                        } else {
                            System.out.println("Numéro Invalide");
                        }

                    } else {
                        System.out.println("Email Invalide");
                    }
                    break;
                case 2:
                    System.out.println("Veuillez saisir votre courriel Facebook: ");
                    String emailPro = BetterScanner.nextLine();
                    System.out.println("Veuillez saisir votre identifiant: ");

                    if (professionnelControleur.identifierProAvecEmail(emailPro)) {
                        int id = Integer.parseInt(BetterScanner.nextLine());
                        if (professionnelControleur.identifierProEmailEtId(emailPro, id)) {
                            System.out.println("Validé");
                            professionnelControleur.afficherInfosEtQR(emailPro);
                            menuProEnregistre(id);
                        } else {
                            System.out.println("Numéro Invalide");
                        }

                    } else {
                        System.out.println("Email Invalide");
                    }
                    break;
                case 3:
                    choisirPlateforme();
                default:
                    System.err.println("Choix invalide");
            }
        }
    }

    /**
     * menu d'enregistrement pour le membre
     * @param id son identifiant
     */
    public void menuMembreEnregistre(int id) throws IOException {
        System.out.println("1- s'incrire à une séance\n" +
                "2- consulter la facture\n" +
                "3- déconnexion\n");

        int choix = BetterScanner.nextInt();

        switch (choix) {
            case 1:

                //afficher le repertoire
                serviceControleur.afficherRepertoireServices();

                //choisir la seance voulue
                System.out.println("\nÉcrivez le nom de la séance voulue: ");
                String nomSeance = BetterScanner.nextLine();
                if (serviceControleur.identifierServiceParNom(nomSeance)) {
                    Object[] infos = serviceControleur.getInfosInscription(nomSeance);
                    if (serviceControleur.verifierDispo(nomSeance, seance.getNbrInscrit((int) infos[4]))) {
                        //fonction qui retourne les infos
                        System.out.println("Confirmez-vous votre inscription? (oui/non)");
                        if (BetterScanner.nextLine().equalsIgnoreCase("oui")) {
                            seance.inscrireMembre(nomSeance, infos[1].toString(), infos[2].toString(), id, (int) infos[3],
                                    (int) infos[4], infos[5].toString());
                            System.out.println("\nInscription réussie!\n");
                            System.out.println("Frais à payer: " + serviceControleur.getFrais((int) infos[4]) + "$");
                        } else {
                            System.out.println("Inscription annulée");
                        }
                    } else {
                        System.out.println("\nCapacité maximale atteinte!\n");
                        menuMembreEnregistre(id);
                    }
                } else {
                    System.out.println("\nCe service n'existe pas\n");
                }

                break;
            case 2:
                System.out.println("\nVoici les informations pour les services inscrits:\n");

                //get 3 infos a print
                ArrayList<Object[]> infosServices = seance.getServicesInscritsMembre(id);
                if (infosServices.size() != 0) {
                    membreControleur.afficherFactureInfos(id);

                    //basé sur internet pour avoir le temps présent
                    LocalDate date = LocalDate.now();
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    String formattedDate = date.format(format);
                    LocalDateTime dateTime = LocalDateTime.now();
                    format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                    String formattedDateTime = dateTime.format(format);

                    File factureFile = new File(membreControleur.trouverNomMembre(id) + "_" +
                            formattedDateTime + ".txt");
                    FileWriter fileWriter = new FileWriter(factureFile, true);

                    membreControleur.ecrireFactureInfos(id, fileWriter);

                    //afficher les infos
                    professionnelControleur.afficherFactureMembre(infosServices, fileWriter);
                    fileWriter.flush();
                    fileWriter.close();
                } else {
                    System.out.println("Vous n'êtes pas inscrit à un service.");
                }
                break;
            case 3:
                choisirPlateforme();
        }
    }

    /**
     * menu de l'application mobile pour le professionnel une fois enregistré
     * @param id son identifiant
     */
    public void menuProEnregistre(int id) throws IOException {
        System.out.println("1- consulter les inscriptions d'une séance\n" +
                "2- confirmer présence pour un membre\n" +
                "3- consulter la facture\n" +
                "4- déconnexion\n");

        int choix = BetterScanner.nextInt();

        switch (choix) {
            case 1:
                //afficher le repertoire
                serviceControleur.afficherRepertoireServices();

                //choisir la seance voulue
                System.out.println("\nÉcrivez le nom de la séance voulue: ");
                String nomSeance = BetterScanner.nextLine();
                ArrayList<Integer> listeIdMembres = seance.getInscriptions(nomSeance);
                membreControleur.afficherInscriptions(listeIdMembres);

                break;
            case 2:
                //prendre code de la seance
                System.out.println("Veuillez saisir le code de la séance: ");
                int idSeance = BetterScanner.nextInt();

                //afficher infos de la seance
                serviceControleur.afficherService(idSeance);

                System.out.println("\nVeuillez saisir l'identifiant du membre: ");
                int idMembre = BetterScanner.nextInt();
                if (seance.confirmerInscription(idMembre, idSeance)) {
                    System.out.println("\nValidé\n");
                    //afficher les infos
                    serviceControleur.afficherApresConfirmation(idMembre, idSeance);
                } else {
                    System.out.println("Accès à la séance refusé");
                }

                break;
            case 3:
                //afficher ses infos
                System.out.println("\nVoici les informations pour les services données:\n");

                //get infos des services
                ArrayList<Object[]> infosServices = seance.getServicesInscritsPro(id);

                if (infosServices.size() != 0) {
                    professionnelControleur.afficherInfosPro(id);

                    //basé sur internet pour avoir le temps présent
                    LocalDate date = LocalDate.now();
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    String formattedDate = date.format(format);
                    LocalDateTime dateTime = LocalDateTime.now();
                    format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                    String formattedDateTime = dateTime.format(format);

                    File factureFile = new File(professionnelControleur.trouverNomPro(id) + "_" +
                            formattedDateTime + ".txt");
                    FileWriter fileWriter = new FileWriter(factureFile, true);
                    professionnelControleur.fichierFacturePro(id, fileWriter);
                    for (Object[] objects: infosServices) {
                        System.out.println("------------------------------------------");
                        System.out.println("Récurrence hebdomadaire: " + objects[0]);
                        System.out.println("Date et heure à laquelle les données étaient reçues par l'ordinateur: " +objects[1]);
                        System.out.println("Nom du membre: " + membreControleur.trouverNomMembre((Integer) objects[2]));
                        System.out.println("Numéro du membre: " + objects[2]);
                        System.out.println("Code de la séance: " + objects[3]);
                        //afficher montant
                        System.out.println("Montant à payer: " + serviceControleur.getFrais((Integer) objects[3]) + "$");

                        //mettre dans un fichier
                        fileWriter.write("------------------------------------------\n");
                        fileWriter.write("Code de la séance: " + objects[3] + "\n");
                        fileWriter.write("Récurrence hebdomadaire: " + objects[0] + "\n");
                        fileWriter.write("Date et heure à laquelle les données étaient reçues par l'ordinateur: " +
                                objects[1] + "\n");
                        fileWriter.write("Nom du membre: " + membreControleur.trouverNomMembre((Integer) objects[2]) + "\n");
                        fileWriter.write("Numéro du membre: " + objects[2] + "\n");
                        fileWriter.write("Montant à payer: " + serviceControleur.getFrais((Integer) objects[3]) + "$\n");
                    }
                    fileWriter.flush();
                    fileWriter.close();

                } else {
                    System.out.println("Aucun service donné");
                }
                break;
            case 4:
                choisirPlateforme();
        }
    }

}
