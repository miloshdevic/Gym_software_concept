import java.util.ArrayList;
import java.util.Scanner;

/**
 * La classe Rapport crér les rapport nécessaires à la procédure comptable
 */
class RapportSynthese {
    private static ArrayList<Integer> listeIdPro = new ArrayList<Integer>();

    /**
     * Affichage des statistiques globale sur les professionnels offrants des services
     */
    public static void rapportSynthese() {

        listeIdPro = new ArrayList<Integer>();
        System.out.println("------------------------");
        System.out.println("|   Comptes Payables   |");
        System.out.println("------------------------");

        for (Inscription inscription : CentreDonnees.listeInscriptions) {
            int idPro = inscription.getIdPro();
            if (!listeIdPro.contains(idPro)) {
                listeIdPro.add(idPro);
                int nbrServices = 0;
                float frais = 0;
                int montantAVerser = 0;

                System.out.println("ID du professeur: " + idPro);

                for (Service service : CentreDonnees.repertoireServices) {
                    if (inscription.getIdSeance() == service.getIdService()) {
                        frais = service.getFrais();
                    }
                    if (service.getProfessionnel() == idPro)
                        ++nbrServices;
                }

                for (Inscription inscriptions : CentreDonnees.listeInscriptions) {
                    if (inscriptions.getIdPro() == idPro) {
                        montantAVerser += frais;
                    }
                }
                System.out.println("Nombre de services offerts: " + nbrServices);
                System.out.println("Frais du professionnel: " + montantAVerser + "$\n");
            }
        }


        //puis on imprime la version globale

        listeIdPro = new ArrayList<Integer>();
        ArrayList<Integer> servicesUniques = new ArrayList<>();
        int nbrPro = 0;
        int nbrService = 0;
        double fraisTotal = 0;

        for (Service service : CentreDonnees.repertoireServices) {
            for (Inscription inscription : CentreDonnees.listeInscriptions) {
                int serviceId = inscription.getIdSeance();
                int profId = inscription.getIdPro();

                if (service.getIdService() == inscription.getIdSeance())
                    fraisTotal += service.getFrais();
                if (!servicesUniques.contains(serviceId)) {
                    servicesUniques.add(serviceId);
                    ++nbrService;
                }
                if (!listeIdPro.contains(profId)) {
                    listeIdPro.add(profId);
                    ++nbrPro;
                }
            }
        }
        System.out.println("-------------------------");
        System.out.println("|   Rapport Synthèse    |");
        System.out.println("-------------------------");
        System.out.println("Nombre de professionnels du #GYM: " + nbrPro);
        System.out.println("Nombre de service offert : " + nbrService);
        System.out.println("Frais total: " + fraisTotal + '$');
        System.out.println("Appuyez ENTER pour revenir");
        Scanner userInput = new Scanner(System.in);
        userInput.nextLine();
    }

}