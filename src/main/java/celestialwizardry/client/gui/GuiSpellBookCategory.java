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
public class GuiSpellBookCategory extends GuiSpellBook
{
    public SpellBookCategory category;

    public ArrayList<SpellBookEntry> entries = new ArrayList<SpellBookEntry>();

    int page = 0;

    public GuiSpellBookCategory(InventoryPlayer player, SpellBookCategory category)
    {
        super(player);

        this.category = category;
    }

    @Override
    public void initGui()
    {
        state = BookState.CATEGORY;
        super.initGui();

        entries.clear();
        entries.addAll(category.entries);
        Collections.sort(entries);
    }

    @Override
    protected void populateIndex()
    {
        for (int i = page * CATEGORIES_IN_COLUMN; i < (page + 1) * CATEGORIES_IN_COLUMN; i++)
        {
            GuiButtonInvisible button = (GuiButtonInvisible) buttonList.get(i - page * CATEGORIES_IN_COLUMN);
            SpellBookEntry entry = i >= entries.size() ? null : entries.get(i);

            if (entry != null)
            {
                button.displayString = (entry.isPriority() ? EnumChatFormatting.ITALIC : "") + StringHelper.localize(
                        entry.getUnlocalizedName());
            }
            else
            {
                button.displayString = "";
            }
        }
    }
}
