package celestialwizardry.client.gui;

import celestialwizardry.handler.ClientTickEventHandler;
import celestialwizardry.inventory.ContainerSpellBook;
import celestialwizardry.reference.Resources;
import celestialwizardry.util.Colour;
import celestialwizardry.util.StringHelper;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public abstract class GuiSpellBook extends GuiContainer
{
    public GuiSpellBook current;

    public BookState state;
    public InventoryPlayer player;

    private static final Colour COLOR_GUIDES = new Colour(1f, 0f, 0f);
    private static final Colour COLOR_NOTES = new Colour(0f, 1f, 0f);
    private static final Colour COLOR_SPELLS = new Colour(0f, 0f, 1f);

    protected GuiButton guide;
    protected GuiButton notes;
    protected GuiButton spells;

    protected int anInteger; // TODO Come up with better name for this :P

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

        buttonList.clear();

        guide = new GuiButtonInvisible(100, guiLeft - 48 + 8 * 2, guiTop + 20 + 2, 48, 19,
                                       StringHelper.localize("bookmark." + Resources.RESOURCE_PREFIX + "guide"));
        buttonList.add(guide);

        notes = new GuiButtonInvisible(101, guiLeft - 48 + 8 * 2, guiTop + 40 + 2, 48, 19,
                                       StringHelper.localize("bookmark." + Resources.RESOURCE_PREFIX + "notes"));
        buttonList.add(notes);

        spells = new GuiButtonInvisible(102, guiLeft - 48 + 8 * 2, guiTop + 60 + 2, 48, 19,
                                        StringHelper.localize("bookmark." + Resources.RESOURCE_PREFIX + "spells"));
        buttonList.add(spells);

        anInteger = 3;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTick, int mouseX, int mouseY)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(Resources.Textures.GUI_SPELL_BOOK);
        int xStart = guiLeft;
        int yStart = guiTop;

        // Render guides - bookmark
        COLOR_GUIDES.setGLColor();
        this.drawTexturedModalRect(xStart - 48 + 8, yStart + 20, 200, 190, 48, 19);
        
        // Render notes - bookmark
        COLOR_NOTES.setGLColor();
        this.drawTexturedModalRect(xStart - 48 + 8, yStart + 40, 200, 190, 48, 19);
        
        // Render spells - bookmark
        COLOR_SPELLS.setGLColor();
        this.drawTexturedModalRect(xStart - 48 + 8, yStart + 60, 200, 190, 48, 19);

        Colour.resetGLColor();
        
        // Render book
        this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, 160);
        
        // Render player inventory
        this.drawTexturedModalRect(xStart, yStart + 166, 0, 166, 197, ySize - 165);
        
        this.mc.getTextureManager().bindTexture(Resources.Textures.GUI_SPELL_BOOK_PAGES);
        this.drawTexturedModalRect(xStart+11, yStart+14, 11, 13, 196, 141);
    }
    //this.drawTexturedModalRect(x, y, u, v, width, height);
    @Override
    protected void actionPerformed(GuiButton button)
    {
        // super.actionPerformed(button);

        switch (button.id)
        {
            case 100:
                mc.displayGuiScreen(new GuiSpellBookGuide(player));
                flipPage();
                break;

            case 101:
                mc.displayGuiScreen(new GuiSpellBookNotes(player));
                flipPage();
                break;

            case 102:
                mc.displayGuiScreen(new GuiSpellBookSpells(player));
                flipPage();
                break;

            default:
                // Debug
                flipPage();
                break;
        }
    }
    
    @Override
    protected void mouseMovedOrUp(int p_146286_1_, int p_146286_2_, int which) {
    	System.out.println("Mouse up: " + (which==0 || which == 1));
    	super.mouseMovedOrUp(p_146286_1_, p_146286_2_, which);
    }

    @Override
    public boolean doesGuiPauseGame()
    {
        return false;
    }

    protected void flipPage()
    {
        ClientTickEventHandler.notifyPageChange(false);
    }

    protected abstract boolean isIndex();

    protected abstract BookState getState();

    protected abstract boolean customSecondPage();

    protected static enum BookState
    {
        GUIDE,
        NOTES,
        SPELLS
    }
}
