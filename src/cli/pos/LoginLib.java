package cli.pos;

import dataconnection.DBConnection;
import static dataconnection.DBConnection.conn;
import java.sql.ResultSet;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.ImageIcon;


public class LoginLib extends DBConnection {

    private static int u_id;
    private static String username;
    private static String password;
    private static String fullname;
    private static String account_type;
    private static Blob picture;
    private static String date;
    private static String log_in_time;
    private static String log_out_time;
    private static String log_userId;
    private static int u_type;
    private static int pos_number;
    private static String status;
    private static ImageIcon usericon = null;
    private static ImageIcon usericonThumbnail = null;
    private static Vector data;

    /**
     * @return the status
     */
    public static String getStatus() {
        return status;
    }

    /**
     * @param aStatus the status to set
     */
    public static void setStatus(String aStatus) {
        status = aStatus;
    }

    public ImageIcon getUsericon() {
        return usericon;
    }

    public void setUsericon(ImageIcon usericon) {
        this.usericon = usericon;
    }

    public static ImageIcon getUsericonThumbnail() {
        return usericonThumbnail;
    }

    public static void setUsericonThumbnail(ImageIcon aUsericonThumbnail) {
        usericonThumbnail = aUsericonThumbnail;
    }

    public static int getU_id() {
        return u_id;
    }

    public static void setU_id(int aU_id) {
        u_id = aU_id;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String aUsername) {
        username = aUsername;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String aPassword) {
        password = aPassword;
    }

    public static String getFullname() {
        return fullname;
    }

    public static void setFullname(String aFullname) {
        fullname = aFullname;
    }

    public static Blob getPicture() {
        return picture;
    }

    public static void setPicture(Blob aPicture) {
        picture = aPicture;
    }

    public static Vector getData() {
        return data;
    }

    public static void setData(Vector aData) {
        data = aData;
    }

    public static String getDate() {
        return date;
    }

    public static void setDate(String aDate) {
        date = aDate;
    }

    public static String getLog_in_time() {
        return log_in_time;
    }

    public static void setLog_in_time(String aLog_in_time) {
        log_in_time = aLog_in_time;
    }

    public static String getLog_out_time() {
        return log_out_time;
    }

    public static void setLog_out_time(String aLog_out_time) {
        log_out_time = aLog_out_time;
    }

    public static String getLog_userId() {
        return log_userId;
    }

    public static void setLog_userId(String aLog_userId) {
        log_userId = aLog_userId;
    }

    public static int getU_type() {
        return u_type;
    }

    public static void setU_type(int aU_type) {
        u_type = aU_type;
    }

    public static String getAccount_type() {
        return account_type;
    }

    public static void setAccount_type(String aAccount_type) {
        account_type = aAccount_type;
    }

    public static int getPos_number() {
        return pos_number;
    }

    public static void setPos_number(int aPos_number) {
        pos_number = aPos_number;
    }
    
    public void ThumbnailforUser() {
    int maxDim = 70;
    try {
      Image inImage = getUsericon().getImage();

      double scale = (double) maxDim / (double) inImage.getHeight(null);
      if (inImage.getWidth(null) > inImage.getHeight(null)) {
        scale = (double) maxDim / (double) inImage.getWidth(null);
      }

      int scaledW = (int) (scale * inImage.getWidth(null));
      int scaledH = (int) (scale * inImage.getHeight(null));

      BufferedImage outImage = new BufferedImage(scaledW, scaledH,
          BufferedImage.TYPE_INT_RGB);

      AffineTransform tx = new AffineTransform();

      if (scale < 1.0d) {
        tx.scale(scale, scale);
      }

      Graphics2D g2d = outImage.createGraphics();
      g2d.drawImage(inImage, tx, null);
      g2d.dispose();
      
            setUsericonThumbnail(new ImageIcon(outImage));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

    public void selectallUsers(){
        
        this.setData(new Vector());
        try{
            super.getConnectToDbHost();
            PreparedStatement statement = conn.prepareStatement("SELECT CASE WHEN status = 'Online' THEN 'Online' ELSE 'Offline' END AS Status, Username FROM user INNER JOIN account_type ON (user.U_type = account_type.U_type) ORDER BY Pos_number");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
            String statusl = rs.getString("Status");
            String usernamel = rs.getString("Username");
//                Blob picturel = rs.getBlob("Picture"); 
//                setUsericon(new ImageIcon(picturel.getBytes(1L, (int) picturel.length())));
//                ThumbnailforUser();
            //Process Data
            Vector rows = new Vector();
            rows.add(statusl);
            rows.addElement(usernamel);
//            rows.addElement(picturel);
            this.getData().add(rows);
            }
            rs.close();
            statement.close();
        } catch (SQLException sqle){
            }
        }

    public void selectUsernameANDPassword() {
        this.setData(new Vector());
        try {
            super.getConnectToDbHost();
            PreparedStatement statement = conn.prepareStatement("SELECT Username, Password, U_id, U_type, Picture FROM user WHERE Username = ? AND Password = ?;");
            statement.setString(1, getUsername().trim());
            statement.setString(2, getPassword().trim());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String usernamel = rs.getString("Username");
                String passwordl = rs.getString("Password");
                int u_idl = rs.getInt("U_id");
                int u_typel = rs.getInt("U_type");
                // Process data here
                Vector rows = new Vector();
                this.setUsername(usernamel);
                this.setPassword(passwordl);
                this.setU_type(u_typel);
                this.setU_id(u_idl);
                this.getData().add(rows);
            }
            System.out.println("Who login? = "+this.getUsername());
            rs.close();
            statement.close();

        } catch (SQLException sqle) {
        }
    }

    public void UpdateuserDomain() {
        try {
            super.getConnectToDbHost();
            PreparedStatement statement = conn.prepareStatement("UPDATE user SET U_type=? WHERE Fullname = ?");
            statement.setInt(1, getU_type());
            statement.setString(2, getFullname());
            statement.execute();
            statement.close();

        } catch (SQLException sqle) {
             sqle.printStackTrace();
        }
    }

    public void insertUserLoginTime() {
        try {
            super.getConnectToDbHost();
            PreparedStatement statement = conn.prepareStatement("INSERT INTO log SET U_id=?, Log_in_time=?, Date=?, System='Inventory'");
            statement.setInt(1, getU_id());
            statement.setString(2, getLog_in_time());
            statement.setString(3, getDate());
            statement.execute();
            System.out.println("Inserting Login-Date ="+this.getLog_in_time()+"\n"
                            +"Inserting Login-Time = "+this.getDate());
            statement.close();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
    
    public void UpdateUserStatus() {
        try {
            super.getConnectToDbHost();
            PreparedStatement statement = conn.prepareStatement("UPDATE user SET Status=? WHERE U_id = ?");
            statement.setString(1, getStatus());
            statement.setInt(2, getU_id());
            statement.execute();
            System.out.println("Update User status to Online");
            statement.close();

        } catch (SQLException sqle) {
             sqle.printStackTrace();
        }
    }


    public void insertUserLogoutTime() {
        try {
            super.getConnectToDbHost();
            PreparedStatement statement = conn.prepareStatement("UPDATE log SET Log_out_time=? WHERE U_id = ?");
            statement.setString(1, getLog_out_time());
            statement.setInt(2, getU_id());
            statement.execute();
            statement.close();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    public void UserLogs(){
        this.setData(new Vector());
        try{
            super.getConnectToDbHost();
            PreparedStatement statement = conn.prepareStatement("SELECT DISTINCT user.Fullname, log.Date, log.Log_in_time, log.Log_out_time, log.System FROM log INNER JOIN user ON(log.U_id = user.U_id)ORDER BY log.Date DESC");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
            String fullnamel = rs.getString("Fullname");
            String datel = rs.getString("Date");
            String log_in_timel = rs.getString("Log_in_time");
            String log_out_timel = rs.getString("Log_out_time");
            String systeml = rs.getString("System");
            //Process Data
            Vector rows = new Vector();
            rows.add(fullnamel);
            rows.add(datel);
            rows.add(log_in_timel);
            rows.add(log_out_timel);
            rows.add(systeml);

            this.getData().add(rows);
            }
            rs.close();
            statement.close();
        } catch (SQLException sqle){
            }
        }

    public void DeleteallLogs() {
        try {
            super.getConnectToDbHost();
            PreparedStatement statement = conn.prepareStatement("DELETE FROM log;");
            statement.execute();
            statement.close();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    public void displayUserInfo(){
        this.setData(new Vector());
        try{
            super.getConnectToDbHost();
            PreparedStatement statement = conn.prepareStatement("SELECT Picture, Pos_number, Username FROM user WHERE user.U_id = ?");
            statement.setInt(1, getU_id());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
            Blob picturel = rs.getBlob("Picture");
            int pos_numberl = rs.getInt("Pos_number");
            String usernamel= rs.getString("Username"); 
            //Process Data
            Vector rows = new Vector();
            this.setPicture(picturel);
            this.setPos_number(pos_numberl);
            this.setUsername(usernamel);
            this.getData().add(rows);
            }
            rs.close();
            statement.close();
        } catch (SQLException sqle){
            }
    }

}