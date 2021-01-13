package View;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Controller.SysData;
import model.Difficulty;
import model.Question;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;

public class updateQuestion extends JFrame {

	private JPanel contentPane;
	private JTextField question;
	private JTextField answer1;
	private JTextField answer2;
	private JTextField answer3;
	private JTextField answer4;
	private JComboBox<Difficulty> comboBox = new JComboBox<Difficulty>();
	private JComboBox<String> comboBox_1 = new JComboBox<String>();
	private Question oldQ;

	/**
	 * Create the frame.
	 */
	public updateQuestion(Question q) {
		setTitle("Hamka Game");
		setIconImage(Toolkit.getDefaultToolkit().getImage(mainScreen.class.getResource("/image/icon.jpeg")));
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 908, 639);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setResizable(false);
		this.oldQ = q;

		JLabel QuestionLabel = new JLabel("Question");
		QuestionLabel.setFont(new Font("Ravie", Font.BOLD, 18));
		QuestionLabel.setForeground(Color.WHITE);
		QuestionLabel.setBounds(100, 77, 121, 37);
		contentPane.add(QuestionLabel);

		JLabel Answer1Label = new JLabel("Answer 1");
		Answer1Label.setForeground(Color.WHITE);
		Answer1Label.setFont(new Font("Ravie", Font.BOLD, 18));
		Answer1Label.setBounds(100, 133, 121, 37);
		contentPane.add(Answer1Label);

		JLabel Answer2Label = new JLabel("Answer 2");
		Answer2Label.setForeground(Color.WHITE);
		Answer2Label.setFont(new Font("Ravie", Font.BOLD, 18));
		Answer2Label.setBounds(100, 188, 121, 37);
		contentPane.add(Answer2Label);

		JLabel Answer3Label = new JLabel("Answer 3");
		Answer3Label.setForeground(Color.WHITE);
		Answer3Label.setFont(new Font("Ravie", Font.BOLD, 18));
		Answer3Label.setBounds(100, 243, 121, 37);
		contentPane.add(Answer3Label);

		JLabel Answer4Label = new JLabel("Answer 4");
		Answer4Label.setForeground(Color.WHITE);
		Answer4Label.setFont(new Font("Ravie", Font.BOLD, 18));
		Answer4Label.setBounds(100, 298, 121, 37);
		contentPane.add(Answer4Label);

		JLabel CorrectAnswer1Label = new JLabel("Correct Answer ");
		CorrectAnswer1Label.setForeground(Color.WHITE);
		CorrectAnswer1Label.setFont(new Font("Ravie", Font.BOLD, 18));
		CorrectAnswer1Label.setBounds(100, 353, 228, 37);
		contentPane.add(CorrectAnswer1Label);

		JLabel DifficultyLabel = new JLabel("Difficulty");
		DifficultyLabel.setForeground(Color.WHITE);
		DifficultyLabel.setFont(new Font("Ravie", Font.BOLD, 18));
		DifficultyLabel.setBounds(100, 408, 148, 37);
		contentPane.add(DifficultyLabel);

		JLabel lblNewLabel_2 = new JLabel("Update Question");
		lblNewLabel_2.setFont(new Font("Ravie", Font.BOLD, 25));
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(258, 10, 303, 51);
		contentPane.add(lblNewLabel_2);

		JLabel numberLabel = new JLabel("Team:");
		numberLabel.setForeground(Color.WHITE);
		numberLabel.setFont(new Font("Ravie", Font.BOLD, 18));
		numberLabel.setBounds(100, 463, 121, 37);
		contentPane.add(numberLabel);

		RoundButton button_2 = new RoundButton("");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateQuestionActionPerformed(arg0);
			}

		});
		button_2.setFont(new Font("Ravie", Font.BOLD, 19));
		button_2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/submit.png")));
		button_2.setBackground(new Color(255, 240, 245));
		button_2.setBounds(415, 488, 100, 100);
		contentPane.add(button_2);

		question = new JTextField();
		question.setFont(new Font("Arial", Font.BOLD, 16));
		question.setBounds(274, 80, 377, 29);
		contentPane.add(question);
		question.setColumns(10);
		question.setText(q.getText());

		answer1 = new JTextField();
		answer1.setFont(new Font("Arial", Font.BOLD, 16));
		answer1.setColumns(10);
		answer1.setBounds(274, 136, 377, 29);
		contentPane.add(answer1);
		answer1.setText(q.getAnswers().get(0));

		answer2 = new JTextField();
		answer2.setFont(new Font("Arial", Font.BOLD, 16));
		answer2.setColumns(10);
		answer2.setBounds(274, 191, 377, 29);
		contentPane.add(answer2);
		answer2.setText(q.getAnswers().get(1));

		answer3 = new JTextField();
		answer3.setFont(new Font("Arial", Font.BOLD, 16));
		answer3.setColumns(10);
		answer3.setBounds(274, 246, 377, 29);
		contentPane.add(answer3);
		answer3.setText(q.getAnswers().get(2));

		answer4 = new JTextField();
		answer4.setFont(new Font("Arial", Font.BOLD, 16));
		answer4.setColumns(10);
		answer4.setBounds(274, 301, 377, 29);
		contentPane.add(answer4);
		answer4.setText(q.getAnswers().get(3));

		comboBox.setFont(new Font("Arial", Font.BOLD, 16));
		comboBox.setBounds(258, 415, 139, 29);
		contentPane.add(comboBox);

		for (Difficulty type : Difficulty.values()) {
			DefaultComboBoxModel<Difficulty> model = (DefaultComboBoxModel<Difficulty>) comboBox.getModel();
			if (model.getIndexOf(type) == -1) {
				comboBox.addItem(type);
			}
		}

		comboBox.setSelectedItem(q.getLevel());

		JLabel lblNewLabel_1 = new JLabel("Giraffe");
		lblNewLabel_1.setFont(new Font("Ravie", Font.BOLD, 16));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(206, 464, 139, 37);
		contentPane.add(lblNewLabel_1);
		comboBox_1.setFont(new Font("Ravie", Font.BOLD, 16));

		comboBox_1.setBounds(316, 360, 81, 29);
		contentPane.add(comboBox_1);
		comboBox_1.addItem("1");
		comboBox_1.addItem("2");
		comboBox_1.addItem("3");
		comboBox_1.addItem("4");

		comboBox_1.setSelectedItem(q.getCorrect_ans());

		RoundButton button_5 = new RoundButton("");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				backActionPerformed(arg0);
			}
		});

		button_5.setFont(new Font("Ravie", Font.BOLD, 19));
		button_5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/backk.png")));
		button_5.setBackground(new Color(255, 240, 245));
		button_5.setBounds(10, 10, 50, 50);
		contentPane.add(button_5);

		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/image/background.jpeg")));
		lblNewLabel.setBounds(0, 0, 894, 601);
		contentPane.add(lblNewLabel);

	}

	protected void backActionPerformed(ActionEvent arg0) {
		Questions q = new Questions();
		q.setVisible(true);
		this.setVisible(false);
	}

	private void updateQuestionActionPerformed(ActionEvent arg0) {
		boolean flag = false;
		String Text = "";
		String ans1 = "";
		String ans2 = "";
		String ans3 = "";
		String ans4 = "";
		String CorrectAnswer = "";
		Difficulty difficultly = null;

		if (question.getText().isEmpty()) {
			flag = true;
		} else {
			Text = question.getText();
		}

		if (answer1.getText().isEmpty()) {
			flag = true;
		} else {
			ans1 = answer1.getText();
		}

		if (answer2.getText().isEmpty()) {
			flag = true;
		} else {
			ans2 = answer2.getText();
		}

		ans3 = answer3.getText();

		ans4 = answer4.getText();

		if (comboBox_1.getSelectedIndex() == -1) {
			flag = true;
		} else {
			CorrectAnswer = (String) comboBox_1.getSelectedItem();
		}

		if (comboBox.getSelectedIndex() == -1) {
			flag = true;
		} else {
			difficultly = (Difficulty) comboBox.getSelectedItem();
		}

		if ((ans3.equals("") && CorrectAnswer.equals("3")) || (ans4.equals("") && CorrectAnswer.equals("4"))) {
			JOptionPane.showMessageDialog(this, "You have chosen an empty correct answer", null,
					JOptionPane.ERROR_MESSAGE);
		} else

		if (flag) {
			JOptionPane.showMessageDialog(this, "please fill/correct all the feilds", null, JOptionPane.ERROR_MESSAGE);
		} else {
			ArrayList<String> answers = new ArrayList<String>();
			answers.add(ans1);
			answers.add(ans2);
			answers.add(ans3);
			answers.add(ans4);

			Question newQ = new Question(Text, answers, CorrectAnswer, difficultly, "Giraffe");
			if (SysData.updateQuestion(newQ, oldQ)) {
				JOptionPane.showMessageDialog(this, "updated Succefully");
				question.setText("");
				answer1.setText("");
				answer2.setText("");
				answer3.setText("");
				answer4.setText("");
				comboBox_1.setSelectedIndex(-1);
				comboBox.setSelectedIndex(-1);
			} else
				JOptionPane.showMessageDialog(this, "Something went wrong", null, JOptionPane.ERROR_MESSAGE);

		}

	}
}
