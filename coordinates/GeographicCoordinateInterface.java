package coordinates;

import params.EllipsoidParms;
import params.LatParm;
import params.MolodenskyParm;

/**
 * @class GeographicCoordinateInterface
 * 
 * @brief concrete implementation of abstract class Coordinate, represents a
 *        geographic coordinate (As interface for strategy)<br/>
 * @author unknown as of 2016-11
 * @remark renamed to GeographicCoordinateInterface. Class is now separated into
 *         GeographicCoordinate (only input coordinate) and
 *         GeographicCoordinateInterface (Interface for strategy) on 2018-01-12
 *         by Johanna Stoetzer
 * @remark updated header on 2017-11-29 by Markus Müller
 * @remark renamed to GeographicCoordinate (was GeographicCoordinates) on
 *         2016-11-26 by Patrick Hübner
 * @version 0.1
 * @param point       number - Point identification number of the
 *                    GeographicCoordinate as long
 * @param longitude   - Longitude of the GeographicCoordinate as double
 * @param latitude    - Latitude of the GeographicCoordinate as double
 * @param height      - Height of the GeographicCoordinate as double
 * @param ellipsoidal - Height above ellipsoid of the GeographicCoordinate as
 *                    integer
 */
public final class GeographicCoordinateInterface extends Coordinate {

	private long point_number;
	private double longitude;
	private double latitude;
	private double height;
	private int ellipsoidal;
	/**
	 * < Height above ellipsoid: 0 => no height (not defined), 1 => ellipsoidal, 2
	 * => geoid
	 */

	private static GeographicCoordinateInterface myInstance = null;

	/******************************************************************************************************************
	 * CONSTRUCTORS *
	 ******************************************************************************************************************/

	/**
	 * @remark public/visible due to testing
	 * @param longitude
	 * @param latitude
	 */

	public GeographicCoordinateInterface() {
		longitude = -1.;
		latitude = -1.;
		height = -10000.;
	} // end Constructor

	public GeographicCoordinateInterface(double longitude, double latitude) {
		this.longitude = longitude;
		this.latitude = latitude;
		height = 0.;
	} // end Constructor

	private GeographicCoordinateInterface(double longitude, double latitude, double height) {
		this.longitude = longitude;
		this.latitude = latitude;
		this.height = height;
	} // end Constructor

	/******************************************************************************************************************
	 * DESTRUCTORS *
	 ******************************************************************************************************************/

	/**
	 * @brief factory method that returns the singular instance of the singleton
	 *        class initialized with default constructor
	 */
	public static GeographicCoordinateInterface getInstance() {
		if (myInstance == null) {
			myInstance = new GeographicCoordinateInterface();
		} else {
			myInstance.resetState();

			myInstance.longitude = -1.;
			myInstance.latitude = -1.;
			myInstance.height = -10000.;
		}

		return myInstance;
	}

	/**
	 * @brief sets all parameters of the instance to default value 0, needed because
	 *        only one instance of the class can exist (singleton class)
	 */
	private void resetState() // destructor
	{
		// Zustand loeschen
		point_number = 0;
		longitude = 0;
		latitude = 0;
		height = 0;
		ellipsoidal = 0;

	} // end Destructor

	/**
	 * @brief factory method that returns the singular instance of the singleton
	 *        class initialized with constructor that accepts values for longitude
	 *        and latitude
	 * @param longitude - double value of the geographical longitude the
	 *                  GeographicCoordinate instance should be initialized with
	 * @param latitude  - double value of the geographical latitude the
	 *                  GeographicCoordinate instance should be initialized with
	 */
	public static GeographicCoordinateInterface getInstance(double longitude, double latitude) {
		if (myInstance == null) {
			myInstance = new GeographicCoordinateInterface(longitude, latitude);
		} else {
			myInstance.resetState();

			myInstance.longitude = longitude;
			myInstance.latitude = latitude;
			myInstance.height = 0.;
		}

		return myInstance;
	}

	/**
	 * @brief factory method that returns the singular instance of the singleton
	 *        class initialized with constructor that accepts values for longitude,
	 *        latitude and height
	 * @param longitude - double value of the geographical longitude the
	 *                  GeographicCoordinate instance should be initialized with
	 * @param latitude  - double value of the geographical latitude the
	 *                  GeographicCoordinate instance should be initialized with
	 * @param height    - double value of the height the GeographicCoordinate
	 *                  instance should be initialized with
	 */
	public static GeographicCoordinateInterface getInstance(double longitude, double latitude, double height) {
		if (myInstance == null) {
			myInstance = new GeographicCoordinateInterface(longitude, latitude, height);
		} else {
			myInstance.resetState();

			myInstance.longitude = longitude;
			myInstance.latitude = latitude;
			myInstance.height = height;
		}

		return myInstance;
	}

	/******************************************************************************************************************
	 * HELPER METHODS *
	 ******************************************************************************************************************/

	/**
	 * @brief prints a string representation of the particular GeographicCoordinate
	 *        instance into the console
	 */
	public void print() {
		final double RHO = 180. / Math.PI;
		System.out.println("Geographic coordinates:");
		System.out.println("Point ID: " + getPointid());
		System.out.println("Point Number: " + point_number);
		System.out.println("Longitude: " + longitude * RHO);
		System.out.println("Latitude: " + latitude * RHO);
		System.out.println("Height: " + height);
	} // end print

	/**
	 * @brief allows the particular GeographicCoordinate instance to be converted to
	 *        GeographicCoordinate. simply returns this reference
	 * @param ellipsoidParms - an object of type EllipsoidParms that contains
	 *                       details about the ellipsoid the GeographicCoordinate
	 *                       should refer to
	 */
	@Override
	public GeographicCoordinateInterface getAsGeographicInterface(EllipsoidParms ellipsoidParms) {
		return this;
	}

	/**
	 * @brief allows the the particular GeographicCoordinate instance to be
	 *        converted from GeographicCoordinate. simply uses copy method
	 * @param geographicCoordinate - an object of type GeographicCoordinate that
	 *                             represents the geographic coordinate the concrete
	 *                             Coordinate instance should be converted from
	 * @param ellipsoidParms       - an object of type EllipsoidParms that contains
	 *                             details about the ellipsoid the
	 *                             GeographicCoordinate refers to
	 */
	@Override
	public void getAsTargetCoordinate(EllipsoidParms ellipsoidParms,
			GeographicCoordinateInterface geographicCoordinate) {
		copy(geographicCoordinate);
	}

	/**
	 * @brief calculates a Molodensky transformation
	 * @param ell            - an object of type EllipsoidParms that contains
	 *                       details about the ellipsoid the transformation refers
	 *                       to
	 * @param trafoparameter - an object of type MolodenskyParm that contains the
	 *                       parameters of the Molodenskij transformation
	 * @return geo_p2 - returns a transformed geographic coordinate
	 */
	public GeographicCoordinateInterface molodenskytrafo(EllipsoidParms ell, MolodenskyParm trafoparameter) {

		double dl, db, dh;
		GeographicCoordinateInterface geo_p2 = GetAppropriateCoordinate.getGeographicCoordinateInterface();
		LatParm latparm = new LatParm();

		latparm.Constant(ell, latitude);

		/*
		 * * * * This standard formula is used in this program, because all parameters
		 * are available from NIMA * * *
		 */

		db = (-trafoparameter.getTransx() * Math.sin(latitude) * Math.cos(longitude)
				- trafoparameter.getTransy() * Math.sin(latitude) * Math.sin(longitude)
				+ trafoparameter.getTransz() * Math.cos(latitude)
				+ trafoparameter.getDa()
						* (latparm.getRadn() * ell.getFirstEccentricity() * Math.sin(latitude) * Math.cos(latitude))
						/ ell.getSemiMajorAxis()
				+ trafoparameter.getDf()
						* (latparm.getRadm() * (ell.getSemiMajorAxis() / ell.getSemiMinorAxis())
								+ latparm.getRadn() * (ell.getSemiMinorAxis() / ell.getSemiMajorAxis()))
						* Math.sin(latitude) * Math.cos(latitude))
				/ (latparm.getRadm() + height);

		dl = (-trafoparameter.getTransx() * Math.sin(longitude) + trafoparameter.getTransy() * Math.cos(longitude))
				/ ((latparm.getRadn() + height) * Math.cos(latitude));

		dh = trafoparameter.getTransx() * Math.cos(latitude) * Math.cos(longitude)
				+ trafoparameter.getTransy() * Math.cos(latitude) * Math.sin(longitude)
				+ trafoparameter.getTransz() * Math.sin(latitude)
				- trafoparameter.getDa() * (ell.getSemiMajorAxis() / latparm.getRadn())
				+ trafoparameter.getDf() * (ell.getSemiMinorAxis() / ell.getSemiMajorAxis()) * latparm.getRadn()
						* Math.pow(Math.sin(latitude), 2);

		geo_p2.latitude = latitude + db;
		geo_p2.longitude = longitude + dl;
		geo_p2.height = height + dh;

		geo_p2.setPointid(getPointid());

		return geo_p2;
	}

	/**
	 * @brief calculates pulse width (Fusspunktbreite) for a given range
	 * @param s   - double value for the range
	 * @param ell - an object of type EllipsoidParms that contains details about the
	 *            ellipsoid the pulse width calculation refers to
	 * @return br - returns the pulse width (Fusspunktbreite) as double value
	 */
	public double fupubre(double s, EllipsoidParms ell) {
		int nu;
		double sigma, n, nhilf, n2, n3, n4, b0, b2, b4, b6, sisi, cosi, cosi2;
		double alf[] = new double[11], alfq, br = 0.;

		nhilf = Math.sqrt(1. + ell.getSecondEccentricity());
		n = (nhilf - 1.) / (nhilf + 1.);
		n2 = n * n;
		n3 = n2 * n;
		n4 = n2 * n2;
		b0 = (3. + (-5.25 + (7.75 - 10.265625 * n) * n) * n) * n;
		b2 = (10.5 + (-151. / 3. + 5045. / 32. * n) * n) * n2;
		b4 = (151. / 3. - 3291. / 8. * n) * n3;
		b6 = 1097. / 4 * n4;

		/* * * * Initialization of ALF-Field * * * */

		for (nu = 0; nu <= 10; nu++)
			alf[nu] = 0.;

		/* * * * Calculation of alpha_q (alpha quer) * * * */

		alf[0] = 1.;

		for (nu = 0; nu <= 9; nu++)
			alf[nu + 1] = -(1. - 0.25 / Math.pow((nu + 1), 2)) * ell.getSecondEccentricity() * alf[nu];

		alfq = 0.;

		for (nu = 0; nu <= 10; nu++)
			alfq = alfq + alf[nu];

		alfq = (ell.getC()) * alfq;

		sigma = s / alfq;
		sisi = Math.sin(sigma);
		cosi = Math.cos(sigma);
		cosi2 = cosi * cosi;
		br = sigma + sisi * cosi * (b0 + cosi2 * (b2 + cosi2 * (b4 + cosi2 * b6)));
		return br;
	} // end fupubre

	/**
	 * @brief sets longitude and latitude according to given parameters
	 * @param xg    - double value representing geographic decimal coordinate?
	 * @param yg    - double value representing geographic decimal coordinate?
	 * @param ell   - an object of type EllipsoidParms that contains details about
	 *              the ellipsoid
	 * @param scale - scale as double value
	 * @param l0    - ??
	 */
	public void GeographicLongitudeLatitude(double xg, double yg, EllipsoidParms ell, double scale, double l0) {
		double bf, ygrad, ygrad2, vbf2, tbf2;
		LatParm latparm = new LatParm();
		/* Calculation of pulse width (Fusspunktbreite) */
		bf = fupubre(xg, ell);

		latparm.Constant(ell, bf);
		ygrad = yg / (latparm.getRadn() * scale);
		ygrad2 = ygrad * ygrad;
		vbf2 = latparm.getVbr() * latparm.getVbr();
		tbf2 = latparm.getTbr() * latparm.getTbr();

		/* latitude and longitude (JEK) */
		latitude = bf
				- .5 * latparm.getTbr() * ygrad2 * vbf2 * (1. - ygrad2 / 12. * (5. + 3. * tbf2 + latparm.getEtabr()
						- 9. * tbf2 * latparm.getEtabr() + ygrad2 / 30. * (61. + 90. * tbf2 + 45. * tbf2 * tbf2)));

		longitude = l0 + ygrad / Math.cos(bf)
				* (1. - ygrad2 / 6. * (vbf2 + 2. * tbf2 - (5. + tbf2 * (28. + 24. * tbf2)) / 20. * ygrad2));

	}

	/**
	 * @brief works like a copy constructor, initializes particular instance with
	 *        the values of the instance passed as parameter
	 * @param geo - the GeographicCoordinate to initialize the particular instance
	 *            with
	 */
	public void copy(GeographicCoordinateInterface geo) { // Copy attributes
		this.setPointid(geo.getPointid());
		this.point_number = geo.point_number;
		this.longitude = geo.longitude;
		this.latitude = geo.latitude;
		this.height = geo.height;
		this.ellipsoidal = geo.ellipsoidal;
	}

	/******************************************************************************************************************
	 * GETTER AND SETTER *
	 ******************************************************************************************************************/

	/**
	 * @brief getter method that returns point number of the particular Geographic
	 *        Coordinate point
	 * @return point_number - returns the point number of the particular Geographic
	 *         Coordinate point as long value
	 */
	public long getPoint_number() {
		return point_number;
	}

	/**
	 * @brief setter method that accepts point number for the particular Geographic
	 *        Coordinate point
	 * @param point_number - a long value representing the point number for the
	 *                     particular Geographic Coordinate point
	 */
	public void setPoint_number(long point_number) {
		this.point_number = point_number;
	}

	/**
	 * @brief getter method that returns the geographical longitude
	 * @return longitude - returns a double value representing the geographical
	 *         longitude
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * @brief setter method that accepts the geographical longitude
	 * @param longitude - double value representing the geographical longitude
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/**
	 * @brief getter method that returns the geographical latitude
	 * @return latitude - returns a double value representing the geographical
	 *         latitude
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * @brief setter method that accepts the geographical latitude
	 * @param latitude - double value representing the geographical latitude
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * @brief getter method that returns the height
	 * @return height - returns a double value representing the height
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * @brief setter method accepts the height
	 * @param height - double value representing the height
	 */
	public void setHeight(double height) {
		this.height = height;
	}

	/**
	 * @brief getter method that returns the height above ellipsoid
	 * @return ellipsoidal - returns a double value representing the height above
	 *         ellipsoid
	 */
	public int getEllipsoidal() {
		return ellipsoidal;
	}

	/**
	 * @brief setter method accepts the height above ellipsoid
	 * @param ellipsoidal - double value representing the height above ellipsoid
	 */
	public void setEllipsoidal(int ellipsoidal) {
		this.ellipsoidal = ellipsoidal;
	}
}
