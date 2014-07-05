package celestialwizardry.crystal.tileentity;

import celestialwizardry.api.energy.EnergyType;
import celestialwizardry.crystal.api.crystal.EnergyPacket;
import celestialwizardry.crystal.api.crystal.ICrystalNetworkBuffer;
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
    protected ICrystalNetworkBuffer dest = null;

    /* ======================================== ICrystalNetwork START ===================================== */

    /**
     * Sends a {@link EnergyPacket} to the target {@link celestialwizardry.crystal.api.crystal.ICrystal}
     */
    @Override
    public void sendPacket()
    {
        if (dest != null)
        {
            PacketBuilder builder = getBuilder();

            builder.setEnergyType(getEnergyType());

            float amount = getPoolSize() >= defaultPacketSize() ? defaultPacketSize() : Math.max(getPoolSize(), 0);
            pool = MathHelper.clampZero_float(getPoolSize() - amount, getMaxPoolSize());

            builder.append(amount);

            EnergyPacket packet = builder.toPacket();

            onPacketSent(packet);
            dest.onPacketReceived(packet);
        }
    }

    /* ======================================== ICrystalNetwork END ===================================== */

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

    @Override
    public void setDest(int x, int y, int z)
    {
        if (worldObj.getTileEntity(x, y, z) instanceof ICrystalNetworkBuffer)
        {
            dest = (ICrystalNetworkBuffer) worldObj.getTileEntity(x, y, z);
            LogHelper.info("Set dest " + dest.toString() + " for " + toString()); // TODO Debug level
        }
        else
        {
            dest = null;
            LogHelper.info("Set dest null for " + toString()); // TODO Debug level
        }
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

    public abstract float defaultPacketSize();
}
