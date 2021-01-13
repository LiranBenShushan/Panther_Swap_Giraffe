package View;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.GameController;
import model.Game;
import model.Question;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.WindowConstants;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class displayQuestion extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public displayQuestion(Question q, Game g) {
		setTitle("Hamka Game");
		setIconImage(Toolkit.getDefaultToolkit().getImage(mainScreen.class.getResource("/image/icon.jpeg")));
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setBounds(300, 150, 602, 552);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setResizable(false);
		JLabel lblNewLabel_1 = new JLabel("QUESTION");
		lblNewLabel_1.setFont(new Font("Ravie", Font.PLAIN, 25));
		lblNewLabel_1.setForeground(new Color(255, 51, 51));
		lblNewLabel_1.setBounds(203, 32, 169, 40);
		contentPane.add(lblNewLabel_1);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("<html>" + q.getAnswers().get(0) + "</html>");
		rdbtnNewRadioButton.setFont(new Font("Arial", Font.BOLD, 14));
		rdbtnNewRadioButton.setOpaque(false);
		rdbtnNewRadioButton.setContentAreaFilled(false);
		rdbtnNewRadioButton.setBorderPainted(false);
		rdbtnNewRadioButton.setForeground(Color.white);
		rdbtnNewRadioButton.setBounds(6, 152, 582, 50);
		contentPane.add(rdbtnNewRadioButton);

		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("<html>" + q.getAnswers().get(1) + "</html>");
		rdbtnNewRadioButton_1.setFont(new Font("Arial", Font.BOLD, 14));
		rdbtnNewRadioButton_1.setBounds(6, 209, 582, 50);
		rdbtnNewRadioButton_1.setOpaque(false);
		rdbtnNewRadioButton_1.setContentAreaFilled(false);
		rdbtnNewRadioButton_1.setBorderPainted(false);
		rdbtnNewRadioButton_1.setForeground(Color.white);
		contentPane.add(rdbtnNewRadioButton_1);

		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("");
		if (!(q.getAnswers().get(2).equals(""))) {
			rdbtnNewRadioButton_2.setText("<html>" + q.getAnswers().get(2) + "</html>");
			rdbtnNewRadioButton_2.setFont(new Font("Arial", Font.BOLD, 14));
			rdbtnNewRadioButton_2.setBounds(6, 272, 582, 50);
			rdbtnNewRadioButton_2.setOpaque(false);
			rdbtnNewRadioButton_2.setContentAreaFilled(false);
			rdbtnNewRadioButton_2.setBorderPainted(false);
			rdbtnNewRadioButton_2.setForeground(Color.white);
			contentPane.add(rdbtnNewRadioButton_2);
		}
		JRadioButton rdbtnNewRadioButton_3 = new JRadioButton("");
		;
		if (!(q.getAnswers().get(3).equals(""))) {
			rdbtnNewRadioButton_3.setText("<html>" + q.getAnswers().get(3) + "</html>");
			rdbtnNewRadioButton_3.setFont(new Font("Arial", Font.BOLD, 14));
			rdbtnNewRadioButton_3.setOpaque(false);
			rdbtnNewRadioButton_3.setContentAreaFilled(false);
			rdbtnNewRadioButton_3.setBorderPainted(false);
			rdbtnNewRadioButton_3.setForeground(Color.white);
			rdbtnNewRadioButton_3.setBounds(6, 335, 582, 60);
			contentPane.add(rdbtnNewRadioButton_3);
		}

		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnNewRadioButton);
		group.add(rdbtnNewRadioButton_1);
		group.add(rdbtnNewRadioButton_2);
		group.add(rdbtnNewRadioButton_3);

		JLabel lblNewLabel_2 = new JLabel("<html>" + q.getText() + "</html>");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(6, 94, 578, 50);
		contentPane.add(lblNewLabel_2);

		JButton btnNewButton = new RoundButton("");
		btnNewButton.setIcon(new ImageIcon(displayQuestion.class.getResource("/image/send-comment-48.png")));
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setBounds(238, 410, 89, 85);
		contentPane.add(btnNewButton);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(displayQuestion.class.getResource("/image/backgrounddd.jpeg")));
		lblNewLabel.setBounds(0, 0, 598, 524);
		contentPane.add(lblNewLabel);

		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ImageIcon icon = new ImageIcon("src/image/checkmark.png");

				if (rdbtnNewRadioButton.isSelected()) {
					GameController.getInstance().updatepointsafterquestion(g, q, "1");
					if (q.getCorrect_ans().equals("1")) {
						JOptionPane.showMessageDialog(null, "Correct answer", null, JOptionPane.INFORMATION_MESSAGE,
								icon);
					} else {
						JOptionPane.showMessageDialog(null, "Wrong answer", null, JOptionPane.ERROR_MESSAGE);
					}
					setVisible(false);
				} else if (rdbtnNewRadioButton_1.isSelected()) {
					GameController.getInstance().updatepointsafterquestion(g, q, "2");
					if (q.getCorrect_ans().equals("2")) {
						JOptionPane.showMessageDialog(null, "Correct answer", null, JOptionPane.INFORMATION_MESSAGE,
								icon);
					} else {
						JOptionPane.showMessageDialog(null, "Wrong answer", null, JOptionPane.ERROR_MESSAGE);
					}
					setVisible(false);
				} else if (rdbtnNewRadioButton_2.isSelected()) {
					GameController.getInstance().updatepointsafterquestion(g, q, "3");
					if (q.getCorrect_ans().equals("3")) {
						JOptionPane.showMessageDialog(null, "Correct answer", null, JOptionPane.INFORMATION_MESSAGE,
								icon);
					} else {
						JOptionPane.showMessageDialog(null, "Wrong answer", null, JOptionPane.ERROR_MESSAGE);
					}
					setVisible(false);
				} else if (rdbtnNewRadioButton_3.isSelected()) {
					GameController.getInstance().updatepointsafterquestion(g, q, "4");
					if (q.getCorrect_ans().equals("4")) {
						JOptionPane.showMessageDialog(null, "Correct answer", null, JOptionPane.INFORMATION_MESSAGE,
								icon);
					} else {
						JOptionPane.showMessageDialog(null, "Wrong answer", null, JOptionPane.ERROR_MESSAGE);
					}
					setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, "You have to choose an answer", null,
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		;

	}
}
