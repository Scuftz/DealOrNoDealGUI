/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DealOrNoDealGUI;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author shivn
 */
@Entity
public class Player implements Serializable
{
    @Id
    private String username;
    private String password;
    private int highscore;
    
    public Player()
    {
        
    }
    
    public Player(String username, String password)
    {
        this.username = username;
        this.password = password;
        this.highscore = 0;
    }
    
    public String getUsername()
    {
        return this.username;
    }
    public void setUsername(String un)
    {
        this.username = un;
    }
    
    public String getPassword()
    {
        return this.password;
    }
    public void setPassword(String pw)
    {
        this.password = pw;
    }
    
    public int getHighscore()
    {
        return this.highscore;
    }
    public void setHighscore(int score)
    {
        this.highscore = score;
    }
    
    @Override
    public String toString()
    {
        return this.username + ": " + this.highscore;
    }
}
