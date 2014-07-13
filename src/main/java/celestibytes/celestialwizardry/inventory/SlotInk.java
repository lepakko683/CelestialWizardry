package celestibytes.celestialwizardry.inventory;

import celestibytes.celestialwizardry.api.writing.IWriter;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotInk extends Slot
{
    public SlotInk(IInventory iInventory, int x, int y, int z)
    {
        super(iInventory, x, y, z);
    }

    @Override
    public void onSlotChange(ItemStack itemStack1, ItemStack itemStack2)
    {
        super.onSlotChange(itemStack1, itemStack2);
    }

    /**
     * Check if the stack is a valid item for this slot. Always true beside for the armor slots.
     */
    @Override
    public boolean isItemValid(ItemStack stack)
    {
        return (stack.getItem() instanceof IWriter);
    }
}
