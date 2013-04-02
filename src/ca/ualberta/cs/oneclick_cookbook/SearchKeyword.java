package ca.ualberta.cs.oneclick_cookbook;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;

import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SearchKeyword extends AsyncTask{
	
	protected ArrayList<Recipe> doInBackground(String string) {
          
		NetworkHandler nh = new NetworkHandler();
        // params comes from the execute() call: params[0] is the url.
        try {
			return nh.searchRecipes(string);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
    }
	
    // onPostExecute displays the results of the AsyncTask.
    protected void onPostExecute(ArrayList<Recipe> recipes) {
    	// Convert them to a string list
		//ListView listView = (ListView) findViewById(R.id.lViewRecipes);
		
    	ArrayList<String> content = new ArrayList<String>();
    	for(int i = 0; i < recipes.size(); i++) {
    		//content.add(recipes[i].toString());
    	}
    	
    	//ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
    	//				android.R.layout.simple_list_item_1, content);
    	
    	//listView.setAdapter(adapter);
    }
    
	@Override
	protected Object doInBackground(Object... params) {
		// TODO Auto-generated method stub
		return null;
	}

}
