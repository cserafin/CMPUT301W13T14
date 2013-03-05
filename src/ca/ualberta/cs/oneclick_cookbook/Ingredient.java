package ca.ualberta.cs.oneclick_cookbook;

public class Ingredient {
	private int quantity;
	private String measurement;
	private String name;
	
	Ingredient (String name, int quantity, String measurement) {
		this.name = name;
		this.quantity = quantity;
		this.measurement = measurement;
	}
	
	public void modifyQuantity (int quantity, String measurement) {
		this.quantity = quantity;
		this.measurement = measurement;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String newName){
		this.name = newName;
	}

	public int getQuantity() {
		return quantity;
	}

	public String getMeasurement() {
		return measurement;
	}
	
	//Does not physically print it out, it only changes it to a string.
	//Should we modify the name to "toString()"?
	public String printIngredient() {
		
		String ingredient = quantity +" "+measurement+" of "+name;
		return ingredient;
	}

}
