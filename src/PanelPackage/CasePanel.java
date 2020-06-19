package PanelPackage;
import DealOrNoDealGUI.Case;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * PDC Assignment 2
 * This is the CasePanel Class, used to display all the cases in the main game that don't belong to the player
 * @author Shivneel Singh (18021394)
 * @since 11/06/2020
 */
public class CasePanel extends JPanel
{
    /**
     * Variables
     */
    private ArrayList<Case> caseListBtn;
    
    /**
     * Constructor
     * @param caseListBtn   The list of cases to be displayed
     */
    public CasePanel(ArrayList<Case> caseListBtn)
    {
        setBackground(Color.black);
        setLayout(null);
        this.caseListBtn = caseListBtn;
        createCaseLocations();
    }
    
    /**
     * This method is used to add the cases to the panel
     */
    public void createCaseLocations()
    {
        Point location = new Point(25, 432);
        int xIncrease = 135;
        int yIncrease = 105;
        
        int counter = 0;
        for(int k = 0; k < 5; k++)
        {
            for(int j = 0; j < 5; j++)
            {
                Case tempCase = caseListBtn.get(counter);
                if(tempCase.isPlayerCase())
                {
                    tempCase = caseListBtn.get(++counter);
                }
                tempCase.setLocation(location);
                location.x += xIncrease;
                add(tempCase);
                counter++;
            }
            location.y -= yIncrease;
            location.x = 25;
        }
    }
}