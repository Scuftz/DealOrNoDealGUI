package DealOrNoDealGUI;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 *
 * @author shivn
 */
public class GradientCmp extends JLabel
{
    protected MoneyValueType mvt;
    protected boolean open;
    
    public GradientCmp(String text, MoneyValueType mvt)
    {
        super(text);
        this.mvt = mvt;
        this.open = false;
    }
    
    public void setOpen()
    {
        this.open = true;
    }
    
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
                c1 = Color.CYAN;
                c2 = Color.BLUE;
            }
            else if (this.mvt == MoneyValueType.RED)
            {
                c1 = new Color(204,0,82);
                c2 = new Color(255,51,153);
            }
            else
            {
                c1 = new Color(0, 153, 51);
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
