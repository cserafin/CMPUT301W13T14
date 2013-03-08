import ca.ualberta.cs.oneclick_cookbook.Ingredient;

public class TestMaster {

    public static void main(String args[]) {
	System.out.println("Hello!!");
	Ingredient i = new Ingredient("Flour", 34, "Cups");
	System.out.println(i.toString());
    }
}
