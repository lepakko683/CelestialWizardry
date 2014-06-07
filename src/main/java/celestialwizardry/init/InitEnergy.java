package celestialwizardry.init;

import celestialwizardry.api.energy.EnergyElemental;
import celestialwizardry.api.energy.EnergyHeat;
import celestialwizardry.api.energy.EnergyType;
import celestialwizardry.api.energy.MysteriousMatter;

public class InitEnergy {
	
	public static final EnergyType mysteriousMatter = new MysteriousMatter();
	public static final EnergyType heat = new EnergyHeat();
	public static final EnergyType elementalFire = new EnergyElemental("Elemental Fire");
	public static final EnergyType elementalWater = new EnergyElemental("Elemental Water");
	public static final EnergyType elementalEarth = new EnergyElemental("Elemental Earth");
	public static final EnergyType elementalAir = new EnergyElemental("Elemental Air");
	
	public static void init() {
		
	}
}
