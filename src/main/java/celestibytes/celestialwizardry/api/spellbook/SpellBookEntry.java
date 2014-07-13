package celestibytes.celestialwizardry.api.spellbook;

import net.minecraft.util.StatCollector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpellBookEntry implements Comparable<SpellBookEntry>
{
    public final String unlocalizedName;
    public final SpellBookCategory category;

    public List<SpellBookPage> pages = new ArrayList<SpellBookPage>();
    private boolean priority = false;

    /**
     * @param unlocalizedName The unlocalized name of this entry. This will be localized by the client display.
     */
    public SpellBookEntry(String unlocalizedName, SpellBookCategory category)
    {
        this.unlocalizedName = unlocalizedName;
        this.category = category;
    }

    /**
     * Sets this page as prioritized, as in, will appear before others in the spell book.
     */
    public SpellBookEntry setPriority()
    {
        priority = true;
        return this;
    }

    public boolean isPriority()
    {
        return priority;
    }

    public String getUnlocalizedName()
    {
        return unlocalizedName;
    }

    /**
     * Sets what pages you want this entry to have.
     */
    public SpellBookEntry setSpellBookPages(SpellBookPage... pages)
    {
        this.pages.addAll(Arrays.asList(pages));

        for (int i = 0; i < this.pages.size(); i++)
        {
            this.pages.get(i).onPageAdded(this, i);
        }

        return this;
    }

    /**
     * Adds a page to the list of pages.
     */
    public void addPage(SpellBookPage page)
    {
        pages.add(page);
    }

    public final String getNameForSorting()
    {
        return (priority ? 0 : 1) + StatCollector.translateToLocal(getUnlocalizedName());
    }

    @Override
    public int compareTo(SpellBookEntry o)
    {
        return getNameForSorting().compareTo(o.getNameForSorting());
    }
}
