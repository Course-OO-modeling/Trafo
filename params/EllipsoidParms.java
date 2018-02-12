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
    private String id;	/**< ID */
    private double c;   /**< Radius of curvature */
    private double es2; /**< Second eccentricity */
    private double a;   /**< Semi-major axis */
    private double b;   /**< Semi-minor axis */
    private double f;   /**< Flattening */
    private double e2;  /**< First eccentricity */
    // if change of datum
    private String toid;	/**< ID (if change of datum) */
    private double toc;   /**< Radius of curvature (if change of datum) */
    private double toes2; /**< Second eccentricity (if change of datum) */
    private double toa;   /**< Semi-major axis (if change of datum) */
    private double tob;   /**< Semi-minor axis (if change of datum) */
    private double tof;   /**< Flattening (if change of datum) */
    private double toe2;  /**< First eccentricity (if change of datum) */
    // only if necessary
    private double N;     /**< Radius N (only if necessary) */
    private double M;     /**< Radius M (only if necessary) */

    private double Soldner_l0;  /**< Parameters for converting geographic coordinates -> coordinates.Soldner */
    private double Soldner_b0;  /**< Parameters for converting geographic coordinates -> coordinates.Soldner */

    private double GK_refmer;   /**< Parameters for converting geographic coordinates -> coordinates.GaussKrueger */

/******************************************************************************************************************
* KONSTRUKTOREN                                                                                                   *
******************************************************************************************************************/
    
    /**
     * @brief Constructor for the class params.EllipsoidParms
     */
    public EllipsoidParms() {
        id = "bessel";
        es2 = 0.0067192188;
        c = 6398786.849;
        a = c / Math.sqrt(1. + es2);
        b = c / (1. + es2);
        f = 1 - (b / a);
        e2 = es2 / (1. + es2);
    } // end of Constructor

    /**
     * @brief General constructor for the class params.EllipsoidParms
     * 
     * Transfer of two shape parameters
     */
    public EllipsoidParms(double es2, double c) { // 
        id = "unknown";
        this.es2 = es2;
        this.c = c;
        a = c / Math.sqrt(1 + es2);
        b = c / (1 + es2);
        f = 1 - (b / a);
        e2 = es2 / (1 + es2);
    } // end of Constructor

/******************************************************************************************************************
* HILFSMETHODEN                                                                                                   *
******************************************************************************************************************/
    public void calculatelongitudeParms(GeographicCoordinateInterface geo) {
        double cbr, etabr, vbr, vbr2;
        cbr = Math.cos(geo.getLatitude());
        etabr = es2 * cbr * cbr;
        vbr2 = 1 + etabr;
        vbr = Math.sqrt(vbr2);
        N = c / vbr;
        M = N / vbr2;
    }

    public void print() {
        System.out.println("Konstanten");
        System.out.println("Ellipsoid: " + id);
        System.out.println("es2: " + es2);
        System.out.println("e2 : " + e2);
        System.out.println("c  : " + c);
        System.out.println("a  : " + a);
        System.out.println("b  : " + b);
        System.out.println("f  : " + f);
    } // end print


/******************************************************************************************************************
* GETTER UND SETTER                                                                                                   *
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

    public double getEs2() {
        return es2;
    }

    public void setEs2(double es2) {
        this.es2 = es2;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getF() {
        return f;
    }

    public void setF(double f) {
        this.f = f;
    }

    public double getE2() {
        return e2;
    }

    public void setE2(double e2) {
        this.e2 = e2;
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
