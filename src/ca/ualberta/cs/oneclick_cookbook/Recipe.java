/**
 * This class is the class that is used to store the recipes that are submitted
 * by users. It contains all the infromation that is needed for the recipes.
 * 
 * @author	Chris Serafin, Peter Maidens, Ken "Mike" Armstrong, Kimberly Kramer
 * 
 */

package ca.ualberta.cs.oneclick_cookbook;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class Recipe {

	private String name = "";
	private Pantry ingredients = null;
	private String steps = "";
	private ArrayList<String> pictures = null;
	private int promotions = 0;
	private int demotions = 0;

	// for online IDing
	private String id;

	public Recipe() {

	}

	/**
	 * Constructor. Generates the recipe ID internally.
	 * 
	 * @param name
	 *            Name of the recipe
	 * @param ingredients
	 *            Actually a Pantry Class Object that is used as the list of
	 *            ingredients
	 * @param steps
	 *            A string that outlines the steps requried in cooking the
	 *            recipe
	 */
	public Recipe(String name, Pantry ingredients, String steps) {
		this.name = name;
		this.ingredients = ingredients;
		this.steps = steps;
		this.promotions = 0;
		this.demotions = 0;
		this.pictures = new ArrayList<String>();

		// Generate the ID tag (should be unique enough :) )
		Random random = new Random();
		UUID id = UUID.randomUUID();
		this.id = id.toString() + "-" + System.nanoTime() + "-" + random.nextInt(100000);
	}

	public String getID() {
		return this.id;
	}

	public void changeName(String newName) {
		this.name = newName;
		return;
	}

	public String getName() {
		return this.name;
	}

	public void changeSteps(String newSteps) {
		this.steps = newSteps;
		return;
	}

	public String getSteps() {
		return this.steps;
	}

	public void modifyIngredients(Pantry newIngredients) {
		this.ingredients = newIngredients;
		return;
	}

	public Pantry getIngredients() {
		return this.ingredients;
	}

	public void promote() {
		this.promotions += 1;
		return;
	}

	public void demote() {
		this.demotions += 1;
		return;
	}

	public int getRating() {
		int rating = this.promotions - this.demotions;
		return rating;
	}

	public void addImageID(String id) {
		pictures.add(id);
	}

	/**
	 * Gets the image in the given poistion
	 * 
	 * @param position
	 *            The position of the image to get
	 * @return The image requested, null if invalid.
	 */
	public String getImageID(int position) {
		if (position >= pictures.size() || position < 0) {
			return null;
		} else {
			return pictures.get(position);
		}
	}

	/**
	 * Function that deletes an image in the given position
	 * 
	 * @param position
	 *            The position of the image to delete
	 * @return True if image was deleted, false otherwise.
	 */
	public boolean deleteImage(int position) {
		if (position >= pictures.size() || position < 0) {
			return false;
		}

		else {
			pictures.remove(position);
			return true;
		}
	}

	/**
	 * Turns the recipe into a String.
	 * 
	 * @return String The recipe that has been reformated into a string.
	 */
	public String toString() {
		String localName = this.name;
		String localIngredients = this.ingredients.toString();

		if (localIngredients.length() > 20) {
			localIngredients = localIngredients.substring(0, 20) + "...";
		}

		if (localName.length() > 20) {
			localName = localName.substring(0, 20) + "...";
		}

		return "Title: " + localName + "\nIngredients: " + localIngredients;
	}

	/**
	 * Checks whether the recipe has valid info. Also checks all ingredients in
	 * the recipe for validity.
	 * 
	 * @return True if all info is valid, false otherwise.
	 */
	public int isValidInfo() {

		if (name == null || steps == null || ingredients == null) {
			return Constants.NULL_VALUE;
		}

		else if (name.equals("") || steps.equals("") || ingredients.isEmpty()) {
			return Constants.ZERO_VALUE;
		}

		else {
			return ingredients.isValidInfo();
		}
	}

	/**
	 * Posts 'this' recipe to elasticsearch
	 * <p>
	 * Not Currently Implemented
	 */
	public void postToES() {

	}
}
