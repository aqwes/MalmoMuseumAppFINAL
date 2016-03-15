package Client;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * This class reads the inputstreams from all clients and append them to the gui window.
 * Created by Dennis on 2016-01-03.
 */
public class ClientThread extends Thread {
    private final Socket socket;
    private DataInputStream streamIn;
    private QuestionPanel questionPanel;
    private boolean connected = true;
    private String userInput = null;
    private String[] splitArray = null;
    private LoginGUI gui;
    private Client client;



    public ClientThread(Socket socket, QuestionPanel questionPanel, LoginGUI gui, Client client) {
        this.gui = gui;
        this.questionPanel = questionPanel;
        this.socket = socket;
        this.client=client;
        start();
    }

    public void close() {
        try {
            if (streamIn != null) streamIn.close();
        } catch (IOException ioe) {
            System.out.println("Error closing input stream: " + ioe);
        }
    }


    /**
     * This method read data from the stream that the server has written.
     */
    public void run() {
        String message=null;
        while (true) {
            try {
                streamIn = new DataInputStream(socket.getInputStream());
                while (connected) {
                     message=streamIn.readUTF();
                    if (message.equals("Connected")) {
                        questionPanel.start();
                        gui.close();
                        connected = false;
                    }
                    else if (message.equals("Wrong")) {
                        gui.emptyfields();
                        JOptionPane.showMessageDialog(null,"Fel användarnamn eller lösernord.");
                    }
                    else if (message.equals("regWrong")) {
                        gui.emptyfields();
                        JOptionPane.showMessageDialog(null,"Både användarnamn och lösenord måste uppnå följande regler"+"\n"+
                        "* Måste vara minst 6 karaktärer långt."+"\n"+
                                "* Måste innehålla minst en versal."+"\n"+
                                "* Måste innehålla minst en gemen."+"\n"+
                                "* Mellanslag är ej tillåtet.");
                    }

                }

                while ((userInput = streamIn.readUTF()) != null) {
                    if (userInput.equals("noMoreQuestions")) {
                        questionPanel.clear();
                        questionPanel.noMoreQuestions();
                        client.sendPoint(questionPanel.getPoints());
                        close();
                        break;
                    }
                    splitArray = userInput.split("\\?");
                    for (String s : splitArray) {
                        questionPanel.setQuestion(s);

                    }
                }

            } catch (IOException e) {
            }
        }
    }

}
