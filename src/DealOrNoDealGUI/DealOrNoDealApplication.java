package DealOrNoDealGUI;
import org.apache.log4j.BasicConfigurator;

public class DealOrNoDealApplication
{
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