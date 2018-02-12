package coordinates;

// package Klassen;


import params.EllipsoidParms;
import params.LatParm;

/**
 * @class Gauss
 * @brief Class Gauss extends class Coordinate
 * @author unknown
 * @remark updated header on 2017-11-29 by Markus Mueller
 * @version 0.1
 * @param abszisse - x-value as double
 * @param ordinate - y-value as double
 * @param scale - scale of central median as double
 * @param height - height as double
 * @param ellipsoidal - height above ellipsoid as integer
 */ 

public abstract class Gauss extends Coordinate {
    private double abszisse;	/**< x-value */
    private double ordinate;	/**< y-value */
    private double scale;		/**< scale of central meridian */
    private double height;		/**< Height */
    private int ellipsoidal;	/**< Height above ellipsoid:, 0 => no height, 1 => ellipsoidal, 2 => geoid */

/******************************************************************************************************************
* HELPER METHODS                                                                                                  *
******************************************************************************************************************/

    /**
     * @brief CalculateAbszisseOrdinate calculates the abscissa and ordinate
     * @author unknown
     * @remark updated header 29.11.2017 by Markus Mueller
     * @param cdl - a double
     * @param hnull - a double
     * @param latparm -  a LatParm object
     */ 
    protected void CalculateAbszisseOrdinate(double cdl, double hnull, LatParm latparm) {

        double cdl2 = cdl * cdl;

        abszisse = scale * (hnull + 0.5 * latparm.getRadn() * latparm.getTbr() * cdl2 * (1. +
                cdl2 / 12. * (5. + 9. * latparm.getEtabr() + 4. * latparm.getEtabr() * latparm.getEtabr() - latparm.getTbr2() + cdl2 / 30. *
                (61. - 58. * latparm.getTbr2() + latparm.getTbr2() * latparm.getTbr2()))));

        ordinate = scale * latparm.getRadn() * cdl * (1. + cdl2 / 6. *
                (latparm.getVbr2() - latparm.getTbr2() + cdl2 / 20. * (5. - 18. * latparm.getTbr2() +
                latparm.getTbr2() * latparm.getTbr2() + 14. * latparm.getEtabr() - 58. * latparm.getEtabr() *
                latparm.getTbr2())));

    } // end CalculateAbszisseOrdinate
/* 


*/

    public abstract void print();

    public abstract GeographicCoordinateInterface getAsGeographicInterface(EllipsoidParms ell);


/******************************************************************************************************************
* GETTER AND SETTER                                                                                                   *
******************************************************************************************************************/

    /**
     * @brief getter method that returns the the abscissa of the particular Gauss coordinate
     * @return abszisse - returns the abscissa of the particular Gauss coordinate as double
     */
    public double getAbszisse() {
        return abszisse;
    }

    /**
     * @brief setter method that accepts the abscissa of the particular Gauss coordinate
     * @param abszisse - sets a double representing the abscissa for the particular Gauss coordinate
     */
    public void setAbszisse(double abszisse) {
        this.abszisse = abszisse;
    }

    /**
     * @brief getter method that returns the the ordinate of the particular Gauss coordinate
     * @return ordinate - returns the ordinate of the particular Gauss coordinate as double
     */
    public double getOrdinate() {
        return ordinate;
    }

    /**
     * @brief setter method that accepts the ordinate of the particular Gauss coordinate
     * @param ordinate - sets a double representing the ordinate for the particular Gauss coordinate
     */
    public void setOrdinate(double ordinate) {
        this.ordinate = ordinate;
    }

    /**
     * @brief getter method that returns the the scale of the particular Gauss coordinate
     * @return scale - returns the ordinate of the particular Gauss coordinate as double as double
     */
    public double getScale() {
        return scale;
    }

    /**
     * @brief setter method that accepts the scale of the particular Gauss coordinate
     * @param scale - sets a double representing the scale for the particular Gauss coordinate
     */
    public void setScale(double scale) {
        this.scale = scale;
    }

    /**
     * @brief getter method that returns the the height of the particular Gauss coordinate
     * @return height - returns the height of the particular Gauss coordinate as double
     */
    public double getHeight() {
        return height;
    }

    /**
     * @brief setter method that accepts the height of the particular Gauss coordinate
     * @param height - sets a double representing the height for the particular Gauss coordinate
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * @brief getter method that returns the the ellipsoidal of the particular Gauss coordinate
     * @return ellipsoidal - returns the ellipsoidal of the particular Gauss coordinate as double
     */
    public int getEllipsoidal() {
        return ellipsoidal;
    }

    /**
     * @brief setter method that accepts the ellipsoidal of the particular Gauss coordinate
     * @param ellipsoidal - sets a double representing the ellipsoidal for the particular Gauss coordinate
     */
    public void setEllipsoidal(int ellipsoidal) {
        this.ellipsoidal = ellipsoidal;
    }



} // end GaussIsotherm

