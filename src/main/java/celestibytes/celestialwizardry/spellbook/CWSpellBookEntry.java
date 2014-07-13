package celestibytes.celestialwizardry.spellbook;

import celestibytes.celestialwizardry.api.CWApi;
import celestibytes.celestialwizardry.api.spellbook.SpellBookCategory;
import celestibytes.celestialwizardry.api.spellbook.SpellBookEntry;
import celestibytes.celestialwizardry.api.spellbook.SpellBookPage;
import celestibytes.celestialwizardry.reference.Resources;

public class CWSpellBookEntry extends SpellBookEntry
{
    public CWSpellBookEntry(String unlocalizedName, SpellBookCategory category)
    {
        super(unlocalizedName, category);
        CWApi.addEntry(this, category);
    }

    @Override
    public SpellBookEntry setSpellBookPages(SpellBookPage... pages)
    {
        for (SpellBookPage page : pages)
        {
            page.unlocalizedName = "page." + Resources.RESOURCE_PREFIX + getLazyUnlocalizedName() + "."
                    + page.unlocalizedName;
        }

        return super.setSpellBookPages(pages);
    }

    @Override
    public String getUnlocalizedName()
    {
        return "entry." + Resources.RESOURCE_PREFIX + super.getUnlocalizedName();
    }

    public String getLazyUnlocalizedName()
    {
        return super.getUnlocalizedName();
    }
}
