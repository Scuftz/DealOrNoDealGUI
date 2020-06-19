package PanelPackage;
import SpecialClassPackage.RestrictInputLength;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * PDC Assignment 2
 * This is the LoginPanel Class
 * @author Shivneel Singh (18021394)
 * @since 11/06/2020
 */
public class LoginPanel extends CurvedJPanel
{
    /**
     * Variables
     */
    private JLabel loginLbl, usernameLbl, passwordLbl;
    private JTextField usernameTxt;
    private JPasswordField passwordTxt;
    public JButton loginBtn;
    private Font fontOne, fontTwo;
    
    /**
     * Constructor
     */
    public LoginPanel()
    {
        fontOne = new Font("Arial", Font.PLAIN, 10);
        fontTwo = new Font("Arial", Font.PLAIN, 20);
        
        setLayout(null);
        setBounds(400,150,400,285);
        setBackground(new Color(255,255,255, 245));
        setOpaque(false);
        
        createLoginLbl();
        add(loginLbl);
        
        createUsernameLbl();
        add(usernameLbl);
        
        createUsernameTxt();
        add(usernameTxt);
        
        createPasswordLbl();
        add(passwordLbl);
        
        createPasswordField();
        add(passwordTxt);
        
        createLoginButton();
        add(loginBtn);
    }
    
    /**
     * This method will set an ActionListener (the Controller) for the login button
     * @param controller 
     */
    public void setButtonListener(ActionListener controller)
    {
        loginBtn.addActionListener(controller);
    }
    
    /**
     * This method will set the password field blank
     */
    public void setPasswordBlank()
    {
        passwordTxt.setText("");
    }
    
    /**
     * This method will get the password entered by the player
     * @return   The player's password that they entered
     */
    public char[] getPassword()
    {
        return passwordTxt.getPassword();
    }
    
    /**
     * This method will return the username entered by the player
     * @return   The player's username that they entered
     */
    public String getUsername()
    {
        return usernameTxt.getText();
    }
    
    /**
     * This method will create the login label
     */
    public void createLoginLbl()
    {
        loginLbl = new JLabel("Login");
        loginLbl.setLocation(175, 10);
        loginLbl.setFont(new Font("Microsoft YaHei UI Light", Font.PLAIN, 20));
        loginLbl.setForeground(Color.DARK_GRAY);
        loginLbl.setSize(80, 30);
    }
    
    /**
     * This method will create the username label
     */
    public void createUsernameLbl()
    {
        usernameLbl = new JLabel("Username");
        usernameLbl.setLocation(72, 60);
        usernameLbl.setFont(fontOne);
        usernameLbl.setForeground(Color.DARK_GRAY);
        usernameLbl.setSize(50, 10);
    }
    
    /**
     * This method will create the text field for the player's username
     */
    public void createUsernameTxt()
    {
        usernameTxt = new JTextField();
        usernameTxt.setDocument(new RestrictInputLength(20));
        usernameTxt.setFont(fontTwo);
        usernameTxt.setLocation(72, 75);
        usernameTxt.setSize(255, 30);
        usernameTxt.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
    }
        
    /**
     * This method will create the password label
     */
    public void createPasswordLbl()
    {
        passwordLbl = new JLabel("Password");
        passwordLbl.setLocation(72, 125);
        passwordLbl.setFont(fontOne);
        passwordLbl.setForeground(Color.DARK_GRAY);
        passwordLbl.setSize(50, 10);
    }
        
    /**
     * This method will create the text field for the player's password
     */
    public void createPasswordField()
    {
        passwordTxt = new JPasswordField();
        passwordTxt.setEchoChar('*');
        passwordTxt.setDocument(new RestrictInputLength(20));
        passwordTxt.setFont(fontTwo);
        passwordTxt.setLocation(72, 140);
        passwordTxt.setSize(255, 30);
        passwordTxt.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
    }
     
    /**
     * This method will create the login button 
     */
    public void createLoginButton()
    {
        loginBtn = new JButton("LOGIN");
        loginBtn.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 15));
        loginBtn.setLocation(125, 210);
        loginBtn.setSize(150, 35);
        loginBtn.setBackground(Color.YELLOW);
        loginBtn.setBorder(BorderFactory.createRaisedBevelBorder());
    }
}