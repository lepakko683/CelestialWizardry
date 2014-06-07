package celestialwizardry.api.energy;

public class EnergyElemental extends EnergyType {
	
	private String name;
	
	public EnergyElemental(String name) {
		this.name = name;
	}

	@Override
	public boolean canBeTransformedInto(EnergyType energy) {
		return false;
	}

	@Override
	public float getEnergyValue() {
		return 0;
	}

	@Override
	public String getEnergyName() {
		return name;
	}

	@Override
	public boolean isMagicalEnergy() {
		return false;
	}

	@Override
	public boolean isElementalEnergy() {
		return false;
	}

	@Override
	public boolean isMundane() {
		return false;
	}

	@Override
	public float crossEnergyTypeTransformLoss(EnergyType target) {
		return 0;
	}

}
