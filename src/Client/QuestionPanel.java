package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class QuestionPanel implements ActionListener {
    private Font header = new Font("TimesRoman", Font.PLAIN, 30);
    private Font reg = new Font("TimesRoman", Font.PLAIN, 22);
    private Font que = new Font("TimesRoman", Font.BOLD, 20);
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
        frame = new JFrame("Frågepanel");

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setSize(new Dimension(350, 450));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(northPanel, BorderLayout.NORTH);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(southPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private void init() {
        northPanel = new JPanel();
        centerPanel = new JPanel();
        southPanel = new JPanel();

        northPanel.setBackground(Color.black);
        centerPanel.setBackground(Color.LIGHT_GRAY);
        southPanel.setBackground(Color.LIGHT_GRAY);
//Question
        questionlabel1 = new JLabel("Fråga");
        questionlabel1.setPreferredSize(new Dimension(170, 50));
        questionlabel1.setFont(header);
        questionlabel1.setForeground(Color.LIGHT_GRAY);

        pointslabel1 = new JLabel("Poäng:");
        pointslabel1.setPreferredSize(new Dimension(70, 50));
        pointslabel1.setFont(reg);
        pointslabel1.setForeground(Color.LIGHT_GRAY);

        points = new JTextPane();
        points.setPreferredSize(new Dimension(30, 30));
        points.setFont(header);
        points.setBackground(Color.lightGray);
        points.setEditable(false);

        question1 = new JTextPane();
        question1.setPreferredSize(new Dimension(350, 120));
        question1.setFont(reg);
        question1.setBackground(Color.LIGHT_GRAY);
        question1.setEditable(false);


// Points


//Answer 1
        answer1 = new JCheckBox();
        answer1.setPreferredSize(new Dimension(300, 40));
        answer1.setFont(que);


//Answer 2
        answer2 = new JCheckBox();
        answer2.setPreferredSize(new Dimension(300, 40));
        answer2.setFont(que);


//Answer 3
        answer3 = new JCheckBox();
        answer3.setPreferredSize(new Dimension(300, 40));
        answer3.setFont(que);


        answerCorrOrWrong = new JLabel();
        answerCorrOrWrong.setPreferredSize(new Dimension(200, 30));
        answerCorrOrWrong.setFont(header);


        nextButton = new JButton("Nästa fråga");
        nextButton.setPreferredSize(new Dimension(300, 40));
        nextButton.setFont(header);


        exitButton = new JButton("Avsluta");
        exitButton.setPreferredSize(new Dimension(300, 40));
        exitButton.setFont(header);


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

        northPanel.setPreferredSize(new Dimension(350, 150));
        centerPanel.setPreferredSize(new Dimension(200, 110));
        southPanel.setPreferredSize(new Dimension(350, 130));
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
                    answerCorrOrWrong.setText("Rätt!");
                    userPoints++;
                } else {
                    answerCorrOrWrong.setText("Fel!");
                }

            }
            if (answer2.isSelected()) {
                disabelButtons();
                answer = "2";
                if (correctAnswer.equals(answer)) {
                    answerCorrOrWrong.setText("Rätt!");
                    userPoints++;
                } else {
                    answerCorrOrWrong.setText("Fel!");
                }
            }
            if (answer3.isSelected()) {
                disabelButtons();
                answer = "3";
                if (correctAnswer.equals(answer)) {
                    answerCorrOrWrong.setText("Rätt!");
                    userPoints++;
                } else {
                    answerCorrOrWrong.setText("Fel!");
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
        if(Integer.parseInt(points.getText())<=2){
            JOptionPane.showMessageDialog(null,"Det där gick inte så bra, skärpning! Du fick "+points.getText()+" poäng.");
        }

        else if(Integer.parseInt(points.getText())==3){
            JOptionPane.showMessageDialog(null,"Du var ganska duktig men vi tror att du kan göra lite bättre ifrån dig. Du fick "+points.getText()+" poäng.");
        }
        else if(Integer.parseInt(points.getText())==4){
            JOptionPane.showMessageDialog(null,"Bra jobbat! Du fick "+points.getText()+" poäng");
        }
        else if(Integer.parseInt(points.getText())==5){
            JOptionPane.showMessageDialog(null,"Alla rätt! Du är en riktigt mästaren! Stort grattis! Du fick "+points.getText()+" poäng.");
        }
        close();
    }

    public String getPoints() {
        return points.getText();
    }

    public void clear() {
        question1.setText("");
        answer1.setText("");
        answer2.setText("");
        answer3.setText("");

    }
}


