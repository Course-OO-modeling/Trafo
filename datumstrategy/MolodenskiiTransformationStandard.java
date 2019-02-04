package datumstrategy;

// package Klassen;

/**
 * @class MolodenskiiTransformationStandard
 * @brief Algorithmic realization of the molodensky transformation
 * @author unknown
 *  @remark last refactored 11.12.2017 by Eva Majer <br/>
 *  <ul>
 *  	<li>adaptions for ControlParms Singleton</li>
 *  	<li>Translation of some comments</li></li>
 *  	<li>removed declaration of dl hd db which appeared 3 times</li></li>
 *  </ul>
 * @remark changed the class name (CamelCase) on 20.12.17 by Markus Hillemann
 * @version 0.1
 */

import params.ControlParms;
import coordinates.GeographicCoordinateInterface;
import datumstrategy.TransformationStrategy;

public class MolodenskiiTransformationStandard extends TransformationStrategy {

    private static MolodenskiiTransformationStandard myInstance = null;

    private MolodenskiiTransformationStandard() {
    }

    public static MolodenskiiTransformationStandard getInstance() {

        if (myInstance == null)
        {
           myInstance = new MolodenskiiTransformationStandard();
        }

        return myInstance;
    }

    @Override
    public void transform(GeographicCoordinateInterface geo) {

        // Algorithmic realisation of the date change to Molodenskii
        ControlParms control = ControlParms.getInstance();
        double dl, db, dh;

        control.calculatelongitudeParms(geo);

        /* Use of this standard formula as all Parameters are given by NIMA */
        db = (-control.getDx() * Math.sin(geo.getLatitude()) * Math.cos(geo.getLongitude())
                - control.getDy() * Math.sin(geo.getLatitude()) * Math.sin(geo.getLongitude())
                + control.getDz() * Math.cos(geo.getLatitude())
                + control.getDa() * (control.getN() * control.getFirstEccentricity() * Math.sin(geo.getLatitude())
                * Math.cos(geo.getLatitude())) / control.getSemiMajorAxis()
                + control.getDf() * (control.getM() * (control.getSemiMajorAxis() / control.getSemiMinorAxis()) + control.getN() * (control.getSemiMinorAxis()
                / control.getSemiMajorAxis())) * Math.sin(geo.getLatitude()) * Math.cos(geo.getLatitude()))
                / (control.getM() + geo.getHeight());

        dl = (-control.getDx() * Math.sin(geo.getLongitude())
                + control.getDy() * Math.cos(geo.getLongitude()))
                / ((control.getN() + geo.getHeight()) * Math.cos(geo.getLatitude()));

        dh = control.getDx() * Math.cos(geo.getLatitude()) * Math.cos(geo.getLongitude())
                + control.getDy() * Math.cos(geo.getLatitude()) * Math.sin(geo.getLongitude())
                + control.getDz() * Math.sin(geo.getLatitude())
                - control.getDa() * (control.getSemiMajorAxis() / control.getN())
                + control.getDf() * (control.getSemiMinorAxis() / control.getSemiMajorAxis())
                * control.getN() * Math.pow(Math.sin(geo.getLatitude()), 2);

        geo.setLatitude(geo.getLatitude() + db);
        geo.setLongitude(geo.getLongitude() + dl);
        geo.setHeight(geo.getHeight() + dh);
    }
}
