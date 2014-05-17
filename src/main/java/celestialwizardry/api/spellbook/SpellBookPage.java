package celestialwizardry.api.spellbook;

import celestialwizardry.api.spellbook.internal.IGuiSpellBookEntry;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class SpellBookPage
{
    public String unlocalizedName;

    public SpellBookPage(String unlocalizedName)
    {
        this.unlocalizedName = unlocalizedName;
    }

    /**
     * Does the rendering for this page.
     *
     * @param gui The active GuiScreen
     * @param mx  The mouse's relative X position.
     * @param my  The mouse's relative Y position.
     */
    @SideOnly(Side.CLIENT)
    public abstract void renderScreen(IGuiSpellBookEntry gui, int mx, int my);

    /**
     * Called per update tick.
     */
    @SideOnly(Side.CLIENT)
    public void updateScreen()
    {
        // NO-OP
    }

    /**
     * Called when {@link SpellBookEntry#setSpellBookPages(SpellBookPage...)} is called.
     */
    public void onPageAdded(SpellBookEntry entry, int index)
    {
        // NO-OP
    }

    public String getUnlocalizedName()
    {
        return unlocalizedName;
    }
}
