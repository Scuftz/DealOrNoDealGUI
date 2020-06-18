package PanelPackage;
import SpecialClassPackage.ThreadedButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

/**
 *
 * @author shivn
 */
public class GameHeaderPanel extends ImagePanel
{
    private JLabel caseRemainingNumberLbl, casesToOpenLbl;
    private JButton colouredBtn;
    private Color[] colourArray;
    private Border border;
    private ArrayList<ThreadedButton> tbs = new ArrayList();
    
    public GameHeaderPanel(int casesToOpen)
    {
        setLayout(null);
        setOpaque(false);
        setPreferredSize(new Dimension(1200, 50));
        
        createCaseRemainingLabel(casesToOpen);
        add(caseRemainingNumberLbl);
        createCaseToOpenLabel();
        add(casesToOpenLbl);
        createButtons();
    }
    
    public void changeCaseRemainingValue(int casesToOpen)
    {
        caseRemainingNumberLbl.setText(Integer.toString(casesToOpen));
    }
    
    public void createCaseRemainingLabel(int casesToOpen)
    {
        caseRemainingNumberLbl = new JLabel(Integer.toString(casesToOpen));
        caseRemainingNumberLbl.setBounds(590, 5, 25, 40);
        caseRemainingNumberLbl.setFont(new Font("Dialog", Font.BOLD, 40));      
    }
    public void createCaseToOpenLabel()
    {
        casesToOpenLbl = new JLabel("To Open");
        casesToOpenLbl.setBounds(620, 15, 50, 20);
    }
    
    public void createButtons()
    {
        SpecialClassPackage.ThreadedButton threadButton;
        int[] xLocations = new int[]{70, 110, 150, 1025, 1065, 1105};
        int yLocation = 15;
        SpecialClassPackage.MoneyValueType mvt;
        for (int k = 0; k < 6; k++)
        {
            if(k == 0 || k == 3)
            {
                mvt = SpecialClassPackage.MoneyValueType.RED;
            }
            else if(k == 1 || k == 4)
            {
                mvt = SpecialClassPackage.MoneyValueType.GREEN;
            }
            else
            {
                mvt = SpecialClassPackage.MoneyValueType.BLUE;
            }
            threadButton = new SpecialClassPackage.ThreadedButton(xLocations[k], yLocation, mvt);
            tbs.add(threadButton);
            add(threadButton);
        }
    }
    public ArrayList<ThreadedButton> getButtons()
    {
        return this.tbs;
    }
}
