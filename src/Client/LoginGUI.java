package Client;

import javax.swing.*;
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

    public LoginGUI(Client client) {
        this.client = client;
        frame = new JFrame("MalmöMuseumApp - login");
        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);
        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {

        panel.setLayout(null);

        JLabel userLabel = new JLabel("Username");
        userLabel.setBounds(10, 10, 80, 25);
        panel.add(userLabel);

        userText = new JTextField(20);
        userText.setBounds(100, 10, 160, 25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 40, 80, 25);
        panel.add(passwordLabel);

        passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 40, 160, 25);
        panel.add(passwordText);

        loginButton = new JButton("Login");
        loginButton.setBounds(10, 80, 80, 25);
        panel.add(loginButton);


        loginButton.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e) {
        if (loginButton == e.getSource()) {
            client.connect(userText.getText(), passwordText.getText());
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


