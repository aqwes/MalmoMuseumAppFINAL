package DataBase;

import Server.ServerThread;

import java.sql.*;

/**
 * Created by Dennis on 2016-03-15.
 */
public class ConnectorUser {

    private final String jdbcUrl;
    private Connection conn = null;
    private ResultSet resultSet = null;
    private Statement statement = null;
    private boolean connected;
    private String name;
    private String pass;
    private final String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{6,}$";
    private boolean reg = false;
    private final ServerThread serverThread;
    private String point;

    public ConnectorUser(int port, ServerThread serverThread) {
        this.serverThread = serverThread;
        String dbName = "museumapp";
        String userName = "AdminUser";
        String password = "Hejsan54";
        String hostname = "malmoapp.cssde7wbzw9z.us-west-2.rds.amazonaws.com";
        jdbcUrl = "jdbc:mysql://" + hostname + ":" +
                port + "/" + dbName + "?user=" + userName + "&password=" + password;

        try {
            conn = DriverManager.getConnection(jdbcUrl);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean user(String username, String password) {
        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT userName, password from Medlem");

            while (resultSet.next()) {
                name = resultSet.getString(1);
                pass = resultSet.getString(2);

                if (name.equals(username) && pass.equals(password)) {
                    return connected = true;
                }
            }
            if (!name.equals(username) || !pass.equals(password)) {
                return connected = false;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connected;
    }


    public void sendPoint(String points, String user) {
        try {
            conn = DriverManager.getConnection(jdbcUrl);
            PreparedStatement pst = conn.prepareStatement("UPDATE Medlem SET points = '" + points + "' "
                    + "WHERE userName = '" + user + "';");
            pst.executeUpdate();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean connectorREG(String username, String password) {
        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT userName, password from Medlem");

            while (resultSet.next()) {
                name = resultSet.getString(1);
                if (username.equals(name)) {
                    reg = false;
                }
                else{
                    reg=true;
                }
            }
            if (reg) {
                if (username.matches(regex) && password.matches(regex)) {
                    statement.executeUpdate("INSERT INTO Medlem (userName, password, points) VALUES "
                            + "('" + username + "', '" + password + "','0');");
                }else{
                    reg=false;
            }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reg;
    }

    public void getHighScore() {
        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT userName,points from Medlem ORDER by points desc");

            while (resultSet.next()) {
                name = resultSet.getString(1);
                point = resultSet.getString(2);
                serverThread.highScore(name, point);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
}
