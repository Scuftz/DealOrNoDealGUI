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

/**
 * PDC Assignment 2
 * This is the View class, part of the MVC
 * @author Shivneel Singh (18021394)
 * @since 11/06/2020
 */
public class View extends JFrame implements Observer, Runnable
{
    /**
     * Variables and Formats
     */
    private int result;
    private NumberFormat nf = NumberFormat.getNumberInstance();
    public JToggleButton dealAcceptedToggle = new JToggleButton("Deal"); //has to reset
    private Dimension screenDimension, frameDimension;
    protected Timer timer;
    private ArrayList<Case> caseListBtn = new ArrayList(); //reset

    /**
     * Panels used in the View
     */
    protected PanelPackage.BackgroundPanel backgroundPanel;
    protected PanelPackage.LoginPanel loginPanel;
    protected PanelPackage.GameHeaderPanel gameHeaderPanel;
    protected PanelPackage.CasePanel casePanel;
    protected PanelPackage.MoneyPanel moneyPanels;
    protected PanelPackage.MainGamePanel mainGamePanel;
    protected PanelPackage.SelectCasePanel selectCasePanel;
    protected PanelPackage.EndOfGamePanel endPanel;
    
    
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
        dealAcceptedToggle.setName("Deal");
        createLoginPanel();
        timer = new Timer(250, null);
    }
        
    /**
     * This is the update method which the view uses to check what has been updated when the
     * Model has notified the view of a change
     * @param obs   The model being observed
     * @param obj   The update class which contains the variables and methods which the View will use
     */
    @Override
    public void update(Observable obs, Object obj) {
        UpdateInfo update = (UpdateInfo) obj;
        
        if (update.getQuitGameFlag()) //Player quit game
        {
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
        else if(!update.getLoginFlag()) //Player failed to log in
        {
            if(update.getBlankFlag())
            {
                System.out.println("Please Enter All Details");
                JOptionPane.showMessageDialog(null, "Please Enter All Details", "Login Failed", 2);
                update.setBlankFlag(false);
            }
            else
            {
                System.out.println("Login Attempt Failed");
                JOptionPane.showMessageDialog(null, "Incorrect Login Details", "Login Failed", 0);
                loginPanel.setPasswordBlank();
            }
        }
        else if(!update.getGameStartedFlag()) //If game hasn't started, set up game
        {
            update.setGameStartedFlag(true);
            this.createCasesForController(update.getCaseList(), update.getTotalAmountOfCases());
            this.selectCasePanel(update.getCaseList());
        }
        else if (!update.getCaseSelectedFlag()) //If the player hasn't selected their case, set up the case panel
        {
            System.out.println("User Case Selected");
            update.setCaseSelectedFlag(true);
            createTopPanel(update.getCasesRemainingThisRound(), update.getFlashBtns());
            createMoneyPanels(update.getMoneyLabels());
            this.createCasePanel(update.getCaseList());
            this.displayMainGame();
        }
        else if (update.getEndOfGameFlag()) //If the game has ended
        {
            System.out.println("End of Game");
            this.getContentPane().removeAll();
            this.displayEndOfGame(update.getDealAcceptedFlag(), update.getPlayerCase().getCaseValue(), update.getBankOffer(), update.getPlayerHighScore(), update.getAllTimeScore());
        }
        else if(update.getEndOfRoundFlag()) //If the round has ended
        {
            update.setEndOfRoundFlag(false);
            System.out.println("End of Round");
            this.updateCaseToOpen(update.getCasesRemainingThisRound());
            
            SwingUtilities.invokeLater(this);
            SwingUtilities.invokeLater(new Runnable()
            {
                @Override
                public void run()
                {
                    timer.start();
                }
            });
            do
            {
                displayBankOffer(update.getBankOffer());
                System.out.println(result);
                if(result == 0)
                {
                    update.setDealAcceptedFlag(true);
                    dealAcceptedToggle.setSelected((!dealAcceptedToggle.isSelected()));
                }
            } while (result < 0);
        }
        else //Update cases left in the round
        {
            this.updateCaseToOpen(update.getCasesRemainingThisRound());
        }
        this.validate();
        this.repaint();
    }
    
    /**
     * Run method implemented from Runnable
     * Allows the GUI to be updated in while the player receives a bank offer
     */
    @Override
    public void run()
    {
        this.validate();
        this.repaint();
    }
    
    /**
     * This method will create a pop up that will display to the user the bank offer
     * @param bankOffer   The bank offer to be displayed
     */
    public void displayBankOffer(int bankOffer)
    {
        Object[] options = {"Deal", "No Deal"};
        result = JOptionPane.showOptionDialog(null, ("BANK OFFER: $" + nf.format(bankOffer)), "Bank Offer",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
    }
        
    /**
     * This method will set up the end of game panel
     * @param dealAccepted   Has the player accepted a bank offer
     * @param userCaseValue  The case value of the player's case
     * @param bankOffer      The highest bank offer the player has received
     * @param userHighScore  The player's current high score
     * @param allTimeScore   The all time high score across all players
     */
    public void displayEndOfGame(boolean dealAccepted, int userCaseValue, int bankOffer, int userHighScore, int allTimeScore)
    {
        backgroundPanel.removeAll();
        endPanel.updatePanel(dealAccepted, userCaseValue, bankOffer, userHighScore, allTimeScore);
        backgroundPanel.add(endPanel);
        add(backgroundPanel);
    }
    
    /**
     * This method will update the amount of cases to open in the game
     * @param num   Amount of cases to open
     */
    public void updateCaseToOpen(int num)
    {
        gameHeaderPanel.changeCaseRemainingValue(num);
    }
    
    /**
     * This method will add the controller as ActionListeners for all the Case buttons in the game
     * @param caseList   The list of cases
     * @param totalAmountOfCases   The total amount of cases in the game
     */
    public void createCasesForController(ArrayList<Case> caseList, int totalAmountOfCases)
    {
        for (int k = 1; k <= totalAmountOfCases; k++)
        {
            caseListBtn.add(caseList.get(k - 1));
        }
    }
    
    /**
     * This method will create the login panel
     */
    public void createLoginPanel()
    {
        backgroundPanel = new PanelPackage.BackgroundPanel();
        loginPanel = new PanelPackage.LoginPanel();
        endPanel = new PanelPackage.EndOfGamePanel(); //this is only to add actions listeners to buttons
        backgroundPanel.add(loginPanel);
        add(backgroundPanel);
    }
    
    /**
     * This method will create the case selection panel, where the player decides their case
     * @param caseList   The list of cases the player can choose from
     */
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
    
    /**
     * This method will display the main game panel, where the magic of the game happens
     */
    public void displayMainGame()
    {
        this.getContentPane().removeAll();
        createMainGamePanel();
        getContentPane().add(mainGamePanel);       
        this.revalidate();
        this.repaint();
    }
       
    /**
     * This method will create the main game panel, the main display of the game
     */
    public void createMainGamePanel()
    {
        mainGamePanel = new PanelPackage.MainGamePanel(frameDimension);
        mainGamePanel.add(gameHeaderPanel, BorderLayout.NORTH);
        mainGamePanel.add(moneyPanels.getLeftPanel(), BorderLayout.WEST);
        mainGamePanel.add(moneyPanels.getRightPanel(), BorderLayout.EAST);
        mainGamePanel.add(casePanel, BorderLayout.CENTER);
    }
    
    /**
     * This method will create the top panel in the game where the amount of cases to open per round is shown
     * And where the flashing buttons are
     * @param casesToOpen   The amount of cases to open
     * @param flashBtn      The flashing buttons used in the game
     */
    public void createTopPanel(int casesToOpen, ArrayList<FlashButton> flashBtn)
    {
        gameHeaderPanel = new PanelPackage.GameHeaderPanel(casesToOpen, flashBtn);  
    }
    
    /**
     * This method will create the money panels in the main game panel
     * @param moneyLabels   The money labels used to display the money values
     */
    public void createMoneyPanels(LinkedHashMap<Integer, GradientLabel> moneyLabels)
    {
        moneyPanels = new PanelPackage.MoneyPanel(moneyLabels);
    }
    
    /**
     * This method will create the main game case panel in the centre of the main game panel
     * @param caseList   The list of cases in the game
     */
    public void createCasePanel(ArrayList<Case> caseList)
    {
        casePanel = new PanelPackage.CasePanel(caseList);
    }
     
    /**
     * This method sets the controllers for the login button, quit game button, and the timer
     * @param controller   The ActionListener (the Controller) used to listen to these components
     */
    public void setController(ActionListener controller)
    {
        loginPanel.setButtonListener(controller);
        endPanel.setButtonListener(controller);
        timer.addActionListener(controller);
    }
    
    /**
     * This method sets the controllers for the Case buttons in the game
     * This is defined separately as they are not created upon the Controller's instantiation in the main application
     * Thus are stored separately
     * @param controller   The ActionListener (the Controller) used to listen to the buttons
     */
    public void setCaseController(ActionListener controller)
    {
        for(Case c : caseListBtn)
        {
            c.addActionListener(controller);
        }        
    }
    
    /**
     * This method sets the controller for the dealAccepted toggle button
     * @param controller   The ItemListener (the Controller) used to listen to the toggle button
     */
    public void setItemController(ItemListener controller)
    {
        dealAcceptedToggle.addItemListener(controller);
    }
}