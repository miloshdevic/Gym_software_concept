import java.util.ArrayList;
import java.util.Collections;

/**
 * classe pour le professionnel
 * hérite de la classe Client
 */
public class Professionnel extends Client {

    public String[] domaineExpertise;
    private int salaireDu;


    /**
     * constructeur de Professionnel
     * @param nom nom du professionnel
     * @param adresse adresse du professionnel
     * @param email email du professionnel
     * @param ville ville du professionnel
     * @param province province du professionnel
     * @param codePostal code postal du professionnel
     * @param domaineExpertise domaine d'expertise du professionnel
     */
    public Professionnel(String nom, String adresse, String email, String ville, String province,
                         String codePostal,String[] domaineExpertise) {
        super(nom, adresse, email, ville, province, codePostal);
        this.domaineExpertise = domaineExpertise;
    }


    //getters et setters

    public String[] getDomaineExpertise() {
        return this.domaineExpertise;
    }

    public void setDomaineExpertise(String[] domaineExpertise) {
        this.domaineExpertise = domaineExpertise;
    }

    public int getSalaireDu() {
        return this.salaireDu;
    }

    public void setSalaireDu(int salaireDu) {
        this.salaireDu = salaireDu;
    }

}
