package coordinates;

import params.EllipsoidParms;
import params.ControlParms;
import params.LatParm;

/**
 * @class XYZCoordinate
 * @brief Class XYZCoordinate
 * @author David Li
 * @remark 2016-12-04 removed unused comments and variables by David Li
 * @remark 2016-12-04 added new method
 *         calculateRotationMatrix(SpatialSimilarityTransformParm) by David Li
 * @remark 2018-01-29 added constructor with ControlParms by Markus Mueller
 * @version 0.1
 * @param x - x-coordinate as double
 * @param y - y-coordinate as double
 * @param z - z-coordinate as double
 */
public final class XYZCoordinate extends Coordinate {

	private double x, y, z;

	/******************************************************************************************************************
	 * CONSTRUCTORS *
	 ******************************************************************************************************************/

	/**
	 * @brief main constructor to set the coordinates
	 */
	public XYZCoordinate() {
		ControlParms value = ControlParms.getInstance();
		x = value.getSourceCoordinateX();
		y = value.getSourceCoordinateY();
		z = value.getSourceCoordinateZ();
	}

	public void init() {
		x = -1.;
		y = -1.;
		z = 0.;
	}

	/**
	 * @brief Constructor for generating a geocentricCoordinate
	 * @remark parameters changed to x-, y- and zAxis (were too inexpressive x1, x2
	 *         and x3) by unknown
	 * @remark 2018-01-25 renamed parameters to xValue, yValue, zValue (were xAxis,
	 *         yAxis, zAxis before) by Markus Müller
	 * @param xValue - x component of XYZ coordinate
	 * @param yValue - y component of XYZ coordinate
	 * @param zValue - z component of XYZ coordinate
	 */
	public XYZCoordinate(double xValue, double yValue, double zValue) {
		x = xValue;
		y = yValue;
		z = zValue;
	}

	public XYZCoordinate(double xValue, double yValue) {
		x = xValue;
		y = yValue;
		z = -1.0;
	}

	/******************************************************************************************************************
	 * HELPER METHODS *
	 ******************************************************************************************************************/

	/**
	 * @brief Function for transforming geocentric coordinates to geographic
	 *        coordinates
	 * @brief Implemented by Michael Loos on 16.06.1998, based on a
	 *        FORTRAN-Subroutine from 12.08.1993 by Hansjoerg Kutterer
	 * @brief
	 *        <p>
	 *        => Needs the function 'Constant' from LatParm.java!
	 *        </p>
	 * @author Michael Loos
	 * @remark renamed the variable bralt to altitude by David Li 2016-12-04
	 * @remark renamed the variable eps to maxLonLatDistance by David Li 2016-12-04
	 * @remark formal changes in summer 2000 by Daniel Mueller
	 * @param ell - an object of type EllipsoidParms
	 * @return geo - returns geographic coordinate consisting of the double values
	 *         latitude, longitude and ellipsoidal height
	 */
	@Override
	public GeographicCoordinateInterface getAsGeographicInterface(EllipsoidParms ell) {
		double ellipsoidLat = 0., longitude = 0., ellispoidHeight = 0.;
		double xydist, latitude, help;
		GeographicCoordinateInterface geo = GetAppropriateCoordinate.getGeographicCoordinateInterface();
		LatParm latparm = new LatParm();
		/* criterion for stopping the iteration */
		double maxLonLatDistance = 1.0e-14;
		double maxIteration = 100;
		/*
		 * * * * Iterative solution *** A-priori-allocation
		 **/
		xydist = Math.sqrt(x * x + y * y);
		latitude = Math.atan2(z, xydist);

		for (int i = 1; i < maxIteration; i++) {
			latparm.Constant(ell, latitude);
			ellipsoidLat = Math.atan2(z + ell.getFirstEccentricity() * latparm.getRadn() * Math.sin(latitude), xydist);

			help = (ellipsoidLat - latitude);
			if (help < 0)
				help = help * (-1);
			if (help < maxLonLatDistance)
				break;

			latitude = ellipsoidLat;
		}
		ellispoidHeight = xydist / Math.cos(ellipsoidLat) - latparm.getRadn();
		longitude = Math.atan2(y, x);

		geo.setLatitude(latitude);
		geo.setLongitude(longitude);
		geo.setHeight(ellispoidHeight);
		return geo;
	}

	/**
	 * @brief Function for transforming geographic coordinates to geocentric
	 *        coordinates
	 * @author Michael Loos, 1998-06-06 as of a FORTRAN-Subroutine from Hansjoerg
	 *         Kutterer, 1993-08-12
	 * @remark formal changes in Summer 2000 by Daniel Mueller
	 * @remark Java-Version in February 2003 by Norbert Roesch
	 * @brief Algorithm as of G. Schmitt, M. Illner and R. Jaeger,
	 *        "Transformationsprobleme", pp. 125-142 in Mitteilungen des DVW
	 *        Landesvereins BaWue, Sonderheft "GPS und Integration von GPS in
	 *        bestehende geodaetische Netze", 1991.
	 * @param ell      - an object of type EllipsoidParms that represents
	 *                 ellipsoidal parameters
	 * @param geocoord - an object of type GeographicCoordinate that represents the
	 *                 geographic coordinate to transform
	 */
	@Override
	public void getAsTargetCoordinate(EllipsoidParms ell, GeographicCoordinateInterface geocoord) {
		LatParm latparm = new LatParm();

		latparm.Constant(ell, geocoord.getLatitude());

		this.x = (latparm.getRadn() + geocoord.getHeight()) * Math.cos(geocoord.getLatitude())
				* Math.cos(geocoord.getLongitude());
		this.y = (latparm.getRadn() + geocoord.getHeight()) * Math.cos(geocoord.getLatitude())
				* Math.sin(geocoord.getLongitude());
		this.z = (latparm.getRadn() / (1.0 + ell.getSecondEccentricity()) + geocoord.getHeight())
				* Math.sin(geocoord.getLatitude());

	}

	public void print() {
		System.out.println("Geo-centric coordinate (XYZ):");
		System.out.println("x: " + x);
		System.out.println("y: " + y);
		System.out.println("z: " + z);
	}

	/******************************************************************************************************************
	 * GETTER AND SETTER *
	 ******************************************************************************************************************/

	/**
	 * @brief getter method that returns the x-coordinate of the particular
	 *        XYZCoordinate
	 * @return x - returns the x-coordinate value of the particular Coordinate point
	 *         as double
	 */
	public double getX() {
		return x;
	}

	/**
	 * @brief setter method that accepts x-coordinate for the particular
	 *        XYZCoordinate
	 * @param x - sets a double representing the x-coordinate value for the
	 *          particular XYZCoordinate
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * @brief getter method that returns the y-coordinate of the particular
	 *        XYZCoordinate
	 * @return y - returns the y-coordinate value of the particular Coordinate point
	 *         as double
	 */
	public double getY() {
		return y;
	}

	/**
	 * @brief setter method that accepts y-coordinate for the particular
	 *        XYZCoordinate
	 * @param y - sets a double representing the y-coordinate value for the
	 *          particular XYZCoordinate
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * @brief getter method that returns the z-coordinate of the particular
	 *        XYZCoordinate
	 * @return z - returns the z-coordinate value of the particular Coordinate point
	 *         as double
	 */
	public double getZ() {
		return z;
	}

	/**
	 * @brief setter method that accepts z-coordinate for the particular
	 *        XYZCoordinate
	 * @param z - sets a double representing the z-coordinate value for the
	 *          particular XYZCoordinate
	 */
	public void setZ(double z) {
		this.z = z;
	}

}
