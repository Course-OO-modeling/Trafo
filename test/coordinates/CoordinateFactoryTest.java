package test.coordinates;

import static org.junit.Assert.*;
import org.junit.Test;
import coordinates.Coordinate;
import coordinates.CoordinateFactory;
import coordinates.GaussKrueger;
import coordinates.UTM;
import coordinates.Soldner;
import coordinates.GeographicCoordinate;

public class CoordinateFactoryTest {

	@Test
	public void testGetCoordGK() {
		final String kindOfCoordinate = "gk";
		Coordinate testCoordinate = CoordinateFactory.getCoord(kindOfCoordinate);
		assertTrue(testCoordinate instanceof GaussKrueger);
	}
	@Test
	
	public void testGetCoordUTM() {
		final String kindOfCoordinate = "utm";
		Coordinate testCoordinate = CoordinateFactory.getCoord(kindOfCoordinate);
		assertTrue(testCoordinate instanceof UTM);
	}
	
	@Test
	public void testGetCoordSoldner() {
		final String kindOfCoordinate = "soldner";
		Coordinate testCoordinate = CoordinateFactory.getCoord(kindOfCoordinate);
		assertTrue(testCoordinate instanceof Soldner);
	}

	@Test
	public void testGetCoordGeographic() {
		final String kindOfCoordinate = "geo";
		Coordinate testCoordinate = CoordinateFactory.getCoord(kindOfCoordinate);
		assertTrue(testCoordinate instanceof GeographicCoordinate);
	}
}
