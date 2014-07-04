package celestialwizardry.crystal.tileentity;

import celestialwizardry.crystal.api.crystal.EnergyPacket;
import celestialwizardry.crystal.api.crystal.ICrystal;
import celestialwizardry.crystal.api.crystal.ICrystalNetworkBuffer;
import celestialwizardry.crystal.api.crystal.ICrystalNetworkPool;
import celestialwizardry.crystal.reference.CrystalNames;
import celestialwizardry.crystal.util.PacketBuilder;

import net.minecraft.nbt.NBTTagCompound;

import java.util.ArrayList;
import java.util.List;

public abstract class TileEntityCrystalNetworkBuffer extends TileEntityCrystalNetwork implements ICrystalNetworkBuffer
{
    protected List<EnergyPacket> buffer = new ArrayList<EnergyPacket>();

    /* ======================================== ICrystalBuffer START ===================================== */

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

    /**
     * Called when this {@link ICrystal} sends a {@link EnergyPacket}.
     *
     * @param packet the sent {@link EnergyPacket}
     */
    @Override
    public void onPacketSent(EnergyPacket packet)
    {

    }

    /**
     * Called when this {@link ICrystal} receives a {@link EnergyPacket}.
     *
     * @param packet the received {@link EnergyPacket}
     */
    @Override
    public void onPacketReceived(EnergyPacket packet)
    {

    }

    /* ======================================== ICrystalBuffer END ===================================== */

    /* ======================================== ICrystal START ===================================== */

    /* ======================================== ICrystal END ===================================== */

    /* ======================================== TileEntity START ===================================== */

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);

        NBTTagCompound bufferCompound = nbtTagCompound.getCompoundTag(CrystalNames.NBT.BUFFER);

        for (int i = 0; i < nbtTagCompound.getInteger(CrystalNames.NBT.BUFFER_SIZE); i++)
        {
            buffer.add(new EnergyPacket(bufferCompound.getString(String.valueOf(i))));
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
                bufferCompound.setString(String.valueOf(i), packet.toString());
            }
        }

        nbtTagCompound.setTag(CrystalNames.NBT.BUFFER, bufferCompound);
        nbtTagCompound.setInteger(CrystalNames.NBT.BUFFER_SIZE, buffer.size());
    }

    /* ======================================== TileEntity END ===================================== */

    /* ======================================== TileEntityCrystal START ===================================== */

    @Override
    public PacketBuilder getBuilder()
    {
        return new PacketBuilder(getMaxPacketSize());
    }

    /* ======================================== TileEntityCrystal END ===================================== */

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
