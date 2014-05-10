package celestialwizardry.api;

import celestialwizardry.api.spell.Spell;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class CWApi
{
    private static Map<String, Spell> spellMap = new HashMap<String, Spell>();

    public static Logger apiLog = null;

    public static void registerSpell(Spell spell, String name)
    {
        if (!spellMap.containsKey(name))
        {
            spellMap.put(name, spell);
        }
        else
        {
            apiLog.warn("Trying to register duplicate spell, skipping!");
        }
    }

    public static Spell getSpell(String name)
    {
        if (spellMap.containsKey(name))
        {
            return spellMap.get(name);
        }

        apiLog.warn("Trying to get null spell, skipping!");

        return null;
    }

    public static Map<String, Spell> getSpellMap()
    {
        return spellMap;
    }
}
