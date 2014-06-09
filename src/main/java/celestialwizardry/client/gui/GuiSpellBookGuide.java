package celestialwizardry.client.gui;

import celestialwizardry.api.spellbook.SpellBookCategory;
import celestialwizardry.handler.ClientTickEventHandler;
import celestialwizardry.registry.SpellBookRegistry;
import celestialwizardry.util.StringHelper;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.InventoryPlayer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.List;

@SideOnly(Side.CLIENT)
public class GuiSpellBookGuide extends GuiSpellBook
{
    public static final int CATEGORIES_IN_COLUMN = 10;

    public GuiSpellBookGuide(InventoryPlayer player)
    {
        super(player);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void initGui()
    {
        super.initGui();

        current = this;

        if (isIndex())
        {
            // TODO Continue buttons on next page if we get that far

            int x = 14;

            for (int i = 0; i < CATEGORIES_IN_COLUMN; i++)
            {
                int y = 12 + i * 12;
                buttonList.add(new GuiButtonInvisible(buttonId++ /*i*/, guiLeft + x, guiTop + y, 110, 10, ""));
            }

            populateIndex();
        }
    }

    @Override
    protected boolean isIndex()
    {
        return true;
    }

    @Override
    protected BookState getState()
    {
        return BookState.GUIDE;
    }

    @Override
    protected void actionPerformed(GuiButton button)
    {
        super.actionPerformed(button);

        if (isIndex())
        {
            int i = button.id - bookmarks;

            if (i < 0)
            {
                return;
            }

            List<SpellBookCategory> categories = SpellBookRegistry.getAllCategories();
            SpellBookCategory category = i >= categories.size() ? null : categories.get(i);

            if (category != null)
            {
                mc.displayGuiScreen(new GuiSpellBookCategory(player, category));
                ClientTickEventHandler.notifyPageChange();
            }
        }
    }

    protected void populateIndex()
    {
        List<SpellBookCategory> categoryList = SpellBookRegistry.getAllCategories();

        for (int i = bookmarks; i < CATEGORIES_IN_COLUMN; i++)
        {
            int j = i - bookmarks;
            GuiButtonInvisible button = (GuiButtonInvisible) buttonList.get(i);
            SpellBookCategory category = j >= categoryList.size() ? null : categoryList.get(j);

            if (category != null)
            {
                button.displayString = StringHelper.localize(category.getUnlocalizedName());
            }
            else
            {
                button.displayString = "";
            }
        }
    }
}
