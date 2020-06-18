package SpecialClassPackage;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
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
public class ThreadedButton extends JButton
{
    private Border border = BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLACK, Color.BLACK);
    private MoneyValueType mvt;
    private Boolean changeOfPaint = false;
    
    public ThreadedButton(int xLoc, int yLoc, MoneyValueType mvt)
    {
        this.setLocation(xLoc, yLoc);
        this.setSize(20, 20);
        this.mvt = mvt;
        this.setBorder(border);    
    }
    
    public void doShit(Lock lock, Condition con) throws InterruptedException
    {
        this.changeOfPaint = true;
        this.revalidate();
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
            this.setBackground(Color.black);
        }
        super.paintComponent(g);
    }
}

//    private JButton colouredBtn;
//    private Color[] colourArray;
//    private ArrayList<JButton> btnList;

//    public ArrayList<JButton> getButtonList()
//    {
//        return this.btnList;
//    }

// colourArray = new Color[]{ Color.RED, Color.GREEN, Color.BLUE };
//        border = BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLACK, Color.BLACK);
        
//        btnList = new ArrayList();
//        int xPosition = 70;
//        int xIncrease = 40;
//        for (int k = 0; k < 6; k++)
//        {
//            colouredBtn = new JButton("");
//            colouredBtn.setSize(20, 20);
//            colouredBtn.setBorder(border);
//            
//            if (k >= 3)
//            {
//                if (xPosition < 1025)
//                {
//                    xPosition = 1025;
//                }
//                colouredBtn.setBackground(colourArray[k - 3]);
//            }
//            else
//            {
//                colouredBtn.setBackground(colourArray[k]);
//            }
//            colouredBtn.setLocation(xPosition, 15);
//            btnList.add(colouredBtn);
//            xPosition += xIncrease;
//        }

//        Thread.sleep(1000);
//        lock.lock();
//        
//        System.out.println("Press return key");
//        new Scanner(System.in).nextLine();
//        System.out.println("Got return");
//        con.signal();
//        
//        try
//        {
//            System.out.println("T2 in try");
//            Thread.sleep(2000);
//            System.out.println("finish sleeping");
//        }
//        finally
//        {
//            lock.unlock();
//        }