package test.coordinates;

import org.junit.Assert;
import org.junit.Test;

import coordinates.GaussKrueger;
import coordinates.GeographicCoordinateInterface;
import params.EllipsoidParms;

/**
 *  @class GaussKruegerTest
 *  
 *  @brief Test class with unit tests to test behavior class GaussKrueger
 *  
 *  @remark last refactored 11.12.2017 by Eva Majer <br/>
 *  <ul>
 *  	<li>adaptions for ControlParms Singleton</li>
 *  </ul>
 */

public class GaussKruegerTest {
	private static final double RHO = 180/Math.PI;
	private static final double EPSILON = 0.0001;

	/**
     * @brief tests method getAsGeographic from class GaussKrueger, overwritten from abstract class coordinate
     * 	expected values and input values are taken from book "Rechenformeln und Rechenbeispiele zur Landesvermessung - Teil 2" 
     *  from Prof.Dr.-Ing. Albert Schoedlbauer, Robert Wichmann Verlag Karlsruhe, page 88  
     */
	@Test
	public void getAsGeographicTest() {
		final double expectedLatitude = (48.445954311)/RHO;
		final double expectedLongitude = (10.716478196)/RHO;
		final double expectedHeight = 0.;

		GaussKrueger testCoordinate = new GaussKrueger();
		EllipsoidParms ellipsoidParameters = new EllipsoidParms();
		GeographicCoordinateInterface resultGeographicCoordinateInterface = testCoordinate.getAsGeographicInterface(ellipsoidParameters);
		
		double resultLatitude = resultGeographicCoordinateInterface.getLatitude();
		double resultLongitude = resultGeographicCoordinateInterface.getLongitude();
		double resultHeight = resultGeographicCoordinateInterface.getHeight();
		
		Assert.assertEquals(
				String.format("Latitude %s of result coordinate does't match expected value %s.", resultLatitude, expectedLatitude), 
				expectedLatitude, resultLatitude, EPSILON);
		Assert.assertEquals(
				String.format("Longitude %s of result coordinate does't match expected value %s.", resultLongitude, expectedLongitude), 
				expectedLongitude, resultLongitude, EPSILON);
		Assert.assertEquals(
				String.format("Height %s of result coordinate does't match expected value %s.", resultHeight, expectedHeight), 
				expectedHeight, resultHeight, EPSILON);
	}
}
