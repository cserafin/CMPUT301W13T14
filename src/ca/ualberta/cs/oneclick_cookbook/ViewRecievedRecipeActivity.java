package ca.ualberta.cs.oneclick_cookbook;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

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

}
