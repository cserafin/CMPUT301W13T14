package ca.ualberta.cs.oneclick_cookbook;

import java.util.ArrayList;

// This class abstracts a pantry (or list of ingredients)
// Wraps ArrayList functions
public class Pantry {
	private ArrayList<Ingredient> ingredientList;
	
	public Pantry() {
		
	}
	
    // Adds the ingredient to the list
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

    // Removes an ingredient based on Object
    public boolean removeIngredient(Ingredient i) {
        return ingredientList.remove(i);
	}   

    // Removes an ingredient based on index position
    public void removeIngredient(int i) {
        ingredientList.remove(i);
    }

    // Clears the entire pantry
    public void emptyPantry() {
        ingredientList.clear();
    }

    // Returns whether the pantry is empty or not
    public boolean isEmpty() {
        return ingredientList.isEmpty();
    }

    // Gets the Ingredient at the specified position
    public Ingredient get(int i) {
        return ingredientList.get(i);
    }
}
