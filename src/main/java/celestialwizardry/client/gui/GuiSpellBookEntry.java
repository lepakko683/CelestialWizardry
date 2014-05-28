package celestialwizardry.client.gui;

import celestialwizardry.api.spellbook.SpellBookEntry;
import celestialwizardry.api.spellbook.SpellBookPage;
import celestialwizardry.api.spellbook.internal.IGuiSpellBookEntry;
import celestialwizardry.client.handler.ClientTickEventHandler;
import celestialwizardry.util.StringHelper;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiSpellBookEntry extends GuiSpellBook implements IGuiSpellBookEntry
{
    public int page = 0;
    SpellBookEntry entry;
    GuiScreen parent;
    String title;

    GuiButton leftButton;
    GuiButton rightButton;

    public GuiSpellBookEntry(SpellBookEntry entry, GuiScreen parent)
    {
        this.entry = entry;
        this.parent = parent;

        title = StringHelper.localize(entry.getUnlocalizedName());
    }

    @Override
    @SuppressWarnings("unchecked")
    public void initGui()
    {
        super.initGui();

        buttonList.add(new GuiButtonBackWithShift(0, left + xSize / 2 - 8, top + ySize + 2));
        buttonList.add(leftButton = new GuiButtonPage(1, left, top + ySize - 10, false));
        buttonList.add(rightButton = new GuiButtonPage(2, left + xSize - 18, top + ySize - 10, true));

        updatePageButtons();
    }

    @Override
    public SpellBookEntry getEntry()
    {
        return entry;
    }

    @Override
    public int getPageOn()
    {
        return page;
    }

    @Override
    boolean isIndex()
    {
        return false;
    }

    @Override
    void drawHeader()
    {
        // NO-OP
    }

    @Override
    String getTitle()
    {
        return String.format("%s (%s/%s)", title, page + 1, entry.pages.size());
    }

    @Override
    protected void actionPerformed(GuiButton button)
    {
        switch (button.id)
        {
            case 0:
                mc.displayGuiScreen(GuiScreen.isShiftKeyDown() ? new GuiSpellBook() : parent);
                ClientTickEventHandler.notifyPageChange();
                break;
            case 1:
                page--;
                ClientTickEventHandler.notifyPageChange();
                break;
            case 2:
                page++;
                ClientTickEventHandler.notifyPageChange();
                break;
        }

        updatePageButtons();
    }

    public void updatePageButtons()
    {
        leftButton.enabled = page != 0;
        rightButton.enabled = page + 1 < entry.pages.size();
    }

    @Override
    public void drawScreen(int par1, int par2, float par3)
    {
        super.drawScreen(par1, par2, par3);

        SpellBookPage page = entry.pages.get(this.page);
        page.renderScreen(this, par1, par2);
    }

    @Override
    public void updateScreen()
    {
        SpellBookPage page = entry.pages.get(this.page);
        page.updateScreen();
    }

    @Override
    public int getLeft()
    {
        return left;
    }

    @Override
    public int getTop()
    {
        return top;
    }

    @Override
    public int getWidth()
    {
        return xSize;
    }

    @Override
    public int getHeight()
    {
        return ySize;
    }

    @Override
    public float getZLevel()
    {
        return zLevel;
    }
}
