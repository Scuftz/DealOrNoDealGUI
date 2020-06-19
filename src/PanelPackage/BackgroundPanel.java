package PanelPackage;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * PDC Assignment 2
 * This is the BackgroundPanel Class, used to create a panel with the Deal or No Deal background image
 * @author Shivneel Singh (18021394)
 * @since 11/06/2020
 */
public class BackgroundPanel extends ImagePanel
{
    /**
     * Constructor
     */
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
