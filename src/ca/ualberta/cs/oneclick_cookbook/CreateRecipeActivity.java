package ca.ualberta.cs.oneclick_cookbook;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class CreateRecipeActivity extends Activity {

	// We can add a bundle here to reuse this activity for editing
	// ie. pass 1 for edit, 0 for create
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_recipe);
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
		case R.id.bCreateAddPhoto:
			onAddPhoto();
			break;
		case R.id.bCreateRemovePhoto:
			onRemovePhoto();
			break;
		}
	}
	
	// Called when user clicks Done
	//TODO Add ingredients section
	public void onDone() {
		EditText name;
		EditText steps;
		
		name = (EditText) findViewById(R.id.createEnterName);
		steps = (EditText) findViewById(R.id.createEnterSteps);
		
		String namestring = name.getText().toString();
		String stepstring = steps.getText().toString();
		
		Recipe r = new Recipe(namestring, null, stepstring);
		
		//TODO Add upload section and save section
		GlobalApplication g = (GlobalApplication) getApplication();
		g.getCurrent().addRecipe(r);
		
		finish();
		return;
	}
	
	// Called when user clicks Delete
	//TODO Add prompt before delete
	public void onDelete() {
		finish();
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
