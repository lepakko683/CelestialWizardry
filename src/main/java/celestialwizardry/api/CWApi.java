package celestialwizardry.api;

import celestialwizardry.api.spell.Spell;
import celestialwizardry.api.spellbook.SpellBookCategory;
import celestialwizardry.api.spellbook.SpellBookEntry;
import celestialwizardry.api.spellgrammar.Rune;

import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public final class CWApi
{
    public static final String API_VERSION = "@API_VERSION@";

    public static Logger apiLog = null;

    private static final String CW_PACKAGE = "celestialwizardry.";

    private static final String REGISTRY_PACKAGE = CW_PACKAGE + "registry.";
    private static final String SPELL_REGISTRY = REGISTRY_PACKAGE + "SpellRegistry";
    private static final String SPELL_BOOK_REGISTRY = REGISTRY_PACKAGE + "SpellBookRegistry";
    private static final String RUNE_REGISTRY = REGISTRY_PACKAGE + "RuneRegistry";

    private static final String CONFIG_PACKAGE = CW_PACKAGE + "config.";
    private static final String SPELL_CONFIG_PACKAGE = CONFIG_PACKAGE + "spell.";
    private static final String CONFIG_SPELLS = SPELL_CONFIG_PACKAGE + "ConfigSpells";

    ////////// SPELLS ////////////////////

    /**
     * Register a spell
     *
     * @param spell
     */
    public static void registerSpell(Spell spell)
    {
        try
        {
            Class<?> clazz = Class.forName(SPELL_REGISTRY);
            Class[] args = new Class[]{Spell.class};
            Method method = clazz.getDeclaredMethod("registerSpell", args);
            method.invoke(null, spell);
        }
        catch (ClassNotFoundException e)
        {
            CWApi.apiLog.warn("Failed to invoke method " + SPELL_REGISTRY + ".registerSpell");
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            CWApi.apiLog.warn("Failed to invoke method " + SPELL_REGISTRY + ".registerSpell");
            e.printStackTrace();
        }
        catch (IllegalArgumentException e)
        {
            CWApi.apiLog.warn("Failed to invoke method " + SPELL_REGISTRY + ".registerSpell");
            e.printStackTrace();
        }
        catch (NoSuchMethodException e)
        {
            CWApi.apiLog.warn("Failed to invoke method " + SPELL_REGISTRY + ".registerSpell");
            e.printStackTrace();
        }
        catch (SecurityException e)
        {
            CWApi.apiLog.warn("Failed to invoke method " + SPELL_REGISTRY + ".registerSpell");
            e.printStackTrace();
        }
        catch (InvocationTargetException e)
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
        catch (ClassNotFoundException e)
        {
            CWApi.apiLog.warn("Failed to invoke method " + SPELL_REGISTRY + ".getSpell");
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            CWApi.apiLog.warn("Failed to invoke method " + SPELL_REGISTRY + ".getSpell");
            e.printStackTrace();
        }
        catch (IllegalArgumentException e)
        {
            CWApi.apiLog.warn("Failed to invoke method " + SPELL_REGISTRY + ".getSpell");
            e.printStackTrace();
        }
        catch (NoSuchMethodException e)
        {
            CWApi.apiLog.warn("Failed to invoke method " + SPELL_REGISTRY + ".getSpell");
            e.printStackTrace();
        }
        catch (SecurityException e)
        {
            CWApi.apiLog.warn("Failed to invoke method " + SPELL_REGISTRY + ".getSpell");
            e.printStackTrace();
        }
        catch (InvocationTargetException e)
        {
            CWApi.apiLog.warn("Failed to invoke method " + SPELL_REGISTRY + ".getSpell");
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Handles spell cost configuration for you
     *
     * @param spell
     * @param defaultCost
     *
     * @return
     */
    public static double handleSpellCost(Spell spell, Double defaultCost)
    {
        try
        {
            Class<?> clazz = Class.forName(CONFIG_SPELLS);
            Class[] args = new Class[]{Spell.class, Double.class};
            Method method = clazz.getDeclaredMethod("handleSpellCost", args);
            return (Double) method.invoke(null, spell, defaultCost);
        }
        catch (ClassNotFoundException e)
        {
            CWApi.apiLog.warn("Failed to invoke method " + CONFIG_SPELLS + ".handleSpellCost");
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            CWApi.apiLog.warn("Failed to invoke method " + CONFIG_SPELLS + ".handleSpellCost");
            e.printStackTrace();
        }
        catch (IllegalArgumentException e)
        {
            CWApi.apiLog.warn("Failed to invoke method " + CONFIG_SPELLS + ".handleSpellCost");
            e.printStackTrace();
        }
        catch (NoSuchMethodException e)
        {
            CWApi.apiLog.warn("Failed to invoke method " + CONFIG_SPELLS + ".handleSpellCost");
            e.printStackTrace();
        }
        catch (SecurityException e)
        {
            CWApi.apiLog.warn("Failed to invoke method " + CONFIG_SPELLS + ".handleSpellCost");
            e.printStackTrace();
        }
        catch (InvocationTargetException e)
        {
            CWApi.apiLog.warn("Failed to invoke method " + CONFIG_SPELLS + ".handleSpellCost");
            e.printStackTrace();
        }

        return 1.0d;
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
        catch (ClassNotFoundException e)
        {
            CWApi.apiLog.warn("Failed to invoke method " + SPELL_BOOK_REGISTRY + ".addCategory");
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            CWApi.apiLog.warn("Failed to invoke method " + SPELL_BOOK_REGISTRY + ".addCategory");
            e.printStackTrace();
        }
        catch (IllegalArgumentException e)
        {
            CWApi.apiLog.warn("Failed to invoke method " + SPELL_BOOK_REGISTRY + ".addCategory");
            e.printStackTrace();
        }
        catch (NoSuchMethodException e)
        {
            CWApi.apiLog.warn("Failed to invoke method " + SPELL_BOOK_REGISTRY + ".addCategory");
            e.printStackTrace();
        }
        catch (SecurityException e)
        {
            CWApi.apiLog.warn("Failed to invoke method " + SPELL_BOOK_REGISTRY + ".addCategory");
            e.printStackTrace();
        }
        catch (InvocationTargetException e)
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
        catch (ClassNotFoundException e)
        {
            CWApi.apiLog.warn("Failed to invoke method " + SPELL_BOOK_REGISTRY + ".getAllCategories");
        }
        catch (IllegalAccessException e)
        {
            CWApi.apiLog.warn("Failed to invoke method " + SPELL_BOOK_REGISTRY + ".getAllCategories");
        }
        catch (IllegalArgumentException e)
        {
            CWApi.apiLog.warn("Failed to invoke method " + SPELL_BOOK_REGISTRY + ".getAllCategories");
        }
        catch (NoSuchMethodException e)
        {
            CWApi.apiLog.warn("Failed to invoke method " + SPELL_BOOK_REGISTRY + ".getAllCategories");
        }
        catch (SecurityException e)
        {
            CWApi.apiLog.warn("Failed to invoke method " + SPELL_BOOK_REGISTRY + ".getAllCategories");
        }
        catch (InvocationTargetException e)
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
        catch (ClassNotFoundException e)
        {
            CWApi.apiLog.warn("Failed to invoke method " + SPELL_BOOK_REGISTRY + ".addEntry");
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            CWApi.apiLog.warn("Failed to invoke method " + SPELL_BOOK_REGISTRY + ".addEntry");
            e.printStackTrace();
        }
        catch (IllegalArgumentException e)
        {
            CWApi.apiLog.warn("Failed to invoke method " + SPELL_BOOK_REGISTRY + ".addEntry");
            e.printStackTrace();
        }
        catch (NoSuchMethodException e)
        {
            CWApi.apiLog.warn("Failed to invoke method " + SPELL_BOOK_REGISTRY + ".addEntry");
            e.printStackTrace();
        }
        catch (SecurityException e)
        {
            CWApi.apiLog.warn("Failed to invoke method " + SPELL_BOOK_REGISTRY + ".addEntry");
            e.printStackTrace();
        }
        catch (InvocationTargetException e)
        {
            CWApi.apiLog.warn("Failed to invoke method " + SPELL_BOOK_REGISTRY + ".addEntry");
            e.printStackTrace();
        }
    }

    ////////// RUNES ////////////////////

    /**
     * Registers a rune
     *
     * @param rune
     */
    public static void registerRune(Rune rune)
    {
        try
        {
            Class<?> clazz = Class.forName(RUNE_REGISTRY);
            Class[] args = new Class[]{Rune.class};
            Method method = clazz.getDeclaredMethod("registerRune", args);
            method.invoke(null, rune);
        }
        catch (ClassNotFoundException e)
        {
            CWApi.apiLog.warn("Failed to invoke method " + RUNE_REGISTRY + ".registerRune");
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            CWApi.apiLog.warn("Failed to invoke method " + RUNE_REGISTRY + ".registerRune");
            e.printStackTrace();
        }
        catch (IllegalArgumentException e)
        {
            CWApi.apiLog.warn("Failed to invoke method " + RUNE_REGISTRY + ".registerRune");
            e.printStackTrace();
        }
        catch (NoSuchMethodException e)
        {
            CWApi.apiLog.warn("Failed to invoke method " + RUNE_REGISTRY + ".registerRune");
            e.printStackTrace();
        }
        catch (SecurityException e)
        {
            CWApi.apiLog.warn("Failed to invoke method " + RUNE_REGISTRY + ".registerRune");
            e.printStackTrace();
        }
        catch (InvocationTargetException e)
        {
            CWApi.apiLog.warn("Failed to invoke method " + RUNE_REGISTRY + ".registerRune");
            e.printStackTrace();
        }
    }

    /**
     * Gets a registered rune
     *
     * @param name
     *
     * @return
     */
    public static Rune getRune(String name)
    {
        try
        {
            Class<?> clazz = Class.forName(RUNE_REGISTRY);
            Class[] args = new Class[]{String.class};
            Method method = clazz.getDeclaredMethod("getRune", args);
            return (Rune) method.invoke(null, name);
        }
        catch (ClassNotFoundException e)
        {
            CWApi.apiLog.warn("Failed to invoke method " + RUNE_REGISTRY + ".getRune");
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            CWApi.apiLog.warn("Failed to invoke method " + RUNE_REGISTRY + ".getRune");
            e.printStackTrace();
        }
        catch (IllegalArgumentException e)
        {
            CWApi.apiLog.warn("Failed to invoke method " + RUNE_REGISTRY + ".getRune");
            e.printStackTrace();
        }
        catch (NoSuchMethodException e)
        {
            CWApi.apiLog.warn("Failed to invoke method " + RUNE_REGISTRY + ".getRune");
            e.printStackTrace();
        }
        catch (SecurityException e)
        {
            CWApi.apiLog.warn("Failed to invoke method " + RUNE_REGISTRY + ".getRune");
            e.printStackTrace();
        }
        catch (InvocationTargetException e)
        {
            CWApi.apiLog.warn("Failed to invoke method " + RUNE_REGISTRY + ".getRune");
            e.printStackTrace();
        }

        return null;
    }
}
