package coordinates;

import params.EllipsoidParms;


/**
 *  @class GeographicCoordinate
 *  
 *  @brief concrete implementation of abstract class Coordinate, represents a geographic coordinate (as input coordinate)<br/>
 *	@author unknown as of 2016-11
 *	@remark Split former class GeographicCoordinate into GeographicCoordinate (only input coordinate) and GeographicCoordinateInterface (Interface for strategy) on 2018-01-12 by Johanna Stoetzer
 *  @remark updated header on 2017-11-29 by Markus Mueller
 *  @remark renamed to GeographicCoordinate (was GeographicCoordinates) on 2016-11-26 by Patrick Huebner
 *	@version 0.1
 *	@param point number - Point identification number of the GeographicCoordinate as long
 *	@param longitude - Longitude of the GeographicCoordinate as double
 *	@param latitude - Latitude of the GeographicCoordinate as double
 *	@param height - Height of the GeographicCoordinate as double
 *	@param ellipsoidal - Height above ellipsoid of the GeographicCoordinate as integer
 */
public final class GeographicCoordinate extends Coordinate {

    private long point_number;
    private double longitude;
    private double latitude;
    private double height;
    private int ellipsoidal;    /**< Height above ellipsoid: 0 => no height (not defined), 1 => ellipsoidal, 2 => geoid */


    private static GeographicCoordinate myInstance = null;
    
    
    /******************************************************************************************************************
    * CONSTRUCTORS                                                                                                    *
    ******************************************************************************************************************/

    

    public GeographicCoordinate() 
    {
        longitude = -1.;
        latitude = -1.;
        height = -10000.;
    } // end Constructor

    public GeographicCoordinate(double longitude, double latitude) 
    {
        this.longitude = longitude;
        this.latitude = latitude;
        height = 0.;
    } // end Constructor

    public GeographicCoordinate(double longitude, double latitude, double height) 
    {
        this.longitude = longitude;
        this.latitude = latitude;
        this.height = height;
    } // end Constructor


    
    /******************************************************************************************************************
    * DESTRUCTORS                                                                                                   *
    ******************************************************************************************************************/

    /**
     * @brief sets all parameters of the instance to default value 0, needed because only one instance of the class can exist (singleton class)
     */
    private void resetState() // destructor
    {
        // Delete state
        point_number = 0;
        longitude = 0;
        latitude = 0;
        height = 0;
        ellipsoidal = 0;

    } // end Destructor

    /**
     * @brief factory method that returns the singular instance of the singleton class initialized with constructor that accepts values for longitude and latitude
     * @param longitude - double value of the geographical longitude the GeographicCoordinate instance should be initialized with
     * @param latitude - double value of the geographical latitude the GeographicCoordinate instance should be initialized with
     */
    public static GeographicCoordinate getInstance(double longitude, double latitude) {
        if (myInstance == null) {
            myInstance = new GeographicCoordinate(longitude, latitude);
        } else {
            myInstance.resetState();

            myInstance.longitude = longitude;
            myInstance.latitude = latitude;
            myInstance.height = 0.;
        }

        return myInstance;
    }

    /**
     * @brief factory method that returns the singular instance of the singleton class initialized with constructor that accepts values for longitude, latitude and height
     * @param longitude - double value of the geographical longitude the GeographicCoordinate instance should be initialized with
     * @param latitude - double value of the geographical latitude the GeographicCoordinate instance should be initialized with
     * @param height - double value of the height the GeographicCoordinate instance should be initialized with
     */
    public static GeographicCoordinate getInstance(double longitude, double latitude, double height) {
        if (myInstance == null) {
            myInstance = new GeographicCoordinate(longitude, latitude, height);
        } else {
            myInstance.resetState();

            myInstance.longitude = longitude;
            myInstance.latitude = latitude;
            myInstance.height = height;
        }

        return myInstance;
    }


    /******************************************************************************************************************
    * HELPER METHODS                                                                                                  *
    ******************************************************************************************************************/

    /**
     * @brief prints a string representation of the particular GeographicCoordinate instance into the console
     */
    public void print() {
    	final double RHO = 180. / Math.PI;
        System.out.println("Geographic coordinate:");
        System.out.println("Point ID: " + getPointid());
        System.out.println("Point Number: " + point_number);
        System.out.println("Longitude: " + longitude * RHO);
        System.out.println("Latitude: " + latitude * RHO);
        System.out.println("Height: " + height);
    } // end print

    /**
     * @brief allows the particular GeographicCoordinate instance to be converted to GeographicCoordinate. Assigns all parameters of GeographicCoordinateInterface the values of the parameters of this object. 
     * @param ellipsoidParms - an object of type EllipsoidParms that contains details about the ellipsoid the GeographicCoordinate should refer to
     */
    @Override
	public GeographicCoordinateInterface getAsGeographicInterface(EllipsoidParms ellipsoidParms) {
    	GeographicCoordinateInterface geographic = GetAppropriateCoordinate.getGeographicCoordinateInterface();
    	
    	geographic.setPoint_number(this.point_number);
    	geographic.setEllipsoidal(this.ellipsoidal);
    	geographic.setHeight(this.height);
    	geographic.setLatitude(this.latitude);
    	geographic.setLongitude(this.longitude);
    	
    	return geographic;
	}

    /**
     * @brief allows the the particular GeographicCoordinate instance to be converted from GeographicCoordinate. Assigns all values of the parameters of this object the  parameters of this object. 
     * @param geoCoordInt - an object of type GeographicCoordinate that represents the geographic coordinate the concrete Coordinate instance should be converted from
     * @param ellipsoidParms - an object of type EllipsoidParms that contains details about the ellipsoid the GeographicCoordinate refers to
     */
	@Override
	public void getAsTargetCoordinate(EllipsoidParms ellipsoidParms, GeographicCoordinateInterface geoCoordInt) {
		
	    this.setPoint_number(geoCoordInt.getPoint_number());
	    this.setEllipsoidal(geoCoordInt.getEllipsoidal());
	    this.setHeight(geoCoordInt.getHeight());
	    this.setLatitude(geoCoordInt.getLatitude());
	    this.setLongitude(geoCoordInt.getLongitude());
	}


    /******************************************************************************************************************
    * GETTER AND SETTER                                                                                                   *
    ******************************************************************************************************************/

    /**
     * @brief getter method that returns point number of the particular Geographic Coordinate point
     * @return point_number - returns the point number of the particular Geographic Coordinate point as long value
     */
    public long getPoint_number() {
        return point_number;
    }

    /**
     * @brief setter method that accepts point number for the particular Geographic Coordinate point
     * @param point_number - a long value representing the point number for the particular Geographic Coordinate point
     */
    public void setPoint_number(long point_number) {
        this.point_number = point_number;
    }

    /**
     * @brief getter method that returns the geographical longitude 
     * @return longitude - returns a double value representing the geographical longitude
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
     * @return latitude - returns a double value representing the geographical latitude
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
     * @return ellipsoidal - returns a double value representing the height above ellipsoid
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
