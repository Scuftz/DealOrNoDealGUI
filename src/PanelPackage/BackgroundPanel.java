/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PanelPackage;

import DealOrNoDealGUI.*;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author shivn
 */
public class BackgroundPanel extends ImagePanel
{
    public BackgroundPanel()
    {
        setLayout(null);
        setLocation(0,0);
        setSize(1200, 609);
        try {
            Image img = ImageIO.read(new File("logo.jpg"));
            setBackground(img);
        } catch (IOException ex) {
            System.out.println("io excpo");
        }   
//        backgroundPanel.add(loginPanel);    
    }        
}
