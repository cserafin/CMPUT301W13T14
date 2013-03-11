package ca.ualberta.cs.oneclick_cookbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

//TODO Implement this class
// This class will directly communicate with the remote server
// Had to completely rewrite(my bad) should be mostly working

/**
 * Handles connections to the network and updates/modifies/retrieves and deletes entries from elastic search
 * @author Chris
 * 
 */
public class NetworkHandler {
	// Opens and handles the HTTP connection
	private HttpClient httpClient = new DefaultHttpClient();
	
	// Hands the recipe Gson conversion
	private Gson gson = new Gson();
	
	/**
	 * Handles the HTTP POST operation. Converts type recipe to a Json object and uploads to elasticsearch
	 * @param recipe
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	public void postToES(Recipe recipe) throws IllegalStateException, IOException{
		HttpPut httpPut = new HttpPut("http://cmput301.softwareprocess.es:8080/testing/lab01/"+recipe.getID());
		StringEntity recipeString = null;
		
		// Attempt to convert the recipe class to a usable Json type
		try {
			recipeString = new StringEntity(gson.toJson(recipe));
		}
		catch (UnsupportedEncodingException e){
			e.printStackTrace();
		}
		
		httpPut.setHeader("accept","application/json");
		httpPut.setEntity(recipeString);
		
		HttpResponse response = null;
		try {
			response = httpClient.execute(httpPut);
		}
		catch (ClientProtocolException e) {
			e.printStackTrace();
		}
		
		// For debugging
		String status = response.getStatusLine().toString();
		System.out.println(status);
		
		HttpEntity entity = response.getEntity();
		BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent()));
		String output;
		System.err.println("Output from Server -> ");
		while ((output = br.readLine()) != null) {
			System.err.println(output);
		}

		httpPut.abort();
	}


	/**
	 * Handles the HTTPGet for retrieving recipes from elasticsearch. Grabs the Json object and converts
	 * to a recipe object.
	 * @param id: id of the recipe as stored in the elasticsearch
	 * @return: recipe that was found
	 */
	public Recipe getFromES(String id) {
		try {
			HttpGet httpGet = new HttpGet("http://cmput301.softwareprocess.es:8080/testing/lab01/"+id);	
			
			httpGet.addHeader("accept","application/json");
			
			HttpResponse response = httpClient.execute(httpGet);
			
			// For debugging
			String status = response.getStatusLine().toString();
			System.out.println(status);
			
			String json = getJsonContent(response);
			
			// Tells the program what format to expect
			java.lang.reflect.Type EsResponseType = new TypeToken<GSONTranslator<Recipe>>(){}.getType();
			GSONTranslator<Recipe> EsResponse = gson.fromJson(json, EsResponseType);
			
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
	 * @param id
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public void deleteRecipe(String id) throws ClientProtocolException, IOException {
		HttpDelete httpDelete = new HttpDelete("http://cmput301.softwareprocess.es:8080/testing/lab02/"+id);
		httpDelete.addHeader("Accept","application/json");

		HttpResponse response = httpClient.execute(httpDelete);

		// For debugging
		String status = response.getStatusLine().toString();
		System.out.println(status);

		HttpEntity entity = response.getEntity();
		BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent()));
		
		String output;
		System.err.println("Output from Server -> ");
		while ((output = br.readLine()) != null) {
			System.err.println(output);
		}
		
		httpDelete.abort();
	}
	
	/**
	 * Converts HttpResponses to a string representation of a Json object
	 * @param response: response from trying to grab a Json object from elasticsearch
	 * @return String representation of a Json object
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	private String getJsonContent(HttpResponse response) throws IllegalStateException, IOException {
		BufferedReader br = new BufferedReader(
			new InputStreamReader((response.getEntity().getContent())));
			String output;
			System.err.println("Output from Server -> ");
			String json = "";
			while ((output = br.readLine()) != null) {
				System.err.println(output);
				json += output;
			}
			System.err.println("JSON:"+json);
			return json;
	}

}
