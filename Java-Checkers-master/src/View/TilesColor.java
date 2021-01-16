package View;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TilesColor extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public TilesColor() {
		setTitle("Hamka Game");
		setIconImage(Toolkit.getDefaultToolkit().getImage(mainScreen.class.getResource("/image/icon.jpeg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 892, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Tiles Color");
		lblNewLabel_1.setFont(new Font("Ravie", Font.PLAIN, 25));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(289, 0, 370, 82);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_5 = new JLabel("Display Qustion");
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setFont(new Font("Open Sans", Font.BOLD, 15));
		lblNewLabel_5.setBounds(59, 65, 129, 27);
		contentPane.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel(
				"Returning a checker to life, if the player has only one queen and two checkers on the board");
		lblNewLabel_6.setForeground(Color.WHITE);
		lblNewLabel_6.setBounds(59, 88, 725, 56);
		lblNewLabel_6.setFont(new Font("Open Sans", Font.BOLD, 15));
		contentPane.add(lblNewLabel_6);

		JLabel lblNewLabel_3 = new JLabel(
				"If the player has not made a move within 30 seconds, a green tiles appears and it is possible move\r\n\t\t");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Open Sans", Font.BOLD, 15));

		lblNewLabel_3.setBounds(59, 141, 774, 26);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel(
				"If there is no eating option, a red square will appear from the possible places, beyond the square earns \r\n");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setBounds(61, 167, 807, 50);
		lblNewLabel_4.setFont(new Font("Open Sans", Font.BOLD, 15));

		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_7 = new JLabel("the player another step in the same turn with the same checker");
		lblNewLabel_7.setForeground(Color.WHITE);
		lblNewLabel_7.setBounds(59, 205, 533, 30);
		lblNewLabel_7.setFont(new Font("Open Sans", Font.BOLD, 15));

		contentPane.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel(
				"If the player has not made amove within 90 seconds, orange tiles appear in all possible tiles move");
		lblNewLabel_8.setForeground(Color.WHITE);
		lblNewLabel_8.setBounds(59, 244, 785, 26);
		lblNewLabel_8.setFont(new Font("Open Sans", Font.BOLD, 15));
		contentPane.add(lblNewLabel_8);

		JLabel lblNewLabel_2 = new JLabel("Update points according to tiles color");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(10, 355, 505, 37);
		lblNewLabel_2.setFont(new Font("Ravie", Font.PLAIN, 19));
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_9 = new JLabel(
				"<html> update points according to the answer (correct/worng) ,and to the question level ");
		lblNewLabel_9.setForeground(Color.WHITE);
		lblNewLabel_9.setBounds(59, 390, 650, 25);
		lblNewLabel_9.setFont(new Font("Open Sans", Font.BOLD, 15));
		contentPane.add(lblNewLabel_9);

		JLabel lblNewLabel_10 = new JLabel("Adding 50 points to player's score");
		lblNewLabel_10.setForeground(Color.WHITE);
		lblNewLabel_10.setBounds(59, 481, 533, 25);
		lblNewLabel_10.setFont(new Font("Open Sans", Font.BOLD, 15));
		contentPane.add(lblNewLabel_10);

		JButton btnNewButton_6 = new RoundButton("");
		btnNewButton_6.setFont(new Font("Ravie", Font.BOLD, 19));
		btnNewButton_6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/backk.png")));
		btnNewButton_6.setBackground(new Color(255, 240, 245));
		btnNewButton_6.setBounds(10, 11, 53, 43);
		contentPane.add(btnNewButton_6);
		btnNewButton_6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				infoScreen info = new infoScreen();
				info.setVisible(true);
			}
		});

		JLabel lblNewLabel_11 = new JLabel("");
		lblNewLabel_11.setOpaque(true);
		lblNewLabel_11.setBackground(Color.YELLOW);
		lblNewLabel_11.setBounds(10, 65, 39, 27);
		contentPane.add(lblNewLabel_11);

		JLabel label = new JLabel("");
		label.setOpaque(true);
		label.setBackground(new Color(0, 51, 255));
		label.setBounds(10, 103, 39, 27);
		contentPane.add(label);

		JLabel label_1 = new JLabel("");
		label_1.setOpaque(true);
		label_1.setBackground(new Color(51, 255, 0));
		label_1.setBounds(10, 141, 39, 27);
		contentPane.add(label_1);

		JLabel lblNewLabel_12 = new JLabel("");
		lblNewLabel_12.setOpaque(true);
		lblNewLabel_12.setBackground(new Color(255, 0, 0));
		lblNewLabel_12.setBounds(10, 179, 39, 27);
		contentPane.add(lblNewLabel_12);

		JLabel lblNewLabel_13 = new JLabel("");
		lblNewLabel_13.setOpaque(true);
		lblNewLabel_13.setBackground(Color.YELLOW);
		lblNewLabel_13.setBounds(10, 390, 39, 27);
		contentPane.add(lblNewLabel_13);

		JLabel lblNewLabel_14 = new JLabel("");
		lblNewLabel_14.setOpaque(true);
		lblNewLabel_14.setBackground(Color.ORANGE);
		lblNewLabel_14.setBounds(10, 243, 39, 27);
		contentPane.add(lblNewLabel_14);

		JLabel lblNewLabel_15 = new JLabel("");
		lblNewLabel_15.setBackground(Color.GREEN);
		lblNewLabel_15.setOpaque(true);
		lblNewLabel_15.setBounds(10, 479, 39, 27);
		contentPane.add(lblNewLabel_15);

		JLabel lblNewLabel_16 = new JLabel(
				"if the answer is correct and the question's level is easy the player get 100 points and if it worng answer the player lose 250 points ");
		lblNewLabel_16.setFont(new Font("Open Sans", Font.BOLD, 12));
		lblNewLabel_16.setForeground(Color.WHITE);
		lblNewLabel_16.setBounds(10, 416, 774, 27);
		contentPane.add(lblNewLabel_16);

		JLabel lblNewLabel_17 = new JLabel(
				"if the answer is correct and the question's level is mediocre the player get 200 points and if it worng answer the player lose 100 points ");
		lblNewLabel_17.setFont(new Font("Open Sans", Font.BOLD, 12));
		lblNewLabel_17.setForeground(Color.WHITE);
		lblNewLabel_17.setBounds(10, 439, 823, 14);
		contentPane.add(lblNewLabel_17);

		JLabel lblNewLabel_18 = new JLabel(
				"if the answer is correct and the question's level is diffecult the player get 200 points and if it worng answer the player lose 100 points ");
		lblNewLabel_18.setFont(new Font("Open Sans", Font.BOLD, 12));
		lblNewLabel_18.setForeground(Color.WHITE);
		lblNewLabel_18.setBounds(10, 454, 800, 14);
		contentPane.add(lblNewLabel_18);
		
		JLabel lblNewLabel_14_1 = new JLabel("");
		lblNewLabel_14_1.setOpaque(true);
		lblNewLabel_14_1.setBackground(new Color(139, 0, 139));
		lblNewLabel_14_1.setBounds(10, 281, 39, 27);
		contentPane.add(lblNewLabel_14_1);
		
		JLabel lblNewLabel_8_1 = new JLabel("If the player has not made a move within 45 seconds, purple tile will appear and suprise you with one of the following:");
		lblNewLabel_8_1.setForeground(Color.WHITE);
		lblNewLabel_8_1.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNewLabel_8_1.setBounds(59, 282, 926, 26);
		contentPane.add(lblNewLabel_8_1);
				
				JLabel lblNewLabel_8_1_1 = new JLabel("reset the checkers board (without changing the scores and timers), display question or adding 200 points");
				lblNewLabel_8_1_1.setForeground(Color.WHITE);
				lblNewLabel_8_1_1.setFont(new Font("Dialog", Font.BOLD, 15));
				lblNewLabel_8_1_1.setBounds(59, 306, 829, 26);
				contentPane.add(lblNewLabel_8_1_1);
						
						JLabel lblNewLabel_14_1_1 = new JLabel("");
						lblNewLabel_14_1_1.setOpaque(true);
						lblNewLabel_14_1_1.setBackground(new Color(139, 0, 139));
						lblNewLabel_14_1_1.setBounds(10, 512, 39, 27);
						contentPane.add(lblNewLabel_14_1_1);
						
						JLabel lblNewLabel_10_1 = new JLabel("If randomly chosen - adding 200 points to player's score");
						lblNewLabel_10_1.setForeground(Color.WHITE);
						lblNewLabel_10_1.setFont(new Font("Dialog", Font.BOLD, 15));
						lblNewLabel_10_1.setBounds(59, 514, 533, 25);
						contentPane.add(lblNewLabel_10_1);
						
								JLabel lblNewLabel = new JLabel("New label");
								lblNewLabel.setIcon(new ImageIcon(TilesColor.class.getResource("/image/background.jpeg")));
								lblNewLabel.setBounds(0, 0, 876, 572);
								contentPane.add(lblNewLabel);

	}
}
