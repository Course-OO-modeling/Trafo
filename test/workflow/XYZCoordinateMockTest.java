package test.workflow;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import coordinates.Coordinate;
import coordinates.GeographicCoordinateInterface;
import coordinates.GetAppropriateCoordinate;
import datumstrategy.GetAppropriateTransformationAlgorithm;
import datumstrategy.TransformationStrategy;
import params.ControlParms;
import coordinates.XYZCoordinate;

public class XYZCoordinateMockTest extends XYZCoordinate {
	
	@Test
	public void testXYZCoordinateWorkflow() {
		/* 
		 * Test workflow for xyz --> Geo --> xyz
		 */
		
		// get instance of controller
		ControlParms controller = ControlParms.getInstance();
		
		// set values for coordinates and transformation in controller
		controller.setSourceCoordinateX(4146218.97725); // Karlsruhe Schlossturm
		controller.setSourceCoordinateY(612630.07091);
		controller.setSourceCoordinateZ(4791601.92917);
		controller.setKindOfTrafo("none");
		
		// get source xyz-coordinates
		Coordinate sourceCoord = GetAppropriateCoordinate.getCoord("xyz");
		
		// initialise geographic coordinates with source coordinates
		GeographicCoordinateInterface geographic = GetAppropriateCoordinate.getGeographicCoordinateInterface();
		geographic = sourceCoord.getAsGeographicInterface(controller);
		
		// get a transformation strategy
		TransformationStrategy dat = GetAppropriateTransformationAlgorithm.getStrategy();
		
		// get target xyz-coordinates
		Coordinate targetCoord = GetAppropriateCoordinate.getCoord("xyz");
		
		// do transformation
		dat.transform(geographic);
		targetCoord.getAsTargetCoordinate(controller, geographic);
		
		sourceCoord.print();
		targetCoord.print();
		
		// check if source and target coordinates are equal
		assertEquals(((XYZCoordinate)sourceCoord).getX(), ((XYZCoordinate)targetCoord).getX(), 0.001);
		assertEquals(((XYZCoordinate)sourceCoord).getY(), ((XYZCoordinate)targetCoord).getY(), 0.001);
	}

}
