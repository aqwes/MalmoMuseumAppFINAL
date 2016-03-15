package Client;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * Created by Dennis on 2016-03-15.
 */
class ClientThread extends Thread {
    private final Socket socket;
    private DataInputStream streamIn;
    private final QuestionPanel questionPanel;
    private boolean connected = true;
    private String userInput = null;
    private String[] splitArray = null;
    private final LoginGUI gui;
    private final Client client;
    private final ArrayList<String> arraylist = new ArrayList<>();
    private int i;
    private final HighscorePanel h;


    public ClientThread(Socket socket, QuestionPanel questionPanel, LoginGUI gui, Client client) {
        this.gui = gui;
        this.questionPanel = questionPanel;
        h = new HighscorePanel();
        questionPanel.start();
        this.socket = socket;
        this.client=client;
        h.start();
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
        String message;
        while (true) {
            try {
                streamIn = new DataInputStream(socket.getInputStream());
                while (connected) {
                     message=streamIn.readUTF();
                    switch (message) {
                        case "Connected":
                            questionPanel.show();
                            gui.close();
                            connected = false;
                            break;
                        case "Wrong":
                        case "regWrong":
                            gui.emptyfields();
                            JOptionPane.showMessageDialog(null, "Användarnamn eller lösenord är fel!");
                            break;
                        case "regCorr":
                            questionPanel.start();
                            gui.close();
                            connected = false;
                            JOptionPane.showMessageDialog(null, "Grattis! Du är nu registrerad. Välkommen in i värmen!");
                            break;
                    }
                }

                while ((userInput = streamIn.readUTF()) != null) {
                    if (userInput.equals("noMoreQuestions")) {
                        questionPanel.clear();
                        questionPanel.noMoreQuestions();
                        client.sendPoint(questionPanel.getPoints());

                    }
                    if (userInput.contains("Score.")) {
                        userInput = userInput.replaceAll("Score.", "");
                        arraylist.add(userInput);
                        i++;
                        if (i == 7) {
                            setHighscore();
                        }
                    }
                    splitArray = userInput.split("\\?");
                    for (String s : splitArray) {
                        questionPanel.setQuestion(s);
                    }
                }

            } catch (IOException ignored) {
            }
        }
    }

    private void setHighscore() {
        h.showPanel();
        for (String number : arraylist) {
            h.setHighscore(number);
        }
        client.stop();
    }

}
