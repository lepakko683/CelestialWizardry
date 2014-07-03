package celestialwizardry.registry;

import celestialwizardry.api.spell.Spell;
import celestialwizardry.util.LogHelper;

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
            LogHelper.info("Registering spell " + name);
            spellMap.put(name, spell);
        }
        else
        {
            LogHelper.warn("Trying to register duplicate spell, skipping!");
        }
    }

    public static Spell getSpell(String name)
    {
        if (spellMap.containsKey(name))
        {
            return spellMap.get(name);
        }

        LogHelper.warn("Trying to get null spell, skipping!");

        return null;
    }

    public static Spell getSpellFromString(String name)
    {
        return getSpell(name);
    }
}
