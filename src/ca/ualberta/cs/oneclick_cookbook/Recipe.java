package ca.ualberta.cs.oneclick_cookbook;

import java.util.List;

import android.provider.MediaStore.Images;

public class Recipe {

	private String name;
	private Pantry ingredients;
	private String steps;
	private List<Images> pictures = null;
	private int promotions = 0;
	private int demotions = 0;

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
		// Make sure it's not too long to mess up format
		return "Name: " + this.name + "\nIngredients: "
				+ ingredients.get(0).toString();
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
