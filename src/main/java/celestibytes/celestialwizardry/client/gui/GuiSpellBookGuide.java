package celestibytes.celestialwizardry.client.gui;

import celestibytes.celestialwizardry.api.spellbook.SpellBookCategory;
import celestibytes.celestialwizardry.registry.SpellBookRegistry;
import celestibytes.celestialwizardry.util.CWStringHelper;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.InventoryPlayer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

import java.util.List;

@SideOnly(Side.CLIENT)
public class GuiSpellBookGuide extends GuiSpellBook
{
    protected static final int CATEGORIES_IN_COLUMN = 10;
    protected static final int LIST_X = 14;
    protected static final int LIST_Y = 35;

    public GuiSpellBookGuide(InventoryPlayer player)
    {
        super(player, "Guide");
    }

    @Override
    @SuppressWarnings("unchecked")
    public void initGui()
    {
        super.initGui();

        current = this;

        if (isIndex())
        {
            int x = LIST_X;

            for (int i = 0; i < CATEGORIES_IN_COLUMN; i++)
            {
                int y = LIST_Y + (i * CATEGORIES_IN_COLUMN);
                buttonList.add(new GuiButtonInvisible(i, guiLeft + x, guiTop + y, 110, 10, ""));
            }

            populateIndex();
        }
    }

    protected void populateIndex()
    {
        List<SpellBookCategory> categoryList = SpellBookRegistry.getAllCategories();

        for (int i = anInteger; i < CATEGORIES_IN_COLUMN; i++)
        {
            int j = i - anInteger;
            GuiButtonInvisible button = (GuiButtonInvisible) buttonList.get(i);
            SpellBookCategory category = j >= categoryList.size() ? null : categoryList.get(j);

            if (category != null)
            {
                button.displayString = CWStringHelper.localize(category.getUnlocalizedName());
            }
            else
            {
                button.displayString = "";
            }
        }
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);

        int x = guiLeft + LIST_X; // guiLeft + width / 2;
        int y = LIST_Y - getTitleHeight(); // guiTop - getTitleHeight();

        drawCenteredString(fontRendererObj, getTitle(), x, y, 0x00FF00);

        String subtitle = getSubtitle();

        if (subtitle != null)
        {
            // x = guiLeft * 2 + width;
            y = y + 10; // (guiTop - getTitleHeight() + 10) * 2;
            GL11.glScalef(0.5F, 0.5F, 1F);
            drawCenteredString(fontRendererObj, subtitle, x, y, 0x00FF00);
            GL11.glScalef(2F, 2F, 1F);
        }
    }

    String getTitle()
    {
        return "Guide"; // TODO
    }

    String getSubtitle()
    {
        return null;
    }

    int getTitleHeight()
    {
        return getSubtitle() == null ? 12 : 16;
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
    protected boolean customSecondPage()
    {
        return false;
    }

    @Override
    protected void actionPerformed(GuiButton button)
    {
        super.actionPerformed(button);

        int i = button.id - anInteger;

        if (i < 0)
        {
            return;
        }

        List<SpellBookCategory> categories = SpellBookRegistry.getAllCategories();
        SpellBookCategory category = i >= categories.size() ? null : categories.get(i);

        if (category != null)
        {
            mc.displayGuiScreen(new GuiSpellBookCategory(player, category));
            flipPage();
        }
    }

    protected void actionPerformedSkip(GuiButton button)
    {
        super.actionPerformed(button);
    }
}
