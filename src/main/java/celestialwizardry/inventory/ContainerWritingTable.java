package celestialwizardry.inventory;

import celestialwizardry.api.writing.IWriter;
import celestialwizardry.tileentity.TileEntityWritingTable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerWritingTable extends Container
{
    private final int PLAYER_INVENTORY_ROWS = 3;
    private final int PLAYER_INVENTORY_COLUMNS = 9;

    public ContainerWritingTable(InventoryPlayer inventoryPlayer, TileEntityWritingTable tileEntityWritingTable)
    {
        this.addSlotToContainer(new SlotInk(tileEntityWritingTable, TileEntityWritingTable.INK_INVENTORY_INDEX, 8, 8));

        // Add the player's inventory slots to the container
        for (int inventoryRowIndex = 0; inventoryRowIndex < PLAYER_INVENTORY_ROWS; ++inventoryRowIndex)
        {
            for (int inventoryColumnIndex = 0; inventoryColumnIndex < PLAYER_INVENTORY_COLUMNS; ++inventoryColumnIndex)
            {
                this.addSlotToContainer(new Slot(inventoryPlayer, inventoryColumnIndex + inventoryRowIndex * 9 + 9,
                                                 30 + inventoryColumnIndex * 18, 118 + inventoryRowIndex * 18));
            }
        }

        // Add the player's action bar slots to the container
        for (int actionBarSlotIndex = 0; actionBarSlotIndex < PLAYER_INVENTORY_COLUMNS; ++actionBarSlotIndex)
        {
            this.addSlotToContainer(new Slot(inventoryPlayer, actionBarSlotIndex, 30 + actionBarSlotIndex * 18, 176));
        }
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex)
    {
        ItemStack newItemStack = null;
        Slot slot = (Slot) inventorySlots.get(slotIndex);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemStack = slot.getStack();
            newItemStack = itemStack.copy();

            // Attempt to shift click something from the ink inventory into the player inventory
            if (slotIndex < TileEntityWritingTable.INVENTORY_SIZE)
            {
                if (!this
                        .mergeItemStack(itemStack, TileEntityWritingTable.INVENTORY_SIZE, inventorySlots.size(), false))
                {
                    return null;
                }
            }
            // Special case if we are dealing with an non-valid being shift clicked
            else if (!(itemStack.getItem() instanceof IWriter))
            {
                // Attempt to shift click a item from the player inventory into the hot bar inventory
                if (slotIndex < (TileEntityWritingTable.INVENTORY_SIZE) + (PLAYER_INVENTORY_ROWS
                        * PLAYER_INVENTORY_COLUMNS))
                {
                    if (!this.mergeItemStack(itemStack, (TileEntityWritingTable.INVENTORY_SIZE) + (PLAYER_INVENTORY_ROWS
                            * PLAYER_INVENTORY_COLUMNS), inventorySlots.size(), false))
                    {
                        return null;
                    }
                }
                // Attempt to shift click a item from the hot bar inventory into the player inventory
                else if (!this.mergeItemStack(itemStack, TileEntityWritingTable.INVENTORY_SIZE,
                                              (TileEntityWritingTable.INVENTORY_SIZE) + (PLAYER_INVENTORY_ROWS
                                                      * PLAYER_INVENTORY_COLUMNS), false
                                             ))
                {
                    return null;
                }
            }
            // Attempt to shift click a ink into the ink inventory
            else if (!this.mergeItemStack(itemStack, 0, TileEntityWritingTable.INVENTORY_SIZE, false))
            {
                return null;
            }

            if (itemStack.stackSize == 0)
            {
                slot.putStack(null);
            }
            else
            {
                slot.onSlotChanged();
            }
        }

        return newItemStack;
    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return true;
    }
}
