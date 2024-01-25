package university_management_system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class SignUpWindow extends JFrame implements ActionListener, WindowListener {

    private static SignInWindow signInWindow;
    private ImagePanel panel = new ImagePanel();
    JLabel userLabel, passwordLabel, departementLabel;
    JTextField userNameTextField, userDepartementTextField;
    JPasswordField UserPassword;
    JButton signUpButton;
    Font font = new Font(null, Font.BOLD, 18);

    public SignUpWindow(SignInWindow n) {
        signInWindow = n;
    }

    public void showFrame() {
        panel.setBackground(new Color(238, 184, 54));
        //intialize user name and password and departement label
        userLabel = new JLabel("User Name ");
        passwordLabel = new JLabel("Password ");
        userLabel.setBounds(20, 20, 110, 30);
        userLabel.setFont(font);
        passwordLabel.setBounds(20, 60, 100, 30);
        passwordLabel.setFont(font);
        departementLabel = new JLabel("Departement");
        departementLabel.setBounds(20, 100, 120, 30);
        departementLabel.setFont(font);

        panel.add(userLabel);
        panel.add(passwordLabel);
        panel.add(departementLabel);

        //intialize username textfield and passwordfield
        userNameTextField = new JTextField();
        UserPassword = new JPasswordField();
        userDepartementTextField = new JTextField("   AI , PE or NE  only  ");
        userNameTextField.setBackground(new Color(230, 230, 230));
        userNameTextField.setBounds(140, 20, 150, 30);
        UserPassword.setBackground(new Color(230, 230, 230));
        UserPassword.setBounds(140, 60, 150, 30);
        userDepartementTextField.setBackground(new Color(230, 230, 230));
        userDepartementTextField.setBounds(140, 100, 150, 30);
        panel.add(userNameTextField);
        panel.add(UserPassword);
        panel.add(userDepartementTextField);

        userDepartementTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                userDepartementTextField.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
            }
        });

        //intialize sing up button  
        signUpButton = new JButton("Sign Up");
        signUpButton.setBounds(100, 170, 150, 40);
        signUpButton.setFocusPainted(false);
        signUpButton.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        signUpButton.setBackground(Color.DARK_GRAY);
        signUpButton.setForeground(Color.WHITE);
        signUpButton.addActionListener(this);
        panel.add(signUpButton);

        //create panel frame 
        setTitle("University_Management_System");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setBounds((Toolkit.getDefaultToolkit().getScreenSize().width - 640) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - 427) / 2, 640, 427);
        add(panel);
        setVisible(true);
        addWindowListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signUpButton) {
            String userName = userNameTextField.getText();
            String userPassword = UserPassword.getText();
            String userDepartment = userDepartementTextField.getText();

            if (userName != null && !userName.equals("") && userPassword != null && !userPassword.equals("") && userDepartment != null && !userDepartment.equals("")) {
                if (userDepartment.equalsIgnoreCase("ai") || userDepartment.equalsIgnoreCase("pe") || userDepartment.equalsIgnoreCase("ne")) {
                    String Authentication = JOptionPane.showInputDialog("Please give the Authentication Code ");
                    if (Authentication.equals("0000")) {
                        databases.UserDatabase.InsertUser(userName, userPassword, userDepartment);
                        JOptionPane.showMessageDialog(this, "You signed Up !\nHello Doctor "+userName);
                        dispose();
                        signInWindow.setVisible(true);

                    } else if (Authentication == null || Authentication.equals("")) {
                        JOptionPane.showMessageDialog(this, "       INCORRECT !         \nRE-INSERT THE AUTHENTICATION CODE PLEASE ....");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Please enter a valid Departement ! AI or PE or NE");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please fill all the Text Fields");
            }

        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        int res = JOptionPane.showConfirmDialog(this, "Do you Really want to go back to \n the sign in Window without signing up ?", "", JOptionPane.INFORMATION_MESSAGE);
        switch (res) {
            case JOptionPane.YES_OPTION:
                setVisible(false);
                signInWindow.setVisible(true);
                break;
            default:
                break;
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
}
