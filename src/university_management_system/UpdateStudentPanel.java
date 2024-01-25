package university_management_system;

import domain.StudentData;
import domain.UserData;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;


public class UpdateStudentPanel extends JPanel implements ActionListener{
    UserData loggedUser;
    JLabel firstNameLabel,lastNameLabel,addressLabel,departmentLabel;
    JTextField firstNameTextField,lastNameTextField,addressTextField,departmentTextField;
    JButton updateSutdentButton;
    JTable degreesTable;
    TableModel tableModel;
    JScrollPane scrollPane;
    String tableContent[][];
    String tableHeader[] = {" ID ", " First Name ", " Last Name "};
    ArrayList<StudentData> list;
    int rowIndex=-1,rowId;
    Font font=new Font(null,Font.BOLD,20);
    public UpdateStudentPanel(UserData loggedUser) {
        this.loggedUser=loggedUser;
        list = databases.StudentDatabase.getStudents(loggedUser.getUserDepartement());
        showFrame();
    }

    public void showFrame() {
        setLayout(null);
        setBackground(new Color(238, 184, 54));
        //intialize the update lebels and the text fields with the update button
        
        firstNameLabel=new JLabel("First Name ");
        lastNameLabel=new JLabel("Last Name");
        addressLabel=new JLabel("Address ");
        departmentLabel=new JLabel("Department ");
        firstNameLabel.setFont(font);
        lastNameLabel.setFont(font);
        addressLabel.setFont(font);
        departmentLabel.setFont(font);
        firstNameLabel.setBounds(410,10,150,30);
        lastNameLabel.setBounds(410,80,150,30);
        addressLabel.setBounds(410,150,150,30);
        departmentLabel.setBounds(410,220,150,30);
        add(firstNameLabel);
        add(lastNameLabel);
        add(addressLabel);
        add(departmentLabel);
        
        firstNameTextField=new JTextField("");
        lastNameTextField=new JTextField("");
        addressTextField=new JTextField("");
        departmentTextField=new JTextField("");
        firstNameTextField.setBounds(410,45,200,25);
        lastNameTextField.setBounds(410,115,200,25);
        addressTextField.setBounds(410,185,200,25);
        departmentTextField.setBounds(410,255,200,25);
        firstNameTextField.setBackground(new Color(230, 230, 230));
                lastNameTextField.setBackground(new Color(230, 230, 230));
                addressTextField.setBackground(new Color(230, 230, 230));
                departmentTextField.setBackground(new Color(230, 230, 230));
        add(firstNameTextField);
        add(lastNameTextField);
        add(addressTextField);
        add(departmentTextField);
        
        
        
        updateSutdentButton=new JButton("Update Student");
        updateSutdentButton.setBounds(435, 290, 150, 30);
        updateSutdentButton.setFocusPainted(false);
        updateSutdentButton.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        updateSutdentButton.setBackground(Color.DARK_GRAY);
        updateSutdentButton.setForeground(Color.WHITE);
        updateSutdentButton.addActionListener(this);
        add(updateSutdentButton);
        
        //intialize the table with its scroll pane 
        tableContent = new String[list.size()][3];
        for (int i = 0; i < list.size(); i++) {
            tableContent[i][0] = "" + list.get(i).getId();
            tableContent[i][1] = "" + list.get(i).getFirsName();
            tableContent[i][2] = "" + list.get(i).getLastName();
        }
        degreesTable = new JTable(tableContent, tableHeader);
        degreesTable.setBackground(Color.DARK_GRAY);
        degreesTable.setForeground(Color.WHITE);
        tableModel=degreesTable.getModel();
        //configure the dimentions of the table with the orientation
        ((DefaultTableCellRenderer) degreesTable.getTableHeader().getDefaultRenderer()).setHorizontalAlignment((int) JLabel.CENTER_ALIGNMENT);
        DefaultTableCellRenderer d = new DefaultTableCellRenderer();
        d.setHorizontalAlignment(JLabel.CENTER);
        degreesTable.getColumnModel().getColumn(0).setCellRenderer(d);
        for (int i = 0; i < degreesTable.getColumnCount(); i++) {
            degreesTable.getColumnModel().getColumn(i).setCellRenderer(d);
        }
        degreesTable.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        try{degreesTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
               
                rowIndex=degreesTable.getSelectedRow();
                rowId=Integer.parseInt((String)tableModel.getValueAt(rowIndex, 0)); 
                StudentData selectedStudent=list.get(rowIndex);
                firstNameTextField.setText(selectedStudent.getFirsName());
                lastNameTextField.setText(selectedStudent.getLastName());
                addressTextField.setText(selectedStudent.getAddress());
                departmentTextField.setText(selectedStudent.getDepartment());
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {
                degreesTable.setBackground(new Color(238, 184, 54));
                degreesTable.setForeground(Color.BLACK);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                degreesTable.setBackground(Color.DARK_GRAY);
                degreesTable.setForeground(Color.WHITE);
            }
        });}catch(Exception E){E.printStackTrace();}

        scrollPane = new JScrollPane(degreesTable);
        scrollPane.setBounds(0, 0, 400, 365);
        scrollPane.setBackground(Color.DARK_GRAY);
        scrollPane.setForeground(Color.WHITE);
        add(scrollPane);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    String firstName=firstNameTextField.getText();
    String lastName=lastNameTextField.getText();
    String address=addressTextField.getText();
    String department=departmentTextField.getText();
    if(firstName!=null && !firstName.equals("")&&address!=null && !address.equals("") &&lastName!=null && !lastName.equals("") && department!=null && !department.equals("")){
    StudentData studentData=new StudentData(rowId,firstName,lastName,address,department);
    databases.StudentDatabase.updateStudent(studentData);
    }else
        JOptionPane.showMessageDialog(this, "Please fill all the Text fields");
       
    }
    public void refreshTable(){
    list = databases.StudentDatabase.getStudents(loggedUser.getUserDepartement());
    tableContent = new String[list.size()][3];
    for (int i = 0; i < list.size(); i++) {
            tableContent[i][0] = "" + list.get(i).getId();
            tableContent[i][1] = "" + list.get(i).getFirsName();
            tableContent[i][2] = "" + list.get(i).getLastName();
        }
        degreesTable = new JTable(tableContent, tableHeader);
        degreesTable.setBackground(Color.DARK_GRAY);
        degreesTable.setForeground(Color.WHITE);
        tableModel=degreesTable.getModel();
        //configure the dimentions of the table with the orientation
        ((DefaultTableCellRenderer) degreesTable.getTableHeader().getDefaultRenderer()).setHorizontalAlignment((int) JLabel.CENTER_ALIGNMENT);
        DefaultTableCellRenderer d = new DefaultTableCellRenderer();
        d.setHorizontalAlignment(JLabel.CENTER);
        degreesTable.getColumnModel().getColumn(0).setCellRenderer(d);
        for (int i = 0; i < degreesTable.getColumnCount(); i++) {
            degreesTable.getColumnModel().getColumn(i).setCellRenderer(d);
        }
        degreesTable.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        degreesTable.revalidate();
        degreesTable.repaint();    
    }
}
