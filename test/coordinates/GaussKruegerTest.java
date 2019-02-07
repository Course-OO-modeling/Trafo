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
	private final double RHO = 180/Math.PI;
	private final double EPSILON_IN_METER = 0.005;
	private final double EPSILON_IN_DEGREE = 0.00000001;

	/**
     * @brief tests method getAsGeographic from class GaussKrueger, overwritten from abstract class coordinate
     * 	expected values and input values are taken from book "Rechenformeln und Rechenbeispiele zur Landesvermessung - Teil 2" 
     *  from Prof.Dr.-Ing. Albert Schoedlbauer, Robert Wichmann Verlag Karlsruhe, page 88  
     */
	@Test
	public void getAsGeographic() {
		final double expectedLatitude = (48.44595431)/RHO;
		final double expectedLongitude = (10.71647819)/RHO;
		final double expectedHeight = 0.;

		GaussKrueger testCoordinate = new GaussKrueger();
		testCoordinate.setHoch(5368263.249);
		testCoordinate.setRechts(4405057.629);
		testCoordinate.setHeight(0.);
		// Bessel is assumed
		EllipsoidParms ellipsoidParameters = new EllipsoidParms(0.0067192188, 6398786.849);
		GeographicCoordinateInterface resultGeographicCoordinateInterface = testCoordinate.getAsGeographicInterface(ellipsoidParameters);
		
		double resultLatitude = resultGeographicCoordinateInterface.getLatitude();
		double resultLongitude = resultGeographicCoordinateInterface.getLongitude();
		double resultHeight = resultGeographicCoordinateInterface.getHeight();
		
		Assert.assertEquals(
				String.format("Latitude %s of result coordinate does't match expected value %s.", resultLatitude, expectedLatitude), 
				expectedLatitude, resultLatitude, EPSILON_IN_DEGREE);
		Assert.assertEquals(
				String.format("Longitude %s of result coordinate does't match expected value %s.", resultLongitude, expectedLongitude), 
				expectedLongitude, resultLongitude, EPSILON_IN_DEGREE);
		Assert.assertEquals(
				String.format("Height %s of result coordinate does't match expected value %s.", resultHeight, expectedHeight), 
				expectedHeight, resultHeight, EPSILON_IN_DEGREE);
	}
	
	@Test
	public void getAsGaussKrueger() {
		double expectedHoch = 5368263.249;
		double expectedRechts = 4405057.629;
		double expectedHeight = 0.;

		GaussKrueger testCoordinate = new GaussKrueger();
		GeographicCoordinateInterface geographicCoordinateInterface = new GeographicCoordinateInterface();
		geographicCoordinateInterface.setLongitude(10.71647819/RHO);
		geographicCoordinateInterface.setLatitude(48.44595431/RHO);
		geographicCoordinateInterface.setHeight(0.);
		// Bessel is assumed
		EllipsoidParms ellipsoidParameters = new EllipsoidParms(0.0067192188, 6398786.849);
		ellipsoidParameters.setGK_refmer(12.);
		testCoordinate.getAsTargetCoordinate(ellipsoidParameters, geographicCoordinateInterface);
		
		//Assert.assertEquals(12, testCoordinate.getIlao());
		Assert.assertEquals(expectedHoch, testCoordinate.getHoch(), EPSILON_IN_METER);
		Assert.assertEquals(expectedRechts, testCoordinate.getRechts(), EPSILON_IN_METER);
		Assert.assertEquals(expectedHeight, testCoordinate.getHeight(), EPSILON_IN_METER);
	}
}
