package celestialwizardry.registry;

import celestialwizardry.CelestialWizardry;
import celestialwizardry.api.energy.EnergyType;

import java.util.HashMap;
import java.util.Map;

public class EnergyRegistry
{

    public static Map<String, EnergyType> energyMap = new HashMap<String, EnergyType>();

    public static void registerEnergyType(EnergyType energy)
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

    public static EnergyType getEnergyType(String name)
    {
        if (energyMap.containsKey(name))
        {
            return energyMap.get(name);
        }

        CelestialWizardry.log.warn("Trying to get null energy type, skipping!");

        return null;
    }
}
