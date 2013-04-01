import org.junit.*;
import static org.junit.Assert.*;
import ca.ualberta.cs.oneclick_cookbook.Ingredient;
import ca.ualberta.cs.oneclick_cookbook.Constants;

/* **********************************************
 * Class to test the Ingredient class.
 *
 * Author: Kenneth Armstrong
 *
 * ********************************************/

public class IngredientTest {
    
    private Ingredient i = null;

    @Before
    public void setup() {
        // Create a new ingredient object
        i = new Ingredient("Flour", 42, "Pounds");
    }

    @After 
    public void destory() {
        i = null;
    }

    @Test
    public void testBasics() {
        // Test some of the basic getters and setters
        assertEquals("Test: getName", "Flour", i.getName());
        assertEquals("Test: getQuantity", 42, i.getQuantity());
        assertEquals("Test: getMeasurements", "Pounds", i.getMeasurement());
        i.modifyQuantity(100, "Cups");
        assertEquals("Test: getMeasurements 2", "Cups", i.getMeasurement());
        assertEquals("Test: getQuantity 2", 100, i.getQuantity());
    }

    @Test
    public void testIsValidInfo() {
        // Test isValidInfo function of Ingredient class
        // Should be good to start
        assertEquals("Test: valid info", i.isValidInfo(), Constants.GOOD);
        // Pass it a zero value and test
        i.modifyQuantity(0,"Stuff");
        assertEquals("Test: zero info", i.isValidInfo(), Constants.ZERO_VALUE);
        // Pass it a null value and test
        i.modifyQuantity(23, null);
        assertEquals("Test: null info", i.isValidInfo(), Constants.NULL_VALUE);
    }

    // Note that this test will likely change a bit
    @Test 
    public void testToString() {
        // Test string formatting
        assertEquals("Test: toString", i.toString(), "42 Pounds of Flour");
    }

    @Test
    public void testToLower() {
        // Change the ingredient to lower case and make sure it worked
        i.toLower();
        assertEquals("Test: toLower: Test 1", i.toString(), "42 pounds of flour");
    } 
}
        
    
