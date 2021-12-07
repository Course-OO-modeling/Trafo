package test.datumstrategy;

import static org.junit.Assert.*;
import org.junit.Test;
import datumstrategy.TransformationStrategy;
import params.ControlParms;
import coordinates.GeographicCoordinateInterface;
import datumstrategy.GetAppropriateTransformationAlgorithm;

/**
 * @class SpatialSimilarityTransformationTrigTest
 * @brief Tests the Spatial Similarity Transformation as a Datum Strategy. Input
 *        values and expected values were calculated by hand.
 * @author Wentao Lu
 * @date Jan 2020
 * @version 0.1
 * 
 */

public class SpatialSimilarityTransformationTrigTest {

	private final double RHO = 180 / Math.PI;
	private final double EPSILON_IN_METER = 0.001;
	private final double EPSILON_IN_RAD = 0.00000001;

	@Test
	public void testTransform() {

		ControlParms control = ControlParms.getInstance();
		control.setKindoftrafo("3DTrig");
		control.setDx(100);
		control.setDy(100);
		control.setDz(100);
		control.setWx(60 / RHO);
		control.setWy(60 / RHO);
		control.setWz(0);
		control.setMassstab(1.1);

		final double expectedLatitude = (45.41306469) / RHO;
		final double expectedLongitude = (84.06362314) / RHO;
		final double expectedHeight = 635482.79430;

		GeographicCoordinateInterface testCoordinate = GeographicCoordinateInterface.getInstance();

		testCoordinate.setLatitude(49.0 / RHO);
		testCoordinate.setLongitude(8.5 / RHO);
		testCoordinate.setHeight(120.);

		TransformationStrategy dat = GetAppropriateTransformationAlgorithm.getStrategy();
		dat.transform(testCoordinate);

		assertEquals(expectedLatitude, testCoordinate.getLatitude(), EPSILON_IN_RAD);
		assertEquals(expectedLongitude, testCoordinate.getLongitude(), EPSILON_IN_RAD);
		assertEquals(expectedHeight, testCoordinate.getHeight(), EPSILON_IN_METER);
	}
}
