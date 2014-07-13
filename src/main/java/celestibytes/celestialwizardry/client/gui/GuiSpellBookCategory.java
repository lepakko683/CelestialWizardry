package celestibytes.celestialwizardry.client.gui;

import celestibytes.celestialwizardry.api.spellbook.SpellBookCategory;
import celestibytes.celestialwizardry.api.spellbook.SpellBookEntry;
import celestibytes.celestialwizardry.util.CWStringHelper;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.EnumChatFormatting;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.Collections;

@SideOnly(Side.CLIENT)
public class GuiSpellBookCategory extends GuiSpellBookGuide
{
    public SpellBookCategory category;
    String title;

    public ArrayList<SpellBookEntry> entries = new ArrayList<SpellBookEntry>();

    int page = 0; // TODO Check the page

    public GuiSpellBookCategory(InventoryPlayer player, SpellBookCategory category)
    {
        super(player);

        this.category = category;
        title = CWStringHelper.localize(category.getUnlocalizedName());
    }

    @Override
    @SuppressWarnings("unchecked")
    public void initGui()
    {
        super.initGui();

        entries.clear();
        entries.addAll(category.entries);
        Collections.sort(entries);

        /* int x = LIST_X;

        for (int i = 0; i < CATEGORIES_IN_COLUMN; i++)
        {
            int y = LIST_Y + i * CATEGORIES_IN_COLUMN;
            buttonList.add(new GuiButtonInvisible(i, guiLeft + x, guiTop + y, 110, 10, ""));
            range++;
        } */

        // TODO updatePageButtons();
        populateIndex();
    }

    @Override
    protected void populateIndex()
    {
        for (int i = CATEGORIES_IN_COLUMN * page; i < CATEGORIES_IN_COLUMN * (page + 1); i++)
        {
            GuiButtonInvisible button = (GuiButtonInvisible) buttonList.get(i - (CATEGORIES_IN_COLUMN * page));
            SpellBookEntry entry = i >= entries.size() ? null : entries.get(i);

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
    protected void actionPerformed(GuiButton button)
    {
        super.actionPerformedSkip(button);

        switch (button.id)
        {
            case 30:
                mc.displayGuiScreen(new GuiSpellBookGuide(player));
                flipPage();
                break;

            case 31:
                page--;
                // TODO updatePageButtons();
                populateIndex();
                flipPage();
                break;

            case 32:
                page++;
                // TODO updatePageButtons();
                populateIndex();
                flipPage();
                break;

            default:
                int index = button.id + page * CATEGORIES_IN_COLUMN;

                if (index >= entries.size())
                {
                    return;
                }

                SpellBookEntry entry = entries.get(index);
                mc.displayGuiScreen(new GuiSpellBookEntry(player, entry, this));
                flipPage();
                break;
        }
    }

    /* @Override
    protected boolean isIndex()
    {
        return false;
    } */

    @Override
    protected boolean customSecondPage()
    {
        return super.customSecondPage(); // TODO
    }
}
