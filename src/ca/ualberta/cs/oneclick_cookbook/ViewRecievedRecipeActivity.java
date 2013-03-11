package ca.ualberta.cs.oneclick_cookbook;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

public class ViewRecievedRecipeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_recieved_recipe);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_recieved_recipe, menu);
		return true;
	}
	
	// Handles clicks from the user
	public void clickHandler(View v) {
		switch(v.getId()) {
		case R.id.bFriRecipeReturn:
			finish();
			break;
		case R.id.bFriRecipeAccept:
			//TODO Add accept code here
			break;
		case R.id.bFriRecipeDecline:
			//TODO Add decline code here
			break;
		}
	}

}
