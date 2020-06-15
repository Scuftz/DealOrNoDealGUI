package DealOrNoDealGUI;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.io.*;
import java.text.NumberFormat;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;

public class View extends JFrame implements Observer
{
    /**
     * Variables
     */
    //log in panels and variables
    private ImagePanel backgroundPanel, topPanel;
    private CurvedJPanel loginPanel;
    private Dimension screenDimension, frameDimension;
    public JTextField usernameTxt;
    public JPasswordField passwordTxt;
    private JButton loginBtn;
    public boolean setUp = false;
    private NumberFormat nf = NumberFormat.getNumberInstance();
    public JToggleButton tb = new JToggleButton("Deal");
            
    //in game panels and variables
    private JPanel mainGamePanel; //main panel
    private JPanel casePanel, leftMoneyPanel, rightMoneyPanel; //panels inside main game panel
    private JLabel caseRemainingNumberLbl, usernameLbl, passwordLbl, loginLbl;
    private ArrayList<Case> caseListBtn = new ArrayList();
    
    /**
     * Constructor
     */
    public View()
    {
        super("Deal or No Deal");
        setResizable(false);
        setLayout(null);
        setSize(1200, 599);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Toolkit tk = Toolkit.getDefaultToolkit();
        screenDimension = tk.getScreenSize();
        frameDimension = this.getSize();
        setLocation((screenDimension.width-frameDimension.width)/2, (screenDimension.height-frameDimension.height)/2);
        
        //main panels
        loginPanel = new CurvedJPanel();
        backgroundPanel = new ImagePanel();
        mainGamePanel = new JPanel();
        //panels in main game panel
        casePanel = new JPanel();
        leftMoneyPanel = new JPanel();
        rightMoneyPanel = new JPanel();
        topPanel = new ImagePanel();
        tb.setName("Deal");
        createLoginPanel();
        background();
    }
    
        
    /**
     * @param obs
     * @param obj 
     */
    @Override
    public void update(Observable obs, Object obj) {
        UpdateInfo update = (UpdateInfo) obj;
        
        if(!update.loginFlag)
        {
            System.out.println("in view LOG IN FAILED!");
            JOptionPane.showMessageDialog(null, "Login Failed", "Failed to Login", 0);
            passwordTxt.setText("");
        }
        else if(!update.gameStarted)
        {
            update.loginFlag = true;
            update.gameStarted = true;
            this.createCasesForController(update.caseList);
            this.selectCasePanel(update.caseList);
        }
        else if (!update.caseSelected)
        {
            update.caseSelected = true;
            createTopPanel(update.casesRemainingThisRound);
            createMoneyPanels(update.tester);
            this.createCasePanel(update.caseList);
            this.displayMainGame();
        }
        else if (update.endOfGame)
        {
            System.out.println("ending");
            this.getContentPane().removeAll();
            //go to end game panel, maybe introduce timer here with a thread when display shocker result
        }
        else if(update.endOfRound)
        {
            update.endOfRound = false;
            this.updateCaseToOpen(update.casesRemainingThisRound);
            this.validate();
            this.repaint();
            int result = displayBankOffer(update.bankOffer);
            if(result == 0)
            {
                System.out.println(result);
                update.endOfGame = true;
                tb.setSelected(true);
            }
            System.out.println(result);
        }
        else if (update.quitGame)
        {
            
        }
        else
        {
            this.updateCaseToOpen(update.casesRemainingThisRound);
        }
        this.validate();
        this.repaint();
    }
    
    public int displayBankOffer(int bankOffer)
    {
        Object[] options = {"Deal", "No Deal"};
        int accept = JOptionPane.showOptionDialog(null, ("BANK OFFER: $" + nf.format(bankOffer)), "Bank Offer",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
        return accept;
    }
    
    public void updateCaseToOpen(int num)
    {
        caseRemainingNumberLbl.setText(Integer.toString(num));
    }
    
    public void createCasesForController(ArrayList<Case> caseList)
    {
        for (int k = 1; k <= 26; k++)
        {
            caseListBtn.add(caseList.get(k - 1));
        }
    }
    
    public void background()
    {
        backgroundPanel.setLayout(null);
        backgroundPanel.setLocation(0,0);
        backgroundPanel.setSize(1200, 609);
        try {
            Image img = ImageIO.read(new File("logo.jpg"));
            backgroundPanel.setBackground(img);
        } catch (IOException ex) {
            System.out.println("io excpo");
        }
        
        add(backgroundPanel);
        backgroundPanel.add(loginPanel);
    }
    
//    public void selectCasePanel()
    public void selectCasePanel(ArrayList<Case> caseList)
    {
        backgroundPanel.remove(loginPanel);
        JPanel temp = new JPanel(new GridLayout(4, 7, 25, 25));
        temp.setBounds(200, 130, 790, 350);
        temp.setBackground(new Color(255,255,255,100));
//        for(Case c : caseListBtn)
        for(Case c : caseList)
        {
            temp.add(c);
        }
        JLabel select = new JLabel("Select A Case!");
        select.setFont(new Font("Verdana", Font.BOLD, 40));
        select.setForeground(Color.YELLOW);
        select.setBounds(450, 30, 400, 40);
        backgroundPanel.add(select);
        backgroundPanel.add(temp);
        backgroundPanel.revalidate();
        backgroundPanel.repaint(); 
    }
    
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
    
    public void displayMainGame()
    {
        this.getContentPane().removeAll();
        createMainGamePanel();
        getContentPane().add(mainGamePanel);       
        this.revalidate();
        this.repaint();
    }
       
    public void createMainGamePanel()
    {
        mainGamePanel.setLayout(new BorderLayout());
        mainGamePanel.setLocation(0, 0);
        mainGamePanel.setSize(this.frameDimension.width, this.frameDimension.height);
        
        mainGamePanel.add(topPanel, BorderLayout.NORTH);
        mainGamePanel.add(leftMoneyPanel, BorderLayout.WEST);
        mainGamePanel.add(rightMoneyPanel, BorderLayout.EAST);
        mainGamePanel.add(casePanel, BorderLayout.CENTER);
    }
    
    public void createTopPanel(int casesToOpen)
    {
        topPanel.setLayout(null);
        topPanel.setBackground(Color.blue);
        topPanel.setOpaque(false);
        topPanel.setPreferredSize(new Dimension(1200, 50));
        topPanel.setBackground(new Color(255, 217, 102));
        
        caseRemainingNumberLbl = new JLabel(Integer.toString(casesToOpen));
        caseRemainingNumberLbl.setBounds(590, 5, 25, 40);
        Font f = caseRemainingNumberLbl.getFont();
        caseRemainingNumberLbl.setFont(new Font(caseRemainingNumberLbl.getFont().getFontName(), Font.PLAIN, 40));

        JLabel caseToOpenLbl = new JLabel("To Open");
        caseToOpenLbl.setBounds(620, 15, 50, 20);

        topPanel.add(caseRemainingNumberLbl);
        topPanel.add(caseToOpenLbl);
        
        JButton test;
        Color[] c = new Color[]{ Color.RED, Color.GREEN, Color.BLUE };
        int xPos = 70;
        int xIncrease = 40;
        for (int k = 0; k < 3; k++)
        {
            test = new JButton("");
            test.setSize(20, 20);
            test.setLocation(xPos, 15);
            test.setBackground(c[k]);
            test.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLACK, Color.BLACK));
            topPanel.add(test);
            xPos += xIncrease;
        }
        xPos = 1025;
        for (int k = 0; k < 3; k++)
        {
            test = new JButton("");
            test.setSize(20, 20);
            test.setLocation(xPos, 15);
            test.setBackground(c[k]);
            test.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLACK, Color.BLACK));
            topPanel.add(test);
            xPos += xIncrease;
        }     
    }
    
    public void createMoneyPanels(LinkedHashMap<Integer, GradientCmp> valueLbls)
    {
        leftMoneyPanel.setLayout(new BoxLayout(leftMoneyPanel, BoxLayout.Y_AXIS));
        rightMoneyPanel.setLayout(new BoxLayout(rightMoneyPanel, BoxLayout.Y_AXIS));
        int width = 250;
        int height = 40;
                
        for(Map.Entry<Integer, GradientCmp> entry  : valueLbls.entrySet())
        {
            GradientCmp lbl = entry.getValue();
            lbl.setOpaque(false);
            lbl.setFont(new Font("Arial", Font.BOLD, 20));
            lbl.setForeground(Color.WHITE);
            lbl.setMinimumSize(new Dimension(width, height));
            lbl.setPreferredSize(new Dimension(width, height));
            lbl.setMaximumSize(new Dimension(width, height));
            
            BevelBorder rbb = new BevelBorder(BevelBorder.RAISED);
            MatteBorder mb;
            CompoundBorder cb;
            if(leftMoneyPanel.getComponents().length < 13)
            {
                mb = new MatteBorder(0, 0, 0, 1, (new Color(255, 204, 51)));     
                cb = new CompoundBorder(rbb, mb);
                lbl.setHorizontalAlignment(SwingConstants.RIGHT);
                leftMoneyPanel.add(lbl);
            }
            else
            {
                mb = new MatteBorder(0, 1, 0, 0, (new Color(255, 204, 51)));
                cb = new CompoundBorder(rbb, mb);
                lbl.setHorizontalAlignment(SwingConstants.LEFT);
                rightMoneyPanel.add(lbl);
            }
            lbl.setBorder(cb);           
        }
    }
    
//    public void createCasePanel()
    public void createCasePanel(ArrayList<Case> caseList)
    {
        casePanel.setBackground(Color.black);
        casePanel.setLayout(null);
        Point location = new Point(25, 432);
        int xIncrease = 135;
        int yIncrease = 105;
        
        int counter = 0;
        for(int k = 0; k < 5; k++)
        {
            for(int j = 0; j < 5; j++)
            {
//                Case temp = caseListBtn.get(counter);
                Case temp = caseList.get(counter);
                if(temp.isPlayerCase())
                {
//                    temp = caseListBtn.get(++counter);
                    temp = caseListBtn.get(++counter);
                }
                temp.setLocation(location);
                location.x += xIncrease;
                casePanel.add(temp);
                counter++;
            }
            location.y -= yIncrease;
            location.x = 25;
        }
    }
        
    public void setController(ActionListener controller)
    {
        loginBtn.addActionListener(controller);
    }
    
    public void setCaseController(ActionListener controller)
    {
        for(Case c : caseListBtn)
        {
            c.addActionListener(controller);
        }        
    }
    
    public void setItemController(ItemListener controller)
    {
        tb.addItemListener(controller);
    }
}


//THIS WAS INSIDE CREATE MONEY PANELS        
//        for(GradientCmp lbl : caseValuesList)
//        {
//            lbl.setOpaque(false);
//            lbl.setFont(new Font("Arial", Font.BOLD, 20));
//            lbl.setForeground(Color.WHITE);
//            lbl.setMinimumSize(new Dimension(width, height));
//            lbl.setPreferredSize(new Dimension(width, height));
//            lbl.setMaximumSize(new Dimension(width, height));
//            
//            BevelBorder rbb = new BevelBorder(BevelBorder.RAISED);
//            MatteBorder mb;
//            CompoundBorder cb;
//            if(leftMoneyPanel.getComponents().length < 13)
//            {
//                mb = new MatteBorder(0, 0, 0, 1, (new Color(255, 204, 51)));     
//                cb = new CompoundBorder(rbb, mb);
//                lbl.setHorizontalAlignment(SwingConstants.RIGHT);
//                leftMoneyPanel.add(lbl);
//            }
//            else
//            {
//                mb = new MatteBorder(0, 1, 0, 0, (new Color(255, 204, 51)));
//                cb = new CompoundBorder(rbb, mb);
//                lbl.setHorizontalAlignment(SwingConstants.LEFT);
//                rightMoneyPanel.add(lbl);
//            }
//            lbl.setBorder(cb);
//        }