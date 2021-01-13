package View;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LogIn extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Create the frame.
	 */
	public LogIn() {
		setTitle("Hamka Game");
		setIconImage(Toolkit.getDefaultToolkit().getImage(mainScreen.class.getResource("/image/icon.jpeg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 499, 472);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setResizable(false);

		JLabel lblNewLabel_1 = new JLabel("<html>You Have to LogIn in Order to Manage Questions</html>");
		lblNewLabel_1.setFont(new Font("Ravie", Font.BOLD, 20));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(53, 39, 390, 87);
		contentPane.add(lblNewLabel_1);

		RoundButton button_2 = new RoundButton("");
		button_2.setFont(new Font("Ravie", Font.BOLD, 19));
		button_2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/user.png")));
		button_2.setBackground(new Color(255, 240, 245));
		button_2.setBounds(53, 147, 75, 75);
		contentPane.add(button_2);

		RoundButton button_3 = new RoundButton("");
		button_3.setFont(new Font("Ravie", Font.BOLD, 19));
		button_3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/pass.png")));
		button_3.setBackground(new Color(255, 240, 245));
		button_3.setBounds(53, 252, 78, 78);
		contentPane.add(button_3);

		RoundButton button_5 = new RoundButton("");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				backActionPerformed(arg0);
			}
		});

		button_5.setFont(new Font("Ravie", Font.BOLD, 19));
		button_5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/backk.png")));
		button_5.setBackground(new Color(255, 240, 245));
		button_5.setBounds(10, 10, 50, 50);
		contentPane.add(button_5);

		textField = new JTextField();
		textField.setFont(new Font("Arial", Font.BOLD, 16));
		textField.setBounds(163, 171, 163, 25);
		contentPane.add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(163, 276, 163, 25);
		contentPane.add(passwordField);

		JButton button_4 = new JButton("   Login ");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				loginActionPerformed(arg0);

			}
		});
		button_4.setFont(new Font("Ravie", Font.BOLD, 19));
		button_4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/loginn.png")));
		button_4.setBackground(new Color(255, 240, 245));
		button_4.setBounds(112, 351, 178, 46);
		contentPane.add(button_4);

		JLabel lblNewLabel_2 = new JLabel("<html>User name: Admin Password: Admin</html>");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setBounds(330, 200, 141, 75);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/image/backgrounddd.jpeg")));
		lblNewLabel.setBounds(0, 0, 485, 435);
		contentPane.add(lblNewLabel);

	}

	protected void backActionPerformed(ActionEvent arg0) {
		mainScreen m = new mainScreen();
		m.setVisible(true);
		this.setVisible(false);

	}

	protected void loginActionPerformed(ActionEvent arg0) {
		if (!textField.getText().equals("Admin") || !passwordField.getText().equals("Admin"))
			JOptionPane.showMessageDialog(this, "Please check your username and password again", null,
					JOptionPane.ERROR_MESSAGE);
		else {
			Questions q = new Questions();
			q.setVisible(true);
			this.setVisible(false);
		}

	}
}
