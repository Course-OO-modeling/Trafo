package test.datumstrategy;

import static org.junit.Assert.*;
import org.junit.Test;
import datumstrategy.TransformationStrategy;
import params.ControlParms;
import coordinates.GeographicCoordinateInterface;
import datumstrategy.GetAppropriateTransformationAlgorithm;

/**
 * @class MolodenskiiTransformationStandardTest
 * @brief Tests the Molodenskii Transformation Standard as a Datum Strategy.
 *        Input values and expected values were calculated by hand.
 * @author Steven Landgraf
 * @date December 2019
 * @version 0.1
 * 
 */

public class MolodenskiiTransformationStandardTest {

	private final double RHO = 180 / Math.PI;
	private final double EPSILON_IN_METER = 0.001;
	private final double EPSILON_IN_RAD = 0.00000001;

	@Test
	public void testTransform() {

		ControlParms control = ControlParms.getInstance();
		control.setKindoftrafo("molStandard");
		control.setDx(100);
		control.setDy(100);
		control.setDz(100);
		control.setDa(-23);
		control.setDf(-0.00000008);

		final double expectedLatitude = (48.99981817 / RHO);
		final double expectedLongitude = (8.00116328 / RHO);
		final double expectedHeight = 172.235;

		GeographicCoordinateInterface testCoordinate = GeographicCoordinateInterface.getInstance();

		testCoordinate.setLatitude(49 / RHO);
		testCoordinate.setLongitude(8 / RHO);
		testCoordinate.setHeight(0.);

		TransformationStrategy dat = GetAppropriateTransformationAlgorithm.getStrategy();
		dat.transform(testCoordinate);

		assertEquals(expectedLatitude, testCoordinate.getLatitude(), EPSILON_IN_RAD);
		assertEquals(expectedLongitude, testCoordinate.getLongitude(), EPSILON_IN_RAD);
		assertEquals(expectedHeight, testCoordinate.getHeight(), EPSILON_IN_METER);

	}

}
