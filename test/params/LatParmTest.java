package test.params;

import static org.junit.Assert.*;

import org.junit.Test;

import params.EllipsoidParms;
import params.LatParm;

public class LatParmTest {

	@Test
	public void test() {
		/**
		 * @brief Test based on parameters of GRS 80
		 * @author N. Roesch
		 * @date 18.01.2017
		 */
		EllipsoidParms ell = new EllipsoidParms(0.00673949677548, 6399593.6259);
		LatParm result = new LatParm();
		result.Constant(ell, 49. / (180. / Math.PI));
		assertEquals(6381083.5688, result.getRadg(), 0.001);
		assertEquals(6371848.6282, result.getRadm(), 0.001);
	}

}
