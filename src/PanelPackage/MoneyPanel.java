package PanelPackage;
import SpecialClassPackage.GradientLabel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;

/**
 * PDC Assignment 2
 * This is the MoneyPanel Class, used to display the money values in the game
 * @author Shivneel Singh (18021394)
 * @since 11/06/2020
 */
public class MoneyPanel
{
    /**
     * Variables
     */
    private JPanel leftMoneyPanel;
    private JPanel rightMoneyPanel;
    private LinkedHashMap<Integer, GradientLabel> moneyLabels;
    
    /**
     * Constructor
     * @param moneyLbls   All of the value labels to be displayed
     */
    public MoneyPanel(LinkedHashMap<Integer, GradientLabel> moneyLbls)
    {
        this.leftMoneyPanel = new JPanel();
        this.rightMoneyPanel = new JPanel();
        this.moneyLabels = moneyLbls;
        createMoneyPanels();
    }
    
    /**
     * This method will create both the left and right money panels
     */
    public void createMoneyPanels()
    {
        this.leftMoneyPanel.setLayout(new BoxLayout(this.leftMoneyPanel, BoxLayout.Y_AXIS));
        this.rightMoneyPanel.setLayout(new BoxLayout(this.rightMoneyPanel, BoxLayout.Y_AXIS));
        int width = 250;
        int height = 40;
                
        for(Map.Entry<Integer, GradientLabel> entry  : moneyLabels.entrySet())
        {
            GradientLabel lbl = entry.getValue();
            lbl.setOpaque(false);
            lbl.setFont(new Font("Arial", Font.BOLD, 20));
            lbl.setForeground(Color.WHITE);
            lbl.setMinimumSize(new Dimension(width, height));
            lbl.setPreferredSize(new Dimension(width, height));
            lbl.setMaximumSize(new Dimension(width, height));
            
            BevelBorder rbb = new BevelBorder(BevelBorder.RAISED);
            MatteBorder mb;
            CompoundBorder cb;
            if(leftMoneyPanel.getComponents().length < 13)
            {
                mb = new MatteBorder(0, 0, 0, 1, (new Color(255, 204, 51)));     
                cb = new CompoundBorder(rbb, mb);
                lbl.setHorizontalAlignment(SwingConstants.RIGHT);
                leftMoneyPanel.add(lbl);
            }
            else
            {
                mb = new MatteBorder(0, 1, 0, 0, (new Color(255, 204, 51)));
                cb = new CompoundBorder(rbb, mb);
                lbl.setHorizontalAlignment(SwingConstants.LEFT);
                rightMoneyPanel.add(lbl);
            }
            lbl.setBorder(cb);           
        }
    }
    
    /**
     * This method will get the left money panel
     * @return   The left money panel
     */
    public JPanel getLeftPanel()
    {
        return this.leftMoneyPanel;
    }
    
    /**
     * This method will get the right money panel
     * @return   The right money panel
     */
    public JPanel getRightPanel()
    {
        return this.rightMoneyPanel;
    }
}