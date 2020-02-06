package datumstrategy;

import params.ControlParms;
import coordinates.GeographicCoordinateInterface;
import coordinates.XYZCoordinate;
import datumstrategy.TransformationStrategy;
import Jama.Matrix;

/**
 * @class SpatialSimilarityTransformationInfin
 * @brief Algorithmic realization of the 3D similarity transformation also known
 *        as 7-parameter transformation.
 * 
 *        The method takes all needed parameters from the class ControlParms
 *        that extends the class EllipsoidParms. Needed parameter for the
 *        7-Param-Transformation: 3 translation-, 1 scale- and 3 rotation
 *        -parameters. The start coordinates are taken from the class
 *        GeographicCoordinate and have to be converted into cartesian
 *        coordinates to realize the transformation. The 7-Param-Transformation
 *        is done with cartesian coordinates. After the Transformation, the new
 *        cartesian coordinates are converted into ellipsoidal coordinates by an
 *        iterative process.
 * 
 *        source of conversions: Geodaetische Flaechenkoordinaten, Uebung2,
 *        kart2geo.pdf, 20.11.2014 source of 7-Param-Transformation:
 *        https://de.wikipedia.org/wiki/Helmert-Transformation
 * @author Andreas Eppler
 * @remark adaptions for ControlParms Singleton on 11.12.2017 by Eva Majer
 * @remark use the methods of class XYZCoordinate to convert between ellipsoidal
 *         and cartesian coordinates on 19.12.17 by Markus Hillemann
 * @remark changed the class name (CamelCase) on 20.12.17 by Markus Hillemann
 * @remark implemented the calculation of the 7-Param-Transformation with
 *         matrices instead of singular values on 11.12.2019 by Steven Landgraf
 * @version 0.1
 */

public class SpatialSimilarityTransformationInfin extends TransformationStrategy {

	@Override
	public void transform(GeographicCoordinateInterface geo) {

		// ContolParms: all kinds of parameters
		ControlParms control = ControlParms.getInstance();

		// Source in cartesian coordinates
		XYZCoordinate XYZSource = new XYZCoordinate();

		// New cartesian Coordinates of Point B
		XYZCoordinate XYZDestination = new XYZCoordinate();

		// Conversion Ellipsoidal --> Cartesian
		XYZSource.getAsTargetCoordinate(control, geo);

		// 7-Param-Transformation
		double[][] anglesOfRotation = { { 1., control.getWz(), -control.getWy() },
				{ -control.getWz(), 1., control.getWx() }, { control.getWy(), -control.getWx(), 1. } };
		double[] translationVector = { control.getDx(), control.getDy(), control.getDz() };
		double[] sourceCoord = { XYZSource.getX(), XYZSource.getY(), XYZSource.getZ() };

		Matrix destination = new Matrix(3, 1);
		Matrix translation = new Matrix(translationVector, 3);
		Matrix rotation = new Matrix(anglesOfRotation);
		Matrix source = new Matrix(sourceCoord, 3);

		destination = translation.plus(rotation.times(control.getMassstab()).times(source));

		XYZDestination.setX(destination.get(0, 0));
		XYZDestination.setY(destination.get(1, 0));
		XYZDestination.setZ(destination.get(2, 0));

		// Conversion Cartesian --> Ellipsoidal
		geo = XYZDestination.getAsGeographicInterface(control);

	}
}
