package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Dennis on 2016-03-03.
 */
class LoginGUI implements ActionListener {
    private JTextField userText;
    private JTextField passwordText;
    private JFrame frame;
    private Client client;
    private JButton loginButton;
    private JButton quitButton;


    private Font que = new Font("TimesRoman", Font.ROMAN_BASELINE, 18);

    public LoginGUI(Client client) {
        this.client = client;
        frame = new JFrame("MalmöMuseumApp - inloggning");
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setBackground(Color.black);
        frame.add(panel);
        placeComponents(panel);
        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {

        panel.setLayout(null);

        JLabel userLabel = new JLabel("Användarnamn:");
        userLabel.setBounds(10, 10, 120, 25);
        userLabel.setFont(que);
        userLabel.setForeground(Color.WHITE);
        panel.add(userLabel);


        userText = new JTextField(20);
        userText.setBounds(125, 10, 160, 25);
        userText.setFont(que);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Lösenord:");
        passwordLabel.setBounds(10, 40, 120, 25);
        passwordLabel.setFont(que);
        passwordLabel.setForeground(Color.WHITE);
        panel.add(passwordLabel);

        passwordText = new JPasswordField(20);
        passwordText.setBounds(125, 40, 160, 25);
        passwordText.setFont(que);
        panel.add(passwordText);

        loginButton = new JButton("Logga in");
        loginButton.setBounds(10, 80, 100, 30);
        loginButton.setFont(que);
        panel.add(loginButton);


        quitButton = new JButton("Avsluta");
        quitButton.setBounds(140, 80, 100, 30);
        quitButton.setFont(que);
        panel.add(quitButton);

        loginButton.addActionListener(this);
        quitButton.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e) {
        if (loginButton == e.getSource()) {
            client.connect(userText.getText(), passwordText.getText());
        }
        if (quitButton == e.getSource()) {
           close();
        }
    }

    public void close() {
        frame.dispose();
        frame.setVisible(false);
    }

    public void emptyfields() {
        userText.setText("");
        passwordText.setText("");
    }
}


