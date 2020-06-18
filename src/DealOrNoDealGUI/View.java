package DealOrNoDealGUI;
import SpecialClassPackage.GradientLabel;
import SpecialClassPackage.FlashButton;
import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

public class View extends JFrame implements Observer, Runnable
{
    private int result;
    public boolean setUp = false;
    private NumberFormat nf = NumberFormat.getNumberInstance();
    public JToggleButton toggle = new JToggleButton("Deal");
    private Dimension screenDimension, frameDimension;

    protected Timer timer;
    protected PanelPackage.BackgroundPanel backgroundPanel;
    protected PanelPackage.LoginPanel loginPanel;
    protected PanelPackage.GameHeaderPanel gameHeaderPanel;
    protected PanelPackage.CasePanel casePanel;
    protected PanelPackage.MoneyPanel moneyPanels;
    protected PanelPackage.MainGamePanel mainGamePanel;
    protected PanelPackage.SelectCasePanel selectCasePanel;
    protected PanelPackage.EndOfGamePanel endPanel;
    
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
        toggle.setName("Deal");
        createLoginPanel();
        timer = new Timer(250, null);
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
            loginPanel.setPasswordBlank();
        }
        else if(!update.gameStarted)
        {
            update.loginFlag = true;
            update.gameStarted = true;
            this.createCasesForController(update.caseList, update.totalAmountOfCases);
            this.selectCasePanel(update.caseList);
        }
        else if (!update.caseSelected)
        {
            System.out.println("User Case Selected");
            update.caseSelected = true;
            createTopPanel(update.casesRemainingThisRound, update.flashBtn);
            createMoneyPanels(update.tester);
            this.createCasePanel(update.caseList);
            this.displayMainGame();
        }
        else if (update.endOfGame)
        {
            System.out.println("End of Game");
            this.getContentPane().removeAll();
            this.displayEndOfGame(update.dealAccepted, update.userCaseValue, update.bankOffer, update.userHighScore, update.allTimeHighScore);
        }
        else if(update.endOfRound)
        {
            update.endOfRound = false;
            System.out.println("End of Round");
            this.updateCaseToOpen(update.casesRemainingThisRound);
            
            SwingUtilities.invokeLater(this);
            SwingUtilities.invokeLater(new Runnable()
            {
                @Override
                public void run()
                {
                    timer.start();
                }
            });
            displayBankOffer(update.bankOffer);
            if(result == 0)
            {
                update.dealAccepted = true;
                toggle.setSelected(true);
            }
        }
        else if (update.quitGame)
        {
            System.out.println("Quitting Game");
        }
        else
        {
            this.updateCaseToOpen(update.casesRemainingThisRound);
        }
        this.validate();
        this.repaint();
    }
    
    @Override
    public void run()
    {
        this.validate();
        this.repaint();
    }
    
    public void displayBankOffer(int bankOffer)
    {
        System.out.println("We Are In Display Bank Offer--------------------------------");
        Object[] options = {"Deal", "No Deal"};
        result = JOptionPane.showOptionDialog(null, ("BANK OFFER: $" + nf.format(bankOffer)), "Bank Offer",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
        System.out.println("RINDBO: " + result);
    }
        
    public void displayEndOfGame(boolean dealAccepted, int userCaseValue, int bankOffer, int userHighScore, int allTimeScore)
    {
        backgroundPanel.removeAll();
        endPanel = new PanelPackage.EndOfGamePanel(dealAccepted, userCaseValue, bankOffer, userHighScore, allTimeScore);
        backgroundPanel.add(endPanel);
        add(backgroundPanel);
    }
    
    public void updateCaseToOpen(int num)
    {
        gameHeaderPanel.changeCaseRemainingValue(num);
    }
    
    public void createCasesForController(ArrayList<Case> caseList, int totalAmountOfCases)
    {
        for (int k = 1; k <= totalAmountOfCases; k++)
        {
            caseListBtn.add(caseList.get(k - 1));
        }
    }
    
    public void createLoginPanel()
    {
        backgroundPanel = new PanelPackage.BackgroundPanel();
        loginPanel = new PanelPackage.LoginPanel();
        backgroundPanel.add(loginPanel);
        add(backgroundPanel);
    }
    
    public void selectCasePanel(ArrayList<Case> caseList)
    {
        backgroundPanel.remove(loginPanel);
        selectCasePanel = new PanelPackage.SelectCasePanel(caseList);
        JLabel selectLbl = new JLabel("Select A Case!");
        selectLbl.setFont(new Font("Verdana", Font.BOLD, 40));
        selectLbl.setForeground(Color.YELLOW);
        selectLbl.setBounds(450, 30, 400, 40);
        backgroundPanel.add(selectLbl);
        backgroundPanel.add(selectCasePanel);
        backgroundPanel.revalidate();
        backgroundPanel.repaint();
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
        mainGamePanel = new PanelPackage.MainGamePanel(frameDimension);
        mainGamePanel.add(gameHeaderPanel, BorderLayout.NORTH);
        mainGamePanel.add(moneyPanels.getLeftPanel(), BorderLayout.WEST);
        mainGamePanel.add(moneyPanels.getRightPanel(), BorderLayout.EAST);
        mainGamePanel.add(casePanel, BorderLayout.CENTER);
    }
    
    public void createTopPanel(int casesToOpen, ArrayList<FlashButton> flashBtn)
    {
        gameHeaderPanel = new PanelPackage.GameHeaderPanel(casesToOpen, flashBtn);  
    }
    
    public void createMoneyPanels(LinkedHashMap<Integer, GradientLabel> valueLbls)
    {
        moneyPanels = new PanelPackage.MoneyPanel(valueLbls);
    }
    
    public void createCasePanel(ArrayList<Case> caseList)
    {
        casePanel = new PanelPackage.CasePanel(caseList);
    }
        
    public void setController(ActionListener controller)
    {
        loginPanel.setButtonListener(controller);
        timer.addActionListener(controller);
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
        toggle.addItemListener(controller);
    }
}