/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DealOrNoDealGUI;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author shivn
 */
@Entity
public class Player
{
    @Id
    private String username;
    private String password;
    private int score;
    
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
    
    public int getScore()
    {
        return this.score;
    }
    public void setScore(int score)
    {
        this.score = score;
    }
    
    public String toString()
    {
        return this.username + ": " + this.score;
    }
}
