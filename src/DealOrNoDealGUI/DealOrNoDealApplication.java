package DealOrNoDealGUI;
import org.apache.log4j.BasicConfigurator;

/**
 * PDC Assignment 2
 * This is the main application
 * @author Shivneel Singh (18021394)
 * @since 11/06/2020
 */
public class DealOrNoDealApplication
{
    /**
     * Main method, setting up the Model, View and Controller
     * The BasicConfigurator.configure() is called to resolve a warning issue (asked on BlackBoard, is the recommended solution by Weihua)
     * @param args 
     */
    public static void main(String[] args)
    { 
        BasicConfigurator.configure(); 
        Model model = new Model();
        View view = new View();
        model.addObserver(view);
        Controller controller = new Controller(model, view);
        view.setVisible(true);  
    }
}