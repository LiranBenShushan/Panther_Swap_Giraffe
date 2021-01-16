package tests;

import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;
import model.Difficulty;
import model.Game;
import model.Question;
import Controller.SysData;

public class JUnitTests {

	@Test
	public void QuestionTest() {
		String content = "What's my name?";
		int number = 1;
		Difficulty difficultly = Difficulty.easy;
		ArrayList<String> answers = new ArrayList<String>();
		answers.add("Sulafa");
		answers.add("Samya");
		answers.add("Sameh");
		answers.add("Samer");
		String CorrectAnswer1 = "Samer";
		String CorrectAnswer2 = null;
		boolean isMultipleChoice = false;
		Question q = new Question( content, answers,CorrectAnswer1,difficultly,"Giraffe");
		Assert.assertNotNull(q);
		
	}

	@Test
	public void AddQuestionTest() {
		int number = 1;
		boolean isMultipleChoice = true;
		String content = "Who ar1e the Females here";
		Difficulty difficultly = Difficulty.easy;
		ArrayList<String> answers = new ArrayList<String>();
		answers.add("Sulafa");
		answers.add("Sameh1");
		answers.add("Samya");
		answers.add("Samer");
		String correct1 = "Sulafa";
		//String correct2 = "Samya";

		Question q = new Question( content, answers,correct1,difficultly,"Giraffe");
		int questions = SysData.getQuestions().size();

		SysData.addQuestion(q);
		Assert.assertNotEquals(questions, SysData.getQuestions().size());
	}

	@Test
	public void AddGameToHistoryTest() {

		String Winner = "Sameh";
		int maxPoints = 200;
		String TotalTime = "01:30";
		Game game = new Game(Winner, maxPoints, TotalTime);
		int games = SysData.getGames().size();
		SysData.addToHostory(game);
		;

		Assert.assertNotEquals(games, SysData.getGames().size());
	}

	@Test
	public void PopRandomQuestionTest() {

		Question question = SysData.popRandomQuestion();
		Assert.assertNotNull(question);

	}

	@Test
	public void DeleteQuestionTest() {

		ArrayList<String> answers = new ArrayList<String>();
		int number = 2;
		boolean isMultipleChoice = true;
		String content = "Who are the men here";
		Difficulty difficultly = Difficulty.easy;
		answers.add("Sulafa");
		answers.add("Sameh");
		answers.add("Samya");
		answers.add("Samer");
		String correct1 = "Samer";
		String correct2 = "Sameh";

		Question q = new Question( content, answers,correct1,difficultly,"Giraffe");
		SysData.addQuestion(q);
		int quesNo = SysData.getQuestions().size();
		SysData.deleteQuestion(q);

		Assert.assertNotEquals(quesNo, SysData.getQuestions().size());
	}

}