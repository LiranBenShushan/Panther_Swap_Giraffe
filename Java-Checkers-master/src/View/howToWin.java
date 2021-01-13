package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class howToWin extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public howToWin() {
		setTitle("Hamka Game");
		setIconImage(Toolkit.getDefaultToolkit().getImage(mainScreen.class.getResource("/image/icon.jpeg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 892, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("How To Win ");
		lblNewLabel_1.setBounds(313, 11, 266, 34);
		lblNewLabel_1.setFont(new Font("Ravie", Font.PLAIN, 20));
		lblNewLabel_1.setForeground(Color.WHITE);
		contentPane.add(lblNewLabel_1);

		JButton btnNewButton = new RoundButton("");
		btnNewButton.setBounds(334, 71, 117, 106);
		btnNewButton.setIcon(new ImageIcon(howToWin.class.getResource("/image/Win.png")));
		btnNewButton.setBackground(Color.WHITE);
		contentPane.add(btnNewButton);

		JButton btnNewButton_6 = new RoundButton("");
		btnNewButton_6.setBounds(10, 11, 48, 45);
		btnNewButton_6.setFont(new Font("Ravie", Font.BOLD, 19));
		btnNewButton_6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/backk.png")));
		btnNewButton_6.setBackground(new Color(255, 240, 245));
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

		JEditorPane dtrpnTheTimeThat = new JEditorPane();
		dtrpnTheTimeThat.setOpaque(false);
		dtrpnTheTimeThat.setForeground(Color.WHITE);
		dtrpnTheTimeThat.setFont(new Font("Open Sans", Font.BOLD, 15));
		dtrpnTheTimeThat.setText(
				"The time that is allocated for each move is one minute. Player played faster than one minute, will get x \r\npoints, where x is the number of seconds remaining to one minute, and vice versa, player that exceeded\r\n the allocated time will lose x points, where x is the number of seconds past one minute.");
		dtrpnTheTimeThat.setBounds(53, 234, 813, 83);
		contentPane.add(dtrpnTheTimeThat);

		JEditorPane dtrpnEatinganOpponents = new JEditorPane();
		dtrpnEatinganOpponents.setBounds(53, 338, 656, 111);
		dtrpnEatinganOpponents.setForeground(Color.WHITE);
		dtrpnEatinganOpponents.setOpaque(false);
		dtrpnEatinganOpponents.setFont(new Font("Open Sans", Font.BOLD, 15));
		dtrpnEatinganOpponents.setText(
				"The game ends when one of the players has no more checkers on the\r\n board, or is unable to take any step because all of his checkers are blocked.\r\nThe winniner  is the player with the maximal points and not the player \r\nwho wins the checkers game itself");
		contentPane.add(dtrpnEatinganOpponents);

		JLabel lblNewLabel_2 = new JLabel("Move,Time&Points");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(10, 180, 217, 34);
		lblNewLabel_2.setFont(new Font("Ravie", Font.BOLD, 15));
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(howToWin.class.getResource("/image/checked.png")));
		lblNewLabel_3.setBounds(10, 247, 32, 32);
		contentPane.add(lblNewLabel_3);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(howToWin.class.getResource("/image/checked.png")));
		label.setBounds(10, 364, 32, 32);
		contentPane.add(label);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(0, 0, 876, 556);
		lblNewLabel.setIcon(new ImageIcon(checkerMovinginfo.class.getResource("/image/background.jpeg")));
		contentPane.add(lblNewLabel);

	}
}
