import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

// From www.vogella.com/articles/JUnit/article.html

@RunWith(Suite.class)
@SuiteClasses({ IngredientTest.class, PantryTest.class, RecipeTest.class,
            ConstantsTest.class, FriendTest.class })
public class AllTests {

} 
