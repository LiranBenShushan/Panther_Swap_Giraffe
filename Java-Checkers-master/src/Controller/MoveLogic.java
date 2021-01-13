/* Name: MoveLogic
* Author: Devon McGrath
* Description: This class simply validates moves.
*/
package Controller;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import View.CheckersWindow;
import model.Board;
import model.Game;
import model.Move;

/**
 * The {@code MoveLogic} class determines what a valid move is. It fully
 * implements all the rules of checkers.
 */
public class MoveLogic {
	/**
	 * Determines if the specified move is valid based on the rules of checkers.
	 * 
	 * @param game       the game to check against.
	 * @param startIndex the start index of the move.
	 * @param endIndex   the end index of the move.
	 * @return true if the move is legal according to the rules of checkers.
	 * @see {@link #isValidMove(Board, boolean, int, int, int)}
	 */
	public static boolean isValidMove(Game game, int startIndex, int endIndex) {
		return game == null ? false
				: isValidMove(game.getBoard(), game.isP1Turn(), startIndex, endIndex, game.getSkipIndex());
	}

	public static boolean isKingCutBorder(Board board, int startIndex, int endIndex) {
		if (board.get(startIndex) != Board.BLACK_KING && board.get(startIndex) != Board.WHITE_KING)
			return false;
		Point start = Board.toPoint(startIndex);
		Point end = Board.toPoint(endIndex);
		int dx = end.x - start.x;
		int dy = end.y - start.y;

		if (Math.abs(dx) + Math.abs(dy) == Board.Board_Size) {
			return true;
		}

		return false;
	}

	public static boolean isValidKingMove(Board board, int startIndex, int endIndex) {
		int counter = 0;
		int cross = 1;
		Point startKing = Board.toPoint(startIndex);
		Point endKing = Board.toPoint(endIndex);
		List<Point> checkers;
		if (board.get(startIndex) == Board.BLACK_KING) {
			checkers = board.find(Board.WHITE_CHECKER);
			checkers.addAll(board.find(Board.WHITE_KING));
		} else {
			checkers = board.find(Board.BLACK_CHECKER);
			checkers.addAll(board.find(Board.BLACK_KING));
		}
		for (Point p : checkers) {
			int dxc = p.x - startKing.x;
			int dyc = p.y - startKing.y;
			int dxl = endKing.x - startKing.x;
			int dyl = endKing.y - startKing.y;
			cross = dxc * dyl - dyc * dxl;
			if (cross == 0) {
				boolean flag = false;
				if (Math.abs(dxl) >= Math.abs(dyl))
					flag = dxl > 0 ? startKing.x <= p.x && p.x <= endKing.x : endKing.x <= p.x && p.x <= startKing.x;
				else
					flag = dyl > 0 ? startKing.y <= p.y && p.y <= endKing.y : endKing.y <= p.y && p.y <= startKing.y;
				if (flag == true) {
					counter += 1;
				}
			}
		}
		if (counter > 1) {
			return false;
		}
		return true;
	}

	/**
	 * Determines if the specified move is valid based on the rules of checkers.
	 * 
	 * @param board      the current board to check against.
	 * @param isP1Turn   the flag indicating if it is player 1's turn.
	 * @param startIndex the start index of the move.
	 * @param endIndex   the end index of the move.
	 * @param skipIndex  the index of the last skip this turn.
	 * @return true if the move is legal according to the rules of checkers.
	 * @see {@link #isValidMove(Game, int, int)}
	 */

	public static void getKingMoves(Board board, int startIndex, int endIndex, Game game) {
		if (MoveLogic.isKingCutBorder(board, startIndex, endIndex)) {
			Point startKing = Board.toPoint(startIndex);
			Point endKing = Board.toPoint(endIndex);

			int endKingBack_X = 0;
			int endkingBack_Y = 0;
			int startKingForward_X = 0;
			int startKingForward_Y = 0;
			int WhiteCounter = 0;
			int BlackCounter = 0;
			int Whiteindex = 0;
			int Blackindex = 0;
			Point StartArrow = null;
			Point EndArrow = null;

			if (endKing.x > startKing.x && endKing.y > startKing.y && board.get(endKing.x, endKing.y) == Board.EMPTY) {// ok
																														// left
																														// right
				endKingBack_X = endKing.x;
				endkingBack_Y = endKing.y;
				startKingForward_X = startKing.x - 1;
				startKingForward_Y = startKing.y + 1;
				if (board.get(startIndex) == Board.BLACK_KING) {
					while (endKingBack_X <= Board.Board_Size - 1 && endkingBack_Y > startKing.y) {
						if (board.get(endKingBack_X, endkingBack_Y) == Board.WHITE_CHECKER
								|| board.get(endKingBack_X, endkingBack_Y) == Board.WHITE_KING) {
							Whiteindex = Board.toIndex(endKingBack_X, endkingBack_Y);
							WhiteCounter++;
						}
						if (board.get(endKingBack_X, endkingBack_Y) == Board.BLACK_CHECKER
								|| board.get(endKingBack_X, endkingBack_Y) == Board.BLACK_KING)
							BlackCounter++;

						endKingBack_X++;
						endkingBack_Y--;
					}

					StartArrow = new Point(endKingBack_X, endkingBack_Y);
					EndArrow = new Point(endKingBack_X - 1, endkingBack_Y + 1);

				} else if (board.get(startIndex) == Board.WHITE_KING) {
					while (endKingBack_X <= Board.Board_Size - 1 && endkingBack_Y > startKing.y) {
						if (board.get(endKingBack_X, endkingBack_Y) == Board.BLACK_CHECKER
								|| board.get(endKingBack_X, endkingBack_Y) == Board.BLACK_KING) {
							Blackindex = Board.toIndex(endKingBack_X, endkingBack_Y);
							BlackCounter++;
						}
						if (board.get(endKingBack_X, endkingBack_Y) == Board.WHITE_CHECKER
								|| board.get(endKingBack_X, endkingBack_Y) == Board.WHITE_KING)
							WhiteCounter++;
						endKingBack_X++;
						endkingBack_Y--;
					}

					StartArrow = new Point(endKingBack_X, endkingBack_Y);
					EndArrow = new Point(endKingBack_X - 1, endkingBack_Y + 1);

				}

				if (board.get(startIndex) == Board.BLACK_KING) {
					while (startKingForward_X >= 0 && startKingForward_Y < endKing.y) {
						if (board.get(startKingForward_X, startKingForward_Y) == Board.WHITE_CHECKER
								|| board.get(startKingForward_X, startKingForward_Y) == Board.WHITE_KING) {
							Whiteindex = Board.toIndex(startKingForward_X, startKingForward_Y);
							WhiteCounter++;
						}
						if (board.get(startKingForward_X, startKingForward_Y) == Board.BLACK_CHECKER
								|| board.get(startKingForward_X, startKingForward_Y) == Board.BLACK_KING)
							BlackCounter++;

						startKingForward_X--;
						startKingForward_Y++;
					}

				} else if (board.get(startIndex) == Board.WHITE_KING) {
					while (startKingForward_X >= 0 && startKingForward_Y < endKing.y) {
						if (board.get(startKingForward_X, startKingForward_Y) == Board.BLACK_CHECKER
								|| board.get(startKingForward_X, startKingForward_Y) == Board.BLACK_KING) {
							Blackindex = Board.toIndex(startKingForward_X, startKingForward_Y);
							BlackCounter++;
						}
						if (board.get(startKingForward_X, startKingForward_Y) == Board.WHITE_CHECKER
								|| board.get(startKingForward_X, startKingForward_Y) == Board.WHITE_KING)
							WhiteCounter++;

						startKingForward_X--;
						startKingForward_Y++;
					}
				}

				boolean flag = checkTheStateBlack(Board.BLACK_KING, WhiteCounter, BlackCounter, board, Whiteindex,
						startIndex, endIndex, StartArrow, EndArrow)
						|| checkTheStateWhite(Board.WHITE_KING, WhiteCounter, BlackCounter, board, Blackindex,
								startIndex, endIndex, StartArrow, EndArrow);

				int endIndexTemp = -1;
				int startIndexTemp = -1;

				if (WhiteCounter == 0 && BlackCounter == 0) {
					endIndexTemp = endIndex;
					startIndexTemp = startIndex;
				}
				if (flag == false) {
					if (WhiteCounter != 0 || BlackCounter != 0) {
						WhiteCounter = 0;
						BlackCounter = 0;
					}
					// up down
					endKingBack_X = endKing.x;
					endkingBack_Y = endKing.y;
					startKingForward_X = startKing.x + 1;
					startKingForward_Y = startKing.y - 1;
					if (board.get(startIndex) == Board.BLACK_KING) {
						while (endkingBack_Y <= Board.Board_Size - 1 && endKingBack_X > startKing.x) {
							if (board.get(endKingBack_X, endkingBack_Y) == Board.WHITE_CHECKER
									|| board.get(endKingBack_X, endkingBack_Y) == Board.WHITE_KING) {
								Whiteindex = Board.toIndex(endKingBack_X, endkingBack_Y);
								WhiteCounter++;
							}
							if (board.get(endKingBack_X, endkingBack_Y) == Board.BLACK_CHECKER
									|| board.get(endKingBack_X, endkingBack_Y) == Board.BLACK_KING)
								BlackCounter++;

							endKingBack_X--;
							endkingBack_Y++;
						}

						StartArrow = new Point(endKingBack_X, endkingBack_Y);
						EndArrow = new Point(endKingBack_X + 1, endkingBack_Y - 1);
					} else if (board.get(startIndex) == Board.WHITE_KING) {
						while (endkingBack_Y <= Board.Board_Size - 1 && endKingBack_X > startKing.x) {
							if (board.get(endKingBack_X, endkingBack_Y) == Board.BLACK_CHECKER
									|| board.get(endKingBack_X, endkingBack_Y) == Board.BLACK_KING) {
								Blackindex = Board.toIndex(endKingBack_X, endkingBack_Y);
								BlackCounter++;
							}
							if (board.get(endKingBack_X, endkingBack_Y) == Board.WHITE_CHECKER
									|| board.get(endKingBack_X, endkingBack_Y) == Board.WHITE_KING)
								WhiteCounter++;

							endKingBack_X--;
							endkingBack_Y++;
						}

						StartArrow = new Point(endKingBack_X, endkingBack_Y);
						EndArrow = new Point(endKingBack_X + 1, endkingBack_Y - 1);

					}

					if (board.get(startIndex) == Board.BLACK_KING) {
						while (startKingForward_Y >= 0 && startKingForward_X < endKing.x) {
							if (board.get(startKingForward_X, startKingForward_Y) == Board.WHITE_CHECKER
									|| board.get(startKingForward_X, startKingForward_Y) == Board.WHITE_KING) {
								Whiteindex = Board.toIndex(startKingForward_X, startKingForward_Y);
								WhiteCounter++;
							}
							if (board.get(startKingForward_X, startKingForward_Y) == Board.BLACK_CHECKER
									|| board.get(startKingForward_X, startKingForward_Y) == Board.BLACK_KING)
								BlackCounter++;

							startKingForward_X++;
							startKingForward_Y--;
						}
					} else if (board.get(startIndex) == Board.WHITE_KING) {
						while (startKingForward_Y >= 0 && startKingForward_X < endKing.x) {
							if (board.get(startKingForward_X, startKingForward_Y) == Board.BLACK_CHECKER
									|| board.get(startKingForward_X, startKingForward_Y) == Board.BLACK_KING) {
								Blackindex = Board.toIndex(startKingForward_X, startKingForward_Y);
								BlackCounter++;
							}
							if (board.get(startKingForward_X, startKingForward_Y) == Board.WHITE_CHECKER
									|| board.get(startKingForward_X, startKingForward_Y) == Board.WHITE_KING)
								WhiteCounter++;

							startKingForward_X++;
							startKingForward_Y--;
						}
					}

				}

				flag = checkTheStateBlack(Board.BLACK_KING, WhiteCounter, BlackCounter, board, Whiteindex, startIndex,
						endIndex, StartArrow, EndArrow)
						|| checkTheStateWhite(Board.WHITE_KING, WhiteCounter, BlackCounter, board, Blackindex,
								startIndex, endIndex, StartArrow, EndArrow);

			}

			if (endKing.x < startKing.x && endKing.y > startKing.y && board.get(endKing.x, endKing.y) == Board.EMPTY) {// ok
																														// left
																														// right
				endKingBack_X = endKing.x;
				endkingBack_Y = endKing.y;
				startKingForward_X = startKing.x + 1;
				startKingForward_Y = startKing.y + 1;
				if (board.get(startIndex) == Board.BLACK_KING) {
					while (endKingBack_X >= 0 && endkingBack_Y > startKing.y) {
						if (board.get(endKingBack_X, endkingBack_Y) == Board.WHITE_CHECKER
								|| board.get(endKingBack_X, endkingBack_Y) == Board.WHITE_KING) {
							Whiteindex = Board.toIndex(endKingBack_X, endkingBack_Y);
							WhiteCounter++;
						}
						if (board.get(endKingBack_X, endkingBack_Y) == Board.BLACK_CHECKER
								|| board.get(endKingBack_X, endkingBack_Y) == Board.BLACK_KING)
							BlackCounter++;

						endKingBack_X--;
						endkingBack_Y--;
					}

					StartArrow = new Point(endKingBack_X, endkingBack_Y);
					EndArrow = new Point(endKingBack_X + 1, endkingBack_Y + 1);

				} else if (board.get(startIndex) == Board.WHITE_KING) {
					while (endKingBack_X >= 0 && endkingBack_Y > startKing.y) {
						if (board.get(endKingBack_X, endkingBack_Y) == Board.BLACK_CHECKER
								|| board.get(endKingBack_X, endkingBack_Y) == Board.BLACK_KING) {
							Blackindex = Board.toIndex(endKingBack_X, endkingBack_Y);
							BlackCounter++;
						}
						if (board.get(endKingBack_X, endkingBack_Y) == Board.WHITE_CHECKER
								|| board.get(endKingBack_X, endkingBack_Y) == Board.WHITE_KING)
							WhiteCounter++;

						endKingBack_X--;
						endkingBack_Y--;
					}
					StartArrow = new Point(endKingBack_X, endkingBack_Y);
					EndArrow = new Point(endKingBack_X + 1, endkingBack_Y + 1);
				}
				if (board.get(startIndex) == Board.BLACK_KING) {
					while (startKingForward_X <= Board.Board_Size - 1 && startKingForward_Y < endKing.y) {
						if (board.get(startKingForward_X, startKingForward_Y) == Board.WHITE_CHECKER
								|| board.get(startKingForward_X, startKingForward_Y) == Board.WHITE_KING) {
							Whiteindex = Board.toIndex(startKingForward_X, startKingForward_Y);
							WhiteCounter++;
						}
						if (board.get(startKingForward_X, startKingForward_Y) == Board.BLACK_CHECKER
								|| board.get(startKingForward_X, startKingForward_Y) == Board.BLACK_KING)
							BlackCounter++;

						startKingForward_X++;
						startKingForward_Y++;
					}
				} else if (board.get(startIndex) == Board.WHITE_KING) {
					while (startKingForward_X <= Board.Board_Size - 1 && startKingForward_Y < endKing.y) {
						if (board.get(startKingForward_X, startKingForward_Y) == Board.BLACK_CHECKER
								|| board.get(startKingForward_X, startKingForward_Y) == Board.BLACK_KING) {
							Blackindex = Board.toIndex(startKingForward_X, startKingForward_Y);
							BlackCounter++;
						}
						if (board.get(startKingForward_X, startKingForward_Y) == Board.WHITE_CHECKER
								|| board.get(startKingForward_X, startKingForward_Y) == Board.WHITE_KING)
							WhiteCounter++;

						startKingForward_X++;
						startKingForward_Y++;
					}
				}

				boolean flag = checkTheStateBlack(Board.BLACK_KING, WhiteCounter, BlackCounter, board, Whiteindex,
						startIndex, endIndex, StartArrow, EndArrow)
						|| checkTheStateWhite(Board.WHITE_KING, WhiteCounter, BlackCounter, board, Blackindex,
								startIndex, endIndex, StartArrow, EndArrow);

				boolean flag2 = false;
				int endIndexTemp = -1;
				int startIndexTemp = -1;

				if (WhiteCounter == 0 && BlackCounter == 0) {
					flag2 = true;
					endIndexTemp = endIndex;
					startIndexTemp = startIndex;
				}

				if (flag == false) {
					if (WhiteCounter != 0 || BlackCounter != 0) {
						WhiteCounter = 0;
						BlackCounter = 0;
					}
					// up down
					endKingBack_X = endKing.x;
					endkingBack_Y = endKing.y;
					startKingForward_X = startKing.x - 1;
					startKingForward_Y = startKing.y - 1;
					if (board.get(startIndex) == Board.BLACK_KING) {
						while (endkingBack_Y <= Board.Board_Size - 1 && endKingBack_X < startKing.x) {
							if (board.get(endKingBack_X, endkingBack_Y) == Board.WHITE_CHECKER
									|| board.get(endKingBack_X, endkingBack_Y) == Board.WHITE_KING) {
								Whiteindex = Board.toIndex(endKingBack_X, endkingBack_Y);
								WhiteCounter++;
							}
							if (board.get(endKingBack_X, endkingBack_Y) == Board.BLACK_CHECKER
									|| board.get(endKingBack_X, endkingBack_Y) == Board.BLACK_KING)
								BlackCounter++;

							endKingBack_X++;
							endkingBack_Y++;
						}
						StartArrow = new Point(endKingBack_X, endkingBack_Y);
						EndArrow = new Point(endKingBack_X - 1, endkingBack_Y - 1);
					} else if (board.get(startIndex) == Board.WHITE_KING) {
						while (endkingBack_Y <= Board.Board_Size - 1 && endKingBack_X < startKing.x) {
							if (board.get(endKingBack_X, endkingBack_Y) == Board.BLACK_CHECKER
									|| board.get(endKingBack_X, endkingBack_Y) == Board.BLACK_KING) {
								Blackindex = Board.toIndex(endKingBack_X, endkingBack_Y);
								BlackCounter++;
							}
							if (board.get(endKingBack_X, endkingBack_Y) == Board.WHITE_CHECKER
									|| board.get(endKingBack_X, endkingBack_Y) == Board.WHITE_KING)
								WhiteCounter++;

							endKingBack_X++;
							endkingBack_Y++;
						}
						StartArrow = new Point(endKingBack_X, endkingBack_Y);
						EndArrow = new Point(endKingBack_X - 1, endkingBack_Y - 1);
					}
					if (board.get(startIndex) == Board.BLACK_KING) {
						while (startKingForward_Y >= 0 && startKingForward_X > endKing.x) {
							if (board.get(startKingForward_X, startKingForward_Y) == Board.WHITE_CHECKER
									|| board.get(startKingForward_X, startKingForward_Y) == Board.WHITE_KING) {
								Whiteindex = Board.toIndex(startKingForward_X, startKingForward_Y);
								WhiteCounter++;
							}
							if (board.get(startKingForward_X, startKingForward_Y) == Board.BLACK_CHECKER
									|| board.get(startKingForward_X, startKingForward_Y) == Board.BLACK_KING)
								BlackCounter++;

							startKingForward_X--;
							startKingForward_Y--;
						}
					} else if (board.get(startIndex) == Board.WHITE_KING) {
						while (startKingForward_Y >= 0 && startKingForward_X > endKing.x) {
							if (board.get(startKingForward_X, startKingForward_Y) == Board.BLACK_CHECKER
									|| board.get(startKingForward_X, startKingForward_Y) == Board.BLACK_KING) {
								Blackindex = Board.toIndex(startKingForward_X, startKingForward_Y);
								BlackCounter++;
							}
							if (board.get(startKingForward_X, startKingForward_Y) == Board.WHITE_CHECKER
									|| board.get(startKingForward_X, startKingForward_Y) == Board.WHITE_KING)
								WhiteCounter++;

							startKingForward_X--;
							startKingForward_Y--;
						}
					}
				}

				flag = checkTheStateBlack(Board.BLACK_KING, WhiteCounter, BlackCounter, board, Whiteindex, startIndex,
						endIndex, StartArrow, EndArrow)
						|| checkTheStateWhite(Board.WHITE_KING, WhiteCounter, BlackCounter, board, Blackindex,
								startIndex, endIndex, StartArrow, EndArrow);

			}

			if (endKing.x < startKing.x && endKing.y < startKing.y && board.get(endKing.x, endKing.y) == Board.EMPTY) {// ok
																														// left
																														// right
				endKingBack_X = endKing.x;
				endkingBack_Y = endKing.y;
				startKingForward_X = startKing.x + 1;
				startKingForward_Y = startKing.y - 1;
				if (board.get(startIndex) == Board.BLACK_KING) {
					while (endKingBack_X >= 0 && endkingBack_Y < startKing.y) {
						if (board.get(endKingBack_X, endkingBack_Y) == Board.WHITE_CHECKER
								|| board.get(endKingBack_X, endkingBack_Y) == Board.WHITE_KING) {
							Whiteindex = Board.toIndex(endKingBack_X, endkingBack_Y);
							WhiteCounter++;
						}
						if (board.get(endKingBack_X, endkingBack_Y) == Board.BLACK_CHECKER
								|| board.get(endKingBack_X, endkingBack_Y) == Board.BLACK_KING)
							BlackCounter++;

						endKingBack_X--;
						endkingBack_Y++;
					}
					StartArrow = new Point(endKingBack_X, endkingBack_Y);
					EndArrow = new Point(endKingBack_X + 1, endkingBack_Y - 1);

				} else if (board.get(startIndex) == Board.WHITE_KING) {
					while (endKingBack_X >= 0 && endkingBack_Y < startKing.y) {
						if (board.get(endKingBack_X, endkingBack_Y) == Board.BLACK_CHECKER
								|| board.get(endKingBack_X, endkingBack_Y) == Board.BLACK_KING) {
							Blackindex = Board.toIndex(endKingBack_X, endkingBack_Y);
							BlackCounter++;
						}
						if (board.get(endKingBack_X, endkingBack_Y) == Board.WHITE_CHECKER
								|| board.get(endKingBack_X, endkingBack_Y) == Board.WHITE_KING)
							WhiteCounter++;

						endKingBack_X--;
						endkingBack_Y++;
					}

					StartArrow = new Point(endKingBack_X, endkingBack_Y);
					EndArrow = new Point(endKingBack_X + 1, endkingBack_Y - 1);

				}
				if (board.get(startIndex) == Board.BLACK_KING) {
					while (startKingForward_X <= Board.Board_Size - 1 && startKingForward_Y > endKing.y) {
						if (board.get(startKingForward_X, startKingForward_Y) == Board.WHITE_CHECKER
								|| board.get(startKingForward_X, startKingForward_Y) == Board.WHITE_KING) {
							Whiteindex = Board.toIndex(startKingForward_X, startKingForward_Y);
							WhiteCounter++;
						}
						if (board.get(startKingForward_X, startKingForward_Y) == Board.BLACK_CHECKER
								|| board.get(startKingForward_X, startKingForward_Y) == Board.BLACK_KING)
							BlackCounter++;

						startKingForward_X++;
						startKingForward_Y--;
					}
				} else if (board.get(startIndex) == Board.WHITE_KING) {
					while (startKingForward_X <= Board.Board_Size - 1 && startKingForward_Y > endKing.y) {
						if (board.get(startKingForward_X, startKingForward_Y) == Board.BLACK_CHECKER
								|| board.get(startKingForward_X, startKingForward_Y) == Board.BLACK_KING) {
							Blackindex = Board.toIndex(startKingForward_X, startKingForward_Y);
							BlackCounter++;
						}
						if (board.get(startKingForward_X, startKingForward_Y) == Board.WHITE_CHECKER
								|| board.get(startKingForward_X, startKingForward_Y) == Board.WHITE_KING)
							WhiteCounter++;

						startKingForward_X++;
						startKingForward_Y--;
					}

				}

				boolean flag = checkTheStateBlack(Board.BLACK_KING, WhiteCounter, BlackCounter, board, Whiteindex,
						startIndex, endIndex, StartArrow, EndArrow)
						|| checkTheStateWhite(Board.WHITE_KING, WhiteCounter, BlackCounter, board, Blackindex,
								startIndex, endIndex, StartArrow, EndArrow);
				boolean flag2 = false;
				int endIndexTemp = -1;
				int startIndexTemp = -1;

				if (WhiteCounter == 0 && BlackCounter == 0) {
					flag2 = true;
					endIndexTemp = endIndex;
					startIndexTemp = startIndex;
				}

				if (flag == false) {
					if (WhiteCounter != 0 || BlackCounter != 0) {
						WhiteCounter = 0;
						BlackCounter = 0;
					}
					// up down
					endKingBack_X = endKing.x;
					endkingBack_Y = endKing.y;
					startKingForward_X = startKing.x - 1;
					startKingForward_Y = startKing.y + 1;
					if (board.get(startIndex) == Board.BLACK_KING) {
						while (endkingBack_Y >= 0 && endKingBack_X < startKing.x) {
							if (board.get(endKingBack_X, endkingBack_Y) == Board.WHITE_CHECKER
									|| board.get(endKingBack_X, endkingBack_Y) == Board.WHITE_KING) {
								Whiteindex = Board.toIndex(endKingBack_X, endkingBack_Y);
								WhiteCounter++;
							}
							if (board.get(endKingBack_X, endkingBack_Y) == Board.BLACK_CHECKER
									|| board.get(endKingBack_X, endkingBack_Y) == Board.BLACK_KING)
								BlackCounter++;

							endKingBack_X++;
							endkingBack_Y--;
						}
						StartArrow = new Point(endKingBack_X, endkingBack_Y);
						EndArrow = new Point(endKingBack_X - 1, endkingBack_Y + 1);

					} else if (board.get(startIndex) == Board.WHITE_KING) {
						while (endkingBack_Y >= 0 && endKingBack_X < startKing.x) {
							if (board.get(endKingBack_X, endkingBack_Y) == Board.BLACK_CHECKER
									|| board.get(endKingBack_X, endkingBack_Y) == Board.BLACK_KING) {
								Blackindex = Board.toIndex(endKingBack_X, endkingBack_Y);
								BlackCounter++;
							}
							if (board.get(endKingBack_X, endkingBack_Y) == Board.WHITE_CHECKER
									|| board.get(endKingBack_X, endkingBack_Y) == Board.WHITE_KING)
								WhiteCounter++;

							endKingBack_X++;
							endkingBack_Y--;
						}

						StartArrow = new Point(endKingBack_X, endkingBack_Y);
						EndArrow = new Point(endKingBack_X - 1, endkingBack_Y + 1);

					}
					if (board.get(startIndex) == Board.BLACK_KING) {
						while (startKingForward_Y <= Board.Board_Size - 1 && startKingForward_X > endKing.x) {
							if (board.get(startKingForward_X, startKingForward_Y) == Board.WHITE_CHECKER
									|| board.get(startKingForward_X, startKingForward_Y) == Board.WHITE_KING) {
								Whiteindex = Board.toIndex(startKingForward_X, startKingForward_Y);
								WhiteCounter++;
							}
							if (board.get(startKingForward_X, startKingForward_Y) == Board.BLACK_CHECKER
									|| board.get(startKingForward_X, startKingForward_Y) == Board.BLACK_KING)
								BlackCounter++;

							startKingForward_X--;
							startKingForward_Y++;
						}
					} else if (board.get(startIndex) == Board.WHITE_KING) {
						while (startKingForward_Y <= Board.Board_Size - 1 && startKingForward_X > endKing.x) {
							if (board.get(startKingForward_X, startKingForward_Y) == Board.BLACK_CHECKER
									|| board.get(startKingForward_X, startKingForward_Y) == Board.BLACK_KING) {
								Blackindex = Board.toIndex(startKingForward_X, startKingForward_Y);
								BlackCounter++;
							}
							if (board.get(startKingForward_X, startKingForward_Y) == Board.WHITE_CHECKER
									|| board.get(startKingForward_X, startKingForward_Y) == Board.WHITE_KING)
								WhiteCounter++;

							startKingForward_X--;
							startKingForward_Y++;
						}

					}
				}

				flag = checkTheStateBlack(Board.BLACK_KING, WhiteCounter, BlackCounter, board, Whiteindex, startIndex,
						endIndex, StartArrow, EndArrow)
						|| checkTheStateWhite(Board.WHITE_KING, WhiteCounter, BlackCounter, board, Blackindex,
								startIndex, endIndex, StartArrow, EndArrow);

			}

			if (endKing.y < startKing.y && endKing.x > startKing.x && board.get(endKing.x, endKing.y) == Board.EMPTY) {
				// left right
				endKingBack_X = endKing.x;
				endkingBack_Y = endKing.y;
				startKingForward_X = startKing.x - 1;
				startKingForward_Y = startKing.y - 1;
				if (board.get(startIndex) == Board.BLACK_KING) {
					while (endKingBack_X <= Board.Board_Size - 1 && endkingBack_Y < startKing.y) {
						if (board.get(endKingBack_X, endkingBack_Y) == Board.WHITE_CHECKER
								|| board.get(endKingBack_X, endkingBack_Y) == Board.WHITE_KING) {
							Whiteindex = Board.toIndex(endKingBack_X, endkingBack_Y);
							WhiteCounter++;
						}
						if (board.get(endKingBack_X, endkingBack_Y) == Board.BLACK_CHECKER
								|| board.get(endKingBack_X, endkingBack_Y) == Board.BLACK_KING)
							BlackCounter++;
						endKingBack_X++;
						endkingBack_Y++;
					}
					StartArrow = new Point(endKingBack_X, endkingBack_Y);
					EndArrow = new Point(endKingBack_X - 1, endkingBack_Y - 1);

				} else if (board.get(startIndex) == Board.WHITE_KING) {
					while (endKingBack_X <= Board.Board_Size - 1 && endkingBack_Y < startKing.y) {
						if (board.get(endKingBack_X, endkingBack_Y) == Board.BLACK_CHECKER
								|| board.get(endKingBack_X, endkingBack_Y) == Board.BLACK_KING) {
							Blackindex = Board.toIndex(endKingBack_X, endkingBack_Y);
							BlackCounter++;
						}
						if (board.get(endKingBack_X, endkingBack_Y) == Board.WHITE_CHECKER
								|| board.get(endKingBack_X, endkingBack_Y) == Board.WHITE_KING)
							WhiteCounter++;
						endKingBack_X++;
						endkingBack_Y++;
					}

					StartArrow = new Point(endKingBack_X, endkingBack_Y);
					EndArrow = new Point(endKingBack_X - 1, endkingBack_Y - 1);

				}
				if (board.get(startIndex) == Board.BLACK_KING) {
					while (startKingForward_X >= 0 && startKingForward_Y > endKing.y) {
						if (board.get(startKingForward_X, startKingForward_Y) == Board.WHITE_CHECKER
								|| board.get(startKingForward_X, startKingForward_Y) == Board.WHITE_KING) {
							Whiteindex = Board.toIndex(startKingForward_X, startKingForward_Y);
							WhiteCounter++;
						}
						if (board.get(startKingForward_X, startKingForward_Y) == Board.BLACK_CHECKER
								|| board.get(startKingForward_X, startKingForward_Y) == Board.BLACK_KING)
							BlackCounter++;

						startKingForward_X--;
						startKingForward_Y--;
					}
				} else if (board.get(startIndex) == Board.WHITE_KING) {
					while (startKingForward_X >= 0 && startKingForward_Y > endKing.y) {
						if (board.get(startKingForward_X, startKingForward_Y) == Board.BLACK_CHECKER
								|| board.get(startKingForward_X, startKingForward_Y) == Board.BLACK_KING) {
							Blackindex = Board.toIndex(startKingForward_X, startKingForward_Y);
							BlackCounter++;
						}
						if (board.get(startKingForward_X, startKingForward_Y) == Board.WHITE_CHECKER
								|| board.get(startKingForward_X, startKingForward_Y) == Board.WHITE_KING)
							WhiteCounter++;

						startKingForward_X--;
						startKingForward_Y--;
					}

				}

				boolean flag = checkTheStateBlack(Board.BLACK_KING, WhiteCounter, BlackCounter, board, Whiteindex,
						startIndex, endIndex, StartArrow, EndArrow)
						|| checkTheStateWhite(Board.WHITE_KING, WhiteCounter, BlackCounter, board, Blackindex,
								startIndex, endIndex, StartArrow, EndArrow);
				boolean flag2 = false;
				int endIndexTemp = -1;
				int startIndexTemp = -1;

				if (WhiteCounter == 0 && BlackCounter == 0) {
					flag2 = true;
					endIndexTemp = endIndex;
					startIndexTemp = startIndex;
				}

				if (flag == false) {
					if (WhiteCounter != 0 || BlackCounter != 0) {
						WhiteCounter = 0;
						BlackCounter = 0;
					}
					// up down
					endKingBack_X = endKing.x;
					endkingBack_Y = endKing.y;
					startKingForward_X = startKing.x + 1;
					startKingForward_Y = startKing.y + 1;
					if (board.get(startIndex) == Board.BLACK_KING) {
						while (endkingBack_Y >= 0 && endKingBack_X > startKing.x) {
							if (board.get(endKingBack_X, endkingBack_Y) == Board.WHITE_CHECKER
									|| board.get(endKingBack_X, endkingBack_Y) == Board.WHITE_KING) {
								Whiteindex = Board.toIndex(endKingBack_X, endkingBack_Y);
								WhiteCounter++;
							}
							if (board.get(endKingBack_X, endkingBack_Y) == Board.BLACK_CHECKER
									|| board.get(endKingBack_X, endkingBack_Y) == Board.BLACK_KING)
								BlackCounter++;
							endKingBack_X--;
							endkingBack_Y--;
						}

						StartArrow = new Point(endKingBack_X, endkingBack_Y);
						EndArrow = new Point(endKingBack_X + 1, endkingBack_Y + 1);

					} else if (board.get(startIndex) == Board.WHITE_KING) {
						while (endkingBack_Y >= 0 && endKingBack_X > startKing.x) {
							if (board.get(endKingBack_X, endkingBack_Y) == Board.BLACK_CHECKER
									|| board.get(endKingBack_X, endkingBack_Y) == Board.BLACK_KING) {
								Blackindex = Board.toIndex(endKingBack_X, endkingBack_Y);
								BlackCounter++;
							}
							if (board.get(endKingBack_X, endkingBack_Y) == Board.WHITE_CHECKER
									|| board.get(endKingBack_X, endkingBack_Y) == Board.WHITE_KING)
								WhiteCounter++;
							endKingBack_X--;
							endkingBack_Y--;
						}

						StartArrow = new Point(endKingBack_X, endkingBack_Y);
						EndArrow = new Point(endKingBack_X + 1, endkingBack_Y + 1);

					}
					if (board.get(startIndex) == Board.BLACK_KING) {
						while (startKingForward_Y <= Board.Board_Size - 1 && startKingForward_X < endKing.x) {
							if (board.get(startKingForward_X, startKingForward_Y) == Board.WHITE_CHECKER
									|| board.get(startKingForward_X, startKingForward_Y) == Board.WHITE_KING) {
								Whiteindex = Board.toIndex(startKingForward_X, startKingForward_Y);
								WhiteCounter++;
							}
							if (board.get(startKingForward_X, startKingForward_Y) == Board.BLACK_CHECKER
									|| board.get(startKingForward_X, startKingForward_Y) == Board.BLACK_KING)
								BlackCounter++;

							startKingForward_X++;
							startKingForward_Y++;
						}
					} else if (board.get(startIndex) == Board.WHITE_KING) {
						while (startKingForward_Y <= Board.Board_Size - 1 && startKingForward_X < endKing.x) {
							if (board.get(startKingForward_X, startKingForward_Y) == Board.BLACK_CHECKER
									|| board.get(startKingForward_X, startKingForward_Y) == Board.BLACK_KING) {
								Blackindex = Board.toIndex(startKingForward_X, startKingForward_Y);
								BlackCounter++;
							}
							if (board.get(startKingForward_X, startKingForward_Y) == Board.WHITE_CHECKER
									|| board.get(startKingForward_X, startKingForward_Y) == Board.WHITE_KING)
								WhiteCounter++;

							startKingForward_X++;
							startKingForward_Y++;
						}

					}
				}

				flag = checkTheStateBlack(Board.BLACK_KING, WhiteCounter, BlackCounter, board, Whiteindex, startIndex,
						endIndex, StartArrow, EndArrow)
						|| checkTheStateWhite(Board.WHITE_KING, WhiteCounter, BlackCounter, board, Blackindex,
								startIndex, endIndex, StartArrow, EndArrow);

			}

		}

	}

	public static boolean checkTheStateWhite(int id, int whiteCounter, int blackCounter, Board board, int blackindex,
			int startindex, int endindex, Point startArrow, Point EndArrow) {
		if (board.get(startindex) == id) {
			if (blackCounter == 1 && whiteCounter == 0) {
				if (Game.AvailableCutMoves.containsKey(EndArrow)) {
					if (!Game.AvailableCutMoves.get(EndArrow).contains(startArrow))
						Game.AvailableCutMoves.get(EndArrow).add(startArrow);
					return false;
				} else {
					ArrayList<Point> points = new ArrayList<>();
					points.add(startArrow);
					Game.AvailableCutMoves.put(EndArrow, points);
					return false;
				}
			} else if (whiteCounter == 0 && blackCounter == 0) {
				if (Game.AvailableCutMoves.containsKey(EndArrow)) {
					if (!Game.AvailableCutMoves.get(EndArrow).contains(startArrow))
						Game.AvailableCutMoves.get(EndArrow).add(startArrow);
					return false;
				} else {
					ArrayList<Point> points = new ArrayList<>();
					points.add(startArrow);
					Game.AvailableCutMoves.put(EndArrow, points);
					return false;
				}
			}

		}
		return false;
	}

	public static boolean checkTheStateBlack(int id, int whiteCounter, int blackCounter, Board board, int whiteindex,
			int startindex, int endindex, Point startArrow, Point EndArrow) {
		if (board.get(startindex) == id) {
			if (whiteCounter == 1 && blackCounter == 0) {
				if (Game.AvailableCutMoves.containsKey(EndArrow)) {
					if (!Game.AvailableCutMoves.get(EndArrow).contains(startArrow))
						Game.AvailableCutMoves.get(EndArrow).add(startArrow);
					return false;
				} else {
					ArrayList<Point> points = new ArrayList<>();
					points.add(startArrow);
					Game.AvailableCutMoves.put(EndArrow, points);
					return false;
				}
			} else if (whiteCounter == 0 && blackCounter == 0) {
				if (Game.AvailableCutMoves.containsKey(EndArrow)) {
					if (!Game.AvailableCutMoves.get(EndArrow).contains(startArrow))
						Game.AvailableCutMoves.get(EndArrow).add(startArrow);
					return false;
				} else {
					ArrayList<Point> points = new ArrayList<>();
					points.add(startArrow);
					Game.AvailableCutMoves.put(EndArrow, points);
					return false;
				}
			}

		}
		return false;
	}

	public static boolean isValidMove(Board board, boolean isP1Turn, int startIndex, int endIndex, int skipIndex) {
		Point startKing = Board.toPoint(startIndex);
		Point endKing = Board.toPoint(endIndex);

		// Basic checks
		boolean flag2 = false;
		if (board == null || !Board.isValidIndex(startIndex) || !Board.isValidIndex(endIndex)) {
			flag2 = true;
		} else if (startIndex == endIndex) {
			flag2 = true;
		} else if (Board.isValidIndex(skipIndex) && skipIndex != startIndex) {
			flag2 = true;
		}

		if (board.get(startIndex) == Board.BLACK_KING && !isKingCutBorder(board, startIndex, endIndex)) {
			List<Point> checkers2;
			int cross = 1;
			int counter = 0;
			checkers2 = board.find(Board.BLACK_CHECKER);
			checkers2.addAll(board.find(Board.BLACK_KING));
			for (Point p : checkers2) {
				int dxc = p.x - startKing.x;
				int dyc = p.y - startKing.y;
				int dxl = endKing.x - startKing.x;
				int dyl = endKing.y - startKing.y;
				cross = dxc * dyl - dyc * dxl;
				if (cross == 0) {
					boolean flag = false;
					if (Math.abs(dxl) >= Math.abs(dyl))
						flag = dxl > 0 ? startKing.x <= p.x && p.x <= endKing.x
								: endKing.x <= p.x && p.x <= startKing.x;
					else
						flag = dyl > 0 ? startKing.y <= p.y && p.y <= endKing.y
								: endKing.y <= p.y && p.y <= startKing.y;
					if (flag == true) {
						counter += 1;
					}
				}
			}
			if (counter > 1) {
				flag2 = true;
			}
		} else if (board.get(startIndex) == Board.WHITE_KING && !isKingCutBorder(board, startIndex, endIndex)) {
			List<Point> checkers2;
			int cross = 1;
			int counter = 0;
			checkers2 = board.find(Board.WHITE_CHECKER);
			checkers2.addAll(board.find(Board.WHITE_KING));
			for (Point p : checkers2) {
				int dxc = p.x - startKing.x;
				int dyc = p.y - startKing.y;
				int dxl = endKing.x - startKing.x;
				int dyl = endKing.y - startKing.y;
				cross = dxc * dyl - dyc * dxl;
				if (cross == 0) {
					boolean flag = false;
					if (Math.abs(dxl) >= Math.abs(dyl))
						flag = dxl > 0 ? startKing.x <= p.x && p.x <= endKing.x
								: endKing.x <= p.x && p.x <= startKing.x;
					else
						flag = dyl > 0 ? startKing.y <= p.y && p.y <= endKing.y
								: endKing.y <= p.y && p.y <= startKing.y;
					if (flag == true) {
						counter += 1;
					}
				}
			}

			if (counter > 1)
				flag2 = true;
		}
		// Perform the tests to validate the move
		if (!validateIDs(board, isP1Turn, startIndex, endIndex)) {
			flag2 = true;
		} else if (!validateDistance(board, isP1Turn, startIndex, endIndex)) {
			flag2 = true;
		}
		if (flag2 == true) {
			return false;
		} else // Passed all tests
			return true;
	}

	/**
	 * Validates all ID related values for the start, end, and middle (if the move
	 * is a skip).
	 * 
	 * @param board      the current board to check against.
	 * @param isP1Turn   the flag indicating if it is player 1's turn.
	 * @param startIndex the start index of the move.
	 * @param endIndex   the end index of the move.
	 * @return true if and only if all IDs are valid.
	 */
	private static boolean validateIDs(Board board, boolean isP1Turn, int startIndex, int endIndex) {

		// Check if end is clear
		if (board.get(endIndex) != Board.EMPTY) {
			return false;
		}

		// Check if proper ID
		int id = board.get(startIndex);
		if ((isP1Turn && id != Board.BLACK_CHECKER && id != Board.BLACK_KING)
				|| (!isP1Turn && id != Board.WHITE_CHECKER && id != Board.WHITE_KING)) {
			return false;
		}

		// Check the middle
		Point middle = Board.middle(startIndex, endIndex);
		int midID = board.get(Board.toIndex(middle));
		if (board.get(startIndex) != Board.BLACK_KING && board.get(startIndex) != Board.WHITE_KING)
			if (midID != Board.INVALID && ((!isP1Turn && midID != Board.BLACK_CHECKER && midID != Board.BLACK_KING)
					|| (isP1Turn && midID != Board.WHITE_CHECKER && midID != Board.WHITE_KING))) {
				return false;
			}

		// Passed all tests
		return true;
	}

	/**
	 * Checks that the move is diagonal and magnitude 1 or 2 in the correct
	 * direction. If the magnitude is not 2 (i.e. not a skip), it checks that no
	 * skips are available by other checkers of the same player.
	 * 
	 * @param board      the current board to check against.
	 * @param isP1Turn   the flag indicating if it is player 1's turn.
	 * @param startIndex the start index of the move.
	 * @param endIndex   the end index of the move.
	 * @return true if and only if the move distance is valid.
	 */
	private static boolean validateDistance(Board board, boolean isP1Turn, int startIndex, int endIndex) {

		// Check that it was a diagonal move
		Point start = Board.toPoint(startIndex);
		Point end = Board.toPoint(endIndex);
		int dx = end.x - start.x;
		int dy = end.y - start.y;
		if (board.get(startIndex) != Board.BLACK_KING && board.get(startIndex) != Board.WHITE_KING) {
			if (Math.abs(dx) != Math.abs(dy) || Math.abs(dx) > 2 || dx == 0) {
				return false;
			}
		}

		if (board.get(startIndex) == Board.BLACK_KING || board.get(startIndex) == Board.WHITE_KING) {
			if ((Math.abs(dx) != Math.abs(dy) || dx == 0) && (!isKingCutBorder(board, startIndex, endIndex))) {
				return false;
			}
		}

		// Check that it was in the right direction
		if (Move.isSkip == false) {
			int id = board.get(startIndex);
			if ((id == Board.WHITE_CHECKER && dy > 0) || (id == Board.BLACK_CHECKER && dy < 0)) {
				return false;
			}
		}
		// Check that if this is not a skip, there are none available
		Point middle = Board.middle(startIndex, endIndex);
		int midID = board.get(Board.toIndex(middle));
		if (midID < 0) {

			// Get the correct checkers
			List<Point> checkers;
			if (isP1Turn) {
				checkers = board.find(Board.BLACK_CHECKER);
				checkers.addAll(board.find(Board.BLACK_KING));
			} else {
				checkers = board.find(Board.WHITE_CHECKER);
				checkers.addAll(board.find(Board.WHITE_KING));
			}

		}

		// Passed all tests
		return true;
	}

	public static ArrayList<Integer> CheckersToSkip(Board board, boolean isP1Turn, int startIndex) {
		ArrayList<Integer> skips = new ArrayList<Integer>();
		List<Point> checkers;
		if (isP1Turn) {
			checkers = board.find(Board.BLACK_CHECKER);
			checkers.addAll(board.find(Board.BLACK_KING));
		} else {
			checkers = board.find(Board.WHITE_CHECKER);
			checkers.addAll(board.find(Board.WHITE_KING));
		}
		for (Point p : checkers) {
			int index = Board.toIndex(p);
			if (!MoveGenerator.getSkipsFirst(board, index).isEmpty()) {
				skips.add(index);
			}
		}
		return skips;
	}

	public static int checkerSkip(Board board, boolean isP1Turn) {
		List<Point> checkers;
		if (isP1Turn) {
			checkers = board.find(Board.BLACK_CHECKER);
			checkers.addAll(board.find(Board.BLACK_KING));
		} else {
			checkers = board.find(Board.WHITE_CHECKER);
			checkers.addAll(board.find(Board.WHITE_KING));
		}

		// Check if any of them have a skip available
		for (Point p : checkers) {
			int index = Board.toIndex(p);
			if (!MoveGenerator.getSkips(board, index).isEmpty()) {
				return index;
			}
		}
		return -1;
	}

	/**
	 * Checks if the specified checker is safe (i.e. the opponent cannot skip the
	 * checker).
	 * 
	 * @param board   the current board state.
	 * @param checker the point where the test checker is located at.
	 * @return true if and only if the checker at the point is safe.
	 */
	public static boolean isSafe(Board board, Point checker) {

		// Trivial cases
		if (board == null || checker == null) {
			return true;
		}
		int index = Board.toIndex(checker);
		if (index < 0) {
			return true;
		}
		int id = board.get(index);
		if (id == Board.EMPTY) {
			return true;
		}

		// Determine if it can be skipped
		boolean isBlack = (id == Board.BLACK_CHECKER || id == Board.BLACK_KING);
		List<Point> check = new ArrayList<>();
		MoveGenerator.addPoints(check, checker, Board.BLACK_KING, 1);
		for (Point p : check) {
			int start = Board.toIndex(p);
			int tid = board.get(start);

			// Nothing here
			if (tid == Board.EMPTY || tid == Board.INVALID) {
				continue;
			}

			// Check ID
			boolean isWhite = (tid == Board.WHITE_CHECKER || tid == Board.WHITE_KING);
			if (isBlack && !isWhite) {
				continue;
			}
			boolean isKing = (tid == Board.BLACK_KING || tid == Board.BLACK_KING);

			// Determine if valid skip direction
			int dx = (checker.x - p.x) * 2;
			int dy = (checker.y - p.y) * 2;
			if (!isKing && (isWhite ^ (dy < 0))) {
				continue;
			}
			int endIndex = Board.toIndex(new Point(p.x + dx, p.y + dy));
			if (MoveGenerator.isValidSkip(board, start, endIndex)) {
				return false;
			}
		}

		return true;
	}

}