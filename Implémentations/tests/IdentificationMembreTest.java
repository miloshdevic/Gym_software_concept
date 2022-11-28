import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IdentificationMembreTest {

    @Test
    void testIdentification() {
        Membre membre = new Membre("joe", "325 dskd","joe@test.com", "montreal", "QC", "H2H1H1");
        ArrayList<Membre> listeMembres=new ArrayList<Membre>(Arrays.asList(membre));
        MembreControleur membreControl=new MembreControleur();
        membreControl.setListeMembres(listeMembres);
        assertFalse(membreControl.identifierMembreEmailEtId("joe@test.com", 200000000));
        assertTrue(membreControl.identifierMembreEmailEtId("joe@test.com", membre.getIdClient()));
        assertFalse(membreControl.identifierMembreEmailEtId("notjoe@test.com", membre.getIdClient()));

    }
}
