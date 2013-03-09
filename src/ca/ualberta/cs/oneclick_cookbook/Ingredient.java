package ca.ualberta.cs.oneclick_cookbook;


public class Ingredient {
	private int quantity;
	private String measurement;
	private String name;
	
	public Ingredient (String name, int quantity, String measurement) {
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

    public void setQuantity(int quantity) {
	    this.quantity = quantity;
    }

	public String getMeasurement() {
		return measurement;
	}

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }
	
	// Converts the ingredient to a nicely formatted string
	public String toString() {
		String ingredient = quantity + " " + measurement + " of " + name;
		return ingredient;
	}

    // Checks to make sure the info is valid. Uses constants in this file
    public int isValidInfo() {
        if (name == null || measurement == null) {
            return Constants.NULL_VALUE;
        }
        else if (name.length() == 0 || quantity == 0 || measurement.length() == 0) {
            return Constants.ZERO_VALUE;
        }

        return Constants.GOOD;
    } 

}
