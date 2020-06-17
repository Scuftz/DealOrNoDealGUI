package PanelPackage;
import DealOrNoDealGUI.Case;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author shivn
 */
public class CasePanel extends JPanel
{
    private ArrayList<Case> caseListBtn;
    
    public CasePanel(ArrayList<Case> caseListBtn)
    {
        setBackground(Color.black);
        setLayout(null);
        this.caseListBtn = caseListBtn;
        createCaseLocations();
    }
    
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