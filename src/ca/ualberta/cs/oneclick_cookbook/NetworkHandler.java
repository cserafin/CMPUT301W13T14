package ca.ualberta.cs.oneclick_cookbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.StrictMode;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

//TODO Implement this class
// This class will directly communicate with the remote server
// Had to completely rewrite(my bad) should be mostly working

/**
 * Handles connections to the network and updates/modifies/retrieves and deletes
 * entries from elasticsearch. Also handles the connection for a keyword search
 */
public class NetworkHandler {
	// Opens and handles the HTTP connection
	private HttpClient httpClient = new DefaultHttpClient();

	// Hands the recipe Gson conversion
	private Gson gson = new Gson();

	public NetworkHandler() {
		System.err.println("Test made handler");
	}

	/**
	 * Handles the HTTP POST operation. Converts type recipe to a Json object
	 * and uploads to elasticsearch
	 * 
	 * @param recipe
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	public void postToES(Recipe recipe) throws IllegalStateException,
			IOException {

		// SHOULD put the stuff in a seperate thread and remove below two lines
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);

		HttpPut httpPut = new HttpPut(
				"http://cmput301.softwareprocess.es:8080/cmput301w13t14/recipes/"
						+ recipe.getID());
		StringEntity recipeString = null;

		// Attempt to convert the recipe class to a usable Json type
		try {
			recipeString = new StringEntity(gson.toJson(recipe));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		httpPut.setHeader("accept", "application/json");
		httpPut.setEntity(recipeString);

		HttpResponse response = null;
		try {
			response = httpClient.execute(httpPut);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		}

		// For debugging
		String status = response.getStatusLine().toString();
		System.out.println(status);

		HttpEntity entity = response.getEntity();
		BufferedReader br = new BufferedReader(new InputStreamReader(
				entity.getContent()));
		String output;
		System.err.println("Output from Server -> ");
		while ((output = br.readLine()) != null) {
			System.err.println(output);
		}

		httpPut.abort();
	}

	/**
	 * Handles the HTTPGet for retrieving recipes from elasticsearch. Grabs the
	 * Json object and converts to a recipe object.
	 * 
	 * @param id
	 *            : id of the recipe as stored in the elasticsearch
	 * @return: recipe that was found
	 */
	public Recipe getFromES(String id) {
		// SHOULD put the stuff in a seperate thread and remove below two lines
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
		try {
			HttpGet httpGet = new HttpGet(
					"http://cmput301.softwareprocess.es:8080/cmput301w13t14/recipes/"
							+ id);

			httpGet.addHeader("accept", "application/json");

			HttpResponse response = httpClient.execute(httpGet);

			// For debugging
			String status = response.getStatusLine().toString();
			System.out.println(status);

			String json = getJsonContent(response);

			// Tells the program what format to expect
			java.lang.reflect.Type EsResponseType = new TypeToken<GSONTranslator<Recipe>>() {
			}.getType();
			GSONTranslator<Recipe> EsResponse = gson.fromJson(json,
					EsResponseType);

			httpGet.abort();

			Recipe recipe = EsResponse.getSource();

			return recipe;

		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Deletes a recipe from elasticsearch based on id
	 * 
	 * @param id
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public void deleteRecipe(String id) throws ClientProtocolException,
			IOException {

		// SHOULD put the stuff in a seperate thread and remove below two lines
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);

		HttpDelete httpDelete = new HttpDelete(
				"http://cmput301.softwareprocess.es:8080/cmput301w13t14/recipes/" + id);
		httpDelete.addHeader("Accept", "application/json");

		HttpResponse response = httpClient.execute(httpDelete);

		// For debugging
		String status = response.getStatusLine().toString();
		System.out.println(status);

		HttpEntity entity = response.getEntity();
		BufferedReader br = new BufferedReader(new InputStreamReader(
				entity.getContent()));

		String output;
		System.err.println("Output from Server -> ");
		while ((output = br.readLine()) != null) {
			System.err.println(output);
		}

		httpDelete.abort();
	}

	/**
	 * Converts HttpResponses to a string representation of a Json object
	 * 
	 * @param response
	 *            : response from trying to grab a Json object from
	 *            elasticsearch
	 * @return String representation of a Json object
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	private String getJsonContent(HttpResponse response)
			throws IllegalStateException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(
				(response.getEntity().getContent())));
		String output;
		System.err.println("Output from Server -> ");
		String json = "";
		while ((output = br.readLine()) != null) {
			System.err.println(output);
			json += output;
		}
		System.err.println("JSON:" + json);
		return json;
	}
	
	/**
	 * Searches for a recipe based on keywords.
	 * 
	 * @param string
	 * 				: String object with keyword(s) to be searched for
	 * @return Array of recipes that match the keyword(s)
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public ArrayList<Recipe> searchRecipes(String string) throws ClientProtocolException, IOException {
		// SHOULD put the stuff in a seperate thread and remove below two lines
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		HttpGet searchURL = new HttpGet("http://cmput301.softwareprocess.es:8080/cmput301w13t14/recipes/_search?pretty=1&q=" +
		java.net.URLEncoder.encode(string,"UTF-8"));
		searchURL.setHeader("Accept","application/json");
		HttpResponse response = httpClient.execute(searchURL);
		
		String json = getEntityContent(response);
		
		Type elasticSearchSearchResponseType = new TypeToken<ElasticSearchSearchResponse<Recipe>>(){}.getType();
		ElasticSearchSearchResponse<Recipe> esResponse = gson.fromJson(json, elasticSearchSearchResponseType);
		
		ArrayList<Recipe> recipes = new ArrayList<Recipe>();
		for (ElasticSearchResponse<Recipe> r : esResponse.getHits()) {
			recipes.add(r.getSource());
		}
		
		searchURL.abort();
		
		return recipes;
	}	
	
	/**
	 * Allows for a search of recipes based on ingredients on hand
	 * @param ingredients
	 * 					: A string representation of a list of ingredients in the users
	 * 					pantry
	 * @return Array of recipes that match the ingredients in the users pantry
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public ArrayList<Recipe> searchIngredients(String ingredients) throws ClientProtocolException, IOException {
		// SHOULD put the stuff in a seperate thread and remove below two lines
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		HttpPost searchURL = new HttpPost("http://cmput301.softwareprocess.es:8080/cmput301w13t14/recipes/_search?pretty=1");
		String query = "{\"query\" : {\"query_string\" : {\"default_field\" : \"ingredients\",\"query\" : \"" + ingredients + "\"}}}";
		StringEntity stringentity = new StringEntity(query);

		searchURL.setHeader("Accept","application/json");
		searchURL.setEntity(stringentity);

		HttpResponse response = httpClient.execute(searchURL);
		
		String json = getEntityContent(response);

		Type elasticSearchSearchResponseType = new TypeToken<ElasticSearchSearchResponse<Recipe>>(){}.getType();
		ElasticSearchSearchResponse<Recipe> esResponse = gson.fromJson(json, elasticSearchSearchResponseType);
		
		ArrayList<Recipe> recipes = new ArrayList<Recipe>();
		for (ElasticSearchResponse<Recipe> r : esResponse.getHits()) {
			recipes.add(r.getSource());
		}
		
		searchURL.abort();
		
		return recipes;
	}
	
	/**
	 * Converts the search results to usable json objects
	 * @param response
	 * 					: The response from a search of elasticsearch
	 * @return A Json representation of the search results
	 * @throws IOException
	 */
	private String getEntityContent(HttpResponse response) throws IOException {
		BufferedReader br = new BufferedReader(
		new InputStreamReader((response.getEntity().getContent())));
		String output;
		String json = "";
		while ((output = br.readLine()) != null) {
			json += output;
		}
		
		return json;
	}

}
