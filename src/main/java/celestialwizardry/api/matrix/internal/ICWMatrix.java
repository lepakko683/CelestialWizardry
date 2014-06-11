package celestialwizardry.api.matrix.internal;

import net.minecraft.item.ItemStack;

public interface ICWMatrix
{
    public int getTier(ItemStack stack);

    public int getDamageForTier(int tier);

    public int getDamageFromTier(int tier);
}
