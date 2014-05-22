package celestialwizardry.registry;

import celestialwizardry.CelestialWizardry;
import celestialwizardry.api.spell.Spell;

import java.util.HashMap;
import java.util.Map;

public abstract class SpellRegistry
{
    public static Map<String, Spell> spellMap = new HashMap<String, Spell>();

    public static void registerSpell(Spell spell)
    {
        String name = spell.getName();

        if (!spellMap.containsKey(name))
        {
            CelestialWizardry.log.info("Registering spell " + name);
            spellMap.put(name, spell);
        }
        else
        {
            CelestialWizardry.log.warn("Trying to register duplicate spell, skipping!");
        }
    }

    public static Spell getSpell(String name)
    {
        if (spellMap.containsKey(name))
        {
            return spellMap.get(name);
        }

        CelestialWizardry.log.warn("Trying to get null spell, skipping!");

        return null;
    }

    public static Spell getSpellFromString(String name)
    {
        return getSpell(name);
    }
}
