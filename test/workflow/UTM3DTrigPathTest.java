package test.workflow;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import coordinates.Coordinate;
import coordinates.GeographicCoordinateInterface;
import coordinates.GetAppropriateCoordinate;
import coordinates.UTM;
import datumstrategy.GetAppropriateTransformationAlgorithm;
import datumstrategy.TransformationStrategy;
import params.ControlParms;


public class UTM3DTrigPathTest extends UTM {
	
	@Test
	public void testUTMWorkflow() {
		/* 
		 * Test path: for UTM --> Geo --> spatial similarity transformation 
		 * Geo --> UTM
		 */
		
		// get instance of controller
		ControlParms controller = ControlParms.getInstance();
		
		// set values for coordinates and transformation in controller
		controller.setSourceCoordinateX(456451.0); // Karlsruhe (tower of the castle)
		controller.setSourceCoordinateY(5429179.7);
		controller.setSourceCoordinateZ(0.0);
		controller.setKindOfTrafo("3DTrig");
		controller.setDx(0.);
		controller.setDy(0.);
		controller.setDz(0.);
		controller.setScaleFactor(1.);
		
		// get source UTM-coordinates
		Coordinate sourceCoord = GetAppropriateCoordinate.getCoord("utm");
		
		// initialise geographic coordinates with source coordinates
		GeographicCoordinateInterface geographic = GetAppropriateCoordinate.getGeographicCoordinateInterface();
		geographic = sourceCoord.getAsGeographicInterface(controller);
		
		// get a transformation strategy
		TransformationStrategy dat = GetAppropriateTransformationAlgorithm.getStrategy();
		
		// get target UTM-coordinates
		Coordinate targetCoord = GetAppropriateCoordinate.getCoord("utm");
		
		// do transformation
		dat.transform(geographic);
		targetCoord.getAsTargetCoordinate(controller, geographic);
		
		sourceCoord.print();
		targetCoord.print();
		
		// check if source and target coordinates are equal
		assertEquals(((UTM)sourceCoord).getNorth(), ((UTM)targetCoord).getNorth(), 0.001);
		assertEquals(((UTM)sourceCoord).getEast(), ((UTM)targetCoord).getEast(), 0.001);
	}

}
