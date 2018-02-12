package test.coordinates;

import static org.junit.Assert.*;
import org.junit.Test;
import coordinates.UTM;
import services.SharedValues;
import params.EllipsoidParms;

public class UTMasGeographic extends UTM implements SharedValues {

	@Test
	public void testGetAsGeographic() {
		EllipsoidParms ell = new EllipsoidParms();
		UTM UTM1 = new UTM();
		UTM1.getAsGeographicInterface(ell);
		
		assertEquals(48.810694, UTM1.getAsGeographicInterface(ell).getLatitude() * rho, 0.001);
		assertEquals(11.062405, UTM1.getAsGeographicInterface(ell).getLongitude() * rho, 0.00001);
	}

}
