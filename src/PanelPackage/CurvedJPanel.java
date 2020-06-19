package PanelPackage;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 * PDC Assignment 2
 * This is the CurvedJPanel Class, used to create a panel with a curved edges
 * @author Shivneel Singh (18021394)
 * @since 11/06/2020
 */
public abstract class CurvedJPanel extends JPanel
{
    /**
     * This is the overridden paintComponent used to create the curved edge panel
     * @param g   The Graphics g used to paint the component
     */
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Dimension arcs = new Dimension(15,15);
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        graphics.setColor(getBackground());
        graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint background
        graphics.setColor(Color.WHITE);
        graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint border
    }
}