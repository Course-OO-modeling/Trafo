package test.coordinates;

import org.junit.Assert;
import org.junit.Test;

import coordinates.GeographicCoordinateInterface;
import coordinates.UTM;
import params.EllipsoidParms;

public class UTMTest {
	private static final double RHO = 180/Math.PI;
	private static final double DELTA = 0.0001;

	/**
     * @brief tests method fromGeographic from class UTM, overwritten from abstract class coordinate
     * 	expected values and input values are taken from book "Rechenformeln und Rechenbeispiele zur Landesvermessung - Teil 2" 
     *  from Prof.Dr.-Ing. Albert Schoedlbauer, Robert Wichmann Verlag Karlsruhe, page 51  
     *  
     *  CURRENTLY FAILS!
     */
	@Test
	public void fromGeographicTest() {
		final double inputLongitude = 10.716273056/RHO;
		final double inputLatitude = 48.445827528/RHO;
		final double inputHeight = 0.0;
		final double inputEs2 = 0.00676817;
		final double inputC = 6397376.633;
		
		final double expectedRechtsWert = 126923.0496;
		final double expectedHochWert = 5367382.31;
		final double expectedHeight = 0.0;
		
		GeographicCoordinateInterface geographicCoordinateInterface = GeographicCoordinateInterface.getInstance(
				inputLongitude, inputLatitude, inputHeight);
		EllipsoidParms ellipsoidParameters = new EllipsoidParms(inputEs2, inputC);
		UTM utmResult = new UTM();
		utmResult.fromGeographicInterface(ellipsoidParameters, geographicCoordinateInterface);
		
		double resultRechtswert = utmResult.getOrdinate();
		double resultHochwert = utmResult.getAbszisse();
		double resultHeight = utmResult.getHeight();
		
		Assert.assertEquals(
				String.format("Rechtswert %s of result coordinate does't match expected value %s.", resultRechtswert, expectedRechtsWert), 
				expectedRechtsWert, resultRechtswert, DELTA);
		Assert.assertEquals(
				String.format("Hochwert %s of result coordinate does't match expected value %s.", resultHochwert, expectedHochWert), 
				expectedHochWert, resultHochwert, DELTA);
		Assert.assertEquals(
				String.format("Height %s of result coordinate does't match expected value %s.", resultHeight, expectedHeight), 
				expectedHeight, resultHeight, DELTA);
	}
}
