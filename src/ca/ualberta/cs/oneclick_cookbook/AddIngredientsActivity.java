package ca.ualberta.cs.oneclick_cookbook;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class AddIngredientsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_ingredients);
	}

	public void onResume() {
		super.onResume();
		refresh();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_ingredients, menu);
		return true;
	}

	// Refreshes the list show that new ingredients are show immediately
	public void refresh() {
		ListView listView = (ListView) findViewById(R.id.lViewIngredients);
		GlobalApplication app = (GlobalApplication) getApplication();
		ArrayList<String> content = app.getCurrentIngredients()
				.getStringArrayList();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, content);
		listView.setAdapter(adapter);
	}

	// Called when the user clicks add ingredient
	public void onAdd() {
		EditText quantity = (EditText) findViewById(R.id.addIngredientQuantity);
		EditText name = (EditText) findViewById(R.id.addIngredientName);

		String quantityString = quantity.getText().toString();
		Integer amount = new Integer(quantityString);
		String nameString = name.getText().toString();
		Ingredient ingredient = new Ingredient(nameString, amount, "cups");
		GlobalApplication app = (GlobalApplication) getApplication();
		Pantry pantry = app.getCurrentIngredients();
		pantry.addIngredient(ingredient);
		refresh();

		return;
	}

	public void onDeleteAll() {
		// Builds the alert dialog box
		AlertDialog.Builder prompt = new AlertDialog.Builder(this);
		prompt.setTitle("Delete All");
		prompt.setMessage("Are you sure you want to delete all ingredients? They "
				+ "will be gone... forever.");

		prompt.setNegativeButton("No", new DialogInterface.OnClickListener() {

			// User has changed their mind
			@Override
			public void onClick(DialogInterface dialog, int which) {
				return;
			}
		});
		prompt.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

			// User does want to delete all ingredients
			@Override
			public void onClick(DialogInterface dialog, int which) {
				GlobalApplication app = (GlobalApplication) getApplication();
				Pantry pantry = app.getCurrentIngredients();
				pantry.emptyPantry();
				refresh();
			}
		});
		prompt.show();		
		return;
	}

	// Called when user selects Done
	public void onDone() {
		finish();
		return;
	}

	// Handles the clicks from the user and directs them
	public void clickHandler(View v) {
		switch (v.getId()) {
		case R.id.bAddIngredient:
			onAdd();
			break;
		case R.id.deleteAllIngredient:
			onDeleteAll();
			break;
		case R.id.bIngredientsDone:
			onDone();
			break;
		}
	}

}
