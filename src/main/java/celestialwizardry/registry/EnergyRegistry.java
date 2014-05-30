package celestialwizardry.registry;

import celestialwizardry.CelestialWizardry;
import celestialwizardry.api.energy.IEnergy;

import java.util.HashMap;
import java.util.Map;

public class EnergyRegistry
{

    public static Map<String, IEnergy> energyMap = new HashMap<String, IEnergy>();

    public static void registerEnergyType(IEnergy energy)
    {
        String name = "";// rune.getName();

        if (!energyMap.containsKey(name))
        {
            CelestialWizardry.log.info("Registering energy type " + name);
            energyMap.put(name, energy);
        }
        else
        {
            CelestialWizardry.log.warn("Trying to register duplicate energy type, skipping!");
        }
    }

    public static IEnergy getEnergyType(String name)
    {
        if (energyMap.containsKey(name))
        {
            return energyMap.get(name);
        }

        CelestialWizardry.log.warn("Trying to get null energy type, skipping!");

        return null;
    }
}
