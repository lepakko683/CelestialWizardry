package celestibytes.celestialwizardry.client.gui.spellbookold;

import celestibytes.celestialwizardry.api.spellbook.SpellBookCategory;
import celestibytes.celestialwizardry.api.spellbook.SpellBookEntry;
import celestibytes.celestialwizardry.client.gui.GuiButtonInvisible;
import celestibytes.celestialwizardry.handler.ClientTickEventHandler;
import celestibytes.celestialwizardry.util.CWStringHelper;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.EnumChatFormatting;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SideOnly(Side.CLIENT)
@Deprecated
public class GuiSpellBookOldIndex extends GuiSpellBookOld
{
    SpellBookCategory category;
    String title;
    int page = 0;

    GuiButton leftButton;
    GuiButton rightButton;

    List<SpellBookEntry> entriesToDisplay = new ArrayList<SpellBookEntry>();

    public GuiSpellBookOldIndex(SpellBookCategory category)
    {
        this.category = category;
        title = CWStringHelper.localize(category.getUnlocalizedName());
    }

    @Override
    void drawHeader()
    {
        // NO-OP
    }

    @Override
    String getTitle()
    {
        return title;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void initGui()
    {
        super.initGui();
        buttonList.add(new GuiButtonBack(12, left + xSize / 2 - 8, top + ySize + 2));
        buttonList.add(leftButton = new GuiButtonPage(13, left, top + ySize - 10, false));
        buttonList.add(rightButton = new GuiButtonPage(14, left + xSize - 18, top + ySize - 10, true));

        entriesToDisplay.clear();
        entriesToDisplay.addAll(category.entries);
        Collections.sort(entriesToDisplay);

        updatePageButtons();
        populateIndex();
    }

    @Override
    void populateIndex()
    {
        for (int i = page * 12; i < (page + 1) * 12; i++)
        {
            GuiButtonInvisible button = (GuiButtonInvisible) buttonList.get(i - page * 12);
            SpellBookEntry entry = i >= entriesToDisplay.size() ? null : entriesToDisplay.get(i);

            if (entry != null)
            {
                button.displayString = (entry.isPriority() ? EnumChatFormatting.ITALIC : "") + CWStringHelper
                        .localize(entry.getUnlocalizedName());
            }
            else
            {
                button.displayString = "";
            }
        }
    }

    @Override
    protected void actionPerformed(GuiButton par1GuiButton)
    {
        switch (par1GuiButton.id)
        {
            case 12:
                mc.displayGuiScreen(new GuiSpellBookOld());
                ClientTickEventHandler.notifyPageChange(true);
                break;
            case 13:
                page--;
                updatePageButtons();
                populateIndex();
                ClientTickEventHandler.notifyPageChange(true);
                break;
            case 14:
                page++;
                updatePageButtons();
                populateIndex();
                ClientTickEventHandler.notifyPageChange(true);
                break;
            default:
                int index = par1GuiButton.id + page * 12;
                if (index >= entriesToDisplay.size())
                {
                    return;
                }

                SpellBookEntry entry = entriesToDisplay.get(index);
                mc.displayGuiScreen(new GuiSpellBookOldEntry(entry, this));
                ClientTickEventHandler.notifyPageChange(true);
        }
    }

    public void updatePageButtons()
    {
        leftButton.enabled = page != 0;
        rightButton.enabled = page < (entriesToDisplay.size() - 1) / 12;
    }
}
