package celestialwizardry.crystal.api.matrix.internal;

import celestialwizardry.crystal.api.matrix.IMatrix;

import net.minecraft.item.ItemStack;

public interface ICWMatrix extends IMatrix
{
    public int getTier(ItemStack stack);

    public int getDamageForTier(int tier);

    public int getDamageFromTier(int tier);
}
