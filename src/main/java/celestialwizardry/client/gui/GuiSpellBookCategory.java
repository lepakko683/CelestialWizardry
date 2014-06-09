package celestialwizardry.client.gui;

import celestialwizardry.api.spellbook.SpellBookCategory;
import celestialwizardry.api.spellbook.SpellBookEntry;
import celestialwizardry.util.StringHelper;

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

    public ArrayList<SpellBookEntry> entries = new ArrayList<SpellBookEntry>();

    int page = 0; // TODO Check the page

    public GuiSpellBookCategory(InventoryPlayer player, SpellBookCategory category)
    {
        super(player);

        this.category = category;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void initGui()
    {
        super.initGui();

        entries.clear();
        entries.addAll(category.entries);
        Collections.sort(entries);

        int x = 14;

        for (int i = 0; i < CATEGORIES_IN_COLUMN; i++)
        {
            // TODO Continue buttons on next page if we get that far

            int y = 12 + i * 12;
            buttonList.add(new GuiButtonInvisible(buttonId++ /*i*/, guiLeft + x, guiTop + y, 110, 10, ""));
        }

        populateIndex();
    }

    @Override
    protected void populateIndex()
    {
        for (int i = bookmarks + (CATEGORIES_IN_COLUMN * page); i < CATEGORIES_IN_COLUMN * (page + 1); i++)
        {
            int j = i - bookmarks;
            GuiButtonInvisible button = (GuiButtonInvisible) buttonList.get(i - (CATEGORIES_IN_COLUMN * page));
            SpellBookEntry entry = j >= entries.size() ? null : entries.get(j);

            if (entry != null)
            {
                button.displayString = (entry.isPriority() ? EnumChatFormatting.ITALIC : "") + StringHelper
                        .localize(entry.getUnlocalizedName());
            }
            else
            {
                button.displayString = "";
            }
        }
    }

    @Override
    protected boolean isIndex()
    {
        return false;
    }
}
