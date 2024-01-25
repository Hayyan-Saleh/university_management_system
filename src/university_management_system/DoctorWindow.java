 package university_management_system;

import domain.UserData;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

 
 
public class DoctorWindow extends JFrame {
    JTabbedPane tabbedPane;
    AddStudentPanel addStudentPanel;
    AddDegreesPanel addDegreesPanel;
    ShowDegreesPanel showDegreesPanel;
    UpdateStudentPanel updateStudentPanel;
    UpdateDegreePanel updateDegreePanel;
    UserData loggedUser;
    JButton backButton;
    public DoctorWindow(UserData loggedUser){
        this.loggedUser=loggedUser; 
        showFrame();
         
    }
    public void showFrame(){
    
    //intialize tabbedPane panels
        backButton=new JButton("Back to sign in window");
        backButton.setFocusPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setBorder(BorderFactory.createEmptyBorder());
        backButton.addActionListener((e)->{
            new SignInWindow().showFrame();
            dispose();
            });
        addStudentPanel=new AddStudentPanel(loggedUser);
        addDegreesPanel=new AddDegreesPanel(loggedUser);
        showDegreesPanel=new ShowDegreesPanel(loggedUser);
        updateStudentPanel=new UpdateStudentPanel(loggedUser);
        updateDegreePanel=new UpdateDegreePanel(loggedUser);
       
    //create Doctor window frame
        setTitle("University_Management_System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setBounds((Toolkit.getDefaultToolkit().getScreenSize().width - 640) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - 427) / 2, 640, 427);
        add(addStudentPanel);
        setVisible(true);
        
    // intialize the tabbedPane    
        tabbedPane=new JTabbedPane();
        tabbedPane.setBackground(new Color(238, 184, 54));
        tabbedPane.addTab("Add Student",addStudentPanel);
        tabbedPane.addTab("Add Degrees", addDegreesPanel);
        tabbedPane.addTab("Show Degrees",showDegreesPanel);
        tabbedPane.addTab("Update Student", updateStudentPanel);
        tabbedPane.addTab("Update Mark",updateDegreePanel);
        tabbedPane.addTab("",null);
        tabbedPane.setTabComponentAt(tabbedPane.getTabCount()-1, backButton);
        add(tabbedPane);
    
    }
    public void refreshTabbedPane(){
        tabbedPane.removeAll();
        addDegreesPanel.refreshTable();
        showDegreesPanel.refreshTable();
        updateDegreePanel.refreshTable();
        updateStudentPanel.refreshTable();
        tabbedPane.addTab("Add Student",addStudentPanel);
        tabbedPane.addTab("Add Degrees", addDegreesPanel);
        tabbedPane.addTab("Show Degrees",showDegreesPanel);
        tabbedPane.addTab("Update Student", updateStudentPanel);
        tabbedPane.addTab("Update Mark",updateDegreePanel);
        tabbedPane.addTab("",null);
        tabbedPane.setTabComponentAt(tabbedPane.getTabCount()-1, backButton);
    }
    
}
