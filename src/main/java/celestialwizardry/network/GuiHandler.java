package celestialwizardry.network;

import celestialwizardry.client.gui.GuiSpellBook;
import celestialwizardry.client.gui.inventory.GuiSpellBookInventory;
import celestialwizardry.client.gui.inventory.GuiWritingTable;
import celestialwizardry.inventory.ContainerSpellBook;
import celestialwizardry.inventory.ContainerWritingTable;
import celestialwizardry.inventory.InventorySpellBook;
import celestialwizardry.reference.GuiIds;
import celestialwizardry.tileentity.TileEntityWritingTable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{
    /**
     * Returns a Server side Container to be displayed to the user.
     *
     * @param ID     The Gui ID Number
     * @param player The player viewing the Gui
     * @param world  The current world
     * @param x      X Position
     * @param y      Y Position
     * @param z      Z Position
     *
     * @return A GuiScreen/Container to be displayed to the user, null if none.
     */
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if (ID == GuiIds.WRITING_TABLE)
        {
            TileEntityWritingTable tileEntityWritingTable = (TileEntityWritingTable) world.getTileEntity(x, y, z);
            return new ContainerWritingTable(player.inventory, tileEntityWritingTable);
        }
        else if (ID == GuiIds.SPELL_BOOK)
        {
            // NOOP
        }
        else if (ID == GuiIds.SPELL_BOOK_INVENTORY)
        {
            return new ContainerSpellBook(player, new InventorySpellBook(player.getHeldItem()));
        }

        return null;
    }

    /**
     * Returns a Container to be displayed to the user. On the client side, this needs to return a instance of GuiScreen
     * On the server side, this needs to return a instance of Container
     *
     * @param ID     The Gui ID Number
     * @param player The player viewing the Gui
     * @param world  The current world
     * @param x      X Position
     * @param y      Y Position
     * @param z      Z Position
     *
     * @return A GuiScreen/Container to be displayed to the user, null if none.
     */
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if (ID == GuiIds.WRITING_TABLE)
        {
            TileEntityWritingTable tileEntityWritingTable = (TileEntityWritingTable) world.getTileEntity(x, y, z);
            return new GuiWritingTable(player.inventory, tileEntityWritingTable);
        }
        else if (ID == GuiIds.SPELL_BOOK)
        {
            return GuiSpellBook.currentOpen;
        }
        else if (ID == GuiIds.SPELL_BOOK_INVENTORY)
        {
            return new GuiSpellBookInventory(player, new InventorySpellBook(player.getHeldItem()));
        }

        return null;
    }
}