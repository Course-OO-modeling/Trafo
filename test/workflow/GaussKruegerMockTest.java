package test.workflow;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import coordinates.Coordinate;
import coordinates.GaussKrueger;
import coordinates.GeographicCoordinateInterface;
import coordinates.GetAppropriateCoordinate;
import datumstrategy.GetAppropriateTransformationAlgorithm;
import datumstrategy.TransformationStrategy;
import params.ControlParms;
import params.EllipsoidParms;


public class GaussKruegerMockTest extends GaussKrueger {
	
	@Test
	public void testGaussKruegerWorkflow() {
		/* 
		 * Test workflow for GK --> Geo --> GK
		 */
		
		// get instance of controller
		ControlParms controller = ControlParms.getInstance();
		
		// set values for coordinates and transformation in controller
		controller.setSourceCoordinateX(3456510.7); // Karlsruhe Schlossturm
		controller.setSourceCoordinateY(5430912.4);
		controller.setSourceCoordinateZ(0.0);
		controller.setKindOfTrafo("none");
		
		// set reference meridian in ellipsoid parameters (needed for getAsTargetCoordinate)
		EllipsoidParms ell = new EllipsoidParms(0.0067394968, 6399593.626);
		ell.setGK_refmer(3 * (int) ((controller.getSourceCoordinateX()) / 1e+6));
		
		// get source GK-coordinates
		Coordinate sourceCoord = GetAppropriateCoordinate.getCoord("gk");
		
		//((GaussKrueger) sourceCoord).set;
		
		// initialise geographic coordinates with source coordinates
		GeographicCoordinateInterface geographic = GetAppropriateCoordinate.getGeographicCoordinateInterface();
		geographic = sourceCoord.getAsGeographicInterface(ell);
		
		
		// get a transformation strategy
		TransformationStrategy dat = GetAppropriateTransformationAlgorithm.getStrategy();
		
		// get target GK-coordinates
		Coordinate targetCoord = GetAppropriateCoordinate.getCoord("gk");
		
		// do transformation
		dat.transform(geographic);
		targetCoord.getAsTargetCoordinate(ell, geographic);
		
		sourceCoord.print();
		targetCoord.print();
		
		// check if source and target coordinates are equal
		assertEquals(((GaussKrueger)sourceCoord).getRechts(), ((GaussKrueger)targetCoord).getRechts(), 0.001);
		assertEquals(((GaussKrueger)sourceCoord).getHoch(), ((GaussKrueger)targetCoord).getHoch(), 0.001);
	}

}
