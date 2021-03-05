package datumstrategy;

import coordinates.Coordinate;
import coordinates.GeographicCoordinateInterface;

/**
 *  @file TransformationStrategy.java
 *  
 *  @brief contains abstract class TransformationStrategy
 *  
 *  @author unknown as of 11.2016
 *  
 */

/**
 * @class TransformationStrategy
 * 
 * @brief abstract class that encapsulates transformation algorithms for
 *        different coordinates<br/>
 *        the concrete transformation algorithms that are implemented by the
 *        child classes of TransformationStrategy implement the abstract method
 *        {@link #transform(GeographicCoordinateInterface)
 *        transform(GeographicCoordinateInterface)} that performs the
 *        transformation with geographic coordinates as input/output
 * @remark last refactored 26.11.2016 by Patrick Hübner <br/>
 *         <ul>
 *         <li>changed from interface to abstract class</li>
 *         <li>renamed to TransformationStrategy</li>
 *         <li>renamed method datum to {@link #transform(Coordinate, Coordinate)
 *         transform(Coordinate, Coordinate)} and made it protected</li>
 *         <li>added abstract method
 *         {@link #transform(GeographicCoordinateInterface)
 *         transform(GeographicCoordinateInterface)}</li>
 *         </ul>
 * @remark last refactored 11.12.2017 by Eva Majer <br/>
 * @remark last refactored 21.01.2019 by Norbert R�sch (now only object of
 *         GeographicCoordinateInterface are allowed to communicate with the
 *         different strategies
 *         <ul>
 *         <li>adaptions for ControlParms Singleton</li>
 *         <li>Translation of some comments</li></li>
 *         </ul>
 */
public abstract class TransformationStrategy {

	/**
	 * @brief abstract method for the transformation of a geographic coordinate
	 *
	 * @param geographicCoordinate - an object of type GeographicCoordinateInterface
	 *                             which is used for input/output for the
	 *                             transformation algorithm
	 */
	abstract public void transform(GeographicCoordinateInterface geographicCoordinate);
}
