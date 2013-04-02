package ca.ualberta.cs.oneclick_cookbook;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

/**
 * Class that allows the user to search for other recipes.
 * @author Kenneth Armstrong
 *
 */
public class SearchActivity extends Activity {
	
	ArrayList<Recipe> recipes = new ArrayList<Recipe>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}
	
	/**
	 * Function that handles and directs the clicks from the user.
	 * 
	 * @param v
	 *            The view of the button that was clicked.
	 */
	public void clickHandler(View v) {
		Intent intent;
		switch(v.getId()) {
		case R.id.bHomeSReturn:
			finish();
			break;
		case R.id.bHomeSPantry:
			NetworkHandler nh = new NetworkHandler();
			
			try {
				recipes = nh.searchIngredients("test");
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			ListView listView = (ListView) findViewById(R.id.searchResults);
			GlobalApplication app = (GlobalApplication) getApplication();

			// Convert them to a string list
			ArrayList<String> content = new ArrayList<String>();
			for(int i = 0; i < recipes.size(); i++) {
	    		content.add(recipes.get(i).toString());
	    	}
			
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, content);
			listView.setAdapter(adapter);

			// Set the onclick action
			listView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> adapterView, View view,
						int position, long id) {
					// Get and set the current recipes based on position
					GlobalApplication app = (GlobalApplication) getApplication();
					Recipe currentRecipe = recipes.get(position);
					app.setCurrentRecipe(currentRecipe);
					//set up test to check user for the recipe
					if (currentRecipe.getOwner() != null) {
						if (currentRecipe.getOwner() == app.getCurrentUser().getUserName()){
							Intent intent = new Intent(
									app,
									ca.ualberta.cs.oneclick_cookbook.ViewFullRecipeActivity.class);
							intent.putExtra("position", position);
							startActivity(intent);
						}
						else {
							Intent intent = new Intent(
									app,
									ca.ualberta.cs.oneclick_cookbook.CreateRecipeActivity.class);
							intent.putExtra("position", position);
							startActivity(intent);
						}
					}
					//no current user allow for edit in the recipe
					else {
						Intent intent = new Intent(
								app,
								ca.ualberta.cs.oneclick_cookbook.CreateRecipeActivity.class);
						intent.putExtra("position", position);
						startActivity(intent);
					}
				}

			});

			// Set what happens when a user clicks on an item
			listView.setOnItemLongClickListener(new OnItemLongClickListener() {

				@Override
				public boolean onItemLongClick(AdapterView<?> arg0, View v,
						final int position, long arg3) {
					// Builds the alert dialog box
					AlertDialog.Builder prompt = new AlertDialog.Builder(v
							.getContext());
					prompt.setTitle("Delete Recipe");
					prompt.setMessage("Are you sure you want to delete this recipe? It "
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

								// User does want to delete the recipe they are long
								// pressing
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// Get the global state
									GlobalApplication app = (GlobalApplication) getApplication();

									// Remove from ES
									NetworkHandler nh = new NetworkHandler();
									String id = recipes.get(position).getID();

									try {
										nh.deleteRecipe(id);
									} catch (IOException e) {
										e.printStackTrace();
									}

									// Delete from the user
									app.getCurrentUser().deleteRecipe(position);

								}
							});
					prompt.show();
					return false;
				}

			});
			break;
		case R.id.bHomeSSearch:
			intent = new Intent(this.getApplicationContext(),
					KeywordSearchActivity.class);
			startActivity(intent);
			break;
		}
	}

}


