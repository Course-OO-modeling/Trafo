package params;

import coordinates.GeographicCoordinateInterface;

// package Klassen;

/**
 * @class EllipsoidParms
 * @brief Ellipsoid parameters
 * @author unknown
 * @remark add header and some comments on 2018-01-06 by Yunhao Huang
 */
public class EllipsoidParms {
	private String id;
	/** < ID */
	private double c;
	/** < Radius of curvature */
	private double secondEccentricity;
	private double semiMajorAxis;
	private double semiMinorAxis;
	private double flattening;
	private double firstEccentricity;
	// in the case of change of datum
	private String toid;
	/** < ID (if change of datum) */
	private double toc;
	/** < Radius of curvature (if change of datum) */
	private double toes2;
	/** < Second eccentricity (if change of datum) */
	private double toa;
	/** < Semi-major axis (if change of datum) */
	private double tob;
	/** < Semi-minor axis (if change of datum) */
	private double tof;
	/** < Flattening (if change of datum) */
	private double toe2;
	/** < First eccentricity (if change of datum) */
	// only if necessary
	private double N;
	/** < Radius N (only if necessary) */
	private double M;
	/** < Radius M (only if necessary) */

	private double Soldner_l0;
	/** < Parameters for converting geographic coordinates -> coordinates.Soldner */
	private double Soldner_b0;
	/** < Parameters for converting geographic coordinates -> coordinates.Soldner */

	private double GK_refmer;

	/**
	 * < Parameters for converting geographic coordinates ->
	 * coordinates.GaussKrueger
	 */

	/******************************************************************************************************************
	 * KONSTRUKTOREN *
	 ******************************************************************************************************************/

	/**
	 * @brief Constructor for the class params.EllipsoidParms
	 */
	public EllipsoidParms() {
		id = "bessel";
		secondEccentricity = 0.0067192188;
		c = 6398786.849;
		semiMajorAxis = c / Math.sqrt(1. + secondEccentricity);
		semiMinorAxis = c / (1. + secondEccentricity);
		flattening = 1 - (semiMinorAxis / semiMajorAxis);
		firstEccentricity = secondEccentricity / (1. + secondEccentricity);
	} // end of Constructor

	/**
	 * @brief General constructor for the class params.EllipsoidParms
	 * 
	 *        Transfer of two shape parameters
	 */
	public EllipsoidParms(double es2, double c) { //
		id = "unknown";
		this.secondEccentricity = es2;
		this.c = c;
		semiMajorAxis = c / Math.sqrt(1 + es2);
		semiMinorAxis = c / (1 + es2);
		flattening = 1 - (semiMinorAxis / semiMajorAxis);
		firstEccentricity = es2 / (1 + es2);
	} // end of Constructor

	/******************************************************************************************************************
	 * HILFSMETHODEN *
	 ******************************************************************************************************************/
	public void calculatelongitudeParms(GeographicCoordinateInterface geo) {
		double cbr, etabr, vbr, vbr2;
		cbr = Math.cos(geo.getLatitude());
		etabr = secondEccentricity * cbr * cbr;
		vbr2 = 1 + etabr;
		vbr = Math.sqrt(vbr2);
		N = c / vbr;
		M = N / vbr2;
	}

	public void print() {
		System.out.println("Konstanten");
		System.out.println("Ellipsoid: " + id);
		System.out.println("secondEccentricity: " + secondEccentricity);
		System.out.println("firstEccentricity : " + firstEccentricity);
		System.out.println("c  : " + c);
		System.out.println("semiMajorAxis  : " + semiMajorAxis);
		System.out.println("semiMinorAxis  : " + semiMinorAxis);
		System.out.println("flattening  : " + flattening);
	} // end print

	/******************************************************************************************************************
	 * GETTER UND SETTER *
	 ******************************************************************************************************************/

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getC() {
		return c;
	}

	public void setC(double c) {
		this.c = c;
	}

	public double getSecondEccentricity() {
		return secondEccentricity;
	}

	public void setSecondEccentricity(double es2) {
		this.secondEccentricity = es2;
	}

	public double getSemiMajorAxis() {
		return semiMajorAxis;
	}

	public void setSemiMajorAxis(double a) {
		this.semiMajorAxis = a;
	}

	public double getSemiMinorAxis() {
		return semiMinorAxis;
	}

	public void setSemiMinorAxis(double b) {
		this.semiMinorAxis = b;
	}

	public double getFlattening() {
		return flattening;
	}

	public void setFlattening(double f) {
		this.flattening = f;
	}

	public double getFirstEccentricity() {
		return firstEccentricity;
	}

	public void setFirstEccentricity(double e2) {
		this.firstEccentricity = e2;
	}

	public String getToid() {
		return toid;
	}

	public void setToid(String toid) {
		this.toid = toid;
	}

	public double getToc() {
		return toc;
	}

	public void setToc(double toc) {
		this.toc = toc;
	}

	public double getToes2() {
		return toes2;
	}

	public void setToes2(double toes2) {
		this.toes2 = toes2;
	}

	public double getToa() {
		return toa;
	}

	public void setToa(double toa) {
		this.toa = toa;
	}

	public double getTob() {
		return tob;
	}

	public void setTob(double tob) {
		this.tob = tob;
	}

	public double getTof() {
		return tof;
	}

	public void setTof(double tof) {
		this.tof = tof;
	}

	public double getToe2() {
		return toe2;
	}

	public void setToe2(double toe2) {
		this.toe2 = toe2;
	}

	public double getN() {
		return N;
	}

	public void setN(double n) {
		N = n;
	}

	public double getM() {
		return M;
	}

	public void setM(double m) {
		M = m;
	}

	public double getSoldner_l0() {
		return Soldner_l0;
	}

	public void setSoldner_l0(double soldner_l0) {
		Soldner_l0 = soldner_l0;
	}

	public double getSoldner_b0() {
		return Soldner_b0;
	}

	public void setSoldner_b0(double soldner_b0) {
		Soldner_b0 = soldner_b0;
	}

	public double getGK_refmer() {
		return GK_refmer;
	}

	public void setGK_refmer(double GK_refmer) {
		this.GK_refmer = GK_refmer;
	}

} // end class params.EllipsoidParms
