package celestialwizardry.api;

import celestialwizardry.api.spell.Spell;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Method;

public class CWApi
{
    public static Logger apiLog = null;

    private static final String REGISTRY_PACKAGE = "celestialwizardry.registry.";
    private static final String SPELL_REGISTRY = REGISTRY_PACKAGE + "SpellRegistry";

    public static void registerSpell(Spell spell)
    {
        registerSpell(spell, spell.getName());
    }

    public static void registerSpell(Spell spell, String name)
    {
        try
        {
            Class<?> clazz = Class.forName(REGISTRY_PACKAGE + "SpellRegistry");
            Class[] args = new Class[]{Spell.class, String.class};
            Method method = clazz.getDeclaredMethod("registerSpell", args);
            method.invoke(null, spell, name);
        }
        catch (Exception e)
        {
            CWApi.apiLog.warn("Failed to invoke method " + SPELL_REGISTRY + ".registerSpell");
            e.printStackTrace();
        }
    }

    public static Spell getSpell(String name)
    {
        try
        {
            Class<?> clazz = Class.forName(REGISTRY_PACKAGE + "SpellRegistry");
            Class[] args = new Class[]{String.class};
            Method method = clazz.getDeclaredMethod("getSpell", args);
            return (Spell) method.invoke(null, name);
        }
        catch (Exception e)
        {
            CWApi.apiLog.warn("Failed to invoke method " + SPELL_REGISTRY + ".getSpell");
        }

        return null;
    }
}
