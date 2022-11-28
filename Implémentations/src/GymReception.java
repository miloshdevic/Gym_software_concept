import java.io.IOException;

/**
 * simulation de la réception du #GYM
 * l'agent traite toutes les requêtes disponibles
 * Il est formé pour savoir comment utiliser le logiciel
 */
public class GymReception extends Accueil {


    /**
     * affichage du logiciel #GYM (réception)
     */
    public void afficherMenu() throws IOException {
        while (true) {
            System.out.println(
                    "+----------------------------------------------------------+\n" +
                            "|                                                          |\n" +
                            "|         ------- Centre de Données #GYM -------           |\n" +
                            "|                                                          |\n" +
                            "+----------------------------------------------------------+\n\n" +
                            "1- gestion de membre\n" +
                            "2- gestion de professionnel\n" +
                            "3- gestion de services\n" +
                            "4- procédures comptables\n" +
                            "5- revenir en arrière\n");

            int choix = BetterScanner.nextInt();

            switch(choix) {
                case 1:
                    System.out.println( "1- créer un nouveau compte\n" +
                            "2- identifier un membre\n" +
                            "3- mettre à jour le statut d'un membre\n" +
                            "4- supprimer le compte d'un membre\n" +
                            "5- revenir en à l'accueil\n");

                    int choix2 = BetterScanner.nextInt();
                    switch (choix2) {
                        case 1:
                            choisir("creerMembre");
                            break;
                        case 2:
                            choisir("identifierMembre");
                            break;
                        case 3:
                            choisir("updateMembre");
                            break;
                        case 4:
                            choisir("supprimerMembre");
                            break;
                        case 5:
                            this.afficherMenu();
                    }
                    break;
                case 2:
                    System.out.println( "1- créer un nouveau compte\n" +
                            "2- identifier un professionnel\n" +
                            "3- mettre à jour le statut d'un professionnel\n" +
                            "4- supprimer le compte d'un professionnel\n" +
                            "5- revenir en à l'accueil\n");

                    int choix3 = BetterScanner.nextInt();
                    switch (choix3) {
                        case 1:
                            choisir("creerPro");
                            break;
                        case 2:
                            choisir("identifierPro");
                            break;
                        case 3:
                            choisir("updatePro");
                            break;
                        case 4:
                            choisir("supprimerPro");
                            break;
                        case 5:
                            this.afficherMenu();
                    }
                    break;
                case 3:
                    System.out.println( "1- créer un nouveau service\n" +
                            "2- s'inscrire à un service\n" +
                            "3- mettre à jour le statut d'un service\n" +
                            "4- confirmer présence à une séance\n" +
                            "5- supprimer un service\n" +
                            "6- revenir en à l'accueil\n");

                    int choix4 = BetterScanner.nextInt();
                    switch (choix4) {
                        case 1:
                            choisir("creerService");
                            break;
                        case 2:
                            choisir("inscrire");
                            break;
                        case 3:
                            choisir("updateService");
                            break;
                        case 4:
                            choisir("confirmerPresence");
                            break;
                        case 5:
                            choisir("supprimerService");
                            break;
                        case 6:
                            this.afficherMenu();
                    }
                    break;
                case 4:
                    this.procedureComptable();
                    break;
                case 5:
                    choisirPlateforme();
                    break;
                default:
                    System.err.println("Choix invalide");
            }
        }
    }


    /**
     * agent choisit la tâche qu'on aimerait faire
     * @param commande correspond a la tache a faire
     */
    public void choisir(String commande) throws IOException {
        switch (commande) {
            case "creerMembre":
                membreControleur.creerMembre();

                break;
            case "identifierMembre": {
                //this.membreControleur.afficherInfos("email@jsp.com");
                System.out.println("Veuillez saisir l'identifiant du membre svp:\n");
                int id = BetterScanner.nextInt();
                if (membreControleur.identifierMembre(id))
                    System.out.println("\nValidé\n");
                else if (id == 111111111)//cas ou le statut est marqué
                    System.out.println("\nMembre suspendu en raison de certains frais dus impayés.\n");
                else
                    System.out.println("\nNuméro Invalide\n\n");

                break;
            }
            case "updateMembre": {
                System.out.println("Veuillez saisir l'identifiant du membre svp:\n");
                int id = BetterScanner.nextInt();
                if (membreControleur.identifierMembre(id))
                    membreControleur.updateMembre(id);
                else
                    System.out.println("Numéro Invalide\n");

                break;
            }
            case "supprimerMembre": {
                System.out.println("Veuillez saisir l'identifiant du membre svp:\n");
                int id = BetterScanner.nextInt();
                if (membreControleur.identifierMembre(id))
                    membreControleur.supprimerMembre(id);
                else
                    System.out.println("Numéro Invalide\n");
                System.out.println("Compte membre supprimé.\n");

                break;
            }
            case "creerPro": {
                professionnelControleur.creerPro();
                break;
            }
            case "identifierPro": {
                System.out.println("Veuillez saisir l'identifiant du professionnel svp:\n");
                int id = BetterScanner.nextInt();
                if (professionnelControleur.identifierPro(id))
                    System.out.println("Validé\n");
                else
                    System.out.println("Numéro Invalide\n");

                break;
            }
            case "updatePro": {
                System.out.println("Veuillez saisir l'identifiant du professionnel svp:\n");
                int id = BetterScanner.nextInt();
                if (professionnelControleur.identifierPro(id))
                    professionnelControleur.updatePro(id);
                else
                    System.out.println("Numéro Invalide\n");

                break;
            }
            case "supprimerPro": {
                System.out.println("Veuillez saisir l'identifiant du professionnel svp:\n");
                int id = BetterScanner.nextInt();
                if (membreControleur.identifierMembre(id))
                    professionnelControleur.supprimerPro(id);
                else
                    System.out.println("Numéro Invalide\n");
                professionnelControleur.supprimerPro(id);
                System.out.println("Compte professionnel supprimé.\n");

                break;
            }
            case "creerService": {
                System.out.println("Entrez l'identifiant du professionnel: ");
                int id = BetterScanner.nextInt();
                if (professionnelControleur.identifierPro(id))
                    serviceControleur.creerService(id);
                else
                    System.out.println("Nouveau professionnel. Créez un compte professionnel avant de continuer.");

                break;
            }
            case "inscrire": {
                System.out.println("Entrez l'identifiant du membre: ");
                int id = BetterScanner.nextInt();
                if (membreControleur.identifierMembre(id)) {
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
                        }
                    } else {
                        System.out.println("\nCe service n'existe pas\n");
                    }
                } else {
                    System.out.println("Numéro Invalide\n");
                }

                break;
            }
            case "updateService": {
                System.out.println("Veuillez saisir l'identifiant du service svp:\n");
                int id = BetterScanner.nextInt();
                if (serviceControleur.identifierService(id))
                    serviceControleur.updateService(id);
                else
                    System.out.println("Numéro Invalide\n");

                break;
            }
            case "confirmerPresence": {
                System.out.println("Entrez l'identifiant du service: ");
                int id = BetterScanner.nextInt();
                if (serviceControleur.identifierService(id)) {
                    System.out.println("\nVeuillez saisir l'identifiant du membre: ");
                    int idMembre = BetterScanner.nextInt();

                    if (membreControleur.identifierMembre(idMembre)) {
                        serviceControleur.afficherService(id);

                        if (seance.confirmerInscription(idMembre, id)) {
                            System.out.println("\nValidé\n");
                            //afficher les infos
                            serviceControleur.afficherApresConfirmation(idMembre, id);
                        } else {
                            System.out.println("\nAccès à la séance refus\né");
                        }

                    } else {
                        System.out.println("Numéro Invalide");
                    }

                } else {
                    System.out.println("Ce service n'existe pas.");
                }

                break;
            }
            case "supprimerService": {
                System.out.println("Veuillez saisir l'identifiant du service svp:\n");
                int id = BetterScanner.nextInt();
                serviceControleur.supprimerService(id);
                System.out.println("Service supprimé.\n");
                break;
            }
        }

        System.out.println("\nAppuyez sur 1 pour revenir à la réception ou 2 pour quitter\n");
        int done = BetterScanner.nextInt();
        if (done == 1)
            this.afficherMenu();
        if (done == 2)
            System.exit(0);
    }


    /**
     * afficher les differentes options pour procedure comptable
     */
    public void procedureComptable() throws IOException {
        System.out.println("1- Imprimer le rapport");
        System.out.println("2- Afficher le fichier TEF");
        System.out.println("3- Procédure financière des membres");
        System.out.println("4- Revenir en arrière");

        int choix = BetterScanner.nextInt();

        switch (choix) {
            case 1:
                RapportSynthese.rapportSynthese();
                break;
            case 2:
                professionnelControleur.creerTEF();
                break;
            case 3:
                membreControleur.transfererDossiersRnB();
                break;
            case 4:
                this.afficherMenu();
                break;
        }
    }
}
