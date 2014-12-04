package com.dekkoh.actionbarmenu;

import java.util.ArrayList;
import java.util.List;

import com.example.dekkohbeta.R;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.PopupWindow;

public class ActionbarMenu {
	
	private String menuContents[];  
	private PopupWindow popupWindowMenu;	
	private List<String> menuList = new ArrayList<String>();
	
	private Context context;
	private Window window;
	private ActionBar actionbar;
	
	public static void setActivityInformation(Context context, Window window, ActionBar actionbar){
		actionbarMenu.context = context;
		actionbarMenu.window = window;
		actionbarMenu.actionbar = actionbar;
	}
	
	private static ActionbarMenu actionbarMenu = new ActionbarMenu();
	
	private ActionbarMenu(){
		menuList.add("My Profile");
		menuList.add("My Activity");
		menuList.add("Following");
		menuList.add("Messages");
		menuList.add("Settings");
		
		menuContents = new String[menuList.size()];
		menuList.toArray(menuContents);
		
		popupWindowMenu = popupWindowMenu();
	};
	
	
	public static ActionbarMenu getActionBarMenuInstance(){
		return actionbarMenu;
	}
	
	public PopupWindow getPopupWindowMenu(){
		return popupWindowMenu;
	}
	
	public void customizeActionBar(){
		window.requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
		ActionBar actionBar = actionbar;
		actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000ff")));
		actionBar.setStackedBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000ff")));
		
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayShowTitleEnabled(false);
		LayoutInflater layoutInflater = LayoutInflater.from(context);

		View mCustomView = layoutInflater.inflate(R.layout.custom_actionbar, null);
		// Enable buttons here
		actionBar.setCustomView(mCustomView);
		actionBar.setDisplayShowCustomEnabled(true);

	}
	
	
	private PopupWindow popupWindowMenu() {
		// TODO Auto-generated method stub
		PopupWindow popupWindow = new PopupWindow();
		//ListView menuListView = new ListView();
		return popupWindow;
	}

}
