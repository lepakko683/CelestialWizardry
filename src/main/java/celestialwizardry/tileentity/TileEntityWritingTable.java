package celestialwizardry.tileentity;

import celestialwizardry.item.ItemScroll;
import celestialwizardry.network.PacketHandler;
import celestialwizardry.network.message.MessageTileEntityWritingTable;
import celestialwizardry.reference.Names;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;

public class TileEntityWritingTable extends TileEntityCW implements IInventory
{
    public static final int INVENTORY_SIZE = 1;
    public static final int SCROLL_INVENTORY_INDEX = 0;

    private ItemStack[] inventory;

    public TileEntityWritingTable()
    {
        inventory = new ItemStack[INVENTORY_SIZE];
    }

    /**
     * Returns the number of slots in the inventory.
     */
    @Override
    public int getSizeInventory()
    {
        return inventory.length;
    }

    /**
     * Returns the stack in slot i
     *
     * @param var1
     */
    @Override
    public ItemStack getStackInSlot(int var1)
    {
        return inventory[var1];
    }

    /**
     * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
     * new stack.
     *
     * @param var1
     * @param var2
     */
    @Override
    public ItemStack decrStackSize(int var1, int var2)
    {
        ItemStack itemStack = getStackInSlot(var1);

        if (itemStack != null)
        {
            if (itemStack.stackSize <= var2)
            {
                setInventorySlotContents(var1, null);
            }
            else
            {
                itemStack = itemStack.splitStack(var2);

                if (itemStack.stackSize == 0)
                {
                    setInventorySlotContents(var1, null);
                }
            }
        }

        return itemStack;
    }

    /**
     * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
     * like when you close a workbench GUI.
     *
     * @param var1
     */
    @Override
    public ItemStack getStackInSlotOnClosing(int var1)
    {
        ItemStack itemStack = getStackInSlot(var1);

        if (itemStack != null)
        {
            setInventorySlotContents(var1, null);
        }

        return itemStack;
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     *
     * @param var1
     * @param var2
     */
    @Override
    public void setInventorySlotContents(int var1, ItemStack var2)
    {
        inventory[var1] = var2;

        if (var2 != null && var2.stackSize > getInventoryStackLimit())
        {
            var2.stackSize = getInventoryStackLimit();
        }
    }

    /**
     * Returns the name of the inventory
     */
    @Override
    public String getInventoryName()
    {
        return this.hasCustomName() ? this.getCustomName() : Names.Containers.WRITING_TABLE;
    }

    /**
     * Returns if the inventory is named
     */
    @Override
    public boolean hasCustomInventoryName()
    {
        return false;
    }

    /**
     * Returns the maximum stack size for a inventory slot.
     */
    @Override
    public int getInventoryStackLimit()
    {
        return 1;
    }

    /**
     * Do not make give this method the name canInteractWith because it clashes with Container
     *
     * @param var1
     */
    @Override
    public boolean isUseableByPlayer(EntityPlayer var1)
    {
        return true;
    }

    @Override
    public void openInventory()
    {

    }

    @Override
    public void closeInventory()
    {

    }

    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot.
     *
     * @param var1
     * @param var2
     */
    @Override
    public boolean isItemValidForSlot(int var1, ItemStack var2)
    {
        return var2.getItem() instanceof ItemScroll;
    }

    @Override
    public Packet getDescriptionPacket()
    {
        return PacketHandler.INSTANCE.getPacketFrom(new MessageTileEntityWritingTable(this));
    }
}
