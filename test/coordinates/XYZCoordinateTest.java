package test.coordinates;

import static org.junit.Assert.*;

import org.junit.Test;

import coordinates.CoordinateFactory;
import coordinates.GeographicCoordinateInterface;
import coordinates.XYZCoordinate;
import params.EllipsoidParms;
//import params.LatParm;

public class XYZCoordinateTest {

	@Test
	public void testGetAsGeographicInterface() {
		/** @remark got reference data from www.sapos-bw.de/trafoErg_B_X.php **/
		final double RHO = 180. / Math.PI;
		GeographicCoordinateInterface geo = CoordinateFactory.getGeographicCoordinateInterface();
		XYZCoordinate xyzCoordinate = new XYZCoordinate(4145957.8404159, 619617.55080143, 4790162.7065932);
		EllipsoidParms ell = new EllipsoidParms(0.0067192188, 6398786.849);
		
		geo = xyzCoordinate.getAsGeographicInterface(ell);
		assertEquals(8.5, geo.getLongitude()*RHO, 0.0000001);
		assertEquals(49.0, geo.getLatitude()*RHO, 0.0000001);
		assertEquals(120, geo.getHeight(), 0.001);
	}

	@Test
	public void testGetAsTargetCoordinate() {
		
		fail("Not yet implemented");
	}

}
