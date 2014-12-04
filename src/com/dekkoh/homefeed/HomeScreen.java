package com.dekkoh.homefeed;


import com.dekkoh.actionbarmenu.ActionbarMenu;
import com.example.dekkohbeta.R;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class HomeScreen extends FragmentActivity implements OnClickListener{
	
	// Coordinates for swipe action detector
	private float x1, x2;
	private float y1, y2;
	private static int currentIndex = 0;  //store and keep updating in shared preference
	
	ImageButton ibMenu;
	ImageButton ibPost;
	ImageButton ibMap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);	
		customizeActionBar();

		setContentView(R.layout.activity_home);
		
		//ActionbarMenu.setActivityInformation(getApplicationContext(), getWindow(), getActionBar());
		//ActionbarMenu.getActionBarMenuInstance().customizeActionBar();
				initialize();
	}
	
	private void initialize() {
		// TODO Auto-generated method stub
		ibMenu = (ImageButton)findViewById(R.id.ibMenu);
		ibMap = (ImageButton)findViewById(R.id.ibMap);
		ibPost = (ImageButton)findViewById(R.id.ibPost);
		ibMenu.setOnClickListener(this);
		ibMap.setOnClickListener(this);
		ibPost.setOnClickListener(this);
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
		            	FragmentTransition.getFragmentTransitionInstance().getQuestion(currentIndex, getSupportFragmentManager());
		            }
		            break;
		       }
			}

    	return false;
    }

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch(view.getId()){
			case R.id.ibMenu:
							//ActionbarMenu.getActionBarMenuInstance().getPopupWindowMenu().showAsDropDown(view);
							break;
							
			case R.id.ibMap:
							Toast.makeText(getApplicationContext(), "Map Clicked", Toast.LENGTH_SHORT).show();
							break;
							
			case R.id.ibPost:
							Toast.makeText(getApplicationContext(), "Post Clicked", Toast.LENGTH_SHORT).show();
							break;
		}
		
	}
    
	
}
