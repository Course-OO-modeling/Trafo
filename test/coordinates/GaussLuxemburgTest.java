package test.coordinates;

import static org.junit.Assert.*;
import org.junit.Test;

import coordinates.GaussKrueger;
import coordinates.GaussLuxemburg;
import coordinates.GeographicCoordinateInterface;
import params.EllipsoidParms;

public class GaussLuxemburgTest {

	@Test
	public void testGetAsGeographicInterface() {
		double resultLongitude = 49.57156;
		double resultLatitude = 5.93075;
		GaussLuxemburg testCoordinate = new GaussLuxemburg();
		EllipsoidParms ellipsoidParameters = new EllipsoidParms(0.006768170197, 6378388.0);
		GeographicCoordinateInterface resultGeographicCoordinateInterface = testCoordinate
				.getAsGeographicInterface(ellipsoidParameters);
		assertEquals(resultLongitude, resultGeographicCoordinateInterface.getLongitude(),0.1);
		assertEquals(resultLatitude, resultGeographicCoordinateInterface.getLatitude(),0.1);
		fail("Not yet implemented");
	}

	@Test
	public void testGetAsTargetCoordinate() {
		fail("Not yet implemented");
	}

	@Test
	public void testPrint() {
		fail("Not yet implemented");
	}

}
