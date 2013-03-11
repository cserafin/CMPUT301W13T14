package ca.ualberta.cs.oneclick_cookbook;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemLongClickListener;

public class AddIngredientsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_ingredients);
	}

	// Called when activity restarts
	// Should refresh the screen
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
		setUpSpinner();
		setUpListView();
	}
	
	// Sets up the measurement spinner
	public void setUpSpinner() {
		Spinner measurements = (Spinner) findViewById(R.id.addIngredientMeasurement);
		ArrayList<String> units = new ArrayList<String>();
		
		// Goes through and converts all of the positions to units
		for (int i=0; i<Constants.NUM_OF_UNITS; i++) {
			units.add(Constants.getUnitFromPosition(i));
		}
		
		ArrayAdapter<String> unitsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
				units);
		measurements.setAdapter(unitsAdapter);
	}
	
	public void setUpListView() {
		// Set up the list view
		ListView listView = (ListView) findViewById(R.id.lViewIngredients);
		GlobalApplication app = (GlobalApplication) getApplication();
		ArrayList<String> content = app.getCurrentRecipe().getIngredients().getStringArrayList();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, content);
		listView.setAdapter(adapter);
		// Set what happens when a user clicks on an item
		listView.setOnItemLongClickListener(new OnItemLongClickListener() {
			
			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View v,
					final int position, long arg3) {
				// Builds the alert dialog box
				AlertDialog.Builder prompt = new AlertDialog.Builder(v.getContext());
				prompt.setTitle("Delete Ingredient");
				prompt.setMessage("Are you sure you want to delete this ingredient? It "
						+ "will be gone... forever.");

				prompt.setNegativeButton("No", new DialogInterface.OnClickListener() {

					// User has changed their mind
					@Override
					public void onClick(DialogInterface dialog, int which) {
						return;
					}
				});
				prompt.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

					// User does want to delete the ingredient they are long pressing
					@Override
					public void onClick(DialogInterface dialog, int which) {
						//TODO Add network and local storage deletion here
						GlobalApplication app = (GlobalApplication) getApplication();
						Pantry pantry = app.getCurrentRecipe().getIngredients();
						pantry.removeIngredient(position);
						refresh();
					}
				});
				prompt.show();			
				return false;
			}
			
		});
	}

	// Called when the user clicks add ingredient
	public void onAdd() {
		EditText quantity = (EditText) findViewById(R.id.addIngredientQuantity);
		EditText name = (EditText) findViewById(R.id.addIngredientName);
		Spinner units = (Spinner) findViewById(R.id.addIngredientMeasurement);
		
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
		Pantry pantry = app.getCurrentRecipe().getIngredients();
		pantry.addIngredient(ingredient);
		refresh();

		return;
	}

	// Called when the user clicks DeleteAll
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
				Pantry pantry = app.getCurrentRecipe().getIngredients();
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
