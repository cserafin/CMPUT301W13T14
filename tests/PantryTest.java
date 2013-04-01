import java.util.ArrayList;

import org.junit.*;
import static org.junit.Assert.*;
import ca.ualberta.cs.oneclick_cookbook.Ingredient;
import ca.ualberta.cs.oneclick_cookbook.Pantry;
import ca.ualberta.cs.oneclick_cookbook.Constants;

/* ***********************************************
 * Class that tests the Pantry class.
 *
 * Author: Kenneth Armstrong
 *
 * **********************************************/

public class PantryTest {
    private Pantry p = null;

    @Before
    public void setup() {
        p = new Pantry();
    }

    @After
    public void destory() {
        p = null;
    }

    // Test the constructor and isEmpty function
    @Test
    public void testIsEmpty() {
        assertTrue(p.isEmpty());
    }

    // Test the add and remove with object functions
    @Test
    public void testAddRemoveObject() {
        assertTrue(p.isEmpty());
        Ingredient i = new Ingredient("Flour", 42, "Pounds");
        p.addIngredient(i);

        // Should no longer be empty
        assertFalse(p.isEmpty());
        p.removeIngredient(i);

        // Should be empty, since we removed the ingredient we added
        assertTrue(p.isEmpty());
    }

    // Test the remove by name function
    @Test 
    public void testRemoveByName() {
        // Make sure it's empty
        assertTrue(p.isEmpty());

        // Add some ingredients
        p.addIngredient(new Ingredient("Flour", 42, "Pounds"));
        p.addIngredient(new Ingredient("Bacon", 13, "Crates"));
        p.addIngredient(new Ingredient("Toast", 200, "Slices"));
        
        // Test some of the functions
        assertEquals("Test: removeByName1", 3, p.getNumberOfItems());
        assertTrue("Test: removeByName2", p.removeIngredient("Flour"));
        assertEquals("Test: removeByName3", 2, p.getNumberOfItems());
        assertFalse("Test: removeByName4", p.removeIngredient("NotInList"));
        assertEquals("Test: removeByName5", 2, p.getNumberOfItems());
    }

    // Test the clear entire pantry function
    @Test
    public void testClearPantry() {
        // Add a bunch of ingredients
        p.addIngredient(new Ingredient("Flour", 42, "Pounds"));
        p.addIngredient(new Ingredient("Bacon", 13, "Crates"));
        p.addIngredient(new Ingredient("Toast", 200, "Slices"));
        p.addIngredient(new Ingredient("Flour", 42, "Pounds"));
        p.addIngredient(new Ingredient("Bacon", 13, "Crates"));
        p.addIngredient(new Ingredient("Toast", 200, "Slices"));
        p.addIngredient(new Ingredient("Flour", 42, "Pounds"));
        p.addIngredient(new Ingredient("Bacon", 13, "Crates"));
        p.addIngredient(new Ingredient("Toast", 200, "Slices"));

        // Make sure that they all got added
        assertEquals("Test: clearPantry1", 9, p.getNumberOfItems());
        p.emptyPantry();
        // Make sure that the pantry got emptied
        assertTrue("Test: clearPantry2", p.isEmpty());
    }

    // Test the pantry validity function
    @Test
    public void testPantryCheck() {
        // Add some ingredients
        p.addIngredient(new Ingredient("Flour", 42, "Pounds"));
        p.addIngredient(new Ingredient("Bacon", 13, "Crates"));
        p.addIngredient(new Ingredient("Toast", 200, "Slices"));
        
        // Make sure it is valid
        assertEquals("Test: pantrycheck1", Constants.GOOD, p.isValidInfo());
        
        // Add an invalid ingredient and test
        p.addIngredient(new Ingredient("Bread", 200, null));
        assertEquals("Test: pantrycheck2", Constants.NULL_VALUE, p.isValidInfo());
        
        // Remove the invalid ingredient, and another invalid one, and retest
        p.removeIngredient("Bread");
        p.addIngredient(new Ingredient("Eggs", 0, "Items"));
        assertEquals("Test: pantrycheck3", Constants.ZERO_VALUE, p.isValidInfo());
    }

    // Test the pantry search function
    @Test
    public void testPantrySearch() {
        // Add some ingredients
        p.addIngredient(new Ingredient("Flour", 42, "Pounds"));
        p.addIngredient(new Ingredient("Bacon", 13, "Crates"));
        p.addIngredient(new Ingredient("Toast", 200, "Slices"));
        
        // Search the pantry for items we added
        assertTrue("Test:pantrySearch:Test 1", p.isInPantry("Flour"));
        assertTrue("Test:pantrySearch:Test 2", p.isInPantry("Toast"));
        p.emptyPantry();

        // Since pantry is empty, should be false
        assertFalse("Test:pantrySearch:Test 3", p.isInPantry("Flour"));
    }
    
    //Test the pantry string array function
    @Test
    public void testStringArrayList() {
        // Create the arrayList of strings that we would expect
    	ArrayList<String> s = new ArrayList<String>();
    	p.addIngredient(new Ingredient("Flour", 3, "mL"));
    	s.add(p.get(0).toString());
    	p.addIngredient(new Ingredient("Bread", 3, "mL"));
    	s.add(p.get(1).toString());
    	p.addIngredient(new Ingredient("Salt", 4, "teaspoons"));
    	s.add(p.get(2).toString());

        // Make sure it's the same
    	assertEquals("Pantry:stringarrayList:Test 1", p.getStringArrayList(), s);
    }
    
    // Test the toLower function of pantry
    @Test
    public void testToLower() {
        // Add some ingredients with mixed case characters
    	p.addIngredient(new Ingredient("Flour", 3, "mL"));
    	p.addIngredient(new Ingredient("BrEAD", 3, "CuPs"));
    	p.addIngredient(new Ingredient("SALt", 3, "ITemS"));
        
        // Lowercase all characters
    	p.toLower();

        // Should mean that all letters are lowercase
    	assertTrue("Pantry:toLower:Test 1", p.isInPantry("bread"));
    	assertFalse("Pantry:toLower:Test 2", p.isInPantry("sALt"));
    }
    
    // Test the toString function of pantry, will change likely
    @Test
    public void testToString() {
        // Create a string, and test it against the format we expect
    	String s = "";
    	p.addIngredient(new Ingredient("Flour", 3, "mL"));
    	s += p.get(0).toString() + ", ";
    	p.addIngredient(new Ingredient("Bread", 3, "mL"));
    	s += p.get(1).toString() + ", ";
    	p.addIngredient(new Ingredient("Salt", 4, "teaspoons"));
    	s += p.get(2).toString();
    	assertEquals("Pantry:toString:Test 1", s, p.toString());
    }
                   
}



