import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import ca.ualberta.cs.oneclick_cookbook.User;
import ca.ualberta.cs.oneclick_cookbook.Pantry;
import ca.ualberta.cs.oneclick_cookbook.Recipe;

/* *********************************
 * Class that tests the User class.
 * 
 * Author: Kenneth Armstrong
 * 
 */

public class UserTest {
	private User u;

	@Before
	public void setup() {
		u = new User("John Doe", "password", "jd42", "jd42@gmail.com");
	}

	@After
	public void destroy() {
		u = null;
	}

	// Test the basics of the User class
	@Test
	public void testBasics() {
		// Test the constructor and getters/setters
		assertEquals("User:testBasics:Test 1", u.getEmailAddress(), "jd42@gmail.com");
		assertEquals("User:testBasics:Test 2", u.getUserName(), "John Doe");
		assertEquals("User:testBasics:Test 3", u.getScreenName(), "jd42");
	}
	
	@Test
	public void testAddRemoveRecipe() {
		// Make a recipe
		Recipe r = new Recipe("Sweetroll", new Pantry(), "Cook it.");
		
		// Add the recipe to the user and make sure it's there
		u.addRecipe(r.getID());
		assertEquals("User:testAddRemove:Test 1", r.getID(), u.getUserRecipes().get(0));
		
		// Delete the recipe and make sure it's gone.
		u.deleteRecipe(0);
		assertTrue("User:testAddRemove:Test 1", u.getUserRecipes().isEmpty());
	}
	
	@Test
	public void testRecipesToString() {
		// Create an array list of strings
		ArrayList<String> ids = new ArrayList<String>();
		Recipe recipe;
		
		// Go through and add the strings to each list
		for (int i = 0; i<20; i++) {
			recipe = new Recipe();
			ids.add(recipe.getID());
			u.addRecipe(recipe.getID());
		}
		
		// Make sure the lists are the same
		assertEquals("User:testRecipesString:Test 1",
					ids, u.getUserRecipes());
		
	}
}