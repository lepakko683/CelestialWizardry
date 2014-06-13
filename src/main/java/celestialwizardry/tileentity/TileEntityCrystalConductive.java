package celestialwizardry.tileentity;

import celestialwizardry.api.crystal.ICrystal;
import celestialwizardry.api.energy.EnergyType;
import celestialwizardry.block.BlockCrystal;
import celestialwizardry.registry.EnergyRegistry;

import net.minecraft.block.Block;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class TileEntityCrystalConductive extends TileEntityCrystal
{
    private static final int MAX_DISTANCE = 5;
    private static final float MAX_BUFFER = 100f;

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
     * The maximum amount of {@link celestialwizardry.api.energy.EnergyType} that can be stored in the {@link
     * celestialwizardry.api.crystal.ICrystal}.
     *
     * @return The maximum buffer size
     */
    @Override
    public float getMaxBuffer()
    {
        return MAX_BUFFER;
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
    public void updateEntity()
    {
        super.updateEntity();
    }

    /* ======================================== TileEntity END ===================================== */
}
