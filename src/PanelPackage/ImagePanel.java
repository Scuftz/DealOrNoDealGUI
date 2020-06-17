package PanelPackage;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author shivn
 */
public abstract class ImagePanel extends JPanel
{
    Image img;
    public void setBackground(Image image)
    {
        this.img = image;
    }
    
    @Override
    protected void paintComponent(Graphics g)
    {
        if(img != null)
        {
            super.paintComponent(g);
            g.drawImage(img, 0, 0, 1200, 609, this);
        }
        else
        {
            int w = getWidth();
            int h = getHeight();
        
            Graphics2D g2d = (Graphics2D)g;
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            Color c1 = new Color(204, 153, 0);
            Color c2 = Color.yellow;
        
            GradientPaint gp = new GradientPaint(this.getWidth() / 2, 0, c1, this.getWidth() / 2, this.getHeight(), c2, true);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, w, h);
            this.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.yellow));
            super.paintComponent(g);
        }
    }
}