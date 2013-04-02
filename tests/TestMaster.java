import ca.ualberta.cs.oneclick_cookbook.*;

public class TestMaster {
	
	private static final int NUM_RECIPES = 20;
	
    public static void main(String args[]) {
    	User user = new User("John Doe", "password", "jd64", "jd64@gmail.com");
    	
    	for (int i = 0; i < NUM_RECIPES; i++) {
    		user.addRecipe(new Recipe().getID());
    	}
    	
    	if (user.getRecipesToString().size() != NUM_RECIPES) {
    		System.out.println("Test 1 failed.");
    	}
    	
    	user.deleteRecipe(2);
    	
    	if (user.getRecipesToString().size() != NUM_RECIPES - 1) {
    		System.out.println("Test 2 failed.");
    	}
    	
    	System.out.println("\nIf you didn't see any errors above, good!");
    }
}
