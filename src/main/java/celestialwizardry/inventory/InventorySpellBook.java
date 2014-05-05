package celestialwizardry.inventory;

import celestialwizardry.reference.Names;
import celestialwizardry.util.INBTTaggable;
import celestialwizardry.util.NBTHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import java.util.UUID;

public class InventorySpellBook implements IInventory, INBTTaggable
{
    public ItemStack parentItemStack;
    protected ItemStack[] inventory;
    protected String customName;

    public InventorySpellBook(ItemStack stack)
    {
        parentItemStack = stack;

        int size = ContainerSpellBook.INVENTORY_ROWS * ContainerSpellBook.INVENTORY_COLUMNS;
        inventory = new ItemStack[size];

        readFromNBT(parentItemStack.getTagCompound());
    }

    public void onGuiSaved(EntityPlayer player)
    {
        parentItemStack = findParentItemStack(player);

        if (parentItemStack != null)
        {
            save();
        }
    }

    public ItemStack findParentItemStack(EntityPlayer player)
    {
        if (NBTHelper.hasUUID(parentItemStack))
        {
            UUID parentItemStackUUID = new UUID(parentItemStack.getTagCompound().getLong(Names.NBT.UUID_MOST_SIG),
                                                parentItemStack.getTagCompound().getLong(Names.NBT.UUID_LEAST_SIG));

            for (int i = 0; i < player.inventory.getSizeInventory(); i++)
            {
                ItemStack itemStack = player.inventory.getStackInSlot(i);

                if (NBTHelper.hasUUID(itemStack))
                {
                    if (itemStack.getTagCompound().getLong(Names.NBT.UUID_MOST_SIG) == parentItemStackUUID
                            .getMostSignificantBits()
                            && itemStack.getTagCompound().getLong(Names.NBT.UUID_LEAST_SIG) == parentItemStackUUID
                            .getLeastSignificantBits())
                    {
                        return itemStack;
                    }
                }
            }
        }

        return null;
    }

    public boolean matchesUUID(UUID uuid)
    {
        return NBTHelper.hasUUID(parentItemStack)
                && parentItemStack.getTagCompound().getLong(Names.NBT.UUID_LEAST_SIG) == uuid.getLeastSignificantBits()
                && parentItemStack.getTagCompound().getLong(Names.NBT.UUID_MOST_SIG) == uuid.getMostSignificantBits();
    }

    public void save()
    {
        NBTTagCompound tagCompound = parentItemStack.getTagCompound();

        if (tagCompound == null)
        {
            tagCompound = new NBTTagCompound();

            UUID uuid = UUID.randomUUID();
            tagCompound.setLong(Names.NBT.UUID_MOST_SIG, uuid.getMostSignificantBits());
            tagCompound.setLong(Names.NBT.UUID_LEAST_SIG, uuid.getLeastSignificantBits());
        }

        writeToNBT(tagCompound);
        parentItemStack.setTagCompound(tagCompound);
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
     * @param slot
     */
    @Override
    public ItemStack getStackInSlot(int slot)
    {
        return inventory[slot];
    }

    /**
     * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
     * new stack.
     *
     * @param slot
     * @param amount
     */
    @Override
    public ItemStack decrStackSize(int slot, int amount)
    {
        ItemStack itemStack = getStackInSlot(slot);
        if (itemStack != null)
        {
            if (itemStack.stackSize <= amount)
            {
                setInventorySlotContents(slot, null);
            }
            else
            {
                itemStack = itemStack.splitStack(amount);
                if (itemStack.stackSize == 0)
                {
                    setInventorySlotContents(slot, null);
                }
            }
        }

        return itemStack;
    }

    /**
     * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
     * like when you close a workbench GUI.
     *
     * @param slot
     */
    @Override
    public ItemStack getStackInSlotOnClosing(int slot)
    {
        if (inventory[slot] != null)
        {
            ItemStack itemStack = inventory[slot];
            inventory[slot] = null;
            return itemStack;
        }
        else
        {
            return null;
        }
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     *
     * @param slot
     * @param stack
     */
    @Override
    public void setInventorySlotContents(int slot, ItemStack stack)
    {
        inventory[slot] = stack;
    }

    /**
     * Returns the name of the inventory
     */
    @Override
    public String getInventoryName()
    {
        return this.hasCustomName() ? this.getCustomName() : Names.Containers.SPELL_BOOK;
    }

    public boolean hasCustomName()
    {
        return customName != null && customName.length() > 0;
    }

    public String getCustomName()
    {
        return customName;
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
     * For tile entities, ensures the chunk containing the tile entity is saved to disk later - the game won't think it
     * hasn't changed and skip it.
     */
    @Override
    public void markDirty()
    {

    }

    /**
     * Do not make give this method the name canInteractWith because it clashes with Container
     *
     * @param player
     */
    @Override
    public boolean isUseableByPlayer(EntityPlayer player)
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
     * @param slot
     * @param stack
     */
    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack)
    {
        return true; // TODO
    }

    @Override
    public void readFromNBT(NBTTagCompound tagCompound)
    {
        if (tagCompound != null && tagCompound.hasKey("Items"))
        {
            // Read in the ItemStacks in the inventory from NBT
            if (tagCompound.hasKey("Items"))
            {
                NBTTagList tagList = tagCompound.getTagList("Items", 10);
                inventory = new ItemStack[this.getSizeInventory()];

                for (int i = 0; i < tagList.tagCount(); ++i)
                {
                    NBTTagCompound nbtTagCompound = tagList.getCompoundTagAt(i);
                    byte slotIndex = nbtTagCompound.getByte("Slot");

                    if (slotIndex >= 0 && slotIndex < inventory.length)
                    {
                        inventory[slotIndex] = ItemStack.loadItemStackFromNBT(nbtTagCompound);
                    }
                }
            }

            // Read in any custom name for the inventory
            if (tagCompound.hasKey("display") && tagCompound.getTag("display").getClass().equals(NBTTagCompound.class))
            {
                if (tagCompound.getCompoundTag("display").hasKey("Name"))
                {
                    customName = tagCompound.getCompoundTag("display").getString("Name");
                }
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound)
    {
        // Write the ItemStacks in the inventory to NBT
        NBTTagList tagList = new NBTTagList();

        for (int currentIndex = 0; currentIndex < inventory.length; ++currentIndex)
        {
            if (inventory[currentIndex] != null)
            {
                NBTTagCompound nbtTagCompound = new NBTTagCompound();
                nbtTagCompound.setByte("Slot", (byte) currentIndex);
                inventory[currentIndex].writeToNBT(nbtTagCompound);
                tagList.appendTag(nbtTagCompound);
            }
        }

        tagCompound.setTag("Items", tagList);
    }
}
