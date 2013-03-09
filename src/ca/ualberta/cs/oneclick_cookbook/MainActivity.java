package ca.ualberta.cs.oneclick_cookbook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings.System;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	// Handles the button clicks for the main activity
	//TODO Implement some of the handler functions
	public void clickHandler(View v) {
    	switch(v.getId()) {
    	case R.id.bHomeLogin:
    		break;
    	case R.id.bHomeCreate:
    		Intent intent = new Intent(this.getApplicationContext(), CreateRecipeActivity.class);
    		startActivity(intent);
    		break;
    	case R.id.bHomeViewRecipes:
    		break;
    	case R.id.bViewRecipeHistory:
    		break;
    	case R.id.bHomeManagePantry:
    		break;
    	case R.id.bHomeSearchRecipes:
    		break;
    	}
    }
}
