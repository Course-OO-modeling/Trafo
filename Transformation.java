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
 * @copyright No licence restrictions yet
 * @version 0.1
 * <img src="Implementation.png" alt="Screenshot">
 */

/**
 * @class Transformation
 * @brief Main Transformatin class
 * @author Norbert Roesch
 * @version 0.1
 * @date November 2016
 * @warning For educational purposes only
 * @copyright No licence restrictions yet
 * @remark last refactored 26.11.2016 by Patrick Huebner <br/>
 *  <ul>
 *  	<li>adapted to design changes (TransformationStrategy no accepts arbitrary Coordinate instances instead of GeographicCoordinates)</li>
 *  </ul>
 * @remark last refactored 11.12.2017 by Eva Majer <br/>
 *  <ul>
 *  	<li>adaptions for ControlParms Singleton</li>
 *  	<li>Translation of some comments</li></li>
 *  </ul>
 * @remark last refactored 21.01.2018 by Yucheng <br/>
 *  <ul>
 *  	<li>merge the class 'Coordinate' into parent class 'Coordinate' </li>
 *  </ul>
 */

public class Transformation {
    public static void main(String[] args) {
    	/* First of all a class encompassing the data of the controller is needed */
        ControlParms control = ControlParms.getInstance();
        /* A geographic coordinate is always needed
         * it represents the interface for the strategy class        */
        GeographicCoordinateInterface geographic = GetAppropriateCoordinate.getGeographicCoordinateInterface();
        /* The Factory gives back an appropriate coordinate (depending on the users input) **/
        Coordinate sourceCoord = GetAppropriateCoordinate.getCoord(control.getFromprojection());
        /* This line can be omitted after a test strategy is introduced  * */
        sourceCoord.print();
        /* The input coordinate is converted into a geographic coordinate (interface)     **/ 
        geographic = sourceCoord.getAsGeographicInterface(control);
        /* Only for testing purposes */
        geographic.print();
        /* Change of datum is invoked */
        TransformationStrategy dat = GetAppropriateTransformationAlgorithm.getStrategy();
        /* Define users' output coordinate system */
        Coordinate targetCoord = GetAppropriateCoordinate.getCoord(control.getToprojection());
        /* Perform change of datum */
        dat.transform(geographic);        
        /* Test the result, may be removed later - see above */
        geographic.print();        
        /* Transformation back from geographic to plane */
        targetCoord.getAsTargetCoordinate(control, geographic);
        /* Again a testing statement - may be removed too */
        targetCoord.print();
    } // end main
} // end class Transformation
