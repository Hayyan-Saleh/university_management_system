package university_management_system;

import domain.UserData;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddStudentPanel extends JPanel implements ActionListener {

    UserData loggedUser;
    JLabel firstNameLabel, lastNameLabel, departmentLabel, addressLabel;
    JTextField firstNameTextField, lastNameTextField, departmentTextField, addressTextField;
    JButton addStudentButton;
    Font font = new Font(null, Font.BOLD, 18);

    JLabel loggedUserNamepre, loggedUserNamepos, loggedUserDepartmentpre, loggedUserDepartmentpos;

    public AddStudentPanel(UserData loggedUser) {
        this.loggedUser = loggedUser;
        setLayout(null);
        setBackground(new Color(238, 184, 54));
        //intialize the labels 
        firstNameLabel = new JLabel("First Name");
        lastNameLabel = new JLabel("Last Name");
        addressLabel = new JLabel("Address");
        departmentLabel = new JLabel("Department");

        firstNameLabel.setBounds(20, 20, 110, 30);
        firstNameLabel.setFont(font);
        lastNameLabel.setBounds(20, 60, 100, 30);
        lastNameLabel.setFont(font);
        addressLabel.setBounds(20, 100, 120, 30);
        addressLabel.setFont(font);
        departmentLabel.setBounds(20, 140, 120, 30);
        departmentLabel.setFont(font);

        loggedUserNamepre = new JLabel("User Name : ");
        loggedUserNamepos = new JLabel("DR." + loggedUser.getUserName());
        loggedUserDepartmentpre = new JLabel("User Departement : ");
        loggedUserDepartmentpos = new JLabel(loggedUser.getUserDepartement());
        loggedUserNamepre.setBounds(350, 20, 150, 30);
        loggedUserNamepos.setBounds(350, 50, 150, 30);
        loggedUserDepartmentpre.setBounds(350, 80, 150, 30);
        loggedUserDepartmentpos.setBounds(350, 110, 150, 30);
        loggedUserNamepre.setForeground(Color.DARK_GRAY);
        loggedUserNamepos.setFont(font);
        loggedUserDepartmentpre.setForeground(Color.DARK_GRAY);
        loggedUserDepartmentpos.setFont(font);
        add(loggedUserDepartmentpos);
        add(loggedUserDepartmentpre);
        add(loggedUserNamepos);
        add(loggedUserNamepre);

        add(firstNameLabel);
        add(lastNameLabel);
        add(addressLabel);
        add(departmentLabel);

        //intialize the textFields 
        firstNameTextField = new JTextField();
        lastNameTextField = new JTextField();
        addressTextField = new JTextField();
        departmentTextField = new JTextField();
        firstNameTextField.setBounds(140, 20, 150, 30);
        firstNameTextField.setBackground(new Color(230, 230, 230));
        lastNameTextField.setBounds(140, 60, 150, 30);
        lastNameTextField.setBackground(new Color(230, 230, 230));
        addressTextField.setBounds(140, 100, 150, 30);
        addressTextField.setBackground(new Color(230, 230, 230));
        departmentTextField.setBounds(140, 140, 150, 30);
        departmentTextField.setBackground(new Color(230, 230, 230));

        add(firstNameTextField);
        add(lastNameTextField);
        add(addressTextField);
        add(departmentTextField);

        //intialize the addSutdent button 
        addStudentButton = new JButton("Add Student");
        addStudentButton.setBounds(100, 200, 100, 40);
        addStudentButton.setFocusPainted(false);
        addStudentButton.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        addStudentButton.setBackground(Color.DARK_GRAY);
        addStudentButton.setForeground(Color.WHITE);
        addStudentButton.addActionListener(this);
        add(addStudentButton);
        //clock panel
        JLabel timeLabelpre = new JLabel("Current Time :");
        JLabel timeLabelpos = new JLabel("00:00:00");
        timeLabelpre.setBounds(350, 140, 150, 30);
        timeLabelpos.setBounds(350, 170, 250, 30);
        timeLabelpre.setForeground(Color.DARK_GRAY);
        timeLabelpos.setForeground(Color.darkGray);
        timeLabelpos.setFont(new Font("Arail",Font.BOLD,16));
        add(timeLabelpre);
        add(timeLabelpos);
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                timeLabelpos.setText(new Date().toString());
                    try {
                        Thread.currentThread().sleep(1);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(AddStudentPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        thread.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addStudentButton) {
            databases.StudentDatabase.InsertStudent(firstNameTextField.getText(), lastNameTextField.getText(), addressTextField.getText(), departmentTextField.getText());
            firstNameTextField.setText("");
            lastNameTextField.setText("");
            addressTextField.setText("");
            departmentTextField.setText("");
        }

    }

}
