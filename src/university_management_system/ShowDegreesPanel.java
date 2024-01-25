package university_management_system;

import domain.StudentData;
import domain.UserData;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class ShowDegreesPanel extends JPanel implements ActionListener {

    UserData loggedUser;
    JTable degreesTable;
    JScrollPane scrollPane;
    String tableContent[][];
    ArrayList<StudentData> list;
    String[] columns = {" ID ", " Full Name ", "Marks Sum", " Address ", "Departement"};
    JButton print;
    JButton refresh;
    public ShowDegreesPanel(UserData loggedUser) {
        this.loggedUser = loggedUser;
        setLayout(null);
        list = databases.StudentDatabase.getStudentsAndDegrees(loggedUser.getUserDepartement());
        showFrame();
    }

    public void showFrame() {
        setLayout(null);
        setBackground(new Color(238, 184, 54));

        //intialize the table with its scroll pane 
        tableContent = new String[list.size()][5];
        for (int i = 0; i < list.size(); i++) {
            tableContent[i][0] = "" + list.get(i).getId();
            tableContent[i][1] = "" + list.get(i).getFirsName();
            tableContent[i][2] = "" + list.get(i).getLastName();
            tableContent[i][3] = "" + list.get(i).getAddress();
            tableContent[i][4]=""+ list.get(i).getDepartment();

        }
        degreesTable = new JTable(tableContent, columns);
        degreesTable.setBackground(Color.DARK_GRAY);
        degreesTable.setForeground(Color.WHITE);
        try {
            degreesTable.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

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
            });
        } catch (Exception E) {
            E.printStackTrace();
        }

        scrollPane = new JScrollPane(degreesTable);
        scrollPane.setBounds(0, 30, 640, 335);
        scrollPane.setBackground(Color.DARK_GRAY);
        scrollPane.setForeground(Color.WHITE);
        add(scrollPane);
        
        //intialize the print button 
        print=new JButton("Print Data");
        print.setBounds(250,0,100,30);
        print.setFocusPainted(false);
        print.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        print.setBackground(Color.DARK_GRAY);
        print.setForeground(Color.WHITE);
        print.addActionListener(this);
        add(print);
        //intialize the refresh button 
        refresh=new JButton("Refresh Table");
        refresh.setBounds(10,0,100,30);
        refresh.setFocusPainted(false);
        refresh.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        refresh.setBackground(Color.DARK_GRAY);
        refresh.setForeground(Color.WHITE);
        refresh.addActionListener(this);
        add(refresh);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==print){
        MessageFormat messageFormats=new MessageFormat("Students Degrees");
        MessageFormat messageFormatp=new MessageFormat("Page {1}");
        try{
        degreesTable.print(JTable.PrintMode.NORMAL, messageFormats, messageFormatp);
        }catch(PrinterException ex){System.out.println(ex.getMessage());}
    }else if(e.getSource()==refresh){
     list = databases.StudentDatabase.getStudentsAndDegrees(loggedUser.getUserDepartement());
    tableContent = new String[list.size()][5];
    for (int i = 0; i < list.size(); i++) {
            tableContent[i][0] = "" + list.get(i).getId();
            tableContent[i][1] = "" + list.get(i).getFirsName();
            tableContent[i][2] = "" + list.get(i).getLastName();
            tableContent[i][3] = "" + list.get(i).getAddress();
            tableContent[i][4]=""+ list.get(i).getDepartment();

        }
      //  degreesTable = new JTable(tableContent, columns);
         
     // Create a table model from the list of students and degrees
  TableModel tableModel = new DefaultTableModel(tableContent, columns);

  // Update the table model with the new data
    degreesTable.setModel(tableModel);
    degreesTable.revalidate();
    degreesTable.repaint();
 
    }
            
    
    }
    
    public void refreshTable(){
    list = databases.StudentDatabase.getStudentsAndDegrees(loggedUser.getUserDepartement());
    tableContent = new String[list.size()][5];
    for (int i = 0; i < list.size(); i++) {
            tableContent[i][0] = "" + list.get(i).getId();
            tableContent[i][1] = "" + list.get(i).getFirsName();
            tableContent[i][2] = "" + list.get(i).getLastName();
            tableContent[i][3] = "" + list.get(i).getAddress();
            tableContent[i][4]=""+ list.get(i).getDepartment();

        }
      //  degreesTable = new JTable(tableContent, columns);
         
     // Create a table model from the list of students and degrees
  TableModel tableModel = new DefaultTableModel(tableContent, columns);

  // Update the table model with the new data
    degreesTable.setModel(tableModel);
    degreesTable.revalidate();
    degreesTable.repaint();
    }
}
