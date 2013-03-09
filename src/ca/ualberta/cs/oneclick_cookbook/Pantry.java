package ca.ualberta.cs.oneclick_cookbook;

import java.util.ArrayList;

// This class abstracts a pantry (or list of ingredients)
// Wraps ArrayList functions
public class Pantry {

	private ArrayList<Ingredient> ingredientList = null;
	

	public Pantry() {
		ingredientList = new ArrayList<Ingredient>();
	}
	

    // Adds the ingredient to the list
	public void addIngredient(Ingredient i) {
		ingredientList.add(i);
	}


    // Removes ingredient by name
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


    // Checks whether the entire pantry has valid info
    // Note that the return value is dependant on the order
    // Of the items in the pantry
    public int isValidInfo() {
        for (int i=0; i<ingredientList.size(); i++) {
            if (ingredientList.get(i).isValidInfo() != Constants.GOOD) {
                return ingredientList.get(i).isValidInfo();
            }
        }
        return Constants.GOOD;
    }


    public int getNumberOfItems() {
        return ingredientList.size();
    }


    public boolean isInPantry(String name) {
		for (int i=0; i<ingredientList.size(); i++) {
			Ingredient ingredient = (Ingredient) ingredientList.get(i);

			if (ingredient.getName() == name) {
				return true;
			}
		}
		// This happens if name wasn't in the list
		return false;
	}


    // Converts all ingredients to lower case
    public void toLower() {
        for (int i=0; i<ingredientList.size(); i++) {
            ingredientList.get(i).toLower();
        }
    }
}
