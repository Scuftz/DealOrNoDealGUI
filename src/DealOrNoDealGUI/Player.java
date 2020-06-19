package DealOrNoDealGUI;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * PDC Assignment 2
 * This is the Player Entity/Table that exist in the DB
 * @author Shivneel Singh (18021394)
 * @since 11/06/2020
 */
@Entity
public class Player implements Serializable
{
    @Id
    private String username; //Primary Key
    private String password;
    private int highscore;
    
    
    /**
     * Constructor
     * @param username
     * @param password 
     */
    public Player(String username, String password)
    {
        this.username = username;
        this.password = password;
        this.highscore = 0;
    }
    
    /**
     * Empty constructor for hibernate instantiation purposes
     */
    public Player(){}
    
    /**
     * Method will return the player's username
     * @return   The player's username
     */
    public String getUsername()
    {
        return this.username;
    }
    
    /**
     * Method will set the player's username
     * @param un   The username value to be stored
     */
    public void setUsername(String un)
    {
        this.username = un;
    }
    
    /**
     * Method will get the player's password
     * @return   The player's password
     */
    public String getPassword()
    {
        return this.password;
    }
    
    /**
     * This method will set the player's password
     * @param pw   The password value to be stored
     */
    public void setPassword(String pw)
    {
        this.password = pw;
    }
    
    /**
     * This method will get the player's high score
     * @return   High score of the player
     */
    public int getHighscore()
    {
        return this.highscore;
    }
    
    /**
     * This method sets the player's high score
     * @param score   The high score value to be stored
     */
    public void setHighscore(int score)
    {
        this.highscore = score;
    }
}
