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
 * @class MockStrategyTest
 * 
 * @brief Test class with two integration tests to test the transition of a
 *        Gauss-Krueger-coordinate from the 3rd to the 4th zone and vice
 *        versa. The datumstrategy "MockStrategy" is used.
 * @author Jessica Palka
 * @date December 2019
 */

public class MockStrategyTest {
	private final double EPSILON_IN_METER = 0.001;

	/**
	 * @brief Test method "testTransformGaussKruegerZone3ToZone4" to test the
	 *        transition of a Gauss-Krueger-coordinate from the third to the fourth
	 *        zone without changing the geodetic datum (DHDN).
	 * @remark Expected values and input values for this test are taken from
	 *         Prof.Dr.-Ing. Albert Schoedlbauer, "Rechenformeln und Rechenbeispiele
	 *         zur Landesvermessung - Teil 2", Robert Wichmann Verlag Karlsruhe,
	 *         page 153
	 */

	@Test
	public void testTransformGaussKruegerZone3ToZone4() {
		double expectedHochZone4 = 5368263.248;
		double expectedRechtsZone4 = 4405057.629;
		double expectedHeightZone4 = 0.;

		// Set input Gauss-Krueger-coordinates (third zone)
		GaussKrueger testCoordinateZone3To4 = new GaussKrueger();
		testCoordinateZone3To4.setHoch(5368890.802);
		testCoordinateZone3To4.setRechts(3626967.248);
		testCoordinateZone3To4.setHeight(0.);

		// Bessel-ellipsoid is assumed
		EllipsoidParms besselParameters = new EllipsoidParms(0.0067192188, 6398786.849);

		// Transform Gauss-Krueger-coordinates (third zone) to geographic coordinates
		GeographicCoordinateInterface resultGeographicCoordinateInterface = testCoordinateZone3To4
				.getAsGeographicInterface(besselParameters);

		// Perform mock strategy for the transformation
		ControlParms control = ControlParms.getInstance();
		control.setKindOfTrafo("none");
		TransformationStrategy datumDhdnToDhdn = GetAppropriateTransformationAlgorithm.getStrategy();
		datumDhdnToDhdn.transform(resultGeographicCoordinateInterface);

		// Transform geographic coordinates to Gauss-Krueger-coordinates (fourth zone)
		besselParameters.setGK_refmer(12.);
		testCoordinateZone3To4.getAsTargetCoordinate(besselParameters, resultGeographicCoordinateInterface);

		// Assert results
		assertEquals(expectedHochZone4, testCoordinateZone3To4.getHoch(), EPSILON_IN_METER);
		assertEquals(expectedRechtsZone4, testCoordinateZone3To4.getRechts(), EPSILON_IN_METER);
		assertEquals(expectedHeightZone4, testCoordinateZone3To4.getHeight(), EPSILON_IN_METER);
	}

	/**
	 * @brief Test method "testTransformGaussKruegerZone4ToZone3" to test the
	 *        transition of a Gauss-Krueger-coordinate from the fourth to the third
	 *        zone without changing the geodetic datum (DHDN).
	 * @remark Expected values and input values for this test are taken from
	 *         Prof.Dr.-Ing. Albert Schoedlbauer, "Rechenformeln und Rechenbeispiele
	 *         zur Landesvermessung - Teil 2", Robert Wichmann Verlag Karlsruhe,
	 *         page 154
	 */

	@Test
	public void testTransformGaussKruegerZone4ToZone3() {
		double expectedHochZone3 = 5368890.802;
		double expectedRechtsZone3 = 3626967.248;
		double expectedHeightZone3 = 0.;

		// Set input Gauss-Krueger-coordinates (fourth zone)
		GaussKrueger testCoordinateZone4To3 = new GaussKrueger();
		testCoordinateZone4To3.setHoch(5368263.248);
		testCoordinateZone4To3.setRechts(4405057.629);
		testCoordinateZone4To3.setHeight(0.);

		// Bessel-ellipsoid is assumed
		EllipsoidParms besselParameters = new EllipsoidParms(0.0067192188, 6398786.849);

		// Transform Gauss-Krueger-coordinates (fourth zone) to geographic coordinates
		GeographicCoordinateInterface resultGeographicCoordinateInterface = testCoordinateZone4To3
				.getAsGeographicInterface(besselParameters);

		// Perform mock strategy for the transformation
		ControlParms control = ControlParms.getInstance();
		control.setKindOfTrafo("none");
		TransformationStrategy datumDhdnToDhdn = GetAppropriateTransformationAlgorithm.getStrategy();
		datumDhdnToDhdn.transform(resultGeographicCoordinateInterface);

		// Transform geographic coordinates to Gauss-Krueger-coordinates (third zone)
		besselParameters.setGK_refmer(9.);
		testCoordinateZone4To3.getAsTargetCoordinate(besselParameters, resultGeographicCoordinateInterface);

		// Assert results
		assertEquals(expectedHochZone3, testCoordinateZone4To3.getHoch(), EPSILON_IN_METER);
		assertEquals(expectedRechtsZone3, testCoordinateZone4To3.getRechts(), EPSILON_IN_METER);
		assertEquals(expectedHeightZone3, testCoordinateZone4To3.getHeight(), EPSILON_IN_METER);
	}
}
