package celestialwizardry.client.gui;

import celestialwizardry.api.spellbook.IAddonEntry;
import celestialwizardry.api.spellbook.SpellBookEntry;
import celestialwizardry.api.spellbook.SpellBookPage;
import celestialwizardry.api.spellbook.internal.IGuiSpellBookEntry;
import celestialwizardry.util.CWStringHelper;

import net.minecraft.entity.player.InventoryPlayer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiSpellBookEntry extends GuiSpellBookGuide implements IGuiSpellBookEntry
{
    public SpellBookEntry entry;

    GuiSpellBookCategory parent;
    String title;
    String subtitle;
    int page = 0; // TODO Check the page

    public GuiSpellBookEntry(InventoryPlayer player, SpellBookEntry entry, GuiSpellBookCategory parent)
    {
        super(player);

        this.entry = entry;
        this.parent = parent;

        title = CWStringHelper.localize(entry.getUnlocalizedName());

        if (entry instanceof IAddonEntry)
        {
            subtitle = CWStringHelper.localize(((IAddonEntry) entry).getSubtitle());
        }
        else
        {
            subtitle = null;
        }
    }

    @Override
    public void initGui()
    {
        super.initGui();

        current = this;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);

        SpellBookPage bookPage = entry.pages.get(page);
        bookPage.renderScreen(this, mouseX, mouseY);
    }

    @Override
    public void updateScreen()
    {
        super.updateScreen();

        SpellBookPage bookPage = entry.pages.get(page);
        bookPage.updateScreen();
    }

    @Override
    String getTitle()
    {
        return String.format("%s (%s/%s)", title, page + 1, entry.pages.size());
    }

    @Override
    String getSubtitle()
    {
        return subtitle;
    }

    @Override
    protected boolean isIndex()
    {
        return false;
    }

    /**
     * Gets the entry currently portrayed in this gui.
     */
    @Override
    public SpellBookEntry getEntry()
    {
        return null;
    }

    /**
     * Gets the current page the spell book GUI is browsing.
     */
    @Override
    public int getPageOn()
    {
        return 0;
    }

    /**
     * Gets the leftmost part of the GUI.
     */
    @Override
    public int getLeft()
    {
        return guiLeft;
    }

    /**
     * Gets the topmost part of the GUI.
     */
    @Override
    public int getTop()
    {
        return guiTop;
    }

    /**
     * Gets the GUI's width.
     */
    @Override
    public int getWidth()
    {
        return xSize;
    }

    /**
     * Gets the GUI's height
     */
    @Override
    public int getHeight()
    {
        return ySize;
    }

    /**
     * Gets the GUI's Z level for rendering.
     */
    @Override
    public float getZLevel()
    {
        return zLevel;
    }
}
