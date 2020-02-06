package params;

// package Klassen;

/**
 * @class MolodenskyParm
 * @author unknown
 * @brief Molodensky Parameters
 * @remark add header on 2018-02-02 by Yunhao Huang
 */
public class MolodenskyParm {
	private double transx, transy, transz; // Translation
	private double da, df; // Rotation

	/******************************************************************************************************************
	 * KONSTRUKTOREN *
	 ******************************************************************************************************************/

	/**
	 * @brief Transformation parameter from WGS84 to the geodesic datumstrategy.
	 *        European Datum 1950 (ED50) for West Germany.
	 * @author Nima Mazroob
	 */
	public MolodenskyParm() {
		transx = 87.;
		transy = 98.;
		transz = 121.;
		da = 251.;
		df = 0.14192702e-4;
	} // end Constructor

	/******************************************************************************************************************
	 * GETTER UND SETTER *
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

	public double getDa() {
		return da;
	}

	public void setDa(double da) {
		this.da = da;
	}

	public double getDf() {
		return df;
	}

	public void setDf(double df) {
		this.df = df;
	}

} // params.MolodenskyParm
