package celestialwizardry.tileentity;

import celestialwizardry.api.crystal.ICrystal;
import celestialwizardry.api.crystal.ITileCrystal;
import celestialwizardry.api.energy.EnergyType;
import celestialwizardry.block.BlockCrystal;

import net.minecraft.block.Block;
import net.minecraft.world.World;

import java.util.List;

public class TileEntityCrystalConductive extends TileEntityCrystal
{
    public TileEntityCrystalConductive(BlockCrystal crystal)
    {
        super(crystal);
    }

    /* ======================================== ITileCrystal START ===================================== */

    // NO-OP

    /* ======================================== ITileCrystal END ===================================== */

    /* ======================================== ICrystal START ===================================== */

    /**
     * Get the {@link celestialwizardry.api.energy.EnergyType}s this {@link celestialwizardry.api.crystal.ICrystal} can handle.
     *
     * @param world the {@link net.minecraft.world.World} this {@link celestialwizardry.api.crystal.ICrystal} is
     *
     * @return An array of {@link celestialwizardry.api.energy.EnergyType} this {@link celestialwizardry.api.crystal.ICrystal} can handle
     */
    @Override
    public List<EnergyType> acceptableEnergies(World world)
    {
        return crystal.acceptableEnergies(world);
    }

    /**
     * The {@link net.minecraft.block.Block} instance that implements {@link celestialwizardry.api.crystal.ICrystal}.
     *
     * @return the {@link net.minecraft.block.Block}
     */
    @Override
    public Block getBlock()
    {
        return crystal.getBlock();
    }

    /**
     * Can this {@link celestialwizardry.api.crystal.ICrystal} be bounded to given {@link celestialwizardry.api
     * .crystal.ICrystal}.
     *
     * @param world   the {@link net.minecraft.world.World} this {@link celestialwizardry.api.crystal.ICrystal} is
     * @param crystal the {@link celestialwizardry.api.crystal.ICrystal} this {@link celestialwizardry.api.crystal
     * .ICrystal} is going to be bounded with
     *
     * @return can this {@link celestialwizardry.api.crystal.ICrystal} to the {@link celestialwizardry.api.crystal
     * .ICrystal}
     */
    @Override
    public boolean canBoundTo(World world, ICrystal crystal)
    {
        return this.crystal.canBoundTo(world, crystal);
    }

    /* ======================================== ICrystal END ===================================== */
}
