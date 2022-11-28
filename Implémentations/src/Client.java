/**
 * classe client qui est parent pour les classes
 * Professionnel et Membre
 */
public class Client {

    private int idClient;
    private String nom;
    private String email;
    private String adresse;
    private String ville;
    private String province;
    private String codePostal;

    /**
     * constructeur de Client
     * @param nom nom du client
     * @param adresse adresse du client
     * @param email email du client
     * @param ville ville du client
     * @param province province du client
     * @param codePostal code postal du client
     */
    public Client(String nom, String adresse, String email, String ville, String province, String codePostal) {
        this.idClient = ClientId.getInstance().generateNewId();//100000000 + (int) (Math.random() * 900000000);
        //ClientId.getInstance().generateNewId();
        this.nom = nom;
        this.email = email;
        this.adresse = adresse;
        this.ville = ville;
        this.province = province;
        this.codePostal = codePostal;
    }


    //getters et setters


    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return this.adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }
}
