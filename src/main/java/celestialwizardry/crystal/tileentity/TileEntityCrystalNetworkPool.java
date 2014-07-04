package celestialwizardry.crystal.tileentity;

import celestialwizardry.api.energy.EnergyType;
import celestialwizardry.crystal.api.crystal.ICrystalNetworkPool;
import celestialwizardry.crystal.util.PacketBuilder;
import celestialwizardry.util.LogHelper;
import celestialwizardry.util.MathHelper;

public abstract class TileEntityCrystalNetworkPool extends TileEntityCrystalNetwork implements ICrystalNetworkPool
{
    protected EnergyType currentEnergy;
    protected float pool = 0F;

    /* ======================================== ICrystalNetworkPool START ===================================== */

    @Override
    public EnergyType getEnergyType()
    {
        return currentEnergy;
    }

    @Override
    public float getPoolSize()
    {
        return pool;
    }

    /**
     * Takes energy from the {@link celestialwizardry.crystal.api.crystal.ICrystalNetworkPool}'s {@link
     * celestialwizardry.api.energy.EnergyType} pool
     *
     * @param amount the requested amount of {@link celestialwizardry.api.energy.EnergyType}
     *
     * @return returns the amount of {@link celestialwizardry.api.energy.EnergyType} the {@link
     * celestialwizardry.crystal.api.crystal.ICrystalNetworkPool} can give
     */
    @Override
    public float takeEnergy(float amount)
    {
        return 0;
    }

    /**
     * Adds {@link EnergyType} to the pool
     *
     * @param amount the amount that will be added
     */
    @Override
    public boolean addEnergy(float amount, EnergyType type)
    {
        if (!acceptableEnergies(worldObj).contains(type))
        {
            return false;
        }

        if (!getEnergyType().equals(type))
        {
            // TODO Handle transformation
        }

        pool = MathHelper.clampZero_float(getPoolSize() + amount, getMaxPoolSize());
        LogHelper.debug("Added " + String.valueOf(amount) + " energy to " + toString());

        return true;
    }

    /* ======================================== ICrystalNetworkPool END ===================================== */

    /* ======================================== TileEntityCrystal START ===================================== */

    @Override
    public PacketBuilder getBuilder()
    {
        return new PacketBuilder(getMaxPoolSize() / 10);
    }

    /* ======================================== TileEntityCrystal END ===================================== */
}
