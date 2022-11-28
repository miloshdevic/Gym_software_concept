import java.io.IOException;
import java.util.Scanner;

/**
 * classe main qui fait rouler le programme
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Accueil accueil = new Accueil();
        accueil.choisirPlateforme();
    }
}
