package celestibytes.celestialwizardry.crystal.api.matrix.internal;

import celestibytes.celestialwizardry.crystal.api.matrix.IMatrix;

import net.minecraft.item.ItemStack;

public interface ICWMatrix extends IMatrix
{
    public int getTier(ItemStack stack);

    public int getDamageForTier(int tier);

    public int getDamageFromTier(int tier);
}
