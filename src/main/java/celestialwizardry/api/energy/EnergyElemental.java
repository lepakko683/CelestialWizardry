package celestialwizardry.api.energy;

public class EnergyElemental extends EnergyType {
	
	private String name = null;
	
	public EnergyElemental(Object type) {
		super(type, CWEnergyCategory.ELEMENTAL);
		if(type != null && type instanceof CWEnergyType) {
			if(type == CWEnergyType.ELEMENTAL_AIR) {
				name = "Elemental Air";
			}
			
			if(type == CWEnergyType.ELEMENTAL_WATER) {
				name = "Elemental Water";
			}
			
			if(type == CWEnergyType.ELEMENTAL_EARTH) {
				name = "Elemental Earth";
			}
			
			if(type == CWEnergyType.ELEMENTAL_FIRE) {
				name = "Elemental Fire";
			}
		}
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
	public float crossEnergyTypeTransformLoss(EnergyType target) {
		return 0;
	}

}
