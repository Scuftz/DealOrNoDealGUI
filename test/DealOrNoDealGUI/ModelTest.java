package DealOrNoDealGUI;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 * PDC Assignment 2
 * This is the Model Testing Class, testing the methods from the Model
 * @author Shivneel Singh (18021394)
 * @since 11/06/2020
 */
public class ModelTest {
    
    private Model model;
    
    public ModelTest() {
    }
    
    @Before
    public void setUp() {
        model = new Model();
    }

    /**
     * Test of calculateBankOffer method, of class Model.
     * This test method calculates the bank offer of the situation where 2 cases are left,
     * and compares it with the expected bank offer
     */
    @Test
    public void testCalculateBankOffer() {
        System.out.println("Test: Calculate Bank Offer");
        int roundNumber = 8;
        int totalCasesLeft = 2;
        ArrayList<Case> cases = new ArrayList();
        cases.add(new Case(1, 1000));
        cases.add(new Case(2, 2000));
        int expResult = 1500;
        int bankOffer = model.calculateBankOffer(roundNumber, totalCasesLeft, cases);
        assertEquals(expResult, bankOffer);
    }
}