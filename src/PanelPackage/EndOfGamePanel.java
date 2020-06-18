package PanelPackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import static java.awt.GridBagConstraints.CENTER;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.text.NumberFormat;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;

/**
 *
 * @author shivn
 */
public class EndOfGamePanel extends CurvedJPanel
{
    private boolean dealAccepted;
    private JLabel dealNoDealLbl, bankedOffer, caseValueLbl, yourCaseLbl, messageLbl, highScoreLbl, highScoreValueLbl, topScoreLbl, topScoreValueLbl;
    private JButton playAgainBtn, quitBtn;
    private GridBagConstraints gbc = new GridBagConstraints();
    private Font f = new Font("Times New Roman", Font.BOLD, 30);
    private Font f2 = new Font("Sitka Heading", Font.BOLD, 34);
    private int userCaseValue, bankOffer, userHighScore, allTimeHighScore;
    private NumberFormat nf = NumberFormat.getNumberInstance();

    public EndOfGamePanel(boolean dealAccepted, int userCaseValue, int bankOffer, int userHighScore, int allTimeScore)
    {
        this.userCaseValue = userCaseValue;
        this.bankOffer = bankOffer;
        this.userHighScore = userHighScore;
        this.allTimeHighScore = allTimeScore;
        
        setLayout(new GridBagLayout());
        setOpaque(false);
        setBounds(275, 40, 650, 500);
        setBackground(new Color(255, 219, 77, 240));
        setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));//, Color.lightGray, Color.lightGray, Color.white, Color.white))
        this.buildEndScreen();
        this.dealAccepted = dealAccepted;
        if(dealAccepted)
        {
            dealEndScreen();
        }
        else
        {
            noDealEndScreen();
        }
    }
    
    public void dealEndScreen()
    {
        dealNoDealLbl.setText("YOU TOOK A DEAL WORTH:");
        bankedOffer.setForeground(new Color(0,153,51));
        if(this.bankOffer > this.userCaseValue)
        {
            messageLbl.setText("Congrats! You made the right call...");
        }
        else
        {
            messageLbl.setText("You should've risked it!");
        }
    }
    public void noDealEndScreen()
    {
        dealNoDealLbl.setText("YOU DECLINED AN OFFER OF:");
        bankedOffer.setForeground(new Color(230,0,0));
        if(this.bankOffer < this.userCaseValue)
        {
            messageLbl.setText("Congrats! You made the right call...");
            bankedOffer.setForeground(Color.BLACK);
            caseValueLbl.setForeground(new Color(0,153,51));
        }
        else
        {
            messageLbl.setText("Should've taken that deal!");
        }
    }
    
    public void buildEndScreen()
    {
        gbc.anchor = CENTER;
        
        gbc.insets.top = 20;
        gbc.insets.bottom = 20;
        gbc.insets.left = 20;
        gbc.insets.right = 20;
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        dealNoDealLbl = new JLabel("PLACEHOLDER");
        dealNoDealLbl.setForeground(new Color(0,0,0));
        dealNoDealLbl.setFont(f);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(dealNoDealLbl, gbc);
        
        bankedOffer = new JLabel("$" + nf.format(bankOffer));
        bankedOffer.setFont(f2);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(bankedOffer, gbc);
        
        yourCaseLbl = new JLabel("YOUR CASE: ");
        yourCaseLbl.setForeground(new Color(0,0,0));
        yourCaseLbl.setFont(f);
        gbc.gridx = 0;
        gbc.gridy = 1;
        System.out.println("!!!!!!!!!" + gbc.fill);
        add(yourCaseLbl, gbc);
        
        caseValueLbl = new JLabel("$" + nf.format(userCaseValue));
                caseValueLbl.setForeground(new Color(0,0,0));

        caseValueLbl.setFont(f2);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(caseValueLbl, gbc);
        
        gbc.fill = 0;
        messageLbl = new JLabel("PLACEHOLDER");
        messageLbl.setForeground(new Color(0,0,0));
        messageLbl.setFont(f);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(messageLbl, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        System.out.println("?????????????" + gbc.fill);
        highScoreLbl = new JLabel("Your High Score: ");
        highScoreLbl.setForeground(new Color(0,0,0));
        highScoreLbl.setFont(f);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        add(highScoreLbl, gbc);
        
        highScoreValueLbl = new JLabel("$" + nf.format(userHighScore));
        highScoreValueLbl.setForeground(new Color(0,0,0));
        highScoreValueLbl.setFont(f2);
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(highScoreValueLbl, gbc);
        
        topScoreLbl = new JLabel("All-Time High Score: ");
        topScoreLbl.setForeground(new Color(0,0,0));
        topScoreLbl.setFont(f);
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(topScoreLbl, gbc);
        
        topScoreValueLbl = new JLabel("$" + nf.format(allTimeHighScore));
        topScoreValueLbl.setForeground(new Color(0,0,0));
        topScoreValueLbl.setFont(f2);
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(topScoreValueLbl, gbc);
        
        playAgainBtn = new JButton("Play Again");
        playAgainBtn.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(playAgainBtn, gbc);
        
        quitBtn = new JButton("Quit Game");
        quitBtn.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        gbc.gridx = 1;
        gbc.gridy = 5;
        add(quitBtn, gbc);
    }
    
    public void noDealBuilderEndScreen()
    {
        
    }
    
}
