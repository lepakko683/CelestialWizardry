package celestialwizardry.tileentity;

import celestialwizardry.api.crystal.EnergyPacket;
import celestialwizardry.api.crystal.ICrystalBuffer;
import celestialwizardry.api.crystal.ICrystal;
import celestialwizardry.block.BlockCrystal;
import celestialwizardry.reference.Names;

import net.minecraft.nbt.NBTTagCompound;

import java.util.ArrayList;
import java.util.List;

public abstract class TileEntityCrystalBuffer extends TileEntityCrystal implements ICrystalBuffer
{
    protected List<EnergyPacket> buffer = new ArrayList<EnergyPacket>(); // TODO Write to NBT

    public TileEntityCrystalBuffer(BlockCrystal blockCrystal)
    {
        super(blockCrystal);
    }

    /* ======================================== ICrystalBuffer START ===================================== */

    /**
     * The maximum size of a {@link EnergyPacket} that can be stored in the {@link ICrystal}.
     *
     * @return The maximum size
     */
    @Override
    public float getMaxPacketSize()
    {
        return 0;
    }

    /**
     * The maximum amount of {@link EnergyPacket}s that can be stored in the {@link ICrystal}.
     *
     * @return the maximum amount
     */
    @Override
    public int getMaxPackets()
    {
        return 0;
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

    /* ======================================== ICrystalBuffer END ===================================== */

    /* ======================================== ICrystal START ===================================== */

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

    /* ======================================== ICrystal END ===================================== */

    /* ======================================== TileEntity START ===================================== */

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);

        NBTTagCompound bufferCompound = nbtTagCompound.getCompoundTag(Names.NBT.BUFFER);

        for (int i = 0; i < nbtTagCompound.getInteger(Names.NBT.BUFFER_SIZE); i++)
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

        nbtTagCompound.setTag(Names.NBT.BUFFER, bufferCompound);
        nbtTagCompound.setInteger(Names.NBT.BUFFER_SIZE, buffer.size());
    }

    /* ======================================== TileEntity END ===================================== */
}
