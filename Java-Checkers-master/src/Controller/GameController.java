package Controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import View.CheckerBoard;
import View.CheckersWindow;
import model.Board;
import model.Game;
import model.Move;
import model.Question;

public class GameController {

	private static GameController singleton;

	public static GameController getInstance() {
		if (singleton == null)
			singleton = new GameController();
		return singleton;

	}

	public void yellowTiles(Graphics g, CheckerBoard cb, Game game) {
		for (int i = 0; i < game.getYellowT().size(); i++) {
			cb.paintColors(g, game.getYellowT().get(i).x, game.getYellowT().get(i).y, Color.yellow);
		}
	}

	public void redTile(Graphics g, CheckerBoard cb, Game game) {
		if (game.getRedTile().x != -5 && game.getRedTile().y != -5) {
			cb.paintColors(g, game.getRedTile().x, game.getRedTile().y, Color.red);
		}
	}

	public void blueTile(Graphics g, CheckerBoard cb, Game game) {
		if (game.getBlueTile().x != -5 && game.getBlueTile().y != -5) {
			cb.paintColors(g, game.getBlueTile().x, game.getBlueTile().y, Color.blue);
		}
	}

	public void greenTile(Graphics g, CheckerBoard cb, Game game) {
		if (game.getGreenTile().x != -5 && game.getGreenTile().y != -5) {
			cb.paintColors(g, game.getGreenTile().x, game.getGreenTile().y, Color.green);
		}
	}

	public void orangeTiles(Graphics g, CheckerBoard cb, Game game) {
		for (int i = 0; i < game.getOrangeT().size(); i++) {
			cb.paintColors(g, game.getOrangeT().get(i).x, game.getOrangeT().get(i).y, Color.orange);
		}
	}

	public void purpleTile(Graphics g, CheckerBoard cb, Game game) {
		if (game.getPurpleTile().x != -5 && game.getPurpleTile().y != -5) {
			cb.paintColors(g, game.getPurpleTile().x, game.getPurpleTile().y, new Color(128,0,128));
		}
	}
	


	public void gameOver(Game game, CheckerBoard board) {
		try {
			if (game.isGameOver()) {
				board.update(true);
			} else
				board.update(false);
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean checkTheSound() {
		return CheckersWindow.soundFlag;
	}

	public void displayQuestion(Question q, Game g) {
		View.displayQuestion dq = new View.displayQuestion(q, g);
		dq.setVisible(true);

	}

	public void updatepointsafterquestion(Game g, Question q, String Answer) {
		g.setPointAnswer(q, Answer);
		CheckersWindow.restart1();

	}

	public void blueTileMsg(Game game) {
		if (game.getBlueTile().x != -5 && game.getBlueTile().y != -5) {
			CheckersWindow.BMsg();
		}

	}

	public void redTileMsg(Game game) {
		if (game.getRedTile().x != -5 && game.getRedTile().y != -5) {
			CheckersWindow.RMsg();
		}

	}
	
	
	public void EatMsg(Game game , int point) {
			CheckersWindow.PMsg(point);
		
	}

	public void GreenMsg(Game game , int point) {
		CheckersWindow.GMsg(point);
	
}
	public void PurpleMsg(Game game , int point) {
		CheckersWindow.purpleMsg(point);
}
	
	public void QMsg(Game game , int point) {
		CheckersWindow.QMsg(point);
	
}

	public void PMsg(Game game , int point) {
		CheckersWindow.PointsMsg(point);
	
}

	public int restartTimer(boolean ToRestart) {

		return CheckersWindow.restartTimmer(ToRestart);
	}

	public int restartTotalTimer(boolean ToRestart) {

		return CheckersWindow.restartTotalTimmer(ToRestart);
	}

	public String getTotalTime() {
		return CheckersWindow.getTotalTimeG();
	}

	public boolean isOnRedTile(int index, Game game) {
		if (game.getEndIn() == -1) {
			return true;
		} else if (index == game.getEndIn()) {
			return true;
		} else
			return false;
	}

	public void restartAfterpause() {
		CheckersWindow.restartTotalTimmerAfterPause();
	}

	public void setEmptyLabel() {
		CheckersWindow.lblNewLabel_7.setText("");
		CheckersWindow.lblNewLabel_9.setText("");

	}

	public void resetTimer() {
		CheckersWindow.restart1();
	}
	
	
	public void resetBoard(Board b) {
		b.reset();
	}
	
}
