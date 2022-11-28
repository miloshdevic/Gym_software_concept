import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NomProfessionnelTest {

    @Test
    void testNom() {
        String[] qual= {"Yoga","Fitness"};
        Professionnel pro = new Professionnel("bob", "123 nom" ,"bob@test.com", "montreal",
                "RS", "J2W1X4", qual);
        ProfessionnelControleur proControl=new ProfessionnelControleur();
        ArrayList<Professionnel> listePro =new ArrayList<Professionnel>(Arrays.asList(pro));
        proControl.setListePro(listePro);
        assertEquals("bob",proControl.trouverNomPro(pro.getIdClient()));
        assertEquals("",proControl.trouverNomPro(888));
    }
}
