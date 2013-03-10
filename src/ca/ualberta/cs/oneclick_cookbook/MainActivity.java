package ca.ualberta.cs.oneclick_cookbook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

// In the current state of the program, the user is 
// always logged in as John Doe, and the sessions aren't
// saved. We can add changes to this later
public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// Set the welcome message (default account is John Doe
		TextView t = (TextView) findViewById(R.id.tWelcomeMessage);
		GlobalApplication app = (GlobalApplication) getApplication();
		t.setText("Hello, " + app.getCurrentUser().getUserName() + "!");
	}
	
	public void onResume() {
		super.onResume();
		// The below code is neccessary to avoid recipe staying as 
		GlobalApplication app = (GlobalApplication) getApplication();
		app.setCurrentRecipe(null);
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	// Handles the button clicks for the main activity
	//TODO Implement some of the handler functions
	public void clickHandler(View v) {
    	Intent intent;
		switch(v.getId()) {
    	case R.id.bHomeLogin:
    		break;
    	case R.id.bHomeCreate:
    		intent = new Intent(this.getApplicationContext(), CreateRecipeActivity.class);
    		startActivity(intent);
    		break;
    	case R.id.bHomeRecipes:
    		intent = new Intent(this.getApplicationContext(), ViewRecipesActivity.class);
    		startActivity(intent);
    		break;
    	case R.id.bHomeHistory:
    		break;
    	case R.id.bHomePantry:
    		intent = new Intent(this.getApplicationContext(), ManagePantryActivity.class);
    		startActivity(intent);
    		break;
    	case R.id.bHomeSearch:
    		break;
    	}
    }
}
