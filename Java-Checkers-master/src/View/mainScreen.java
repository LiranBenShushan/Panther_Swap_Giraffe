package View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Toolkit;

public class mainScreen extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public mainScreen() {
		setTitle("Hamka Game");
		setIconImage(Toolkit.getDefaultToolkit().getImage(mainScreen.class.getResource("/image/icon.jpeg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 890, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setResizable(false);

		JButton btnNewButton = new RoundButton("New game");
		btnNewButton.setBackground(new Color(204, 51, 51));
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setFont(new Font("Ravie", Font.BOLD, 19));
		btnNewButton.setBounds(0, 0, 194, 171);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new RoundButton("History");
		btnNewButton_1.setBounds(128, 115, 161, 144);
		btnNewButton_1.setBackground(new Color(0, 0, 0));
		btnNewButton_1.setForeground(new Color(245, 245, 220));
		btnNewButton_1.setFont(new Font("Ravie", Font.BOLD, 19));
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_3 = new RoundButton("Exit");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btnNewButton_3.setBounds(200, 354, 124, 107);
		btnNewButton_3.setBackground(new Color(0, 0, 0));
		btnNewButton_3.setForeground(new Color(245, 245, 220));
		btnNewButton_3.setFont(new Font("Ravie", Font.BOLD, 19));
		contentPane.add(btnNewButton_3);

		JButton btnNewButton_2 = new RoundButton("Question Management");
		btnNewButton_2.setBackground(new Color(204, 51, 51));
		btnNewButton_2.setBounds(0, 182, 289, 256);
		btnNewButton_2.setForeground(Color.BLACK);
		btnNewButton_2.setFont(new Font("Ravie", Font.BOLD, 15));
		contentPane.add(btnNewButton_2);

		JButton btnNewButton_4 = new RoundButton("");
		btnNewButton_4.setIcon(new ImageIcon(PlayerVsPlayer.class.getResource("/image/infBuuton.PNG")));
		btnNewButton_4.setBackground(new Color(255, 255, 255));
		btnNewButton_4.setForeground(Color.BLACK);
		btnNewButton_4.setFont(new Font("Ravie", Font.BOLD, 19));
		btnNewButton_4.setBounds(807, 11, 56, 54);
		contentPane.add(btnNewButton_4);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(mainScreen.class.getResource("/image/background1.jpeg")));
		lblNewLabel.setBounds(0, 0, 884, 471);
		contentPane.add(lblNewLabel);

		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				NewGame nG = new NewGame();
				nG.setVisible(true);
				setVisible(false);
			}
		});

		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				History history = new History();
				history.setVisible(true);
				setVisible(false);
			}
		});

		btnNewButton_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				LogIn log = new LogIn();
				log.setVisible(true);
				setVisible(false);

			}
		});

		btnNewButton_4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				infoScreen is = new infoScreen();
				is.setVisible(true);

			}
		});

	}
}
