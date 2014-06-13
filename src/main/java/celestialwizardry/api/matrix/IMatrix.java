package celestialwizardry.api.matrix;

import celestialwizardry.api.energy.EnergyType;

import net.minecraft.item.ItemStack;

/**
 * This interface should be implemented by items that should be handled as matrixes.
 */
public interface IMatrix
{
    /**
     * Checks the {@link EnergyType} stored inside this matrix.
     *
     * @param stack the {@link ItemStack} to check the {@link EnergyType} from
     *
     * @return the {@link EnergyType} stored inside the {@link ItemStack}
     */
    public EnergyType getEnergyType(ItemStack stack);

    /**
     * Checks the amount of {@link EnergyType} stored inside this matrix.
     *
     * @param stack the {@link ItemStack} to check the amount from
     *
     * @return the amount of {@link EnergyType} stored inside the {@link ItemStack} as {@link Float}
     */
    public float getEnergyStored(ItemStack stack);

    /**
     * Tries to add given amount to {@link EnergyType} stored in this matrix.
     *
     * @param stack     the {@link ItemStack} to receive the {@link EnergyType}
     * @param type      the {@link EnergyType} that is being added
     * @param amount    the amount of {@link EnergyType} to add
     * @param transform if there is already another {@link EnergyType} stored, making this true allows matrix to try to
     *                  transform the new energy into existing one
     *
     * @return returns true if the {@link EnergyType} was successfully stored
     */
    public boolean appendEnergy(ItemStack stack, EnergyType type, float amount, boolean transform);

    /**
     * Tries to add given amount of already existing {@link EnergyType} to {@link EnergyType} stored in this matrix.
     *
     * @param stack  the {@link ItemStack} to receive the {@link EnergyType}
     * @param amount the amount of {@link EnergyType} to add
     *
     * @return returns true if the {@link EnergyType} was successfully stored
     */
    public boolean appendEnergy(ItemStack stack, float amount);

    /**
     * Tries to decrease given amount of {@link EnergyType} stored in this matrix.
     *
     * @param stack  the {@link ItemStack} to lose the {@link EnergyType}
     * @param type   the {@link EnergyType} that is being decreased
     * @param amount the amount of {@link EnergyType} to decrease
     *
     * @return returns true if the {@link EnergyType} was successfully decreased
     */
    public boolean decreaseEnergy(ItemStack stack, EnergyType type, float amount);

    /**
     * Tries to decrease given amount of already existing {@link EnergyType} stored in this matrix.
     *
     * @param stack  the {@link ItemStack} to lose the {@link EnergyType}
     * @param amount the amount of {@link EnergyType} to decrease
     *
     * @return returns true if the {@link EnergyType} was successfully decreased
     */
    public boolean decreaseEnergy(ItemStack stack, float amount);

    /**
     * Tries to set the matrix full of already existing {@link EnergyType}
     *
     * @param stack the {@link ItemStack} to be set full
     *
     * @return returns true if the {@link EnergyType} was successfully filled
     */
    public boolean setFull(ItemStack stack);

    /**
     * Tries to set the matrix full of given {@link EnergyType}
     *
     * @param stack the {@link ItemStack} to be set full
     * @param type  the {@link EnergyType} that the {@link ItemStack} is being filled with
     *
     * @return returns true if the {@link EnergyType} was successfully filled
     */
    public boolean setFull(ItemStack stack, EnergyType type);

    /**
     * Tries to set the matrix {@link EnergyType} to certain amount
     *
     * @param stack  the {@link ItemStack} to get {@link EnergyType} set
     * @param amount the amount of {@link EnergyType} to set
     *
     * @return returns true if the {@link EnergyType} was successfully set
     */
    public boolean setEnergy(ItemStack stack, float amount);

    /**
     * Tries to set the matrix {@link EnergyType} to certain amount
     *
     * @param stack  the {@link ItemStack} to get {@link EnergyType} set
     * @param amount the amount of {@link EnergyType} to set
     * @param type   the {@link EnergyType} that the {@link ItemStack} {@link EnergyType} is being set to
     *
     * @return returns true if the {@link EnergyType} was successfully set
     */
    public boolean setEnergy(ItemStack stack, float amount, EnergyType type);

    /**
     * Returns the maximum amount of {@link EnergyType} that can be stored inside this matrix
     *
     * @param stack the {@link ItemStack} to check the maximum amount of {@link EnergyType} from
     *
     * @return returns the maximum amount of {@link EnergyType} that can be stored inside this matrix
     */
    public float getMaxEnergy(ItemStack stack);
}
