package celestialwizardry.tileentity;

import java.util.List;
import java.util.ArrayList;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import celestialwizardry.api.writing.IWriter;
import celestialwizardry.client.IRenderableObject;
import celestialwizardry.client.render.IRenderable;
import celestialwizardry.client.render.Renderables;
import celestialwizardry.network.PacketHandler;
import celestialwizardry.network.message.MessageTileEntityWritingTable;
import celestialwizardry.reference.Names;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBook;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.Packet;

public class TileEntityWritingTable extends TileEntityCW implements IInventory
{
    public static final int INVENTORY_SIZE = 5;
    public static final int INK_INVENTORY_INDEX = 0; // == LEFT_TOP or RIGHT_TOP
    public static final int PEN_INVENTORY_INDEX = 1;
    /**The current item on the middle of the table*/
    public static final int MIDDLE_INVENTORY_INDEX = 2;
    public static final int LEFT_TOP_INVENTORY_INDEX = 3;
    public static final int RIGHT_TOP_INVENTORY_INDEX = 4;

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
        ItemStack itemStack = getStackInSlot(slot);

        if (itemStack != null)
        {
            setInventorySlotContents(slot, null);
        }

        return itemStack;
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

        if (stack != null && stack.stackSize > getInventoryStackLimit())
        {
            stack.stackSize = getInventoryStackLimit();
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

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);
        NBTTagList nbttaglist = nbtTagCompound.getTagList("Items", 10);
        this.inventory = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.inventory.length)
            {
                this.inventory[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);

        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.inventory.length; ++i)
        {
            if (this.inventory[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte) i);
                this.inventory[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        nbtTagCompound.setTag("Items", nbttaglist);
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
        return (stack.getItem() instanceof IWriter);
    }

    @Override
    public Packet getDescriptionPacket()
    {
        return PacketHandler.INSTANCE.getPacketFrom(new MessageTileEntityWritingTable(this));
    }
    
    /**Checks whether it is possible to continue writing. Used when copying books if it should continue copying.*/
    public boolean canWrite() {
    	return false; //
    }
    
    public String getMainObjectType() {
    	return "book";
    }

    //Get data for rendering details TODO: organize into a single method 
    public IRenderableObject getInk()
    {
        return null;
    }

    @SideOnly(Side.CLIENT)
    public IRenderable getMainObject()
    {
    	if(this.inventory[MIDDLE_INVENTORY_INDEX] == null) {
    		return null;
    	}
    	Item it = this.inventory[MIDDLE_INVENTORY_INDEX].getItem();
    	if(it != null) {
    		System.out.println("helloo!");
    		if(it instanceof ItemBook) {
    			return Renderables.renderBookOnWritingTable;
    		}
    		return this.inventory[MIDDLE_INVENTORY_INDEX].getItem() instanceof IRenderable ? (IRenderable) this.inventory[MIDDLE_INVENTORY_INDEX].getItem() : null;
    	}
    	return null;
    	
    }

    public IRenderableObject getTopLeft()
    {
        return null;
    }

    public IRenderableObject getTopRight()
    {
        return null;
    }
    
    public List<IRenderableObject> getRenderables() 
    {
    	List<IRenderableObject> ret = new ArrayList<IRenderableObject>(3); //TODO: 3 + Items in the top slots
    	
    	return ret;
    }
}
