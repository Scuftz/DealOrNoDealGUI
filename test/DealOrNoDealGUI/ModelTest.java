/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DealOrNoDealGUI;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author shivn
 */
public class ModelTest {
    
    private Model model;
    
    public ModelTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        model = new Model();
    }
    
    @After
    public void tearDown() {
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