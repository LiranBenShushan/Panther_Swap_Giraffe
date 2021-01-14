/* Name: Move
 * Author: Devon McGrath
 * Description: This class represents a move.
 */

package model;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import Controller.GameController;
import Controller.MoveGenerator;
import Controller.MoveLogic;
import Controller.SysData;
import View.CheckerBoard;
import View.CheckersWindow;

/**
 * The {@code Move} class represents a move and contains a weight associated
 * with the move.
 */
public class Move {
	/** The weight corresponding to an invalid move. */
	public static final double WEIGHT_INVALID = Double.NEGATIVE_INFINITY;

	/** The start index of the move. */
	private byte startIndex;

	/** The end index of the move. */
	private byte endIndex;

	/** The weight associated with the move. */
	private double weight;

	public static boolean isKingCutBorder = true;
	public static boolean UpDown = false;
	public static boolean LeftRight = false;
	public static boolean isSkip = false;
	public int chosenOption;
	private static int flag = 0;
	private static int ComputerCount = 0;

	public Move(int startIndex, int endIndex) {
		setStartIndex(startIndex);
		setEndIndex(endIndex);
	}

	public Move(Point start, Point end) {
		setStartIndex(Board.toIndex(start));
		setEndIndex(Board.toIndex(end));
	}

	public boolean checkTheStateWhite(int id, int whiteCounter, int blackCounter, Board board, int blackindex,
			int startindex, int endindex) {
		if (board.get(startindex) == id) {
			if (blackCounter == 1 && whiteCounter == 0) {
				board.set(blackindex, Board.EMPTY);
				board.set(endindex, board.get(startindex));
				board.set(startindex, Board.EMPTY);
				return true;
			} else if (whiteCounter == 0 && blackCounter == 0) {
				Game.Buttonclicked = false;
				return false;
			}

		}
		Game.Buttonclicked = false;
		return false;
	}

	public boolean checkTheStateBlack(int id, int whiteCounter, int blackCounter, Board board, int whiteindex,
			int startindex, int endindex) {
		if (board.get(startindex) == id) {
			if (whiteCounter == 1 && blackCounter == 0) {
				board.set(whiteindex, Board.EMPTY);
				board.set(endindex, board.get(startindex));
				board.set(startindex, Board.EMPTY);
				return true;
			} else if (whiteCounter == 0 && blackCounter == 0) {
				Game.Buttonclicked = false;
				return false;
			}

		}
		Game.Buttonclicked = false;
		return false;
	}

	public boolean MoveLogic(Board board, int startIndex, int endIndex, Game game, int redTile)
			throws UnsupportedAudioFileException, IOException, LineUnavailableException {

		boolean flag5 = false;
		Game.toRemove = -1;
		GameController.getInstance().restartAfterpause();
		GameController.getInstance().setEmptyLabel();

		int cross = 1;
		// Validate the move
		if (!MoveLogic.isValidMove(game, startIndex, endIndex)
				|| (!MoveLogic.isValidKingMove(board, startIndex, endIndex) && isKingCutBorder == false)) {
			Game.Buttonclicked = false;

			return false;
		}
		List<Point> DeltaPoints = MoveGenerator.getKingSkips(board, startIndex, game);

		// Burning player
		if (!MoveLogic.CheckersToSkip(board, game.isP1Turn(), startIndex).isEmpty()) {
			ArrayList<Integer> checkers = MoveLogic.CheckersToSkip(board, game.isP1Turn(), startIndex);
			List<Point> endpoints = MoveGenerator.getSkipsFirst(board, startIndex);
			if (!checkers.contains(startIndex)) {
				Random rand = new Random();
				int checkerToRemove = checkers.get(rand.nextInt(checkers.size()));
				board.set(checkerToRemove, Board.EMPTY);
				Game.toRemove = checkerToRemove;
				flag5 = true;

			}
			if (checkers.contains(startIndex) && !endpoints.contains(Board.toPoint(endIndex))
					&& board.get(startIndex) != Board.BLACK_KING && board.get(startIndex) != Board.WHITE_KING) {
				board.set(startIndex, Board.EMPTY);
				Game.toRemove = startIndex;
				flag5 = true;

			}

		}

		// Make the move
		Point middle = Board.middle(startIndex, endIndex);
		int midIndex = Board.toIndex(middle);
		if (board.get(startIndex) != Board.BLACK_KING && board.get(startIndex) != Board.WHITE_KING) {
			board.set(endIndex, board.get(startIndex));
			board.set(midIndex, Board.EMPTY);
			board.set(startIndex, Board.EMPTY);
			flag5 = true;

			if (midIndex != -1) {
				game.setPoints(100, true);
			}

		} else if (MoveLogic.isKingCutBorder(board, startIndex, endIndex) && isKingCutBorder == true) {
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

			if (endKing.x > startKing.x && endKing.y > startKing.y) {// ok
				// left right
				if (LeftRight == true) {
					Move.LeftRight = false;
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
				}
				boolean flag = checkTheStateBlack(Board.BLACK_KING, WhiteCounter, BlackCounter, board, Whiteindex,
						startIndex, endIndex)
						|| checkTheStateWhite(Board.WHITE_KING, WhiteCounter, BlackCounter, board, Blackindex,
								startIndex, endIndex);
				if (flag == true)
					game.setPoints(100, true);

				boolean flag2 = false;
				int endIndexTemp = -1;
				int startIndexTemp = -1;

				if (WhiteCounter == 0 && BlackCounter == 0) {
					flag2 = true;
					endIndexTemp = endIndex;
					startIndexTemp = startIndex;
				}

				if (UpDown == true) {
					Move.UpDown = false;
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
						endIndex)
						|| checkTheStateWhite(Board.WHITE_KING, WhiteCounter, BlackCounter, board, Blackindex,
								startIndex, endIndex);
				if (flag == true)
					game.setPoints(100, true);

				if (flag == false && WhiteCounter == 0 && BlackCounter == 0) {
					board.set(endIndex, board.get(startIndex));
					board.set(startIndex, Board.EMPTY);
				} else if (flag == false && flag2 == true) {
					board.set(endIndexTemp, board.get(startIndexTemp));
					board.set(startIndexTemp, Board.EMPTY);
				}

			}

			if (endKing.x < startKing.x && endKing.y > startKing.y) {// ok
				if (LeftRight == true) {
					Move.LeftRight = false;
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
				}
				boolean flag = checkTheStateBlack(Board.BLACK_KING, WhiteCounter, BlackCounter, board, Whiteindex,
						startIndex, endIndex)
						|| checkTheStateWhite(Board.WHITE_KING, WhiteCounter, BlackCounter, board, Blackindex,
								startIndex, endIndex);

				if (flag == true)
					game.setPoints(100, true);

				boolean flag2 = false;
				int endIndexTemp = -1;
				int startIndexTemp = -1;

				if (WhiteCounter == 0 && BlackCounter == 0) {
					flag2 = true;
					endIndexTemp = endIndex;
					startIndexTemp = startIndex;
				}

				if (UpDown == true) {
					Move.UpDown = false;
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
						endIndex)
						|| checkTheStateWhite(Board.WHITE_KING, WhiteCounter, BlackCounter, board, Blackindex,
								startIndex, endIndex);
				if (flag == true)
					game.setPoints(100, true);

				if (flag == false && WhiteCounter == 0 && BlackCounter == 0) {
					board.set(endIndex, board.get(startIndex));
					board.set(startIndex, Board.EMPTY);
				} else if (flag == false && flag2 == true) {
					board.set(endIndexTemp, board.get(startIndexTemp));
					board.set(startIndexTemp, Board.EMPTY);
				}

			}

			if (endKing.x < startKing.x && endKing.y < startKing.y) {// ok
				if (LeftRight == true) {
					Move.LeftRight = false;
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
				}
				boolean flag = checkTheStateBlack(Board.BLACK_KING, WhiteCounter, BlackCounter, board, Whiteindex,
						startIndex, endIndex)
						|| checkTheStateWhite(Board.WHITE_KING, WhiteCounter, BlackCounter, board, Blackindex,
								startIndex, endIndex);

				if (flag == true)
					game.setPoints(100, true);

				boolean flag2 = false;
				int endIndexTemp = -1;
				int startIndexTemp = -1;

				if (WhiteCounter == 0 && BlackCounter == 0) {
					flag2 = true;
					endIndexTemp = endIndex;
					startIndexTemp = startIndex;
				}

				if (UpDown == true) {
					Move.UpDown = false;
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
						endIndex)
						|| checkTheStateWhite(Board.WHITE_KING, WhiteCounter, BlackCounter, board, Blackindex,
								startIndex, endIndex);
				if (flag == true)
					game.setPoints(100, true);

				if (flag == false && WhiteCounter == 0 && BlackCounter == 0) {
					board.set(endIndex, board.get(startIndex));
					board.set(startIndex, Board.EMPTY);
				} else if (flag == false && flag2 == true) {
					board.set(endIndexTemp, board.get(startIndexTemp));
					board.set(startIndexTemp, Board.EMPTY);
				}

			}

			if (endKing.y < startKing.y && endKing.x > startKing.x) {
				if (LeftRight == true) {
					Move.LeftRight = false;
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
				}

				boolean flag = checkTheStateBlack(Board.BLACK_KING, WhiteCounter, BlackCounter, board, Whiteindex,
						startIndex, endIndex)
						|| checkTheStateWhite(Board.WHITE_KING, WhiteCounter, BlackCounter, board, Blackindex,
								startIndex, endIndex);
				if (flag == true)
					game.setPoints(100, true);

				boolean flag2 = false;
				int endIndexTemp = -1;
				int startIndexTemp = -1;

				if (WhiteCounter == 0 && BlackCounter == 0) {
					flag2 = true;
					endIndexTemp = endIndex;
					startIndexTemp = startIndex;
				}

				if (UpDown == true) {
					Move.UpDown = false;
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
						endIndex)
						|| checkTheStateWhite(Board.WHITE_KING, WhiteCounter, BlackCounter, board, Blackindex,
								startIndex, endIndex);
				if (flag == true)
					game.setPoints(100, true);

				if (flag == false && WhiteCounter == 0 && BlackCounter == 0) {
					board.set(endIndex, board.get(startIndex));
					board.set(startIndex, Board.EMPTY);
				} else if (flag == false && flag2 == true) {
					board.set(endIndexTemp, board.get(startIndexTemp));
					board.set(startIndexTemp, Board.EMPTY);
				}

			}

			Game.Buttonclicked = false;

		} else if (Move.isKingCutBorder == false) {
			int counter = 0;
			Point point = null;
			Point startKing = Board.toPoint(startIndex);
			Point endKing = Board.toPoint(endIndex);
			List<Point> checkers;
			checkers = board.find(Board.WHITE_CHECKER);
			checkers.addAll(board.find(Board.WHITE_KING));
			checkers.addAll(board.find(Board.BLACK_CHECKER));
			checkers.addAll(board.find(Board.BLACK_KING));

			checkers.remove(startKing);

			for (Point p : checkers) {
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
						if ((board.get(p.x, p.y) == Board.BLACK_CHECKER && board.get(startIndex) == Board.BLACK_KING)
								|| (board.get(p.x, p.y) == Board.WHITE_CHECKER
										&& board.get(startIndex) == Board.WHITE_KING)
								|| (board.get(p.x, p.y) == Board.BLACK_KING
										&& board.get(startIndex) == Board.BLACK_KING)
								|| (board.get(p.x, p.y) == Board.WHITE_KING
										&& board.get(startIndex) == Board.WHITE_KING)) {
							Game.Buttonclicked = false;
							return false;
						}
						counter += 1;
						point = p;

					}
				}
			}

			if (counter > 1) {
				Game.Buttonclicked = false;
				return false;
			}

			if (counter == 1) {
				board.set(Board.toIndex(point), Board.EMPTY);
				game.setPoints(100, true);

			}
			board.set(endIndex, board.get(startIndex));
			board.set(startIndex, Board.EMPTY);
			Game.Buttonclicked = false;
		}

		if (board.get(endIndex) != Board.EMPTY || flag5 == true) {
			int counter = 0;
			if ((board.get(endIndex) == Board.BLACK_KING || board.get(endIndex) == Board.WHITE_KING)
					&& !DeltaPoints.isEmpty()) {
				for (int i = 0; i < DeltaPoints.size(); i++) {
					if (board.get(Board.toIndex(DeltaPoints.get(i))) != Board.EMPTY) {
						counter++;
					}
				}
				if (counter == DeltaPoints.size()) {
					board.set(endIndex, Board.EMPTY);
					Game.toRemove = endIndex;
				}

			}

			// Make the checker a king if necessary
			Point end = Board.toPoint(endIndex);
			int id = board.get(endIndex);
			boolean switchTurn = false;
			if (end.y == 0 && id == Board.WHITE_CHECKER) {
				board.set(endIndex, Board.WHITE_KING);
				switchTurn = true;

			} else if (end.y == Board.Board_Size - 1 && id == Board.BLACK_CHECKER) {
				board.set(endIndex, Board.BLACK_KING);
				switchTurn = true;
			}

			// Check if the turn should switch (i.e. no more skips)
			boolean midValid = Board.isValidIndex(midIndex);
			if (midValid) {
				game.setSkipIndex(endIndex);
			}

			if (!MoveGenerator.getSkips(board.copy(), endIndex).isEmpty()) {
				isSkip = true;
			}

			if (!midValid || MoveGenerator.getSkips(board.copy(), endIndex).isEmpty()) {
				switchTurn = true;
				isSkip = false;
			}

			/** check the if endIndex is yellow tile */

			boolean flag3 = false;
			if ((game.getPlayer1() instanceof ComputerPlayer && game.isP1Turn())
					|| (game.getPlayer2() instanceof ComputerPlayer && !game.isP1Turn())) {
				flag3 = true;
			}

			if (flag3 == false) {
				for (int i = 0; i < game.getYellowT().size(); i++) {
					try {
						if (endIndex == Board.toIndex(game.getYellowT().get(i)) && !game.isGameOver()) {

							GameController.getInstance().displayQuestion(SysData.popRandomQuestion(), game);
							flag = 1;
						}
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
			/** check if the endIndex is red tile */
			if ((game.getPlayer1() instanceof HumanPlayer && game.isP1Turn())
					|| (game.getPlayer2() instanceof HumanPlayer && !game.isP1Turn())) {
				if (endIndex == Board.toIndex(game.getRedTile())) {
					GameController.getInstance().redTileMsg(game);
					int points = GameController.getInstance().restartTimer(true);
					if (points > 60) {

						points -= 60;
						game.setPoints(points, false);
						if (midIndex != -1) {
							GameController.getInstance().EatMsg(game, points);

						} else
							GameController.getInstance().PMsg(game, -points);

					}

					else if (points < 60) {
						points = 60 - points;
						game.setPoints(points, true);
						if (midIndex != -1) {
							GameController.getInstance().EatMsg(game, points);

						} else
							GameController.getInstance().PMsg(game, +points);

					}
					game.yellowTiles();
					game.blueTiles();
					game.setEndIn(endIndex);
					return true;
				}

			} else {
				if (endIndex == redTile) {
					int points = GameController.getInstance().restartTimer(true);

					System.out.println("***RED TILE Comp***");
					if (points > 60) {

						points -= 60;
						game.setPoints(points, false);
						if (midIndex != -1) {
							GameController.getInstance().EatMsg(game, points);

						} else
							GameController.getInstance().PMsg(game, -points);

					}

					else if (points < 60) {
						points = 60 - points;
						game.setPoints(points, true);
						if (midIndex != -1) {
							GameController.getInstance().EatMsg(game, points);

						} else
							GameController.getInstance().PMsg(game, +points);

					}
					game.yellowTiles();
					game.blueTiles();
					game.setEndIn(redTile);
					ComputerPlayer.End = redTile;
					return true;
				}

			}

			if (game.getPlayer2() instanceof ComputerPlayer && !game.isP1Turn())
				if (ComputerPlayer.End != -1) {
					if (startIndex != ComputerPlayer.End) {

						return false;
					}
				}
			boolean Greenflag = false;
			/** check if the endIndex is green tile */
			if (endIndex == Board.toIndex(game.getGreenTile())) {
				System.out.println("***GREEN TILE***");
				game.setPoints(50, true);
				Greenflag = true;
			}

			/** check if the endIndex is blue tile */
			if (endIndex == Board.toIndex(game.getBlueTile())) {
				game.yellowTiles();
				game.redTiles();
				game.setPlace(endIndex);
				GameController.getInstance().blueTileMsg(game);
				return true;

			}

			if (game.getEndIn() != -1) {
				if (startIndex != game.getEndIn()) {
					Game.Buttonclicked = false;
					return false;
				} else {
					game.setEndIn(-1);
				}
			}

			/* START PANTHER TEAM */
			
			/**
			 * This is the place we check about purple tile, if the purple is a tile we generate a random Number between 0-2 included and acts as below : 
			 * 0 - Give the player 200 points.
			 * 1 - Pop a question to the player. 
			 * 2 - Reset the game board 
			 */
			boolean Purpleflag = false;
			/** check if the endIndex is PURPLE tile */
			if (endIndex == Board.toIndex(game.getPurpleTile())) {
				System.out.println("***PURPLE TILE***");
				Random rand = new Random();
				chosenOption = rand.nextInt(3);
				// if the random state == 0 then the player gets 200 points to his score.
				if(chosenOption == 0) {
					game.setPoints(200, true);
					Purpleflag = true;
				}
				// if the random state == 1 then the player will get a random question.
				else if(chosenOption == 1) {
					try {
						if (endIndex == Board.toIndex(game.getPurpleTile()) && !game.isGameOver()) {
							GameController.getInstance().displayQuestion(SysData.popRandomQuestion(), game);
							flag = 1;
						}
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				// if the random state == 2 then we will reset the board and keep the score and time.
				else if(chosenOption == 2) {
					GameController.getInstance().resetBoard(board);
				}
			}
			/* END PANTHER TEAM */
			
			
			if (switchTurn) {
				int points;
				if (flag == 1) {
					points = GameController.getInstance().restartTimer(false);
				} else {
					points = GameController.getInstance().restartTimer(true);
				}
				if (points > 60) {

					points -= 60;
					game.setPoints(points, false);
					if (midIndex != -1 && (board.get(endIndex) != Board.WHITE_KING && board.get(endIndex) != Board.BLACK_KING )) {
						GameController.getInstance().EatMsg(game, -points);
					} else if (Greenflag == false && Purpleflag == false)
						GameController.getInstance().PMsg(game, -points);
					else if (Greenflag == true && Purpleflag == false) {
						GameController.getInstance().GreenMsg(game, -points);
					}
					/*Panther team */
					else if (Purpleflag == true && chosenOption == 0) {
						System.out.println("yay -200");
						GameController.getInstance().PurpleMsg(game, -points);
					}
					/*Panther team */
				}

				else if (points < 60) {
					points = 60 - points;
					game.setPoints(points, true);

					if (midIndex != -1 && (board.get(endIndex) != Board.WHITE_KING && board.get(endIndex) != Board.BLACK_KING )) {
						GameController.getInstance().EatMsg(game, points);

					} else if (Greenflag == false && Purpleflag == false)
						GameController.getInstance().PMsg(game, points);
					else if (Greenflag == true && Purpleflag == false) {
						GameController.getInstance().GreenMsg(game, points);
					}
					/*Panther team */

					else if (Purpleflag == true && chosenOption == 0) {
						System.out.println("yay 200");
						GameController.getInstance().PurpleMsg(game, points);
					}
					/*Panther team */
				}
				switchTurn(game);

			}

			GameController.getInstance().resetTimer();
			return true;

		}
		Game.Buttonclicked = false;
		return false;

	}

	public static void switchTurn(Game game) {

		game.setP1Turn(!game.isP1Turn());
		game.setSkipIndex(-1);
		game.setEndIn(-1);
		game.yellowTiles();
		game.redTiles();
		game.blueTiles();
		game.setOrangeT(new ArrayList<Point>());
		game.getGreenTile().setLocation(-5, -5);
		game.getPurpleTile().setLocation(-5, -5);

	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = (byte) startIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = (byte) endIndex;
	}

	public Point getStart() {
		return Board.toPoint(startIndex);
	}

	public void setStart(Point start) {
		setStartIndex(Board.toIndex(start));
	}

	public Point getEnd() {
		return Board.toPoint(endIndex);
	}

	public void setEnd(Point end) {
		setEndIndex(Board.toIndex(end));
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public void changeWeight(double delta) {
		this.weight += delta;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "[startIndex=" + startIndex + ", " + "endIndex=" + endIndex + ", weight="
				+ weight + "]";
	}
}
