package test.datumstrategy;

import static org.junit.Assert.*;
import org.junit.Test;
import datumstrategy.TransformationStrategy;
import params.ControlParms;
import coordinates.GeographicCoordinateInterface;
import datumstrategy.GetAppropriateTransformationAlgorithm;

/**
 * @class SpatialSimilarityTransformationInfinTest
 * @brief Tests the Spatial Similarity Transformation as a Datum Strategy. Input
 *        values and expected values were calculated by hand.
 * @author Steven Landgraf
 * @date December 2019
 * @version 0.1
 * 
 */

public class SpatialSimilarityTransformationInfinTest {

	private final double RHO = 180. / Math.PI;
	private final double EPSILON_IN_METER = 0.001;
	private final double EPSILON_IN_RAD = 0.00000001;

	@Test
	public void testTransform() {

		ControlParms control = ControlParms.getInstance();
		control.setKindOfTrafo("3DInfin");
		control.setDx(100.);
		control.setDy(100.);
		control.setDz(100.);
		control.setWx(1. / RHO);
		control.setWy(1. / RHO);
		control.setWz(0.);
		control.setMassstab(1.1);

		final double expectedLatitude = (45.68278397) / RHO;
		final double expectedLongitude = (16.23227418) / RHO;
		final double expectedHeight = 638421.64879;

		GeographicCoordinateInterface testCoordinate = GeographicCoordinateInterface.getInstance();

		testCoordinate.setLatitude(45. / RHO);
		testCoordinate.setLongitude(15. / RHO);
		testCoordinate.setHeight(0.);

		TransformationStrategy dat = GetAppropriateTransformationAlgorithm.getStrategy();
		dat.transform(testCoordinate);

		assertEquals(expectedLatitude, testCoordinate.getLatitude(), EPSILON_IN_RAD);
		assertEquals(expectedLongitude, testCoordinate.getLongitude(), EPSILON_IN_RAD);
		assertEquals(expectedHeight, testCoordinate.getHeight(), EPSILON_IN_METER);
	}
}
