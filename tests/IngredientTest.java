import org.junit.*;
import static org.junit.Assert.*;
import ca.ualberta.cs.oneclick_cookbook.Ingredient;
import ca.ualberta.cs.oneclick_cookbook.Constants;

// Class to test ingredient
public class IngredientTest {
    
    private Ingredient i = null;

    @Before
    public void setup() {
        i = new Ingredient("Flour", 42, "Pounds");
    }

    @After 
    public void destory() {
        i = null;
    }

    @Test
    public void testBasics() {
        assertEquals("Test: getName", "Flour", i.getName());
        assertEquals("Test: getQuantity", 42, i.getQuantity());
        assertEquals("Test: getMeasurements", "Pounds", i.getMeasurement());
        i.modifyQuantity(100, "Cups");
        assertEquals("Test: getMeasurements 2", "Cups", i.getMeasurement());
        assertEquals("Test: getQuantity 2", 100, i.getQuantity());
    }

    @Test
    public void testIsValidInfo() {
        assertEquals("Test: valid info", i.isValidInfo(), Constants.GOOD);
        i.modifyQuantity(0,"Stuff");
        assertEquals("Test: zero info", i.isValidInfo(), Constants.ZERO_VALUE);
        i.modifyQuantity(23, null);
        assertEquals("Test: null info", i.isValidInfo(), Constants.NULL_VALUE);
    }

    // Note that this test will likely change a bit
    @Test 
    public void testToString() {
        assertEquals("Test: toString", i.toString(), "42 Pounds of Flour");
    }
}
        
    
