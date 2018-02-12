package test.coordinates;

import coordinates.*;
import org.junit.Test;
import static org.junit.Assert.assertTrue;


/**
 *  @class getCoordTest
 *  @brief Testing of the method getCoord from the class coordinates.CoordinateFactory.java
 *  @author Eva Majer
 *  @remark refactored 2018-03-02 by Eva Majer <br/>
 *  <ul>
 *  	<li>adaptions for ControlParms Singleton</li>
 *  	<li>Translation of some comments</li></li>
 *  </ul>
 *  @version 0.1
 */

public class getCoordTest {

    @Test
    public void getCoordTest1() {
        String type1 = "gk", type2 ="utm", type3 = "soldner", type4 = "xyz";
        assertTrue(CoordinateFactory.getCoord(type1) instanceof GaussKrueger);
        assertTrue(CoordinateFactory.getCoord(type2) instanceof UTM);
        assertTrue(CoordinateFactory.getCoord(type3) instanceof Soldner);
        assertTrue(CoordinateFactory.getCoord(type4) instanceof XYZCoordinate);
    }
}