package celestialwizardry.tileentity;

import celestialwizardry.api.crystal.ICrystal;
import celestialwizardry.api.energy.EnergyRegistry;
import celestialwizardry.api.energy.EnergyType;
import celestialwizardry.block.BlockCrystal;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class TileEntityCrystalConductive extends TileEntityCrystal
{
    private static final int MAX_DISTANCE = 5;
    private static final float MAX_BUFFER = 100f;

    protected boolean hasReceivedInitialPacket = false;

    public TileEntityCrystalConductive(BlockCrystal crystal)
    {
        super(crystal);
    }

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
     * The current {@link celestialwizardry.api.energy.EnergyType} stored inside the {@link celestialwizardry.api.crystal.ICrystal}.
     *
     * @return the current {@link celestialwizardry.api.energy.EnergyType} stored inside the {@link celestialwizardry.api.crystal.ICrystal}.
     */
    @Override
    public EnergyType getCurrentEnergyType()
    {
        return null;
    }

    /**
     * Sets the {@link celestialwizardry.api.crystal.ICrystal}'s {@link celestialwizardry.api.energy.EnergyType} to
     * given one.
     *
     * @param energyType the {@link celestialwizardry.api.energy.EnergyType} to set
     *
     * @return true if the {@link celestialwizardry.api.energy.EnergyType} was changed
     */
    @Override
    public boolean setCurrentEnergyType(EnergyType energyType)
    {
        return false;
    }

    /**
     * Sets the {@link celestialwizardry.api.crystal.ICrystal}'s energy buffer full.
     *
     * @return true if the operation was successful
     */
    @Override
    public boolean setFull()
    {
        return false;
    }

    /**
     * Sets the {@link celestialwizardry.api.crystal.ICrystal}'s energy buffer full of certain {@link celestialwizardry.api.energy.EnergyType}.
     *
     * @param energyType the {@link celestialwizardry.api.energy.EnergyType} to set
     *
     * @return true if the operation was successful
     */
    @Override
    public boolean setFull(EnergyType energyType)
    {
        return false;
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
     * Can this {@link celestialwizardry.api.crystal.ICrystal} be bounded to given {@link
     * celestialwizardry.api.crystal.ICrystal}.
     *
     * @param world   the {@link net.minecraft.world.World} this {@link celestialwizardry.api.crystal.ICrystal} is
     * @param crystal the {@link celestialwizardry.api.crystal.ICrystal} this {@link celestialwizardry.api.crystal
     *                .ICrystal} is going to be bounded with
     *
     * @return can this {@link celestialwizardry.api.crystal.ICrystal} to the {@link celestialwizardry.api.crystal
     * .ICrystal}
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

    /**
     * The {@link net.minecraft.block.Block} instance that implements {@link ICrystal}.
     *
     * @return the {@link net.minecraft.block.Block}
     */
    @Override
    public Block getBlock()
    {
        return blockCrystal;
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
}
