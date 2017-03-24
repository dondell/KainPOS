package cli.login;

import dataconnection.DBConnection;
import static dataconnection.DBConnection.conn;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class LoginLib extends DBConnection {

    private static int u_id;
    private static String username;
    private static String password;
    private static String fullname;
    private static String account_type;
    
    public boolean login(String username, String password) {
        boolean bRet = false;
        try {
            super.getConnectToDbHost();
            PreparedStatement statement = conn.prepareStatement(
                    "SELECT * FROM kainpos.systemusers as su\n"
                    + "inner join systemusertype sut on sut.idSystemUserType = su.UserTypeId\n"
                    + "where su.username = '"+username+"' and su.password = '"+password+"';");
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                String dataUsername = rs.getString("username");
                String dataPassword = rs.getString("password");
                System.out.println("Username " + dataUsername + " Password " + dataPassword);
                //Process Data
                bRet = true;
            }
            rs.close();
            statement.close();
        } catch (SQLException sqle) {
            bRet = false;
        }
        return bRet;
    }

    public void updateUser(int userId, String username, String password, int userTypeId) {
        try {
            super.getConnectToDbHost();
            PreparedStatement statement = conn.prepareStatement(
                    "UPDATE `kainpos`.`systemusers` "
                    + "SET\n"
                    + "`Username` = ?, "
                    + "`Password` = ?, "
                    + "`UserTypeId` = ? "
                    + "WHERE UserId = ?;");
            statement.setString(1, username);
            statement.setString(1, password);
            statement.setInt(3, userTypeId);
            statement.setInt(4, userId);
            statement.execute();
            statement.close();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    public void deleteUser(int userId) {
        try {
            super.getConnectToDbHost();
            PreparedStatement statement = conn.prepareStatement("DELETE FROM systemusers Where userid = " + userId + ";");
            statement.execute();
            statement.close();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        //Test
        LoginLib l = new LoginLib();
        if(l.login("admin", "admin")) {
            System.out.println("Login");
        }
    }
    
}




