package celestialwizardry.api;

import celestialwizardry.api.spell.Spell;
import celestialwizardry.api.spellbook.SpellBookCategory;
import celestialwizardry.api.spellbook.SpellBookEntry;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public final class CWApi
{
    private static List<SpellBookCategory> categories = new ArrayList<SpellBookCategory>();
    private static List<SpellBookEntry> allEntries = new ArrayList<SpellBookEntry>();

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

    /**
     * Adds a category to the list of registered categories to appear in the spell book.
     */
    public static void addCategory(SpellBookCategory category)
    {
        categories.add(category);
    }

    /**
     * Gets all registered categories.
     */
    public static List<SpellBookCategory> getAllCategories()
    {
        return categories;
    }

    /**
     * Registers a spell book entry and adds it to the category passed in.
     */
    public static void addEntry(SpellBookEntry entry, SpellBookCategory category)
    {
        allEntries.add(entry);
        category.entries.add(entry);
    }
}
