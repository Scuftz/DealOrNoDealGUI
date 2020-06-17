package PanelPackage;
import DealOrNoDealGUI.*;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author shivn
 */
public class BackgroundPanel extends ImagePanel
{
    public BackgroundPanel()
    {
        setLayout(null);
        setLocation(0,0);
        setSize(1200, 609);
        try {
            Image img = ImageIO.read(new File("logo.jpg"));
            setBackground(img);
        } catch (IOException ex) {
            System.out.println("Image Not Found!");
        }   
    }        
}
