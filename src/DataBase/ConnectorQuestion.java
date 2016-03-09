package DataBase;

import Server.ServerThread;

import java.io.IOException;
import java.sql.*;

public class ConnectorQuestion {

    private final String jdbcUrl;
    private Connection conn = null;
    private ResultSet resultSet = null;
    private Statement statement = null;
    private ServerThread serverThread;
    private int i = 1;


    public ConnectorQuestion(int port, ServerThread serverThread) {
        this.serverThread = serverThread;
        String dbName = "MuseumQuestion";
        String userName = "AdminUser";
        String password = "Hejsan54";
        String hostname = "museumquestion.cssde7wbzw9z.us-west-2.rds.amazonaws.com";
        jdbcUrl = "jdbc:mysql://" + hostname + ":" +
                port + "/" + dbName + "?user=" + userName + "&password=" + password;

        try {
            conn = DriverManager.getConnection(jdbcUrl);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void getQuestions() throws SQLException, IOException {
        statement = conn.createStatement();
        resultSet = statement.executeQuery("SELECT Question, Answer from Questions WHERE id =" + i);

        if (resultSet.next()) {
            serverThread.sendQuestions(resultSet.getString(1));
            serverThread.sendQuestions(resultSet.getString(2) + "?");

        }
        else if (!resultSet.next()) {
            serverThread.noMoreQuestions();
        }
    }

    public void getAnswers() throws SQLException, IOException {
        statement = conn.createStatement();
        resultSet = statement.executeQuery("SELECT Answer, Answer2, Answer3 from Answers WHERE id=" + i);

        if (resultSet.next()) {
            serverThread.sendAnswers(resultSet.getString(1) + "?");
            serverThread.sendAnswers(resultSet.getString(2) + "?");
            serverThread.sendAnswers(resultSet.getString(3) + "?");
        }
        i++;
    }
}