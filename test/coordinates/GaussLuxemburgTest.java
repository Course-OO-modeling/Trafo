package test.coordinates;

import static org.junit.Assert.*;
import org.junit.Test;

import coordinates.GaussLuxemburg;
import coordinates.GeographicCoordinateInterface;
import params.EllipsoidParms;

public class GaussLuxemburgTest {

	@Test
	public void testGetAsGeographicInterface() {
		final double EpsilonInMeter = 0.1;
		final double resultLongitude = 49.571556353;
		final double resultLatitude = 5.930748120;
		GaussLuxemburg testCoordinate = new GaussLuxemburg();
		// Hayford 1924
		EllipsoidParms ellipsoidParameters = new EllipsoidParms(0.006768170197, 6378388.0);
		GeographicCoordinateInterface resultGeographicCoordinateInterface = testCoordinate
				.getAsGeographicInterface(ellipsoidParameters);
		assertEquals(resultLongitude, resultGeographicCoordinateInterface.getLongitude(),EpsilonInMeter);
		assertEquals(resultLatitude, resultGeographicCoordinateInterface.getLatitude(),EpsilonInMeter);
		fail("Not yet finished");
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
