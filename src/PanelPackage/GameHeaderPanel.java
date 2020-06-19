package PanelPackage;
import SpecialClassPackage.FlashButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 * PDC Assignment 2
 * This is the GameHeaderPanel Class, used to create the top panel of the main game panel
 * @author Shivneel Singh (18021394)
 * @since 11/06/2020
 */
public class GameHeaderPanel extends ImagePanel
{
    /**
     * Variables
     */
    private JLabel caseRemainingNumberLbl, casesToOpenLbl;
    private JButton colouredBtn;
    private Color[] colourArray;
    private ArrayList<FlashButton> flashingButtons;
    
    /**
     * Constructor
     * @param casesToOpen   The amount of cases to be opened in the first round
     * @param flashBtn   The flashing buttons added to the panel
     */
    public GameHeaderPanel(int casesToOpen, ArrayList<FlashButton> flashBtns)
    {
        setLayout(null);
        setOpaque(false);
        setPreferredSize(new Dimension(1200, 50));
        
        createCaseRemainingLabel(casesToOpen);
        add(caseRemainingNumberLbl);
        createCaseToOpenLabel();
        add(casesToOpenLbl);
        flashingButtons = flashBtns;
        addButtons(flashingButtons);
    }
    
    /**
     * This method will set the cases remaining value
     * @param casesToOpen   Amount of cases to open in the round
     */
    public void changeCaseRemainingValue(int casesToOpen)
    {
        caseRemainingNumberLbl.setText(Integer.toString(casesToOpen));
    }
    
    /**
     * This method will create the cases remaining label
     * @param casesToOpen   The amount of cases to be opened
     */
    public void createCaseRemainingLabel(int casesToOpen)
    {
        caseRemainingNumberLbl = new JLabel(Integer.toString(casesToOpen));
        caseRemainingNumberLbl.setBounds(590, 5, 25, 40);
        caseRemainingNumberLbl.setFont(new Font("Dialog", Font.BOLD, 40));      
    }
    
    /**
     * This method will create the 'To Open' label
     */
    public void createCaseToOpenLabel()
    {
        casesToOpenLbl = new JLabel("To Open");
        casesToOpenLbl.setBounds(620, 15, 50, 20);
    }
    
    /**
     * This method will add the flashing buttons to the panel
     * @param flashBtn   The list containing the flashing buttons
     */
    public void addButtons(ArrayList<FlashButton> flashBtn)
    {
        for (FlashButton fb : flashBtn)
        {
            add(fb);
        }
    }
    
    /**
     * This method
     * @return 
     */
    public ArrayList<FlashButton> getButtons()
    {
        return this.flashingButtons;
    }
}