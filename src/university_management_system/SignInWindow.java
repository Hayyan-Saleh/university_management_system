package university_management_system;

import java.awt.Toolkit;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import databases.UserDatabase;
import domain.UserData;

public class SignInWindow extends JFrame implements ActionListener {

    JLabel userLabel, passwordLabel, informationLabel;
    JTextField userNameTextField;
    JPasswordField UserPassword;
    JButton signInButton, signUpButton;
    Font font = new Font(null, Font.BOLD, 18);
    private ImagePanel panel = new ImagePanel();

    public SignInWindow() {
    showFrame();
    }

    public void showFrame() {
         panel.setBackground(new Color(238, 184, 54));
        //intialize user name and password label
        userLabel = new JLabel("User Name ");
        passwordLabel = new JLabel("Password ");
        userLabel.setBounds(20, 20, 110, 30);
        userLabel.setFont(font);
        passwordLabel.setBounds(20, 60, 100, 30);
        passwordLabel.setFont(font);
        panel.add(userLabel);
        panel.add(passwordLabel);
        
        //intialize username textfield and passwordfield
        userNameTextField = new JTextField();
        UserPassword = new JPasswordField();
        userNameTextField.setBackground(new Color(230, 230, 230));
        userNameTextField.setBounds(130, 20, 150, 30);
        UserPassword.setBackground(new Color(230, 230, 230));
        UserPassword.setBounds(130, 60, 150, 30);
        panel.add(userNameTextField);
        panel.add(UserPassword);

        //intialize sign in and sing up buttons with information Label
        signInButton = new JButton("Sign in ");
        signInButton.setBounds(120, 120, 100, 30);
        signInButton.setFocusPainted(false);
        signInButton.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        signInButton.setBackground(Color.DARK_GRAY);
        signInButton.setForeground(Color.WHITE);

        informationLabel = new JLabel("Don't have an account ? Sign Up !");
        informationLabel.setBounds(15, 180, 299, 30);
        informationLabel.setFont(font);

        signUpButton = new JButton("Sign Up");
        signUpButton.setBounds(120, 240, 100, 30);
        signUpButton.setFocusPainted(false);
        signUpButton.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        signUpButton.setBackground(Color.DARK_GRAY);
        signUpButton.setForeground(Color.WHITE);

        panel.add(signInButton);
        panel.add(informationLabel);
        panel.add(signUpButton);

        signInButton.addActionListener(this);
        signUpButton.addActionListener(this);

        //create panel frame 
        setTitle("University_Management_System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setBounds((Toolkit.getDefaultToolkit().getScreenSize().width - 640) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - 427) / 2, 640, 427);
        add(panel);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signInButton) {
            int checkState = UserDatabase.checkUser(userNameTextField.getText(), UserPassword.getText());
            switch (checkState) {
                case 1:
                   UserData loggedUser=databases.UserDatabase.getUserByName(userNameTextField.getText());
                   JOptionPane.showMessageDialog(this, "Hello Doctor " + loggedUser.getUserName(), "logged in", JOptionPane.PLAIN_MESSAGE);
                   new DoctorWindow(loggedUser);
                    dispose();
                    break;
                case 2:
                    JOptionPane.showMessageDialog(this, "The password is incorrect ! \nplease give a VALID password", "Password wrong", JOptionPane.ERROR_MESSAGE);
                    break;
                case 0:
                    JOptionPane.showMessageDialog(this, "The User Name is Wrong ! \nplease give a VALID User name ", "User Name wrong", JOptionPane.ERROR_MESSAGE);
                    break;
                default:
                    break;
            }
        }
        if (e.getSource() == signUpButton) {
            this.setVisible(false);
            SignUpWindow w = new SignUpWindow(this);
            w.showFrame();
        }
    }
}
