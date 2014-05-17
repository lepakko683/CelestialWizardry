package celestialwizardry.inventory;

import celestialwizardry.item.ItemSpellBook;
import celestialwizardry.reference.Names;
import celestialwizardry.util.NBTHelper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerSpellBook extends Container
{
    public static final int INVENTORY_ROWS = 4;
    public static final int INVENTORY_COLUMNS = 12;

    private final EntityPlayer player;
    private final InventorySpellBook inventorySpellBook;

    // Player Inventory
    private final int PLAYER_INVENTORY_ROWS = 3;
    private final int PLAYER_INVENTORY_COLUMNS = 9;

    private int inventoryRows;
    private int inventoryColumns;

    public ContainerSpellBook(EntityPlayer player, InventorySpellBook inventorySpellBook)
    {
        this.player = player;
        this.inventorySpellBook = inventorySpellBook;

        inventoryRows = INVENTORY_ROWS;
        inventoryColumns = INVENTORY_COLUMNS;

        // Add the Spell Book slots to the container
        for (int rowIndex = 0; rowIndex < inventoryRows; ++rowIndex)
        {
            for (int columnIndex = 0; columnIndex < inventoryColumns; ++columnIndex)
            {
                this.addSlotToContainer(
                        new SlotSpellBook(this, inventorySpellBook, player, columnIndex + rowIndex * inventoryColumns,
                                          8 + columnIndex * 18, 18 + rowIndex * 18)
                                       );
            }
        }

        // Add the player's inventory slots to the container
        for (int inventoryRowIndex = 0; inventoryRowIndex < PLAYER_INVENTORY_ROWS; ++inventoryRowIndex)
        {
            for (int inventoryColumnIndex = 0; inventoryColumnIndex < PLAYER_INVENTORY_COLUMNS; ++inventoryColumnIndex)
            {
                this.addSlotToContainer(new Slot(player.inventory, inventoryColumnIndex + inventoryRowIndex * 9 + 9,
                                                 35 + inventoryColumnIndex * 18, 104 + inventoryRowIndex * 18));
            }
        }

        // Add the player's action bar slots to the container
        for (int actionBarSlotIndex = 0; actionBarSlotIndex < PLAYER_INVENTORY_COLUMNS; ++actionBarSlotIndex)
        {
            this.addSlotToContainer(new Slot(player.inventory, actionBarSlotIndex, 35 + actionBarSlotIndex * 18, 162));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return true;
    }

    @Override
    public void onContainerClosed(EntityPlayer player)
    {
        super.onContainerClosed(player);

        if (!player.worldObj.isRemote)
        {
            // We can probably do this better now considering the InventorySpellBook has a findParent method
            InventoryPlayer invPlayer = player.inventory;
            for (ItemStack itemStack : invPlayer.mainInventory)
            {
                if (itemStack != null)
                {
                    if (NBTHelper.hasTag(itemStack, Names.NBT.SPELL_BOOK_INVENTORY_OPEN))
                    {
                        NBTHelper.removeTag(itemStack, Names.NBT.SPELL_BOOK_INVENTORY_OPEN);
                    }
                }
            }

            saveInventory(player);
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

            // Attempt to shift click something from the inventory into the player inventory
            if (slotIndex < inventoryRows * inventoryColumns)
            {
                if (!this.mergeItemStack(itemStack, inventoryRows * inventoryColumns, inventorySlots.size(), false))
                {
                    return null;
                }
            }
            // Special case if we are dealing with an Spell being shift clicked
            else if (itemStack.getItem() instanceof ItemSpellBook)
            {
                // Attempt to shift click a bag from the player inventory into the hot bar inventory
                if (slotIndex < (inventoryRows * inventoryColumns) + (PLAYER_INVENTORY_ROWS * PLAYER_INVENTORY_COLUMNS))
                {
                    if (!this.mergeItemStack(itemStack, (inventoryRows * inventoryColumns) + (PLAYER_INVENTORY_ROWS
                            * PLAYER_INVENTORY_COLUMNS), inventorySlots.size(), false))
                    {
                        return null;
                    }
                }
                // Attempt to shift click a book from the hot bar inventory into the player inventory
                else if (!this.mergeItemStack(itemStack, inventoryRows * inventoryColumns,
                                              (inventoryRows * inventoryColumns) + (PLAYER_INVENTORY_ROWS
                                                      * PLAYER_INVENTORY_COLUMNS), false
                                             ))
                {
                    return null;
                }
            }
            // Attempt to shift click a valid into the bag inventory TODO Spells are valid
            else if (!this.mergeItemStack(itemStack, 0, inventoryRows * inventoryColumns, false))
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

    public void saveInventory(EntityPlayer player)
    {
        inventorySpellBook.onGuiSaved(player);
    }
}
