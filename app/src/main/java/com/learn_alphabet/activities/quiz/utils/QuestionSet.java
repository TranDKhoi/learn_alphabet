package com.learn_alphabet.activities.quiz.utils;

public class QuestionSet {

	String question;
	int id;
	int ansImage;
	int audio;

	public QuestionSet(int ansImage, String question, int id, int audio) {
		this.question = question;
		this.id = id;
		this.ansImage = ansImage;
		this.audio = audio;
	}

	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getAnsImage() {
		return ansImage;
	}
	public void setAnsImage(int ansImage) {
		this.ansImage = ansImage;
	}

	public int getAudio() {
		return audio;
	}
	public void setAudio(int audio) {
		this.audio = audio;
	}

}