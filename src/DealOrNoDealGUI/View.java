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
import java.util.concurrent.locks.*;
import javax.swing.*;

public class View extends JFrame implements Observer, Runnable
{
    protected Lock lock = new ReentrantLock();
    protected Condition con = lock.newCondition();
    private int result;
    
    public boolean setUp = false;
    private NumberFormat nf = NumberFormat.getNumberInstance();
    public JToggleButton tb = new JToggleButton("Deal");
    private Dimension screenDimension, frameDimension;

    protected Timer timer;
    protected PanelPackage.BackgroundPanel bp;
    protected PanelPackage.LoginPanel lp;
    protected PanelPackage.GameHeaderPanel ghp;
    protected PanelPackage.CasePanel cp;
    protected PanelPackage.MoneyPanel mp;
    protected PanelPackage.MainGamePanel mgp;
    protected PanelPackage.SelectCasePanel scp;
    protected PanelPackage.EndOfGamePanel ep;
    
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
        tb.setName("Deal");
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
            createTopPanel(update.casesRemainingThisRound, update.flashBtn);
            createMoneyPanels(update.tester);
            this.createCasePanel(update.caseList);
            this.displayMainGame();
        }
        else if (update.endOfGame)
        {
            System.out.println("End of Game");
            this.getContentPane().removeAll();
            this.displayEndOfGame(update.dealAccepted);
            //go to end game panel, maybe introduce timer here with a thread when display shocker 
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
                tb.setSelected(true);
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
        
    public void displayEndOfGame(boolean dealAccepted)
    {
        bp.removeAll();
        ep = new PanelPackage.EndOfGamePanel(dealAccepted);
        bp.add(ep);
        add(bp);
    }
    
    public void updateCaseToOpen(int num)
    {
        ghp.changeCaseRemainingValue(num);
    }
    
    public void createCasesForController(ArrayList<Case> caseList)
    {
        for (int k = 1; k <= 26; k++)
        {
            caseListBtn.add(caseList.get(k - 1));
        }
    }
    
    public void createLoginPanel()
    {
        bp = new PanelPackage.BackgroundPanel();
        lp = new PanelPackage.LoginPanel();
        bp.add(lp);
        add(bp);
    }
    
    public void selectCasePanel(ArrayList<Case> caseList)
    {
        bp.remove(lp);
        scp = new PanelPackage.SelectCasePanel(caseList);
        JLabel selectLbl = new JLabel("Select A Case!");
        selectLbl.setFont(new Font("Verdana", Font.BOLD, 40));
        selectLbl.setForeground(Color.YELLOW);
        selectLbl.setBounds(450, 30, 400, 40);
        bp.add(selectLbl);
        bp.add(scp);
        bp.revalidate();
        bp.repaint();
    }
    
    public void displayMainGame()
    {
        this.getContentPane().removeAll();
        createMainGamePanel();
        getContentPane().add(mgp);       
        this.revalidate();
        this.repaint();
    }
       
    public void createMainGamePanel()
    {
        mgp = new PanelPackage.MainGamePanel(frameDimension);
        mgp.add(ghp, BorderLayout.NORTH);
        mgp.add(mp.getLeftPanel(), BorderLayout.WEST);
        mgp.add(mp.getRightPanel(), BorderLayout.EAST);
        mgp.add(cp, BorderLayout.CENTER);
    }
    
    public void createTopPanel(int casesToOpen, ArrayList<FlashButton> flashBtn)
    {
        ghp = new PanelPackage.GameHeaderPanel(casesToOpen, flashBtn);  
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
        lp.setButtonListener(controller);
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
        tb.addItemListener(controller);
    }
}