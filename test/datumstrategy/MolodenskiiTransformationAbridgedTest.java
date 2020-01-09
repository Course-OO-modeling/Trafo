package test.datumstrategy;

/**
 * @class MolodenskiiTransformationAbridgedTest
 * @brief Test of the algorithmic realization of the abridged molodensky transformation
 * @author Svea Krikau
 * @remark created 7. December 2019
 * @version 0.1
 * Quelle: http://www.mygeodesy.id.au/documents/Molodensky%20V2.pdf
 */
import static org.junit.Assert.*;

import org.junit.Test;

import coordinates.GeographicCoordinateInterface;
import datumstrategy.GetAppropriateTransformationAlgorithm;
import datumstrategy.TransformationStrategy;
import params.ControlParms;

public class MolodenskiiTransformationAbridgedTest {

	private final double RHO = 180 / Math.PI;
	private final double EPSILON_meter = 0.0001;
	private final double EPSILON_rad = 0.000000001;

	/**
	 * Test of the abridged Molodenskii Transformation. Transformation from the
	 * Australian Geodetic Datum 1966 (AGD66) to the World Geodetic System 1984
	 * (WGS84) Source: http://www.mygeodesy.id.au/documents/Molodensky%20V2.pdf
	 */
	@Test
	public void testMolodenskiiTransformationAbridged() {

		ControlParms control = ControlParms.getInstance();

		control.setKindoftrafo("mol_abridged");
		//control.setKindoftrafo("mol_stand");

		// From Projection: Australian Geodetic Datum 1966 (AGD66)
		control.setId("AGD66");
		control.setDx(-134);
		control.setDy(-48);
		control.setDz(+149);
		control.setDa(-23);
		control.setDf(-0.00000008120449);
		control.setN(6386195.179722);
		control.setM(6359435.481976);
		control.setFirstEccentricity(0.006694541854588);
		control.setSecondEccentricity(Math.sqrt(0.00669454185588) / (Math.sqrt(2 - 0.006694541854588)));
		control.setC(6399473.817479015);

		control.setSemiMajorAxis(6378160.);
		control.setFlattening(1 / 298.25);

		// To Projection: World Geodetic System 1984 (WGS84)
		control.setToid("wgs84");
		control.setToa(6378137);
		control.setTof(1 / 298.257223563);

		// Create the Coordinates
		GeographicCoordinateInterface geographic = new GeographicCoordinateInterface();

		geographic.setLatitude(-37.8 / RHO);
		geographic.setLongitude(144.966666667 / RHO);
		geographic.setHeight(50.);
		geographic.setEllipsoidal(1);

		// Apply the Transformation Strategy
		TransformationStrategy dat = GetAppropriateTransformationAlgorithm.getStrategy();
		dat.transform(geographic);

		// test of the coordinates after the transformation
		assertEquals(-37.79848036 / RHO, geographic.getLatitude(), EPSILON_rad);
		assertEquals(144.9679864 / RHO, geographic.getLongitude(), EPSILON_rad);
		assertEquals(46.378062, geographic.getHeight(), EPSILON_meter);

	}
}