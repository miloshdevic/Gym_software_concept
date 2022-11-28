import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ConfirmationSeanceTest {


    @Test
    void testConfirmation() {
        Seance seance = new Seance();
        seance.inscrireMembre("Yoga", "01-08-2021 10:10:10", "Lundi",
                100000000, 100000001, 1231000, "Apporter bouteille");
        assertTrue(seance.confirmerInscription(100000000, 1231000));
        assertFalse(seance.confirmerInscription(200000000, 1231000));
        assertFalse(seance.confirmerInscription(100000000, 3211000));
    }
}