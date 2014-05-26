package celestialwizardry.registry;

import celestialwizardry.CelestialWizardry;
import celestialwizardry.api.spellgrammar.Rune;

import java.util.HashMap;
import java.util.Map;

public abstract class RuneRegistry
{
    public static Map<Integer, Rune> runeMap = new HashMap<Integer, Rune>();

    public static void registerSpell(Rune rune)
    {
        String name = "";// rune.getName();

        if (!runeMap.containsKey(name))
        {
            CelestialWizardry.log.info("Registering rune " + name);
            runeMap.put(rune.hashCode(), rune);
        }
        else
        {
            CelestialWizardry.log.warn("Trying to register duplicate rune, skipping!");
        }
    }

    public static Rune getRune(String name)
    {
        if (runeMap.containsKey(name))
        {
            return runeMap.get(name);
        }

        CelestialWizardry.log.warn("Trying to get null rune, skipping!");

        return null;
    }
}
