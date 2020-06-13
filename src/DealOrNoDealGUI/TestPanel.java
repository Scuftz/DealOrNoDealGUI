/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DealOrNoDealGUI;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author shivn
 */
public class TestPanel extends JPanel
{
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        int w = getWidth();
        int h = getHeight();
        System.out.println(h);
//        Color c2 = new Color(176, 149, 0);
//        Color c2 = new Color(50, 50, 0);
        Color c2 = Color.YELLOW;
        Color c1 = Color.BLACK;
        GradientPaint gp = new GradientPaint(0, 50, c1, 0, 420, c2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
    }
}
