package test.services;

import static org.junit.Assert.*;

import org.junit.Test;

import coordinates.Gauss;
import services.*;
import params.*;

public class CalculationServiceTest extends CalculationService {

	@Test
	public void testMeridianLength() {
		final double RHO = 180. / Math.PI;
		EllipsoidParms ell = new EllipsoidParms();
		double latitude = 48.445954306/RHO;
		double result = Gauss.meridianLength(latitude, ell);
		assertEquals(5367467.3849, result, 0.001);
	}
}
