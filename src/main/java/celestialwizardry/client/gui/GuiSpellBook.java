package celestialwizardry.client.gui;

import celestialwizardry.api.spellbook.SpellBookCategory;
import celestialwizardry.inventory.ContainerSpellBook;
import celestialwizardry.reference.Resources;
import celestialwizardry.registry.SpellBookRegistry;
import celestialwizardry.util.StringHelper;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

import java.util.List;

@SideOnly(Side.CLIENT)
public class GuiSpellBook extends GuiContainer
{
    public BookState state;

    public GuiSpellBook(InventoryPlayer player)
    {
        super(new ContainerSpellBook(player));

        xSize = 216;
        ySize = 255;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void initGui()
    {
        super.initGui();

        state = BookState.INDEX;

        buttonList.clear();

        int x = 18;

        for (int i = 0; i < 12; i++)
        {
            int y = 16 + i * 12;
            buttonList.add(new GuiButtonInvisible(i, guiLeft + x, guiTop + y, 110, 10, ""));
        }

        populateIndex();
    }

    private void populateIndex()
    {
        List<SpellBookCategory> categoryList = SpellBookRegistry.getAllCategories();

        for (int i = 3; i < 12; i++)
        {
            int j = i - 3;
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

    @Override
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(Resources.Textures.GUI_SPELL_BOOK);
        int xStart = guiLeft;
        int yStart = guiTop;
        this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
    }

    @Override
    public boolean doesGuiPauseGame()
    {
        return false;
    }

    private static enum BookState
    {
        INDEX, CATEGORY, ENTRY
    }
}
