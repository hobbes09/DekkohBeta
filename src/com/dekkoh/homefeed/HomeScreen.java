package com.dekkoh.homefeed;


import com.example.dekkohbeta.R;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class HomeScreen extends FragmentActivity{
	
	// Coordinates for swipe action detector
	private float x1, x2;
	private float y1, y2;
	private static int currentIndex = 0;  //store and keep updating in shared preference

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		customizeActionBar();
		setContentView(R.layout.activity_home);
		
	}
	
	private void customizeActionBar() {
		// TODO Auto-generated method stub
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
		ActionBar actionBar = getActionBar();
		actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000ff")));
		actionBar.setStackedBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000ff")));
		
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayShowTitleEnabled(false);
		LayoutInflater layoutInflater = LayoutInflater.from(this);

		View mCustomView = layoutInflater.inflate(R.layout.custom_actionbar, null);
		// Enable buttons here
		actionBar.setCustomView(mCustomView);
		actionBar.setDisplayShowCustomEnabled(true);

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}


	// onTouchEvent () method gets called when User performs any touch event on screen 
    // Method to handle touch event like left to right swap and right to left swap
    public boolean onTouchEvent(MotionEvent touchEvent){
    	
    	switch(touchEvent.getAction()){
	    	case MotionEvent.ACTION_DOWN:
		    	{
		    		x1 = touchEvent.getX();
		            y1 = touchEvent.getY();
		            break;
		        }
		    case MotionEvent.ACTION_UP:
		        {
		        	x2 = touchEvent.getX();
		            y2 = touchEvent.getY(); 
		            //if left to right sweep event on screen
		            if ((x1 < x2) && (Math.abs(x1-x2) > Math.abs(y1-y2))){
		            	Toast.makeText(this, "Left to Right Swap Performed", Toast.LENGTH_SHORT).show();
		            }
		            // if right to left sweep event on screen
		            if ((x1 > x2) && (Math.abs(x1-x2) > Math.abs(y1-y2))){
		                Toast.makeText(this, "Right to Left Swap Performed", Toast.LENGTH_SHORT).show();
		            }
		            // if UP to Down sweep event on screen
		            if ((y1 < y2) && (Math.abs(x1-x2) < Math.abs(y1-y2))){
		            	Toast.makeText(this, "UP to Down Swap Performed", Toast.LENGTH_SHORT).show();
		            }
		            //if Down to UP sweep event on screen
		            if ((y1 > y2) && (Math.abs(x1-x2) < Math.abs(y1-y2))){
		            	Toast.makeText(this, "Down to UP Swap Performed", Toast.LENGTH_SHORT).show();
		            	currentIndex++;
		            	animation(currentIndex);
		            }
		            break;
		       }
			}

    	return false;
    }

	private void animation(int currentIndex) {
		// TODO Auto-generated method stub
		QuestionFragment questionFragment = new QuestionFragment();
		Bundle questionFragArgs = new Bundle();
		questionFragArgs.putString("LOCATION", "Location1");
		questionFragArgs.putString("USERNAME", "Username1");
		questionFragArgs.putString("QUESTION", "Question1 ?? + currentIndex = " + currentIndex);
		questionFragment.setArguments(questionFragArgs);
		
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.setCustomAnimations(R.animator.frag_slide_in_from_bottom, 0);
	    transaction.replace(R.id.contentHomeActivity, questionFragment);
	    transaction.commit();
	
	}
	
}
