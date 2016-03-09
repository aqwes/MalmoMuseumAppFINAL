package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class QuestionPanel implements ActionListener {
    private Font header = new Font("TimesRoman", Font.PLAIN, 30);
    private Font reg = new Font("TimesRoman", Font.PLAIN, 18);
    private JTextPane question1;
    private JTextPane points;
    private String correctAnswer;
    private Client client;
    private JCheckBox answer1;
    private JCheckBox answer2;
    private JCheckBox answer3;
    private int userPoints;
    private JLabel answerCorrOrWrong;
    private JLabel pointslabel1;
    private JLabel questionlabel1;
    private JPanel northPanel;
    private JFrame frame;
    private JPanel centerPanel;
    private JPanel southPanel;
    private JButton nextButton;
    private JButton exitButton;
    private int j = 0;

    public QuestionPanel(Client client) {
        this.client = client;
    }

    public void start() {
        init();
        frame = new JFrame("QuestionPanel");
        frame.setSize(new Dimension(350, 430));
        frame.setLayout(new BorderLayout());
        frame.add(northPanel, BorderLayout.NORTH);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(southPanel, BorderLayout.SOUTH);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void init() {
        northPanel = new JPanel();
        centerPanel = new JPanel();
        southPanel = new JPanel();

        northPanel.setBackground(Color.red);
        centerPanel.setBackground(Color.red);
        southPanel.setBackground(Color.red);
//Question
        questionlabel1 = new JLabel("Question");
        questionlabel1.setPreferredSize(new Dimension(220, 30));
        questionlabel1.setFont(header);

        pointslabel1 = new JLabel("Points:");
        pointslabel1.setPreferredSize(new Dimension(50, 18));
        pointslabel1.setFont(reg);

        points = new JTextPane();
        points.setPreferredSize(new Dimension(30, 30));
        points.setFont(header);
        points.setBackground(Color.lightGray);
        points.setEditable(false);

        question1 = new JTextPane();
        question1.setPreferredSize(new Dimension(350, 40));
        question1.setFont(reg);
        question1.setBackground(Color.lightGray);
        question1.setEditable(false);

// Points


//Answer 1
        answer1 = new JCheckBox();
        answer1.setPreferredSize(new Dimension(200, 40));
        answer1.setFont(reg);


//Answer 2
        answer2 = new JCheckBox();
        answer2.setPreferredSize(new Dimension(200, 40));
        answer2.setFont(reg);


//Answer 3
        answer3 = new JCheckBox();
        answer3.setPreferredSize(new Dimension(200, 40));
        answer3.setFont(reg);


        answerCorrOrWrong = new JLabel();
        answerCorrOrWrong.setPreferredSize(new Dimension(200, 30));
        answerCorrOrWrong.setFont(header);


        nextButton = new JButton("Question");
        nextButton.setPreferredSize(new Dimension(300, 40));
        nextButton.setFont(header);


        exitButton = new JButton("Exit");
        exitButton.setBounds(10, 80, 80, 25);


        northPanel.add(questionlabel1);
        northPanel.add(pointslabel1);
        northPanel.add(points);
        northPanel.add(question1);
        centerPanel.add(answer1);
        centerPanel.add(answer2);
        centerPanel.add(answer3);
        southPanel.add(answerCorrOrWrong);
        southPanel.add(nextButton);
        southPanel.add(exitButton);

        northPanel.setPreferredSize(new Dimension(350, 132));
        centerPanel.setPreferredSize(new Dimension(200, 132));
        southPanel.setPreferredSize(new Dimension(350, 132));
        nextButton.addActionListener(this);
        exitButton.addActionListener(this);
        answer1.addActionListener(this);
        answer2.addActionListener(this);
        answer3.addActionListener(this);

        answer1.setEnabled(false);
        answer2.setEnabled(false);
        answer3.setEnabled(false);

    }


    public void setQuestion(String question) {
        if (j == 0) {
            //Question
            question1.setText(question + "?");
            j++;
        } else if (j == 1) {
            correctAnswer = question;
            j++;
        } else if (j == 2) {
            //Answer1
            answer1.setText(question);
            j++;
        } else if (j == 3) {
            //Answer2
            answer2.setText(question);
            j++;
        } else if (j == 4) {
            //Answer3
            answer3.setText(question);
            j = 0;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String answer;

        if (exitButton == e.getSource()) {
            client.stop();
        }
        if (nextButton == e.getSource()) {

            client.nextQuestion();
            answer1.setEnabled(true);
            answer2.setEnabled(true);
            answer3.setEnabled(true);
            answer1.setSelected(false);
            answer2.setSelected(false);
            answer3.setSelected(false);
            answerCorrOrWrong.setText("");
        }
        try {


            if (answer1.isSelected()) {
                disabelButtons();
                answer = "1";
                if (correctAnswer.equals(answer)) {
                    answerCorrOrWrong.setText("CORRECT");
                    userPoints++;
                } else {
                    answerCorrOrWrong.setText("WRONG");
                }

            }
            if (answer2.isSelected()) {
                disabelButtons();
                answer = "2";
                if (correctAnswer.equals(answer)) {
                    answerCorrOrWrong.setText("CORRECT");
                    userPoints++;
                } else {
                    answerCorrOrWrong.setText("WRONG");
                }
            }
            if (answer3.isSelected()) {
                disabelButtons();
                answer = "3";
                if (correctAnswer.equals(answer)) {
                    answerCorrOrWrong.setText("CORRECT");
                    userPoints++;
                } else {
                    answerCorrOrWrong.setText("WRONG");
                }
            }
        } catch (NullPointerException r) {
        }
        points.setText("" + userPoints);
    }

    public void close() {
        frame.dispose();
        frame.setVisible(false);
    }
    public void disabelButtons(){
        answer1.setEnabled(false);
        answer2.setEnabled(false);
        answer3.setEnabled(false);
    }
    public void noMoreQuestions(){
        JOptionPane.showMessageDialog(null,"Congratz you made it! Points: "+points.getText());
        close();
    }

    public String getPoints() {
        return points.getText();
    }
}

