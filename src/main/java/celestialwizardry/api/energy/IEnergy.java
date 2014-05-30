package celestialwizardry.api.energy;

public interface IEnergy {
	//Solar energy, Lunar energy, earth energy, fire energy, air energy, water energy
	//Mundane energy types: heat, ...
	public boolean canBeTransformedInto(IEnergy energy);
	
	public float getEnergyValue();
	
	public String getEnergyName();
	
	public boolean isMagicalEnergy();
	
	public boolean isElementalEnergy();
	
	public boolean isMundane();
	
	public float crossEnergyTypeTransformLoss();
	
//	public float crossEnergyTypeTransformLoss(IEnergy target); TODO
	
}
