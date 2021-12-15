/**
 * 
 */
package coordinates;

import params.EllipsoidParms;
import params.LatParm;

/**
 * @author Norbert Rösch
 *
 */
public class GaussLuxemburg extends Gauss {
	private double east;
	private double north;
	
	/**
	 * @brief prints a string representation of the Gauss-Luxemburg object into the
	 *        console
	 */
	@Override
	public void print() {
		System.out.println("Gauss-Luxemburg coordinate:");
		System.out.println("Rechts: " + east);
		System.out.println("Hoch  : " + north);
		System.out.println("Height  : " + getHeight());
	} // end print
	
	/**
	 * @brief transforms the coordinates of the current GaussLuxemburg instance into
	 *        geographic coordinates
	 * @param ell - an object of type EllipsoidParams that stores information about
	 *            the reference ellipsoid used for the geographic coordinates
	 * @return geographic - returns an object of type GeographicCoordinate that
	 *         represents the current GaussKrueger instance transformed to
	 *         geographic coordinates
	 */
	@Override
	public GeographicCoordinateInterface getAsGeographicInterface(EllipsoidParms ell) {
		double lao, kennlao;
		double xg, yg, l0;
		final double RHO = 180. / Math.PI;
		GeographicCoordinateInterface geographic = GetAppropriateCoordinate.getGeographicCoordinateInterface();

		xg = north;
		yg = east;
		/* kennlao = (int) ((rechts) / 1e+6);
		lao = 3 * kennlao;
		l0 = lao / RHO;

		yg = east;

		geographic.GeographicLongitudeLatitude(xg, yg, ell, 1., l0);
		geographic.setHeight(this.getHeight());*/

		return geographic;
	} // end gauss2geographic
	
	/**
	 * @brief transforms geographic coordinates into GaussKrueger coordinates and
	 *        stores them in the current GaussKrueger instance
	 * @param ell      - an object of type EllipsoidParams that stores information
	 *                 about the reference ellipsoid used for the geographic
	 *                 coordinates
	 * @param geocoord - an object of type GeographicCoordinate that contains the
	 *                 geographic coordinates to be transformed in GaussKrueger
	 *                 coordinates
	 */
	@Override
	public void getAsTargetCoordinate(EllipsoidParms ell, GeographicCoordinateInterface geocoord) {

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

		this.setEast(this.getOrdinate() + (kennlao + .5) * 1e+6);
		this.setNorth(this.getAbszisse());
		this.setHeight(geocoord.getHeight());

	}

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

}
