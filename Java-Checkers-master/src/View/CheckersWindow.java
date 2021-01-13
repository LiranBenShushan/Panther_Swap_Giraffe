
package View;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.BorderLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controller.GameController;
import Controller.SysData;
import model.Game;
import model.GameObserver;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The {@code CheckersWindow} class is responsible for managing a window. This
 * window contains a game of checkers and also options to change the settings of
 * the game with an {@link OptionPanel}.
 */
public class CheckersWindow extends JFrame {

	private static final long serialVersionUID = 8782122389400590079L;

	/** The default width for the checkers window. */
	public static final int DEFAULT_WIDTH = 1000;

	/** The default height for the checkers window. */
	public static final int DEFAULT_HEIGHT = 700;

	/** The default title for the checkers window. */
	public static final String DEFAULT_TITLE = "Hamka Game";

	public static boolean soundFlag = false;

	private static boolean timerFlag = false;

	/** The checker board component playing the updatable game. */
	private CheckerBoard board;

	private JPanel contentPane;
	private JLabel timerLable = new JLabel();
	private JLabel TotalTimerLable = new JLabel();
	private JLabel turnTime = new JLabel("Turn Time");
	private JLabel totalTime = new JLabel("Total Time");
	private JTable LoadTable;
	private JButton restartBtn;
	private JButton Savebtn;
	private JButton Loadbtn;
	private static JButton pause;
	private JButton sound;
	public static JButton user;
	private JFrame f;
	static int second;
	static int minute;
	static Timer timer;
	static int Totalsecond;
	static int Totalminute;
	static Timer Totaltimer;
	public static JLabel lblNewLabel_3 = new JLabel("0");
	public static JLabel lblNewLabel_4 = new JLabel("0");
	private String Player1;
	private String Player2;
	public static JLabel lblNewLabel_5 = new JLabel("");
	public static JLabel lblNewLabel_7 = new JLabel("");
	public static JLabel lblNewLabel_9 = new JLabel("");

	/**
	 * @wbp.parser.constructor
	 */
	public CheckersWindow(String player1, String player2)
			throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		this(DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_TITLE, player1, player2);

	}

	public String getPlayer1() {
		return Player1;
	}

	public void setPlayer1(String player1) {
		Player1 = player1;
	}

	public String getPlayer2() {
		return Player2;
	}

	public void setPlayer2(String player2) {
		Player2 = player2;
	}

	public CheckersWindow(int width, int height, String title, String player1, String player2)
			throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		// Setup the window
		super(title);
		super.setSize(width, height);
		super.setLocationByPlatform(true);
		setTitle("Hamka Game");
		setIconImage(Toolkit.getDefaultToolkit().getImage(mainScreen.class.getResource("/image/icon.jpeg")));
		this.Player1 = player1;
		this.Player2 = player2;
		minute = 0;
		second = 0;
		Totalsecond = 0;
		Totalminute = 0;
		lblNewLabel_3 = new JLabel("0");
		lblNewLabel_4 = new JLabel("0");

		this.setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		simpleTimer();
		timer.start();
		simpleTotalTimer();
		Totaltimer.start();
		turnTime.setFont(new Font("Ravie", Font.BOLD, 20));
		turnTime.setForeground(Color.WHITE);

		totalTime.setFont(new Font("Ravie", Font.BOLD, 23));
		totalTime.setForeground(Color.WHITE);
		turnTime.setBounds(281, 605, 154, 30);
		contentPane.add(turnTime);
		timerLable.setForeground(Color.WHITE);
		timerLable.setFont(new Font("Ravie", Font.BOLD, 20));
		timerLable.setBounds(453, 605, 118, 30);
		contentPane.add(timerLable);

		totalTime.setBounds(625, 90, 177, 30);
		contentPane.add(totalTime);
		TotalTimerLable.setFont(new Font("Ravie", Font.BOLD, 23));
		TotalTimerLable.setForeground(Color.WHITE);

		TotalTimerLable.setBounds(663, 130, 97, 30);
		contentPane.add(TotalTimerLable);

		this.board = new CheckerBoard(this);
		board.setBounds(-98, 57, 733, 498);
		contentPane.add(board);
		this.Savebtn = new RoundButton("");
		Savebtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/import.png")));
		Savebtn.setFont(new Font("Ravie", Font.BOLD, 19));
		Savebtn.setBackground(new Color(255, 240, 245));
		Savebtn.setBounds(10, 612, 50, 50);
		contentPane.add(Savebtn);

		this.Loadbtn = new RoundButton("");
		Loadbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/export.png")));
		Loadbtn.setFont(new Font("Ravie", Font.BOLD, 19));
		Loadbtn.setBackground(new Color(255, 240, 245));
		Loadbtn.setBounds(60, 612, 50, 50);
		contentPane.add(Loadbtn);

		this.restartBtn = new RoundButton("");
		restartBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				restart();
				lblNewLabel_9.setText("");
				lblNewLabel_7.setText("");
				

			}
		});

		restartBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/restart.png")));
		restartBtn.setFont(new Font("Ravie", Font.BOLD, 19));
		restartBtn.setBackground(new Color(255, 240, 245));
		restartBtn.setBounds(110, 612, 50, 50);
		contentPane.add(restartBtn);

		this.sound = new RoundButton("");
		sound.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!soundFlag) {
					sound.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/mute.png")));
					board.getGame().getAd().pauseMove();
					soundFlag = true;
				} else {
					sound.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/sound.png")));
					soundFlag = false;
				}
			}
		});
		sound.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/sound.png")));
		sound.setBackground(new Color(255, 240, 245));
		sound.setBounds(922, 10, 50, 50);
		contentPane.add(sound);

		pause = new RoundButton("");
		pause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!timerFlag) {
					pause.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/play.png")));
					Totaltimer.stop();
					timer.stop();
					timerFlag = true;
				} else {
					pause.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/pause.png")));
					timerFlag = false;
					Totaltimer.start();
					timer.start();
				}

			}
		});
		pause.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/pause.png")));
		pause.setBackground(new Color(255, 240, 245));
		pause.setBounds(922, 70, 50, 50);
		contentPane.add(pause);

		Savebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (board.getGame().WriteGameState()) {
					showMessageDialog(null, "Succesfully saved the game state");
				} else {
					showMessageDialog(null, "SomeThing Wrong, Cant Save");

				}

			}
		});

		this.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				if (JOptionPane.showConfirmDialog(f, "Do you want to save this game?", "Really Closing?",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					if (board.getGame().WriteGameState()) {
						showMessageDialog(null, "Succesfully saved the game state");
					} else {
						showMessageDialog(null, "SomeThing Wrong, Cant Save");

					}
					System.exit(0);
				}
			}
		});

		Loadbtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					
					lblNewLabel_9.setText("");
					lblNewLabel_7.setText("");
					
					ArrayList<String> rowData = new ArrayList<String>();
					ArrayList<String> DataArray = SysData.setGameState();
					Object[][] array = null;
					int j = 0;
					int counter = 0;

					f = new JFrame();

					// Column Names
					String[] columnNames = { "State" };
					DefaultTableModel tableModel = new DefaultTableModel();
					tableModel.setColumnIdentifiers(columnNames);
					array = new Object[100][1];

					// Initializing the JTabl
					for (int h = 0; h < DataArray.size(); h++) {

						rowData.add(DataArray.get(h));
						if (DataArray.get(h).equals("W") || DataArray.get(h).equals("B")) {
							if (counter == j) {
								array[j][0] = rowData.get(0) + ",";

								for (int f = 1; f < rowData.size(); f++) {
									if (f != rowData.size() - 1)
										array[j][0] += rowData.get(f) + ",";
									else if (f == rowData.size() - 1)
										array[j][0] += rowData.get(f);
								}

							}
							counter++;
							j++;
							rowData.clear();
						}
					}

					LoadTable = new JTable(array, columnNames);

					LoadTable.addMouseListener(new java.awt.event.MouseAdapter() { // row is clicked
						public void mouseClicked(java.awt.event.MouseEvent evt) {
							board.getGame().Point1 = 0;
							board.getGame().point2 = 0;
							int selectedRowIndex = LoadTable.getSelectedRow();
							int selectedColumnIndex = LoadTable.getSelectedColumn();
							String selectedObject = (String) LoadTable.getModel().getValueAt(selectedRowIndex,
									selectedColumnIndex);
							board.getGame().SetLoadGame(selectedObject);
						}

					});

					JPanel topPanel;
					JPanel btnPanel;
					JScrollPane scrollPane;

					f.setTitle("Company Record Application");
					f.setSize(500, 200);
					f.setBackground(Color.gray);
					f.setVisible(true);

					topPanel = new JPanel();
					btnPanel = new JPanel();

					topPanel.setLayout(new BorderLayout());
					f.getContentPane().add(topPanel, BorderLayout.CENTER);
					f.getContentPane().add(btnPanel, BorderLayout.SOUTH);
					scrollPane = new JScrollPane(LoadTable);
					topPanel.add(scrollPane, BorderLayout.CENTER);
					JButton DoneButton = new JButton("Done");
					DoneButton.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
							// TODO Auto-generated method stub
							f.dispose();
						}
					});

					btnPanel.add(DoneButton);

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				repaint();
			}
		});

		JLabel lblNewLabel_1 = new JLabel(Player1 + "'s Points:");
		lblNewLabel_1.setFont(new Font("Ravie", Font.BOLD, 20));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(607, 205, 290, 30);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel(Player2 + "'s Points:");
		lblNewLabel_2.setFont(new Font("Ravie", Font.BOLD, 20));
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(607, 326, 290, 30);
		contentPane.add(lblNewLabel_2);

		lblNewLabel_3.setFont(new Font("Ravie", Font.BOLD, 20));
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setBounds(899, 207, 87, 27);
		contentPane.add(lblNewLabel_3);

		lblNewLabel_4.setFont(new Font("Ravie", Font.BOLD, 20));
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setBounds(899, 328, 87, 27);
		contentPane.add(lblNewLabel_4);

		RoundButton button_5 = new RoundButton("");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				backActionPerformed(arg0);
			}
		});

		button_5.setFont(new Font("Ravie", Font.BOLD, 19));
		button_5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/backk.png")));
		button_5.setBackground(new Color(255, 240, 245));
		button_5.setBounds(10, 10, 40, 40);
		contentPane.add(button_5);

		lblNewLabel_5.setFont(new Font("Ravie", Font.BOLD, 20));
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setBounds(291, 565, 254, 30);
		contentPane.add(lblNewLabel_5);

		JButton btnNewButton = new RoundButton("");
		btnNewButton.setIcon(new ImageIcon(PlayerVsPlayer.class.getResource("/image/blackuser.png")));
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setFont(new Font("Ravie", Font.BOLD, 19));
		btnNewButton.setBounds(531, 188, 70, 70);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new RoundButton("");
		btnNewButton_1.setIcon(new ImageIcon(PlayerVsPlayer.class.getResource("/image/whiteuser.png")));
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.setFont(new Font("Ravie", Font.BOLD, 19));
		btnNewButton_1.setBounds(531, 308, 70, 70);
		contentPane.add(btnNewButton_1);

		this.user = new RoundButton("");
		user.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/blackuser.png")));
		user.setBackground(new Color(255, 240, 245));
		user.setBounds(190, 565, 70, 70);
		contentPane.add(user);

		JLabel lblNewLabel_6 = new JLabel("HAMKA");
		lblNewLabel_6.setFont(new Font("Ravie", Font.BOLD, 30));
		lblNewLabel_6.setForeground(new Color(255, 51, 51));
		lblNewLabel_6.setBounds(428, 10, 207, 40);
		contentPane.add(lblNewLabel_6);
		lblNewLabel_7.setFont(new Font("Ravie", Font.BOLD, 15));
		lblNewLabel_7.setForeground(Color.WHITE);

		lblNewLabel_7.setBounds(664, 421, 295, 40);
		contentPane.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("REMARKS");
		lblNewLabel_8.setFont(new Font("Ravie", Font.BOLD, 15));
		lblNewLabel_8.setForeground(new Color(255, 51, 51));
		lblNewLabel_8.setBounds(746, 398, 118, 27);
		contentPane.add(lblNewLabel_8);
		lblNewLabel_6.setBounds(428, 10, 207, 36);
		contentPane.add(lblNewLabel_6);

		lblNewLabel_9.setFont(new Font("Ravie", Font.BOLD, 15));
		lblNewLabel_9.setForeground(Color.WHITE);
		lblNewLabel_9.setBounds(645, 472, 285, 76);
		contentPane.add(lblNewLabel_9);

		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/image/backgroundd.jpeg")));
		lblNewLabel.setBounds(0, 0, 996, 672);

		contentPane.add(lblNewLabel);

	}

	protected void backActionPerformed(ActionEvent arg0) {
		if (JOptionPane.showConfirmDialog(f, "Do you want to save this game?", "Really Closing?",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
			if (board.getGame().WriteGameState()) {
				showMessageDialog(null, "Succesfully saved the game state");
			} else {
				showMessageDialog(null, "SomeThing Wrong, Cant Save");

			}
		}

		NewGame n = new NewGame();
		n.setVisible(true);
		this.setVisible(false);
		timer.stop();
		Totaltimer.stop();

	}

	public CheckerBoard getBoard() {
		return board;
	}

	/**
	 * Resets the game of checkers in the window.
	 */
	public void restart() {
		this.board.getGame().restart();
		GameController.getInstance().gameOver(this.board.getGame(), board);
		this.board.getGame().blueTiles();
		this.board.getGame().yellowTiles();
		this.board.getGame().redTiles();
		this.board.getGame().getGreenTile().setLocation(-5, -5);
		this.board.getGame().setOrangeT(new ArrayList<Point>());
		this.board.getGame().Point1 = 0;
		this.board.getGame().point2 = 0;
		Game.toRemove = -1;
		CheckersWindow.restartTimmer(true);
		CheckersWindow.restartTotalTimmer(true);
	}

	public void setGameState(String state) {
		this.board.getGame().setGameState(state);
	}

	public void simpleTimer() {

		timer = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// TODO Auto-generated method stub
				second++;
				timerLable.setText(minute + ":" + second);

				if (second == 60) {
					minute++;
					second = 0;
				}
				timerLable.setText(minute + ":" + second);

				if (minute == 0 && second == 30) {
					board.getGame().greenTiles();
					repaint();
				}
				if (minute == 1 && second == 30) {
					board.getGame().orangeTiles();
					repaint();
				}

			}
		});
	}

	public void simpleTotalTimer() {

		Totaltimer = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// TODO Auto-generated method stub
				Totalsecond++;
				TotalTimerLable.setText(Totalminute + ":" + Totalsecond);
				if (Totalsecond == 60) {
					Totalminute++;
					Totalsecond = 0;
				}
				TotalTimerLable.setText(Totalminute + ":" + Totalsecond);

			}
		});
	}

	public static int restartTimmer(boolean timerRe) {

		int returnTotal = 0;
		returnTotal = minute * 60 + second;

		if (timerRe) {
			minute = 0;
			second = 0;
			timer.start();
			return returnTotal;

		} else {
			return returnTotal;
		}
	}

	public static void restart1() {
		minute = 0;
		second = 0;
		timer.start();
	}

	public static int restartTotalTimmer(boolean restart) {
		if (restart) {
			Totalminute = 0;
			Totalsecond = 0;
			Totaltimer.start();
			return 1;
		}
		return 0;

	}

	public static int restartTotalTimmerAfterPause() {
		if (!Totaltimer.isRunning()) {
			Totaltimer.start();
			pause.setIcon(new ImageIcon(CheckersWindow.class.getResource("/image/pause.png")));
			timerFlag = !timerFlag;
			return 1;
		}
		return 0;

	}

	public static String getTotalTimeG() {
		String returnMinute;
		String retturnSecond;
		returnMinute = Integer.toString(Totalminute);
		retturnSecond = Integer.toString(Totalsecond);
		return (returnMinute + ":" + retturnSecond);
	}

	public static void BMsg() {
		lblNewLabel_9.setFont(lblNewLabel_9.getFont().deriveFont(15.0f));
		lblNewLabel_9.setText("<html>YOU CAN RETURN ONE CHECKER TO THE GAME, PLEASE CHOOSE A PLACE</html>");

	}

	public static void RMsg() {
		lblNewLabel_9.setFont(lblNewLabel_9.getFont().deriveFont(15.0f));
		lblNewLabel_9.setText("<html>You are on red tile, you have another move with the same checker</html>");

	}

	public static void GMsg(int Points) {
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		if (50 + Points < 0)
			lblNewLabel_7.setText("<html>" + (50 + Points) + "</html>");
		else
			lblNewLabel_7.setText("<html>+" + (50 + Points) + "</html>");
		lblNewLabel_7.setFont(lblNewLabel_7.getFont().deriveFont(30.0f));

	}

	public static void PMsg(int point) {
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		System.out.println(point);
		if (100 + point < 0)
			lblNewLabel_7.setText("<html>" + (100 + point) + "</html>");
		else
			lblNewLabel_7.setText("<html>+" + (100 + point) + "</html>");

		lblNewLabel_7.setFont(lblNewLabel_7.getFont().deriveFont(30.0f));
	}

	public static void QMsg(int Points) {
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		if (Points > 0)
			lblNewLabel_7.setText("<html>+" + Points + "</html>");
		else
			lblNewLabel_7.setText("<html>" + Points + "</html>");
		lblNewLabel_7.setFont(lblNewLabel_7.getFont().deriveFont(30.0f));

	}

	public static void PointsMsg(int Points) {
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		if (Points > 0)
			lblNewLabel_7.setText("<html>+" + Points + "</html>");
		else
			lblNewLabel_7.setText("<html>" + Points + "</html>");
		lblNewLabel_7.setFont(lblNewLabel_7.getFont().deriveFont(30.0f));

	}
}
