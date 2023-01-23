package datumstrategy;

import params.ControlParms;
import coordinates.GeographicCoordinateInterface;
import coordinates.XYZCoordinate;

/**
 * @class CardansTransformation
 * @brief Algorithmic realization of Translation and the Cardan's Rotation
 * 
 *        The method takes all needed parameters from the class ControlParms
 *        that extends the class EllipsoidParms. Needed parameter for the
 *        Transformation: 3 translation-, 1 scale- and 3 rotation -parameters.
 *        The start coordinates are taken from the class GeographicCoordinate
 *        and have to be converted into cartesian coordinates to realize the
 *        transformation. The Cardan's Rotation is done with trigonometric
 *        funtion. After the Transformation, the new coordinates are converted
 *        into ellipsoidal coordinates by an iterative process.
 * 
 *        source of cardan's rotation:
 *        http://www.itm.uni-stuttgart.de/courses/madyn/Merkblaetter/M08.pdf (in
 *        Germany) Concept explanation :
 *        http://n.ethz.ch/~roclaudi/download/4.Semster/glossarium%20h%F7ge.pdf
 * @author Yucheng Luo
 * @remark Cardan's rotation matrix added on 01.02.2018 by Yucheng Luo
 * @version 0.1
 */

public class CardansTransformation extends TransformationStrategy {
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
	 * @brief Use Cardan's Rotation to transform the geographic coordinate.
	 * 
	 *        Translation : Cartesian coordinates are used. Rotation : rotate with
	 *        principle of conventional Cardan's Rotation (Wx,Wy,Wz). Algorithm
	 *        explanation : Rotate first about X-axis ,after first step rotate about
	 *        current Y-axis ,at end rotate about current Z-axis.
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
		e1 = Math.cos(control.getWy()) * Math.cos(control.getWz());
		e2 = -Math.cos(control.getWy()) * Math.cos(control.getWz());
		e3 = Math.sin(control.getWy());
		e4 = Math.cos(control.getWx()) * Math.sin(control.getWz())
				+ Math.sin(control.getWx()) * Math.sin(control.getWy()) * Math.cos(control.getWz());
		e5 = Math.cos(control.getWx()) * Math.cos(control.getWz())
				- Math.sin(control.getWx()) * Math.sin(control.getWy()) * Math.sin(control.getWz());
		e6 = -Math.sin(control.getWx()) * Math.cos(control.getWy());
		e7 = Math.sin(control.getWx()) * Math.sin(control.getWz())
				- Math.cos(control.getWx()) * Math.sin(control.getWy()) * Math.cos(control.getWz());
		e8 = Math.sin(control.getWx()) * Math.cos(control.getWz())
				+ Math.cos(control.getWx()) * Math.sin(control.getWy()) * Math.sin(control.getWz());
		e9 = Math.cos(control.getWx()) * Math.cos(control.getWy());

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
