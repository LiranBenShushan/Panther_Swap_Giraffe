/* Name: CheckerBoard
 * Author: Devon McGrath
 * Description: This class is the graphical user interface representation of
 * a checkers game. It is responsible for drawing the checker board and
 * allowing moves to be made. It does not provide a method to allow the user to
 * change settings of the game or restart it.
 */
/* Name: CheckerBoard
 * Author: Devon McGrath
 * Description: This class is the graphical user interface representation of
 * a checkers game. It is responsible for drawing the checker board and
 * allowing moves to be made. It does not provide a method to allow the user to
 * change settings of the game or restart it.
 */
package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Set;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import Controller.MoveGenerator;
import Controller.GameController;
import model.Board;
import model.ComputerPlayer;
import model.Game;
import model.Move;
import model.Player;

/**
 * The {@code CheckerBoard} class is a graphical user interface component that
 * is capable of drawing any checkers game state. It also handles player turns.
 * For human players, this means interacting with and selecting tiles on the
 * checker board. For non-human players, this means using the logic implemented
 * by the specified player object itself is used.
 */
public class CheckerBoard extends JButton {
	private static final long serialVersionUID = -6014690893709316364L;

	/** The amount of milliseconds before a computer player takes a move. */
	private static final int TIMER_DELAY = 1000;

	/**
	 * The number of pixels of padding between this component's border and the
	 * actual checker board that is drawn.
	 */
	private static final int PADDING = 16;
	/** The game of checkers that is being played on this component. */
	private Game game;

	/** The window containing this checker board UI component. */
	private CheckersWindow window;

	/** The last point that the current player selected on the checker board. */
	private Point selected;

	/**
	 * The flag to determine the color of the selected tile. If the selection is
	 * valid, a green color is used to highlight the tile. Otherwise, a red color is
	 * used.
	 */
	private boolean selectionValid;

	/** The color of the light tiles (by default, this is white). */
	private Color lightTile;
	/** The color of the dark tiles (by default, this is black). */
	private Color darkTile;

	/** A convenience flag to check if the game is over. */
	private boolean isGameOver;

	/** The timer to control how fast a computer player makes a move. */
	private Timer timer;
	private static int count = 0;
	static BufferedImage crownImage = null;
	static BufferedImage crownImagewhite = null;
	static BufferedImage fireImage = null;

	public CheckerBoard(CheckersWindow window)
			throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		this(window, new Game(window.getPlayer1(), window.getPlayer2()));

	}

	/**
	 * @throws LineUnavailableException
	 * @throws IOException
	 * @throws UnsupportedAudioFileException
	 * @wbp.parser.constructor
	 */
	public CheckerBoard(CheckersWindow window, Game game)
			throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		// Setup the component
		super.setBorderPainted(false);
		super.setFocusPainted(false);
		super.setContentAreaFilled(false);
		super.setBackground(Color.LIGHT_GRAY);
		this.addActionListener(new ClickListener());
		// Setup the game
		this.game = (game == null) ? new Game(window.getPlayer1(), window.getPlayer2()) : game;
		this.lightTile = Color.WHITE;
		this.darkTile = Color.BLACK;
		this.window = window;
		game.yellowTiles();
		game.redTiles();
		game.blueTiles();
		game.getGreenTile().setLocation(-5, -5);
		game.getPurpleTile().setLocation(-5, -5);
	}

	/**
	 * Checks if the game is over and redraws the component graphics.
	 */
	public void update(boolean gameover) {
		runPlayer();
		this.isGameOver = gameover;
		repaint();
	}

	private void runPlayer() {

		boolean flag6 = false;
		// Nothing to do
		Player player = getCurrentPlayer();
		if (player == null || player.isHuman()) {
			return;
		}

		// Set a timer to run
		this.timer = new Timer(TIMER_DELAY, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					getCurrentPlayer().updateGame(game, Board.toIndex(game.getRedTile()));
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				timer.stop();
				try {
					update(game.isGameOver());

				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		this.timer.start();

	}

	public synchronized boolean setGameState(boolean testValue, String newState, String expected) {

		// Test the value if requested
		if (testValue && !game.getGameState().equals(expected)) {
			return false;
		}

		// Update the game state
		this.game.setGameState(newState);
		repaint();

		return true;
	}

	public void paintColors(Graphics g, int x, int y, Color c) {
		super.paint(g);

		final int W = getWidth(), H = getHeight();
		final int DIM = W < H ? W : H, BOX_SIZE = (DIM - 2 * PADDING) / Board.Board_Size;
		final int OFFSET_X = (W - BOX_SIZE * Board.Board_Size) / 2;
		final int OFFSET_Y = (H - BOX_SIZE * Board.Board_Size) / 2;

		g.setColor(c);
		g.fillRect(OFFSET_X + x * BOX_SIZE, OFFSET_Y + y * BOX_SIZE, BOX_SIZE, BOX_SIZE);

	}

	/**
	 * Draws the current checkers game state.
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);

		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		try {
			game = this.game.copy();
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		// Perform calculations
		final int BOX_PADDING = 4;
		final int W = getWidth(), H = getHeight();
		final int DIM = W < H ? W : H, BOX_SIZE = (DIM - 2 * PADDING) / Board.Board_Size;
		final int OFFSET_X = (W - BOX_SIZE * Board.Board_Size) / 2;
		final int OFFSET_Y = (H - BOX_SIZE * Board.Board_Size) / 2;
		final int CHECKER_SIZE = Math.max(0, BOX_SIZE - 2 * BOX_PADDING);

		// Draw checker board
		g.setColor(Color.BLACK);
		g.drawRect(OFFSET_X - 1, OFFSET_Y - 1, BOX_SIZE * Board.Board_Size + 1, BOX_SIZE * Board.Board_Size + 1);
		g.setColor(lightTile);
		g.fillRect(OFFSET_X, OFFSET_Y, BOX_SIZE * Board.Board_Size, BOX_SIZE * Board.Board_Size);
		g.setColor(darkTile);
		for (int y = 0; y < Board.Board_Size; y++) {
			for (int x = (y + 1) % 2; x < Board.Board_Size; x += 2) {
				g.fillRect(OFFSET_X + x * BOX_SIZE, OFFSET_Y + y * BOX_SIZE, BOX_SIZE, BOX_SIZE);
			}
		}

		// Highlight the selected tile if valid
		if (Board.isValidPoint(selected)) {
			g.setColor(selectionValid ? Color.DARK_GRAY : Color.PINK);
			g.fillRect(OFFSET_X + selected.x * BOX_SIZE, OFFSET_Y + selected.y * BOX_SIZE, BOX_SIZE, BOX_SIZE);
		}

		GameController.getInstance().yellowTiles(g, this, game);
		GameController.getInstance().redTile(g, this, game);
		GameController.getInstance().blueTile(g, this, game);
		GameController.getInstance().greenTile(g, this, game);
		GameController.getInstance().orangeTiles(g, this, game);
		GameController.getInstance().purpleTile(g, this, game);
		if (Game.toRemove != -1) {
			try {
				fireImage = ImageIO.read(new File("src/image/fire.png"));

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			g.drawImage(fireImage, OFFSET_X + 5 + Board.toPoint(Game.toRemove).x * BOX_SIZE,
					OFFSET_Y + 5 + Board.toPoint(Game.toRemove).y * BOX_SIZE, CHECKER_SIZE, CHECKER_SIZE, null);
		}

		// Draw the checkers
		Board b = game.getBoard();
		for (int y = 0; y < Board.Board_Size; y++) {

			int cy = OFFSET_Y + y * BOX_SIZE + BOX_PADDING;
			for (int x = (y + 1) % 2; x < Board.Board_Size; x += 2) {
				int id = b.get(x, y);
				int cx = OFFSET_X + x * BOX_SIZE + BOX_PADDING;
				// Empty, just skip
				if (id == Board.EMPTY) {
					continue;
				}

				// Black checker
				if (id == Board.BLACK_CHECKER) {
					g.setColor(Color.DARK_GRAY);
					g.fillOval(cx + 1, cy + 2, CHECKER_SIZE, CHECKER_SIZE);
					g.setColor(Color.LIGHT_GRAY);
					g.drawOval(cx + 1, cy + 2, CHECKER_SIZE, CHECKER_SIZE);
					g.setColor(Color.BLACK);
					g.fillOval(cx, cy, CHECKER_SIZE, CHECKER_SIZE);
					g.setColor(Color.LIGHT_GRAY);
					g.drawOval(cx, cy, CHECKER_SIZE, CHECKER_SIZE);
				}

				// Black king
				else if (id == Board.BLACK_KING) {
					g.setColor(Color.DARK_GRAY);
					g.fillOval(cx + 1, cy + 2, CHECKER_SIZE, CHECKER_SIZE);
					g.setColor(Color.LIGHT_GRAY);
					g.drawOval(cx + 1, cy + 2, CHECKER_SIZE, CHECKER_SIZE);
					g.setColor(Color.DARK_GRAY);
					g.fillOval(cx, cy, CHECKER_SIZE, CHECKER_SIZE);
					g.setColor(Color.LIGHT_GRAY);
					g.drawOval(cx, cy, CHECKER_SIZE, CHECKER_SIZE);
					g.setColor(Color.BLACK);
					g.fillOval(cx - 1, cy - 2, CHECKER_SIZE, CHECKER_SIZE);
				}

				// White checker
				else if (id == Board.WHITE_CHECKER) {
					g.setColor(Color.LIGHT_GRAY);
					g.fillOval(cx + 1, cy + 2, CHECKER_SIZE, CHECKER_SIZE);
					g.setColor(Color.DARK_GRAY);
					g.drawOval(cx + 1, cy + 2, CHECKER_SIZE, CHECKER_SIZE);
					g.setColor(Color.WHITE);
					g.fillOval(cx, cy, CHECKER_SIZE, CHECKER_SIZE);
					g.setColor(Color.DARK_GRAY);
					g.drawOval(cx, cy, CHECKER_SIZE, CHECKER_SIZE);
				}

				// White king
				else if (id == Board.WHITE_KING) {
					g.setColor(Color.LIGHT_GRAY);
					g.fillOval(cx + 1, cy + 2, CHECKER_SIZE, CHECKER_SIZE);
					g.setColor(Color.DARK_GRAY);
					g.drawOval(cx + 1, cy + 2, CHECKER_SIZE, CHECKER_SIZE);
					g.setColor(Color.LIGHT_GRAY);
					g.fillOval(cx, cy, CHECKER_SIZE, CHECKER_SIZE);
					g.setColor(Color.DARK_GRAY);
					g.drawOval(cx, cy, CHECKER_SIZE, CHECKER_SIZE);
					g.setColor(Color.WHITE);
					g.fillOval(cx - 1, cy - 2, CHECKER_SIZE, CHECKER_SIZE);
				}

				// Any king (add some extra highlights)
				if (id == Board.BLACK_KING || id == Board.WHITE_KING) {
					try {
						crownImage = ImageIO.read(new File("src/image/crown.png"));
						crownImagewhite = ImageIO.read(new File("src/image/WhiteCrown.png"));

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					g.setColor(new Color(255, 240, 0));
					g.drawOval(cx - 1, cy - 2, CHECKER_SIZE, CHECKER_SIZE);
					g.drawOval(cx + 1, cy, CHECKER_SIZE - 4, CHECKER_SIZE - 4);
					if (id == Board.BLACK_KING)
						g.drawImage(crownImagewhite, cx + 6, cy + 6, CHECKER_SIZE - 12, CHECKER_SIZE - 12, null);
					else
						g.drawImage(crownImage, cx + 6, cy + 6, CHECKER_SIZE - 12, CHECKER_SIZE - 12, null);

				}

			}
		}

		if (selected != null
				&& ((game.isP1Turn() && this.game.getBoard().get(Board.toIndex(selected)) == Board.BLACK_KING)
						|| (!game.isP1Turn() && this.game.getBoard().get(Board.toIndex(selected)) == Board.WHITE_KING))
				&& this.game.getPlace() == -1) {
			ArrayList<Point> points = this.game.AvailableMoves(Board.toIndex(selected),
					this.game.getBoard().get(selected.x, selected.y));
			for (int i = 0; i < points.size(); i++) {
				LineArrow line = new LineArrow(OFFSET_X + selected.x * BOX_SIZE + 25,
						OFFSET_Y + selected.y * BOX_SIZE + 25, OFFSET_X + points.get(i).x * BOX_SIZE + 25,
						OFFSET_Y + points.get(i).y * BOX_SIZE + 25, Color.green, 10);
				line.draw(g);
				JButton button = new JButton();
				button.setOpaque(false);
				button.setContentAreaFilled(false);
				button.setBorderPainted(false);
				button.setBounds(OFFSET_X + points.get(i).x * BOX_SIZE + 5, OFFSET_Y + points.get(i).y * BOX_SIZE + 5,
						35, 35);
				button.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						Move.isKingCutBorder = false;
						Game.Buttonclicked = true;
					}
				});

				this.add(button);

			}

			if (!Game.AvailableCutMoves.isEmpty()) {
				int counter=0;
				int value=-10;
				int rowvalue=0;
				int rowkey = 0;
				Set<Point> keys = Game.AvailableCutMoves.keySet();

				for (Point key : keys) {
					if(key.y ==7) {
						rowvalue=10;
					}else if(key.y ==0) {
						rowvalue=40;

					}
					if(Game.AvailableCutMoves.get(key).size()!=1) {
					for(Point val : Game.AvailableCutMoves.get(key)) {
						if(counter >=1) {
							value=25;
							if(key.y ==7) {
								rowvalue=40;
							}else if(key.y ==0) {
								rowvalue=10;
							}

						}
					LineArrow line = new LineArrow(OFFSET_X + val.x * BOX_SIZE + 25,
							OFFSET_Y + val.y * BOX_SIZE + 25,
							OFFSET_X + key.x * BOX_SIZE + rowvalue, OFFSET_Y + key.y * BOX_SIZE + rowvalue, Color.green, 10);
					line.draw(g);

					JButton button = new JButton();
					button.setOpaque(false);
					button.setContentAreaFilled(false);
					button.setBorderPainted(false);
					button.setBounds(OFFSET_X + key.x * BOX_SIZE +value, OFFSET_Y + key.y * BOX_SIZE + value, 35, 35);
					button.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							Move.isKingCutBorder = true;
							Game.Buttonclicked = true;
							
							if(key.y ==7) {
								if (val.y < 0 || val.y > 7) {
									Move.UpDown = true;

								}
								if (val.x < 0 || val.x > 7) {
									Move.LeftRight = true;

								}

							}else if(key.y ==0) {
								if (val.y < 0 || val.y > 7) {
									Move.LeftRight = true;
								}
								if (val.x < 0 || val.x > 7) {
									Move.UpDown = true;

								}
							}
							
						

						}
					});

					this.add(button);
					counter ++ ;
					}
					}else {
						LineArrow line = new LineArrow(OFFSET_X + Game.AvailableCutMoves.get(key).get(0).x * BOX_SIZE + 25,
								OFFSET_Y + Game.AvailableCutMoves.get(key).get(0).y * BOX_SIZE + 25,
								OFFSET_X + key.x * BOX_SIZE + 25, OFFSET_Y + key.y * BOX_SIZE + 25, Color.green, 10);
						line.draw(g);

						JButton button = new JButton();
						button.setOpaque(false);
						button.setContentAreaFilled(false);
						button.setBorderPainted(false);
						button.setBounds(OFFSET_X + key.x * BOX_SIZE +5, OFFSET_Y + key.y * BOX_SIZE + 5, 35, 35);
						button.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								Move.isKingCutBorder = true;
								Game.Buttonclicked = true;
								
								if (key.y == 0 || key.y == 7) {
									Move.UpDown = true;
								}
								if (key.x == 0 || key.x == 7) {
									Move.LeftRight = true;

								}

							}
						});

						this.add(button);
						
					}
				}
				
				value = 0;
				rowvalue=0;
			}
			Game.AvailableCutMoves.clear();

		}

		CheckersWindow.lblNewLabel_3.setText(game.Point1 + "");
		CheckersWindow.lblNewLabel_4.setText(game.point2 + "");

		if (this.getGame().isP1Turn()) {
			CheckersWindow.lblNewLabel_5.setText(window.getPlayer1() + "'s Turn");
			CheckersWindow.user.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/blackuser.png")));
		} else {
			CheckersWindow.lblNewLabel_5.setText(window.getPlayer2() + "'s Turn");
			CheckersWindow.user.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/whiteuser.png")));

		}

		// Draw a game over sign
		if (isGameOver) {
			g.setFont(new Font("Arial", Font.BOLD, 20));
			String msg = "Game Over!";
			String msg2 = "THE WINNER IS " + game.getWinner();
			int width = g.getFontMetrics().stringWidth(msg);
			int width2 = g.getFontMetrics().stringWidth(msg2);

			g.setColor(new Color(240, 240, 255));
			g.fillRoundRect(W / 2 - width / 2 - 5, OFFSET_Y + BOX_SIZE * 4 - 16, width + 10, 30, 10, 10);
			g.fillRoundRect(W / 2 - width2 / 2 - 5, OFFSET_Y + BOX_SIZE * 4 + 16, width2 + 10, 30, 10, 10);
			g.setColor(Color.RED);
			g.drawString(msg, W / 2 - width / 2, OFFSET_Y + BOX_SIZE * 4 + 7);
			g.drawString(msg2, W / 2 - width2 / 2, OFFSET_Y + BOX_SIZE * 4 + 40);
			if (count == 0) {
				count++;
				CheckersWindow.Totaltimer.stop();
				CheckersWindow.timer.stop();
			}
		}

	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		this.game = (game == null) ? new Game(window.getPlayer1(), window.getPlayer2()) : game;
	}

	public CheckersWindow getWindow() {
		return window;
	}

	public void setWindow(CheckersWindow window) {
		this.window = window;
	}

	public Player getCurrentPlayer() {
		return game.isP1Turn() ? game.getPlayer1() : game.getPlayer2();
	}

	public Color getLightTile() {
		return lightTile;
	}

	public void setLightTile(Color lightTile) {
		this.lightTile = (lightTile == null) ? Color.WHITE : lightTile;
	}

	public Color getDarkTile() {
		return darkTile;
	}

	public void setDarkTile(Color darkTile) {
		this.darkTile = (darkTile == null) ? Color.BLACK : darkTile;
	}

	/**
	 * Handles a click on this component at the specified point. If the current
	 * player is not human, this method does nothing. Otherwise, the selected point
	 * is updated and a move is attempted if the last click and this one both are on
	 * black tiles.
	 * 
	 * @param x the x-coordinate of the click on this component.
	 * @param y the y-coordinate of the click on this component.
	 * @throws LineUnavailableException
	 * @throws IOException
	 * @throws UnsupportedAudioFileException
	 */
	private void handleClick(int x, int y) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

		// The game is over or the current player isn't human
		if (isGameOver || !getCurrentPlayer().isHuman()) {
			return;
		}

		Game copy = game.copy();

		// Determine what square (if any) was selected
		final int W = getWidth(), H = getHeight();
		final int DIM = W < H ? W : H, BOX_SIZE = (DIM - 2 * PADDING) / Board.Board_Size;
		final int OFFSET_X = (W - BOX_SIZE * Board.Board_Size) / 2;
		final int OFFSET_Y = (H - BOX_SIZE * Board.Board_Size) / 2;
		x = (x - OFFSET_X) / BOX_SIZE;
		y = (y - OFFSET_Y) / BOX_SIZE;
		Point sel = new Point(x, y);

		// Determine if a move should be attempted
		if (Board.isValidPoint(sel) && Board.isValidPoint(selected)) {
			boolean change = copy.isP1Turn();
			String expected = copy.getGameState();
			boolean move = copy.move(selected, sel);

			boolean updated = (move ? setGameState(true, copy.getGameState(), expected) : false);
			change = (copy.isP1Turn() != change);
			this.selected = change ? null : sel;
		} else {
			this.selected = sel;
		}

		// Check if the selection is valid
		this.selectionValid = isValidSelection(copy.getBoard(), copy.isP1Turn(), selected);

		GameController.getInstance().gameOver(game, this);
	}

	private void handleChosenClick(int x, int y)
			throws UnsupportedAudioFileException, IOException, LineUnavailableException {

		// The game is over or the current player isn't human
		if (isGameOver || !getCurrentPlayer().isHuman()) {
			return;
		}

		Game copy = game.copy();
		// Determine what square (if any) was selected
		final int W = getWidth(), H = getHeight();
		final int DIM = W < H ? W : H, BOX_SIZE = (DIM - 2 * PADDING) / Board.Board_Size;
		final int OFFSET_X = (W - BOX_SIZE * Board.Board_Size) / 2;
		final int OFFSET_Y = (H - BOX_SIZE * Board.Board_Size) / 2;
		x = (x - OFFSET_X) / BOX_SIZE;
		y = (y - OFFSET_Y) / BOX_SIZE;
		Point sel = new Point(x, y);

		// Determine if a move should be attempted
		if (Board.isValidPoint(sel) && Board.isValidPoint(selected)) {
			boolean change = copy.isP1Turn();
			String expected = copy.getGameState();
			boolean move = copy.selectTile(Board.toIndex(sel));
			boolean updated = (move ? setGameState(true, copy.getGameState(), expected) : false);
			if (!move)
			this.window.lblNewLabel_9.setText("<html>please choose a place that is two steps away from the opponent's checkers</html>");
			change = (copy.isP1Turn() != change);
			this.selected = change ? null : sel;
		} else {
			this.selected = sel;
		}

		GameController.getInstance().gameOver(game, this);
	}

	/**
	 * Checks if a selected point is valid in the context of the current player's
	 * turn.
	 * 
	 * @param b        the current board.
	 * @param isP1Turn the flag indicating if it is player 1's turn.
	 * @param selected the point to test.
	 * @return true if and only if the selected point is a checker that would be
	 *         allowed to make a move in the current turn.
	 */
	private boolean isValidSelection(Board b, boolean isP1Turn, Point selected) {
		// Trivial cases
		int i = Board.toIndex(selected), id = b.get(i);
		if (id == Board.EMPTY || id == Board.INVALID) { // no checker here
			return false;
		} else if (isP1Turn ^ (id == Board.BLACK_CHECKER || id == Board.BLACK_KING)) { // wrong checker
			return false;
		} else if (!MoveGenerator.getSkips(b, i).isEmpty()) { // skip available
			return true;
		} else if (MoveGenerator.getMoves(b, i, b.get(i)).isEmpty() && b.get(i) != Board.BLACK_KING
				&& b.get(i) != Board.WHITE_KING) { // no moves
			return false;
		} else if (!GameController.getInstance().isOnRedTile(i, game)) {
			return false;
		}

		return true;
	}

	/**
	 * The {@code ClickListener} class is responsible for responding to click events
	 * on the checker board component. It uses the coordinates of the mouse relative
	 * to the location of the checker board component.
	 */
	private class ClickListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// Get the new mouse coordinates and handle the click
			Point m = CheckerBoard.this.getMousePosition();

			Point n = CheckerBoard.this.getMousePosition();
			if (game.getPlace() != -1) {
				if (n != null) {

					try {
						handleChosenClick(n.x, n.y);
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			} else if (m != null) {
				try {
					handleClick(m.x, m.y);
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		}
	}

}