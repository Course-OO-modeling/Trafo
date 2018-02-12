package test.services;

import static org.junit.Assert.*;

import org.junit.Test;
import services.*;
import params.*;

public class CalculationServiceTest extends CalculationService implements SharedValues {

	@Test
	public void testMeridianLength() {
		EllipsoidParms ell = new EllipsoidParms();
		double latitude = 48.445954306/rho;
		double result = meridianLength(latitude, ell);
		assertEquals(5367467.3849, result, 0.001);
	}

}
