import java.util.Scanner;

/**
 * classe pour gérer les erreurs d'entrées que
 * l'utilisateur pourrait faire
 */
public class BetterScanner {

    private static Scanner scan=new Scanner(System.in);

    /**
     * gérer les erreurs sur la ligne de commande
     * @return le input de l'utilisateur lorsqu'il faut mettre des chiffres (format adéquat)
     */
    public static int nextInt() {

        int val = 0;
        boolean input=true;
        while (input) {
            try {
                String temp = scan.nextLine();
                val = Integer.parseInt(temp);
                input = false;
            }catch (Exception e){
                System.err.println("Mauvaise entrée, veuillez réessayer");
            }
        }
        return val;
    }

    /**
     * gérer les erreurs sur la ligne de commande
     * @return le input de l'utilisateur sur la ligne de commande
     */
    public static String nextLine() {
        return scan.nextLine();
    }


}
