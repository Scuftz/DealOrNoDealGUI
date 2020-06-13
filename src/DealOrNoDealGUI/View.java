package DealOrNoDealGUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;
import java.util.List;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.*;
import javax.swing.SwingConstants;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;

public class View extends JFrame implements Observer
{
    /**
     * Variables
     */
    private TestPanel backgroundPanel;
    private JPanel loginPanel, newAccountPanel, mainGamePanel; //main panels
    private JPanel casePanel, leftMoneyPanel, rightMoneyPanel, caseRemainingPanel; //panels inside main game panel
    private JLabel caseRemainingLbl, caseRemainingNumberLbl, dond, usernameLbl, passwordLbl;
    private JTextArea usernameTxt, passwordTxt;
    private JButton login;
    private ArrayList<JLabel> caseValuesList = new ArrayList<>();
    private Dimension screenDimension, frameDimension;
    
    /**
     * Constructor
     */
    public View()
    {
        setLayout(null);
        setSize(1200, 609);
//        getContentPane().setBackground(Color.black);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Toolkit tk = Toolkit.getDefaultToolkit();
        screenDimension = tk.getScreenSize();
        frameDimension = this.getSize();
        setLocation((screenDimension.width-frameDimension.width)/2, (screenDimension.height-frameDimension.height)/2);
        
        //main panels
        loginPanel = new JPanel();
        backgroundPanel = new TestPanel();
        newAccountPanel = new JPanel();
        mainGamePanel = new JPanel();
        //panels in main game panel
        casePanel = new JPanel();
        caseRemainingPanel = new JPanel();
        leftMoneyPanel = new JPanel();
        rightMoneyPanel = new JPanel();
        
        //main panel stuff
        createLoginPanel();
        createAccountPanel();
        //main game sub panels
        createCaseRemainingPanel();
        createMoneyPanels();
        createCasePanel();
        //storing sub panels in main game panel;
        createMainGamePanel();
        background();
        //initial panel
//        backgroundPanel.setLayout(null);
//        backgroundPanel.setLocation(0,0);
//        backgroundPanel.setSize(1200, 609);
//        add(backgroundPanel);
//        backgroundPanel.add(loginPanel);
//        add(loginPanel);
//        add(backgroundPanel);
//        backgroundPanel.setLayout(null);
//        backgroundPanel.add(loginPanel);
//        add(newAccountPanel);
    }
    
    public void background()
    {
        dond = new JLabel("DEAL OR NO DEAL");
        dond.setFont(new Font("Arial Black", Font.BOLD, 40));
        dond.setForeground(Color.YELLOW);
        dond.setLocation(380, 0);
        dond.setSize(600, 100);
        backgroundPanel.setLayout(null);
        backgroundPanel.setLocation(0,0);
        backgroundPanel.setSize(1200, 609);
        
        add(backgroundPanel);
        
        backgroundPanel.add(dond);
        backgroundPanel.add(loginPanel);
    }
//    public void display
    public void displayMainGame()
    {
        getContentPane().remove(loginPanel);
        getContentPane().add(mainGamePanel);       
    }
    
    //TODO: create new account panel;
    public void createAccountPanel()
    {
        newAccountPanel.setLocation(200, 200);
        newAccountPanel.setSize(100, 100);
        newAccountPanel.setBackground(Color.red);
    }
    
    //TODO: login panel;
    public void createLoginPanel()
    {
        loginPanel.setLayout(null);
        loginPanel.setLocation(300, 130);
        loginPanel.setSize(600, 300);
        loginPanel.setBackground(Color.WHITE);
        
        usernameLbl = new JLabel("Username");
        usernameLbl.setLocation(100, 100);
        usernameLbl.setFont(new Font("Arial", Font.PLAIN, 20));
        usernameLbl.setForeground(Color.red);
//        usernameLbl.setBounds(500, 400, 100, 50);
//        usernameTxt = new JTextArea("Enter Username", 20, 20);
//        usernameTxt.setLocation(600, 350);
        loginPanel.add(usernameLbl);
//        loginPanel.add(usernameTxt);
//        passwordLbl = new JLabel("Password");
//        passwordTxt = new JTextArea("Enter Password", 20, 20);
    }
    
    public void createMainGamePanel()
    {
        mainGamePanel.setLayout(new BorderLayout());
        mainGamePanel.add(caseRemainingPanel, BorderLayout.NORTH);
        mainGamePanel.add(leftMoneyPanel, BorderLayout.WEST);
        mainGamePanel.add(rightMoneyPanel, BorderLayout.EAST);
    }
    
    public void createCaseRemainingPanel()
    {
        caseRemainingPanel.setBackground(Color.yellow);
        caseRemainingPanel.setPreferredSize(new Dimension(1200, 50));
        caseRemainingNumberLbl = new JLabel("6");
        caseRemainingLbl = new JLabel(" Cases Remaining");
        caseRemainingPanel.add(caseRemainingNumberLbl);
        caseRemainingPanel.add(caseRemainingLbl);      
    }
    
    public void createMoneyPanels()
    {
        leftMoneyPanel.setLayout(new BoxLayout(leftMoneyPanel, BoxLayout.Y_AXIS));
        rightMoneyPanel.setLayout(new BoxLayout(rightMoneyPanel, BoxLayout.Y_AXIS));
        int width = 250;
        int height = 40;
        
        for(JLabel lbl : caseValuesList)
        {
            lbl.setHorizontalAlignment(SwingConstants.RIGHT);
            lbl.setBackground(Color.CYAN);
            lbl.setOpaque(true);
            lbl.setFont(new Font("Arial", Font.PLAIN, 20));
            lbl.setMinimumSize(new Dimension(width, height));
            lbl.setPreferredSize(new Dimension(width, height));
            lbl.setMaximumSize(new Dimension(width, height));
            lbl.setBorder(BorderFactory.createLineBorder(Color.blue, 1, true));
            
            if(leftMoneyPanel.getComponents().length < 13)
            {
                lbl.setHorizontalAlignment(SwingConstants.RIGHT);
                leftMoneyPanel.add(lbl);
            }
            else
            {
                lbl.setHorizontalAlignment(SwingConstants.LEFT);
                rightMoneyPanel.add(lbl);
            }
        }
    }
    
    public void createCasePanel()
    {
        
    }
        
    public void setController(ActionListener controller)
    {
        
    }
    
    /**
     * 
     * @param o
     * @param arg 
     */
    @Override
    public void update(Observable obs, Object obj) {
        UpdateInfo update = (UpdateInfo) obj;
        
        System.out.println("we in update");
//        if(!update.loginFlag)
//        {
//        }
        if(update.dealAccepted) //end game
        {
            
        }
        else if(update.endOfRound)
        {
            if(update.totalAmountOfCases == 1) //check if its time to open (no more cases to open)
            {
                
            }
        }
        else if(!update.startGame)
        {
            update.startGame = true;
            for(Integer num : update.duplicateCaseValues)
            {
                caseValuesList.add(new JLabel("" + num));
            }
        }
        else
        {
            
        }
    }
}






//        for(Integer value : )
//        caseValuesLbl.add(new JLabel("1"));
//        caseValuesLbl.add(new JLabel("2"));
//        caseValuesLbl.add(new JLabel("5"));
//        caseValuesLbl.add(new JLabel("10"));
//        caseValuesLbl.add(new JLabel("20"));
//        caseValuesLbl.add(new JLabel("25"));
//        caseValuesLbl.add(new JLabel("50"));
//        caseValuesLbl.add(new JLabel("100"));
//        caseValuesLbl.add(new JLabel("150"));
//        caseValuesLbl.add(new JLabel("200"));
//        caseValuesLbl.add(new JLabel("250"));
//        caseValuesLbl.add(new JLabel("500"));
//        caseValuesLbl.add(new JLabel("750"));
//        caseValuesLbl.clear();
//        caseValuesLbl.add(new JLabel("1000"));
//        caseValuesLbl.add(new JLabel("2000"));
//        caseValuesLbl.add(new JLabel("3000"));
//        caseValuesLbl.add(new JLabel("4000"));
//        caseValuesLbl.add(new JLabel("5000"));
//        caseValuesLbl.add(new JLabel("10000"));
//        caseValuesLbl.add(new JLabel("15000"));
//        caseValuesLbl.add(new JLabel("20000"));
//        caseValuesLbl.add(new JLabel("2500"));
//        caseValuesLbl.add(new JLabel("50000"));
//        caseValuesLbl.add(new JLabel("75000"));
//        caseValuesLbl.add(new JLabel("100000"));
//        caseValuesLbl.add(new JLabel("200000"));
