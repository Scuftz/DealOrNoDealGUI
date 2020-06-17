package PanelPackage;
import DealOrNoDealGUI.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;

public class Case extends JButton
{
    /**
     * Variables
     */
    private int caseNumber;
    private int caseValue;
    private boolean openStatus;
    private boolean playerCase;
    /**
     * This is the constructor for the Case class
     * @param caseNum   An integer to store the cases' number
     * @param caseVal   An integer to store the value of the case
     */
    public Case(int caseNum, int caseVal)
    {
        super(Integer.toString(caseNum));
        this.caseNumber = caseNum;
        this.caseValue = caseVal;
        this.openStatus = false;
        this.playerCase = false;
        this.setSize(110, 80);
        this.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));      
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        if(!this.getOpenStatus())
        {
            this.setForeground(Color.BLACK);
            this.setFont(new Font("serif", Font.BOLD, 40));
            this.setText(Integer.toString(caseNumber));        
        }
        else
        {              
            this.setFont(new Font("Arial Black", Font.BOLD, 17));
            this.setText("$"+Integer.toString(this.caseValue));
            this.setEnabled(false);
        }
        super.paintComponent(g);
    }
    
    /**
     * This method will create a String output that will be displayed in the game board output
     * If the case has been opened, it will show the cases' value else it will show the number of the case
     * @return  A String outputting a cases' number or value
     */
    @Override
    public String toString()
    {      
        if (this.getOpenStatus())
        {
            if (this.caseValue < 1000)
            {
                return String.format("%1$-10s", "[$" + this.caseValue + "]");
            }
            if (this.caseValue == 1000000)
            {
                return String.format("%1$-10s", "[$1MIL]");
            }
            int shortValue = this.caseValue / 1000;
            return String.format("%1$-10s", "[$" + shortValue + "k]");
        }
        else
        {
            return String.format("%1$-10s", "[" + this.caseNumber + "]");
        }
    }
      
    /**
     * This method will get the cases' number
     * @return  An integer for the case number
     */
    public int getCaseNumber()
    {
        return caseNumber;
    }

    /**
     * This method sets the cases' number
     * @param caseNumber    An integer containing the value the case will store
     */
    public void setCaseNumber(int caseNumber)
    {
        this.caseNumber = caseNumber;
    }
    
    /**
     * This method will get the cases' value
     * @return  An integer for the case value
     */
    public int getCaseValue()
    {
        return caseValue;
    }
    
    /**
     * This method sets the cases' value
     * @param caseValue     An integer containing the value the case will store
     */
    public void setCaseValue(int caseValue)
    {
        this.caseValue = caseValue;
    }
    
    /**
     * This method gets the boolean value of whether the case has been opened already
     * @return  A boolean value, true if the case has been opened, else false
     */
    public boolean getOpenStatus()
    {
        return openStatus;
    }

    /**
     * This method sets the open status of the case
     * @param openStatus    A boolean containing whether the case is open or not
     */
    public void setOpenStatus(boolean openStatus)
    {
        this.openStatus = openStatus;
    }
    
    /**
     * This method gets the boolean value whether the case belongs to the player or not
     * @return  A boolean value, true if the case belongs to the player else false
     */
    public boolean isPlayerCase()
    {
        return playerCase;
    }

    /**
     * This methods sets whether the case is the player's case or not
     * @param playerCase    A boolean containing whether the case will be the player's case or not
     */
    public void setPlayerCase(boolean playerCase)
    {
        this.playerCase = playerCase;
    }
}