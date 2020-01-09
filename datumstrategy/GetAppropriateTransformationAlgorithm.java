package datumstrategy;

// package Classes;

/**
 *  @class GetAppropriateTransformationAlgorithm
 *  @remark last refactored 11.12.2017 by Eva Majer <br/>
 *  <ul>
 *   <li>adaptions for ControlParms Singleton</li>
 *  </ul>
 *  @remark option for the abridged Molodenskii Transformation was added by Svea Krikau (7. December 2019)
 */

import params.ControlParms;
import datumstrategy.TransformationStrategy;
import datumstrategy.MolodenskiiTransformationStandard;
import datumstrategy.SpatialSimilarityTransformationInfin;

public class GetAppropriateTransformationAlgorithm {

	public static TransformationStrategy getStrategy() {

		ControlParms control = ControlParms.getInstance();

		if (control.getKindoftrafo() == null) // kein Wechsel des Datums
			return new MockStrategy();
		else if (control.getKindoftrafo() == "mol_stand")
			return MolodenskiiTransformationStandard.getInstance();
		else if (control.getKindoftrafo() == "mol_abridged")
			return MolodenskiiTransformationAbridged.getInstance();
		else if (control.getKindoftrafo() == "3D_infin")
			return new SpatialSimilarityTransformationInfin();
		return null;
	}
}