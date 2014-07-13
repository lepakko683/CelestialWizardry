package celestibytes.celestialwizardry.api.spellbook;

import java.util.ArrayList;
import java.util.List;

public final class SpellBookCategory
{
    public final String unlocalizedName;
    public final List<SpellBookEntry> entries = new ArrayList<SpellBookEntry>();

    /**
     * @param unlocalizedName The unlocalized name of this category. This will be localized by the client display.
     */
    public SpellBookCategory(String unlocalizedName)
    {
        this.unlocalizedName = unlocalizedName;
    }

    public String getUnlocalizedName()
    {
        return unlocalizedName;
    }
}
