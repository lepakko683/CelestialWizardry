package celestialwizardry.client.gui;

import celestialwizardry.api.spellbook.SpellBookCategory;
import celestialwizardry.handler.ClientTickEventHandler;
import celestialwizardry.inventory.ContainerSpellBook;
import celestialwizardry.reference.Resources;
import celestialwizardry.registry.SpellBookRegistry;
import celestialwizardry.util.Colour;
import celestialwizardry.util.StringHelper;
import net.minecraft.client.gui.GuiButton;
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
    public InventoryPlayer player;

    public static final int CATEGORIES_IN_COLUMN = 10;
    private static final Colour COLOR_GUIDES = new Colour(1f,0f,0f);

    protected GuiButton guide;

    public GuiSpellBook(InventoryPlayer player)
    {
        super(new ContainerSpellBook(player));

        this.player = player;

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

        if (state == BookState.INDEX)
        {
            // TODO Continue buttons on next page if we get that far

            int x = 14;

            for (int i = 0; i < CATEGORIES_IN_COLUMN; i++)
            {
                int y = 12 + i * 12;
                buttonList.add(new GuiButtonInvisible(i, guiLeft + x, guiTop + y, 110, 10, ""));
            }

            populateIndex();
        }

        guide = new GuiButtonInvisible(100, guiLeft - 48 + 8 + 2, guiTop + 20, 48, 19, "Guide");
        guide.displayString = "Guide";

        buttonList.add(guide);
    }

    protected void populateIndex()
    {
        List<SpellBookCategory> categoryList = SpellBookRegistry.getAllCategories();

        for (int i = 3; i < CATEGORIES_IN_COLUMN; i++)
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
    protected void actionPerformed(GuiButton button)
    {
        int i = button.id - 3;

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

    @Override
    protected void drawGuiContainerBackgroundLayer(float parTick, int mouse_x, int mouse_y)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(Resources.Textures.GUI_SPELL_BOOK);
        int xStart = guiLeft;
        int yStart = guiTop;
        this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, 160);
        this.drawTexturedModalRect(xStart, yStart + 166, 0, 166, 197, ySize - 165);
        COLOR_GUIDES.setGLColor();
        this.drawTexturedModalRect(xStart - 48 + 8, yStart + 20, 200, 190, 48, 19);
    }

    @Override
    public boolean doesGuiPauseGame()
    {
        return false;
    }

    protected static enum BookState
    {
        INDEX,
        CATEGORY,
        ENTRY
    }
}
