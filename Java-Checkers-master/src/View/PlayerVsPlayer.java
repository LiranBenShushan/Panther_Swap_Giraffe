package View;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.MoveLogic;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JTextField;

public class PlayerVsPlayer extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the frame.
	 */
	public PlayerVsPlayer() {
		setTitle("Hamka Game");
		setIconImage(Toolkit.getDefaultToolkit().getImage(mainScreen.class.getResource("/image/icon.jpeg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 908, 639);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setResizable(false);
		JLabel lblNewLabel_1 = new JLabel("Player VS Player ");
		lblNewLabel_1.setFont(new Font("Ravie", Font.PLAIN, 25));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(324, 33, 322, 54);
		contentPane.add(lblNewLabel_1);

		JButton btnNewButton = new RoundButton("");
		btnNewButton.setIcon(new ImageIcon(PlayerVsPlayer.class.getResource("/image/blackuser.png")));
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setFont(new Font("Ravie", Font.BOLD, 19));
		btnNewButton.setBounds(28, 108, 98, 91);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new RoundButton("");
		btnNewButton_1.setIcon(new ImageIcon(PlayerVsPlayer.class.getResource("/image/whiteuser.png")));
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.setFont(new Font("Ravie", Font.BOLD, 19));
		btnNewButton_1.setBounds(28, 379, 98, 91);
		contentPane.add(btnNewButton_1);

		JLabel lblNewLabel_2 = new JLabel("VS");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Ravie", Font.PLAIN, 25));
		lblNewLabel_2.setBounds(55, 303, 71, 35);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Balck Player");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Ravie", Font.PLAIN, 19));
		lblNewLabel_3.setBounds(28, 225, 180, 34);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("White Player");
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setFont(new Font("Ravie", Font.PLAIN, 19));
		lblNewLabel_4.setBounds(28, 494, 180, 35);
		contentPane.add(lblNewLabel_4);

		JButton btnNewButton_2 = new RoundButton("");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				backActionPerformed(arg0);
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(PlayerVsPlayer.class.getResource("/image/backk.png")));
		btnNewButton_2.setBackground(new Color(255, 255, 255));
		btnNewButton_2.setForeground(Color.BLACK);
		btnNewButton_2.setFont(new Font("Ravie", Font.BOLD, 19));
		btnNewButton_2.setBounds(10, 11, 63, 59);
		contentPane.add(btnNewButton_2);

		JButton btnNewButton_3 = new RoundButton("");
		btnNewButton_3.setIcon(new ImageIcon(PlayerVsPlayer.class.getResource("/image/right-direction.png")));
		btnNewButton_3.setBackground(new Color(255, 255, 255));
		btnNewButton_3.setForeground(Color.BLACK);
		btnNewButton_3.setFont(new Font("Ravie", Font.BOLD, 19));
		btnNewButton_3.setBounds(514, 494, 71, 70);
		contentPane.add(btnNewButton_3);

		textField = new JTextField();
		textField.setFont(new Font("Ravie", Font.BOLD, 16));
		textField.setBounds(350, 404, 164, 32);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Ravie", Font.BOLD, 16));
		textField_1.setBounds(350, 140, 164, 32);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Player Name");
		lblNewLabel_5.setForeground(new Color(255, 255, 255));
		lblNewLabel_5.setFont(new Font("Ravie", Font.PLAIN, 19));
		lblNewLabel_5.setBounds(166, 137, 189, 35);
		contentPane.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Player Name");
		lblNewLabel_6.setBounds(166, 402, 183, 32);
		lblNewLabel_6.setForeground(new Color(255, 255, 255));
		lblNewLabel_6.setFont(new Font("Ravie", Font.PLAIN, 19));
		contentPane.add(lblNewLabel_6);

		JButton btnNewButton_4 = new RoundButton("");
		btnNewButton_4.setIcon(new ImageIcon(PlayerVsPlayer.class.getResource("/image/infBuuton.PNG")));
		btnNewButton_4.setBackground(new Color(255, 255, 255));
		btnNewButton_4.setForeground(Color.BLACK);
		btnNewButton_4.setFont(new Font("Ravie", Font.BOLD, 19));
		btnNewButton_4.setBounds(826, 11, 56, 54);
		contentPane.add(btnNewButton_4);

		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/image/background.jpeg")));
		lblNewLabel.setBounds(5, 5, 882, 590);
		contentPane.add(lblNewLabel);

		btnNewButton_3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String blackName = textField_1.getText();
				String whiteName = textField.getText();
				if (blackName.equals("") || whiteName.equals("")) {
					JOptionPane.showMessageDialog(null, "Enter Name For Players ", null, JOptionPane.ERROR_MESSAGE);
				} else {
					CheckersWindow game = null;
					try {

						game = new CheckersWindow(blackName, whiteName);
						
						setVisible(false);

					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					game.setDefaultCloseOperation(CheckersWindow.EXIT_ON_CLOSE);
					game.setVisible(true);
				}
			}
		});

		btnNewButton_4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				infoScreen is = new infoScreen();
				is.setVisible(true);
				setVisible(true);
			}
		});

	}

	protected void backActionPerformed(ActionEvent arg0) {
		NewGame n = new NewGame();
		n.setVisible(true);
		this.setVisible(false);

	}

}
