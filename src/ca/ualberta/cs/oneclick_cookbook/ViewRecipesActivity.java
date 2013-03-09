package ca.ualberta.cs.oneclick_cookbook;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
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
		ListView listView = (ListView) findViewById(R.id.lViewRecipes);
        GlobalApplication app = (GlobalApplication) getApplication();
        ArrayList<String> content = app.getCurrent().getRecipesToString();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, content);
        listView.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_recipes, menu);
		return true;
	}

}
