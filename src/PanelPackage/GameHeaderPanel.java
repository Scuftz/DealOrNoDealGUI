package PanelPackage;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
        colourArray = new Color[]{ Color.RED, Color.GREEN, Color.BLUE };
        border = BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLACK, Color.BLACK);
        int xPosition = 70;
        int xIncrease = 40;
        for (int k = 0; k < 6; k++)
        {
            colouredBtn = new JButton("");
            colouredBtn.setSize(20, 20);
            colouredBtn.setBorder(border);
            
            if (k >= 3)
            {
                if (xPosition < 1025)
                {
                    xPosition = 1025;
                }
                colouredBtn.setBackground(colourArray[k - 3]);
            }
            else
            {
                colouredBtn.setBackground(colourArray[k]);
            }
            colouredBtn.setLocation(xPosition, 15);
            add(colouredBtn);
            xPosition += xIncrease;
        }
    }
}
