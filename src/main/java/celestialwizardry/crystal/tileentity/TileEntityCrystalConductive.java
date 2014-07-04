package celestialwizardry.crystal.tileentity;

import celestialwizardry.api.energy.EnergyRegistry;
import celestialwizardry.api.energy.EnergyType;
import celestialwizardry.crystal.api.crystal.EnergyPacket;
import celestialwizardry.crystal.api.crystal.ICrystal;
import celestialwizardry.crystal.util.PacketBuilder;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class TileEntityCrystalConductive extends TileEntityCrystalBuffer
{
    private static final int MAX_DISTANCE = 5;

    protected boolean hasReceivedInitialPacket = false;

    /* ======================================== ICrystalBuffer START ===================================== */

    /**
     * The maximum size of a {@link celestialwizardry.crystal.api.crystal.EnergyPacket} that can be stored in the {@link
     * ICrystal}.
     *
     * @return The maximum size
     */
    @Override
    public float getMaxPacketSize()
    {
        return 50F;
    }

    /**
     * The maximum amount of {@link celestialwizardry.crystal.api.crystal.EnergyPacket}s that can be stored in the
     * {@link ICrystal}.
     *
     * @return the maximum amount
     */
    @Override
    public int getMaxPackets()
    {
        return 1;
    }

    /**
     * Called when this {@link ICrystal} sends a {@link EnergyPacket}.
     *
     * @param packet the sent {@link EnergyPacket}
     */
    @Override
    public void onPacketSent(EnergyPacket packet)
    {
        super.onPacketSent(packet);
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
    }

    /* ======================================== ICrystalBuffer END ===================================== */

    /* ======================================== ICrystal START ===================================== */

    /**
     * Get the {@link celestialwizardry.api.energy.EnergyType}s this {@link ICrystal} can handle.
     *
     * @param world the {@link net.minecraft.world.World} this {@link ICrystal} is
     *
     * @return A list of {@link celestialwizardry.api.energy.EnergyType} this {@link ICrystal} can handle
     */
    @Override
    public List<EnergyType> acceptableEnergies(World world)
    {
        List<EnergyType> list = new ArrayList<EnergyType>();

        for (EnergyType energyType : EnergyRegistry.energyMap.values())
        {
            list.add(energyType); // aka every energy type
        }

        return list;
    }

    /**
     * Get the multiplier of energy to input into the block, 1.0 is the original amount of energy in the packet. 0.9,
     * for example, is 90%, so 10% of the energy in the packet will get dissipated.
     *
     * @return the multiplier of energy
     */
    @Override
    public float getEnergyYieldMultiplier()
    {
        return 0.95f;
    }

    /**
     * Can this {@link celestialwizardry.crystal.api.crystal.ICrystal} be bounded to given {@link
     * celestialwizardry.crystal.api.crystal.ICrystal}.
     *
     * @param world   the {@link net.minecraft.world.World} this {@link celestialwizardry.crystal.api.crystal.ICrystal}
     *                is
     * @param crystal the {@link celestialwizardry.crystal.api.crystal.ICrystal} this {@link
     *                celestialwizardry.crystal.api.crystal .ICrystal} is going to be bounded with
     *
     * @return can this {@link celestialwizardry.crystal.api.crystal.ICrystal} to the {@link
     * celestialwizardry.crystal.api.crystal .ICrystal}
     */
    @Override
    public boolean canBoundTo(World world, ICrystal crystal)
    {
        if (crystal.getXPos() == xCoord)
        {
            if (crystal.getZPos() == zCoord)
            {
                if (crystal.getYPos() - yCoord >= -MAX_DISTANCE && crystal.getYPos() - yCoord <= MAX_DISTANCE)
                {
                    return true;
                }
            }
            else
            {
                if (crystal.getZPos() - zCoord >= -MAX_DISTANCE && crystal.getZPos() - zCoord <= MAX_DISTANCE)
                {
                    if (crystal.getYPos() == yCoord)
                    {
                        return true;
                    }
                }
            }
        }
        else
        {
            if (crystal.getZPos() == zCoord)
            {
                if (crystal.getXPos() - xCoord >= -MAX_DISTANCE && crystal.getXPos() - xCoord <= MAX_DISTANCE)
                {
                    if (crystal.getYPos() == yCoord)
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /* ======================================== ICrystal END ===================================== */

    /* ======================================== TileEntity START ===================================== */

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);

        if (worldObj != null && worldObj.isRemote)
        {
            hasReceivedInitialPacket = true;
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);
    }

    @Override
    public void updateEntity()
    {
        super.updateEntity();
    }

    /* ======================================== TileEntity END ===================================== */

    @Override
    public PacketBuilder getBuilder()
    {
        return new PacketBuilder(getMaxPacketSize());
    }
}
