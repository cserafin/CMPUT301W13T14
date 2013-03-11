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
        assertTrue("Test: pantrySearch1", p.isInPantry("Flour"));
        p.emptyPantry();
        assertFalse("Test: pantrySearch2", p.isInPantry("Flour"));
    }
                   
}
        
        
        
