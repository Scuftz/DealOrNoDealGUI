package SpecialClassPackage;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JLabel;

/**
 * PDC Assignment 2
 * This is the GradientLabel Class
 * @author Shivneel Singh (18021394)
 * @since 11/06/2020
 */
public class GradientLabel extends JLabel
{
    /**
     * Variables
     */
    protected MoneyValueType mvt;
    protected boolean open;
    
    /**
     * Constructor
     * @param text   The text for the label
     * @param mvt    It's assigned colour
     */
    public GradientLabel(String text, MoneyValueType mvt)
    {
        super(text);
        this.mvt = mvt;
        this.open = false;
    }
        
    /**
     * Set the label value to be opened (will now turn gray)
     */
    public void setOpen()
    {
        this.open = true;
    }
    
    /**
     * This is the overridden paintComponent used to paint the labels
     * @param g   The Graphics g used to paint the component
     */
    @Override
    protected void paintComponent(Graphics g)
    {
        int w = getWidth();
        int h = getHeight();
        
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        Color c1;
        Color c2;
        
        if (!this.open)
        {
            if(this.mvt == MoneyValueType.BLUE)
            {
                c1 = new Color(0,248,248);
                c2 = Color.BLUE;
            }
            else if (this.mvt == MoneyValueType.RED)
            {
                c1 = new Color(204,0,82);
                c2 = new Color(255,51,153);
            }
            else
            {
                c1 = new Color(0,153,51);
                c2 = new Color(26,240,26);
            }            
        }
        else
        {
            c1 = Color.GRAY;
            c2 = Color.LIGHT_GRAY;
        }

        GradientPaint gp = new GradientPaint(0, (this.getHeight() / 2), c1, this.getWidth(), (this.getHeight() / 2), c2, true);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
        super.paintComponent(g);
    }
}