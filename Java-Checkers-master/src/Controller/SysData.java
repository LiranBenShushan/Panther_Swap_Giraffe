package Controller;

import model.Game;
import model.Question;
import model.historyJason;
import model.historyi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;

public class SysData {
	private static SysData singleton;
	private static historyi historyj = new historyJason();
	private static ArrayList<Game> games = new ArrayList<Game>();
	private static ArrayList<Question> questions = new ArrayList<Question>();
	private static ArrayList<Integer> temp = new ArrayList<Integer>();

	
	
	/**
	 * full constructor
	 */
	public SysData() {
		if (singleton == null) {
			singleton = this;
			reset();

		} else {
			System.out.println("data class must be a singltone !");
		}

	}

	public static ArrayList<Game> gethistory() {
		games = historyj.getGames();
		Collections.sort(games);
		return games;
	}

	/**
	 * add Game To history
	 */
	public static void addToHostory(Game game) {
		games = historyj.getGames();
		if (games.size() < 10) {
			games.add(game);
		} else {
			if (games.get(9).getmaxpoint() < game.getmaxpoint()) {
				games.remove(9);
				games.add(game);
			} else if ((games.get(9).getmaxpoint() == game.getmaxpoint())) {
				games.add(game);
			}
		}
		Collections.sort(games);
		historyj.writeGames(games);

	}

	/**
	 * this method deletes a question from database
	 * 
	 * @param question the question that needs to be deleted
	 */
	public static boolean deleteQuestion(Question question) {

		questions = getQuestions();
		for (int i = 0; i < questions.size(); i++) {
			if (questions.get(i).getText().equals(question.getText())) {
				questions.remove(i);
				historyj.writeQuestions(questions);
				return true;
			}
		}
		return true;

	}

	/**
	 * this method update a question from database
	 * 
	 * @param question the question that needs to be deleted
	 */

	public static boolean updateQuestion(Question question, Question oldQ) {
		questions = getQuestions();
		for (int i = 0; i < questions.size(); i++) {
			if (questions.get(i).getText().equals(oldQ.getText())) {
				questions.set(i, question);
				historyj.writeQuestions(questions);
				return true;
			}
		}
		return true;

	}

	public static Question findQuestion(String question) {
		questions = getQuestions();
		for (int i = 0; i < questions.size(); i++) {
			if (questions.get(i).getText().equals(question)) {
				return questions.get(i);
			}
		}
		return null;

	}

	/**
	 * this method adds a new question to the data
	 * 
	 * @param question : the question to be added
	 * @return true if question was added successfully , false otherwise
	 */

	public static boolean addQuestion(Question question) {
		questions = getQuestions();
		if (question.getAnswers().size() < 2) {
			return false;
		}

		if (question.getAnswers().get(0).equals(question.getAnswers().get(1))
				|| question.getAnswers().get(0).equals(question.getAnswers().get(2))
				|| question.getAnswers().get(0).equals(question.getAnswers().get(3)))
			return false;
		if (question.getAnswers().get(1).equals(question.getAnswers().get(2))
				|| question.getAnswers().get(1).equals(question.getAnswers().get(3)))
			return false;
		if (question.getAnswers().get(2).equals(question.getAnswers().get(3))
				&& !question.getAnswers().get(2).equals("")) {
			return false;
		}

		for (Question q : questions) {
			if (q.getText().equals(question.getText())) {
				return false;
			}

		}
		questions.add(question);
		historyj.writeQuestions(questions);
		return true;
	}

	/**
	 * this method generate a random question from the questions data
	 * 
	 * @return
	 */
	public static Question popRandomQuestion() {
		questions = getQuestions();
		int size = questions.size() - 1;
		int randNum = (int) (Math.random() * ((size) + 1));
		if (temp.size() < questions.size()) {
			if (temp.contains(randNum)) {
				while (temp.contains(randNum)) {
					randNum = (int) (Math.random() * ((size) + 1));
				}
			}

			temp.add(randNum);
		} else {
			temp.clear();
			randNum = (int) (Math.random() * ((size) + 1));
			temp.add(randNum);
		}
		return questions.get(randNum);

	}

	/**
	 * this method gets all of the questions from database
	 * 
	 * @return
	 */

	public static ArrayList<Question> getQuestions() {

		ArrayList<Question> allQuestions = new ArrayList<Question>();
		allQuestions = historyj.getQuestions();
		return allQuestions;

	}

	public static ArrayList<Game> getGames() {
		return games;
	}

	public static void setGames(ArrayList<Game> games) {
		SysData.games = games;
	}

	public static void MakeFile() {
		try {
			File myObj = new File("GameState.txt");
			if (myObj.createNewFile()) {
				System.out.println("File created: " + myObj.getName());
			} else {
				System.out.println("File already exists.");
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}

	public static boolean WriteToFile(String Text) {

		try (FileWriter fw = new FileWriter("GameState.txt", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {
			out.println(Text);
			// more code
			// more code
		} catch (IOException e) {
			return false;
		}
		return true;

	}

	/**
	 * this method reset the state of the data
	 */
	public static void reset() {
		questions = new ArrayList<>();
		games = new ArrayList<Game>();

	}

	/**
	 * this method saves the changes to the .json files
	 * 
	 * @return true if data was saved ,false otherwise
	 */
	public static boolean Save() {

		try {

			return true;

		} catch (Exception e) {

			System.out.println("somthing went wrong data was not saved");

		}
		return false;
	}

	/**
	 * this method set the game state
	 * 
	 * @return true if the state changed
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static ArrayList<String> setGameState() throws FileNotFoundException, IOException {
		File myObj = new File("GameState.txt");
		try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(new FileInputStream(myObj), Charset.forName("UTF-8")))) {
			String c;
			ArrayList<String> AllData = new ArrayList<>();
			while ((c = reader.readLine()) != null) {
				String[] tokens = c.split(",");
				for (int j = 0; j < tokens.length; j++) {
					AllData.add(tokens[j]);
				}
			}
			return AllData;
		}
	}

}
