package com.dekkoh.myprofile;

import com.example.dekkohbeta.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MyProfile extends Fragment {
	
	public MyProfile(){}
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
  
        View rootView = inflater.inflate(R.layout.myprofile_fragment, container, false);
          
        return rootView;
    }

}