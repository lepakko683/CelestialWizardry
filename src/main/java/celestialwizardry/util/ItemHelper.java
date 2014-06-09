package celestialwizardry.util;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public final class ItemHelper
{
    private ItemHelper()
    {

    }

    /**
     * This prevents an overridden getDamage() call from messing up metadata acquisition.
     */
    public static int getItemDamage(ItemStack stack)
    {
        return Items.diamond.getDamage(stack);
    }
}
