package ca.ualberta.cs.oneclick_cookbook;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ViewRecipesActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_recipes);
	}

	@Override
	public void onResume() {
		super.onResume();
		refresh();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_recipes, menu);
		return true;
	}

	// Refreshes the list when changes happen
	public void refresh() {
		ListView listView = (ListView) findViewById(R.id.lViewRecipes);
		GlobalApplication app = (GlobalApplication) getApplication();
		ArrayList<String> content = app.getCurrentUser().getRecipesToString();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, content);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterView, View view,
					int position, long id) {
				// Get and set the current recipes based on position
				GlobalApplication app = (GlobalApplication) getApplication();
				ArrayList<Recipe> userRecipes = app.getCurrentUser().getUserRecipes();
				Recipe currentRecipe = userRecipes.get(position);
				app.setCurrentRecipe(currentRecipe);
				
				Intent intent = new Intent(app,
						ca.ualberta.cs.oneclick_cookbook.CreateRecipeActivity.class);
				intent.putExtra("position", position);
                startActivity(intent);
			}

		});
		
		// Set what happens when a user clicks on an item
		listView.setOnItemLongClickListener(new OnItemLongClickListener() {
			
			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View v,
					final int position, long arg3) {
				// Builds the alert dialog box
				AlertDialog.Builder prompt = new AlertDialog.Builder(v.getContext());
				prompt.setTitle("Delete Recipe");
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

					// User does want to delete the recipe they are long pressing
					@Override
					public void onClick(DialogInterface dialog, int which) {
						//TODO Add upload, local storage deletion here
						GlobalApplication app = (GlobalApplication) getApplication();
						ArrayList<Recipe> recipe = app.getCurrentUser().getUserRecipes();
						recipe.remove(position);
						refresh();
					}
				});
				prompt.show();			
				return false;
			}
			
		});
	}

	public void onDeleteAll() {
		// Builds the alert dialog box
		AlertDialog.Builder prompt = new AlertDialog.Builder(this);
		prompt.setTitle("Delete All");
		prompt.setMessage("Are you sure you want to delete all recipes? They "
				+ "will be gone... forever.");

		prompt.setNegativeButton("No", new DialogInterface.OnClickListener() {

			// User has changed their mind
			@Override
			public void onClick(DialogInterface dialog, int which) {
				return;
			}
		});
		prompt.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

			// User does want to delete all recipes
			@Override
			public void onClick(DialogInterface dialog, int which) {
				//TODO Add upload deletion, local storage deletion code here
				GlobalApplication app = (GlobalApplication) getApplication();
				app.getCurrentUser().clearRecipes();
				refresh();
			}
		});
		prompt.show();
		return;
	}

	public void clickHandler(View v) {
		switch (v.getId()) {
		case R.id.bdeleteAllRecipes:
			onDeleteAll();
			break;
		}
	}

}
