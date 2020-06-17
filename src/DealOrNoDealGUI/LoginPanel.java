/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DealOrNoDealGUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author shivn
 */
public class LoginPanel extends CurvedJPanel
{
    private JLabel loginLbl, usernameLbl, passwordLbl;
    private JTextField usernameTxt;
    private JPasswordField passwordTxt;
    private JButton loginBtn;
    private Font fontOne, fontTwo;
    
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
    
    public void setController(ActionListener controller)
    {
        loginBtn.addActionListener(controller);
    }
    
    public char[] getPassword()
    {
        return passwordTxt.getPassword();
    }
    public String getUsername()
    {
        return usernameTxt.getText();
    }
    
    public void createLoginLbl()
    {
        loginLbl = new JLabel("Login");
        loginLbl.setLocation(175, 10);
        loginLbl.setFont(new Font("Microsoft YaHei UI Light", Font.PLAIN, 20));
        loginLbl.setForeground(Color.DARK_GRAY);
        loginLbl.setSize(80, 30);
    }
    
    public void createUsernameLbl()
    {
        usernameLbl = new JLabel("Username");
        usernameLbl.setLocation(72, 60);
        usernameLbl.setFont(fontOne);
        usernameLbl.setForeground(Color.DARK_GRAY);
        usernameLbl.setSize(50, 10);
    }
    
    public void createUsernameTxt()
    {
        usernameTxt = new JTextField();
        usernameTxt.setDocument(new RestrictInputLength(20));
        usernameTxt.setFont(fontTwo);
        usernameTxt.setLocation(72, 75);
        usernameTxt.setSize(255, 30);
        usernameTxt.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
    }
        
    public void createPasswordLbl()
    {
        passwordLbl = new JLabel("Password");
        passwordLbl.setLocation(72, 125);
        passwordLbl.setFont(fontOne);
        passwordLbl.setForeground(Color.DARK_GRAY);
        passwordLbl.setSize(50, 10);
    }
        
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
