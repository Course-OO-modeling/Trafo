package test.coordinates;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CoordinateFactoryTest.class, GaussKruegerTest.class, GaussTest.class, GeographicCoordinateTest.class,
		UTMTest.class, XYZCoordinateTest.class })
public class AllTests {

}
