package datumstrategy;

import coordinates.Coordinate;
import coordinates.GeographicCoordinateInterface;
import params.ControlParms;

/**
 *  @file TransformationStrategy.java
 *  
 *  @brief contains abstract class TransformationStrategy
 *  
 *  @author unknown as of 11.2016
 *  
 */

/**
 *  @class TransformationStrategy
 *  
 *  @brief abstract class that encapsulates transformation algorithms for different coordinates<br/>
 *  the concrete transformation algorithms that are implemented by the child classes of TransformationStrategy implement the abstract method {@link #transform(GeographicCoordinateInterface) transform(GeographicCoordinateInterface)} that performs the transformation with geographic coordinates as input/output   
 *  @remark last refactored 26.11.2016 by Patrick Huebner <br/>
 *  <ul>
 *  	<li>changed from interface to abstract class</li>
 *  	<li>renamed to TransformationStrategy</li>
 *  	<li>renamed method datum to {@link #transform(Coordinate, Coordinate) transform(Coordinate, Coordinate)} and made it protected</li>
 *  	<li>added abstract method {@link #transform(GeographicCoordinateInterface) transform(GeographicCoordinateInterface)}</li>
 *  </ul>
 *  @remark last refactored 11.12.2017 by Eva Majer <br/>
 *  <ul>
 *  	<li>adaptions for ControlParms Singleton</li>
 *  	<li>Translation of some comments</li></li>
 *  </ul>
 */
public abstract class TransformationStrategy {
	/**
     * @brief transformation method that transforms the Coordinate fromCoordinate according to algorithm of concrete implementation of TransformationStrategy and specifications in controlParms and saves result to Coordinate toCoordinate<br/>
     * uses abstract method {@link #transform(GeographicCoordinateInterface) transform(GeographicCoordinateInterface)} to perform the transformation
     * 
     * @param fromCoordinate - an object of abstract type Coordinate that represents the coordinate to be transformed
     * 
     * @param toCoordinate - an object of abstract type Coordinate that represents the coordinate in which the results should be stored
     *
     */
	public void transform(Coordinate fromCoordinate, Coordinate toCoordinate) {
		ControlParms controlParms = ControlParms.getInstance();
		GeographicCoordinateInterface geographicCoordinate = fromCoordinate.getAsGeographicInterface(controlParms);
		transform(geographicCoordinate);
		toCoordinate.fromGeographicInterface(controlParms, geographicCoordinate);
	}
	
	/**
     * @brief abstract method for the transformation of a geographic coordinate
     *
     * @param geographicCoordinate - an object of type GeographicCoordinateInterface which is used for input/output for the transformation algorithm
     */
	abstract protected void transform(GeographicCoordinateInterface geographicCoordinate);
}
