/* Name: Game
 * Author: Devon McGrath
 * Description: This class represents a game of checkers. It provides a method
 * to update the game state and keep track of who's turn it is.
 */
package model;

import java.io.IOException;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observer;
import java.util.Random;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import Controller.GameController;
import Controller.MoveGenerator;
import Controller.MoveLogic;
import Controller.SysData;

/**
 * The {@code Game} class represents a game of checkers and ensures that all
 * moves made are valid as per the rules of checkers.
 */
public class Game implements Comparable<Game>, GameObserver {
	/** The current state of the checker board. */
	private ArrayList<GameObserver> observers;
	static int iscount = 0;
	private Board board;
	private String TotalTime;
	private String Winner;
	public static int Point1 = 0;
	public static int point2 = 0;
	private int maxPoint = 0;
	public static Point startPoint = new Point(0, 0);
	public static Point endPoint = new Point(0, 0);
	public static boolean Buttonclicked = false;
	private Player Player1;
	private Player player2;

	private static int Gamesecond = 0;
	private static int Gameminute = 0;
	static int ifRemove = -1;

	/** The flag indicating if it is player 1's turn. */
	private boolean isP1Turn;

	/** The index of the last skip, to allow for multiple skips in a turn. */
	private int skipIndex;

	public static ArrayList<Point> AvailableMoves = new ArrayList<Point>();
	public static HashMap<Point,ArrayList<Point>> AvailableCutMoves = new HashMap<>();

	public static Point redTile = new Point();
	private static Point greenTile = new Point();
	private static Point purpleTile = new Point();
	private static ArrayList<Point> yellowT = new ArrayList<Point>();
	private static ArrayList<Point> orangeT = new ArrayList<Point>();
	private static Point blueTile = new Point();
	/** index of the checker on the red tile */
	private static int endIn = -1;
	/** index chosen to return checker */
	private static int place = -1;

	/** checker to remove */
	public static int toRemove = -1;

	private AudioMaker ad;

	public Game(String player1, String player2)
			throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		ad = new AudioMaker();
		if (player1.equals("Computer")) {
			this.Player1 = new ComputerPlayer();
			this.player2 = new HumanPlayer(player2);
		} else if (player2.equals("Computer")) {
			this.player2 = new ComputerPlayer();
			this.Player1 = new HumanPlayer(player1);
		} else {
			this.Player1 = new HumanPlayer(player1);
			this.player2 = new HumanPlayer(player2);
		}
		this.observers = new ArrayList<GameObserver>();
		StartGame(this);

		restart();

	}

	private void notifyObservers() {
		System.out.println(observers.size());
		for (GameObserver gameObserver : observers) {
			gameObserver.update();
		}

	}

	public void StartGame(GameObserver observer) {
		observers.add(observer);
	}

	public Player getPlayer1() {
		return Player1;
	}

	public void setPlayer1(Player player1) {
		Player1 = player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	public Game(String state) {
		setGameState(state);
	}

	public Game(Board board, boolean isP1Turn, int skipIndex) {
		this.board = (board == null) ? new Board() : board;
		this.isP1Turn = isP1Turn;
		this.skipIndex = skipIndex;
	}

	public Game(String Winner, int maxPoints, String TotalTime) {
		this.Winner = Winner;
		this.maxPoint = maxPoints;
		this.TotalTime = TotalTime;

	}

	/**
	 * Creates a copy of this game such that any modifications made to one are not
	 * made to the other.
	 * 
	 * @return an exact copy of this game.
	 * @throws LineUnavailableException
	 * @throws IOException
	 * @throws UnsupportedAudioFileException
	 */
	public Game copy() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		Game g;
		if (Player1 instanceof ComputerPlayer) {
			g = new Game("Computer", ((HumanPlayer) player2).getName());
		} else if (player2 instanceof ComputerPlayer) {
			g = new Game(((HumanPlayer) Player1).getName(), "Computer");
		} else {
			g = new Game(((HumanPlayer) Player1).getName(), ((HumanPlayer) player2).getName());
		}
		g.board = board.copy();
		g.isP1Turn = isP1Turn;
		g.skipIndex = skipIndex;
		g.Winner = this.Winner;
		return g;
	}

	/**
	 * Resets the game of checkers to the initial state.
	 */
	public void restart() {
		this.board = new Board();
		this.isP1Turn = true;
		this.skipIndex = -1;

	}

	/**
	 * Attempts to make a move from the start point to the end point.
	 * 
	 * @param start the start point for the move.
	 * @param end   the end point for the move.
	 * @return true if and only if an update was made to the game state.
	 * @throws LineUnavailableException
	 * @throws IOException
	 * @throws UnsupportedAudioFileException
	 * @see {@link #move(int, int)}
	 */
	public boolean move(Point start, Point end)
			throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		iscount++;
		if (start == null || end == null) {
			return false;
		}
		return move(Board.toIndex(start), Board.toIndex(end), -1);
	}

	/**
	 * Attempts to make a move given the start and end index of the move.
	 * 
	 * @param startIndex the start index of the move.
	 * @param endIndex   the end index of the move.
	 * @return true if and only if an update was made to the game state.
	 * @throws LineUnavailableException
	 * @throws IOException
	 * @throws UnsupportedAudioFileException
	 * @see {@link #move(Point, Point)}
	 */

	public boolean move(int startIndex, int endIndex, int redtile)
			throws UnsupportedAudioFileException, IOException, LineUnavailableException {

		Move move = new Move(startIndex, endIndex);

		boolean flag = move.MoveLogic(board, startIndex, endIndex, this, redtile);
		if (flag && !GameController.getInstance().checkTheSound()) {
			ad.playMove();

		}
		return flag;
	}

	public AudioMaker getAd() {
		return ad;
	}

	public void setAd(AudioMaker ad) {
		this.ad = ad;
	}

	public boolean selectTile(int chosenTile) {
		boolean flag = false;
		if (!Board.isValidIndex(chosenTile) || board.get(chosenTile) != Board.EMPTY)
			return false;

		if (place != -1) {
			if (isP1Turn) {
				for (int i = 0; i < Board.N_Checkers; i++) {
					if (board.get(i) == Board.WHITE_CHECKER || board.get(i) == Board.WHITE_KING) {
						if ((Math.abs((Board.toPoint(i).x) - (Board.toPoint(chosenTile).x)) < 2
								&& Math.abs((Board.toPoint(i).y) - (Board.toPoint(chosenTile).y)) < 2 ) || ((Board.toPoint(chosenTile).y)==7)) {
							flag = true;
						} else
							continue;
					}
				}
				if (!flag) {
					board.set(chosenTile, Board.BLACK_CHECKER);
					place = -1;
					setP1Turn(false);
					return true;
				}

			} else {
				for (int i = 0; i < Board.N_Checkers; i++) {
					if (board.get(i) == Board.BLACK_CHECKER || board.get(i) == Board.BLACK_KING) {
						if ((Math.abs((Board.toPoint(i).x) - (Board.toPoint(chosenTile).x)) < 2
								&& Math.abs((Board.toPoint(i).y) - (Board.toPoint(chosenTile).y)) < 2 ) || ((Board.toPoint(chosenTile).y)==0)) {
							flag = true;

						} else
							continue;
					}
				}
				if (!flag) {
					board.set(chosenTile, Board.WHITE_CHECKER);
					place = -1;
					setP1Turn(true);
					return true;
				}
			}

		}
		return false;
	}

	public void yellowTiles() {
		yellowT = new ArrayList<Point>();
		List<Point> arrlist = new ArrayList<Point>();
		Random rand = new Random();
		arrlist = board.find(Board.EMPTY);
		if (arrlist.isEmpty() == false) {
			while (yellowT.size() < 3) {
				Point randomIndex = arrlist.get(rand.nextInt(arrlist.size()));
				if (!yellowT.contains(randomIndex)) {
					yellowT.add(randomIndex);
				}
			}
		}
		return;
	}

	public void redTiles() {
		if (isP1Turn()) {
			chooseTheRedTile(Board.BLACK_CHECKER, Board.BLACK_KING);

		} else {
			chooseTheRedTile(Board.WHITE_CHECKER, Board.WHITE_KING);
		}

	}

	private void chooseTheRedTile(int id1, int id2) {
		List<Point> arrlist = new ArrayList<Point>();
		ArrayList<Point> redT = new ArrayList<Point>();
		redTile.setLocation(-5, -5);
		Random rand = new Random();
		for (int i = 0; i < Board.N_Checkers; i++) {
			if (board.get(i) == id1 || board.get(i) == id2) {
				arrlist = MoveGenerator.getSkipsFirst(board, i);
				if (arrlist.isEmpty())
					continue;
				else
					break;

			}
		}
		if (arrlist.isEmpty()) {
			for (int i = 0; i < Board.N_Checkers; i++) {
				for (int j = 0; j < Board.N_Checkers; j++) {
					if (MoveLogic.isValidMove(this, i, j)) {
						if (!MoveGenerator.getMoves(board, j, board.get(i)).isEmpty()) {
							redT.add(Board.toPoint(j));
						}
					}

				}
			}
			if (!redT.isEmpty()) {
				redTile = redT.get(rand.nextInt(redT.size()));
				if (yellowT.contains(redTile))
					yellowT.remove(redTile);
			}
		}
		return;
	}

	public void blueTiles() {

		if (isP1Turn()) {
			chooseTheBlueTile(Board.BLACK_CHECKER, Board.BLACK_KING);
		} else {
			chooseTheBlueTile(Board.WHITE_CHECKER, Board.WHITE_KING);
		}
	}

	private void chooseTheBlueTile(int id1, int id2) {
		blueTile.setLocation(-5, -5);
		List<Integer> arrlist = new ArrayList<Integer>();
		Random rand = new Random();
		int countC = 0, countK = 0;
		for (int i = 0; i < Board.N_Checkers; i++) {
			if (board.get(i) == id1)
				countC++;
			if (board.get(i) == id2)
				countK++;
		}
		if (countC == 2 && countK == 1) {

			for (int i = 0; i < Board.N_Checkers; i++) {
				if (board.get(i) == Board.EMPTY) {
					arrlist.add(i);
				}
			}
			int randomIndex = arrlist.get(rand.nextInt(arrlist.size()));
			setBlueTile(Board.toPoint(randomIndex));
			if (yellowT.contains(Board.toPoint(randomIndex)))
				yellowT.remove(Board.toPoint(randomIndex));
			if (Board.toPoint(randomIndex).equals(redTile))
				redTile.setLocation(-5, -5);
		}
	}

	public void greenTiles() {

		if (isP1Turn()) {
			chooseTheGreenTile(Board.BLACK_CHECKER, Board.BLACK_KING);

		} else {

			chooseTheGreenTile(Board.WHITE_CHECKER, Board.WHITE_KING);
		}

	}

	private void chooseTheGreenTile(int id1, int id2) {
		ArrayList<Point> greenT = new ArrayList<Point>();
		greenTile.setLocation(-5, -5);
		Random rand = new Random();

		for (int i = 0; i < Board.N_Checkers; i++) {
			if (board.get(i) == id1 || board.get(i) == id2) {
				for (int j = 0; j < Board.N_Checkers; j++) {
					if (board.get(j) == Board.EMPTY) {
						if (MoveLogic.isValidMove(this, i, j)) {
							greenT.add(Board.toPoint(j));
						}
					}
				}
			}
		}
		if (!greenT.isEmpty()) {
			greenTile = greenT.get(rand.nextInt(greenT.size()));
			if (yellowT.contains(greenTile))
				yellowT.remove(greenTile);
			if (greenTile.equals(redTile))
				redTile.setLocation(-5, -5);
			if (greenTile.equals(blueTile))
				blueTile.setLocation(-5, -5);
		}
	}


	
	
	public void orangeTiles() {

		if (isP1Turn()) {
			chooseTheOrangeTiles(Board.BLACK_CHECKER, Board.BLACK_KING);
		} else {
			chooseTheOrangeTiles(Board.WHITE_CHECKER, Board.WHITE_KING);
		}
	}

	private void chooseTheOrangeTiles(int id1, int id2) {
		orangeT = new ArrayList<Point>();
		for (int i = 0; i < Board.N_Checkers; i++) {
			if (board.get(i) == id1 || board.get(i) == id2) {
				for (int j = 0; j < Board.N_Checkers; j++) {
					if (board.get(j) == Board.EMPTY) {
						if (MoveLogic.isValidMove(this, i, j)) {
							orangeT.add(Board.toPoint(j));
						}
					}
				}
			}
		}
	}

	

	/*-------------------------------------- Panther team --------------------------------------*/
	public void purpleTiles() {

		if (isP1Turn()) {
			chooseThePurpleTile(Board.BLACK_CHECKER, Board.BLACK_KING);
		} else {
			chooseThePurpleTile(Board.WHITE_CHECKER, Board.WHITE_KING);
		}
	}
	
	
	private void chooseThePurpleTile(int id1, int id2) {
		ArrayList<Point> purpleT = new ArrayList<Point>();
		purpleTile.setLocation(-5, -5);
		Random rand = new Random();

//		for (int i = 0; i < Board.N_Checkers; i++) {
//			if (board.get(i) == id1 || board.get(i) == id2) {
//				for (int j = 0; j < Board.N_Checkers; j++) {
//					if (board.get(j) == Board.EMPTY) {
//						if (MoveLogic.isValidMove(this, i, j)) {
//							purpleT.add(Board.toPoint(j));
//						}
//					}
//				}
//			}
//		}
		List<Point> arrlistPurple = new ArrayList<Point>();
		arrlistPurple = board.find(Board.EMPTY);
		System.out.println(arrlistPurple);
		if (!arrlistPurple.isEmpty()) {
			boolean purpleOkay = false;
			while(!purpleOkay) {
				Point potentialTile = arrlistPurple.get(rand.nextInt(arrlistPurple.size()));
				if (!yellowT.contains(potentialTile) && !potentialTile.equals(redTile) && !potentialTile.equals(blueTile) && !potentialTile.equals(greenTile)) {
					purpleTile = potentialTile;
					purpleOkay = true;
					return;
				}
			}
			System.out.println(purpleTile);
		}
		
		return;
		
//		if (!purpleT.isEmpty()) {
//			boolean purpleOkay = false;
//			while(!purpleOkay) {
//				purpleTile = purpleT.get(rand.nextInt(purpleT.size()));
//				if (!yellowT.contains(purpleTile)) {
//					purpleOkay = true;
//					return;
//				}
//				else if (!purpleTile.equals(redTile)) {
//					purpleOkay = true;
//					return;
//				}
//				else if (!purpleTile.equals(blueTile)) {
//					purpleOkay = true;
//					return;
//				}
//				else if (!purpleTile.equals(greenTile)) {
//					purpleOkay = true;
//					return;
//				}
//			}
//			
//		}
	}	
	
	/*-------------------------------------- End of panther team --------------------------------------*/
	
	
	public ArrayList<Point> AvailableMoves(int Startindex, int id) {
		if ((id == Board.BLACK_KING || id == Board.WHITE_KING) && Buttonclicked == false) {
			
			AvailableMoves = (ArrayList<Point>) MoveGenerator.getMoves(board, Startindex, id);
			AvailableMoves.addAll(MoveGenerator.getSkipsFirst(board, Startindex));

			for (int j = 0; j < 32; j++) {
				MoveLogic.getKingMoves(board, Startindex, j, this);
			}
			return AvailableMoves;
		} else {
			AvailableCutMoves.clear();
			AvailableMoves.clear();
			return AvailableMoves;

		}
	}

	/**
	 * Gets a copy of the current board state.
	 * 
	 * @return a non-reference to the current game board state.
	 */
	public Board getBoard() {
		return board.copy();
	}

	/**
	 * Determines if the game is over. The game is over if one or both players
	 * cannot make a single move during their turn.
	 * 
	 * @return true if the game is over.
	 * @throws LineUnavailableException
	 * @throws IOException
	 * @throws UnsupportedAudioFileException
	 */
	public boolean isGameOver() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		// Ensure there is at least one of each checker
		List<Point> black = board.find(Board.BLACK_CHECKER);
		black.addAll(board.find(Board.BLACK_KING));
		if (black.isEmpty()) {
			this.TotalTime = GameController.getInstance().getTotalTime();
			notifyObservers();
			SysData.addToHostory(this);
			ad.playGameover();
			return true;
		}
		List<Point> white = board.find(Board.WHITE_CHECKER);
		white.addAll(board.find(Board.WHITE_KING));
		if (white.isEmpty()) {
			this.TotalTime = GameController.getInstance().getTotalTime();
			notifyObservers();
			SysData.addToHostory(this);
			ad.playGameover();
			return true;
		}

		// Check if the current player can move
		List<Point> test = isP1Turn ? black : white;
		for (Point p : test) {
			int i = Board.toIndex(p);
			if (!MoveGenerator.getMoves(board, i, board.get(i)).isEmpty()
					|| !MoveGenerator.getSkipsFirst(board, i).isEmpty()) {
				return false;
			}
		}

		// No moves
		this.TotalTime = GameController.getInstance().getTotalTime();
		notifyObservers();
		SysData.addToHostory(this);
		ad.playGameover();
		GameController.getInstance().restartTimer(true);
		return true;
	}

	public boolean isP1Turn() {
		return isP1Turn;
	}

	public void setP1Turn(boolean isP1Turn) {

		this.isP1Turn = isP1Turn;

	}

	public int getSkipIndex() {
		return skipIndex;
	}

	/**
	 * Gets the current game state as a string of data that can be parsed by
	 * {@link #setGameState(String)}.
	 * 
	 * @return a string representing the current game state.
	 * @see {@link #setGameState(String)}
	 */
	public String getGameState() {

		// Add the game board
		String state = "";
		for (int i = 0; i < Board.N_Checkers; i++) {
			state += "" + board.get(i);
		}

		// Add the other info
		state += (isP1Turn ? "1" : "0");
		state += skipIndex;

		return state;
	}

	public boolean WriteGameState() {
		String state = "";
		for (int i = 0; i < Board.N_Checkers; i++) {
			if (i == 0) {
				if (board.get(i) == 4)
					state += 11;
				else if (board.get(i) == 3)
					state += 22;
				else if (board.get(i) != 3 && board.get(i) != 4)
					state += board.get(i);
			} else {
				if (board.get(i) == 4)
					state += "," + 11;
				else if (board.get(i) == 3)
					state += "," + 22;
				else if (board.get(i) != 3 && board.get(i) != 4)
					state += "," + board.get(i);

			}
		}

		// Add the other info
		state += "," + (isP1Turn ? "B" : "W");
		if (!SysData.WriteToFile(state))
			return false;
		else
			return true;
	}

	/**
	 * Parses a string representing a game state that was generated from
	 * {@link #getGameState()}.
	 * 
	 * @param state the game state.
	 * @see {@link #getGameState()}
	 */
	public void setGameState(String state) {

		restart();

		// Trivial cases
		if (state == null || state.isEmpty()) {
			return;
		}

		// Update the board
		int n = state.length();
		for (int i = 0; i < Board.N_Checkers && i < n; i++) {
			try {
				int id = Integer.parseInt("" + state.charAt(i));
				this.board.set(i, id);
			} catch (NumberFormatException e) {
			}
		}

		// Update the other info
		if (n > Board.N_Checkers) {
			this.isP1Turn = (state.charAt(Board.N_Checkers) == '1');
		}
		if (n > Board.N_Checkers + 1) {
			try {
				this.skipIndex = Integer.parseInt(state.substring(Board.N_Checkers + 1));
			} catch (NumberFormatException e) {
				this.skipIndex = -1;
			}
		}
	}

	public void SetLoadGame(String selected) {
		int data = 0;
		int k = 0;
		String[] array = selected.split(",");
		for (int i = 0, n = array.length; i < n; i++) {
			String character = array[i];
			if (!character.equals(",") && !character.equals("B") && !character.equals("W")) {
				data = Integer.parseInt(character);
				if (data != -1) {

					if (data == 11) {
						this.board.set(k, Board.WHITE_KING);
						k++;
					} else if (data == 22) {
						this.board.set(k, Board.BLACK_KING);
						k++;
					} else {
						this.board.set(k, data);
						k++;
					}
				}
			} else if (!character.equals("1") && !character.equals(",") && !character.equals("0")
					&& !character.equals("2") && !character.equals("11") && !character.equals("22")) {

				this.isP1Turn = (character.equals("B"));
			}

		}
		blueTiles();
		yellowTiles();
		redTiles();
		greenTile.setLocation(-5, -5);
		purpleTile.setLocation(-5, -5);
		orangeT = new ArrayList<Point>();
		GameController.getInstance().restartTimer(true);
		GameController.getInstance().restartTotalTimer(true);
		toRemove = -1;

	}

	public void setWinner() {
		if (Point1 > point2) {

			maxPoint = Point1;
			if (Player1 instanceof ComputerPlayer) {
				this.Winner = "Computer";

			} else
				this.Winner = ((HumanPlayer) Player1).getName();

		} else {
			maxPoint = point2;
			if (player2 instanceof ComputerPlayer) {
				this.Winner = "Computer";

			} else
				this.Winner = ((HumanPlayer) player2).getName();

		}

	}

	public void setPoints(int point, boolean flag) {
		if (isP1Turn) {
			if (flag) {
				this.Point1 += point;
			} else {
				this.Point1 -= point;

			}
		} else {

			if (flag) {

				this.point2 += point;

			} else {

				this.point2 -= point;

			}
		}
	}

	public int getPoint1() {
		return Point1;
	}

	public void setPoint1(int point1) {
		Point1 = point1;
	}

	public int getPoint2() {
		return point2;
	}

	public void setPoint2(int point2) {
		this.point2 = point2;
	}

	public ArrayList<Point> getYellowT() {
		return yellowT;
	}

	public void setYellowT(ArrayList<Point> yellowT) {
		Game.yellowT = yellowT;
	}

	public void setSkipIndex(int Skipindex) {
		this.skipIndex = Skipindex;
	}

	public int GetSkipIndex() {
		return skipIndex;
	}

	public void setEndIn(int Endin) {
		Game.endIn = Endin;
	}

	public Point getBlueTile() {
		return blueTile;
	}

	public void setBlueTile(Point blueTile) {
		Game.blueTile = blueTile;
	}

	public int getEndIn() {
		// TODO Auto-generated method stub
		return endIn;
	}

	public int getmaxpoint() {
		return maxPoint;
	}

	public String getWinner() {
		return this.Winner;
	}

	public String getTotalTime() {
		return this.TotalTime;
	}

	public Point getRedTile() {
		return redTile;
	}

	public void setRedTile(Point redTile) {
		this.redTile = redTile;
	}

	public Point getGreenTile() {
		return greenTile;
	}

	public Point getPurpleTile() {
		return purpleTile;
	}

	public static void setPurpleTile(Point purpleTile) {
		Game.purpleTile = purpleTile;
	}

	public void setGreenTile(Point greenTile) {
		Game.greenTile = greenTile;
	}

	public ArrayList<Point> getOrangeT() {
		return orangeT;
	}

	public void setOrangeT(ArrayList<Point> orangeT) {
		Game.orangeT = orangeT;
	}

	public int getPlace() {
		return place;
	}

	public void setPlace(int place) {
		Game.place = place;
	}

	@Override
	public int compareTo(Game game2) {
		if (game2.getmaxpoint() > this.maxPoint) {
			return 1;
		}
		if (game2.getmaxpoint() == this.maxPoint) {
			return 0;
		}
		return -1;
	}

	public void setPointAnswer(Question q1, String answer1) {

		if (answer1.equals(q1.getCorrect_ans())) {
			if (q1.getLevel().equals(Difficulty.easy)) {
				setPointsAfter(100, true);
			} else if (q1.getLevel().equals(Difficulty.mediocre)) {

				setPointsAfter(200, true);
			} else if (q1.getLevel().equals(Difficulty.difficult)) {

				setPointsAfter(500, true);
			}
		}

		else {

			if (q1.getLevel().equals(Difficulty.easy)) {
				setPointsAfter(250, false);
			} else if (q1.getLevel().equals(Difficulty.mediocre)) {

				setPointsAfter(100, false);
			} else if (q1.getLevel().equals(Difficulty.difficult)) {
				setPointsAfter(50, false);
			}
		}

	}

	private void setPointsAfter(int point, boolean flag) {
		if (isP1Turn) {
			if (flag) {
				this.point2 += point;
				GameController.getInstance().QMsg(this, point);
			} else {
				this.point2 -= point;
				GameController.getInstance().QMsg(this, -point);

			}
		} else {

			if (flag) {
				this.Point1 += point;
				GameController.getInstance().QMsg(this, point);
			} else {
				this.Point1 -= point;
				GameController.getInstance().QMsg(this, -point);

			}
		}
	}

	@Override
	public String toString() {
		return "Game [TotalTime=" + TotalTime + ", Winner=" + Winner + ", maxPoint=" + maxPoint + "]";
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		this.setWinner();
	}

}