package celestibytes.celestialwizardry.registry;

import celestibytes.celestialwizardry.api.spellbook.SpellBookCategory;
import celestibytes.celestialwizardry.api.spellbook.SpellBookEntry;
import celestibytes.celestialwizardry.util.LogHelper;

import java.util.ArrayList;
import java.util.List;

public abstract class SpellBookRegistry
{
    private static List<SpellBookCategory> categories = new ArrayList<SpellBookCategory>();
    private static List<SpellBookEntry> allEntries = new ArrayList<SpellBookEntry>();

    /**
     * Adds a category to the list of registered categories to appear in the spell book.
     */
    public static void addCategory(SpellBookCategory category)
    {
        LogHelper.info("Adding new spell book category " + category.getUnlocalizedName());
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
        LogHelper.info("Adding new spell book entry " + entry.getUnlocalizedName() + " to category " + category
                .getUnlocalizedName());
        allEntries.add(entry);
        category.entries.add(entry);
    }
}
