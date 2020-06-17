package PanelPackage;
import DealOrNoDealGUI.Case;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author shivn
 */
public class SelectCasePanel extends JPanel
{
    ArrayList<Case> caseList;
    
    public SelectCasePanel(ArrayList<Case> ccaseList)
    {
        setLayout(new GridLayout(4, 7, 25, 25));
        setBounds(200, 130, 790, 350);
        setBackground(new Color(255,255,255,100));
        this.caseList = ccaseList;
        createCasePanel();
    }
    
    public void createCasePanel()
    {
        for(Case c : caseList)
        {
            add(c);
        }
    }
}
