package DealOrNoDealGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JComponent;
import javax.swing.Timer;

public class Controller implements ActionListener, ItemListener
{
    Model model;
    View view;
    int counter = 0;
    
    public Controller(Model model, View view)
    {
        this.model = model;
        this.view = view;
        view.setController(this);
        view.setItemController(this);
    }    
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        String cmp = e.getActionCommand();
        Object o = e.getSource();
        if (o instanceof Case)
        {
            model.openOrSetCase((Case)o);
        }
        else if (o instanceof Timer)
        {
            model.invert();
            counter++;
            System.out.println(counter);
            if (counter % 12 == 0)
            {
                ((Timer)e.getSource()).stop();
            }
        }
        else if(cmp.equals("LOGIN"))
        {
            String un = view.lp.getUsername();
            char[] pw = view.lp.getPassword();
                    
            if((!un.equals("")) && (pw.length>0))
            {
                String password = String.valueOf(pw);
                model.checkLogin(un, password);
                view.setCaseController(this);
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e)
    {
        String name = ((JComponent)e.getItem()).getName();
        
        if(name.equals("Deal"))
        {
            System.out.println("trigger");
            model.endGame();
        }
    }
    
}
