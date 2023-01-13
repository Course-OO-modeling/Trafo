package test.datumstrategy;

import static org.junit.Assert.*;
import org.junit.Test;
import datumstrategy.TransformationStrategy;
import params.ControlParms;
import coordinates.GeographicCoordinateInterface;
import datumstrategy.GetAppropriateTransformationAlgorithm;

/**
 * @class SpatialSimilarityTransformationInfinTest
 * @brief Tests the Spatial Similarity Transformation as infinitesimal implementation against
 * a trigonometric implementation and against the standard Molodenskii transformation
 * @date January 2021
 * @version 0.1
 * 
 */

public class TransformationComparisonTest {

	private final double RHO = 180. / Math.PI;
	private final double EPSILON_IN_METER = 0.1;
	private final double EPSILON_IN_RAD = 0.001 / RHO;

	@Test
	public void testTransform() {
		
		// get instance of controller and set transformation parameters
		ControlParms control = ControlParms.getInstance();
		control.setDx(10);
		control.setDy(10);
		control.setDz(10);
		control.setWx(0.000089932 / RHO); // 0.000089932° = 10 meter at earth's radius
		control.setWy(0.000089932 / RHO);
		control.setWz(0);
		control.setDf(0);
		control.setDa(0);
		control.setMassstab(1.0);
		

		// ----- do infinitesimal transformation
		control.setKindOfTrafo("3DInfin");
		GeographicCoordinateInterface infCoordinate = GeographicCoordinateInterface.getInstance();

		infCoordinate.setLatitude(45. / RHO);
		infCoordinate.setLongitude(15. / RHO);
		infCoordinate.setHeight(0.);

		TransformationStrategy datInf = GetAppropriateTransformationAlgorithm.getStrategy();
		datInf.transform(infCoordinate);
		
		// save results in variables because trig transformation overwrites geocoordinate interface
		double infCoordinateLat = infCoordinate.getLatitude();
		double infCoordinateLon = infCoordinate.getLongitude();
		double infCoordinateHeight = infCoordinate.getHeight();
		
		
		// ----- do trigonometric transformation
		control.setKindOfTrafo("3DTrig");
		GeographicCoordinateInterface trigCoordinate = GeographicCoordinateInterface.getInstance();

		trigCoordinate.setLatitude(45. / RHO);
		trigCoordinate.setLongitude(15. / RHO);
		trigCoordinate.setHeight(0.);

		TransformationStrategy datTrig = GetAppropriateTransformationAlgorithm.getStrategy();
		datTrig.transform(trigCoordinate);
		
		// save results in variables because mol transformation overwrites geocoordinate interface
		double trigCoordinateLat = trigCoordinate.getLatitude();
		double trigCoordinateLon = trigCoordinate.getLongitude();
		double trigCoordinateHeight = trigCoordinate.getHeight();
		
		
		// ----- do Molodenskii transformation
		control.setKindOfTrafo("molStandard");
		GeographicCoordinateInterface molCoordinate = GeographicCoordinateInterface.getInstance();

		molCoordinate.setLatitude(45. / RHO);
		molCoordinate.setLongitude(15. / RHO);
		molCoordinate.setHeight(0.);

		TransformationStrategy dat = GetAppropriateTransformationAlgorithm.getStrategy();
		dat.transform(molCoordinate);
		
		
		// Check if equal
		assertEquals(molCoordinate.getLatitude(), trigCoordinateLat, EPSILON_IN_RAD);
		assertEquals(molCoordinate.getLongitude(), trigCoordinateLon, EPSILON_IN_RAD);
		assertEquals(molCoordinate.getHeight(), trigCoordinateHeight, EPSILON_IN_METER);
		
		assertEquals(infCoordinateLat, trigCoordinateLat, EPSILON_IN_RAD);
		assertEquals(infCoordinateLon, trigCoordinateLon, EPSILON_IN_RAD);
		assertEquals(infCoordinateHeight, trigCoordinateHeight, EPSILON_IN_METER);
	}
}
