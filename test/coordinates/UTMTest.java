package test.coordinates;

import static org.junit.Assert.*;
import org.junit.Test;
import coordinates.UTM;
import coordinates.GeographicCoordinateInterface;
import params.ControlParms;
import params.EllipsoidParms;

public class UTMTest extends UTM {

	@Test
	public void testGetAsGeographicInterface() {
		final double RHO = 180. / Math.PI;
		EllipsoidParms ell = new EllipsoidParms(0.0067394968, 6399593.626);
		ControlParms controller = ControlParms.getInstance();
		controller.setSourceCoordinateX(456450.77); // Karlsruhe Schlossturm
		controller.setSourceCoordinateY(5429179.93);
		controller.setSourceCoordinateZ(0.);
		
		UTM UTM1 = new UTM();
		UTM1.getAsGeographicInterface(ell);

		assertEquals(49.0139728, UTM1.getAsGeographicInterface(ell).getLatitude() * RHO, 0.000001);
		assertEquals(8.4044295, UTM1.getAsGeographicInterface(ell).getLongitude() * RHO, 0.000001);
	}

	@Test
	public void testGetAsUTM() {
		final double RHO = 180. / Math.PI;
		EllipsoidParms ell = new EllipsoidParms(0.0067394968, 6399593.626);
		GeographicCoordinateInterface geographicCoodrdinateInterface = new GeographicCoordinateInterface();
		geographicCoodrdinateInterface.setLongitude(8.4044295 / RHO);
		geographicCoodrdinateInterface.setLatitude(49.0139728 / RHO);
		geographicCoodrdinateInterface.setHeight(0.0);
		UTM UTM1 = new UTM();
		UTM1.getAsTargetCoordinate(ell, geographicCoodrdinateInterface);

		assertEquals(5429179.93, UTM1.getNorth(), 0.02);
		assertEquals(456450.77, UTM1.getEast(), 0.02);
	}
}
