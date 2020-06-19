package PanelPackage;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 * PDC Assignment 2
 * This is the MainGamePanel Class, used as a background panel to display all the panels in the game
 * @author Shivneel Singh (18021394)
 * @since 11/06/2020
 */
public class MainGamePanel extends JPanel
{
    /**
     * Constructor
     * @param frame   Dimensions of the JFrame to mirror its size
     */
    public MainGamePanel(Dimension frame)
    {
        setLayout(new BorderLayout());
        setLocation(0, 0);
        setSize(frame.width, frame.height);
    }
}