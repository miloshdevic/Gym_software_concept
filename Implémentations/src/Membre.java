/**
 * classe pour le membre
 * hérite de la classe Client
 */
public class Membre extends Client {

    private boolean fraisPaye = false;
    private boolean copmteMarque = false;


    /**
     * constructeur de Membre
     * @param nom nom du membre
     * @param adresse adresse du membre
     * @param email email du membre
     * @param ville ville du membre
     * @param province province du membre
     * @param codePostal code postal du membre
     */
    public Membre(String nom, String adresse, String email, String ville, String province, String codePostal) {
        super(nom, adresse, email, ville, province, codePostal);
    }



    //getters et setters


    public Boolean getFraisPaye() {
        return this.fraisPaye;
    }

    public void setFraisPaye(Boolean fraisPaye) {
        this.fraisPaye = fraisPaye;
    }

    public Boolean getCopmteMarque() {
        return this.copmteMarque;
    }

    public void setCopmteMarque(Boolean copmteMarque) {
        this.copmteMarque = copmteMarque;
    }

}
