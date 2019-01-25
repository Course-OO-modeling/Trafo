package coordinates;

// package Classes;

/**
 *  @class CoordinateFactory
 *  @brief Coordinate factory for plane coordinates and geographic coordinates
 *  @author Norbert Rösch
 *  @remark updated header on 2017-11-29 by Markus Mueller
 *  @remark last refactored 11.12.2017 by Eva Majer <br/>
 *  <ul>
 *  	<li>adaptions for ControlParms Singleton</li>
 *  	<li>Translation of some comments</li></li>
 *  </ul>
 *  @remark 2018-01-06 add comments by Yunhao Huang
 *  @remark 2018-01-12 added new GeographicCoordinate as comment by Johanna Stoetzer
 *  @remark 2018-01-25 added XYZ-Coordinate to getCoord by Markus Mueller
 *  @version 0.1
 *  @param Coordinate - the plane coordinates GaussKrueger, UTM and Soldner
 *  @param GeographicCoordinateInterface - the geographic coordinate interface
*/

public class CoordinateFactory {
	/**
     * @brief getter method that returns an appropriate coordinate depending on the users input
     * @param object - string representing the type of the coordinate
     */
    public static Coordinate getCoord(String object) {
        if (object.equals("gk"))
            return new GaussKrueger();
        else if (object.equals("utm"))
            return new UTM();
        else if (object.equals("soldner"))
            return new Soldner();
        else if (object.equals("geo"))
            return new GeographicCoordinate();
        else if (object.equals("xyz"))
            return new XYZCoordinate();
        else
            return null;
    } // end getCoord


    public static GeographicCoordinateInterface getGeographicCoordinateInterface() {
      return GeographicCoordinateInterface.getInstance();
    }

} // end coordinates.CoordinateFactory
