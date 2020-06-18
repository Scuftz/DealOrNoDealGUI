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
import javax.swing.JComponent;
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
    private Font f3 = new Font("Microsoft YaHei UI Light", Font.PLAIN, 20);
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
    
    public void addComponent(JComponent cmp, Font f, int gridx, int gridy, int gridwidth, int fill)
    {
        cmp.setForeground(Color.BLACK);
        cmp.setFont(f);
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.gridwidth = gridwidth;
        gbc.fill = fill;
        add(cmp, gbc);
    }
    
    public void buildEndScreen()
    {
        gbc.anchor = CENTER;
        gbc.insets.top = 20;
        gbc.insets.bottom = 20;
        gbc.insets.left = 20;
        gbc.insets.right = 20;
        
        dealNoDealLbl = new JLabel("PLACEHOLDER");
        addComponent(dealNoDealLbl, f, 0, 0, 1, 2);
        
        bankedOffer = new JLabel("$" + nf.format(bankOffer));
        addComponent(bankedOffer, f2, 1, 0, 1, 2);
        
        yourCaseLbl = new JLabel("YOUR CASE: ");
        addComponent(yourCaseLbl, f, 0, 1, 1, 2);
        
        caseValueLbl = new JLabel("$" + nf.format(userCaseValue));
        addComponent(caseValueLbl, f2, 1, 1, 1, 2);
        
        messageLbl = new JLabel("PLACEHOLDER");
        addComponent(messageLbl, new Font("Sylfaen", Font.PLAIN, 30), 0, 2, 2, 0);
  
        highScoreLbl = new JLabel("Your High Score: ");
        addComponent(highScoreLbl, f, 0, 3, 1, 2);

        highScoreValueLbl = new JLabel("$" + nf.format(userHighScore));
        addComponent(highScoreValueLbl, f2, 1, 3, 1, 2);
        
        topScoreLbl = new JLabel("All-Time High Score: ");
        addComponent(topScoreLbl, f, 0, 4, 1, 2);
        
        topScoreValueLbl = new JLabel("$" + nf.format(allTimeHighScore));
        addComponent(topScoreValueLbl, f2, 1, 4, 1, 2);
        
        playAgainBtn = new JButton("Play Again");
        playAgainBtn.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        addComponent(playAgainBtn, f3, 0, 5, 1, 2);
        
        quitBtn = new JButton("Quit Game");
        quitBtn.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        addComponent(quitBtn, f3, 1, 5, 1, 2);
    }
    
    public void dealEndScreen()
    {
        dealNoDealLbl.setText("YOU TOOK A DEAL WORTH:");
        bankedOffer.setForeground(new Color(0,153,51));
        if(this.bankOffer > this.userCaseValue)
        {
            messageLbl.setText("Congrats! You made the right call...");
            bankedOffer.setForeground(new Color(0,153,51));
        }
        else
        {
            messageLbl.setText("You should've risked it!");
            bankedOffer.setForeground(new Color(230,0,0));
            caseValueLbl.setForeground(new Color(0,153,51));
        }
    }
    public void noDealEndScreen()
    {
        dealNoDealLbl.setText("YOU DECLINED AN OFFER OF:");
        if(this.bankOffer < this.userCaseValue)
        {
            messageLbl.setText("Congrats! You made the right call...");
            bankedOffer.setForeground(Color.BLACK);
            caseValueLbl.setForeground(new Color(0,153,51));
        }
        else
        {
            messageLbl.setText("Should've taken that deal!");
            bankedOffer.setForeground(new Color(0,153,51));
            caseValueLbl.setForeground(new Color(230,0,0));
        }
    }
}