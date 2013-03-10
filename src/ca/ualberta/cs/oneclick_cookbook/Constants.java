package ca.ualberta.cs.oneclick_cookbook;

public class Constants { 
    public static final int ZERO_VALUE = 0;
    public static final int NULL_VALUE = -1;
    public static final int GOOD = 1;
    public static final int ML = 0;
    public static final int L = 1;
    public static final int G = 2;
    public static final int KG = 3;
    public static final int INDIV = 4;
    public static final int CUPS = 5;
    public static final int TBS = 6;
    public static final int TSP = 7;
    // This is important!! Make sure it's up to date
    public static final int NUM_OF_UNITS = 8;
    
    // Method to convert from spinner position to units
    public static String getUnitFromPosition(int position) {
    	switch (position) {
    	case ML:
    		return "ml";
    	case G:
    		return "g";
    	case KG:
    		return "kg";
    	case INDIV:
    		return "items";
    	case CUPS:
    		return "cups";
    	case TBS:
    		return "tablespoons";
    	case TSP:
    		return "teaspoons";
    	default:
    		return "ERROR";
    	}
    }
}

