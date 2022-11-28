import java.util.*;

/**
 * classe pour les séances
 */
public class Seance {

    /**
     * recuperer les infos a print
     * @param id id du membre à trouver
     * @return certaines informations cruciales des membres inscrits dans un service
     * correspondant à l'id
     */
    public ArrayList<Object[]> getServicesInscritsMembre(int id) {
        ArrayList<Object[]> infosServices = new ArrayList<>();
        for (Inscription inscription: CentreDonnees.listeInscriptions) {
            if (inscription.getIdMembre() == id) {
                Object[] infos = new Object[3];
                infos[0] = new String(inscription.getRecurrenceHebdo());
                infos[1] = inscription.getIdPro();
                infos[2] = new String(inscription.getNomSeance());
                infosServices.add(infos);
            }
        }
        return infosServices;
    }


    /**
     * recuperer les infos a print
     * @param id id du professionnel à trouver
     * @return certaines informations cruciales des membres inscrits dans un service
     * correspondant à l'id
     */
    public ArrayList<Object[]> getServicesInscritsPro(int id) {
        ArrayList<Object[]> infosServices = new ArrayList<Object[]>();
        for (Inscription inscription: CentreDonnees.listeInscriptions) {
            if (inscription.getIdPro() == id) {
                Object[] infos = new Object[4];
                infos[0] = new String(inscription.getRecurrenceHebdo());
                infos[1] = new String(inscription.getDateHeureInscription());
                infos[2] = inscription.getIdMembre();
                infos[3] = inscription.getIdSeance();
                infosServices.add(infos);
            }
        }
        return infosServices;
    }


    /**
     * pour avoir le nombre de personnes inscrites
     * @param idSeance id de la séance en question
     * @return le nombre de personnes inscrites
     */
    public int getNbrInscrit(int idSeance) {
        int compte = 0;
        for (Inscription inscription: CentreDonnees.listeInscriptions) {
            if (inscription.getIdSeance() == idSeance) {
                compte++;
            }
        }
        return compte;
    }


    /**
     * inscrire un membre a une seance
     * @param nomSeance nom de la seance
     * @param dateHeureInscription moment de l'incsription
     * @param recurrenceHebdo recurrence hebdomadaire
     * @param idMembre identifiant du membre qui s'inscrit
     * @param idPro identifiant du membre qui le donne
     * @param idSeance code de seance
     * @param commentaires commentaires facultatifs
     */
    public void inscrireMembre(String nomSeance, String dateHeureInscription, String recurrenceHebdo, int idMembre,
                               int idPro, int idSeance, String commentaires) {

        Inscription nouvelleInscription = new Inscription(nomSeance, dateHeureInscription, recurrenceHebdo, idMembre, idPro,
                idSeance, commentaires);
        CentreDonnees.listeInscriptions.add(nouvelleInscription);

        System.out.println("Date et heure actuelles (JJ-MM-AAAA HH:MM:SS): " + dateHeureInscription);
        System.out.println("Récurrence hebdomadaire: " + recurrenceHebdo);
        System.out.println("Numéro du professionnel: " + idPro);
        System.out.println("Numéro du membre: " + idMembre);
        System.out.println("Code de la séance: " + idSeance);
        System.out.println("Commentaires (facultatif): " +commentaires);
    }


    /**
     * recuperer les inscriptions pour la seance
     * @param nom son nom
     * @return la liste des id des membres inscrits
     */
    public ArrayList<Integer> getInscriptions(String nom) {
        ArrayList<Integer> listeIdMembres = new ArrayList<>();
        for (Inscription inscription: CentreDonnees.listeInscriptions) {
            if (inscription.getNomSeance().equalsIgnoreCase(nom)) {
                listeIdMembres.add(inscription.getIdMembre());
            }
        }
        return listeIdMembres;
    }

    /**
     * confirmer l'inscription
     * @param idMembre identifiant du membre
     * @param idService code du service
     * @return si oui ou non un membre confirme son incsription
     */
    public boolean confirmerInscription(int idMembre, int idService) {
        for (Inscription inscription: CentreDonnees.listeInscriptions) {
            if (inscription.getIdMembre() == idMembre && inscription.getIdSeance() == idService)
                return true;
        }
        return false;
    }


}
