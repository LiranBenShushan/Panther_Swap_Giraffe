package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class PlayerVsComputer extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;

	/**
	 * Create the frame.
	 */
	public PlayerVsComputer() {
		setTitle("Hamka Game");
		setIconImage(Toolkit.getDefaultToolkit().getImage(mainScreen.class.getResource("/image/icon.jpeg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 908, 639);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		this.setResizable(false);
		contentPane.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Player VS Computer");
		lblNewLabel_1.setBounds(324, 33, 395, 54);
		lblNewLabel_1.setFont(new Font("Ravie", Font.PLAIN, 25));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		contentPane.add(lblNewLabel_1);

		JButton btnNewButton = new RoundButton("");
		btnNewButton.setBounds(28, 289, 98, 91);
		btnNewButton.setIcon(new ImageIcon(PlayerVsComputer.class.getResource("/image/blackuser.png")));
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setFont(new Font("Ravie", Font.BOLD, 19));
		contentPane.add(btnNewButton);

		JLabel lblNewLabel_3 = new JLabel("Black Player");
		lblNewLabel_3.setBounds(28, 403, 180, 34);
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Ravie", Font.PLAIN, 19));
		contentPane.add(lblNewLabel_3);

		JButton btnNewButton_2 = new RoundButton("");
		btnNewButton_2.setBounds(10, 11, 63, 59);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NewGame n = new NewGame();
				n.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(PlayerVsComputer.class.getResource("/image/backk.png")));
		btnNewButton_2.setBackground(new Color(255, 255, 255));
		btnNewButton_2.setForeground(Color.BLACK);
		btnNewButton_2.setFont(new Font("Ravie", Font.BOLD, 19));
		contentPane.add(btnNewButton_2);

		JButton btnNewButton_3 = new RoundButton("");
		btnNewButton_3.setBounds(514, 494, 71, 70);
		btnNewButton_3.setIcon(new ImageIcon(PlayerVsComputer.class.getResource("/image/right-direction.png")));
		btnNewButton_3.setBackground(new Color(255, 255, 255));
		btnNewButton_3.setForeground(Color.BLACK);
		btnNewButton_3.setFont(new Font("Ravie", Font.BOLD, 19));
		contentPane.add(btnNewButton_3);

		textField_1 = new JTextField();
		textField_1.setBounds(376, 326, 164, 32);
		textField_1.setFont(new Font("Ravie", Font.BOLD, 16));
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Choose Your Color : ");
		lblNewLabel_5.setBounds(28, 166, 254, 35);
		lblNewLabel_5.setForeground(new Color(255, 255, 255));
		lblNewLabel_5.setFont(new Font("Ravie", Font.PLAIN, 19));
		contentPane.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Player Name : ");
		lblNewLabel_6.setBounds(153, 324, 196, 32);
		lblNewLabel_6.setForeground(new Color(255, 255, 255));
		lblNewLabel_6.setFont(new Font("Ravie", Font.PLAIN, 19));
		contentPane.add(lblNewLabel_6);

		JButton btnNewButton_4 = new RoundButton("");
		btnNewButton_4.setBounds(826, 11, 56, 54);
		btnNewButton_4.setIcon(new ImageIcon(PlayerVsComputer.class.getResource("/image/infBuuton.PNG")));
		btnNewButton_4.setBackground(new Color(255, 255, 255));
		btnNewButton_4.setForeground(Color.BLACK);
		btnNewButton_4.setFont(new Font("Ravie", Font.BOLD, 19));
		contentPane.add(btnNewButton_4);

		JButton btnNewButton_1 = new RoundButton("Black");
		btnNewButton_1.setBounds(303, 146, 98, 91);
		btnNewButton_1.setFont(new Font("Ravie", Font.BOLD, 13));
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_5 = new RoundButton("White");
		btnNewButton_5.setBounds(432, 146, 98, 91);
		btnNewButton_5.setFont(new Font("Ravie", Font.BOLD, 13));
		btnNewButton_5.setBackground(new Color(255, 255, 255));
		contentPane.add(btnNewButton_5);

		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setBounds(0, 0, 892, 610);
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/image/background.jpeg")));
		contentPane.add(lblNewLabel);

		btnNewButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnNewButton.setIcon(new ImageIcon(PlayerVsComputer.class.getResource("/image/blackuser.png")));
				lblNewLabel_3.setText("Black Player");

			}
		});

		btnNewButton_5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnNewButton.setIcon(new ImageIcon(PlayerVsComputer.class.getResource("/image/whiteuser.png")));
				lblNewLabel_3.setText("White Player");

			}

		});

		btnNewButton_3.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String Name = textField_1.getText();
				if (Name.equals("")) {
					JOptionPane.showMessageDialog(null, "Enter Name For Player ", null, JOptionPane.ERROR_MESSAGE,
							null);

				}

				if (lblNewLabel_3.getText().equals("White Player")) {
					try {
						CheckersWindow window = new CheckersWindow("Computer", Name);
						window.setVisible(true);
						

						setVisible(false);
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

				if (lblNewLabel_3.getText().equals("Black Player")) {
					try {
						CheckersWindow window = new CheckersWindow(Name, "Computer");
						window.setVisible(true);
					
						setVisible(false);

					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

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
