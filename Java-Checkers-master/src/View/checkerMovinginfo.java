package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JEditorPane;
import javax.swing.JScrollBar;

public class checkerMovinginfo extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public checkerMovinginfo() {
		setTitle("Hamka Game");
		setIconImage(Toolkit.getDefaultToolkit().getImage(mainScreen.class.getResource("/image/icon.jpeg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 892, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Checker Moving");
		lblNewLabel_1.setFont(new Font("Ravie", Font.PLAIN, 20));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(301, 11, 266, 34);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_4 = new JLabel("White Checker");
		lblNewLabel_4.setBounds(552, 153, 160, 14);
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Ravie", Font.PLAIN, 15));
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_2 = new JLabel("Black Checker");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Ravie", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(126, 143, 142, 35);
		contentPane.add(lblNewLabel_2);

		JButton btnNewButton = new RoundButton("");
		btnNewButton.setIcon(new ImageIcon(checkerMovinginfo.class.getResource("/image/blackChecker.png")));
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBounds(157, 59, 83, 73);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new RoundButton("");
		btnNewButton_1.setIcon(new ImageIcon(checkerMovinginfo.class.getResource("/image/down_2.png")));
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setBounds(198, 209, 89, 75);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new RoundButton("");
		btnNewButton_2.setIcon(new ImageIcon(checkerMovinginfo.class.getResource("/image/down _1.png")));
		btnNewButton_2.setBackground(Color.WHITE);
		btnNewButton_2.setBounds(108, 209, 89, 75);
		contentPane.add(btnNewButton_2);

		JLabel lblNewLabel_3 = new JLabel(" moving  down diagonally");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Ravie", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(73, 177, 345, 34);
		contentPane.add(lblNewLabel_3);

		JButton btnNewButton_3 = new RoundButton("");
		btnNewButton_3.setIcon(new ImageIcon(checkerMovinginfo.class.getResource("/image/whiteChecker.png")));
		btnNewButton_3.setBackground(Color.WHITE);
		btnNewButton_3.setBounds(583, 59, 83, 73);
		contentPane.add(btnNewButton_3);

		JButton btnNewButton_4 = new RoundButton("");
		btnNewButton_4.setIcon(new ImageIcon(checkerMovinginfo.class.getResource("/image/up_2.png")));
		btnNewButton_4.setBackground(Color.WHITE);
		btnNewButton_4.setBounds(552, 209, 89, 75);
		contentPane.add(btnNewButton_4);

		JButton btnNewButton_5 = new RoundButton("");
		btnNewButton_5.setIcon(new ImageIcon(checkerMovinginfo.class.getResource("/image/up_1.png")));
		btnNewButton_5.setBackground(Color.WHITE);
		btnNewButton_5.setBounds(644, 209, 89, 75);
		contentPane.add(btnNewButton_5);

		JLabel lblNewLabel_5 = new JLabel(" moving up diagonally");
		lblNewLabel_5.setBounds(531, 183, 321, 23);
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setFont(new Font("Ravie", Font.PLAIN, 15));
		contentPane.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("eating checker");
		lblNewLabel_6.setBounds(10, 295, 204, 34);
		lblNewLabel_6.setForeground(new Color(204, 0, 0));
		lblNewLabel_6.setFont(new Font("Ravie", Font.PLAIN, 18));
		contentPane.add(lblNewLabel_6);

		JButton btnNewButton_6 = new RoundButton("");
		btnNewButton_6.setFont(new Font("Ravie", Font.BOLD, 19));
		btnNewButton_6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/backk.png")));
		btnNewButton_6.setBackground(new Color(255, 240, 245));
		btnNewButton_6.setBounds(10, 11, 54, 48);
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

		JTextPane txtpnToCaptureAn = new JTextPane();
		txtpnToCaptureAn.setForeground(Color.WHITE);
		txtpnToCaptureAn.setText(
				"To capture an opponent's checker, you must jump over it when there is an empty tile beyond it, then you get 100 points.\r\n\r\nThe players must capture checkers if they have the opportunity. If they do not capture them, then their checker is out of the game.\r\n\r\nIf the player has more than one piece that can be captured by the same checker, he must keep jumping in the same turn.\r\n\r\nIt is not possible to capture checkers backwards.\r\n");
		txtpnToCaptureAn.setOpaque(false);
		txtpnToCaptureAn.setBounds(52, 340, 555, 220);
		txtpnToCaptureAn.setFont(new Font("Open Sans", Font.BOLD, 15));
		contentPane.add(txtpnToCaptureAn);

		JLabel lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setIcon(new ImageIcon(checkerMovinginfo.class.getResource("/image/checked.png")));
		lblNewLabel_7.setBounds(10, 352, 32, 32);
		contentPane.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setIcon(new ImageIcon(checkerMovinginfo.class.getResource("/image/checked.png")));
		lblNewLabel_8.setBounds(10, 471, 37, 34);
		contentPane.add(lblNewLabel_8);

		JLabel lblNewLabel_9 = new JLabel("");
		lblNewLabel_9.setIcon(new ImageIcon(checkerMovinginfo.class.getResource("/image/checked.png")));
		lblNewLabel_9.setBounds(10, 409, 46, 34);
		contentPane.add(lblNewLabel_9);

		JLabel lblNewLabel_10 = new JLabel("");
		lblNewLabel_10.setIcon(new ImageIcon(checkerMovinginfo.class.getResource("/image/checked.png")));
		lblNewLabel_10.setBounds(10, 527, 32, 34);
		contentPane.add(lblNewLabel_10);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(checkerMovinginfo.class.getResource("/image/background.jpeg")));
		lblNewLabel.setBounds(0, 0, 876, 561);
		contentPane.add(lblNewLabel);

	}
}
