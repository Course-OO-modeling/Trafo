package params;

// package Klassen;

/**
 * @class SpatialSimilarityTransformParm
 * @author unknown
 * @brief Spatial similarity transform parameters
 * @remark add header on 2018-02-02 by Yunhao Huang
 */
public class SpatialSimilarityTransformParm {
    private double transx, transy, transz;	// Translation
    private double alpha, beta, gamma;		// Rotation
    private double scale;					// Scale
    private int formula;

/******************************************************************************************************************
* KONSTRUKTOREN                                                                                                   *
******************************************************************************************************************/


    public SpatialSimilarityTransformParm() { // DHDN -> WGS84
        double rho = 180. / 3.14159265358979323846;
        transx = 582.00;
        transy = 105.00;
        transz = 414.00;
        alpha = -1.04 / 3600 / rho;
        beta = -0.35 / 3600 / rho;
        gamma = 3.08 / 3600 / rho;
        scale = 1 + 8.3e-6;
        formula = 1;
    } // end Constructor

/******************************************************************************************************************
* GETTER UND SETTER                                                                                                   *
******************************************************************************************************************/

    public double getTransx() {
        return transx;
    }

    public void setTransx(double transx) {
        this.transx = transx;
    }

    public double getTransy() {
        return transy;
    }

    public void setTransy(double transy) {
        this.transy = transy;
    }

    public double getTransz() {
        return transz;
    }

    public void setTransz(double transz) {
        this.transz = transz;
    }

    public double getAlpha() {
        return alpha;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }

    public double getBeta() {
        return beta;
    }

    public void setBeta(double beta) {
        this.beta = beta;
    }

    public double getGamma() {
        return gamma;
    }

    public void setGamma(double gamma) {
        this.gamma = gamma;
    }

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public int getFormula() {
        return formula;
    }

    public void setFormula(int formula) {
        this.formula = formula;
    }

} // end params.SpatialSimilarityTransformParm
