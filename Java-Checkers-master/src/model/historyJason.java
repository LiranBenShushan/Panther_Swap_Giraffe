package model;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class historyJason implements historyi {

	@Override
	public ArrayList<Game> getGames() {
		ArrayList<Game> games = new ArrayList<Game>();
		try {

			Object obj = new JSONParser().parse(new FileReader("history.json"));
			JSONObject jo = (JSONObject) obj;
			JSONArray arr = (JSONArray) jo.get("games");

			for (Object o : arr) {

				JSONObject game = (JSONObject) o;
				String playerName = game.get("Name").toString();
				int score = Integer.parseInt(game.get("score").toString());
				String TotalTime = game.get("TotalTime").toString();

				Game gm = new Game(playerName, score, TotalTime);
				games.add(gm);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		Collections.sort(games);
		return games;
	}

	@Override
	public void writeGames(ArrayList<Game> games) {
		try {

			JSONArray array = new JSONArray();
			JSONObject quesJson = new JSONObject();
			JSONObject quesDetails;
			for (Game gameToAdd : games) {
				quesDetails = new JSONObject();
				quesDetails.put("Name", gameToAdd.getWinner());
				quesDetails.put("score", gameToAdd.getmaxpoint());
				quesDetails.put("TotalTime", gameToAdd.getTotalTime());

				array.add(quesDetails);
			}
			quesJson.put("games", array);
			FileWriter file;
			file = new FileWriter("history.json");
			file.write(quesJson.toString());
			file.flush();
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public ArrayList<Question> getQuestions() {
		ArrayList<Question> questions = new ArrayList<Question>();

		Object obj;
		try {
			obj = new JSONParser().parse(new FileReader("questions1.json"));
			JSONObject jo = (JSONObject) obj;
			JSONArray arr = (JSONArray) jo.get("questions");

			for (Object o : arr) {
				JSONObject question = (JSONObject) o;
				String Text = (String) question.get("question");
				JSONArray answers = (JSONArray) question.get("answers");
				@SuppressWarnings("unchecked")
				ArrayList<String> qs = (ArrayList<String>) answers;
				String correct1 = (String) question.get("correct_ans");
				model.Difficulty sDifficulty = null;
				String Difficultly = (String) question.get("Level");
				String team = (String) question.get("Team");
				if (Difficultly.equals("1")) {
					sDifficulty = Difficulty.easy;
				} else if (Difficultly.equals("2")) {
					sDifficulty = Difficulty.mediocre;
				} else if (Difficultly.equals("3")) {
					sDifficulty = Difficulty.difficult;
				}
				Question q = new Question(Text, qs, correct1, sDifficulty, team);
				questions.add(q);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return questions;
	}

	
	@Override
	public void writeQuestions(ArrayList<Question> questions) {

		try {
			JSONArray array = new JSONArray();
			JSONObject quesJson = new JSONObject();
			JSONObject quesDetails;
			for (Question quesToAdd : questions) {
				quesDetails = new JSONObject();

				quesDetails.put("question", quesToAdd.getText());

				ArrayList<String> answers = new ArrayList<String>();
				answers = quesToAdd.getAnswers();
				quesDetails.put("answers", answers);
				quesDetails.put("correct_ans", quesToAdd.getCorrect_ans());

				if (Difficulty.easy.equals(quesToAdd.getLevel())) {
					quesDetails.put("Level", "1");
				} else if (Difficulty.mediocre.equals(quesToAdd.getLevel())) {
					quesDetails.put("Level", "2");
				} else if (Difficulty.difficult.equals(quesToAdd.getLevel())) {
					quesDetails.put("Level", "3");
				}

				quesDetails.put("Team", "Giraffe");

				array.add(quesDetails);
			}
			quesJson.put("questions", array);
			FileWriter file;
			file = new FileWriter("questions1.json");
			file.write(quesJson.toString());
			file.flush();
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
