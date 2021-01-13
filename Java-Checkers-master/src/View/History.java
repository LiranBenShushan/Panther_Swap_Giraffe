package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controller.SysData;
import model.Game;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class History extends JFrame {

	private JPanel contentPane;
	JTable table;

	/**
	 * Create the frame.
	 */
	public History() {
		setTitle("Hamka Game");
		setIconImage(Toolkit.getDefaultToolkit().getImage(mainScreen.class.getResource("/image/icon.jpeg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 908, 639);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setResizable(false);

		JLabel lblNewLabel_1 = new JLabel("History");
		lblNewLabel_1.setFont(new Font("Ravie", Font.PLAIN, 30));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(323, 46, 210, 54);
		contentPane.add(lblNewLabel_1);

		JButton btnNewButton_2 = new RoundButton("");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainScreen m = new mainScreen();
				m.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(PlayerVsComputer.class.getResource("/image/backk.png")));
		btnNewButton_2.setBackground(new Color(255, 255, 255));
		btnNewButton_2.setForeground(Color.BLACK);
		btnNewButton_2.setFont(new Font("Ravie", Font.BOLD, 19));
		btnNewButton_2.setBounds(10, 11, 63, 59);
		contentPane.add(btnNewButton_2);

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
		table.getTableHeader().setBackground(new Color(169, 169, 169));
		table.getTableHeader().setForeground(new Color(0, 0, 0));
		table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 20));
		table.setFont(new Font("Arial", Font.BOLD, 16));
		table.setSelectionBackground(new Color(250, 128, 114));
		table.setRowHeight(25);
		table.setIntercellSpacing(new Dimension(0, 0));
		table.setFocusable(false);
		scrollPane.setBounds(60, 126, 775, 287);

		scrollPane.setViewportView(table);
		contentPane.add(scrollPane);

		DefaultTableModel model = new DefaultTableModel(new String[] { "Player Name", "Score", "Total Time" }, 0);
		table.setModel(model);

		for (Game g : SysData.gethistory()) {
			String name = g.getWinner();
			int score = g.getmaxpoint();
			String TotalTime = g.getTotalTime();
			Object[] ob = { name, score, TotalTime };
			model.addRow(ob);
		}
		table.getColumnModel().getColumn(0).setPreferredWidth(5);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);

		JButton btnNewButton_4 = new RoundButton("");
		btnNewButton_4.setIcon(new ImageIcon(PlayerVsComputer.class.getResource("/image/infBuuton.PNG")));
		btnNewButton_4.setBackground(new Color(255, 255, 255));
		btnNewButton_4.setForeground(Color.BLACK);
		btnNewButton_4.setFont(new Font("Ravie", Font.BOLD, 19));
		btnNewButton_4.setBounds(826, 11, 56, 54);
		contentPane.add(btnNewButton_4);

		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/image/background.jpeg")));
		lblNewLabel.setBounds(5, 5, 882, 590);
		contentPane.add(lblNewLabel);
		
		btnNewButton_4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				infoScreen in=new infoScreen();
				in.setVisible(true);
			}
		});

	}

}
