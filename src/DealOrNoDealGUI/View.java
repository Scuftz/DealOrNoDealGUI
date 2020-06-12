package DealOrNoDealGUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;
import java.util.List;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.SwingConstants;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;

public class View extends JFrame implements Observer
{
    /**
     * Variables
     */
    private JPanel loginPanel;
    private JPanel casePanel;
    private JPanel leftMoneyPanel;
    private JPanel rightMoneyPanel;
    private JPanel caseRemainingPanel;
    private JLabel caseRemainingLbl, caseRemainingNumberLbl;
    private ArrayList<JLabel> caseValuesLbl = new ArrayList<>();
    
    /**
     * Constructor
     */
    public View()
    {
        this.getContentPane().setLayout(new BorderLayout());
        this.setSize(1200, 609);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenDimension = tk.getScreenSize();
        Dimension frameDimension = this.getSize();
        this.setLocation((screenDimension.width-frameDimension.width)/2, (screenDimension.height-frameDimension.height)/2);
        
        loginPanel = new JPanel();
        casePanel = new JPanel();
        caseRemainingPanel = new JPanel();
        leftMoneyPanel = new JPanel();
        leftMoneyPanel.setLayout(new BoxLayout(leftMoneyPanel, BoxLayout.Y_AXIS));
        rightMoneyPanel = new JPanel();
        rightMoneyPanel.setLayout(new BoxLayout(rightMoneyPanel, BoxLayout.Y_AXIS));
        
        caseRemainingPanel.setBackground(Color.yellow);
        caseRemainingPanel.setPreferredSize(new Dimension(1200, 50));
        caseRemainingNumberLbl = new JLabel("6");
        caseRemainingLbl = new JLabel(" Cases Remaining");
        caseRemainingPanel.add(caseRemainingNumberLbl);
        caseRemainingPanel.add(caseRemainingLbl);
                
        getContentPane().add(caseRemainingPanel, BorderLayout.NORTH);
        getContentPane().add(leftMoneyPanel, BorderLayout.WEST);
        getContentPane().add(rightMoneyPanel, BorderLayout.EAST);
    }
    
    public void setUpCases()
    { 
        for(JLabel lbl : caseValuesLbl)
        {
            lbl.setHorizontalAlignment(SwingConstants.RIGHT);
            lbl.setBackground(Color.CYAN);
            lbl.setOpaque(true);
            lbl.setFont(new Font("Arial", Font.PLAIN, 20));
            int width = 250;
            int height = 40;
            lbl.setMinimumSize(new Dimension(width, height));
            lbl.setPreferredSize(new Dimension(width, height));
            lbl.setMaximumSize(new Dimension(width, height));
            lbl.setBorder(BorderFactory.createLineBorder(Color.blue, 1, true));
            if(caseValuesLbl.size() < 13)
            {
                lbl.setHorizontalAlignment(SwingConstants.RIGHT);
                leftMoneyPanel.add(lbl);
            }
            else
            {
                lbl.setHorizontalAlignment(SwingConstants.LEFT);
                rightMoneyPanel.add(lbl);
            }
        }
        System.out.println("hi");
    }
    public void setController(ActionListener controller)
    {
        
    }
    
    /**
     * 
     * @param o
     * @param arg 
     */
    @Override
    public void update(Observable obs, Object obj) {
        UpdateInfo update = (UpdateInfo) obj;
        
        System.out.println("we in update");
//        if(!update.loginFlag)
//        {
//        }
        if(update.dealAccepted) //end game
        {
            
        }
        else if(update.endOfRound)
        {
            if(update.totalAmountOfCases == 1) //check if its time to open (no more cases to open)
            {
                
            }
        }
        else if(!update.startGame)
        {
            update.startGame = true;
            for(Integer num : update.duplicateCaseValues)
            {
                caseValuesLbl.add(new JLabel("" + num));
            }
        }
        else
        {
            
        }
    }
    
}






//        for(Integer value : )
//        caseValuesLbl.add(new JLabel("1"));
//        caseValuesLbl.add(new JLabel("2"));
//        caseValuesLbl.add(new JLabel("5"));
//        caseValuesLbl.add(new JLabel("10"));
//        caseValuesLbl.add(new JLabel("20"));
//        caseValuesLbl.add(new JLabel("25"));
//        caseValuesLbl.add(new JLabel("50"));
//        caseValuesLbl.add(new JLabel("100"));
//        caseValuesLbl.add(new JLabel("150"));
//        caseValuesLbl.add(new JLabel("200"));
//        caseValuesLbl.add(new JLabel("250"));
//        caseValuesLbl.add(new JLabel("500"));
//        caseValuesLbl.add(new JLabel("750"));
//        caseValuesLbl.clear();
//        caseValuesLbl.add(new JLabel("1000"));
//        caseValuesLbl.add(new JLabel("2000"));
//        caseValuesLbl.add(new JLabel("3000"));
//        caseValuesLbl.add(new JLabel("4000"));
//        caseValuesLbl.add(new JLabel("5000"));
//        caseValuesLbl.add(new JLabel("10000"));
//        caseValuesLbl.add(new JLabel("15000"));
//        caseValuesLbl.add(new JLabel("20000"));
//        caseValuesLbl.add(new JLabel("2500"));
//        caseValuesLbl.add(new JLabel("50000"));
//        caseValuesLbl.add(new JLabel("75000"));
//        caseValuesLbl.add(new JLabel("100000"));
//        caseValuesLbl.add(new JLabel("200000"));
