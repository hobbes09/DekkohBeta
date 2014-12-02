package com.dekkoh.homefeed;

import com.example.dekkohbeta.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class QuestionFragment extends Fragment{
	
	private TextView tvLocation;
	private TextView tvUsername;
	private TextView tvQuestion;
	private ImageView ivHomeImage;
	
	QuestionFragment(){		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.question_fragment, null);
		
		initialize(root);
		setValues();
		
        return root;		
	}

	private void setValues() {
		tvLocation.setText(getArguments().getString("LOCATION"));
		tvUsername.setText(getArguments().getString("USERNAME"));
		tvQuestion.setText(getArguments().getString("QUESTION"));
		//ivHomeImage.setImageBitmap(getArguments().get);
		//root.setBackgroundColor(getArguments().getInt(COLOR));
	}

	private void initialize(View root) {
		tvLocation = (TextView)root.findViewById(R.id.tvLocation);
		tvUsername = (TextView)root.findViewById(R.id.tvUsername);
		tvQuestion = (TextView)root.findViewById(R.id.tvQuestion);
		ivHomeImage = (ImageView)root.findViewById(R.id.ivHomeImage);
	}

}
