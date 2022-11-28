import java.io.IOException;

/**
 * classe d'accueil du #GYM où l'on choisit si on veut
 * simuler l'application mobile ou la réception
 */
public class Accueil {

    static MembreControleur membreControleur = new MembreControleur();
    static ProfessionnelControleur professionnelControleur = new ProfessionnelControleur();
    static ServiceControleur serviceControleur = new ServiceControleur();
    static Seance seance = new Seance();
    static GymReception gymReception = new GymReception();
    static GymMobile gymMobile = new GymMobile();



    /**
     * afficher le menu à choisir (mobile et réception)
     */
    public void choisirPlateforme() throws IOException {
        int choix;

        while (true) {
            System.out.println(
                    "+---------------------------+\n" +
                            "|       Menu à choisir      |\n" +
                            "+---------------------------+\n\n" +
                            "1- naviguer sur l'application mobile de #GYM\n" +
                            "2- se servir de la réception de #GYM\n" +
                            "3- quitter\n");


            choix = BetterScanner.nextInt();
            switch(choix) {
                case 1:
                    gymMobile.afficherMobile();
                    break;
                case 2:
                    gymReception.afficherMenu();
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.err.println("Choix invalide");
            }
        }
    }






}
