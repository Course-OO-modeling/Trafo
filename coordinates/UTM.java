package coordinates;

import params.EllipsoidParms;
import params.LatParm;
import coordinates.Gauss;
import coordinates.GeographicCoordinateInterface;

/**
 * @class UTM
 * @brief Class UTM extends class Gauss
 * @author unknown
 * @remark updated header on 2017-11-29 by Markus Mueller
 * @version 0.1
 * @param east - easting as double
 * @param north - Northing as double
 * @param zone - UTM-Zone as integer
 * @param northhem - hemisphere (0: North or 1:South) as boolean
 */
public class UTM extends Gauss {
    private double east;		/**< Easting */
    private double north;		/**< Northing */
    private int zone;           /**< coordinates.UTM-Zone */
    private boolean northhem;	/**< Hemisphere: 0 => North, 1 => South */

    public UTM() {
        setAbszisse(0.);
        setOrdinate(0.);
        setHeight(0.);
        east = 651416.090;
        north = 5408463.070;
        setScale(.9996);
        zone = 32;
        northhem = true;
    } // end Constructor

    public void init() {
        setAbszisse(0.);
        setOrdinate(0.);
        setHeight(0.);
        east = 0.;
        north = 0.;
        setScale(0.);
        zone = 0;
        northhem = true;
    } // end init

    /**
     * @brief prints UTM coordinates 
     */
    public void print() {
        System.out.println("UTM coordinate:");
        System.out.println("Northing : " + north);
        System.out.println("Easting  : " + east);
        System.out.println("Height   : " + getHeight());
        System.out.println("Zone     : " + zone);
        System.out.println("Hemisphere (north == true): " + northhem);
    } // end print

    /**
     * @brief getAsGeographic returns a GeographicCoordinate object
     * @param ell - ellipsoidal parameters
     * @return geo - output: GeographicCoordinate
     */
    @Override
    public GeographicCoordinateInterface getAsGeographicInterface(EllipsoidParms ell) {
        double xg, yg, l0;
        final double RHO = 180. / Math.PI;
        GeographicCoordinateInterface geo = CoordinateFactory.getGeographicCoordinateInterface();

        xg = north;
        yg = east;
/* * * * for southern hemisphere* * * */
        if (northhem == false)
            xg = xg - 1.e+7;
        xg /= getScale();
        l0 = ((double) (6 * (zone - 30) - 3)) / RHO;
        yg -= 5.e+5;

        geo.GeographicLongitudeLatitude(xg, yg, ell, getScale(), l0);

        geo.setHeight(this.getHeight());
        return geo;
    } // end utm2geo

    /**
     * @brief fromGeographic calculates UTM coordinate from geographic coordinate
	 * @param ell - the ellipsoidal parameters as an instance of EllipsoidParms
	 * @param geocoord - a geographic coordinate as an instance of GeographicCoordinate
     */
    @Override
    public void fromGeographicInterface(EllipsoidParms ell, GeographicCoordinateInterface geocoord) {
        double hnull, l0, cdl, dl;
        final double RHO = 180. / Math.PI;

        LatParm bz = new LatParm();

        zone = (int) (((geocoord.getLongitude() * RHO + 3.0) / 6.0 + 30.0) + 0.5);
        l0 = (double) (6 * (zone - 30) - 3) / RHO;

        /* meridian arc length */
        hnull = Gauss.meridianLength(geocoord.getLatitude(), ell);

        bz.Constant(ell, geocoord.getLatitude());

        dl = geocoord.getLongitude() - l0;
        cdl = bz.getCbr() * dl;

        CalculateAbszisseOrdinate(cdl, hnull, bz);

        north = getAbszisse();
        if (geocoord.getLatitude() < 0.)
            north += 1.e+7;

        east = getOrdinate() + 5.e+5;

        setHeight(geocoord.getHeight());
    } // end

	public double getEast() {
		return east;
	}

	public void setEast(double east) {
		this.east = east;
	}

	public double getNorth() {
		return north;
	}

	public void setNorth(double north) {
		this.north = north;
	}

	public int getZone() {
		return zone;
	}

	public void setZone(int zone) {
		this.zone = zone;
	}

	public boolean isNorthhem() {
		return northhem;
	}

	public void setNorthhem(boolean northhem) {
		this.northhem = northhem;
	}

} // end coordinates.UTM
