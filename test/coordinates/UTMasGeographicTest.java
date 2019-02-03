package test.coordinates;

import static org.junit.Assert.*;
import org.junit.Test;
import coordinates.UTM;
import params.EllipsoidParms;

public class UTMasGeographicTest extends UTM {

	@Test
	public void testGetAsGeographic() {
		final double RHO = 180. / Math.PI;
		EllipsoidParms ell = new EllipsoidParms();
		UTM UTM1 = new UTM();
		UTM1.setEast(651416.090);
		UTM1.setNorth(5408463.070);
		UTM1.setNorthhem(true);
		UTM1.setZone(32);
		UTM1.setScale(0.9996);
		UTM1.getAsGeographicInterface(ell);
		
		assertEquals(48.810694, UTM1.getAsGeographicInterface(ell).getLatitude() * RHO, 0.001);
		assertEquals(11.062405, UTM1.getAsGeographicInterface(ell).getLongitude() * RHO, 0.00001);
	}

}
