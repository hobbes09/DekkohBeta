package com.dekkoh.settings;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dekkohbeta.R;

public class Settings extends Fragment {
	
	public Settings(){}
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
  
        View rootView = inflater.inflate(R.layout.settings_fragment, container, false);
          
        return rootView;
    }

}
