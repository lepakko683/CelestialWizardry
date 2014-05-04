package celestialwizardry.inventory;

import celestialwizardry.tileentity.TileEntityWritingTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class ContainerWritingTable extends Container
{
    private final int PLAYER_INVENTORY_ROWS = 3;
    private final int PLAYER_INVENTORY_COLUMNS = 9;

    public ContainerWritingTable(InventoryPlayer inventoryPlayer, TileEntityWritingTable tileEntityWritingTable)
    {
        this.addSlotToContainer(new Slot(tileEntityWritingTable, TileEntityWritingTable.SCROLL_INVENTORY_INDEX, 8, 8));

        // Add the player's inventory slots to the container
        for (int inventoryRowIndex = 0; inventoryRowIndex < PLAYER_INVENTORY_ROWS; ++inventoryRowIndex)
        {
            for (int inventoryColumnIndex = 0; inventoryColumnIndex < PLAYER_INVENTORY_COLUMNS; ++inventoryColumnIndex)
            {
                this.addSlotToContainer(new Slot(inventoryPlayer, inventoryColumnIndex + inventoryRowIndex * 9 + 9, 30 + inventoryColumnIndex * 18, 118 + inventoryRowIndex * 18));
            }
        }

        // Add the player's action bar slots to the container
        for (int actionBarSlotIndex = 0; actionBarSlotIndex < PLAYER_INVENTORY_COLUMNS; ++actionBarSlotIndex)
        {
            this.addSlotToContainer(new Slot(inventoryPlayer, actionBarSlotIndex, 30 + actionBarSlotIndex * 18, 176));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer var1)
    {
        return true;
    }
}
