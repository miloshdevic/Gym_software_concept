/**
 * classe "singleton" qui s'assure de ne pas avoir deux fois le même identifiant
 * pour les services
 */
public class ServiceId {

    private static final ServiceId idManager = new ServiceId();
    private int lastId = 10;

    private ServiceId() {}


    public static ServiceId getInstance() {
        return idManager;
    }

    public int generateNewId() {
        return this.lastId++;
    }

}
