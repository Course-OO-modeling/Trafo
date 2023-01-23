package datumstrategy;

import params.ControlParms;
import coordinates.GeographicCoordinateInterface;
import coordinates.XYZCoordinate;

/**
 * @class EulersTransformation
 * @brief Algorithmic realization of the Euler's transformation
 * 
 *        The method takes all needed parameters from the class ControlParms
 *        that extends the class EllipsoidParms. Needed parameter for the
 *        EulersTransformation: 3 translation-, 1 scale- and 3 rotation
 *        -parameters. The start coordinates are taken from the class
 *        GeographicCoordinate and have to be converted into cartesian
 *        coordinates to realize the transformation. The Translation is done
 *        with cartesian coordinates and the rotation is implemented with
 *        trigonometric function. After the Transformation, the new cartesian
 *        coordinates are converted into ellipsoidal coordinates by an iterative
 *        process.
 * 
 *        source of EulersTransformation:
 *        https://en.wikipedia.org/wiki/Euler_angles
 * @author Yucheng Luo
 * @remark Euler's angle added on 01.02.2018 by Yucheng Luo
 * @version 0.1
 */

public class EulersTransformation extends TransformationStrategy {
	private double e1;
	private double e2;
	private double e3;
	private double e4;
	private double e5;
	private double e6;
	private double e7;
	private double e8;
	private double e9;

	/**
	 * @brief Use Euler angles to transform the geographic coordinate.
	 * 
	 *        Translation : Cartesian coordinates are used. Rotation : rotate with
	 *        principle of conventional Euler's Standard-x-convention (Rz1,Rx2,Rz3).
	 *        Algorithm explanation : Rotate first about Z-axis ,after first step
	 *        rotate about current X-axis, at end rotate about current Z-axis. Link
	 *        to the Algorithm : https://de.wikipedia.org/wiki/Eulersche_Winkel
	 * 
	 * @author Yucheng Luo
	 */
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

		// 9 elements in rotation matrix e0-e9
		e1 = Math.cos(control.getRz1()) * Math.cos(control.getRz3())
				- Math.sin(control.getRz1()) * Math.cos(control.getRx2()) * Math.sin(control.getRz3());
		e2 = -Math.cos(control.getRz1()) * Math.sin(control.getRz3())
				- Math.sin(control.getRz1()) * Math.cos(control.getRx2()) * Math.cos(control.getRz3());
		e3 = Math.sin(control.getRz1()) * Math.sin(control.getRx2());
		e4 = Math.sin(control.getRz1()) * Math.cos(control.getRz3())
				+ Math.cos(control.getRz1()) * Math.cos(control.getRx2()) * Math.sin(control.getRz3());
		e5 = -Math.sin(control.getRz1()) * Math.sin(control.getRz3())
				+ Math.cos(control.getRz1()) * Math.cos(control.getRx2()) * Math.cos(control.getRz3());
		e6 = -Math.cos(control.getRz1()) * Math.sin(control.getRx2());
		e7 = Math.sin(control.getRx2()) * Math.sin(control.getRz3());
		e8 = Math.sin(control.getRx2()) * Math.cos(control.getRz3());
		e9 = Math.cos(control.getRx2());

		XYZDestination.setX(control.getDx()
				+ control.getScaleFactor() * (XYZSource.getX() * e1 + XYZSource.getY() * e2 + XYZSource.getZ() * e3));
		XYZDestination.setY(control.getDy()
				+ control.getScaleFactor() * (XYZSource.getX() * e4 + XYZSource.getY() * e5 + XYZSource.getZ() * e6));
		XYZDestination.setZ(control.getDz()
				+ control.getScaleFactor() * (XYZSource.getX() * e7 + XYZSource.getY() * e8 + XYZSource.getZ() * e9));

		// Conversion Cartesian --> Ellipsoidal
		geo = XYZDestination.getAsGeographicInterface(control);

		System.out.println("3D infinitesimal");
	}
}
