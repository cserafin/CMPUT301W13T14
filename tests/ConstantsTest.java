import org.junit.*;
import static org.junit.Assert.*;
import ca.ualberta.cs.oneclick_cookbook.Constants;

// Class to test ingredient
public class ConstantsTest {

    @Test
    public void testGetUnitFromPosition() {
        assertEquals("Constants:getUnit:Test 1", "mL", 
                     Constants.getUnitFromPosition(0));
        assertEquals("Constants:getUnit:Test 2", "cups", 
                     Constants.getUnitFromPosition(5));
        assertEquals("Constants:getUnit:Test 3", "units", 
                     Constants.getUnitFromPosition(8000));
        assertEquals("Constants:getUnit:Test 4", "units", 
                     Constants.getUnitFromPosition(-1));
    }

    @Test
    public void testGetPositionFromUnit() {
        assertEquals("Constants:getPosition:Test 1", 2,
                     Constants.getPositionFromUnit("g"));
        assertEquals("Constants:getPosition:Test 2", 1,
                     Constants.getPositionFromUnit("L"));
        assertEquals("Constants:getPosition:Test 3", 4,
                     Constants.getPositionFromUnit(""));
        assertEquals("Constants:getPosition:Test 4", 4,
                     Constants.getPositionFromUnit("wrong"));
    }
}
        
