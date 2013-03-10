package ca.ualberta.cs.oneclick_cookbook;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

public class ManagePantryActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manage_pantry);
	}

	public void onResume() {
		super.onResume();
		refresh();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.manage_pantry, menu);
		return true;
	}
	
	// Refreshes the list show that new ingredients are show immediately
	public void refresh() {
		// Make the spinner for the units
		Spinner measurements = (Spinner) findViewById(R.id.pantryIngredientMeasurement);
		ArrayList<String> units = new ArrayList<String>();
		
		// Goes through and converts all of the positions to units
		for (int i=0; i<Constants.NUM_OF_UNITS; i++) {
			units.add(Constants.getUnitFromPosition(i));
		}
		
		ArrayAdapter<String> unitsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
				units);
		// Hook up the adapter
		measurements.setAdapter(unitsAdapter);
		
		// Set up the list view of items
		ListView listView = (ListView) findViewById(R.id.lViewPantry);
		GlobalApplication app = (GlobalApplication) getApplication();
		ArrayList<String> content = app.getCurrentUser().getUserPantry().getStringArrayList();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, content);
		listView.setAdapter(adapter);
	}
	
	// Called when the user clicks add ingredient
	public void onAdd() {
		EditText quantity = (EditText) findViewById(R.id.pantryIngredientQuantity);
		EditText name = (EditText) findViewById(R.id.pantryIngredientName);
		Spinner units = (Spinner) findViewById(R.id.pantryIngredientMeasurement);
		
		// Get the units for the ingredients
		int position = units.getSelectedItemPosition();
		String unitsString = Constants.getUnitFromPosition(position);
		
		String quantityString = quantity.getText().toString();
		
		// Do the integer conversion like this just in case no number is entered
		Integer amount;
		try {
			amount = new Integer(quantityString);
		} catch (NumberFormatException e) {
			// If we get here, they did't enter a valid number
			return;
		}
		
		String nameString = name.getText().toString();
		Ingredient ingredient = new Ingredient(nameString, amount, unitsString);
		
		// Check for ingredient validity
		if (ingredient.isValidInfo() != Constants.GOOD) {
			return; // They entered wrong info
		}
		
		GlobalApplication app = (GlobalApplication) getApplication();
		Pantry pantry = app.getCurrentUser().getUserPantry();
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
				Pantry pantry = app.getCurrentUser().getUserPantry();
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
		case R.id.bAddIngredientToPantry:
			onAdd();
			break;
		case R.id.bDeleteAllPantry:
			onDeleteAll();
			break;
		case R.id.bPantryDone:
			onDone();
			break;
		}
	}

}
