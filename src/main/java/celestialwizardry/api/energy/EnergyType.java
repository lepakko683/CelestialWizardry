package celestialwizardry.api.energy;

public abstract class EnergyType {
	//Solar energy, Lunar energy, earth energy, fire energy, air energy, water energy
	//Mundane energy types: heat, ...
	public abstract boolean canBeTransformedInto(EnergyType energy);
	
	/**Used for the transform ratio*/
	public abstract float getEnergyValue();
	
	public abstract String getEnergyName();
	
	public abstract boolean isMagicalEnergy();
	
	public abstract boolean isElementalEnergy();
	
	public abstract boolean isMundane();
	
	public abstract float crossEnergyTypeTransformLoss(EnergyType target);
	
}
