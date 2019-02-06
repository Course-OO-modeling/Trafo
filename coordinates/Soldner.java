package coordinates;

import params.EllipsoidParms;
import params.LatParm;
import coordinates.GeographicCoordinateInterface;
import coordinates.Coordinate;

/**
 * @class Soldner
 * @brief Class Soldner extends class Coordinate
 * @author unknown
 * @remark updated header on 2017-11-29 by Markus Mueller
 * @version 0.1
 * @param l0 - reference longitude as double
 * @param b0 - reference latitude as double
 * @param abszisse - x-value as double
 * @param ordinate - y-value as double
 * @param height - height as double
 * @param ellipsoidal - height above ellipsoid as integer
 */

public final class Soldner extends Coordinate {
    private double l0;         /**< reference longitude */
    private double b0;         /**< reference latitude */
    private double abszisse;   /**< x-value */
    private double ordinate;   /**< y-value */
    private double height;     /**< Height */
    private int ellipsoidal;   /**< Height above ellipsoid: 0 => no height (not defined), 1 => ellispsoidal, 2 => geoid */

/******************************************************************************************************************
* CONSTRUCTORS                                                                                                   *
******************************************************************************************************************/


    public Soldner(double l0, double b0, double abszisse, double ordinate, double height, int ellipsoidal) {
        this.l0 = l0;
        this.b0 = b0;
        this.abszisse = abszisse;
        this.ordinate = ordinate;
        this.height = height;
        this.ellipsoidal = ellipsoidal;
    } // end Constructor

    public Soldner(double l0, double b0) {
        this.l0 = l0;
        this.b0 = b0;
        this.abszisse = 0.;
        this.ordinate = 0.;
        this.height = 0.;
    } // end Constructor


    public Soldner() {
        this.l0 = 0.;
        this.b0 = 0.;
        this.abszisse = 0.;
        this.ordinate = 0.;
        this.height = 0.;
    } // end Constructor


/******************************************************************************************************************
* HELPER METHODS                                                                                                   *
******************************************************************************************************************/

    /**
     * @brief prints Soldner coordinates with reference longitude, -latitude, abscissa, ordinate and height
     */
    public void print() {
    	final double RHO = 180. / Math.PI;
        System.out.println("Soldner coordinate:");
        System.out.println("Reference longitude:" + l0 * RHO);
        System.out.println("Reference latitude:" + b0 * RHO);
        System.out.println("Abcissa:" + abszisse);
        System.out.println("Ordinate:" + ordinate);
        System.out.println("Height:" + height);
    } // end print

    /**
     * @brief getAsGeographic returns a GeographicCoordinate
	 * @param ell - the ellipsoidal parameters as an instance of EllipsoidParms
	 * @return geo - output: returns GeographicCoordinate
     */
    public GeographicCoordinateInterface getAsGeographicInterface(EllipsoidParms ell) {
        double xs, ys, ys2, dl;
        double hilf, hilf2, bf;
        GeographicCoordinateInterface geo = CoordinateFactory.getGeographicCoordinateInterface();
        LatParm bz0 = new LatParm(), bzf = new LatParm();

        bz0.Constant(ell, b0);

        /*calculation of nadir width */
        xs = abszisse / bz0.getRadn();
        hilf = 1. - .5 * bz0.getEtabr() * xs * (3. * bz0.getTbr() - (bz0.getTbr2() - 1.) * xs);
        bf = b0 + bz0.getVbr2() * xs * hilf;

        bzf.Constant(ell, bf);

        /*calculation of width and length (radian) */
        ys = ordinate / bzf.getRadn();
        ys2 = ys * ys;
        hilf = bzf.getVbr2() + 3. * bzf.getTbr2() * (1. - 3. * bzf.getEtabr());
        hilf2 = bzf.getVbr2() * bzf.getTbr() * ys2 * (1. - ys2 * hilf / 12.) / 2.;

        geo.setLatitude(bf - hilf2);

        hilf = 1. - bzf.getTbr2() * ys2 / 3. * (1. - (1. + 3. * bzf.getTbr2()) * ys2 / 5.);
        dl = hilf * ys / Math.cos(bf);

        geo.setLongitude(l0 + dl);
        geo.setHeight(height);
        geo.setPointid(this.getPointid());

        return geo;
    } // end sol2geo

    /**
     * @brief fromGeographic calculates Soldner coordinate from geographic coordinate
	 * @param ell - the ellipsoidal parameters as an instance of EllipsoidParms
	 * @param geocoord - a geographic coordinate as an instance of GeographicCoordinate
     */
    public void getAsTargetCoordinate(EllipsoidParms ell, GeographicCoordinateInterface geocoord) {
        double dl, brm, db, db2, hilf, dxbo, sb, sbdl, sbdl2, cb, cbdl, cbdl2;

        this.l0 = ell.getSoldner_l0();
        this.b0 = ell.getSoldner_b0();
        LatParm bzm = new LatParm(), bza = new LatParm();

        dl = geocoord.getLongitude() - ell.getSoldner_l0();
        brm = (geocoord.getLatitude() + ell.getSoldner_b0()) / 2;
        db = geocoord.getLatitude() - ell.getSoldner_b0();
        db2 = db * db;

        /* Calculation of difference of xB and x0 (in mean width) */
        bzm.Constant(ell, brm);

        hilf = (bzm.getVbr2() - bzm.getTbr2() * (1.0 - 4.0 * bzm.getEtabr())) * db2 / 8.0;
        dxbo = bzm.getRadm() * (1.0 + bzm.getEtabr() * hilf / bzm.getVbr2() / bzm.getVbr2()) * db;

        /* Calculation of abscissa and ordinate (in initial width) */
        bza.Constant(ell, geocoord.getLatitude());

        sb = Math.sin(geocoord.getLatitude());
        sbdl = sb * dl;
        sbdl2 = sbdl * sbdl;
        cb = Math.cos(geocoord.getLatitude());
        cbdl = cb * dl;
        cbdl2 = cbdl * cbdl;

        hilf = (5. * bza.getVbr2() - bza.getTbr2()) * cbdl2 / 12.;
        hilf = .5 * bza.getRadn() * sbdl * cbdl * (1. + hilf);
        this.abszisse = dxbo + hilf;

        hilf = cbdl2 * (8. - bza.getTbr2()) / 20.;
        hilf = sbdl2 * (1. + hilf) / 6.;

        this.ordinate = bza.getRadn() * cbdl * (1.0 - hilf);
        this.height = geocoord.getHeight();
        this.ellipsoidal = geocoord.getEllipsoidal();
        this.setPointid(geocoord.getPointid());
    }

    /******************************************************************************************************************
    * GETTER AND SETTER                                                                                                   *
    ******************************************************************************************************************/
           
        /**
         * @brief getter method that returns the reference latitude of the particular Soldner coordinate
         * @return l0 - returns the reference latitude of the particular Soldner coordinate as double
         */
        public double getL0() {
            return l0;
        }

        /**
         * @brief setter method that accepts the reference latitude of the particular Soldner coordinate
         * @param l0 - sets a double representing the reference latitude of the particular Soldner coordinate
         */
        public void setL0(double l0) {
            this.l0 = l0;
        }

        /**
         * @brief getter method that returns the reference longitude of the particular Soldner coordinate
         * @return b0 - returns the reference longitude of the particular Soldner coordinate as double
         */
        public double getB0() {
            return b0;
        }

        /**
         * @brief setter method that accepts the reference longitude of the particular Soldner coordinate
         * @param b0 - sets a double representing the reference longitude of the particular Soldner coordinate
         */
        public void setB0(double b0) {
            this.b0 = b0;
        }

        /**
         * @brief getter method that returns the abscissa of the particular Soldner coordinate
         * @return abszisse - returns the abscissa of the particular Soldner coordinate as double
         */
        public double getAbszisse() {
            return abszisse;
        }

        /**
         * @brief setter method that accepts the abscissa of the particular Soldner coordinate
         * @param abszisse - sets a double representing the abscissa of the particular Soldner coordinate
         */
        public void setAbszisse(double abszisse) {
            this.abszisse = abszisse;
        }

        /**
         * @brief getter method that returns the ordinate of the particular Soldner coordinate
         * @return ordinate - returns the ordinate of the particular Soldner coordinate as double
         */
        public double getOrdinate() {
            return ordinate;
        }

        /**
         * @brief setter method that accepts the ordinate of the particular Soldner coordinate
         * @param ordinate - sets a double representing the ordinate of the particular Soldner coordinate
         */
        public void setOrdinate(double ordinate) {
            this.ordinate = ordinate;
        }

        /**
         * @brief getter method that returns the height of the particular Soldner coordinate
         * @return height - returns the height of the particular Soldner coordinate as double
         */
        public double getHeight() {
            return height;
        }

        /**
         * @brief setter method that accepts the height of the particular Soldner coordinate
         * @param height - sets a double representing the height of the particular Soldner coordinate
         */
        public void setHeight(double height) {
            this.height = height;
        }

        /**
         * @brief getter method that returns the ellipsoidal of the particular Soldner coordinate
         * @return ellipsoidal - returns the ellipsoidal of the particular Soldner coordinate as integer
         */
        public int getEllipsoidal() {
            return ellipsoidal;
        }

        /**
         * @brief setter method that accepts the height above ellipsoid of the particular Soldner coordinate
         * @param ellipsoidal - sets a integer representing the height above ellipsoid of the particular Soldner coordinate
         */
        public void setEllipsoidal(int ellipsoidal) {
            this.ellipsoidal = ellipsoidal;
        }

} // end coordinates.Soldner
