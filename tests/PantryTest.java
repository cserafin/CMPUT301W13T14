import java.util.ArrayList;

import org.junit.*;
import static org.junit.Assert.*;
import ca.ualberta.cs.oneclick_cookbook.Ingredient;
import ca.ualberta.cs.oneclick_cookbook.Pantry;
import ca.ualberta.cs.oneclick_cookbook.Constants;

// This class tests Pantry class
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

    // Test the constructor and other basic functions
    @Test
    public void testBasics() {
        assertTrue(p.isEmpty());
    }

    // Test the add and remove with object functions
    @Test
    public void testAddRemoveObject() {
        assertTrue(p.isEmpty());
        Ingredient i = new Ingredient("Flour", 42, "Pounds");
        p.addIngredient(i);
        assertFalse(p.isEmpty());
        p.removeIngredient(i);
        assertTrue(p.isEmpty());
    }

    // Test the remove by name function
    @Test 
    public void testRemoveByName() {
        assertTrue(p.isEmpty());
        p.addIngredient(new Ingredient("Flour", 42, "Pounds"));
        p.addIngredient(new Ingredient("Bacon", 13, "Crates"));
        p.addIngredient(new Ingredient("Toast", 200, "Slices"));
        assertEquals("Test: removeByName1", 3, p.getNumberOfItems());
        assertTrue("Test: removeByName2", p.removeIngredient("Flour"));
        assertEquals("Test: removeByName3", 2, p.getNumberOfItems());
        assertFalse("Test: removeByName4", p.removeIngredient("NotInList"));
        assertEquals("Test: removeByName5", 2, p.getNumberOfItems());
    }

    // Test the clear entire pantry function
    @Test
    public void testClearPantry() {
        p.addIngredient(new Ingredient("Flour", 42, "Pounds"));
        p.addIngredient(new Ingredient("Bacon", 13, "Crates"));
        p.addIngredient(new Ingredient("Toast", 200, "Slices"));
        p.addIngredient(new Ingredient("Flour", 42, "Pounds"));
        p.addIngredient(new Ingredient("Bacon", 13, "Crates"));
        p.addIngredient(new Ingredient("Toast", 200, "Slices"));
        p.addIngredient(new Ingredient("Flour", 42, "Pounds"));
        p.addIngredient(new Ingredient("Bacon", 13, "Crates"));
        p.addIngredient(new Ingredient("Toast", 200, "Slices"));
        assertEquals("Test: clearPantry1", 9, p.getNumberOfItems());
        p.emptyPantry();
        assertTrue("Test: clearPantry2", p.isEmpty());
    }

    // Test the pantry validity function
    @Test
    public void testPantryCheck() {
        p.addIngredient(new Ingredient("Flour", 42, "Pounds"));
        p.addIngredient(new Ingredient("Bacon", 13, "Crates"));
        p.addIngredient(new Ingredient("Toast", 200, "Slices"));
        assertEquals("Test: pantrycheck1", Constants.GOOD, p.isValidInfo());
        p.addIngredient(new Ingredient("Bread", 200, null));
        assertEquals("Test: pantrycheck2", Constants.NULL_VALUE, p.isValidInfo());
        p.removeIngredient("Bread");
        p.addIngredient(new Ingredient("Eggs", 0, "Items"));
        assertEquals("Test: pantrycheck3", Constants.ZERO_VALUE, p.isValidInfo());
    }

    // Test the pantry search function
    @Test
    public void testPantrySearch() {
        p.addIngredient(new Ingredient("Flour", 42, "Pounds"));
        p.addIngredient(new Ingredient("Bacon", 13, "Crates"));
        p.addIngredient(new Ingredient("Toast", 200, "Slices"));
        assertTrue("Test:pantrySearch:Test 1", p.isInPantry("Flour"));
        assertTrue("Test:pantrySearch:Test 2", p.isInPantry("Toast"));
        p.emptyPantry();
        assertFalse("Test:pantrySearch:Test 3", p.isInPantry("Flour"));
    }
    
    //Test the pantry string array function
    @Test
    public void testStringArrayList() {
    	ArrayList<String> s = new ArrayList<String>();
    	p.addIngredient(new Ingredient("Flour", 3, "mL"));
    	s.add(p.get(0).toString());
    	p.addIngredient(new Ingredient("Bread", 3, "mL"));
    	s.add(p.get(1).toString());
    	p.addIngredient(new Ingredient("Salt", 4, "teaspoons"));
    	s.add(p.get(2).toString());
    	assertEquals("Pantry:stringarrayList:Test 1", p.getStringArrayList(), s);
    }
    
    // Test the toLower function of pantry
    @Test
    public void testToLower() {
    	p.addIngredient(new Ingredient("Flour", 3, "mL"));
    	p.addIngredient(new Ingredient("BrEAD", 3, "CuPs"));
    	p.addIngredient(new Ingredient("SALt", 3, "ITemS"));
    	p.toLower();
    	assertTrue("Pantry:toLower:Test 1", p.isInPantry("bread"));
    	assertFalse("Pantry:toLower:Test 2", p.isInPantry("sALt"));
    }
    
    // Test the toString function of pantry
    @Test
    public void testToString() {
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
        
        
        
