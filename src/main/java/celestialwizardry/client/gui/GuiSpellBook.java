package celestialwizardry.client.gui;

import celestialwizardry.api.spellbook.SpellBookCategory;
import celestialwizardry.api.spellbook.SpellBookEntry;
import celestialwizardry.handler.ClientTickEventHandler;
import celestialwizardry.init.ModItems;
import celestialwizardry.reference.Resources;
import celestialwizardry.registry.SpellBookRegistry;
import celestialwizardry.util.StringHelper;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

import java.util.List;

@SideOnly(Side.CLIENT)
public class GuiSpellBook extends GuiScreen
{
    public static GuiSpellBook currentOpen = new GuiSpellBook();

    public static final String UNLOCALIZED_HEADER = "spellBook." + Resources.RESOURCE_PREFIX + "header";

    int xSize = 146;
    int ySize = 180;
    int left;
    int top;

    @Override
    @SuppressWarnings("unchecked")
    public void initGui()
    {
        super.initGui();

        currentOpen = this;

        left = width / 2 - xSize / 2;
        top = height / 2 - ySize / 2;

        buttonList.clear();

        if (isIndex())
        {
            int x = 18;

            for (int i = 0; i < 12; i++)
            {
                int y = 16 + i * 12;
                buttonList.add(new GuiButtonInvisible(i, left + x, top + y, 110, 10, ""));
            }

            populateIndex();
        }
    }

    @Override
    public void drawScreen(int par1, int par2, float par3)
    {
        GL11.glColor4f(1F, 1F, 1F, 1F);
        mc.renderEngine.bindTexture(Resources.Textures.GUI_SPELL_BOOK);
        drawTexturedModalRect(left, top, 0, 0, xSize, ySize);
        drawCenteredString(fontRendererObj, getTitle(), left + xSize / 2, top - 12, 0x00FF00);

        drawHeader();

        super.drawScreen(par1, par2, par3);
    }

    void drawHeader()
    {
        boolean unicode = fontRendererObj.getUnicodeFlag();
        fontRendererObj.setUnicodeFlag(true);
        fontRendererObj
                .drawSplitString(StringHelper.localize(UNLOCALIZED_HEADER), left + 15, top + 20, 110,
                                 0);
        fontRendererObj.setUnicodeFlag(unicode);
    }

    @Override
    protected void actionPerformed(GuiButton button)
    {
        int i = button.id - 3;

        if (i < 0)
        {
            return;
        }

        List<SpellBookCategory> categoryList = SpellBookRegistry.getAllCategories();
        SpellBookCategory category = i >= categoryList.size() ? null : categoryList.get(i);

        if (category != null)
        {
            mc.displayGuiScreen(new GuiSpellBookIndex(category));
            ClientTickEventHandler.notifyPageChange();
        }
    }

    @Override
    public boolean doesGuiPauseGame()
    {
        return false;
    }

    String getTitle()
    {
        return ModItems.spellBook.getItemStackDisplayName(null);
    }

    boolean isIndex()
    {
        return true;
    }

    void populateIndex()
    {
        List<SpellBookCategory> categoryList = SpellBookRegistry.getAllCategories();

        for (int i = 3; i < 12; i++)
        {
            int i_ = i - 3;
            GuiButtonInvisible button = (GuiButtonInvisible) buttonList.get(i);
            SpellBookCategory category = i_ >= categoryList.size() ? null : categoryList.get(i_);

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

    public static void setEntryToOpen(SpellBookEntry entry)
    {
        currentOpen = new GuiSpellBookEntry(entry, new GuiSpellBookIndex(entry.category));
    }
}
