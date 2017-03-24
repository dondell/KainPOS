package dataconnection;

import java.io.*;
import java.sql.*;

/**
 *
 * @author johnericx or Eric P Lozarita
 */
public class DBConnection {

    /**
     * Creates a new instance of DBConnection
     */
    private static String dbDriver = "com.mysql.cj.jdbc.Driver";
    private static String readFile = System.getProperty("user.dir") + "/src/dataconnection/config.ini";
    private static String jdbcUrl = "jdbc:mysql://";
    private static String dbUrl;
    private static String mySqlUsername = "admin";   //Default username value
    private static String mySqlPassword = "admin";  //Default password value
    public static Connection conn;

    /**
     * @param aDbDriver the dbDriver to set
     */
    public static void setDbDriver(String aDbDriver) {
        dbDriver = aDbDriver;
    }

    /**
     * @param aReadFile the readFile to set
     */
    public static void setReadFile(String aReadFile) {
        readFile = aReadFile;
    }

    /**
     * @param aJdbcUrl the jdbcUrl to set
     */
    public static void setJdbcUrl(String aJdbcUrl) {
        jdbcUrl = aJdbcUrl;
    }

    /**
     * @return the dbUrl
     */
    public static String getDbUrl() {
        return dbUrl;
    }

    /**
     * @param aDbUrl the dbUrl to set
     */
    public static void setDbUrl(String aDbUrl) {
        dbUrl = aDbUrl;
    }

    /**
     * @return the mySqlUsername
     */
    public static String getMySqlUsername() {
        return mySqlUsername;
    }

    /**
     * @param aMySqlUsername the mySqlUsername to set
     */
    public static void setMySqlUsername(String aMySqlUsername) {
        mySqlUsername = aMySqlUsername;
    }

    /**
     * @return the mySqlPassword
     */
    public static String getMySqlPassword() {
        return mySqlPassword;
    }

    /**
     * @param aMySqlPassword the mySqlPassword to set
     */
    public static void setMySqlPassword(String aMySqlPassword) {
        mySqlPassword = aMySqlPassword;
    }

    private String getDbDriver() {
        return dbDriver;
    }

    private String getJdbcUrl() {
        return jdbcUrl;
    }

    private String getUsername() {
        return getMySqlUsername();
    }

    private void setUsername(String val) {
        this.setMySqlUsername(val);
    }

    private String getPassword() {
        return getMySqlPassword();
    }

    private void setPassword(String val) {
        this.setMySqlPassword(val);
    }

    private String getReadFile() {
        return readFile;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection val) {
        this.conn = val;
    }

    private String getConfig() { //get the String value stored from dbUrl fields
        try {
            BufferedReader in = new BufferedReader(new FileReader(getReadFile()));
            String line;
            while ((line = in.readLine()) != null) {
                //a validation testing of the String value return to printline
                //System.out.println(line);
                return dbUrl = line; //store the String value to dbUrl fields came from config.in file
            }
            in.close();
        } catch (IOException e) {
            System.out.println("<" + e + ">");
        }
        return getDbUrl(); //return the value to the dbUrl fields
    }

    public void getConnectToDbHost() {
        synchronized (this.getReadFile()) {
            try {
                if (this.getConn() == null || this.getConn().isClosed()) {
                    try {
                        Class.forName(this.getDbDriver());
                        setConn(DriverManager.getConnection(this.getJdbcUrl() + this.getConfig(), this.getUsername(), this.getPassword()));
                    } catch (Exception e) {
                        System.err.println(e);
                    }
                } else {
                    System.out.println("Connection still open...");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
