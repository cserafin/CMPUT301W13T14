import org.junit.*;
import static org.junit.Assert.*;
import ca.ualberta.cs.oneclick_cookbook.Ingredient;
import ca.ualberta.cs.oneclick_cookbook.Pantry;
import ca.ualberta.cs.oneclick_cookbook.Constants;
import ca.ualberta.cs.oneclick_cookbook.Recipe;
import java.util.ArrayList;

/* ************************************************
 * Class that tests the Recipe class.
 *
 * Author: Kenneth Armstrong
 *
 * *********************************************/

public class RecipeTest {

	private Recipe r = null;

	@Before
	public void setup() {
		Pantry p = new Pantry();

		// Add some ingredients
		p.addIngredient(new Ingredient("Flour", 2, "kilos"));
		p.addIngredient(new Ingredient("Eggs", 3, "Items"));
		p.addIngredient(new Ingredient("Baking soda", 4, "tbsp"));
		p.addIngredient(new Ingredient("Salt", 2, "tsp"));
		p.addIngredient(new Ingredient("Water", 250, "ml"));

		// Add some steps
		String steps = "1. Put flour, baking soda, and salt into bowl.\n"
				+ "2. Add water to mixture.\n" + "3. Add eggs. Mix well.\n"
				+ "4. Put in the oven and let it bake for 42 min.\n"
				+ "5. Enjoy!";

		// Make the recipe
		r = new Recipe("Sweetroll", p, steps);
	}

	@After
	public void destroy() {
		r = null;
	}

	// Tests contructor and basics
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

	// test the ID creation process
	@Test
	public void testIDUniqueness() {
		ArrayList<String> ids = new ArrayList<String>();
		Recipe recipe;

		// Loop through and create a bunch of recipes
		for (int i = 0; i < 20000; i++) {
			recipe = new Recipe();
			ids.add(recipe.getID());
		}

		// Loop through and check for duplicate ID's
		for (int i = 0; i < ids.size(); i++) {
			for (int j = i + 1; j < ids.size(); j++) {
				assertFalse("Recipe:testIDUniqueness:Test 1", ids.get(i)
						.equals(ids.get(j)));
			}
		}
	}

	// Test the isValidInfo function
	@Test
	public void testIsValidInfo() {
		// Should have valid info to begin
		assertTrue("Recipe:testValidInfo:Test 1",
				r.isValidInfo() == Constants.GOOD);

		// Change to invalid info and test
		r.changeName("");
		assertFalse("Recipe:testValidInfo:Test 2",
				r.isValidInfo() == Constants.GOOD);

		// Change steps to invalid info and test
		r.changeName("Sweetroll");
		r.changeSteps(null);
		assertFalse("Recipe:testValidInfo:Test 3",
				r.isValidInfo() == Constants.GOOD);
	}
}
