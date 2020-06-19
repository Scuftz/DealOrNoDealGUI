package DealOrNoDealGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JComponent;
import javax.swing.Timer;

/**
 * PDC Assignment 2
 * This is the Controller Class, part of the MVC
 * @author Shivneel Singh (18021394)
 * @since 11/06/2020
 */
public class Controller implements ActionListener, ItemListener
{
    /**
     * Variables
     */
    Model model;
    View view;
    int counter = 0;
    
    /**
     * Constructor
     * @param model  Model class to call the model's methods
     * @param view   View class for to set action listeners from components of the View
     */
    public Controller(Model model, View view)
    {
        this.model = model;
        this.view = view;
        view.setController(this);
        view.setItemController(this);
    }    
    
    /**
     * ActionListener method that waits for actions to occur from View
     * @param e   An action that occurred
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        String cmp = e.getActionCommand();
        Object o = e.getSource();
        if (o instanceof Case) //Case opened
        {
            model.openOrSetCase((Case)o);
        }
        else if (o instanceof Timer) //Flashing buttons
        {
            model.invert();
            counter++;
            if (counter % 12 == 0)
            {
                ((Timer)e.getSource()).stop();
            }
        }
        else if(cmp.equals("LOGIN")) //Player login
        {
            String un = view.loginPanel.getUsername();
            char[] pw = view.loginPanel.getPassword();
                    
            if((!un.equals("")) && (pw.length>0))
            {
                String password = String.valueOf(pw);
                model.checkLogin(un, password);
                //cases aren't created upon the controllers instantiation so I have to set their controllers later (here)
                view.setCaseController(this);
            }
            else
            {
                model.blankFlag();
            }
        }
        else if (cmp.equals("Quit Game")) //Quit game
        {
            System.out.println("Quitting Game");
            model.quitGame();
        }
    }

    /**
     * ItemListener method that waits for items to change their state from View
     * @param e   An item whose state has changed
     */
    @Override
    public void itemStateChanged(ItemEvent e)
    {
        String name = ((JComponent)e.getItem()).getName();
        if(name.equals("Deal")) //Deal was accepted by player
        {
            System.out.println("Deal Accepted");
            model.endGame();
        }
    }
}