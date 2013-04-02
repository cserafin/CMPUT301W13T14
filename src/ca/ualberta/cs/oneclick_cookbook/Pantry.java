/**
 * @author	Chris Serafin, Peter Maidens, Ken "Mike" Armstrong, Kimberly Kramer
 * 
 * This class abstracts a pantry. It is a controller for the list of ingredients.
 * It allows us to keep a list of ingredients and manage this list.
 */

package ca.ualberta.cs.oneclick_cookbook;

import java.util.ArrayList;

// This class abstracts a pantry (or list of ingredients)
// Wraps ArrayList functions
public class Pantry {

	private ArrayList<Ingredient> ingredientList = null;
	
	/**
	 * Constructor
	 */
	public Pantry() {
		ingredientList = new ArrayList<Ingredient>();
	}
	
	/**
	 * Adds the ingredient to the list
	 * @param Ingredient the ingredient that you want to add
	 */
	public void addIngredient(Ingredient i) {
		ingredientList.add(i);
	}

	/**
	 * Removes the ingredient by name
	 * @param name The name of the ingredient you want to remove. Must be exact
	 */
	public boolean removeIngredient(String name) {
		for (int i=0; i<ingredientList.size(); i++) {
			Ingredient ingredient = (Ingredient) ingredientList.get(i);

			if (ingredient.getName().equals(name)) {
				ingredientList.remove(ingredient);
				return true;
			}
		}
		// This happens if name wasn't in the list
		return false;
	}

	/**
	 * Removes an ingredient based on a passed object
	 * @param i the ingredient you want to remove
	 */
    public boolean removeIngredient(Ingredient i) {
        return ingredientList.remove(i);
	}   

	/**
	 * Removes the ingredient at the ith index
	 * @param i index of the ingredient you want removed
	 */
    public void removeIngredient(int i) {
        ingredientList.remove(i);
    }

	/**
	 * Clears the pantry
	 */
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

	/**
	 * Checks wether the entire pantry is valid info.
	 * NOTE: The return value is dependant on the order of the items in pantry
	 * @return Constants.GOOD Returns this if it is all valid
	 * */
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

	/**
	 * checks wether an item is in the pantry
	 * @param name String of the name of the ingredient that you are testing
	 */
    public boolean isInPantry(String name) {
		for (int i=0; i<ingredientList.size(); i++) {
			Ingredient ingredient = (Ingredient) ingredientList.get(i);

			if (ingredient.getName().equals(name)) {
				return true;
			}
		}
		// This happens if name wasn't in the list
		return false;
	}

	/**
	 * Converst all ingredients to lower case
	 */
    public void toLower() {
        for (int i=0; i<ingredientList.size(); i++) {
            ingredientList.get(i).toLower();
        }
    }
    
    /**
     * Returns an ArrayList of the ingredients in the pantry converted to strings
     * @return s ArrayList of strings
     */
    public ArrayList<String> getStringArrayList() {
    	ArrayList<String> s = new ArrayList<String>();
    	for (int i=0; i<ingredientList.size(); i++) {
    		s.add(ingredientList.get(i).toString());
    	}
    	
    	return s;
    }
    
    /**
     * Converts the pantry to a string
     * @return returns converted pantry
     */
    public String toString() {
    	String s = "";
    	for (int i=0; i<(ingredientList.size() - 1); i++) {
    		s += ingredientList.get(i).toString() + ", ";
    	}
    	s += ingredientList.get(ingredientList.size() - 1).toString();
    	return s;
    }
    
    /**
     * Converts the pantry to a string usable in an elasticsearch query
     * @return returns the string representation
     */
    public String toSearchString() {
    	String s = "";
    	
    	for(int i=0; i < ingredientList.size(); i++) {
    		s += ingredientList.get(i).getName();
    		if(i < ingredientList.size() - 1) {
    			s += " AND ";
    		}
    	}
    	
    	return s;
    }
}
