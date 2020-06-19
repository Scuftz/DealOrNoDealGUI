package DealOrNoDealGUI;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * PDC Assignment 2
 * This is the Database Testing Class, used to test methods from the DB
 * @author Shivneel Singh (18021394)
 * @since 11/06/2020
 */
public class DatabaseTest
{    
    private Database databaseTest;
    
    public DatabaseTest() {
    }
    
    @Before
    public void setUp() {
        databaseTest = new Database();
    }

    /**
     * Test of checkLogin method, of class Database.
     * Checks a successful login of a player who already exists in the database
     */
    @Test
    public void testSuccessLogin() {
        System.out.println("Test: Successful Login");
        String un = "John";
        String pw = "john";
        boolean expResult = true;
        boolean result = databaseTest.checkLogin(un, pw);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of checkLogin method, of class Database.
     * An incorrect login is a player who's username exist but passwords do not match
     */
    @Test
    public void testBadLogin() {
        System.out.println("Test: Failed Login");
        String un = "John";
        String pw = "notjohn";
        boolean expResult = false;
        boolean result = databaseTest.checkLogin(un, pw);
        assertEquals(expResult, result);
    }
    

    /**
     * Test of getAllTimeHighScore method, of class Database.
     * This method should check that the all time high score of all time matching the expected result
     * As of the time of writing this test, the high score is: $56,686
     */
    @Test
    public void testGetAllTimeHighScore() {
        System.out.println("Test: Get All Time High Score");
        Integer expResult = 56686;
        Integer result = databaseTest.getAllTimeHighScore();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getPlayerHighScore method, of class Database.
     * This test method checks that the getPlayerHighScore returns a player's high score correctly
     * A player (Smith) and his score it currently set as 56686
     */
    @Test
    public void testGetPlayerHighScore() {
        System.out.println("Test: Get Individual Player Score");
        Integer expResult = 56686;
        Integer result = databaseTest.getPlayerHighScore("Smith");
        assertEquals(expResult, result);
    }

    /**
     * Test of updateScore method, of class Database.
     * This test method checks if the database successfully can update a player's score
     * It grabs the player current score, increments it by 100 and updates it
     * It then checks if the new score is matching the expected score
     */
    @Test
    public void testUpdateScore() {
        System.out.println("Test: Update Player Score");
        String username = "playerTest";
        int score = databaseTest.getPlayerHighScore(username);
        int expResult = score + 100;
        score += 100;
        databaseTest.updateScore(username, score);
        score = databaseTest.getPlayerHighScore(username);
        assertEquals(expResult, score);
    }
}