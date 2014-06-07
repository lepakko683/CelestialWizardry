package celestialwizardry.api.energy;

public class MysteriousMatter extends EnergyType {

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
		return "Mysterious Matter";
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
		return true;
	}

	@Override
	public float crossEnergyTypeTransformLoss(EnergyType target) {
		return 0;
	}

}
