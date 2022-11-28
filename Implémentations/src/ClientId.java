/**
 * classe "singleton" qui s'assure de ne pas avoir deux fois le même identifiant
 * pour un client (membre et professionnel)
 */
public class ClientId {

    private static final ClientId idManager = new ClientId();
    private int lastId = 100000000;

    private ClientId() {}


    public static ClientId getInstance() {
        return idManager;
    }

    public int generateNewId() {
        return this.lastId++;
    }

}