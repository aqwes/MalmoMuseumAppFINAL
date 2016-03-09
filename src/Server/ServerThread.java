package Server;

import DataBase.ConnectorQuestion;
import DataBase.ConnectorUser;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

/**
 * This is the chatServerThread. It´s purpose is to read and send message to all clients
 * Created by Dennis on 2016-01-02.
 */
public class ServerThread extends Thread {
    private final Socket socket;
    private DataInputStream streamIn;
    private DataOutputStream streamOut;
    private String user;
    private String password;
    private boolean passTrue = false;
    private ConnectorUser connectorUser;
    private boolean userTrue;
    private ConnectorQuestion conQuestion;
    private Server server = null;
    private String points;


    public ServerThread(Server server, Socket socket) {
        this.server = server;
        this.socket = socket;
        conQuestion = new ConnectorQuestion(3306, this);
        connectorUser = new ConnectorUser(3306);

    }

    public void sendQuestions(String s) throws IOException {
        streamOut.writeUTF(s);
        streamOut.flush();
    }

    public void sendAnswers(String answer) throws IOException {
        streamOut.writeUTF(answer);
        streamOut.flush();
    }

    private void readFromClient() {
        try {
            conQuestion.getQuestions();
            conQuestion.getAnswers();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method creates a inputstream and a outputstream. When there is a message to read we append it to the gui.
     */
    public void run() {
        try {
            streamIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            streamOut = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                String line = streamIn.readUTF();
                if (line.contains("next")) {
                    readFromClient();
                }
                if (line.contains("username11.")) {
                    user = line.replaceAll("username11.", "");
                    userTrue = true;
                }
                if (line.contains("password11.")) {
                    password = line.replaceAll("password11.", "");
                    passTrue = true;
                }
                if (userTrue && passTrue) {
                    if (connectorUser.user(user, password)) {
                        streamOut.writeUTF("Connected");
                        streamOut.flush();
                        userTrue = false;
                        passTrue = false;
                    } else if (!connectorUser.user(user, password)) {
                        streamOut.writeUTF("Wrong");
                        streamOut.flush();
                    }
                }
                if (line.contains("clientExit.")) {
                    System.out.print(socket + "Disconected");

                }
                if (line.contains("points.")) {
                    points = line.replaceAll("points.", "");
                    connectorUser.sendPoint(points, user);

                }
            } catch (IOException e) {
            }
        }
    }
        public void noMoreQuestions(){
            try {
                streamOut.writeUTF("noMoreQuestions");
                streamOut.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }


    }
    }

