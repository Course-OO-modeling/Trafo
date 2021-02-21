package test.workflow;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import coordinates.Coordinate;
import coordinates.GeographicCoordinateInterface;
import coordinates.GetAppropriateCoordinate;
import datumstrategy.GetAppropriateTransformationAlgorithm;
import datumstrategy.TransformationStrategy;
import params.ControlParms;
import coordinates.Soldner;


public class SoldnerMockTest extends Soldner {
	
	@Test
	public void testSoldnerWorkflow() {
		/* 
		 * Test workflow for UTM --> Geo --> UTM
		 */
		
		// get instance of controller
		ControlParms controller = ControlParms.getInstance();
		
		// set values for coordinates and transformation in controller
		controller.setSourceCoordinateX(-341625.6); // Karlsruhe Schlossturm
		controller.setSourceCoordinateY(-355439.0);
		controller.setSourceCoordinateZ(0.0);
		controller.setDx(0);
		controller.setDy(0);
		controller.setDz(0);
		controller.setDa(0);
		controller.setDf(0);
		
		// get source Soldner-coordinates
		Coordinate sourceCoord = GetAppropriateCoordinate.getCoord("soldner");
		
		// initialise geographic coordinates with source coordinates
		GeographicCoordinateInterface geographic = GetAppropriateCoordinate.getGeographicCoordinateInterface();
		geographic = sourceCoord.getAsGeographicInterface(controller);
		
		// get a transformation strategy
		TransformationStrategy dat = GetAppropriateTransformationAlgorithm.getStrategy();
		
		// get target Soldner-coordinates
		Coordinate targetCoord = GetAppropriateCoordinate.getCoord("soldner");
				
		// do transformation
		dat.transform(geographic);
		targetCoord.getAsTargetCoordinate(controller, geographic);
				
		sourceCoord.print();
		targetCoord.print();
				
		// check if source and target coordinates are equal
		assertEquals(((Soldner)sourceCoord).getAbszisse(), ((Soldner)targetCoord).getAbszisse(), 0.001);
		assertEquals(((Soldner)sourceCoord).getOrdinate(), ((Soldner)targetCoord).getOrdinate(), 0.001);
	}

}
