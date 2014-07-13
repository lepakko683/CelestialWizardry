package celestibytes.celestialwizardry.crystal.tileentity;

import celestibytes.celestialwizardry.api.energy.EnergyType;
import celestibytes.celestialwizardry.crystal.api.crystal.EnergyPacket;
import celestibytes.celestialwizardry.crystal.api.crystal.ICrystalNetworkBuffer;
import celestibytes.celestialwizardry.crystal.api.crystal.ICrystalNetworkPool;
import celestibytes.celestialwizardry.crystal.reference.CrystalNames;
import celestibytes.celestialwizardry.crystal.util.PacketBuilder;
import celestibytes.celestialwizardry.util.LogHelper;
import celestibytes.core.util.MathHelper;

import net.minecraft.nbt.NBTTagCompound;

public abstract class TileEntityCrystalNetworkPool extends TileEntityCrystalNetwork implements ICrystalNetworkPool
{
    protected static final int DEFAULT_COOLDOWN = 60;
    protected EnergyType currentEnergy;
    protected float pool = 0F;

    /**
     * Sends a {@link EnergyPacket} to the target {@link celestibytes.celestialwizardry.crystal.api.crystal.ICrystal}
     */
    @Override
    public void sendPacket()
    {
        PacketBuilder builder = getBuilder();

        builder.setEnergyType(getEnergyType());

        float amount = getPoolSize() >= defaultPacketSize() ? defaultPacketSize() : Math.max(getPoolSize(), 0);
        pool = MathHelper.clampZero_float(getPoolSize() - amount, getMaxPoolSize());

        builder.append(amount);

        EnergyPacket packet = builder.toPacket();

        onPacketSent(packet);
        dest.onPacketReceived(packet);

        LogHelper.debug("Sent a EnergyPacket from " + toString() + " to " + dest.toString());
    }

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
            LogHelper.debug("Set dest " + dest.toString() + " for " + toString());
        }
        else
        {
            dest = null;
            LogHelper.debug("Set dest null for " + toString());
        }
    }

    @Override
    public int defaultCooldown()
    {
        return DEFAULT_COOLDOWN;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);

        pool = nbtTagCompound.getFloat(CrystalNames.NBT.POOL);

        int x = nbtTagCompound.getInteger(CrystalNames.NBT.DEST_X);
        int y = nbtTagCompound.getInteger(CrystalNames.NBT.DEST_Y);
        int z = nbtTagCompound.getInteger(CrystalNames.NBT.DEST_Z);

        setDest(x, y, z);
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setFloat(CrystalNames.NBT.POOL, pool);

        nbtTagCompound.setInteger(CrystalNames.NBT.DEST_X, dest.getXPos());
        nbtTagCompound.setInteger(CrystalNames.NBT.DEST_Y, dest.getYPos());
        nbtTagCompound.setInteger(CrystalNames.NBT.DEST_Z, dest.getZPos());
    }

    @Override
    public void updateEntity()
    {
        super.updateEntity();

        if (!worldObj.isRemote)
        {
            if (canSend())
            {
                // TODO Redstone (or similar) on off control
                if (dest != null)
                {
                    sendPacket();
                }
            }
        }
    }

    @Override
    public PacketBuilder getBuilder()
    {
        return new PacketBuilder(getMaxPoolSize() / 10, this);
    }

    public abstract float defaultPacketSize();
}
