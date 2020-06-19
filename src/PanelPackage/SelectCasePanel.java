package PanelPackage;
import DealOrNoDealGUI.Case;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * PDC Assignment 2
 * This is the SelectCasePanel Class, used to display all the cases at the start for the user to choose from
 * @author Shivneel Singh (18021394)
 * @since 11/06/2020
 */
public class SelectCasePanel extends JPanel
{
    /**
     * Variables
     */
    ArrayList<Case> caseList;
    
    /**
     * Constructor
     * @param caseList   The cases to be displayed
     */
    public SelectCasePanel(ArrayList<Case> caseList)
    {
        setLayout(new GridLayout(4, 7, 25, 25));
        setBounds(200, 130, 790, 350);
        setBackground(new Color(255,255,255,100));
        this.caseList = caseList;
        createCasePanel();
    }
    
    /**
     * This method will add all the cases to the panel
     */
    public void createCasePanel()
    {
        for(Case c : caseList)
        {
            add(c);
        }
    }
}
