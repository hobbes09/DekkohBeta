package com.dekkoh.homefeed;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.dekkohbeta.R;

public class FragmentTransition {
	
	private String location;
	private String question;
	private String username;
	private int animationChoice;
	// Add field for user image
	//Add field for image

	private static FragmentTransition fragmentTransition = new FragmentTransition();
	
	public static FragmentTransition getFragmentTransitionInstance(){
		return fragmentTransition;
	}
	
	public void getQuestion(int index, FragmentManager supportFragmentManager){
		QuestionFragment questionFragment = new QuestionFragment();
		Bundle questionFragArgs = new Bundle();
		questionFragArgs.putString("LOCATION", "Location1");
		questionFragArgs.putString("USERNAME", "Username1");
		questionFragArgs.putString("QUESTION", "Question1 ?? + currentIndex = " + index);
		questionFragment.setArguments(questionFragArgs);
		
		FragmentTransaction transaction = supportFragmentManager.beginTransaction();
		transaction.setCustomAnimations(R.animator.frag_slide_in_from_bottom, 0);
	    transaction.replace(R.id.contentHomeActivity, questionFragment);
	    transaction.commit();	
	}
}
