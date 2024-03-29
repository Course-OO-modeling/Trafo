// package Klassen;

import params.ControlParms;
import coordinates.GetAppropriateCoordinate;
import coordinates.GeographicCoordinateInterface;
import coordinates.Coordinate;
import datumstrategy.TransformationStrategy;
import datumstrategy.GetAppropriateTransformationAlgorithm;

/**
 * @mainpage Coordinate Transformation
 * @note For educational purposes only
 * @copyright Free software delivered without any warranty
 * @version 0.2
 * <img src="Implementation.png" alt="Screenshot">
 */

/**
 * @class Transformation
 * @brief Main Transformatin class
 * @author Norbert Rösch
 * @date February 2021
 * @warning For educational purposes only
 * @copyright No licence restrictions yet
 * @remark last refactored 26.11.2016 by Patrick Hübner <br/>
 *         <ul>
 *         <li>adapted to design changes (TransformationStrategy no accepts
 *         arbitrary Coordinate instances instead of GeographicCoordinates)</li>
 *         </ul>
 * @remark last refactored 11.12.2017 by Eva Majer <br/>
 *         <ul>
 *         <li>adaptions for ControlParms Singleton</li>
 *         <li>Translation of some comments</li></li>
 *         </ul>
 * @remark last refactored 21.01.2018 by Yucheng <br/>
 *         <ul>
 *         <li>merge the class 'Coordinate' into parent class 'Coordinate'</li>
 *         </ul>
 */

public class Transformation {
	public static void main(String[] args) {
		/* First of all a class encompassing the data of the controller is needed */
		ControlParms control = ControlParms.getInstance();
		/*
		 * A geographic coordinate is always needed it represents the interface for the
		 * strategy class
		 */
		GeographicCoordinateInterface geographic = GetAppropriateCoordinate.getGeographicCoordinateInterface();
		/*
		 * The Factory gives back an appropriate coordinate (depending on the users
		 * input)
		 **/
		Coordinate sourceCoord = GetAppropriateCoordinate.getCoord(control.getFromprojection());
		/*
		 * The input coordinate is converted into a geographic coordinate (interface)
		 **/
		geographic = sourceCoord.getAsGeographicInterface(control);
		/* Change of datum is invoked */
		TransformationStrategy kindOfGeodeticTransformation = GetAppropriateTransformationAlgorithm.getStrategy();
		/* Define users' output coordinate system */
		Coordinate targetCoord = GetAppropriateCoordinate.getCoord(control.getToprojection());
		/* Perform change of datum */
		kindOfGeodeticTransformation.transform(geographic);
		/* Transformation back from geographic to plane */
		targetCoord.getAsTargetCoordinate(control, geographic);
		/* Print result */
		targetCoord.print();
	} // end main
} // end class Transformation
