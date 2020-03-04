package datumstrategy;

/**
 * @class MolodenskiiTransformationAbridged
 * @brief Algorithmic realization of the abridged molodensky transformation
 * @author Svea Krikau
 * @remark created 7. December 2019
 * @version 0.1
 * Quelle: http://www.mygeodesy.id.au/documents/Molodensky%20V2.pdf
 */
import params.ControlParms;
import coordinates.GeographicCoordinateInterface;

public class MolodenskiiTransformationAbridged extends TransformationStrategy {

	private static MolodenskiiTransformationAbridged myInstance = null;

	private MolodenskiiTransformationAbridged() {
	}

	public static MolodenskiiTransformationAbridged getInstance() {

		if (myInstance == null) {
			myInstance = new MolodenskiiTransformationAbridged();
		}

		return myInstance;
	}

	@Override
	public void transform(GeographicCoordinateInterface geo) {

		ControlParms control = ControlParms.getInstance();

		double db, dl, dh;

		// Latitude
		db = (-control.getDx() * Math.sin(geo.getLatitude()) * Math.cos(geo.getLongitude())
				- control.getDy() * Math.sin(geo.getLatitude()) * Math.sin(geo.getLongitude())
				+ control.getDz() * Math.cos(geo.getLatitude())
				+ ((control.getFlattening() * control.getDa() + control.getSemiMajorAxis() * control.getDf())
						* Math.sin(2 * geo.getLatitude())));
		db = db / control.getM();

		// Longitude
		dl = (1. / (control.getN() * Math.cos(geo.getLatitude()))) * ((-control.getDx() * Math.sin(geo.getLongitude()))
				+ (control.getDy() * Math.cos(geo.getLongitude())));

		// Height
		dh = control.getDx() * Math.cos(geo.getLatitude()) * Math.cos(geo.getLongitude())
				+ control.getDy() * Math.cos(geo.getLatitude()) * Math.sin(geo.getLongitude())
				+ control.getDz() * Math.sin(geo.getLatitude()) - control.getDa()
				+ ((control.getFlattening() * control.getDa() + control.getSemiMajorAxis() * control.getDf())
						* Math.pow(Math.sin(geo.getLatitude()), 2));

		// Apply the Transformation
		geo.setLatitude(geo.getLatitude() + db);
		geo.setLongitude(geo.getLongitude() + dl);
		geo.setHeight(geo.getHeight() + dh);
	}

}