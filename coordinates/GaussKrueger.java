package coordinates;


import params.ControlParms;
import params.EllipsoidParms;
import params.LatParm;
import coordinates.Gauss;

/**
 *  @class GaussKrueger
 *  @brief Coordinate point relating to a certain Gauss-Krueger reference meridian
 *  @author unknown
 *  @remark updated header on 2017-11-29 by Markus Mueller
 *  @remark change comment of attribute 'hoch' 2017-12-19 by Markus Hillemann
 *  @remark change attribute name ilao to centralMeridian 2017-12-19 by Markus Hillemann
 *  @version 0.1
 *  @param rechts - Easting as double
 *  @param hoch - Northing as double
 *  @param centralMeridian - Central meridian as integer
 */
public class GaussKrueger extends Gauss {
	
	
    private double rechts;          /**< 'rechts' is the official term by the german survey admistration ('easting') */
    private double hoch; 			/**< 'hoch' is the official term by the german survey admistration ('northing') */
    private int centralMeridian;    /**< central meridian */


/******************************************************************************************************************
* CONSTRUCTORS                                                                                                   *
******************************************************************************************************************/
    /**
     * @brief main constructor to set the coordinates and reference meridian
     */
    public GaussKrueger() {
        ControlParms value = ControlParms.getInstance();
    	setAbszisse(0.);
        setOrdinate(0.);
        rechts = value.getSourceCoordinateX();
        hoch = value.getSourceCoordinateY();
        setHeight(value.getSourceCoordinateZ());
        setScale(1.);
        centralMeridian = 0;
    } // end Konstruktor


/******************************************************************************************************************
* HELPER METHODS                                                                                                  *
******************************************************************************************************************/

    /**
     * @brief prints a string representation of the Gauss-Krueger object into the console
     */
    public void print() {
        System.out.println("Gauss-Krueger coordinate:");
        System.out.println("Rechts: " + rechts);
        System.out.println("Hoch  : " + hoch);
        System.out.println("Height  : " + getHeight());
    } // end print

    /**
     * @brief transforms the coordinates of the current GaussKrueger instance into geographic coordinates
     * @param ell - an object of type EllipsoidParams that stores information about the reference ellipsoid used for the geographic coordinates
     * @return geographic - returns an object of type GeographicCoordinate that represents the current GaussKrueger instance transformed to geographic coordinates
     */
    @Override
    public GeographicCoordinateInterface getAsGeographicInterface(EllipsoidParms ell) {
        double lao, kennlao;
        double xg, yg, l0;
        final double RHO = 180. / Math.PI;
        GeographicCoordinateInterface geographic = CoordinateFactory.getGeographicCoordinateInterface();

        xg = hoch;
        kennlao = (int) ((rechts) / 1e+6);
        lao = 3 * kennlao;
        l0 = lao / RHO;

        yg = rechts - kennlao * 1e+6 - 5e+5;

        geographic.GeographicLongitudeLatitude(xg, yg, ell, 1., l0);
        geographic.setHeight(this.getHeight());

        return geographic;
    } // end gauss2geographic

    // To be implemented and extract the calculation method from coordinates.GeographicCoordinate
    /**
     * @brief transforms geographic coordinates into GaussKrueger coordinates and stores them in the current GaussKrueger instance
     * @param ell - an object of type EllipsoidParams that stores information about the reference ellipsoid used for the geographic coordinates 
     * @param geocoord - an object of type GeographicCoordinate that contains the geographic coordinates to be transformed in GaussKrueger coordinates
     */
    @Override
    public void fromGeographicInterface(EllipsoidParms ell, GeographicCoordinateInterface geocoord) {

        int kennlao = 0;
        double hnull = 0., lo;
        double cdl, dl;
        LatParm latparm = new LatParm();
        final double RHO = 180. / Math.PI;

        kennlao = (int) ell.getGK_refmer() / 3;
        lo = ell.getGK_refmer() / RHO;

        /* meridian arc length */
        hnull = Gauss.meridianLength(geocoord.getLatitude(), ell);

        latparm.Constant(ell, geocoord.getLatitude());

        dl = geocoord.getLongitude() - lo;
        cdl = latparm.getCbr() * dl;
        
        this.CalculateAbszisseOrdinate(cdl, hnull, latparm);

        this.rechts = this.getOrdinate() + (kennlao + .5) * 1e+6;
        this.setHeight(geocoord.getHeight());

    }


/******************************************************************************************************************
* GETTERS AND SETTERS                                                                                                   *
******************************************************************************************************************/

    /**
     * @brief getter method that returns the the northing of the particular GaussKrueger instance
     * @return rechts - returns the northing of the particular GaussKrueger instance as double
     */
    public double getRechts() {
        return rechts;
    }

    /**
     * @brief setter method that accepts the easting of the particular GaussKrueger instance
     * @param rechts - sets a double representing the easting for the particular GaussKrueger instance
     */
    public void setRechts(double rechts) {
        this.rechts = rechts;
    }

    /**
     * @brief getter method for the northing of the current GaussKrueger instance 
     * @return hoch - returns the northing of the current GaussKrueger instance as double 
     */
    public double getHoch() {
        return hoch;
    }

    /**
     * @brief setter method that accepts the northing of the particular GaussKrueger instance
     * @param hoch - sets a double representing the northing for the particular GaussKrueger instance
     */
    public void setHoch(double hoch) {
        this.hoch = hoch;
    }

    /**
     * @brief getter method for the reference meridian of the current GaussKrueger instance 
     * @return centralMeridian - returns the reference meridian  of the current GaussKrueger instance as integer
     */
    public int getIlao() {
        return centralMeridian;
    }

    /**
     * @brief setter method for the number of the reference meridian of the current GaussKrueger instance 
     * @param ilao - sets an integer as the number of the reference meridian of the current GaussKrueger instance
     */
    public void setIlao(int ilao) {
        this.centralMeridian = ilao;
    }

} // end coordinates.GaussKrueger
