package Client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client implements Runnable {
    private String hostname;
    private int port;
    private Socket socketClient;
    private QuestionPanel questionPanel;
    private DataOutputStream streamOut = null;
    private LoginGUI gui;
    private Thread thread;
    private ClientThread client;


    public Client(String hostname, int port) {
        gui = new LoginGUI(this);
        this.hostname = hostname;
        this.port = port;
    }

    public void connect(String userName, String password, boolean regOrLog) {

        System.out.println("Attempting to connect to " + hostname + ":" + port);
        try {
            socketClient = new Socket(hostname, port);
            questionPanel = new QuestionPanel(this);
            client = new ClientThread(socketClient, questionPanel, gui, this);
            thread = new Thread(this);
            thread.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Connected: " + socketClient);

        try {
            streamOut = new DataOutputStream(socketClient.getOutputStream());
        } catch (IOException ignored) {
        }

            try {
                if (!regOrLog) {
                streamOut.writeUTF("username." + userName);
                streamOut.writeUTF("password." + password);
                streamOut.flush();

            }else if (regOrLog) {
                    streamOut.writeUTF("regname." + userName);
                    streamOut.writeUTF("regpass." + password);
                    streamOut.flush();
            }
            } catch (IOException e) {
                e.printStackTrace();
            }

    }

    public void nextQuestion() {
        try {
            streamOut.writeUTF("next");
            streamOut.flush();

        } catch (IOException ignored) {
        }
    }

    @Override
    public void run() {
    }
    public void sendPoint(String points){
        try {
            streamOut.writeUTF("points."+points);
            streamOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void stop() {
        try {
        streamOut.writeUTF("clientExit.");
        streamOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (thread != null) {
            thread.interrupt();
            thread = null;
        }
        try {
            if (questionPanel != null) questionPanel.close();
            if (streamOut != null) streamOut.close();
            if (socketClient != null) socketClient.close();
        } catch (IOException ioe) {
            System.out.println("Error closing ...");
        }
        client.close();
        client.interrupt();
    }
}
