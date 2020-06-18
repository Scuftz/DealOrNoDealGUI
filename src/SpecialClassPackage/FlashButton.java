package SpecialClassPackage;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TimerTask;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

/**
 *
 * @author shivn
 */
public class FlashButton extends JButton
{
    private Border border = BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLACK, Color.BLACK);
    private MoneyValueType mvt;
    private Boolean changeOfPaint = false;
 
    public FlashButton(int xLoc, int yLoc, MoneyValueType mvt)
    {
        this.setLocation(xLoc, yLoc);
        this.setSize(20, 20);
        this.mvt = mvt;
        this.setBorder(border);    
    }
    
    public void invert()
    {
        this.changeOfPaint = (!changeOfPaint);
        this.validate();
        this.repaint();
    }
    
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