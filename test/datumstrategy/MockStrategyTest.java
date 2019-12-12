package test.datumstrategy;

import static org.junit.Assert.*;
import org.junit.Test;

import coordinates.GaussKrueger;
import coordinates.GeographicCoordinateInterface;
import datumstrategy.GetAppropriateTransformationAlgorithm;
import datumstrategy.TransformationStrategy;
import params.EllipsoidParms;
import params.ControlParms;

/**
 *  @class MockStrategyTest
 *  
 *  @brief Test class with two integration tests to test the transition of a Gauss-Krueger-coordinate from the third 
 *  to the fourth strip and vice versa. The datumstrategy "MockStrategy" is used.
 *  @author Jessica Palka
 *  @date December 2019 
 */

public class MockStrategyTest {
	private final double EPSILON_IN_METER = 0.001;

	/**
     * @brief Test method "testTransformGaussKruegerStrip3ToStrip4" to test the transition of a Gauss-Krueger-coordinate 
     *  from the third to the fourth strip without changing the geodetic datum (DHDN).
     * @remark Expected values and input values for this test are taken from Prof.Dr.-Ing. Albert Schoedlbauer,
     *  "Rechenformeln und Rechenbeispiele zur Landesvermessung - Teil 2", Robert Wichmann Verlag Karlsruhe, page 153 
     */
	
	@Test
	public void testTransformGaussKruegerStrip3ToStrip4() {	
		double expectedHochStrip4 = 5368263.248;
		double expectedRechtsStrip4 = 4405057.629;
		double expectedHeightStrip4 = 0.;
		//int expectedCentralMeridian = 0;
		
		// Set input Gauss-Krueger-coordinates (third strip)
		GaussKrueger testCoordinateStrip3To4 = new GaussKrueger();
		testCoordinateStrip3To4.setHoch(5368890.802);
		testCoordinateStrip3To4.setRechts(3626967.248);
		testCoordinateStrip3To4.setHeight(0.);
		
		// Bessel-ellipsoid is assumed
		EllipsoidParms besselParameters = new EllipsoidParms(0.0067192188, 6398786.849);
		
		// Transform Gauss-Krueger-coordinates (third strip) to geographic coordinates
		GeographicCoordinateInterface resultGeographicCoordinateInterface = testCoordinateStrip3To4.getAsGeographicInterface(besselParameters);
		
		// Perform mock strategy for the transformation
		ControlParms control = ControlParms.getInstance();
		control.setKindoftrafo(null);
		TransformationStrategy datumDhdnToDhdn = GetAppropriateTransformationAlgorithm.getStrategy();
		datumDhdnToDhdn.transform(resultGeographicCoordinateInterface);
	
		// Transform geographic coordinates to Gauss-Krueger-coordinates (fourth strip)
		besselParameters.setGK_refmer(12.);
		testCoordinateStrip3To4.getAsTargetCoordinate(besselParameters, resultGeographicCoordinateInterface);
		
		// Assert results
		assertEquals(expectedHochStrip4, testCoordinateStrip3To4.getHoch(), EPSILON_IN_METER);
		assertEquals(expectedRechtsStrip4, testCoordinateStrip3To4.getRechts(), EPSILON_IN_METER);
		assertEquals(expectedHeightStrip4, testCoordinateStrip3To4.getHeight(), EPSILON_IN_METER);
		//assertEquals(expectedCentralMeridian, testCoordinateStrip3To4.getIlao());
	}

	
	/**
     * @brief Test method "testTransformGaussKruegerStrip4ToStrip3" to test the transition of a Gauss-Krueger-coordinate 
     *  from the fourth to the third strip without changing the geodetic datum (DHDN).
     * @remark Expected values and input values for this test are taken from Prof.Dr.-Ing. Albert Schoedlbauer,
     *  "Rechenformeln und Rechenbeispiele zur Landesvermessung - Teil 2", Robert Wichmann Verlag Karlsruhe, page 154 
     */
	
	@Test
	public void testTransformGaussKruegerStrip4ToStrip3() {	
		double expectedHochStrip3 = 5368890.802;
		double expectedRechtsStrip3 = 3626967.248;
		double expectedHeightStrip3 = 0.;
		//int expectedCentralMeridian = 0;
		
		// Set input Gauss-Krueger-coordinates (fourth strip)
		GaussKrueger testCoordinateStrip4To3 = new GaussKrueger();
		testCoordinateStrip4To3.setHoch(5368263.248);
		testCoordinateStrip4To3.setRechts(4405057.629);
		testCoordinateStrip4To3.setHeight(0.);
		
		// Bessel-ellipsoid is assumed
		EllipsoidParms besselParameters = new EllipsoidParms(0.0067192188, 6398786.849);
		
		// Transform Gauss-Krueger-coordinates (fourth strip) to geographic coordinates
		GeographicCoordinateInterface resultGeographicCoordinateInterface = testCoordinateStrip4To3.getAsGeographicInterface(besselParameters);
		
		// Perform mock strategy for the transformation
		ControlParms control = ControlParms.getInstance();
		control.setKindoftrafo(null);
		TransformationStrategy datumDhdnToDhdn = GetAppropriateTransformationAlgorithm.getStrategy();
		datumDhdnToDhdn.transform(resultGeographicCoordinateInterface);
	
		// Transform geographic coordinates to Gauss-Krueger-coordinates (third strip)
		besselParameters.setGK_refmer(9.);
		testCoordinateStrip4To3.getAsTargetCoordinate(besselParameters, resultGeographicCoordinateInterface);
		
		// Assert results
		assertEquals(expectedHochStrip3, testCoordinateStrip4To3.getHoch(), EPSILON_IN_METER);
		assertEquals(expectedRechtsStrip3, testCoordinateStrip4To3.getRechts(), EPSILON_IN_METER);
		assertEquals(expectedHeightStrip3, testCoordinateStrip4To3.getHeight(), EPSILON_IN_METER);
		//assertEquals(expectedCentralMeridian, testCoordinateStrip4To3.getIlao());
	}
}
