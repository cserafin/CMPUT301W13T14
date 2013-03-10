package ca.ualberta.cs.oneclick_cookbook;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class CreateRecipeActivity extends Activity {

	private int position = -1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_recipe);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			position = extras.getInt("position");
		}
	}

	// Called when the app resumes
	public void onResume() {
		super.onResume();
		GlobalApplication app = (GlobalApplication) getApplication();
		Recipe recipe = app.getCurrentRecipe();
		setInfo(recipe);
	}
	
	// Used to make sure that no matter what, current recipe is set to
	// null on exit of create recipe
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	super.onKeyDown(keyCode, event);
	    	if (keyCode == KeyEvent.KEYCODE_BACK) {
	    		GlobalApplication app = (GlobalApplication) getApplication();
	    		app.setCurrentRecipe(null);
	    		finish();
	    		return true;
	    	}
	    	return false;
	    }


	// Sets the info of the recipe based on the current recipe
	public void setInfo(Recipe recipe) {
		EditText name = (EditText) findViewById(R.id.createEnterName);
		EditText steps = (EditText) findViewById(R.id.createEnterSteps);

		name.setText(recipe.getName());
		steps.setText(recipe.getSteps());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_recipe, menu);
		return true;
	}

	// Handles the clicks from the user and directs them
	public void clickHandler(View v) {
		switch (v.getId()) {
		case R.id.bCreateDone:
			onDone();
			break;
		case R.id.bCreateDelete:
			onDelete();
			break;
		case R.id.bCreateAddIngredient:
			onAddIngredients();
			break;
		case R.id.bCreateAddPhoto:
			onAddPhoto();
			break;
		case R.id.bCreateRemovePhoto:
			onRemovePhoto();
			break;
		}
	}

	// Called when the user clicks to add ingredients
	public void onAddIngredients() {
		EditText name = (EditText) findViewById(R.id.createEnterName);
		EditText steps = (EditText) findViewById(R.id.createEnterSteps);
		GlobalApplication app = (GlobalApplication) getApplication();
		//Do this to save any user text they may have
		app.setCurrentRecipe(new Recipe(name.getText().toString(), app.getCurrentRecipe().getIngredients(),
					steps.getText().toString()));
		Intent intent = new Intent(app, AddIngredientsActivity.class);
		startActivity(intent);
	}

	// Called when user clicks Done
	// TODO Add ingredients section
	public void onDone() {
		EditText name;
		EditText steps;

		name = (EditText) findViewById(R.id.createEnterName);
		steps = (EditText) findViewById(R.id.createEnterSteps);
		String namestring = name.getText().toString();
		String stepstring = steps.getText().toString();
		GlobalApplication app = (GlobalApplication) getApplication();

		Recipe r = new Recipe(namestring, app.getCurrentRecipe().getIngredients(),
				stepstring);

		// TODO Add appropriate feedback here
		if (r.isValidInfo() != Constants.GOOD) {
			return;
		}

		// TODO Add upload section and save section

		// Remove the old recipe before adding the new one, if editing
		if (position != -1) {
			app.getCurrentUser().getUserRecipes().remove(position);
		}
		app.getCurrentUser().addRecipe(r);

		// Set null so future recipes start fresh
		app.setCurrentRecipe(null);
		finish();
		return;
	}

	// Called when user clicks Delete
	// TODO Add prompt before delete
	public void onDelete() {
		// Builds the alert dialog box
		AlertDialog.Builder prompt = new AlertDialog.Builder(this);
		prompt.setTitle("Delete All");
		prompt.setMessage("Are you sure you want to delete this recipe? It "
				+ "will be gone... forever.");

		prompt.setNegativeButton("No", new DialogInterface.OnClickListener() {

			// User has changed their mind
			@Override
			public void onClick(DialogInterface dialog, int which) {
				return;
			}
		});
		prompt.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

			// User does want to delete this recipe
			@Override
			public void onClick(DialogInterface dialog, int which) {
				GlobalApplication app = (GlobalApplication) getApplication();
				// If the user is editing a recipe
				if (position != -1) {
					app.getCurrentUser().getUserRecipes().remove(position);
				}
				// Set null so future recipes start fresh
				app.setCurrentRecipe(null);
				finish();
			}
		});
		prompt.show();
		return;
	}

	// Called when user clicks Add Photo
	public void onAddPhoto() {
		return;
	}

	// Called when user clicks Remove Photo
	public void onRemovePhoto() {
		return;
	}

}
