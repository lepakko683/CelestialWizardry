package celestibytes.celestialwizardry.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/**
 * Implemented by items that should be bound to one player and the lock must be broken with magic if someone else wants
 * to use or stole it
 */
public interface ILockedItem
{
    /**
     * Gives the owner of this item.
     *
     * @param stack the {@link ItemStack} to check the owner from
     *
     * @return the display name of the owner ({@link EntityPlayer})
     */
    public String getOwner(ItemStack stack);

    /**
     * Sets the owner for this item.
     *
     * @param stack  the {@link ItemStack} to set the owner to
     * @param player the {@link EntityPlayer} that is set as owner
     */
    public void setOwner(ItemStack stack, EntityPlayer player);

    /**
     * Checks does this item have owner.
     *
     * @param stack the {@link ItemStack} to check the owner from
     *
     * @return true if this item has owner
     */
    public boolean hasOwner(ItemStack stack);
}
