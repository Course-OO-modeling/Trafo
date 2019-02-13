package test.datumstrategy;

import static org.junit.Assert.*;

import org.junit.Test;
import coordinates.GeographicCoordinateInterface;
import datumstrategy.GetAppropriateTransformationAlgorithm;
import datumstrategy.TransformationStrategy;
import params.ControlParms;

public class GetAppropriateTransformationAlgorithmTest {

	@Test
	public void testSpatialSimilarityTransformationInfin() {
		ControlParms control = ControlParms.getInstance();
		control.setKindoftrafo(null);
		GeographicCoordinateInterface geographic = new GeographicCoordinateInterface();
		geographic.setHeight(120.);
		geographic.setLatitude(49.);
		geographic.setLongitude(8.);
		TransformationStrategy dat = GetAppropriateTransformationAlgorithm.getStrategy();
		dat.transform(geographic);
		assertEquals(49., geographic.getLatitude(), 0.000000001);
		assertEquals(8., geographic.getLongitude(),0.000000001);
		assertEquals(120.,geographic.getHeight(), 0.0001);
		
		// fail("Not yet implemented");
	}

}
