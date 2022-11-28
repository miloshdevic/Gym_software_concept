import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * classe controleur des services
 * traite les fonctionnalités pertinentes
 */
public class ServiceControleur {

    private ProfessionnelControleur professionnelControleur = new ProfessionnelControleur();

    /**
     * creer un nouveau service
     * @param idPro identifiant du professionnel qui le demande
     */
    public void creerService(int idPro) {

        Pattern formatARespecter; //sert à vérifier si certaines données sont entrées correctement
        Matcher matcher;
        System.out.println("Veuillez saisir les informations demandées:\n");

        String nom, debut, fin, heureActuelle, heure, recurrence, type, description;
        int capacite, frais;


        System.out.print("Nom du service: ");
        nom = BetterScanner.nextLine();

        //chercher si le service existe déjà dans le repertoire des Services
        for (Service service : CentreDonnees.repertoireServices) {
            if (service.getNom().equalsIgnoreCase(nom)) {
                System.out.println("Service existe déjà!");
                return; //retourner à la page d'accueil
            }
        }

        formatARespecter = Pattern.compile("[0-31]{2}+-[0-12]{2}+-[0-9]{4}");
        while (true) {
            System.out.println("date de début du service (JJ-MM-AAAA): ");
            debut = BetterScanner.nextLine();
            matcher = formatARespecter.matcher(debut);
            if (matcher.find()) {
                break;
            } else {
                System.out.println("Format invalide\n");
                System.out.println("Resaisissez svp ");
            }
        }

        while (true) {
            System.out.println("date de fin du service (JJ-MM-AAAA): ");
            fin = BetterScanner.nextLine();
            matcher = formatARespecter.matcher(fin);
            if (matcher.find()) {
                break;
            } else {
                System.out.println("Format invalide\n");
                System.out.println("Resaisissez svp ");
            }
        }

        formatARespecter = Pattern.compile("^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$");
        while (true) {
            System.out.println("heure à laquelle le service est donné (HH:MM): ");
            heure = BetterScanner.nextLine();
            matcher = formatARespecter.matcher(heure);
            if (matcher.find()) {
                break;
            } else {
                System.out.println("Format invalide\n");
                System.out.println("Resaisissez svp ");
            }
        }

        System.out.println("récurrence hebdomadaire (mettez les jours sur une ligne): ");
        recurrence = BetterScanner.nextLine();

        while (true) {
            System.out.println("capacité maximale: ");
            capacite = Integer.parseInt(BetterScanner.nextLine());
            if (capacite <= 30) {
                break;
            } else {
                System.out.println("capacité maximale de 30 personnes!");
                System.out.println("Resaisissez svp ");
            }
        }

        while (true) {
            System.out.println("frais du service (jusqu'à 100$): ");
            frais = Integer.parseInt(BetterScanner.nextLine());
            if (frais <= 100)
                break;
            else
                System.out.println("Les frais ne doivent pas dépasser 100$");
            System.out.println("Resaisissez svp ");
        }

        while (true) {
            System.out.println("Choisissez le type du service (santé physique, exercice physique): ");
            type = BetterScanner.nextLine();
            if (type.equalsIgnoreCase("santé physique") || type.equalsIgnoreCase("sante physique")
                    || type.equalsIgnoreCase("exercice physique"))
                break;
            else
                System.out.println("Ce type de service n'est pas fournis ici");
            System.out.println("Resaisissez svp ");
        }

        while (true) {
            System.out.println("courte description facultative du service (maximum de 100 caractères): ");
            description = BetterScanner.nextLine();
            if (description.length() <= 100)
                break;
            else
                System.out.println("maximum de 100 caractères");
            System.out.println("Resaisissez svp ");
        }

        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        heureActuelle = dateTime.format(format);
        //System.out.println("date et heure actuelles (JJ-MM-AAAA HH:MM:SS): ");


        Service service = new Service(nom, debut, fin, heureActuelle, heure, recurrence, capacite, frais, type, idPro, description);
        CentreDonnees.repertoireServices.add(service);
        System.out.println("\nService créé! Voici son identifiant: " + service.getIdService());
    }


    /**
     * identifier un service
     * @param id code du service à valider
     * @return un boolean qui valide ou pas l'identification du service
     */
    public boolean identifierService(int id) {
        for (Service service: CentreDonnees.repertoireServices) {
            if (service.getIdService() == id) {
                return true;
            }
        }
        return false;
    }

    /**
     * identifier si le nom entré est un service qui existe
     * @param nom nom du service à valider
     * @return un boolean qui valide ou pas l'identification du service
     */
    public boolean identifierServiceParNom(String nom) {
        for (Service service: CentreDonnees.repertoireServices) {
            if (service.getNom().equalsIgnoreCase(nom)) {
                return true;
            }
        }
        return false;
    }


    /**
     * mettre a jour une info du service
     * @param id son code
     */
    public void updateService(int id) {
        for (Service service: CentreDonnees.repertoireServices) {
            if (service.getIdService() == id) {
                System.out.println("Informations du service:");
                System.out.println("1) Nom du service: " + service.getServicesFournis());
                System.out.println("2) Date de début: " + service.getDateDebut());
                System.out.println("3) Date de fin: " + service.getDateFin());
                System.out.println("4) Heure du service: " + service.getHeure());
                System.out.println("5) Description (facultatif): " + service.getDescription());
                System.out.println("6) Service hebdomadaire: " + service.getRecurrenceHebdo());
                System.out.println("7) Capacité maximale: " + service.getCapaciteMax());
                System.out.println("8) ID du professionel en charge : " + service.getProfessionnel());
                System.out.println("9) Frais du service: " + service.getFrais());
                System.out.println("Entrez le numbre associaté avec les informations que vous souhaitez modifier:");


                Pattern formatARespecter; //sert à vérifier si certaines données sont entrées correctement
                Matcher matcher;
                int choice = Integer.parseInt(BetterScanner.nextLine());
                switch (choice) {
                    case 1:
                        System.out.print("Nouveau nom du service: ");
                        String nom = BetterScanner.nextLine();
                        //a completer
                        while (true) {
                            //chercher si le service existe déjà dans le repertoire des Services
                            for (Service trouverService : CentreDonnees.repertoireServices) {
                                if (trouverService.getNom().equalsIgnoreCase(nom)) {
                                    System.out.println("Service existe déjà!");
                                } else {
                                    service.setServicesFournis(nom);
                                    break;
                                }
                            }
                        }

                    case 2:
                        formatARespecter = Pattern.compile("[0-31]{2}+-[0-12]{2}+-[2020-2100]{4}");
                        //"([0-9]{2})-([0-9]{2})-([0-9]{4})"
                        while (true) {
                            System.out.print("Nouvelle date de début (JJ-MM-AAAA): ");
                            String debut = BetterScanner.nextLine();
                            matcher = formatARespecter.matcher(debut);
                            if (matcher.find()) {
                                service.setDateDebut(debut);
                                break;
                            } else {
                                System.out.println("Format invalide\n");
                                System.out.println("Resaisissez svp ");
                            }
                        }
                        break;

                    case 3:
                        formatARespecter = Pattern.compile("[0-31]{2}+-[0-12]{2}+-[2020-2100]{4}");
                        while (true) {
                            System.out.println("date de fin du service (JJ-MM-AAAA): ");
                            String fin = BetterScanner.nextLine();
                            matcher = formatARespecter.matcher(fin);
                            if (matcher.find()) {
                                System.out.print("Nouvelle date de fin (JJ-MM-AAAA): ");
                                break;
                            } else {
                                System.out.println("Format invalide\n");
                                System.out.println("Resaisissez svp ");
                            }
                        }
                        break;

                    case 4:
                        formatARespecter = Pattern.compile("^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$");
                        while (true) {
                            System.out.print("Nouvelle heure du service (HH:MM): ");
                            String heure = BetterScanner.nextLine();
                            matcher = formatARespecter.matcher(heure);
                            if (matcher.find()) {
                                service.setHeure(heure);
                                break;
                            } else {
                                System.out.println("Format invalide\n");
                                System.out.println("Resaisissez svp ");
                            }
                        }
                        break;

                    case 5:
                        while (true) {
                            System.out.print("Nouvelle description (maximum de 100 caractères): ");
                            String description = BetterScanner.nextLine();
                            if (description.length() <= 100) {
                                service.setDescription(description);
                                break;
                            } else {
                                System.out.println("maximum de 100 caractères");
                            }
                            System.out.println("Resaisissez svp ");
                        }
                        break;

                    case 6:
                        System.out.print("Nouvelle récurrence hebdomadaire: ");
                        service.setRecurrenceHebdo(BetterScanner.nextLine());
                        break;

                    case 7:
                        while (true) {
                            System.out.print("Nouvelle capacité maximale: ");
                            int capacite = Integer.parseInt(BetterScanner.nextLine());
                            if (capacite <= 30) {
                                service.setCapaciteMax(capacite);
                                break;
                            } else {
                                System.out.println("capacité maximale de 30 personnes!");
                                System.out.println("Resaisissez svp ");
                            }
                        }
                        break;

                    case 8:
                        while (true) {
                            System.out.print("Nouveau ID du professionel en charge: ");
                            int idPro = Integer.parseInt(BetterScanner.nextLine());
                            if (professionnelControleur.identifierPro(idPro)) {
                                service.setProfessionnel(BetterScanner.nextInt());
                                break;
                            } else {
                                System.out.println("Identifiant invalide");
                                System.out.println("Resaisissez svp ");
                            }
                        }
                        break;

                    case 9:
                        while (true) {
                            System.out.print("Nouveaux frais du service: ");
                            int frais = Integer.parseInt(BetterScanner.nextLine());
                            if (frais <= 100) {
                                service.setFrais(frais);
                                break;
                            } else {
                                System.out.println("Les frais ne doivent pas dépasser 100$");
                                System.out.println("Resaisissez svp ");
                            }
                        }
                        break;

                    default:
                        System.err.println("Invalid choice.");
                        return;
                }
                System.out.println("Modifications faites!");
                return;
            }
        }
    }


    /**
     * supprimer un service du repertoire
     * @param id son code
     */
    public void supprimerService(int id) {
        System.out.println("Confirmez-vous de vouloir supprimer ce service? (oui/non)\n");
        String reponse = BetterScanner.nextLine();
        if (reponse.equalsIgnoreCase("oui"))
            CentreDonnees.repertoireServices.removeIf(service -> service.getIdService() == id);
        else
            System.out.println("Action annulée");
    }

    /**
     * afficher tous les services du repertoire
     */
    public void afficherRepertoireServices() {
        //Scanner userInput = new Scanner(System.in);
        LocalDate date = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = date.format(format);
        LocalDateTime dateTime = LocalDateTime.now();
        format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDateTime = dateTime.format(format);
        System.out.println("Services disponibles (" + formattedDate + ") :\n");
        System.out.println("Voici la liste des séances:");

        for (Service service: CentreDonnees.repertoireServices) {
            System.out.println("----------------------------------------");
            System.out.println("Nom: " + service.getNom());
            System.out.println("Date de début: " + service.getDateDebut());
            System.out.println("Date de fin: " + service.getDateFin());
            System.out.println("Récurrence hebdomadaire: " + service.getRecurrenceHebdo());
            System.out.println("Description: " + service.getDescription() + '\n');
        }
    }

    /**
     * verifier s'il y a de la place
     * @param nom nom du service
     * @param taille nombre de personnes inscrites en ce moment
     * @return un boolean qui valide ou pas s'il y a de la place pour s'inscrire au service
     */
    public boolean verifierDispo(String nom, int taille) {
        for (Service service: CentreDonnees.repertoireServices) {
            if (service.getNom().equalsIgnoreCase(nom)) {
                return  taille < service.getCapaciteMax();
            }
        }
        return false;
    }

    /**
     * recuperer les infos de l'inscription
     * @param nom nom de la seance
     * @return les infos nécessaire pour l'inscription dans un tableau Object[]
     */
    public Object[] getInfosInscription(String nom) {
        for (Service service: CentreDonnees.repertoireServices) {
            if (service.getNom().equalsIgnoreCase(nom)) {
                LocalDate date = LocalDate.now();
                DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                String formattedDate = date.format(format);
                LocalDateTime dateTime = LocalDateTime.now();
                format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String formattedDateTime = dateTime.format(format);
                String dateActuelle = formattedDate;
                String dateService = service.getRecurrenceHebdo();
                int idPro = service.getProfessionnel();
                int idSeance = service.getIdService();
                String description = service.getDescription();
                Object[] infos = new Object[6];
                infos[0] = new String(nom);
                infos[1] = new String(dateActuelle);
                infos[2] = new String(dateService);
                infos[3] = idPro;
                infos[4] = idSeance;
                infos[5] = new String(description);
                return infos;
            }
        }
        return null;
    }

    /**
     * recuperer les frais pour le service
     * @param id id du service en question
     * @return les frais du service
     */
    public double getFrais(int id) {
        for (Service service: CentreDonnees.repertoireServices) {
            if (service.getIdService() == id) {
                return service.getFrais();
            }
        }
        return Double.NaN;
    }


    /**
     * afficher les infos du service
     * @param id son code
     */
    public void afficherService(int id) {
        for (Service service: CentreDonnees.repertoireServices) {
            if (id == service.getIdService()) {
                LocalDate date = LocalDate.now();
                DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                String formattedDate = date.format(format);
                LocalDateTime dateTime = LocalDateTime.now();
                format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String formattedDateTime = dateTime.format(format);
                System.out.println("-------------------------------");
                System.out.println("Voici les infos pour la séance:");
                System.out.println("Heure actuelle: " + formattedDate);
                System.out.println("Nom du service: " + service.getNom());
                System.out.println("Date de début: " + service.getDateDebut());
                System.out.println("Date de fin: " + service.getDateFin());
                System.out.println("Heure du service: " + service.getHeure());
                System.out.println("Description (facultatif): " + service.getDescription());
                System.out.println("Service hebdomadaire: " + service.getRecurrenceHebdo());
                System.out.println("Capacité maximale: " + service.getCapaciteMax());
                System.out.println("ID du professionel en charge : " + service.getProfessionnel());
                System.out.println("Frais du service: " + service.getFrais());
            }
        }
    }

    /**
     * afficher les informations demandées une fois que la confirmation est faite
     * @param idMembre identifiant du membre concerné
     * @param idService code de service
     */
    public void afficherApresConfirmation(int idMembre, int idService) {
        for (Service service: CentreDonnees.repertoireServices) {
            if (idService == service.getIdService()) {
                LocalDate date = LocalDate.now();
                DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                String formattedDate = date.format(format);
                LocalDateTime dateTime = LocalDateTime.now();
                format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String formattedDateTime = dateTime.format(format);
                System.out.println("-------------------------------");
                System.out.println("Voici les infos pour la séance:");
                System.out.println("Heure actuelle: " + formattedDate);
                System.out.println("ID du professionel en charge : " + service.getProfessionnel());
                System.out.println("Numéro du membre: " + idMembre);
                System.out.println("Code de la séance: " + idService);
                System.out.println("Description (facultatif): " + service.getDescription() + "\n\n");
            }
        }
    }

}
