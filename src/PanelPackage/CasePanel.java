/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    public CasePanel(ArrayList<Case> caseListBtn)
    {
        setBackground(Color.black);
        setLayout(null);
        Point location = new Point(25, 432);
        int xIncrease = 135;
        int yIncrease = 105;
        
        int counter = 0;
        for(int k = 0; k < 5; k++)
        {
            for(int j = 0; j < 5; j++)
            {
                Case temp = caseListBtn.get(counter);
                if(temp.isPlayerCase())
                {
                    temp = caseListBtn.get(++counter);
                }
                temp.setLocation(location);
                location.x += xIncrease;
                add(temp);
                counter++;
            }
            location.y -= yIncrease;
            location.x = 25;
        }
    }
    
    public void createCaseLocations()
    {
        
    }
}
