package celestialwizardry.api.energy;

import celestialwizardry.util.LogHelper;

import java.util.HashMap;
import java.util.Map;

// TODO Move me back
public abstract class EnergyRegistry
{

    public static Map<String, EnergyType> energyMap = new HashMap<String, EnergyType>();

    public static void registerEnergyType(EnergyType energy)
    {
        String name = energy.getEnergyName();

        if (name == null || name.length() == 0)
        {
            LogHelper.error("Trying to register nameless energy type of class \"" + energy.getClass().getName()
                                    + "\", Skipping.");
            return;
        }

        if (!energyMap.containsKey(name))
        {
            LogHelper.info("Registering energy type " + name);
            energyMap.put(name, energy);
        }
        else
        {
            LogHelper.warn("Trying to register duplicate energy type, skipping!");
        }
    }

    public static EnergyType getEnergyType(String name)
    {
        if (energyMap.containsKey(name))
        {
            return energyMap.get(name);
        }

        LogHelper.warn("Trying to get null energy type, skipping!");

        return null;
    }
}
