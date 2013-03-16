package ca.ualberta.cs.oneclick_cookbook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class ViewFavoriteRecipeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_favorite_recipe);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_favorite_recipe, menu);
		return true;
	}

	/**
	 * Function that handles and directs clicks from the user
	 * 
	 * @param v
	 *            The view of the button that was clicked.
	 */
	public void clickHandler(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.bFavRecipeReturn:
			finish();
			break;
		case R.id.bFavRecipeDelete:
			break;
		case R.id.bFavRecipeDemote:
			break;
		case R.id.bFavRecipeEdit:
			break;
		case R.id.bFavRecipePromote:
			break;
		case R.id.bFavRecipePics:
			break;
		case R.id.bFavRecipeSend:
			break;
		}
	}

}
