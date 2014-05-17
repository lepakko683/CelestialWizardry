package celestialwizardry.inventory;

import celestialwizardry.api.spell.ISpellContainer;
import celestialwizardry.item.ItemSpellBook;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotSpellBook extends Slot
{
    private final EntityPlayer player;
    private ContainerSpellBook containerSpellBook;

    public SlotSpellBook(ContainerSpellBook containerSpellBook, IInventory inventory, EntityPlayer player, int x, int y,
                         int z)
    {
        super(inventory, x, y, z);
        this.player = player;
        this.containerSpellBook = containerSpellBook;
    }

    @Override
    public void onSlotChange(ItemStack itemStack1, ItemStack itemStack2)
    {
        super.onSlotChange(itemStack1, itemStack2);
        containerSpellBook.saveInventory(player);
    }

    /**
     * Check if the stack is a valid item for this slot. Always true beside for the armor slots.
     */
    @Override
    public boolean isItemValid(ItemStack stack)
    {
        return !(stack.getItem() instanceof ItemSpellBook) && (stack.getItem() instanceof ISpellContainer);
    }
}
