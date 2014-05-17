package celestialwizardry.api;

import celestialwizardry.api.spell.Spell;
import celestialwizardry.api.spellbook.SpellBookCategory;
import celestialwizardry.api.spellbook.SpellBookEntry;

import org.apache.logging.log4j.Logger;

import java.lang.reflect.Method;
import java.util.List;

public final class CWApi
{
    public static Logger apiLog = null;

    private static final String REGISTRY_PACKAGE = "celestialwizardry.registry.";
    private static final String SPELL_REGISTRY = REGISTRY_PACKAGE + "SpellRegistry";
    private static final String SPELL_BOOK_REGISTRY = REGISTRY_PACKAGE + "SpellBookRegistry";

    ////////// SPELLS ////////////////////

    /**
     * Register a spell
     *
     * @param spell
     */
    public static void registerSpell(Spell spell)
    {
        registerSpell(spell, spell.getName());
    }

    /**
     * Register a spell
     *
     * @param spell
     * @param name
     */
    public static void registerSpell(Spell spell, String name)
    {
        try
        {
            Class<?> clazz = Class.forName(SPELL_REGISTRY);
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

    /**
     * Get a registered spell for name
     *
     * @param name
     *
     * @return
     */
    public static Spell getSpell(String name)
    {
        try
        {
            Class<?> clazz = Class.forName(SPELL_REGISTRY);
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

    ////////// SPELL BOOK ////////////////////

    /**
     * Adds a category to the list of registered categories to appear in the spell book.
     *
     * @param category
     */
    public static void addCategory(SpellBookCategory category)
    {
        try
        {
            Class<?> clazz = Class.forName(SPELL_BOOK_REGISTRY);
            Class[] args = new Class[]{SpellBookCategory.class};
            Method method = clazz.getDeclaredMethod("addCategory", args);
            method.invoke(null, category);
        }
        catch (Exception e)
        {
            CWApi.apiLog.warn("Failed to invoke method " + SPELL_BOOK_REGISTRY + ".addCategory");
            e.printStackTrace();
        }
    }

    /**
     * Gets all registered categories.
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public static List<SpellBookCategory> getAllCategories()
    {
        try
        {
            Class<?> clazz = Class.forName(SPELL_BOOK_REGISTRY);
            Class[] args = new Class[]{};
            Method method = clazz.getDeclaredMethod("getAllCategories", args);
            return (List<SpellBookCategory>) method.invoke(null);
        }
        catch (Exception e)
        {
            CWApi.apiLog.warn("Failed to invoke method " + SPELL_BOOK_REGISTRY + ".getAllCategories");
        }

        return null;
    }

    /**
     * Registers a spell book entry and adds it to the category passed in.
     *
     * @param entry
     * @param category
     */
    public static void addEntry(SpellBookEntry entry, SpellBookCategory category)
    {
        try
        {
            Class<?> clazz = Class.forName(SPELL_BOOK_REGISTRY);
            Class[] args = new Class[]{SpellBookEntry.class, SpellBookCategory.class};
            Method method = clazz.getDeclaredMethod("addEntry", args);
            method.invoke(null, entry, category);
        }
        catch (Exception e)
        {
            CWApi.apiLog.warn("Failed to invoke method " + SPELL_BOOK_REGISTRY + ".addEntry");
            e.printStackTrace();
        }
    }
}
