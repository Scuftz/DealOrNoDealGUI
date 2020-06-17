/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author shivn
 */
public class MoneyPanel
{
    private JPanel leftMoneyPanel;
    private JPanel rightMoneyPanel;
    private LinkedHashMap<Integer, GradientLabel> valueLbls;
    
    public MoneyPanel(LinkedHashMap<Integer, GradientLabel> valueLbls)
    {
        this.leftMoneyPanel = new JPanel();
        this.rightMoneyPanel = new JPanel();
        this.valueLbls = valueLbls;
        createMoneyPanels();
    }
    
    public void createMoneyPanels()
    {
        this.leftMoneyPanel.setLayout(new BoxLayout(this.leftMoneyPanel, BoxLayout.Y_AXIS));
        this.rightMoneyPanel.setLayout(new BoxLayout(this.rightMoneyPanel, BoxLayout.Y_AXIS));
        int width = 250;
        int height = 40;
                
        for(Map.Entry<Integer, GradientLabel> entry  : valueLbls.entrySet())
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
    
    public JPanel getLeftPanel()
    {
        return this.leftMoneyPanel;
    }
    
    public JPanel getRightPanel()
    {
        return this.rightMoneyPanel;
    }
}