package com.dekkoh.models;

import android.graphics.Bitmap;

public class Question {
	
	private String question;
	private String location;
	private String username;
	private Bitmap userImage;
	private Bitmap questionImage;
	
	public long questionIndex;		//unique ===============
	
	public static class QuestionBuilder{
		//Required parameters
		private String question;
		private String location;
		private String username;
		//Optional parameters
		private Bitmap userImage;	// default value to be assigned
		private Bitmap questionImage;  // default value to be assigned
		
		public QuestionBuilder(String question, String location, String username) {
			this.question = question;
			this.location = location;
			this.username = username;
		}
		
		public QuestionBuilder setUserImage(Bitmap image){
			userImage = image;
			return this;
		}
		
		public QuestionBuilder setQuestionImage(Bitmap image){
			questionImage = image;
			return this;
		}
		
		public Question build(){
			return new Question(this);
		}	
		
	}
	
	private Question(QuestionBuilder questionBuilder){
		question = questionBuilder.question;
		location = questionBuilder.location;
		username = questionBuilder.username;
		userImage = questionBuilder.userImage;
		questionImage = questionBuilder.questionImage;
	}
	
}
