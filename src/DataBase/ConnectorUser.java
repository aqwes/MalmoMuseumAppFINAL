package DataBase;

import java.sql.*;

public class ConnectorUser {

    private final String jdbcUrl;
    private Connection conn = null;
    private ResultSet resultSet = null;
    private Statement statement = null;
    private boolean connected;
    private String name;
    private String pass;

    public ConnectorUser(int port) {
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
            PreparedStatement pst = conn.prepareStatement("UPDATE Medlem SET points = '"+points+"' "
                    + "WHERE userName = '"+user+"';");
            pst.executeUpdate();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}