package ca.ualberta.cs.oneclick_cookbook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import android.os.Environment;

import com.google.gson.Gson;

//TODO Implement this class

/**
 * Class that handles the local storage of files on the device.
 * 
 * @author Kenneth Armstrong
 * 
 */
public class StorageHandler {

	public void saveRecipe(Recipe r) {
		// Create the JSON string
		Gson gson = new Gson();
		String json = gson.toJson(r);

		try {
			// Get the folder path and create it if it doesn't exist
			String path = Environment.getExternalStorageDirectory().getAbsolutePath()
					+ "/oneclick_cookbook";
			File folder = new File(path);
			if (!folder.exists()) {
				folder.mkdir();
			}
			
			File file = new File(path + "/" + r.getID());
			file.createNewFile();
			
			// Write to the file
			FileWriter writer = new FileWriter(path + "/" + r.getID());
			writer.write(json);
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public Recipe loadRecipe(String id) {
		Gson gson = new Gson();

		// Get the path to the recipe file
		String path = Environment.getExternalStorageDirectory().getAbsolutePath()
				+ "/oneclick_cookbook/" + id;
		System.out.println(path);
		
		try {

			BufferedReader br = new BufferedReader(new FileReader(path));

			// convert the json string back to object
			Recipe recipe = gson.fromJson(br, Recipe.class);
			return recipe;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public void saveRecipeCache(Recipe r) {
		// Create the JSON string
		Gson gson = new Gson();
		String json = gson.toJson(r);

		try {
			// Get the folder path and create it if it doesn't exist
			String path = Environment.getDownloadCacheDirectory().getAbsolutePath()
					+ "/oneclick_cookbook";
			File folder = new File(path);
			if (!folder.exists()) {
				folder.mkdir();
			}
			
			File file = new File(path + "/" + r.getID());
			file.createNewFile();
			
			// Write to the file
			FileWriter writer = new FileWriter(path + "/" + r.getID());
			writer.write(json);
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public Recipe loadRecipeCache(String id) {
		Gson gson = new Gson();

		// Get the path to the recipe file
		String path = Environment.getDownloadCacheDirectory().getAbsolutePath()
				+ "/oneclick_cookbook/" + id;
		System.out.println(path);
		
		try {

			BufferedReader br = new BufferedReader(new FileReader(path));

			// convert the json string back to object
			Recipe recipe = gson.fromJson(br, Recipe.class);
			return recipe;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
	}

}
