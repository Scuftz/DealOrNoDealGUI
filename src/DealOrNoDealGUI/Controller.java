package DealOrNoDealGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener
{
    Model model;
    View view;
    
    public Controller(Model model, View view)
    {
        this.model = model;
        this.view = view;
        view.setController(this);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        String cmp = e.getActionCommand();
//        if ()
    }
    
}
