package celestibytes.celestialwizardry.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerSpellBook extends Container
{
    // private final EntityPlayer player;

    // Player Inventory
    private final int PLAYER_INVENTORY_ROWS = 3;
    private final int PLAYER_INVENTORY_COLUMNS = 9;

    public ContainerSpellBook(InventoryPlayer inventoryPlayer)
    {
        // this.player = player;

        // Add the player's inventory slots to the container
        for (int inventoryRowIndex = 0; inventoryRowIndex < PLAYER_INVENTORY_ROWS; ++inventoryRowIndex)
        {
            for (int inventoryColumnIndex = 0; inventoryColumnIndex < PLAYER_INVENTORY_COLUMNS; ++inventoryColumnIndex)
            {
                this.addSlotToContainer(new Slot(inventoryPlayer, inventoryColumnIndex + inventoryRowIndex * 9 + 9,
                                                 29 + inventoryColumnIndex * 18, 174 + inventoryRowIndex * 18));
            }
        }

        // Add the player's action bar slots to the container
        for (int actionBarSlotIndex = 0; actionBarSlotIndex < PLAYER_INVENTORY_COLUMNS; ++actionBarSlotIndex)
        {
            this.addSlotToContainer(new Slot(inventoryPlayer, actionBarSlotIndex, 29 + actionBarSlotIndex * 18, 232));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slot)
    {
        return super.transferStackInSlot(player, slot); // TODO
    }

    @Override //mouseButtonNumber 0 = left, 1 = right, 2 = wheel/middle
    public ItemStack slotClick(int slotIndex, int mouseButtonNumber, int par3, EntityPlayer par4EntityPlayer)
    {
//    	System.out.println("As binary string: " + Integer.toBinaryString(par3));
//    	System.out.println("par1: " + slotIndex + " par2: " + mouseButtonNumber + " par3: " + par3);
//    	System.out.println("world remote: " + (par4EntityPlayer != null ? par4EntityPlayer.worldObj.isRemote :
// "playerNull"));
        return super.slotClick(slotIndex, mouseButtonNumber, par3, par4EntityPlayer);
    }
}
