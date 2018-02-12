package datumstrategy;

import params.ControlParms;
import coordinates.GeographicCoordinateInterface;
import coordinates.XYZCoordinate;
import datumstrategy.TransformationStrategy;

/**
 * @class TrafoCalculation
 * @brief Realization of transformation calculation for transformations CardansTransformation, EulerTransformation, SpatialSimilarityTransformationInfin
 * 
 * The method takes all needed parameters from the class ControlParms that extends the class EllipsoidParms. 
 * Needed parameter for the Transformation: 3 translation-, 1 scale- and 3 rotation -parameters, are taken by the transformation class
 * The start coordinates are taken from the class GeographicCoordinate and have to be converted into cartesian coordinates to realize the transformation.
 * After the Transformation, the new coordinates are converted into ellipsoidal coordinates by an iterative process.
 * 
 * @author Matthias Schnell
 * @version 0.1
 */

public  class TrafoCalculation extends TransformationStrategy {

	@Override
	public void transform(GeographicCoordinateInterface geo) {
		
		// ContolParms: all kinds of parameters
		ControlParms control = ControlParms.getInstance();
		       	    	
		// Source in cartesian coordinates
		XYZCoordinate XYZSource = new XYZCoordinate();
		    	
		// New cartesian Coordinates of Point B
		XYZCoordinate XYZDestination = new XYZCoordinate();
		    	    	
		// Conversion Ellipsoidal --> Cartesian
		XYZSource.fromGeographicInterface(control, geo);
		
		XYZDestination.setX(control.getDx() + control.getMassstab() * (XYZSource.getX()*control.gete1() + XYZSource.getY()*control.gete2() + XYZSource.getZ()*control.gete3()));
    	XYZDestination.setY(control.getDy() + control.getMassstab() * (XYZSource.getX()*control.gete4() + XYZSource.getY()*control.gete5() + XYZSource.getZ()*control.gete6()));
    	XYZDestination.setZ(control.getDz() + control.getMassstab() * (XYZSource.getX()*control.gete7() + XYZSource.getY()*control.gete8() + XYZSource.getZ()*control.gete9()));
		
    	geo = XYZDestination.getAsGeographicInterface(control);
    	 	   	
	} 

	public void transformation(GeographicCoordinateInterface geo, ControlParms control) {
		
		// Source in cartesian coordinates
		XYZCoordinate XYZSource = new XYZCoordinate();
		    	
		// New cartesian Coordinates of Point B
		XYZCoordinate XYZDestination = new XYZCoordinate();
		    	    	
		// Conversion Ellipsoidal --> Cartesian
		XYZSource.fromGeographicInterface(control, geo);
		
		XYZDestination.setX(control.getDx() + control.getMassstab() * (XYZSource.getX()*control.gete1() + XYZSource.getY()*control.gete2() + XYZSource.getZ()*control.gete3()));
    	XYZDestination.setY(control.getDy() + control.getMassstab() * (XYZSource.getX()*control.gete4() + XYZSource.getY()*control.gete5() + XYZSource.getZ()*control.gete6()));
    	XYZDestination.setZ(control.getDz() + control.getMassstab() * (XYZSource.getX()*control.gete7() + XYZSource.getY()*control.gete8() + XYZSource.getZ()*control.gete9()));
		
    	geo = XYZDestination.getAsGeographicInterface(control);
    	
    	
	}

}
