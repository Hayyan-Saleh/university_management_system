package databases;


 
import domain.StudentData;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class StudentDatabase {
    public static Connection connect() throws ClassNotFoundException, SQLException{
    Class.forName("com.mysql.cj.jdbc.Driver");
    return DriverManager.getConnection("jdbc:mysql://localhost:3306/uni_database", "root", "0000");
    }
    public static void InsertStudent(String studentFirstName,String studentLastName,String studentAdress,String studentDepartment){
    try (
            Connection connection = connect();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO student VALUES (null,?,?,?,?)");){
            ps.setString(1, studentFirstName);
            ps.setString(2, studentLastName);
            ps.setString(3, studentAdress);
            ps.setString(4,studentDepartment);
            ps.execute();
            JOptionPane.showMessageDialog(null, studentFirstName+" "+studentLastName+" has been added Successfully !");
        } catch (SQLException | ClassNotFoundException e) {
             JOptionPane.showMessageDialog(null, "There is a Student Database Error!\n"+e.getStackTrace(), "dataBaseError", JOptionPane.ERROR_MESSAGE);
           
        }
    }
     public static ArrayList<StudentData> getStudents(String userDepartment) {
        ArrayList<StudentData> list = new ArrayList();
        try (
            Connection connection = connect();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM student;");){
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if(userDepartment.equalsIgnoreCase(rs.getString("department")))
                list.add(new StudentData(rs.getInt("id"),rs.getString("first_name"), rs.getString("last_name"), rs.getString("address"),rs.getString("department")));
            }
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "THERE IS A GET FROM DATAVBASE ERROR!\n"+ e.getStackTrace());
        }

        return list;
    }
      public static ArrayList<StudentData> getStudentsAndDegrees(String userDepartment) {
        ArrayList<StudentData> list = new ArrayList();
        try (
            Connection connection = connect();
            PreparedStatement ps = connection.prepareStatement("""
                                                               SELECT  student.id , student.first_name,student.last_name,student.address,degrees.sum,student.department
                                                               FROM student 
                                                               JOIN degrees ON student.id = degrees.id;""");){
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if(userDepartment.equalsIgnoreCase(rs.getString("department")))
                list.add(new StudentData(rs.getInt("id"),rs.getString("first_name")+" "+rs.getString("last_name"),rs.getString("address") , rs.getString("sum")+"",rs.getString("department")));
            }
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "THERE IS A GET FROM DATAVBASE ERROR!\n"+ e.getStackTrace());
        }

        return list;
    }
    public static void updateStudent(StudentData userData ){
       try(Connection connection=connect();
           PreparedStatement pr=connection.prepareStatement("UPDATE student SET first_name=? , last_name=? , address=? ,department=? WHERE id=? ");){
           pr.setString(1, userData.getFirsName());
           pr.setString(2, userData.getLastName());
           pr.setString(3, userData.getAddress());
           pr.setString(4,userData.getDepartment());
           pr.setInt(5,userData.getId());
           pr.execute();
           JOptionPane.showMessageDialog(null, "Update done successfully !");
       }catch(SQLException | ClassNotFoundException ex){
                          JOptionPane.showMessageDialog(null, "There is a database error ! \n"+ex.getMessage());
               } 
          }
  
    }

