package test.coordinates;

import static org.junit.Assert.*;
import coordinates.GeographicCoordinate;
import coordinates.GeographicCoordinateInterface;
import params.EllipsoidParms;

import org.junit.Test;

public class GeographicCoordinateTest {

	private static final double EPSILONFORDEGREE = 5.e-10;

	/**
	 * @class GeographicCoordinateInterfaceTest
	 * 
	 * @brief test for path 'source coordinate to GeographicCoordinateInterface'
	 *        (and backward) which exists for all coordinates
	 * @remark reason: only the Strategy-class communicates with
	 *         'GeographicCoordinateInterface'
	 * @author Norbert Rösch January 2019
	 * @version 0.1
	 */

	@Test
	public void testGetAsGeographicInterface() {
		EllipsoidParms ellipsoidParms = new EllipsoidParms();
		GeographicCoordinate inputGeographic = new GeographicCoordinate(8.0, 49.0);
		GeographicCoordinateInterface geographicInterface = new GeographicCoordinateInterface(0., 0.);
		geographicInterface = inputGeographic.getAsGeographicInterface(ellipsoidParms);
		assertEquals(geographicInterface.getLatitude(), inputGeographic.getLatitude(), EPSILONFORDEGREE);
		assertEquals(geographicInterface.getLongitude(), inputGeographic.getLongitude(), EPSILONFORDEGREE);
	}

	@Test
	public void testFromGeographicInterface() {
		EllipsoidParms ellipsoidParms = new EllipsoidParms();
		GeographicCoordinate outputGeographic = new GeographicCoordinate(8.0, 49.0);
		GeographicCoordinateInterface geographicInterface = new GeographicCoordinateInterface(0., 0.);
		outputGeographic.getAsTargetCoordinate(ellipsoidParms, geographicInterface);
		assertEquals(geographicInterface.getLatitude(), outputGeographic.getLatitude(), EPSILONFORDEGREE);
		assertEquals(geographicInterface.getLongitude(), outputGeographic.getLongitude(), EPSILONFORDEGREE);
	}

}