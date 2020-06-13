package DealOrNoDealGUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.LayoutManager;
import java.util.List;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
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
    private CurvedJPanel loginPanel;
//    private JPanel backgroundPanel;
    private JPanel newAccountPanel, mainGamePanel; //main panels
    private JPanel casePanel, leftMoneyPanel, rightMoneyPanel, caseRemainingPanel; //panels inside main game panel
    private JLabel caseRemainingLbl, caseRemainingNumberLbl, dond, usernameLbl, passwordLbl, loginLbl;
//    public JTextArea usernameTxt, passwordTxt;
    public JTextField usernameTxt;
    public JPasswordField passwordTxt;
    private JButton loginBtn;
    private ArrayList<JLabel> caseValuesList = new ArrayList<>();
    private Dimension screenDimension, frameDimension;
    
    /**
     * Constructor
     */
    public View()
    {
        setLayout(null);
        setSize(1200, 609);
        getContentPane().setBackground(Color.black);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Toolkit tk = Toolkit.getDefaultToolkit();
        screenDimension = tk.getScreenSize();
        frameDimension = this.getSize();
        setLocation((screenDimension.width-frameDimension.width)/2, (screenDimension.height-frameDimension.height)/2);
        
//        try {
////            this.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("logo.jpg")))));
//            bgi = (new JLabel(new ImageIcon(ImageIO.read(new File("logo.jpg")))));
//            bgi.setSize(1200, 609);
//            bgi.setLocation(0, 0);
////            this.setContentPane(bgi);
//            this.getContentPane().add(bgi);
//        } catch (IOException ex) {
//            
//        }
        
        //main panels
        loginPanel = new CurvedJPanel();
        backgroundPanel = new TestPanel();
//        backgroundPanel = new JPanel();
//        curved = new CurvedJPanel();
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
//        dond = new JLabel("DEAL OR NO DEAL");
//        dond.setFont(new Font("Arial Black", Font.BOLD, 40));
//        dond.setForeground(Color.YELLOW);
//        dond.setLocation(380, 0);
//        dond.setSize(600, 100);
        backgroundPanel.setLayout(null);
        backgroundPanel.setLocation(0,0);
        backgroundPanel.setSize(1200, 609);
        try {
            Image img = ImageIO.read(new File("logo.jpg"));
//            Image img = ImageIO.read(new File("logo.eps"));
//            Image img = ImageIO.read(new File("logo.svg"));
            backgroundPanel.setBackground(img);
        } catch (IOException ex) {
            System.out.println("io excpo");
        }
//        backgroundPanel.setBackground(new Color(0, 0, 0, 200));
//        backgroundPanel.setBackground(0, 0, 0, );
        
        add(backgroundPanel);
//        curved.setBounds(375,150,450,285);//375, 170, 450, 285
//        curved.setBackground(new Color(255,255,255, 245));
//        curved.setOpaque(false);
        backgroundPanel.add(loginPanel);
//        backgroundPanel.add(dond);
//        backgroundPanel.add(loginPanel);
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
        Font f = new Font("Arial", Font.PLAIN, 10);
        Font f2 = new Font("Arial", Font.PLAIN, 20);
        
        loginPanel.setLayout(null);
        loginPanel.setBounds(400,150,400,285);
        loginPanel.setBackground(new Color(255,255,255, 245));
        loginPanel.setOpaque(false);
        
        loginLbl = new JLabel("Login");
        loginLbl.setLocation(175, 10);
        loginLbl.setFont(new Font("Microsoft YaHei UI Light", Font.PLAIN, 20));
        loginLbl.setForeground(Color.DARK_GRAY);
        loginLbl.setSize(80, 30);
        
        usernameLbl = new JLabel("Username");
        usernameLbl.setLocation(72, 60);
        usernameLbl.setFont(f);
        usernameLbl.setForeground(Color.DARK_GRAY);
        usernameLbl.setSize(50, 10);
        
//        usernameTxt = new JTextArea();
        usernameTxt = new JTextField();
        usernameTxt.setDocument(new RestrictInputLength(20));
        usernameTxt.setFont(f2);
        usernameTxt.setLocation(72, 75);
        usernameTxt.setSize(255, 30);
        usernameTxt.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
        
        passwordLbl = new JLabel("Password");
        passwordLbl.setLocation(72, 125);
        passwordLbl.setFont(f);
        passwordLbl.setForeground(Color.DARK_GRAY);
        passwordLbl.setSize(50, 10);
        
//        passwordTxt = new JTextArea();
        passwordTxt = new JPasswordField();
        passwordTxt.setEchoChar('*');
        passwordTxt.setDocument(new RestrictInputLength(20));
        passwordTxt.setFont(f2);
        passwordTxt.setLocation(72, 140);
        passwordTxt.setSize(255, 30);
        passwordTxt.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
        
        loginBtn = new JButton("LOGIN");
        loginBtn.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 15));
        loginBtn.setLocation(125, 210);
        loginBtn.setSize(150, 35);
        loginBtn.setBackground(Color.YELLOW);
        loginBtn.setBorder(BorderFactory.createRaisedBevelBorder());
        loginPanel.add(loginLbl);
        loginPanel.add(usernameLbl);
        loginPanel.add(usernameTxt);
        loginPanel.add(passwordLbl);
        loginPanel.add(passwordTxt);
        loginPanel.add(loginBtn);
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
        loginBtn.addActionListener(controller);
    }
    
    /**
     * 
     * @param obs
     * @param obj
     * @param o
     * @param arg 
     */
    @Override
    public void update(Observable obs, Object obj) {
        UpdateInfo update = (UpdateInfo) obj;
        
        System.out.println("we in update " + update.loginFlag);
        if(!update.loginFlag)
        {
            System.out.println("in view LOG IN FAILED!");
            JOptionPane.showMessageDialog(null, "Login Failed", "Failed to Login", 0);
            passwordTxt.setText("");
        }
        else if(!update.gameStarted) //end game
        {
            update.loginFlag = true;
            update.gameStarted = true;
            for(Integer num : update.duplicateCaseValues)
            {
                caseValuesList.add(new JLabel("" + num));
            }
            //change panels
            //set up new panel
        }
        else if(update.endOfRound)
        {
            if(update.totalAmountOfCases == 1) //check if its time to open (no more cases to open)
            {
                
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
