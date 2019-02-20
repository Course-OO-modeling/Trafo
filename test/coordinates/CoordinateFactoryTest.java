package test.coordinates;

import static org.junit.Assert.*;
import org.junit.Test;
import coordinates.Coordinate;
import coordinates.GetAppropriateCoordinate;
import coordinates.GaussKrueger;
import coordinates.UTM;
import coordinates.Soldner;
import coordinates.GeographicCoordinate;
import coordinates.XYZCoordinate;

/**
 *  @class CoordinateFactoryTest
 *  @brief Coordinate factory for plane coordinates and geographic coordinates
 *  @author Norbert Rösch
 *  @remark January 2019 ensure the factory produces the right objects
 *  @version 0.1
*/

public class CoordinateFactoryTest {

	@Test
	public void testGetCoordGK() {
		final String kindOfCoordinate = "gk";
		Coordinate testCoordinate = GetAppropriateCoordinate.getCoord(kindOfCoordinate);
		assertTrue(testCoordinate instanceof GaussKrueger);
	}
	@Test
	
	public void testGetCoordUTM() {
		final String kindOfCoordinate = "utm";
		Coordinate testCoordinate = GetAppropriateCoordinate.getCoord(kindOfCoordinate);
		assertTrue(testCoordinate instanceof UTM);
	}
	
	@Test
	public void testGetCoordSoldner() {
		final String kindOfCoordinate = "soldner";
		Coordinate testCoordinate = GetAppropriateCoordinate.getCoord(kindOfCoordinate);
		assertTrue(testCoordinate instanceof Soldner);
	}

	@Test
	public void testGetCoordGeographic() {
		final String kindOfCoordinate = "geo";
		Coordinate testCoordinate = GetAppropriateCoordinate.getCoord(kindOfCoordinate);
		assertTrue(testCoordinate instanceof GeographicCoordinate);
	}
	
	@Test
	public void testGetXYZ() {
		final String kindOfCoordinate = "xyz";
		Coordinate testCoordinate = GetAppropriateCoordinate.getCoord(kindOfCoordinate);
		assertTrue(testCoordinate instanceof XYZCoordinate);
	}
}
