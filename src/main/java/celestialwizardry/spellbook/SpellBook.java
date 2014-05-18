package celestialwizardry.spellbook;

import celestialwizardry.api.CWApi;
import celestialwizardry.api.spellbook.SpellBookCategory;
import celestialwizardry.api.spellbook.SpellBookEntry;
import celestialwizardry.reference.Resources;
import celestialwizardry.spellbook.page.PageText;

public final class SpellBook
{
    public static SpellBookCategory categoryBasics = new SpellBookCategory("category." + Resources.RESOURCE_PREFIX + "basics");

    /**
     * Basics
     */
    public static SpellBookEntry spellBook;

    public static void init()
    {
        CWApi.addCategory(categoryBasics);

        spellBook = new CWSpellBookEntry("spellBook", categoryBasics);
        spellBook.setPriority().setSpellBookPages(new PageText("index"));

    }
}
