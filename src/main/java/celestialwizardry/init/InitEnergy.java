package celestialwizardry.init;

import celestialwizardry.api.energy.EnergyElemental;
import celestialwizardry.api.energy.EnergyHeat;
import celestialwizardry.api.energy.IEnergy;
import celestialwizardry.api.energy.MysteriousMatter;

public class InitEnergy {
	
	public static final IEnergy mysteriousMatter = new MysteriousMatter();
	public static final IEnergy heat = new EnergyHeat();
	public static final IEnergy elementalFire = new EnergyElemental("Elemental Fire");
	public static final IEnergy elementalWater = new EnergyElemental("Elemental Water");
	public static final IEnergy elementalEarth = new EnergyElemental("Elemental Earth");
	public static final IEnergy elementalAir = new EnergyElemental("Elemental Air");
	
	public static void init() {
		
	}
}
