package test.coordinates;

import static org.junit.Assert.*;

import org.junit.Test;

import coordinates.Gauss;
import coordinates.GeographicCoordinateInterface;
import params.EllipsoidParms;

/**
 *  @class GaussTest
 *  
 *  @brief Test class with unit tests to test behavior class Gauss
 *  
 *  @remark implemented 7.02.2019 by Norbert Rösch
 *  @remark this test should be run before UTMTest and GaussKruegerTest
 */

public class GaussTest {
	private final double RHO = 180/Math.PI;
	private final double EPSILON_IN_METER = 0.0004;

	@Test
	public void testMeridianLength() {
		int kennlao = 0;
        double hnull = 0.;
        EllipsoidParms ell = new EllipsoidParms(0.0067192188, 6398786.849);
        ell.setGK_refmer(12);
        GeographicCoordinateInterface geocoord = new GeographicCoordinateInterface();
        geocoord.setLatitude(48.44595431/RHO);

        kennlao = (int) ell.getGK_refmer() / 3;

        /* meridian arc length */
        hnull = Gauss.meridianLength(geocoord.getLatitude(), ell);
        assertEquals(kennlao, 4);
        assertEquals(5367467.3856, hnull, EPSILON_IN_METER);
	}
}