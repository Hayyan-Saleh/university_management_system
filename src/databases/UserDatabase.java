package databases;

import domain.UserData;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class UserDatabase {

    public static Connection connect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/uni_database", "root", "0000");
    }

    public static void InsertUser(String userName, String userPassword, String userDepartement) {
        try (Connection connection = connect(); PreparedStatement ps = connection.prepareStatement("INSERT INTO user VALUES (?,?,?)");) {
            ps.setString(1, userName);
            ps.setString(2, userPassword);
            ps.setString(3, userDepartement);
            if (ps.execute()) {
                JOptionPane.showMessageDialog(null, "INSERTION DONE !");
            }
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "There is a User Database Error", "dataBaseError", JOptionPane.ERROR_MESSAGE);

        }
    }

    public static ArrayList<UserData> getUsers() {
        ArrayList<UserData> list = new ArrayList<UserData>();
        try (
            Connection connection = connect();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM user;");){
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new UserData(rs.getString("user_name"), rs.getString("user_password"), rs.getString("department")));
            }
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "THERE IS A GET FROM DATAVBASE ERROR");
        }

        return list;
    }

    public static UserData getUserByName(String userName) {
        try (
            Connection connection = connect();
            PreparedStatement pr = connection.prepareStatement("Select * From user where user_name=? ;");){
            pr.setString(1, userName);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                UserData loggedUser = new UserData(rs.getString(1), rs.getString(2), rs.getString(3));
                return loggedUser;
            }
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "There is a User Database Error ! \n" + ex.getMessage(), "dataBaseError", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public static int checkUser(String user, String pass) {
        ArrayList<UserData> list = getUsers();
        int x = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUserName().equalsIgnoreCase(user)) {
                if (list.get(i).getUserPassword().equals(pass)) {
                    x = 1;
                    break;
                } else {
                    x = 2;
                    break;
                }
            }
        }
        return x;
    }

}
