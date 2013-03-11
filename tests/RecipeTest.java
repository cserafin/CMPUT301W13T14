import org.junit.*;
import static org.junit.Assert.*;
import ca.ualberta.cs.oneclick_cookbook.Ingredient;
import ca.ualberta.cs.oneclick_cookbook.Pantry;
import ca.ualberta.cs.oneclick_cookbook.Constants;
import ca.ualberta.cs.oneclick_cookbook.Recipe;

// This class tests the Recipe class
public class RecipeTest {
    
    private Recipe r = null;

    @Before
    public void setup() {
        Pantry p = new Pantry();
        
        p.addIngredient(new Ingredient("Flour", 2, "kilos"));
        p.addIngredient(new Ingredient("Eggs", 3, "Items"));
        p.addIngredient(new Ingredient("Baking soda", 4, "tbsp"));
        p.addIngredient(new Ingredient("Salt", 2, "tsp"));
        p.addIngredient(new Ingredient("Water", 250, "ml"));

        String steps = "1. Put flour, baking soda, and salt into bowl.\n"
            + "2. Add water to mixture.\n"
            + "3. Add eggs. Mix well.\n"
            + "4. Put in the oven and let it bake for 42 min.\n"
            + "5. Enjoy!";

        r = new Recipe("Sweetroll", p, steps);
    }

    @After
    public void destroy() {
        r = null;
    }

    // Very Basic test
    @Test
    public void testBasics() {
        assertEquals("Recipe:testBasics:Test 1", "Sweetroll", r.getName());
        r.changeName("Something else");
        assertEquals("Recipe:testBasics:Test 2", "Something else", r.getName());
    }

    // Basic test to check promotion rating
    @Test
    public void testPromotions() {
        assertEquals("Recipe:testPromotions:Test 1", 0, r.getRating());
        r.promote();
        r.promote();
        r.demote();
        r.demote();
        assertEquals("Recipe:testPromotions:Test 2", 0, r.getRating());
        r.promote();
        r.promote();
        assertEquals("Recipe:testPromotions:Test 3", 2, r.getRating());
    }
        
}
