package ca.ualberta.cs.oneclick_cookbook;

import java.util.List;

import android.provider.MediaStore.Images;

public class Recipe {

	private String name = "";
	private Pantry ingredients = null;
	private String steps = "";
	private List<Images> pictures = null;
	private int promotions = 0;
	private int demotions = 0;

	public Recipe() {
		
	}
	public Recipe(String name, Pantry ingredients, String steps) {
		this.name = name;
		this.ingredients = ingredients;
		this.steps = steps;
		this.promotions = 0;
		this.demotions = 0;
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

	// Turns the recipe into a string
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

	// Checks the recipe for valid info
	public int isValidInfo() {

		if (name == null || steps == null  || ingredients == null ) {
			return Constants.NULL_VALUE;
		}

		else if (name.equals("") || steps.equals("") || ingredients.isEmpty()) {
			return Constants.ZERO_VALUE;
		}

		else {
			return ingredients.isValidInfo();
		}
	}
}
