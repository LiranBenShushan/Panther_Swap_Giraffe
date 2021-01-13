package View;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import Controller.SysData;
import model.Question;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Questions extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private String selectedQ = "";
	private DefaultTableModel model = new DefaultTableModel(new String[] { "Q #", "Question" }, 0);

	/**
	 * Create the frame.
	 */
	public Questions() {
		setTitle("Hamka Game");
		setIconImage(Toolkit.getDefaultToolkit().getImage(mainScreen.class.getResource("/image/icon.jpeg")));
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 908, 639);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setResizable(false);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(255, 255, 224));
		scrollPane.setOpaque(false);
		scrollPane.setFont(new Font("Arial", Font.BOLD, 20));
		table = new JTable();
		table.setSelectionForeground(Color.WHITE);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFillsViewportHeight(true);
		table.setForeground(new Color(0, 0, 0));
		table.setBackground(new Color(230, 230, 250));
		table.getTableHeader().setForeground(new Color(0, 0, 0));
		table.getTableHeader().setBackground(new Color(169, 169, 169));
		table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 20));
		table.setFont(new Font("Arial", Font.BOLD, 16));
		table.setSelectionBackground(new Color(250, 128, 114));
		table.setRowHeight(25);
		table.setIntercellSpacing(new Dimension(0, 0));
		table.setFocusable(false);
		scrollPane.setBounds(60, 126, 775, 265);
		scrollPane.setViewportView(table);

		contentPane.add(scrollPane);

		table.setModel(model);
		int i = 0;
		for (Question k : SysData.getQuestions()) {
			i++;
			String question = k.getText();
			Object[] ob = { i, question };
			model.addRow(ob);
		}
		table.setAutoResizeMode(table.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(671);

		JLabel lblNewLabel_1 = new JLabel("Questions Managment");
		lblNewLabel_1.setFont(new Font("Ravie", Font.BOLD, 30));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(209, 54, 482, 42);
		contentPane.add(lblNewLabel_1);

		JButton button = new RoundButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addQuestionActionPerformed(arg0);
			}
		});
		button.setFont(new Font("Ravie", Font.BOLD, 19));
		button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/add.png")));
		button.setBackground(new Color(255, 240, 245));
		button.setBounds(160, 432, 100, 100);
		contentPane.add(button);

		RoundButton button_1 = new RoundButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateQuestionActionPerformed(arg0);
			}
		});
		button_1.setFont(new Font("Ravie", Font.BOLD, 19));
		button_1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/refresh-button.png")));
		button_1.setBackground(new Color(255, 240, 245));
		button_1.setBounds(294, 432, 100, 100);
		contentPane.add(button_1);

		RoundButton button_2 = new RoundButton("");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				deleteQuestionActionPerformed(arg0);

			}
		});
		button_2.setFont(new Font("Ravie", Font.BOLD, 19));
		button_2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/delete.png")));
		button_2.setBackground(new Color(255, 240, 245));
		button_2.setBounds(432, 432, 100, 100);
		contentPane.add(button_2);

		RoundButton button_5 = new RoundButton("");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				logoutActionPerformed(arg0);
			}
		});

		button_5.setFont(new Font("Ravie", Font.BOLD, 19));
		button_5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/logout.png")));
		button_5.setBackground(new Color(255, 240, 245));
		button_5.setBounds(10, 10, 50, 50);
		contentPane.add(button_5);

		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/image/background.jpeg")));
		lblNewLabel.setBounds(0, 0, 894, 602);
		contentPane.add(lblNewLabel);

	}

	protected void logoutActionPerformed(ActionEvent arg0) {
		mainScreen m = new mainScreen();
		m.setVisible(true);
		this.setVisible(false);
	}

	protected void addQuestionActionPerformed(ActionEvent arg0) {
		AddQuestion aq = new AddQuestion();
		aq.setVisible(true);
		this.setVisible(false);
	}

	protected void deleteQuestionActionPerformed(ActionEvent arg0) {
		int j = table.getSelectedRow();

		if (j == -1) {
			JOptionPane.showMessageDialog(this, "please choose question", null, JOptionPane.ERROR_MESSAGE);

		} else {
			this.selectedQ = (String) table.getValueAt(j, 1);
			Question q = SysData.findQuestion(this.selectedQ);
			if (SysData.deleteQuestion(q)) {
				JOptionPane.showMessageDialog(this, "deleted Succefully");
				model.removeRow(j);
			}
		}
	}

	protected void updateQuestionActionPerformed(ActionEvent arg0) {
		int j = table.getSelectedRow();
		if (j == -1) {
			JOptionPane.showMessageDialog(this, "please choose question", null, JOptionPane.ERROR_MESSAGE);

		} else {
			this.selectedQ = (String) table.getValueAt(j, 1);
			Question q = SysData.findQuestion(this.selectedQ);
			updateQuestion uq = new updateQuestion(q);
			uq.setVisible(true);
			this.setVisible(false);
		}
	}

}
