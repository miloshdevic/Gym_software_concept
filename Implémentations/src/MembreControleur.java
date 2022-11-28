import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * classe controleur du membre
 * traite les fonctionnalités pertinentes
 */
public class MembreControleur {

    private ArrayList<Membre> listeMembres = new ArrayList<Membre>();


    /**
     * créer un compte pour un nouveau membre
     */
    public void creerMembre() {
        System.out.println("Veuillez saisir les informations demandées:\n");

        String nom, adresse, email, ville, province, codePostal;
        Pattern regex;
        Matcher matcher;
        while (true) {
            System.out.println("Nom et prénom: ");
            nom = BetterScanner.nextLine();
            if (nom.length() <= 25)
                break;
            else System.out.println("maximum de 25 caractères");
        }
        while (true) {
            System.out.print("adresse: ");
            adresse = BetterScanner.nextLine();
            if (adresse.length() <= 25)
                break;
            else System.out.println("maximum de 25 charactères\n");
        }
        regex = Pattern.compile("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b");
        while (true) {
            System.out.print("email: ");
            email = BetterScanner.nextLine();
            matcher = regex.matcher(email);
            if (matcher.find())
                break;
            System.err.println("format d'email invalide\n");
            System.out.println("resaisissez svp: ");
        }
        while (true) {
            System.out.print("Ville: ");
            ville = BetterScanner.nextLine();
            if (ville.length() <= 14)
                break;
            else System.out.println("La ville ne peut pas dépasser 14 charactères");
        }
        regex = Pattern.compile("[a-zA-Z]{2}");
        while (true) {
            System.out.print("Province (AA): ");
            province = BetterScanner.nextLine();
            matcher = regex.matcher(province);
            if (province.length() == 2 && matcher.find())
                break;
            else
                System.out.println("Format invalide\n");
            System.out.println("resaisissez svp: ");
        }
        regex = Pattern.compile("[A-Z0-9A-Z0-9A-Z0-9]{6}");
        while (true) {
            System.out.print("Code Postal (A1A1A1): ");
            codePostal = BetterScanner.nextLine();
            matcher = regex.matcher(codePostal);
            if (matcher.find() && codePostal.length() == 6) {
                break;
            } else {
                System.out.println("Format invalide\n");
                System.out.println("resaisissez svp ");
            }
        }

        System.out.println("frais payés (oui/non): ");
        if (BetterScanner.nextLine().equalsIgnoreCase("oui")) {
            Membre nouveauMembre = new Membre(nom, adresse, email, ville, province, codePostal);
            listeMembres.add(nouveauMembre);
            System.out.println("\nCompte membre créé! Voici son identifiant: " + nouveauMembre.getIdClient());
            //System.out.println("taille " + this.listeMembres.size());
        } else {
            System.out.println("Création de compte annulée.");
        }

    }

    /**
     * identifier le membre seulement
     * @param id id du membre à valider
     * @return un boolean qui valide ou pas l'identification du membre
     */
    public boolean identifierMembre(int id) {
        for (Membre membre : this.listeMembres) {
            if (membre.getIdClient() == id)
                return true;
        }
        return false;
    }

    /**
     * identifier par le courriel seulement
     * @param email emial du membre à valider
     * @return un boolean qui valide ou pas l'identification du membre
     */
    public boolean identifierMembreAvecEmail(String email) {
        for (Membre membre : this.listeMembres) {
            if (membre.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    /**
     * vérifie que l'email et l'id correspond au même membre
     * sinon il y a possibilité de se connecter avec l'email de
     * l'un et l'id d'un autre
     * @param email email du membre à valider
     * @param id id du membre à validé
     * @return un boolean qui valide ou pas l'identification du membre
     */
    public boolean identifierMembreEmailEtId(String email, int id) {
        for (Membre membre : this.listeMembres) {
            if (membre.getIdClient() == id && membre.getEmail().equalsIgnoreCase(email))
                return true;
        }
        return false;
    }

    /**
     * mettre à jour le statut du membre
     * @param id son identifiant
     */
    public void updateMembre(int id) {
        for (Membre membre: this.listeMembres) {
            if (membre.getIdClient() == id) {
                System.out.println("Informations du membre:");
                System.out.println("1) Nom complet: " + membre.getNom());
                System.out.println("2) Email: " + membre.getEmail());
                System.out.println("3) Adresse: " + membre.getAdresse());
                System.out.println("4) Ville: " + membre.getVille());
                System.out.println("5) Province: " + membre.getProvince());
                System.out.println("6) Code postal: " + membre.getCodePostal());
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
                                membre.setNom(nom);
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
                                membre.setEmail(email);
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
                                membre.setAdresse(adresse);
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
                                membre.setVille(ville);
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
                                membre.setProvince(province);
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
     * supprimer le compte du membre
     * @param id id du membre à supprimer
     */
    public void supprimerMembre(int id) {
        System.out.println("Confirmez-vous de vouloir supprimer ce compte? (oui/non)\n");
        String reponse = BetterScanner.nextLine();
        if (reponse.equalsIgnoreCase("oui"))
            listeMembres.removeIf(membre -> membre.getIdClient() == id);
        else
            System.out.println("Action annulée");
    }

    /**
     * afficher les informations et le code QR
     * @param email courriel du membre concerné
     */
    public void afficherInfos(String email) {
        for (Membre membre: this.listeMembres) {
            if (membre.getEmail().equalsIgnoreCase(email)) {
                System.out.println("Informations du membre:");
                System.out.println("Nom complet: " + membre.getNom());
                System.out.println("Identifiant: " + membre.getIdClient());
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
                break;
            }
        }
    }

    /**
     * afficher les infos pour la facture
     * @param id l'identifiant du mebre concerné
     */
    public void afficherFactureInfos(int id) {
        for (Membre membre: listeMembres) {
            if (membre.getIdClient() == id) {
                System.out.println("Nom du membre: " + membre.getNom());
                System.out.println("Numéro du membre: " + membre.getIdClient());
                System.out.println("Adresse du membre: " + membre.getAdresse());
                System.out.println("Ville du membre: " + membre.getVille());
                System.out.println("Province du membre: " + membre.getProvince());
                System.out.println("Code postal du membre: " + membre.getCodePostal());
                break;
            }
        }
    }

    /**
     * ajouter dans le fichier les infos pour la facture
     * @param id l'identifiant du mebre concerné
     */
    public void ecrireFactureInfos(int id, FileWriter fileWriter) throws IOException {
        for (Membre membre: listeMembres) {
            if (membre.getIdClient() == id) {
                fileWriter.write("Nom du membre: " + membre.getNom() + "\n");
                fileWriter.write("Numéro du membre: " + membre.getIdClient() + "\n");
                fileWriter.write("Adresse du membre: " + membre.getAdresse() + "\n");
                fileWriter.write("Ville du membre: " + membre.getVille() + "\n");
                fileWriter.write("Province du membre: " + membre.getProvince() + "\n");
                fileWriter.write("Code postal du membre: " + membre.getCodePostal() + "\n\n\n");
                break;
            }
        }
    }

    /**
     * afficher la liste des gens inscrits
     * @param listeIdMembres liste de membre à parcourir
     */
    public void afficherInscriptions(ArrayList<Integer> listeIdMembres) {
        System.out.println("Voici les inscriptions pour cette séance:");
        for (int id: listeIdMembres) {
            for (Membre membre: listeMembres) {
                if (id == membre.getIdClient()) {
                    System.out.println(membre.getNom());
                }
            }
        }
        System.out.println("------------------------------------------\n\n");
    }


    /**
     * trouver le nom du membre correspondant à l'id qu'on a
     * (nécessaire dans certains cas où on a seulement l'id)
     * @param id l'id en question
     * @return le nom du membre correspondant a l'id
     */
    public String trouverNomMembre(int id) {
        for (Membre membre: listeMembres) {
            if (membre.getIdClient() == id) {
                return membre.getNom();
            }
        }
        return "";
    }

    /**
     * Effectue le transfert des dossiers des membres à la compagnie
     */
    public void transfererDossiersRnB() {
        for (Membre membre: listeMembres) {
            System.out.println("Nom: " + membre.getNom());
            System.out.println("Identifiant: " + membre.getIdClient());
            System.out.println("Email: " + membre.getEmail());
            System.out.println("Adresse: " + membre.getAdresse());
            System.out.println("Ville: " + membre.getVille());
            System.out.println("Province: " + membre.getProvince());
            System.out.println("Code Postal: " + membre.getCodePostal());
            System.out.println("---------------------------------------\n");
        }
        System.out.println("\nAppuyez ENTER pour revenir");
        Scanner userInput = new Scanner(System.in);
        userInput.nextLine();
    }

    //getter et setter

    public ArrayList<Membre> getListeMembres() {
        return listeMembres;
    }

    public void setListeMembres(ArrayList<Membre> listeMembres) {
        this.listeMembres = listeMembres;
    }
}
