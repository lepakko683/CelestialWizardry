package celestialwizardry.client.gui.inventory;

import celestialwizardry.inventory.ContainerSpellBookOld;
import celestialwizardry.inventory.InventorySpellBookOld;
import celestialwizardry.reference.Names;
import celestialwizardry.reference.Resources;
import celestialwizardry.util.NBTHelper;
import celestialwizardry.util.StringHelper;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiSpellBookInventory extends GuiContainer
{
    private final ItemStack theBook;
    private final InventorySpellBookOld inventorySpellBookOld;

    public GuiSpellBookInventory(EntityPlayer player, InventorySpellBookOld inventorySpellBookOld)
    {
        super(new ContainerSpellBookOld(player, inventorySpellBookOld));

        this.theBook = inventorySpellBookOld.theBook;
        this.inventorySpellBookOld = inventorySpellBookOld;

        xSize = 230;
        ySize = 186;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
        fontRendererObj.drawString(StringHelper.localize(inventorySpellBookOld.getInventoryName()), 8, 6, 4210752);
        fontRendererObj
                .drawString(StringHelper.localize(Names.Containers.VANILLA_INVENTORY), 35, ySize - 95 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float opacity, int x, int y)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        this.mc.getTextureManager().bindTexture(Resources.Textures.GUI_SPELL_BOOK_INVENTORY);

        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
    }

    @Override
    public void onGuiClosed()
    {
        super.onGuiClosed();

        if (mc.thePlayer != null)
        {
            for (ItemStack itemStack : mc.thePlayer.inventory.mainInventory)
            {
                if (itemStack != null)
                {
                    if (NBTHelper.hasTag(itemStack, Names.NBT.SPELL_BOOK_INVENTORY_OPEN))
                    {
                        NBTHelper.removeTag(itemStack, Names.NBT.SPELL_BOOK_INVENTORY_OPEN);
                    }
                }
            }
        }
    }
}
