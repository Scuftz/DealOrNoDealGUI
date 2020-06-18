/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PanelPackage;

import java.awt.Font;
import java.awt.GridBagConstraints;
import static java.awt.GridBagConstraints.CENTER;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author shivn
 */
public class DealEndPanel extends CurvedJPanel
{
    private boolean dealAccepted;
    private JLabel dealNoDealLbl, bankedOffer, caseValueLbl, yourCaseLbl, messageLbl, highScoreLbl, highScoreValueLbl, topScoreLbl, topScoreValueLbl;
    private JButton playAgainBtn, quitBtn;
    private GridBagConstraints gbc = new GridBagConstraints();
    private Font f = new Font("Dialog", Font.BOLD, 40);
    
    public DealEndPanel()
    {
        setLayout(new GridBagLayout());
        setOpaque(false);
        setSize(1200, 609);
        gbc.anchor = CENTER;
        
        gbc.insets.top = 20;
        gbc.insets.bottom = 20;
        gbc.insets.left = 20;
        gbc.insets.right = 20;
        
        dealNoDealLbl = new JLabel("DEAL: ");
        dealNoDealLbl.setFont(f);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(dealNoDealLbl, gbc);
        
        bankedOffer = new JLabel("$20,000");
        bankedOffer.setFont(f);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(bankedOffer, gbc);
        
        yourCaseLbl = new JLabel("YOUR CASE: ");
        yourCaseLbl.setFont(f);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(yourCaseLbl, gbc);
        
        caseValueLbl = new JLabel("$200,000");
        caseValueLbl.setFont(f);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(caseValueLbl, gbc);
        
        messageLbl = new JLabel("Congrats! You made the right call!");
        messageLbl.setFont(f);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(messageLbl, gbc);
        
        highScoreLbl = new JLabel("Your High Score: ");
        highScoreLbl.setFont(f);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        add(highScoreLbl, gbc);
        
        highScoreValueLbl = new JLabel("$100,000");
        highScoreValueLbl.setFont(f);
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(highScoreValueLbl, gbc);
        
        topScoreLbl = new JLabel("All-Time High Score: ");
        topScoreLbl.setFont(f);
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(topScoreLbl, gbc);
        
        topScoreValueLbl = new JLabel("$4,000");
        topScoreValueLbl.setFont(f);
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(topScoreValueLbl, gbc);
        
        playAgainBtn = new JButton("Play Again");
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(playAgainBtn, gbc);
        
        quitBtn = new JButton("Quit Game");
        gbc.gridx = 1;
        gbc.gridy = 5;
        add(quitBtn, gbc);
    }
}
