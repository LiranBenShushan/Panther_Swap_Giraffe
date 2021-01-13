package model;

import java.util.ArrayList;

public interface historyi {

	ArrayList<Game> getGames();

	void writeGames(ArrayList<Game> games);

	void writeQuestions(ArrayList<Question> Questions);

	public ArrayList<Question> getQuestions();

}
