package model;

import java.util.ArrayList;

public class Question {
	private String Text;
	private ArrayList<String> answers = new ArrayList<String>();
	private String Correct_ans;
	private Difficulty level;
	private String team;

	public Question(String text, ArrayList<String> answers, String Correct_ans, Difficulty difficulty, String team) {
		super();
		this.Text = text;
		this.level = difficulty;
		this.team = "Giraffe";
		this.Correct_ans = Correct_ans;
		this.answers = answers;

	}

	public String getText() {
		return Text;
	}

	public void setText(String text) {
		Text = text;
	}

	public ArrayList<String> getAnswers() {
		return answers;
	}

	public void setAnswers(ArrayList<String> answers) {
		this.answers = answers;
	}

	public String getCorrect_ans() {
		return Correct_ans;
	}

	public void setCorrect_ans(String correct_ans) {
		Correct_ans = correct_ans;
	}

	public Difficulty getLevel() {
		return level;
	}

	public void setLevel(Difficulty level) {
		this.level = level;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	@Override
	public String toString() {
		return "Question [Text=" + Text + ", answers=" + answers + ", Correct_ans=" + Correct_ans + ", level=" + level
				+ ", team=" + team + "]";
	}

}
