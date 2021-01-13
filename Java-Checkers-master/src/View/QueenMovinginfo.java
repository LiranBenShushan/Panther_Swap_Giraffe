package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class QueenMovinginfo extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public QueenMovinginfo() {
		
		setTitle("Hamka Game");
		setIconImage(Toolkit.getDefaultToolkit().getImage(mainScreen.class.getResource("/image/icon.jpeg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 892, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Queen Moving");
		lblNewLabel_1.setFont(new Font("Ravie", Font.PLAIN, 20));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(301, 11, 266, 34);
		contentPane.add(lblNewLabel_1);

		JButton btnNewButton = new RoundButton("");
		btnNewButton.setIcon(new ImageIcon(QueenMovinginfo.class.getResource("/image/Crown2.png")));
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBounds(345, 56, 105, 95);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new RoundButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(checkerMovinginfo.class.getResource("/image/down_2.png")));
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setBounds(406, 255, 72, 61);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new RoundButton("");
		btnNewButton_2.setIcon(new ImageIcon(checkerMovinginfo.class.getResource("/image/down _1.png")));
		btnNewButton_2.setBackground(Color.WHITE);
		btnNewButton_2.setBounds(333, 255, 72, 61);
		contentPane.add(btnNewButton_2);

		JLabel lblNewLabel_3 = new JLabel(" moving down & up diagonally");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Ravie", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(266, 147, 345, 34);
		contentPane.add(lblNewLabel_3);

		JButton btnNewButton_4 = new RoundButton("");
		btnNewButton_4.setIcon(new ImageIcon(checkerMovinginfo.class.getResource("/image/up_2.png")));
		btnNewButton_4.setBackground(Color.WHITE);
		btnNewButton_4.setBounds(333, 192, 72, 61);
		contentPane.add(btnNewButton_4);

		JButton btnNewButton_5 = new RoundButton("");
		btnNewButton_5.setIcon(new ImageIcon(checkerMovinginfo.class.getResource("/image/up_1.png")));
		btnNewButton_5.setBackground(Color.WHITE);
		btnNewButton_5.setBounds(406, 192, 72, 61);
		contentPane.add(btnNewButton_5);

		JButton btnNewButton_6 = new RoundButton("");
		btnNewButton_6.setFont(new Font("Ravie", Font.BOLD, 19));
		btnNewButton_6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/backk.png")));
		btnNewButton_6.setBackground(new Color(255, 240, 245));
		btnNewButton_6.setBounds(10, 11, 48, 45);
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

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(QueenMovinginfo.class.getResource("/image/checked.png")));
		lblNewLabel_2.setBounds(10, 375, 55, 34);
		contentPane.add(lblNewLabel_2);

		JEditorPane dtrpnEatinganOpponents = new JEditorPane();
		dtrpnEatinganOpponents.setForeground(Color.WHITE);
		dtrpnEatinganOpponents.setOpaque(false);
		dtrpnEatinganOpponents.setFont(new Font("Open Sans", Font.BOLD, 15));
		dtrpnEatinganOpponents.setText(
				"The queen has another special ability and is a passage\r\n through walls, for example, a queen can pass\r\nThrough the right border of the game board and enter back into the board through the left border of the board");
		dtrpnEatinganOpponents.setBounds(57, 364, 533, 95);
		contentPane.add(dtrpnEatinganOpponents);
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(checkerMovinginfo.class.getResource("/image/background.jpeg")));
		lblNewLabel.setBounds(0, 0, 871, 556);
		contentPane.add(lblNewLabel);

	}
}
