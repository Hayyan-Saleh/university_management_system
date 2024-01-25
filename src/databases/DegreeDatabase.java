package databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

public class DegreeDatabase {

    public static Connection connect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/uni_database", "root", "0000");
    }

    public static void insertMark(int id, int m1, int m2, int m3, int m4, int m5, int m6) {
        try (
                Connection connection = connect(); PreparedStatement pr = connection.prepareStatement("INSERT INTO degrees VALUES (?,?,?,?,?,?,?,?);");) {
            pr.setInt(1, id);
            pr.setInt(2, m1);
            pr.setInt(3, m2);
            pr.setInt(4, m3);
            pr.setInt(5, m4);
            pr.setInt(6, m5);
            pr.setInt(7, m6);
            pr.setInt(8, m1 + m2 + m3 + m4 + m5 + m6);
            pr.execute();
            JOptionPane.showMessageDialog(null, "Mark added successfully ! ", "", JOptionPane.PLAIN_MESSAGE);
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "There is a DATABASE error ! \n" + ex.getMessage());
        }

    }

    public static String[] getDegreesById(int ID) {
        try (Connection connection = connect(); PreparedStatement pr = connection.prepareStatement("SELECT * FROM degrees WHERE id=?");) {
            pr.setInt(1, ID);
            pr.execute();
            ResultSet rs = pr.getResultSet();
               rs.next();
                return new String[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)};
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "There is no marks for \nthis student to update ");
        }
        return new String[]{"0", "0", "0", "0", "0", "0"};
    }

    public static void updateDegree(String[] degrees, int ID) {
        try (Connection connection = connect(); PreparedStatement pr = connection.prepareStatement("UPDATE degrees SET m1=? , m2=? , m3=? ,m4=? , m5=? , m6=? WHERE id=? ");) {
            pr.setString(1, degrees[0]);
            pr.setString(2, degrees[1]);
            pr.setString(3, degrees[2]);
            pr.setString(4, degrees[3]);
            pr.setString(5, degrees[4]);
            pr.setString(6, degrees[5]);
            pr.setInt(7, ID);
            pr.execute();
            JOptionPane.showMessageDialog(null, "Update done successfully !");
        } catch (SQLException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "There is a database error ! \n" + ex.getMessage());
        }
    }
}
