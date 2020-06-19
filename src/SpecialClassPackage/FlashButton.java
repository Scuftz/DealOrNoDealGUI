package SpecialClassPackage;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

/**
 * PDC Assignment 2
 * This is the FlashButtons Class
 * @author Shivneel Singh (18021394)
 * @since 11/06/2020
 */
public class FlashButton extends JButton
{
    /**
     * Variables
     */
    private Border border = BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLACK, Color.BLACK);
    private MoneyValueType mvt;
    private Boolean changeOfPaint = false;
 
    /**
     * Constructor
     * @param xLoc   The x coordinate of the button
     * @param yLoc   The y coordinate of the button
     * @param mvt    It's assigned colour
     */
    public FlashButton(int xLoc, int yLoc, MoneyValueType mvt)
    {
        this.setLocation(xLoc, yLoc);
        this.setSize(20, 20);
        this.mvt = mvt;
        this.setBorder(border);    
    }
    
    /**
     * This method will make the button change its colour
     */
    public void invert()
    {
        this.changeOfPaint = (!changeOfPaint);
        this.validate();
        this.repaint();
    }
    
    /**
     * This is the overridden paintComponent used to paint the buttons
     * @param g   The Graphics g used to paint the component
     */
    @Override
    protected void paintComponent(Graphics g)
    {
        if(!changeOfPaint)
        {
            if (mvt == MoneyValueType.RED)
            {
                this.setBackground(Color.RED);
            }
            else if (mvt == MoneyValueType.GREEN)
            {
                this.setBackground(Color.GREEN);
            }
            else
            {
                this.setBackground(Color.BLUE);
            }            
        }
        else
        {
            if (mvt == MoneyValueType.RED)
            {
                this.setBackground(new Color(128,0,0));
            }
            else if (mvt == MoneyValueType.GREEN)
            {
                this.setBackground(new Color(0,128,0));
            }
            else
            {
                this.setBackground(new Color(0,0,128));
            }  
        }
        super.paintComponent(g);
    }
}