package celestibytes.celestialwizardry.crystal.tileentity;

import celestibytes.celestialwizardry.crystal.api.crystal.EnergyPacket;
import celestibytes.celestialwizardry.crystal.api.crystal.ICrystal;
import celestibytes.celestialwizardry.crystal.api.crystal.ICrystalNetwork;
import celestibytes.celestialwizardry.crystal.api.crystal.ICrystalNetworkBuffer;
import celestibytes.celestialwizardry.crystal.api.crystal.ICrystalNetworkPool;
import celestibytes.celestialwizardry.crystal.reference.CrystalNames;
import celestibytes.celestialwizardry.crystal.util.PacketBuilder;
import celestibytes.celestialwizardry.util.LogHelper;

import net.minecraft.nbt.NBTTagCompound;

import java.util.ArrayList;
import java.util.List;

public abstract class TileEntityCrystalNetworkBuffer extends TileEntityCrystalNetwork implements ICrystalNetworkBuffer
{
    protected List<EnergyPacket> buffer = new ArrayList<EnergyPacket>();
    protected ICrystal sender;

    /**
     * Sends a {@link celestibytes.celestialwizardry.crystal.api.crystal.EnergyPacket} to the target {@link
     * celestibytes.celestialwizardry.crystal.api.crystal.ICrystal}
     */
    @Override
    public void sendPacket()
    {

    }

    @Override
    public void setDest(int x, int y, int z)
    {
        if (worldObj.getTileEntity(x, y, z) instanceof ICrystalNetwork)
        {
            dest = (ICrystalNetwork) worldObj.getTileEntity(x, y, z);
            LogHelper.debug("Set dest " + dest.toString() + " for " + toString());
        }
        else
        {
            dest = null;
            LogHelper.debug("Set dest null for " + toString());
        }
    }

    /**
     * Called when this {@link ICrystal} receives a {@link EnergyPacket}.
     *
     * @param packet the received {@link EnergyPacket}
     */
    @Override
    public void onPacketReceived(EnergyPacket packet)
    {
        super.onPacketReceived(packet);

        sender = packet.getCompiler();

        EnergyPacket energyPacket = handlePacket(packet);

        buffer.add(energyPacket);
    }

    /**
     * The current {@link EnergyPacket} buffer of this {@link ICrystal}
     *
     * @return list of the {@link EnergyPacket}s in the buffer
     */
    @Override
    public List<EnergyPacket> getBuffer()
    {
        return buffer;
    }

    @Override
    public int defaultCooldown()
    {
        return 0; // TODO
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);

        NBTTagCompound bufferCompound = nbtTagCompound.getCompoundTag(CrystalNames.NBT.BUFFER);

        for (int i = 0; i < nbtTagCompound.getInteger(CrystalNames.NBT.BUFFER_SIZE); i++)
        {
            NBTTagCompound c = nbtTagCompound.getCompoundTag(CrystalNames.NBT.CRYSTAL + i);

            int x = c.getInteger(CrystalNames.NBT.X);
            int y = c.getInteger(CrystalNames.NBT.Y);
            int z = c.getInteger(CrystalNames.NBT.Z);

            ICrystal sender = null;

            if (worldObj.getTileEntity(x, y, z) instanceof ICrystal)
            {
                sender = (ICrystal) worldObj.getTileEntity(x, y, z);
            }

            buffer.add(new EnergyPacket(bufferCompound.getString(String.valueOf(i)), sender));
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);

        NBTTagCompound bufferCompound = new NBTTagCompound();

        for (int i = 0; i < buffer.size(); i++)
        {
            if (buffer.get(i) != null)
            {
                EnergyPacket packet = buffer.get(i);
                ICrystal crystal = packet.getCompiler();

                bufferCompound.setString(String.valueOf(i), packet.toString());

                NBTTagCompound c = new NBTTagCompound();

                c.setInteger(CrystalNames.NBT.X, crystal.getXPos());
                c.setInteger(CrystalNames.NBT.Y, crystal.getYPos());
                c.setInteger(CrystalNames.NBT.Z, crystal.getZPos());

                nbtTagCompound.setTag(CrystalNames.NBT.CRYSTAL + i, c);
            }
        }

        nbtTagCompound.setTag(CrystalNames.NBT.BUFFER, bufferCompound);
        nbtTagCompound.setInteger(CrystalNames.NBT.BUFFER_SIZE, buffer.size());
    }

    @Override
    public PacketBuilder getBuilder()
    {
        return new PacketBuilder(getMaxPacketSize(), this);
    }

    public EnergyPacket handlePacket(EnergyPacket packet)
    {
        PacketBuilder builder = getBuilder();

        builder.setEnergyType(packet.getEnergyType());
        builder.append(packet.getSize() * getEnergyYieldMultiplier());

        return builder.toPacket();
    }

    @SuppressWarnings("unused")
    public ICrystalNetworkPool findPool()
    {
        if (worldObj.getTileEntity(xCoord + 1, yCoord, zCoord) instanceof ICrystalNetworkPool)
        {
            return (ICrystalNetworkPool) worldObj.getTileEntity(xCoord + 1, yCoord, zCoord);
        }
        else if (worldObj.getTileEntity(xCoord - 1, yCoord, zCoord) instanceof ICrystalNetworkPool)
        {
            return (ICrystalNetworkPool) worldObj.getTileEntity(xCoord - 1, yCoord, zCoord);
        }
        else if (worldObj.getTileEntity(xCoord, yCoord, zCoord + 1) instanceof ICrystalNetworkPool)
        {
            return (ICrystalNetworkPool) worldObj.getTileEntity(xCoord, yCoord, zCoord + 1);
        }
        else if (worldObj.getTileEntity(xCoord, yCoord, zCoord - 1) instanceof ICrystalNetworkPool)
        {
            return (ICrystalNetworkPool) worldObj.getTileEntity(xCoord, yCoord, zCoord - 1);
        }

        return null;
    }
}
