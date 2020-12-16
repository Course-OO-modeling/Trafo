package coordinates;

import params.EllipsoidParms;

/**
 * @class Coordinate
 * @brief Abstract class that represents a coordinate. Enables any concrete
 *        Coordinate class be used as input for TransformationStrategy which
 *        relies on geographic coordinates as input/output
 * @author unknown on 2016-11
 * @remark updated header on 2017-11-29 by Markus Müller
 * @remark renamed to Coordinate (was Coordinates) on 2016-11-26 by Patrick
 *         Hübner
 * @remark added abstract methods
 *         {@link #getAsGeographicInterface(EllipsoidParms) getAsGeographic} and
 *         {@link #getAsTargetCoordinate(EllipsoidParms, GeographicCoordinateInterface)
 *         fromGeographic} to enable any concrete Coordinate class be used as
 *         input for TransformationStrategy which relies on geographic
 *         coordinates as input/output on 2016-11-26 by Patrick Hübner
 * @version 0.1
 * @param pointid - string representing the point identification number
 */
public abstract class Coordinate {
	private String pointid;

	/**
	 * @brief abstract method that allows the concrete Coordinate instance to be
	 *        convertable to GeographicCoordinate
	 * @param ellipsoidParms - an object of type EllipsoidParms that contains
	 *                       details about the ellipsoid the GeographicCoordinate
	 *                       should refer to
	 */
	public abstract GeographicCoordinateInterface getAsGeographicInterface(EllipsoidParms ellipsoidParms);

	/**
	 * @brief abstract method that allows the concrete Coordinate instance to be
	 *        convertable from GeographicCoordinate
	 * @param geographicCoordinate - an object of type GeographicCoordinate that
	 *                             represents the geographic coordinate the concrete
	 *                             Coordinate instance should be converted from
	 * @param ellipsoidParms       - an object of type EllipsoidParms that contains
	 *                             details about the ellipsoid the
	 *                             GeographicCoordinate refers to
	 */
	public abstract void getAsTargetCoordinate(EllipsoidParms ellipsoidParms,
			GeographicCoordinateInterface geographicCoordinate);

	/**
	 * @brief abstract methnd that allows the concrete Coordinate to be output on
	 *        screen
	 */
	public abstract void print();

	/******************************************************************************************************************
	 * GETTER AND SETTER *
	 ******************************************************************************************************************/

	/**
	 * @brief getter method that returns the id of the particular Coordinate point
	 * @return pointid - string representing the point identification number
	 */
	public String getPointid() {
		return pointid;
	}

	/**
	 * @brief setter method that accepts id for the particular Coordinate point
	 * @param pointid - string representing the point identification number
	 */
	public void setPointid(String pointid) {
		this.pointid = pointid;
	}
}
