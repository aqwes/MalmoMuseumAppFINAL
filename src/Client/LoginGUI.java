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
    private JButton regButton;


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

        regButton = new JButton("Registrera");
        regButton.setBounds(140, 80, 100, 30);
        regButton.setFont(que);
        panel.add(regButton);

        loginButton.addActionListener(this);
        regButton.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e) {
        if((userText.getText().length()>0) && (passwordText.getText().length()>0)) {
            if (loginButton == e.getSource()) {
                client.connect(userText.getText(), passwordText.getText(), false);
            }
            if (regButton == e.getSource()) {
                client.connect(userText.getText(), passwordText.getText(), true);
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Användarnamn eller lösenord är fel!");
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


