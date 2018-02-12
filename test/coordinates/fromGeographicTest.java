package test.coordinates;

import static org.junit.Assert.*;
import coordinates.GaussKrueger;
import coordinates.*;
import org.junit.Test;
import params.*;
import services.SharedValues;

public class fromGeographicTest extends GaussKrueger implements SharedValues {

    /**
     * @brief Testing of the method fromGeographic from the class coordinates.GausKrueger.java
     */
	@Test
	public void testFromGeographic() {
		
		
		EllipsoidParms EP1 =  new EllipsoidParms();
		
		GeographicCoordinateInterface GC1 = GeographicCoordinateInterface.getInstance(8.40447 / rho, 49.01398 / rho, 114.23);

		GaussKrueger GK1 = new GaussKrueger();
		
		
		GK1.fromGeographicInterface(EP1, GC1);
			
		//assertEquals(114.23, GK1.getHeight(), 0.001);
		//assertEquals(3456512.492, GK1.getRechts(),0.001);
		assertEquals(5430914.164, GK1.getHoch(), 0.001);
	// GK1.hoch wird Ueberhaupt nicht veraendert. 
	}

}