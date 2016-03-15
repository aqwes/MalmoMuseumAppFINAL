package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Dennis on 2016-03-15.
 */
class HighscorePanel implements ActionListener {
    private final Font header = new Font("TimesRoman", Font.PLAIN, 30);
    private final Font reg = new Font("TimesRoman", Font.PLAIN, 22);

    private JLabel highscore1;
    private JLabel highscore2;
    private JLabel highscore3;
    private JLabel highscore4;
    private JLabel highscore5;
    private JLabel highscore6;
    private JLabel highscore7;
    private JLabel highscore8;
    private JLabel pointslabel;
    private JFrame frame;
    private JPanel northPanel;
    private JPanel centerPanel;
    private JPanel southPanel;
    private JButton exitButton;
    private int j;

    public void start() {
        init();
        frame = new JFrame("Poäng");
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setSize(new Dimension(350, 450));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(northPanel, BorderLayout.NORTH);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(southPanel, BorderLayout.SOUTH);

    }

    private void init() {
        northPanel = new JPanel();
        centerPanel = new JPanel();
        southPanel = new JPanel();

        northPanel.setBackground(Color.black);
        centerPanel.setBackground(Color.LIGHT_GRAY);
        southPanel.setBackground(Color.LIGHT_GRAY);

        pointslabel = new JLabel("Poänglista");
        pointslabel.setPreferredSize(new Dimension(300, 50));
        pointslabel.setFont(header);
        pointslabel.setForeground(Color.LIGHT_GRAY);


        highscore1 = new JLabel();
        highscore1.setPreferredSize(new Dimension(300, 40));
        highscore1.setFont(reg);


        highscore2 = new JLabel();
        highscore2.setPreferredSize(new Dimension(300, 40));
        highscore2.setFont(reg);


        highscore3 = new JLabel();
        highscore3.setPreferredSize(new Dimension(300, 40));
        highscore3.setFont(reg);

        highscore4 = new JLabel();
        highscore4.setPreferredSize(new Dimension(300, 40));
        highscore4.setFont(reg);

        highscore5 = new JLabel();
        highscore5.setPreferredSize(new Dimension(300, 40));
        highscore5.setFont(reg);

        highscore6 = new JLabel();
        highscore6.setPreferredSize(new Dimension(300, 40));
        highscore6.setFont(reg);

        highscore7 = new JLabel();
        highscore7.setPreferredSize(new Dimension(300, 40));
        highscore7.setFont(reg);

        highscore8 = new JLabel();
        highscore8.setPreferredSize(new Dimension(300, 40));
        highscore8.setFont(reg);


        exitButton = new JButton("Avsluta");
        exitButton.setPreferredSize(new Dimension(300, 40));
        exitButton.setFont(header);

        northPanel.add(pointslabel);
        centerPanel.add(highscore1);
        centerPanel.add(highscore2);
        centerPanel.add(highscore3);
        centerPanel.add(highscore4);
        centerPanel.add(highscore5);
        centerPanel.add(highscore6);
        centerPanel.add(highscore7);
        centerPanel.add(highscore8);
        southPanel.add(exitButton);

        northPanel.setPreferredSize(new Dimension(350, 50));
        centerPanel.setPreferredSize(new Dimension(350, 300));
        southPanel.setPreferredSize(new Dimension(350, 50));
        exitButton.addActionListener(this);

    }

    public void setHighscore(String points) {
        if (j == 0) {
            highscore1.setText("1. " + points);
            j++;
        } else if (j == 1) {
            highscore2.setText("2. " + points);
            j++;
        } else if (j == 2) {
            highscore3.setText("3. " + points);
            j++;
        } else if (j == 3) {
            highscore4.setText("4. " + points);
            j++;
        } else if (j == 4) {
            highscore5.setText("5. " + points);
            j++;
        } else if (j == 5) {
            highscore6.setText("6. " + points);
            j++;
        } else if (j == 6) {
            highscore7.setText("7. " + points);
            j++;
        } else if (j == 7) {
            highscore8.setText("8. " + points);
            j++;
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (exitButton == e.getSource()) {
            frame.dispose();
            frame.setVisible(false);
            System.exit(0);
        }
    }

    public void showPanel() {
        frame.setVisible(true);
    }
}


