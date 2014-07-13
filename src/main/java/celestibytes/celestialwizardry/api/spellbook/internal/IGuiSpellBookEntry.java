package celestibytes.celestialwizardry.api.spellbook.internal;

import celestibytes.celestialwizardry.api.spellbook.SpellBookEntry;

public interface IGuiSpellBookEntry
{
    /**
     * Gets the entry currently portrayed in this gui.
     */
    public SpellBookEntry getEntry();

    /**
     * Gets the current page the spell book GUI is browsing.
     */
    public int getPageOn();

    /**
     * Gets the leftmost part of the GUI.
     */
    public int getLeft();

    /**
     * Gets the topmost part of the GUI.
     */
    public int getTop();

    /**
     * Gets the GUI's width.
     */
    public int getWidth();

    /**
     * Gets the GUI's height
     */
    public int getHeight();

    /**
     * Gets the GUI's Z level for rendering.
     */
    public float getZLevel();
}
