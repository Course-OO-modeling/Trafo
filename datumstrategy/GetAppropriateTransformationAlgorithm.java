package datumstrategy;

// package Classes;

/**
 *  @class GetAppropriateTransformationAlgorithm
 *  @remark last refactored 11.12.2017 by Eva Majer <br/>
 *  <ul>
 *   <li>adaptions for ControlParms Singleton</li>
 *  </ul>
 */

import params.ControlParms;

public class GetAppropriateTransformationAlgorithm {

	public static TransformationStrategy getStrategy() {

		ControlParms control = ControlParms.getInstance();
		
		switch(control.getKindoftrafo()){
		case "none": // no change of geodetic datum
			return new MockStrategy();
		case "molStandard":
			return MolodenskiiTransformationStandard.getInstance();
		case "molAbridged":
			return MolodenskiiTransformationAbridged.getInstance();
		case "3DInfin":
			return new SpatialSimilarityTransformationInfin();
		case "3DTrig":
			return new SpatialSimilarityTransformationTrig();
		default:
			return null;
		}
	}
}
