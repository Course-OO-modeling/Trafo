package test.datumstrategy;

import static org.junit.Assert.*;

import org.junit.Test;
import coordinates.GeographicCoordinateInterface;
import datumstrategy.GetAppropriateTransformationAlgorithm;
import datumstrategy.*;
/*import datumstrategy.MockStrategy;
import datumstrategy.MolodenskiiTransformationStandard;*/
import params.ControlParms;

public class GetAppropriateTransformationAlgorithmTest {

	@Test
	public void testSpatialSimilarityTransformationInfin() {
		ControlParms control = ControlParms.getInstance();
		control.setKindoftrafo("none");
		GeographicCoordinateInterface geographic = new GeographicCoordinateInterface();
		geographic.setHeight(120.);
		geographic.setLatitude(49.);
		geographic.setLongitude(8.);
		TransformationStrategy dat = GetAppropriateTransformationAlgorithm.getStrategy();
		dat.transform(geographic);
		assertEquals(49., geographic.getLatitude(), 0.000000001);
		assertEquals(8., geographic.getLongitude(), 0.000000001);
		assertEquals(120., geographic.getHeight(), 0.0001);
	}

	@Test
	public void testGetAppropriateTrafoNone() {
		ControlParms control = ControlParms.getInstance();
		control.setKindoftrafo("none");
		TransformationStrategy strategy = GetAppropriateTransformationAlgorithm.getStrategy();
		assertTrue(strategy instanceof MockStrategy);
	}

	@Test
	public void testGetAppropriateTrafoMolodenskiiStandard() {
		ControlParms control = ControlParms.getInstance();
		control.setKindoftrafo("molStandard");
		TransformationStrategy strategy = GetAppropriateTransformationAlgorithm.getStrategy();
		assertTrue(strategy instanceof MolodenskiiTransformationStandard);
	}
	
	@Test
	public void testGetAppropriateTrafoMolodenskiiAbridged() {
		ControlParms control = ControlParms.getInstance();
		control.setKindoftrafo("molAbridged");
		TransformationStrategy strategy = GetAppropriateTransformationAlgorithm.getStrategy();
		assertTrue(strategy instanceof MolodenskiiTransformationAbridged);
	}
	
	@Test
	public void testGetAppropriateTrafo3DInfin() {
		ControlParms control = ControlParms.getInstance();
		control.setKindoftrafo("3DInfin");
		TransformationStrategy strategy = GetAppropriateTransformationAlgorithm.getStrategy();
		assertTrue(strategy instanceof SpatialSimilarityTransformationInfin);
	}
	
	@Test
	public void testGetAppropriateTrafo3DTrig() {
		ControlParms control = ControlParms.getInstance();
		control.setKindoftrafo("3DTrig");
		TransformationStrategy strategy = GetAppropriateTransformationAlgorithm.getStrategy();
		assertTrue(strategy instanceof SpatialSimilarityTransformationTrig);
	}
	
	@Test
	public void testGetAppropriateTrafoXyzwv() {
		ControlParms control = ControlParms.getInstance();
		control.setKindoftrafo("xyzwv");
		TransformationStrategy strategy = GetAppropriateTransformationAlgorithm.getStrategy();
		assertTrue(strategy == null);
	}
}
