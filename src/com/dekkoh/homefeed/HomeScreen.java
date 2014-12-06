package com.dekkoh.homefeed;

import java.util.ArrayList;

import com.android.following.Following;
import com.dekkoh.messages.Messages;
import com.dekkoh.myactivity.MyActivity;
import com.dekkoh.myprofile.MyProfile;
import com.dekkoh.settings.Settings;
import com.dekkoh.slidingmenu.NavDrawerItem;
import com.dekkoh.slidingmenu.NavDrawerListAdapter;
import com.example.dekkohbeta.R;

import android.app.ActionBar;
import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class HomeScreen extends FragmentActivity implements OnClickListener{
	
	// Coordinates for swipe action detector
	private float x1, x2;
	private float y1, y2;
	
	
	//ImageButton ibMenu;
	ImageButton ibPost;
	ImageButton ibMap;
	TextView tvTitle;
	
	private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
 
    // nav drawer title
    private CharSequence mDrawerTitle;
 
    // used to store app title
    private CharSequence mTitle;
 
    // slide menu items
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;
 
    private ArrayList<NavDrawerItem> navDrawerItems;
    private NavDrawerListAdapter adapter;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);	
		customizeActionBar();	// MUST BE PLACED BEFORE setContentView()
		setContentView(R.layout.activity_home);	
		initialize(savedInstanceState);
	}
	
	private void initialize(Bundle savedInstanceState) {
		//ibMenu = (ImageButton)findViewById(R.id.ibMenu);
		ibMap = (ImageButton)findViewById(R.id.ibMap);
		ibPost = (ImageButton)findViewById(R.id.ibPost);
		tvTitle = (TextView)findViewById(R.id.tvTitle);
		//ibMenu.setOnClickListener(this);
		ibMap.setOnClickListener(this);
		ibPost.setOnClickListener(this);
		
		navigationDrawerInitialisation(savedInstanceState);
	}

	private void customizeActionBar() {	
		// TODO Auto-generated method stub
		//getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
		ActionBar actionBar = getActionBar();
		//actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000ff")));
		//actionBar.setStackedBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000ff")));
		
		//actionBar.setDisplayShowHomeEnabled(false);
		//actionBar.setDisplayShowTitleEnabled(true);
		LayoutInflater layoutInflater = LayoutInflater.from(this);

		View mCustomView = layoutInflater.inflate(R.layout.custom_actionbar, null);
		// Enable buttons here
		actionBar.setCustomView(mCustomView);
		actionBar.setDisplayShowCustomEnabled(true);
	}
	

	private void navigationDrawerInitialisation(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		mTitle = mDrawerTitle = getTitle();
		 
        // load slide menu items
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
 
        // nav drawer icons from resources
        navMenuIcons = getResources()
                .obtainTypedArray(R.array.nav_drawer_icons);
 
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);
 
        navDrawerItems = new ArrayList<NavDrawerItem>();
 
        // adding nav drawer items to array
        // Home
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
        // Find People
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
        // Photos
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
        // Communities, Will add a counter here
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1), true, "22"));
        // Pages
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));

         
 
        // Recycle the typed array
        navMenuIcons.recycle();
        
        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());
 
        // setting the nav drawer list adapter
        adapter = new NavDrawerListAdapter(getApplicationContext(),
                navDrawerItems);
        mDrawerList.setAdapter(adapter);
 
        // enabling action bar app icon and behaving it as toggle button
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
 
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer, //nav menu toggle icon
                R.string.app_name, // nav drawer open - description for accessibility
                R.string.app_name // nav drawer close - description for accessibility
        ){
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                // calling onPrepareOptionsMenu() to show action bar icons
                invalidateOptionsMenu();
            }
 
            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                // calling onPrepareOptionsMenu() to hide action bar icons
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
 
        if (savedInstanceState == null) {
            // on first time display view for first nav item
            displayView(-1);	//Display HomeFeed here 
        }
	}
	
	/**
     * Slide menu item click listener
     * */
    private class SlideMenuClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                long id) {
            // display view for selected nav drawer item
            displayView(position);
        }
    }
 
	

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
 
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // toggle nav drawer on selecting action bar app icon/title
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action bar actions click
        switch (item.getItemId()) {
        case R.id.action_settings:
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
    
    
    /***
     * Called when invalidateOptionsMenu() is triggered
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // if nav drawer is opened, hide the action items
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_settings).setVisible(false);
        //menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }
    
    
    /**
     * Diplaying fragment view for selected nav drawer list item
     * */
    private void displayView(int position) {
        // update the main content by replacing fragments
        Fragment fragment = null;
        switch (position) {
        case 0:
            fragment = new MyProfile();
            break;
        case 1:
            fragment = new MyActivity();
            break;
        case 2:
            fragment = new Following();
            break;
        case 3:
            fragment = new Messages();
            break;
        case 4:
            fragment = new Settings();
            break;
 
        default:
            break;
        }
 
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.contentHomeActivity, fragment).commit();
 
            // update selected item and title, then close the drawer
            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            setTitle(navMenuTitles[position]);
            mDrawerLayout.closeDrawer(mDrawerList);
        } 
        else {
        	fragment = new QuestionFragment();
        	FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
    		transaction.setCustomAnimations(R.animator.frag_slide_in_from_bottom, 0);
    	    transaction.replace(R.id.contentHomeActivity, fragment);
    	    transaction.commit();	
        }
    }
 
    
    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }
 
    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */
 
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }
 
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

	@Override
	public void onClick(View view) {

		switch(view.getId()){
			/*case R.id.ibMenu:
							Toast.makeText(getApplicationContext(), "Menu Clicked", Toast.LENGTH_SHORT).show();
							break;*/
							
			case R.id.ibMap:
							Toast.makeText(getApplicationContext(), "Map Clicked", Toast.LENGTH_SHORT).show();
							break;
							
			case R.id.ibPost:
							Toast.makeText(getApplicationContext(), "Post Clicked", Toast.LENGTH_SHORT).show();
							break;
		}
		
	}
	
}
