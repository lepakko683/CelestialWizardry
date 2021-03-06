package celestibytes.celestialwizardry.client.gui.inventory;

import celestibytes.celestialwizardry.inventory.ContainerWritingTable;
import celestibytes.celestialwizardry.reference.Names;
import celestibytes.celestialwizardry.reference.Resources;
import celestibytes.celestialwizardry.tileentity.TileEntityWritingTable;
import celestibytes.celestialwizardry.util.CWStringHelper;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiWritingTable extends GuiContainer
{
    private TileEntityWritingTable tileEntityWritingTable;

    public GuiWritingTable(InventoryPlayer inventoryPlayer, TileEntityWritingTable tileEntityWritingTable)
    {
        super(new ContainerWritingTable(inventoryPlayer, tileEntityWritingTable));
        this.tileEntityWritingTable = tileEntityWritingTable;
        xSize = 220;
        ySize = 200;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
        String containerName = CWStringHelper.localize(tileEntityWritingTable.getInventoryName());
        fontRendererObj
                .drawString(containerName, xSize / 2 - fontRendererObj.getStringWidth(containerName) / 2, 6, 4210752);
        fontRendererObj.drawString(CWStringHelper.localize(Names.Containers.VANILLA_INVENTORY), 8, ySize - 93, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(Resources.Textures.GUI_WRITING_TABLE);
        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
    }
}
