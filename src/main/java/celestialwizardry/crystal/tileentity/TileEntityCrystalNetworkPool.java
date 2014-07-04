package celestialwizardry.crystal.tileentity;

import celestialwizardry.api.energy.EnergyType;
import celestialwizardry.crystal.api.crystal.EnergyPacket;
import celestialwizardry.crystal.api.crystal.ICrystalNetworkPool;
import celestialwizardry.crystal.reference.CrystalNames;
import celestialwizardry.crystal.util.PacketBuilder;
import celestialwizardry.util.LogHelper;
import celestialwizardry.util.MathHelper;

import net.minecraft.nbt.NBTTagCompound;

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
    public EnergyPacket takePacket(float amount)
    {
        PacketBuilder builder = getBuilder();

        builder.setEnergyType(getEnergyType());
        builder.append(amount);

        pool = MathHelper.clampZero_float(getPoolSize() - amount, getMaxPoolSize());

        return builder.toPacket();
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

    /* ======================================== TileEntity START ===================================== */

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);
        pool = nbtTagCompound.getFloat(CrystalNames.NBT.POOL);
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);
        nbtTagCompound.setFloat(CrystalNames.NBT.POOL, pool);
    }

    /* ======================================== TileEntity END ===================================== */

    /* ======================================== TileEntityCrystal START ===================================== */

    @Override
    public PacketBuilder getBuilder()
    {
        return new PacketBuilder(getMaxPoolSize() / 10);
    }

    /* ======================================== TileEntityCrystal END ===================================== */
}
