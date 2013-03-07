package ca.ualberta.cs.oneclick_cookbook;

import java.util.ArrayList;

public class Pantry {
	private ArrayList<Ingredient> ingredientList;
	
	Pantry() {
		
	}
	
	public void addIngredient(Ingredient i) {
		ingredientList.add(i);
	}
	
	public boolean removeIngredient(String name) {
		for (int i=0; i<ingredientList.size(); i++) {
			Ingredient ingredient = (Ingredient) ingredientList.get(i);

			if (ingredient.getName() == name) {
				ingredientList.remove(ingredient);
				return true;
			}
		}
		// This happens if name wasn't in the list
		return false;
	}
	
}
