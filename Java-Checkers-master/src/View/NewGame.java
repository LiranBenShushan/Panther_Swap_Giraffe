package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewGame extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public NewGame() {
		setTitle("Hamka Game");
		setIconImage(Toolkit.getDefaultToolkit().getImage(mainScreen.class.getResource("/image/icon.jpeg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 709, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setResizable(false);
		JButton btnNewButton = new RoundButton("Player VS Player");
		btnNewButton.setBackground(new Color(250, 128, 114));
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setFont(new Font("Ravie", Font.BOLD, 15));
		btnNewButton.setBounds(54, 26, 237, 207);
		contentPane.add(btnNewButton);

		JButton btnNewButton_2 = new RoundButton("Player VS Computer");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton_2.setBackground(new Color(250, 128, 114));
		btnNewButton_2.setBounds(95, 206, 278, 250);
		btnNewButton_2.setForeground(Color.BLACK);
		btnNewButton_2.setFont(new Font("Ravie", Font.BOLD, 15));
		contentPane.add(btnNewButton_2);

		JButton btnNewButton_3 = new RoundButton("");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				backActionPerformed(arg0);
			}
		});
		btnNewButton_3.setIcon(new ImageIcon(PlayerVsPlayer.class.getResource("/image/backk.png")));
		btnNewButton_3.setBackground(new Color(255, 255, 255));
		btnNewButton_3.setForeground(Color.BLACK);
		btnNewButton_3.setFont(new Font("Ravie", Font.BOLD, 19));
		btnNewButton_3.setBounds(10, 11, 63, 59);
		contentPane.add(btnNewButton_3);

		JButton btnNewButton_4 = new RoundButton("");
		btnNewButton_4.setIcon(new ImageIcon(PlayerVsPlayer.class.getResource("/image/infBuuton.PNG")));
		btnNewButton_4.setBackground(new Color(255, 255, 255));
		btnNewButton_4.setForeground(Color.BLACK);
		btnNewButton_4.setFont(new Font("Ravie", Font.BOLD, 19));
		btnNewButton_4.setBounds(626, 11, 56, 48);
		;
		contentPane.add(btnNewButton_4);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(NewGame.class.getResource("/image/NewGamwPic.jpeg")));
		lblNewLabel.setBounds(0, 0, 693, 456);
		contentPane.add(lblNewLabel);

		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				PlayerVsPlayer pvp = new PlayerVsPlayer();
				pvp.setVisible(true);
				setVisible(false);
			}
		});

		btnNewButton_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				PlayerVsComputer pvc = new PlayerVsComputer();
				pvc.setVisible(true);
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

	protected void backActionPerformed(ActionEvent arg0) {
		mainScreen m = new mainScreen();
		m.setVisible(true);
		this.setVisible(false);

	}
}
