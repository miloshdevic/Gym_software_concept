import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * classe controleur du professionnel
 * traite les fonctionnalités pertinentes
 */
public class ProfessionnelControleur {

    private ArrayList<Professionnel> listePro = new ArrayList<Professionnel>();

    /**
     * créer un compte pour un nouveau professionnel
     */
    public void creerPro() {
        System.out.println("Veuillez saisir les informations demandées:\n");

        String nom, adresse, email, ville, province, codePostal, domainesExpertises;
        Pattern formatARespecter; //sert à vérifier si certaines données sont entrées correctement
        Matcher matcher;
        while (true) {
            System.out.println("Nom et prénom: ");
            nom = BetterScanner.nextLine();
            if (nom.length() <= 25)
                break;
            else
                System.out.println("maximum de 25 caractères");
        }
        while (true) {
            System.out.print("adresse: ");
            adresse = BetterScanner.nextLine();
            if (adresse.length() <= 25)
                break;
            else
                System.out.println("maximum de 25 charactères\n");
        }
        formatARespecter = Pattern.compile("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b");
        while (true) {
            System.out.print("email: ");
            email = BetterScanner.nextLine();
            matcher = formatARespecter.matcher(email);
            if (matcher.find())
                break;
            System.err.println("format d'email invalide");
            System.out.println("resaisissez svp: ");
        }
        while (true) {
            System.out.print("Ville: ");
            ville = BetterScanner.nextLine();
            if (ville.length() <= 14)
                break;
            else
                System.out.println("maximum 14 charactères");
        }
        formatARespecter = Pattern.compile("[a-zA-Z]{2}");
        while (true) {
            System.out.print("Province (AA): ");
            province = BetterScanner.nextLine();
            matcher = formatARespecter.matcher(province);
            if (province.length() == 2 && matcher.find())
                break;
            else
                System.out.println("Format invalide");
        }
        formatARespecter = Pattern.compile("[A-Z0-9A-Z0-9A-Z0-9]{6}");
        while (true) {
            System.out.print("Code Postal (A1A1A1): ");
            codePostal = BetterScanner.nextLine();
            matcher = formatARespecter.matcher(codePostal);
            if (matcher.find() && codePostal.length() == 6) {
                break;
            } else {
                System.out.println("Format invalide\n");
                System.out.println("resaisissez svp ");
            }
        }
        System.out.println("les domaines d'expertises (séparés d'une virgule): ");
        domainesExpertises = BetterScanner.nextLine();

        Professionnel nouveauProfessionnel = new Professionnel(nom, adresse, email, ville, province, codePostal,
                domainesExpertises.split(","));
        this.listePro.add(nouveauProfessionnel);
        System.out.println("\nCompte professionnel créé! Voici son identifiant: " + nouveauProfessionnel.getIdClient());

    }


    /**
     * identifier le professionnel seulement
     * @param id id du professionnel à valider
     * @return un boolean qui valide ou pas l'identification du professionnel
     */
    public boolean identifierPro(int id) {
        for (Professionnel pro: listePro) {
            if (pro.getIdClient() == id)
                return true;
        }
        return false;
    }


    /**
     * identifier par le courriel seulement
     * @param email email du professionnel à valider
     * @return un boolean qui valide ou pas l'identification du professionnel
     */
    public boolean identifierProAvecEmail(String email) {
        for (Professionnel professionnel : this.listePro) {
            if (professionnel.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }


    /**
     * vérifie que l'email et l'id correspond au même professionnel
     * sinon il y a possibilité de se connecter avec l'email de
     * l'un et l'id d'un autre
     * @param email email du professionnel à valider
     * @param id id du professionnel à valider
     * @return un boolean qui valide ou pas l'identification du professionnel
     */
    public boolean identifierProEmailEtId(String email, int id) {
        for (Professionnel pro: listePro) {
            if (pro.getIdClient() == id && pro.getEmail().equalsIgnoreCase(email))
                return true;
        }
        return false;
    }


    /**
     * mettre à jour le statut du professionnel
     * @param id son id
     */
    public void updatePro(int id) {
        for (Professionnel professionnel: this.listePro) {
            if (professionnel.getIdClient() == id) {
                System.out.println("Informations du professionnel:");
                System.out.println("1) Nom complet: " + professionnel.getNom());
                System.out.println("2) Email: " + professionnel.getEmail());
                System.out.println("3) Adresse: " + professionnel.getAdresse());
                System.out.println("4) Ville: " + professionnel.getVille());
                System.out.println("5) Province: " + professionnel.getProvince());
                System.out.println("6) Code postal: " + professionnel.getCodePostal());
                System.out.println("7) Domaine(s) d'expertise(s): " + Arrays.toString(professionnel.getDomaineExpertise()));
                System.out.println("Entez le nombre associé à l'information que vous souhaitez modifier:");

                int choice = BetterScanner.nextInt();
                Pattern regex;
                Matcher matcher;
                switch (choice) {
                    case 1:
                        while (true) {
                            System.out.println("Nouveaux nom et prénom: ");
                            String nom = BetterScanner.nextLine();
                            if (nom.length() <= 25) {
                                professionnel.setNom(nom);
                                break;
                            } else {
                                System.out.println("maximum de 25 caractères");
                            }
                        }
                        break;

                    case 2:
                        regex = Pattern.compile("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b");
                        while (true) {
                            System.out.print("Nouveau email: ");
                            String email = BetterScanner.nextLine();
                            matcher = regex.matcher(email);
                            if (matcher.find()) {
                                professionnel.setEmail(email);
                                break;
                            }
                            System.err.println("format d'email invalide");
                            System.out.println("resaisissez svp: ");
                        }
                        break;

                    case 3:
                        while (true) {
                            System.out.print("Nouvelle adresse: ");
                            String adresse = BetterScanner.nextLine();
                            if (adresse.length() <= 25) {
                                professionnel.setAdresse(adresse);
                                break;
                            } else {
                                System.out.println("maximum de 25 charactères\n");
                            }
                        }
                        break;

                    case 4:
                        while (true) {
                            System.out.print("Nouvelle Ville: ");
                            String ville = BetterScanner.nextLine();
                            if (ville.length() <= 14) {
                                professionnel.setVille(ville);
                                break;
                            } else {
                                System.out.println("maximum 14 charactères");
                            }
                        }
                        break;
                    case 5:
                        regex = Pattern.compile("[a-zA-Z]{2}");
                        while (true) {
                            System.out.print("Nouvelle province (AA): ");
                            String province = BetterScanner.nextLine();
                            matcher = regex.matcher(province);
                            if (province.length() == 2 && matcher.find()) {
                                professionnel.setProvince(province);
                                break;
                            } else {
                                System.out.println("Format invalide");
                            }
                        }
                        break;

                    case 6:
                        regex = Pattern.compile("[A-Z0-9A-Z0-9A-Z0-9]{6}");
                        while (true) {
                            System.out.print("Code Postal (A1A1A1): ");
                            String codePostal = BetterScanner.nextLine();
                            matcher = regex.matcher(codePostal);
                            if (matcher.find() && codePostal.length() == 6) {
                                break;
                            } else {
                                System.out.println("Format invalide\n");
                                System.out.println("resaisissez svp ");
                            }
                        }
                        break;
                    case 7:
                        System.out.println("Nouveau(x) domaine(s) d'expertises(s): ");
                        String domaine = BetterScanner.nextLine();
                        professionnel.setDomaineExpertise(domaine.split(","));
                        break;
                    default:
                        System.err.println("Invalid choice.");
                        return;
                }
                System.out.println("Modifications faites.");
                return;
            }
        }
    }

    /**
     * supprimer le compte du professionnel
     * @param id id du professionnel à supprimer
     */
    public void supprimerPro(int id) {
        System.out.println("Confirmez-vous de vouloir supprimer ce compte? (oui/non)\n");
        String reponse = BetterScanner.nextLine();
        if (reponse.equalsIgnoreCase("oui"))
            listePro.removeIf(pro -> pro.getIdClient() == id);
        else
            System.out.println("Action annulée");

    }

    /**
     * afficher les infos du pros et le code QR
     * @param email email du professionnel concerné
     */
    public void afficherInfosEtQR(String email) {
        for (Professionnel pro: this.listePro) {
            if (pro.getEmail().equalsIgnoreCase(email)) {
                System.out.println("Informations du professionnel:");
                System.out.println("Nom complet: " + pro.getNom());
                System.out.println("Identifiant: " + pro.getIdClient());
                System.out.println("Votre code QR: ");

                //simulation d'un code QR
                System.out.println("##################");
                System.out.println("#__|##__#_==##--_#");
                System.out.println("#--##-==-_-##_####");
                System.out.println("#=#_##-#_-====#=-#");
                System.out.println("###|##_#-=__###--#");
                System.out.println("##################");
                System.out.println("Appuyez sur ENTER pour aller au menu à choisir");
                BetterScanner.nextLine();
            }
        }
    }

    /**
     * afficher l'ensemble des infos du professionnel pour le rapport et/ou facture
     * @param id id du professionnel concerné
     */
    public void afficherInfosPro(int id) {
        for (Professionnel professionnel: listePro) {
            if (professionnel.getIdClient() == id) {
                System.out.println("Nom du professionnel: " + professionnel.getNom());
                System.out.println("Numéro du professionnel: " + professionnel.getIdClient());
                System.out.println("Adresse du professionnel: " + professionnel.getAdresse());
                System.out.println("Ville du professionnel: " + professionnel.getVille());
                System.out.println("Province du professionnele: " + professionnel.getProvince());
                System.out.println("Code postal du professionnel: " + professionnel.getCodePostal());
                break;
            }
        }
    }


    /**
     * ajouter l'ensemble des infos du professionnel pour le fichier facture
     * @param id id du professionnel concerné
     */
    public void fichierFacturePro(int id, FileWriter fileWriter) throws IOException {
        for (Professionnel professionnel: listePro) {
            if (professionnel.getIdClient() == id) {
                fileWriter.write("Nom du professionnel: " + professionnel.getNom() + "\n");
                fileWriter.write("Numéro du professionnel: " + professionnel.getIdClient() + "\n");
                fileWriter.write("Adresse du professionnel: " + professionnel.getAdresse() + "\n");
                fileWriter.write("Ville du professionnel: " + professionnel.getVille() + "\n");
                fileWriter.write("Province du professionnele: " + professionnel.getProvince() + "\n");
                fileWriter.write("Code postal du professionnel: " + professionnel.getCodePostal() + "\n");
                break;
            }
        }
    }

    /**
     * afficher les infos de la facture pour le membre et mettre dans un fichier
     * @param infos les informations nécessaires à afficher pour la facture du membre
     */
    public void afficherFactureMembre(ArrayList<Object[]> infos, FileWriter fileWriter) throws IOException {
        for (Object[] object: infos) {
            System.out.println("\nRécurrence hebdomadaire: " + object[0]);
            System.out.println("Nom du professionnel: " + trouverNomPro((Integer) object[1]));
            System.out.println("Nom du service: " + object[2]);
            System.out.println("------------------------------------------\n");

            //mettre dans un fichier
            fileWriter.write("------------------------------------------\n");
            fileWriter.write("Récurrence hebdomadaire: " + object[0] + "\n");
            fileWriter.write("Nom du professionnel: " + trouverNomPro((Integer) object[1]) + "\n");
            fileWriter.write("Nom du service: " + object[2] + "\n");
        }
    }

    /**
     * trouver le nom du professionnel basé sur l'id
     * (parfois on a seulement l'id mais on a besoin du nom)
     * @param id l'id en question
     * @return le nom du professionnel correspondant à l'id
     */
    public String trouverNomPro(int id) {
        for (Professionnel professionnel: listePro) {
            if (professionnel.getIdClient() == id) {
                return professionnel.getNom();
            }
        }
        return "";
    }

    /**
     * creer fichier TEF
     */
    public void creerTEF() {
        System.out.println("Liste des professionnels offrent des services:");
        for (Professionnel professionel : listePro) {
            System.out.println("Nom: "+professionel.getNom());
            System.out.println("ID: "+professionel.getIdClient());
            double salaire = 0;
            for (Inscription inscription : CentreDonnees.listeInscriptions) {
                for (Service service : CentreDonnees.repertoireServices)
                    if (inscription.getIdSeance() == service.getIdService() &&
                            inscription.getIdPro() == professionel.getIdClient()) {
                        salaire += service.getFrais();
                    }
            }
            System.out.println("Montant à lui verser: "+ salaire + " $\n");
        }
        System.out.println("Appuyez ENTER pour revenir");
        Scanner userInput = new Scanner(System.in);
        userInput.nextLine();
    }

    //getter et setter


    public ArrayList<Professionnel> getListePro() {
        return listePro;
    }

    public void setListePro(ArrayList<Professionnel> listePro) {
        this.listePro = listePro;
    }
}
