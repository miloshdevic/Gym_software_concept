import java.util.ArrayList;

/**
 * classe pour les services
 */
public class Service {

    private String nom;
    private String dateDebut;
    private String dateFin;
    private String dateHeureActuelles;
    private String heure;
    private int capaciteMax;
    private float frais;
    private String description;
    private String servicesFournis;
    private ArrayList<Service> repertoireService;
    private int idService;
    private int professionnel;
    private String recurrenceHebdo;


    /**
     * constructeur de service
     * @param nom nom du service
     * @param dateDebut date de debut du service
     * @param dateFin date de fin du service
     * @param dateHeureActuelles date et heure quand cela a été créé
     * @param heure heure à laquelle c'est donné
     * @param recurrenceHebdo quels jours de la semaine le service se donne
     * @param capaciteMax capactié maximale permise pour le service
     * @param frais frais de participation
     * @param servicesFournis type de service
     * @param professionnel identifiant du professionnel le donnant
     * @param description description facultative du service
     */
    public Service(String nom, String dateDebut, String dateFin, String dateHeureActuelles, String heure, String recurrenceHebdo,
                   int capaciteMax, float frais, String servicesFournis, int professionnel, String description) {
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.dateHeureActuelles = dateHeureActuelles;
        this.heure = heure;
        this.capaciteMax = capaciteMax;
        this.frais = frais;
        this.description = description;
        this.servicesFournis = servicesFournis;
        this.professionnel = professionnel;
        this.recurrenceHebdo = recurrenceHebdo;

        //création d'un identifiant en fonction du type du service fourni et de l'id du professionnel
        int idTemp = ServiceId.getInstance().generateNewId();
        this.idService = mettreBonId(idTemp, this.servicesFournis, this.professionnel);
    }

    /**
     * gestion de l'id du service afin d'avoir le format voulue
     * @param idService id du service qui se sert d'un singleton pour pas avoir deux fois le même
     * @param type début du id depend du type
     * @param idPro avoir les deux derniers chiffres de l'id du professionnel
     * @return l'id qui respecte le format demandé
     */
    public int mettreBonId(int idService, String type, int idPro) {
        int y, i = 0;
        String bonId = "";
        while(i < 2) {
            y = idPro % 10;
            bonId = "" + y + "" + bonId;
            idPro = idPro / 10;
            i++;
        }

        if (type.equalsIgnoreCase("exercice physique")) {
            bonId = "123" + idService + bonId;
        } else {
            bonId = "321" + idService;
        }
        return Integer.parseInt(bonId);
    }



    //getters et setters


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDateDebut() {
        return this.dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return this.dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public String getDateHeureActuelles() {
        return this.dateHeureActuelles;
    }

    public void setDateHeureActuelles(String dateHeureActuelles) {
        this.dateHeureActuelles = dateHeureActuelles;
    }

    public String getHeure() {
        return this.heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public int getCapaciteMax() {
        return this.capaciteMax;
    }

    public void setCapaciteMax(int capaciteMax) {
        this.capaciteMax = capaciteMax;
    }

    public float getFrais() {
        return this.frais;
    }

    public void setFrais(float frais) {
        this.frais = frais;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getServicesFournis() {
        return this.servicesFournis;
    }

    public void setServicesFournis(String servicesFournis) {
        this.servicesFournis = servicesFournis;
    }

    public ArrayList<Service> getRepertoireService() {
        return this.repertoireService;
    }

    public void setRepertoireService(ArrayList<Service> repertoireService) {
        this.repertoireService = repertoireService;
    }

    public int getIdService() {
        return this.idService;
    }

    public void setIdService(int idService) {
        this.idService = idService;
    }

    public int getProfessionnel() {
        return this.professionnel;
    }

    public void setProfessionnel(int professionnel) {
        this.professionnel = professionnel;
    }

    public String getRecurrenceHebdo() {
        return this.recurrenceHebdo;
    }

    public void setRecurrenceHebdo(String recurrenceHebdo) {
        this.recurrenceHebdo = recurrenceHebdo;
    }
}
