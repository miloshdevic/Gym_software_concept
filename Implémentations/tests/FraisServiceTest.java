import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FraisServiceTest {

    @Test
    void testFraisService() {
        ServiceControleur servControl=new ServiceControleur();
        Service service = new Service("Yoga", "01-10-2021", "01-10-2022","01-08-2021 10:10:10",  "12:30", "Lundi", 25, 75.00f,"", 100000001, "Apporter bouteille");
        CentreDonnees.repertoireServices.add(service);
        assertEquals((double)75f,servControl.getFrais(service.getIdService()));
        assertEquals(Double.NaN, servControl.getFrais(3));

    }

}
