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

/**
 * Class that allows for the addition of ingredients to recipes. Associates the
 * entered ingredients with the current recipe, and adds them accordingly.
 * 
 * @author Kenneth Armstrong
 * 
 */
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

	/**
	 * Function that is called whenever the screen should be updated. Updates
	 * the spinners and the list view, providing immediate feedback to the user.
	 */
	public void refresh() {
		setUpSpinner();
		setUpListView();
	}

	/**
	 * Function that sets up the ingredient measurement spinner. The default is
	 * mL, there are several other measurements available.
	 */
	public void setUpSpinner() {
		Spinner measurements = (Spinner) findViewById(R.id.addIngredientMeasurement);
		ArrayList<String> units = new ArrayList<String>();

		// Goes through and converts all of the positions to units
		for (int i = 0; i < Constants.NUM_OF_UNITS; i++) {
			units.add(Constants.getUnitFromPosition(i));
		}

		ArrayAdapter<String> unitsAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, units);
		measurements.setAdapter(unitsAdapter);
	}

	/**
	 * Function that sets up the list view that contains the ingredients. This
	 * allows the user to immediately receive feedback on the ingredients they
	 * have entered, as well as allowing them to long press to delete
	 * ingredients.
	 */
	public void setUpListView() {
		// Set up the list view
		ListView listView = (ListView) findViewById(R.id.lViewIngredients);
		GlobalApplication app = (GlobalApplication) getApplication();
		ArrayList<String> content = app.getCurrentRecipe().getIngredients()
				.getStringArrayList();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, content);
		listView.setAdapter(adapter);

		// Set what happens when a user long clicks on an item (prompts for
		// deletion
		listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View v,
					final int position, long arg3) {
				// Builds the alert dialog box
				AlertDialog.Builder prompt = new AlertDialog.Builder(v
						.getContext());
				prompt.setTitle("Delete Ingredient");
				prompt.setMessage("Are you sure you want to delete this ingredient? It "
						+ "will be gone... forever.");

				prompt.setNegativeButton("No",
						new DialogInterface.OnClickListener() {

							// User has changed their mind
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								return;
							}
						});
				prompt.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {

							// User does want to delete the ingredient they are
							// long pressing
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Add network and local storage deletion
								// here
								GlobalApplication app = (GlobalApplication) getApplication();
								Pantry pantry = app.getCurrentRecipe()
										.getIngredients();
								pantry.removeIngredient(position);
								refresh();
							}
						});
				prompt.show();
				return false;
			}

		});
	}

	/**
	 * Function that is called when the user clicks the add ingredient button.
	 * Adds the ingredient to the recipe. Also refreshes the view to show the
	 * new ingredient.
	 */
	public void onAdd() {
		EditText quantity = (EditText) findViewById(R.id.addIngredientQuantity);
		EditText name = (EditText) findViewById(R.id.addIngredientName);
		Spinner units = (Spinner) findViewById(R.id.addIngredientMeasurement);

		// Get the units the user inputed
		int position = units.getSelectedItemPosition();
		String unitsString = Constants.getUnitFromPosition(position);

		String quantityString = quantity.getText().toString();

		// Do the integer conversion like this just in case no number is entered
		Integer amount;
		try {
			amount = new Integer(quantityString);
		} catch (NumberFormatException e) {
			 // Number conversion failed
			//TODO Add feedback on wrong info
			return;
		}

		String nameString = name.getText().toString();
		Ingredient ingredient = new Ingredient(nameString, amount, unitsString);

		// Check for ingredient validity
		if (ingredient.isValidInfo() != Constants.GOOD) {
			// They entered wrong info
			//TODO Give user feedback on wrong number
			return; 
		}

		GlobalApplication app = (GlobalApplication) getApplication();
		Pantry pantry = app.getCurrentRecipe().getIngredients();
		pantry.addIngredient(ingredient);
		refresh();

		return;
	}

	/**
	 * Function that is called when the user clicks on the delete all button.
	 * Deletes all of the ingredients, and updates the pantry accordingly.
	 */
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

	/**
	 * Function that is called when the user clicks done. Ends the activity.
	 */
	public void onDone() {
		finish();
		return;
	}

	/**
	 * Function that handles the clicks from a user. Calls appropriate
	 * function for the button.
	 * 
	 * @param v The view of the button that was clicked.
	 */
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
