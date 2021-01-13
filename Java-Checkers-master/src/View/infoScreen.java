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

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class infoScreen extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public infoScreen() {
		setTitle("Hamka Game");
		setIconImage(Toolkit.getDefaultToolkit().getImage(mainScreen.class.getResource("/image/icon.jpeg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 892, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Game information");
		lblNewLabel_1.setFont(new Font("Ravie", Font.PLAIN, 20));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(318, 35, 257, 42);
		contentPane.add(lblNewLabel_1);
		RoundButton rndbtnHowToWin = new RoundButton("");
		rndbtnHowToWin.setIcon(new ImageIcon(infoScreen.class.getResource("/image/win_1.png")));
		rndbtnHowToWin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				howToWin htw = new howToWin();
				htw.setVisible(true);
				setVisible(false);
			}
		});
		rndbtnHowToWin.setText("<html>How To Win</html>");
		rndbtnHowToWin.setFont(new Font("Ravie", Font.BOLD, 14));
		rndbtnHowToWin.setBackground(Color.WHITE);
		rndbtnHowToWin.setBounds(10, 190, 190, 190);
		contentPane.add(rndbtnHowToWin);
		
		JButton btnNewButton = new RoundButton("");
		btnNewButton.setFont(new Font("Ravie", Font.BOLD, 14));
		btnNewButton.setText("<html> Checker Moving </html>");
		btnNewButton.setIcon(new ImageIcon(infoScreen.class.getResource("/image/blackChecker.png")));
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBounds(458, 190, 200, 193);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_2 = new RoundButton("");
		btnNewButton_2.setFont(new Font("Ravie", Font.BOLD, 14));
		btnNewButton_2.setText("<html>Queen Moving</html>");
		btnNewButton_2.setIcon(new ImageIcon(infoScreen.class.getResource("/image/Crown2.png")));
		btnNewButton_2.setBackground(Color.WHITE);
		btnNewButton_2.setBounds(686, 190,190, 190);
		contentPane.add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				QueenMovinginfo qmi = new QueenMovinginfo();
				qmi.setVisible(true);
				setVisible(false);

			}
		});



		
		JButton btnNewButton_1 = new RoundButton("");
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.setFont(new Font("Ravie", Font.BOLD, 14));
		btnNewButton_1.setText("<html>Tile's Color</html>");
		btnNewButton_1.setIcon(new ImageIcon(infoScreen.class.getResource("/image/colors.png")));
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setBounds(237, 190, 190, 190);
		contentPane.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TilesColor tc = new TilesColor();
				tc.setVisible(true);
				setVisible(false);

			}
		});
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				checkerMovinginfo cmi = new checkerMovinginfo();
				cmi.setVisible(true);
				setVisible(false);

			}
		});
	
	

	
		RoundButton button_5 = new RoundButton("");
		button_5.setFont(new Font("Ravie", Font.BOLD, 19));
		button_5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/backk.png")));
		button_5.setBackground(new Color(255, 240, 245));
		button_5.setBounds(10, 10, 50, 50);
		contentPane.add(button_5);
		button_5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);

			}
		});

	

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/image/background.jpeg")));
		lblNewLabel.setBounds(0, 0, 876, 573);
		contentPane.add(lblNewLabel);

	}
}
