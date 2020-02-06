package test.coordinates;

import static org.junit.Assert.*;
import org.junit.Test;
import coordinates.UTM;
import coordinates.GeographicCoordinateInterface;
import params.EllipsoidParms;

public class UTMTest extends UTM {

	@Test
	public void testGetAsGeographicInterface() {
		final double RHO = 180. / Math.PI;
		EllipsoidParms ell = new EllipsoidParms(0.0067394968, 6399593.626);
		UTM UTM1 = new UTM();
		UTM1.setEast(651416.090);
		UTM1.setNorth(5408463.070);
		UTM1.setNorthhem(true);
		UTM1.setZone(32);
		UTM1.setScale(0.9996);
		UTM1.getAsGeographicInterface(ell);

		assertEquals(48.810694, UTM1.getAsGeographicInterface(ell).getLatitude() * RHO, 0.000001);
		assertEquals(11.062405, UTM1.getAsGeographicInterface(ell).getLongitude() * RHO, 0.000001);
	}

	@Test
	public void testGetAsUTM() {
		final double RHO = 180. / Math.PI;
		EllipsoidParms ell = new EllipsoidParms(0.0067394968, 6399593.626);
		GeographicCoordinateInterface geographicCoodrdinateInterface = new GeographicCoordinateInterface();
		geographicCoodrdinateInterface.setLongitude(11.062405 / RHO);
		geographicCoodrdinateInterface.setLatitude(48.810694 / RHO);
		geographicCoodrdinateInterface.setHeight(0.0);
		UTM UTM1 = new UTM();
		UTM1.getAsTargetCoordinate(ell, geographicCoodrdinateInterface);

		assertEquals(5408463.070, UTM1.getNorth(), 0.02);
		assertEquals(651416.090, UTM1.getEast(), 0.02);
	}
}
