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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

public class AddDegreesPanel extends JPanel implements ActionListener{
    UserData loggedUser;
    JLabel m1,m2,m3,m4,m5,m6;
    JTextField t1,t2,t3,t4,t5,t6;
    JButton addMarkButton;
    JTable degreesTable;
    TableModel tableModel;
    JScrollPane scrollPane;
    String tableContent[][];
    String tableHeader[] = {" ID ", " First Name ", " Last Name "};
    ArrayList<StudentData> list;
    int rowIndex=-1,rowId;
    Font font=new Font(null,Font.BOLD,20);
    public AddDegreesPanel(UserData loggedUser) {
        this.loggedUser=loggedUser;
        list = databases.StudentDatabase.getStudents(loggedUser.getUserDepartement());
        showFrame();
    }

    public void showFrame() {
        setLayout(null);
        setBackground(new Color(238, 184, 54));
        //intialize the material lebels and the text fields
        m1=new JLabel("First Material");
        m2=new JLabel("Second Material");
        m3=new JLabel("Third Material");
        m4=new JLabel("Forth Material");
        m5=new JLabel("Fifth Material");
        m6=new JLabel("Sixth Material");
        
        m1.setBounds(410,10,200,20);
        m1.setFont(font);
        m2.setBounds(410,60,200,20);
        m2.setFont(font);
        m3.setBounds(410,110,200,20);
        m3.setFont(font);
        m4.setBounds(410,160,200,20);
        m4.setFont(font);
        m5.setBounds(410,210,200,20);
        m5.setFont(font);
        m6.setBounds(410,260,200,20);
        m6.setFont(font);
        add(m1);
        add(m2);
        add(m3);
        add(m4);
        add(m5);
        add(m6);
        t1=new JTextField();
        t1.setBackground(new Color(230, 230, 230));
        t1.setBounds(410, 35, 200, 20);
        t2=new JTextField();
        t2.setBackground(new Color(230, 230, 230));
        t2.setBounds(410, 85, 200, 20);
        t3=new JTextField();
        t3.setBackground(new Color(230, 230, 230));
        t3.setBounds(410, 135, 200, 20);
        t4=new JTextField();
        t4.setBackground(new Color(230, 230, 230));
        t4.setBounds(410, 185, 200, 20);
        t5=new JTextField();
        t5.setBackground(new Color(230, 230, 230));
        t5.setBounds(410, 235, 200, 20);
        t6=new JTextField();
        t6.setBackground(new Color(230, 230, 230));
        t6.setBounds(410, 285, 200, 20);
        
        add(t1);
        add(t2);
        add(t3);
        add(t4);
        add(t5);
        add(t6);
        
        addMarkButton=new JButton("Add Marks");
        addMarkButton.setBounds(430, 315, 150, 30);
        addMarkButton.setFocusPainted(false);
        addMarkButton.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        addMarkButton.setBackground(Color.DARK_GRAY);
        addMarkButton.setForeground(Color.WHITE);
        addMarkButton.addActionListener(this);
        add(addMarkButton);
        
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
       databases.DegreeDatabase.insertMark(rowId, Integer.parseInt(t1.getText()) ,Integer.parseInt( t2.getText()), Integer.parseInt(t3.getText()),Integer.parseInt( t4.getText()),Integer.parseInt( t5.getText()),Integer.parseInt( t6.getText()));
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
