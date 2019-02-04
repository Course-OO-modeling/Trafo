package params;

// package Klassen;


import params.EllipsoidParms;

/**
 * @class LatParm
 * @author unknown
 * @brief Latitude Parameters
 * @remark add header on 2018-01-06 by Yunhao Huang
 */
public final class LatParm {

    private double radm;  /**< radius of meridian curvature M */
    private double radn;  /**< radius of transverse curvature N */
    private double radg;  /**< coordinates. Gaussian curvature radius G */
    private double tbr;   /**< Tangent of latitude */
    private double vbr;   /**< auxiliary value V for the latitude BR */
    private double etabr; /**< auxiliary value eta2 for the latitude BR */
    private double cbr, vbr2, tbr2;

/******************************************************************************************************************
* KONSTRUKTOREN                                                                                                   *
******************************************************************************************************************/

    public LatParm() {
        radm = radn = radg = tbr = 0.;
        vbr = etabr = cbr = vbr2 = tbr2 = 0.;
    } // end params.LatParm

/******************************************************************************************************************
* HILFSMETHODEN                                                                                                   *
******************************************************************************************************************/


    public void Constant(EllipsoidParms ell, double latitude) {
        cbr = Math.cos(latitude);
        etabr = ell.getSecondEccentricity() * cbr * cbr;
        vbr2 = 1 + etabr;
        vbr = Math.sqrt(vbr2);
        radn = ell.getC() / vbr;
        radm = radn / vbr2;
        radg = ell.getC() / vbr2;
        tbr = Math.tan(latitude);
        tbr2 = tbr * tbr;
    } // end Constant


/******************************************************************************************************************
* GETTER UND SETTER                                                                                                   *
******************************************************************************************************************/

    public double getRadm() {
        return radm;
    }

    public void setRadm(double radm) {
        this.radm = radm;
    }

    public double getRadn() {
        return radn;
    }

    public void setRadn(double radn) {
        this.radn = radn;
    }

    public double getRadg() {
        return radg;
    }

    public void setRadg(double radg) {
        this.radg = radg;
    }

    public double getTbr() {
        return tbr;
    }

    public void setTbr(double tbr) {
        this.tbr = tbr;
    }

    public double getVbr() {
        return vbr;
    }

    public void setVbr(double vbr) {
        this.vbr = vbr;
    }

    public double getEtabr() {
        return etabr;
    }

    public void setEtabr(double etabr) {
        this.etabr = etabr;
    }

    public double getCbr() {
        return cbr;
    }

    public void setCbr(double cbr) {
        this.cbr = cbr;
    }

    public double getVbr2() {
        return vbr2;
    }

    public void setVbr2(double vbr2) {
        this.vbr2 = vbr2;
    }

    public double getTbr2() {
        return tbr2;
    }

    public void setTbr2(double tbr2) {
        this.tbr2 = tbr2;
    }

} // end params.LatParm
