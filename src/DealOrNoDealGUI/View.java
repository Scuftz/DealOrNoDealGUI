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
import javax.swing.Timer;

public class View extends JFrame implements Observer
{
    /**
     * Variables
     */
    public boolean setUp = false;
    private NumberFormat nf = NumberFormat.getNumberInstance();
    public JToggleButton tb = new JToggleButton("Deal");
    private Dimension screenDimension, frameDimension;

    protected PanelPackage.BackgroundPanels bp;
    protected PanelPackage.LoginPanels lp;
    protected PanelPackage.GameHeaderPanel ghp;
    protected PanelPackage.CasePanel cp;
    protected PanelPackage.MoneyPanel mp;
    
    private ImagePanel /*backgroundPanel,topPanel,*/ endOfGamePanel;
//    private CurvedJPanel loginPanel;
//    public JTextField usernameTxt;
//    public JPasswordField passwordTxt;
//    private JButton loginBtn;            
//    public JButton[] finalCases = new JButton[2];
//    public GradientLabel[] finalLabels = new GradientLabel[2];
            
    //in game panels and variables
    private JPanel mainGamePanel; //main panel
//    private JPanel /*casePanel, leftMoneyPanel,*/ rightMoneyPanel; //panels inside main game panel
//    private JLabel caseRemainingNumberLbl;//, usernameLbl, passwordLbl, loginLbl;
    private ArrayList<Case> caseListBtn = new ArrayList();
//    public Timer timer;
    
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
//        loginPanel = new CurvedJPanel();
        bp = new PanelPackage.BackgroundPanels();
//        backgroundPanel = new ImagePanel();
        
        mainGamePanel = new JPanel();
        //panels in main game panel
//        casePanel = new JPanel();
//        leftMoneyPanel = new JPanel();
//        rightMoneyPanel = new JPanel();
//        topPanel = new ImagePanel();
        tb.setName("Deal");
        
        endOfGamePanel = new ImagePanel();
        
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
            lp.setPasswordBlank();
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
            System.out.println("User Case Selected");
            update.caseSelected = true;
            createTopPanel(update.casesRemainingThisRound);
//            PanelPackage.GameHeaderPanel
            createMoneyPanels(update.tester);
            this.createCasePanel(update.caseList);
            this.displayMainGame();
        }
        else if (update.endOfGame)
        {
            System.out.println("End of Game");
            this.validate();
            this.repaint();
            System.out.println("Should've repainted");
//            timer.start();
            System.out.println("ouk");
            
//            this.getContentPane().removeAll();
//            this.displayEndOfGame();
            //go to end game panel, maybe introduce timer here with a thread when display shocker result
        }
        else if(update.endOfRound)
        {
            System.out.println("End of Round");
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
            System.out.println("Quitting Game");
        }
        else
        {
            System.out.println("Doing Else");
            this.updateCaseToOpen(update.casesRemainingThisRound);
        }
        if (!update.endOfGame)
        {
            System.out.println("V&P");
            this.validate();
            this.repaint();     
        }
    }
    
    public void displayEndOfGame()
    {
        endOfGamePanel.setLayout(null);
        endOfGamePanel.setOpaque(false);
        endOfGamePanel.setSize(1200, 609);
        
        for (Case c : caseListBtn)
        {
            if(!c.getOpenStatus() & !c.isPlayerCase())
            {
                System.out.println(c.getCaseNumber() + ": " + c.getCaseValue());
            }
        }
        add(endOfGamePanel);
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
//        caseRemainingNumberLbl.setText(Integer.toString(num));
        ghp.changeCaseRemainingValue(num);
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
        bp.add(lp);
        add(bp);
    }
    
    public void selectCasePanel(ArrayList<Case> caseList)
    {
        bp.remove(lp);
        JPanel selectCase = new JPanel(new GridLayout(4, 7, 25, 25));
        selectCase.setBounds(200, 130, 790, 350);
        selectCase.setBackground(new Color(255,255,255,100));
        for(Case c : caseList)
        {
            selectCase.add(c);
        }
        JLabel selectLbl = new JLabel("Select A Case!");
        selectLbl.setFont(new Font("Verdana", Font.BOLD, 40));
        selectLbl.setForeground(Color.YELLOW);
        selectLbl.setBounds(450, 30, 400, 40);
        bp.add(selectLbl);
        bp.add(selectCase);
        bp.revalidate();
        bp.repaint();
    }
    
    public void createLoginPanel()
    {
        lp = new PanelPackage.LoginPanels();
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
        
        mainGamePanel.add(ghp, BorderLayout.NORTH);
//        mainGamePanel.add(leftMoneyPanel, BorderLayout.WEST);
        mainGamePanel.add(mp.getLeftPanel(), BorderLayout.WEST);
//        mainGamePanel.add(rightMoneyPanel, BorderLayout.EAST);
        mainGamePanel.add(mp.getRightPanel(), BorderLayout.EAST);
//        mainGamePanel.add(casePanel, BorderLayout.CENTER);
        mainGamePanel.add(cp, BorderLayout.CENTER);
    }
    
    public void createTopPanel(int casesToOpen)
    {
        ghp = new PanelPackage.GameHeaderPanel(casesToOpen);
//        topPanel.setLayout(null);
//        topPanel.setOpaque(false);
//        topPanel.setPreferredSize(new Dimension(1200, 50));
        
//        caseRemainingNumberLbl = new JLabel(Integer.toString(casesToOpen));
//        caseRemainingNumberLbl.setBounds(590, 5, 25, 40);
//        Font f = caseRemainingNumberLbl.getFont
//        caseRemainingNumberLbl.setFont(new Font("Dialog", Font.BOLD, 40));

//        JLabel caseToOpenLbl = new JLabel("To Open");
//        caseToOpenLbl.setBounds(620, 15, 50, 20);
//
//        topPanel.add(caseRemainingNumberLbl);
//        topPanel.add(caseToOpenLbl);
        
//        JButton test;
//        Color[] c = new Color[]{ Color.RED, Color.GREEN, Color.BLUE };
//        int xPos = 70;
//        int xIncrease = 40;
//        for (int k = 0; k < 3; k++)
//        {
//            test = new JButton("");
//            test.setSize(20, 20);
//            test.setLocation(xPos, 15);
//            test.setBackground(c[k]);
//            test.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLACK, Color.BLACK));
//            topPanel.add(test);
//            xPos += xIncrease;
//        }
//        xPos = 1025;
//        for (int k = 0; k < 3; k++)
//        {
//            test = new JButton("");
//            test.setSize(20, 20);
//            test.setLocation(xPos, 15);
//            test.setBackground(c[k]);
//            test.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLACK, Color.BLACK));
//            topPanel.add(test);
//            xPos += xIncrease;
//        }     
    }
    
    public void createMoneyPanels(LinkedHashMap<Integer, GradientLabel> valueLbls)
    {
        mp = new PanelPackage.MoneyPanel(valueLbls);
    }
    
    public void createCasePanel(ArrayList<Case> caseList)
    {
        cp = new PanelPackage.CasePanel(caseList);
    }
        
    public void setController(ActionListener controller)
    {
        lp.setController(controller);
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