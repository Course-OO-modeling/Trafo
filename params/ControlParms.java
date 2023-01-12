package params;

// package Classes;
/**
 * @class ControlParms
 * @brief Class ControlParms extends class EllipsoidParms
 * @remark last refactored 11.12.2017 by Eva Majer <br/>
 *         <ul>
 *         <li>adaptions for ControlParms Singleton</li>
 *         <li>Translation of some comments</li></li>
 *         <li>Translation of print method output</li></li>
 *         </ul>
 * @remark add some comments on 2018-01-06 by Yunhao Huang
 */

public class ControlParms extends EllipsoidParms {

	private String pointid;
	private long point_number;
	private String fromProjection;
	private String fromDatum;
	private double fromSemiMajorAxis;
	private double fromSemiMinorAxis;
	private double sourceCoordinateX;
	/** < Input coordinate X */
	private double sourceCoordinateY;
	/** < Input coordinate Y */
	private double sourceCoordinateZ;
	/** < Input coordinate Z */
	private int zone;
	private String toprojection;
	private String todatum;
	private double toawert;
	private double tobwert;
	private boolean changedatum;
	/** < True/False means with/without datum changement */
	private String northhem;
	/** < Hemisphere */
	private String kindofhoehe;
	/** < Height calculation */
	private String kindOfTrafo;
	/** < Kind of transformation (e.g. 3D similarity transformation) */
	private double dx;
	/** < Translation X (For Molodenskii und 3D similarity transformation) */
	private double dy;
	/** < Translation Y (For Molodenskii und 3D similarity transformation) */
	private double dz;
	/** < Translation Z (For Molodenskii und 3D similarity transformation) */
	private double da;
	/** < Difference in large semi axis (For Molodenskii-Transformation) */
	private double df;
	/** < Difference flattening (For Molodenskii-Transformation) */
	private double wx;
	/** < Rotation X (For 3D similarity transformation) */
	private double wy;
	/** < Rotation Y (For 3D similarity transformation) */
	private double wz;
	/** < Rotation Z (For 3D similarity transformation) */

	private double Rz1;
	/** < first Rotation about current Z (For Eulerstransformation) */
	private double Rx2;
	/** < second Rotation about current X (For Eulerstransformation) */
	private double Rz3;
	/** < third Rotation about current Z (For Eulerstransformation) */

	private double massstab;
	/** < Scale (For 3D similarity transformation) */
	private double destinationCoordinateX;
	/** < Currently not used */
	private double destinationCoordinateY;
	/** < Currently not used */
	private double destinationCoordinateZ;
	/** < Currently not used */

	private static ControlParms instance = null;

	/** < Singleton */

	/******************************************************************************************************************
	 * CONSTRUCTOR *
	 ******************************************************************************************************************/
	/**
	 * @brief private constructor for singleton
	 */
	private ControlParms() {
		sourceCoordinateX = 5366838.384;
		sourceCoordinateY = 626994.444;
		sourceCoordinateZ = 0.;
		fromDatum = "dhdn";
		todatum = "dhdn";
		fromProjection = "utm";
		toprojection = "xyz";
		kindOfTrafo = "molStandard";
		dx = 87; /* +/- 3 m */
		dy = 98; /* +/- 8 m */
		dz = 121;/* +/- 5 m */
		da = 251;
		df = 0.14192702e-4;
	}

	/**
	 * @brief Singleton getinstance
	 */
	public static ControlParms getInstance() {
		if (instance == null) {
			instance = new ControlParms();
		}
		return instance;
	}

	/******************************************************************************************************************
	 * AUXILIARY METHODS *
	 ******************************************************************************************************************/

	/**
	 * @brief print outputs of class params.ControlParms
	 * @author unknown
	 * @remark updated header 2018-01-06 by Yunhao Huang
	 */
	public void print() {
		System.out.println("Output of class params.ControlParms:");
		System.out.println("input date      : " + fromDatum);
		System.out.println("target date          : " + todatum);
		System.out.println("input coordinates: " + fromProjection);
		System.out.println("target coordinates    : " + toprojection);
		System.out.println("easting         : " + sourceCoordinateX);
		System.out.println("northing           : " + sourceCoordinateY);
		System.out.println("height               : " + sourceCoordinateZ);
		System.out.println("ellipsoid          : " + getId());
		System.out.println("dx                 : " + dx);
		System.out.println("dz                 : " + dz);
		System.out.println("df                 : " + df);
	}

	/******************************************************************************************************************
	 * GETTERS AND SETTERS *
	 ******************************************************************************************************************/

	/**
	 * @brief getter method that returns the id of the particular Coordinate point
	 * @return pointid - string representing the point identification number
	 */
	public String getPointid() {
		return pointid;
	}

	/**
	 * @brief setter method that accepts id for the particular Coordinate point
	 * @param pointid - string representing the point identification number
	 */
	public void setPointid(String pointid) {
		this.pointid = pointid;
	}

	/**
	 * @brief getter method that returns point number of the particular Coordinate
	 *        point
	 * @return point_number - returns the point number of the particular Coordinate
	 *         point as long value
	 */
	public long getPoint_number() {
		return point_number;
	}

	/**
	 * @brief setter method that accepts point number for the particular Coordinate
	 *        point
	 * @param point_number - a long value representing the point number for the
	 *                     particular Coordinate point
	 */
	public void setPoint_number(long point_number) {
		this.point_number = point_number;
	}

	public String getFromprojection() {
		return fromProjection;
	}

	public void setFromprojection(String fromprojection) {
		this.fromProjection = fromprojection;
	}

	public String getFromdatum() {
		return fromDatum;
	}

	public void setFromdatum(String fromdatum) {
		this.fromDatum = fromdatum;
	}

	public double getFromawert() {
		return fromSemiMajorAxis;
	}

	public void setFromawert(double fromawert) {
		this.fromSemiMajorAxis = fromawert;
	}

	public double getFrombwert() {
		return fromSemiMinorAxis;
	}

	public void setFrombwert(double frombwert) {
		this.fromSemiMinorAxis = frombwert;
	}

	public double getSourceCoordinateX() {
		return sourceCoordinateX;
	}

	public void setSourceCoordinateX(double sourceCoordinateX) {
		this.sourceCoordinateX = sourceCoordinateX;
	}

	public double getSourceCoordinateY() {
		return sourceCoordinateY;
	}

	public void setSourceCoordinateY(double sourceCoordinateY) {
		this.sourceCoordinateY = sourceCoordinateY;
	}

	public double getSourceCoordinateZ() {
		return sourceCoordinateZ;
	}

	public void setSourceCoordinateZ(double sourceCoordinateZ) {
		this.sourceCoordinateZ = sourceCoordinateZ;
	}

	public int getZone() {
		return zone;
	}

	public void setZone(int zone) {
		this.sourceCoordinateZ = zone;
	}
	
	public String getToprojection() {
		return toprojection;
	}

	public void setToprojection(String toprojection) {
		this.toprojection = toprojection;
	}

	public String getTodatum() {
		return todatum;
	}

	public void setTodatum(String todatum) {
		this.todatum = todatum;
	}

	public double getToawert() {
		return toawert;
	}

	public void setToawert(double toawert) {
		this.toawert = toawert;
	}

	public double getTobwert() {
		return tobwert;
	}

	public void setTobwert(double tobwert) {
		this.tobwert = tobwert;
	}

	public boolean isChangedatum() {
		return changedatum;
	}

	public void setChangedatum(boolean changedatum) {
		this.changedatum = changedatum;
	}

	public String getNorthhem() {
		return northhem;
	}

	public void setNorthhem(String northhem) {
		this.northhem = northhem;
	}

	public String getKindofhoehe() {
		return kindofhoehe;
	}

	public void setKindofhoehe(String kindofhoehe) {
		this.kindofhoehe = kindofhoehe;
	}

	public String getKindoftrafo() {
		return kindOfTrafo;
	}

	public void setKindoftrafo(String kindOfTrafo) {
		this.kindOfTrafo = kindOfTrafo;
	}

	public double getDx() {
		return dx;
	}

	public void setDx(double dx) {
		this.dx = dx;
	}

	public double getDy() {
		return dy;
	}

	public void setDy(double dy) {
		this.dy = dy;
	}

	public double getDz() {
		return dz;
	}

	public void setDz(double dz) {
		this.dz = dz;
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

	public double getWx() {
		return wx;
	}

	public void setWx(double wx) {
		this.wx = wx;
	}

	public double getWy() {
		return wy;
	}

	public void setWy(double wy) {
		this.wy = wy;
	}

	public double getWz() {
		return wz;
	}

	public void setWz(double wz) {
		this.wz = wz;
	}

	public double getRz1() {
		return Rz1;
	}

	public void setRz1(double Rz1) {
		this.Rz1 = Rz1;
	}

	public double getRx2() {
		return Rx2;
	}

	public void setRx2(double Rx2) {
		this.Rx2 = Rx2;
	}

	public double getRz3() {
		return Rz3;
	}

	public void setRz3(double Rz3) {
		this.Rz3 = Rz3;
	}

	public double getMassstab() {
		return massstab;
	}

	public void setMassstab(double massstab) {
		this.massstab = massstab;
	}

	public double getDestinationCoordinateX() {
		return destinationCoordinateX;
	}

	public void setDestinationCoordinateX(double destinationCoordinateX) {
		this.destinationCoordinateX = destinationCoordinateX;
	}

	public double getDestinationCoordinateY() {
		return destinationCoordinateY;
	}

	public void setDestinationCoordinateY(double destinationCoordinateY) {
		this.destinationCoordinateY = destinationCoordinateY;
	}

	public double getDestinationCoordinateZ() {
		return destinationCoordinateZ;
	}

	public void setDestinationCoordinateZ(double destinationCoordinateZ) {
		this.destinationCoordinateZ = destinationCoordinateZ;
	}
}