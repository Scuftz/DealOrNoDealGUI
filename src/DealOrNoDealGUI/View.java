package DealOrNoDealGUI;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.text.NumberFormat;
import java.util.*;
import javax.swing.*;
public class View extends JFrame implements Observer
{
    /**
     * Variables
     */
    public boolean setUp = false;
    private NumberFormat nf = NumberFormat.getNumberInstance();
    public JToggleButton tb = new JToggleButton("Deal");
    private Dimension screenDimension, frameDimension;

    protected PanelPackage.BackgroundPanel bp;
    protected PanelPackage.LoginPanels lp;
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
        ep = new PanelPackage.EndOfGamePanel();
//        endOfGamePanel.setLayout(null);
//        endOfGamePanel.setOpaque(false);
//        endOfGamePanel.setSize(1200, 609);
        
        for (Case c : caseListBtn)
        {
            if(!c.getOpenStatus() & !c.isPlayerCase())
            {
                System.out.println(c.getCaseNumber() + ": " + c.getCaseValue());
            }
        }
        add(ep);
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
        lp = new PanelPackage.LoginPanels();
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
    
    public void createTopPanel(int casesToOpen)
    {
        ghp = new PanelPackage.GameHeaderPanel(casesToOpen);  
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