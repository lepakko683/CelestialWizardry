package celestibytes.celestialwizardry.spellbook;

import celestibytes.celestialwizardry.api.CWApi;
import celestibytes.celestialwizardry.api.spellbook.SpellBookCategory;
import celestibytes.celestialwizardry.api.spellbook.SpellBookEntry;
import celestibytes.celestialwizardry.reference.Resources;
import celestibytes.celestialwizardry.spellbook.page.PageText;

public final class SpellBook
{
    public static SpellBookCategory categoryBasics = new SpellBookCategory(
            "category." + Resources.RESOURCE_PREFIX + "basics");

    /**
     * Basics
     */
    public static SpellBookEntry spellBook;

    public static void init()
    {
        /**
         * Basics
         */
        CWApi.addCategory(categoryBasics);

        spellBook = new CWSpellBookEntry("spellBook", categoryBasics);
        spellBook.setPriority().setSpellBookPages(new PageText("index"));
    }
}
