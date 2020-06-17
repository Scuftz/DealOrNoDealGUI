package PanelPackage;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 *
 * @author shivn
 */
public class MainGamePanel extends JPanel
{
    public MainGamePanel(Dimension frame)
    {
        setLayout(new BorderLayout());
        setLocation(0, 0);
        setSize(frame.width, frame.height);
    }
}
