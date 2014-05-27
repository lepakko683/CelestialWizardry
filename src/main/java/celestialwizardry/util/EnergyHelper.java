package celestialwizardry.util;

import celestialwizardry.api.energy.IEnergy;

public class EnergyHelper {
	
	/**@return the amount of target energy gained from transforming source energy*/
	public static float getTransfromRatio(IEnergy source, IEnergy target) {
		if(source.isMagicalEnergy() && target.isMagicalEnergy()) {
			return source.getEnergyValue() / target.getEnergyValue();
		}
		return (source.getEnergyValue() / target.getEnergyValue()) * (1-((source.crossEnergyTypeTransformLoss()+target.crossEnergyTypeTransformLoss())/2));
	}
	
	public static boolean canTransform(IEnergy source, IEnergy target) {
		return (source.isMagicalEnergy() && target.isMagicalEnergy()) || (source.isMagicalEnergy() && target.isElementalEnergy()) || (source.isElementalEnergy() && target.isMagicalEnergy());
	}
}
