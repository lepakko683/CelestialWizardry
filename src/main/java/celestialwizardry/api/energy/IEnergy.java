package celestialwizardry.api.energy;

public interface IEnergy {
	//Solar energy, Lunar energy, earth energy, fire energy, air energy, water energy
	//Mundane energy types: heat, ...
	public boolean canBeTransformedInto(IEnergy energy);
	
	/**Used for the transform ratio*/
	public float getEnergyValue();
	
	public String getEnergyName();
	
	public boolean isMagicalEnergy();
	
	public boolean isElementalEnergy();
	
	public boolean isMundane();
	
	public float crossEnergyTypeTransformLoss(IEnergy target);
	
}
