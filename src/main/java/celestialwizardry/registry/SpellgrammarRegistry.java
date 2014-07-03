package celestialwizardry.registry;

import celestialwizardry.api.spellgrammar.SpellgrammarHandler;

import java.util.HashMap;
import java.util.Map;

public class SpellgrammarRegistry
{

    private static Map<String, SpellgrammarHandler> handlers = new HashMap<String, SpellgrammarHandler>();


    public static void registerSpellgrammarHandler(SpellgrammarHandler h)
    {
        if (h.getModId() != null)
        {

        }
    }
}
