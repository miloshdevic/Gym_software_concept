/**
 * classe qui s'occupe des inscriptions des membres
 * aux différents services et ensuite sont stockées dans
 * le Centre de Données
 */
public class Inscription {

    private String dateHeureInscription;
    private String commentaires;
    private String nomSeance;
    private String recurrenceHebdo;
    private int idPro, idMembre, idSeance;


    /**
     * constructeur de inscription, les arguments sont auto-explicatifs
     * @param nomSeance nom de la séance
     * @param dateHeureInscription date et heure lors de l'inscription
     * @param recurrenceHebdo la récurrence hebdomadaire des séances du service
     * @param idMembre id du membre inscrit
     * @param idPro id du pro qui donne le service
     * @param idSeance id de la séance a laquelle le membre est inscrit
     * @param commentaires commentaires facultatifs
     */
    public Inscription(String nomSeance, String dateHeureInscription, String recurrenceHebdo, int idMembre, int idPro, int idSeance, String commentaires) {
        this.nomSeance = nomSeance;
        this.dateHeureInscription = dateHeureInscription;
        this.recurrenceHebdo = recurrenceHebdo;
        this.idMembre = idMembre;
        this.idPro = idPro;
        this.idSeance = idSeance;
        this.commentaires = commentaires;
    }


    //getters et setters

    public String getDateHeureInscription() {
        return dateHeureInscription;
    }

    public void setDateHeureInscription(String dateHeureInscription) {
        this.dateHeureInscription = dateHeureInscription;
    }

    public String getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(String commentaires) {
        this.commentaires = commentaires;
    }

    public String getNomSeance() {
        return nomSeance;
    }

    public void setNomSeance(String nomSeance) {
        this.nomSeance = nomSeance;
    }

    public String getRecurrenceHebdo() {
        return recurrenceHebdo;
    }

    public void setRecurrenceHebdo(String recurrenceHebdo) {
        this.recurrenceHebdo = recurrenceHebdo;
    }

    public int getIdPro() {
        return idPro;
    }

    public void setIdPro(int idPro) {
        this.idPro = idPro;
    }

    public int getIdMembre() {
        return idMembre;
    }

    public void setIdMembre(int idMembre) {
        this.idMembre = idMembre;
    }

    public int getIdSeance() {
        return idSeance;
    }

    public void setIdSeance(int idSeance) {
        this.idSeance = idSeance;
    }
}
