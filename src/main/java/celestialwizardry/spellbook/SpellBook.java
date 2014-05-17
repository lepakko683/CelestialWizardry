package celestialwizardry.spellbook;

import celestialwizardry.api.CWApi;
import celestialwizardry.api.spellbook.SpellBookCategory;
import celestialwizardry.api.spellbook.SpellBookEntry;
import celestialwizardry.spellbook.page.PageText;

public final class SpellBook
{
    public static SpellBookCategory categoryTest = new SpellBookCategory("Test");

    public static SpellBookEntry test;

    public static void init()
    {
        CWApi.addCategory(categoryTest);

        test = new CWSpellBookEntry("TestEntry", categoryTest);
        test.setPriority().setSpellBookPages(new PageText("0"));

    }
}
