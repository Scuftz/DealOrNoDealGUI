package PanelPackage;
import SpecialClassPackage.FlashButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author shivn
 */
public class GameHeaderPanel extends ImagePanel
{
    private JLabel caseRemainingNumberLbl, casesToOpenLbl;
    private JButton colouredBtn;
    private Color[] colourArray;
    private ArrayList<FlashButton> tbs;
    
    public GameHeaderPanel(int casesToOpen, ArrayList<FlashButton> flashBtn)
    {
        setLayout(null);
        setOpaque(false);
        setPreferredSize(new Dimension(1200, 50));
        
        createCaseRemainingLabel(casesToOpen);
        add(caseRemainingNumberLbl);
        createCaseToOpenLabel();
        add(casesToOpenLbl);
        tbs = flashBtn;
        addButtons(tbs);
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
    
    public void addButtons(ArrayList<FlashButton> flashBtn)
    {
        for (FlashButton fb : flashBtn)
        {
            add(fb);
        }
    }
    
    public ArrayList<FlashButton> getButtons()
    {
        return this.tbs;
    }
}