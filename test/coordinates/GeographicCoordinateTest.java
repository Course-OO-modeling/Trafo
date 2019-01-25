package test.coordinates;

import static org.junit.Assert.*;

import coordinates.CoordinateFactory;
import coordinates.GeographicCoordinate;
import coordinates.CoordinateFactory;
import coordinates.GeographicCoordinateInterface;
import params.EllipsoidParms;

import org.junit.Test;

public class GeographicCoordinateTest {

	@Test
	public void testGetAsGeographicInterface() {
		EllipsoidParms ellipsoidParms = new EllipsoidParms();
		GeographicCoordinate inputGeographic = new GeographicCoordinate();
		GeographicCoordinateInterface geographicInterface = CoordinateFactory.getGeographicCoordinateInterface();
		geographicInterface = inputGeographic.getAsGeographicInterface(ellipsoidParms);
		assertEquals(geographicInterface.getLatitude(), inputGeographic.getLatitude(), 0.00001);
		assertEquals(geographicInterface.getLongitude(), inputGeographic.getLongitude(), 0.00001);
	}

	@Test
	public void testFromGeographicInterface() {
		EllipsoidParms ellipsoidParms = new EllipsoidParms();
		GeographicCoordinate outputGeographic = new GeographicCoordinate();
		GeographicCoordinateInterface geographicInterface = CoordinateFactory.getGeographicCoordinateInterface();
		outputGeographic.fromGeographicInterface(ellipsoidParms, geographicInterface);
		assertEquals(geographicInterface.getLatitude(), outputGeographic.getLatitude(), 0.00001);
		assertEquals(geographicInterface.getLongitude(), outputGeographic.getLongitude(), 0.00001);
	}

}
