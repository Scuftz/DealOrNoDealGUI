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
        System.out.println(cmp);
        if(cmp.equals("LOGIN"))
        {
            String un = view.usernameTxt.getText();
            char[] pw = view.passwordTxt.getPassword();
            if((!un.equals("")) && (pw.length>0))
            {
                String password = String.valueOf(pw);
                model.checkLogin(un, password);
            }
            else
            {
                System.out.println("Canot hve null detail");
            }
        }
//        if ()
    }
    
}
