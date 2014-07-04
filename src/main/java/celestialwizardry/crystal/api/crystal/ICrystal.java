package celestialwizardry.crystal.api.crystal;

import celestialwizardry.api.energy.EnergyType;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.List;

/**
 * An interface implemented by all crystals.
 * <p/>
 * The energy sending and receiving handling should always happen in the outputting (the {@link ICrystal} that SENDS
 * energy) side to avoid any conflicts.
 */
@SuppressWarnings("unused") // TODO Remove (here for seeing only the real warnings)
public interface ICrystal
{
    /**
     * Get the {@link EnergyType}s this {@link ICrystal} can handle.
     *
     * @param world the {@link World} this {@link ICrystal} is
     *
     * @return A list of {@link EnergyType} this {@link ICrystal} can handle
     */
    public List<EnergyType> acceptableEnergies(World world);

    /**
     * Get the multiplier of energy to input into the block, 1.0 is the original amount of energy in the packet. 0.9,
     * for example, is 90%, so 10% of the energy in the packet will get dissipated.
     *
     * @return the multiplier of energy
     */
    public float getEnergyYieldMultiplier();

    /**
     * Can this {@link ICrystal} be bounded to given {@link ICrystal}.
     *
     * @param world   the {@link World} this {@link ICrystal} is
     * @param crystal the {@link ICrystal} this {@link ICrystal} is going to be bounded with
     *
     * @return can this {@link ICrystal} to the {@link ICrystal}
     */
    public boolean canBoundTo(World world, ICrystal crystal);

    /**
     * Bounds the blockCrystal {@link TileEntity} to other blockCrystal {@link TileEntity} for output.
     *
     * @param x the x coordinate of the other blockCrystal {@link TileEntity}
     * @param y the y coordinate of the other blockCrystal {@link TileEntity}
     * @param z the z coordinate of the other blockCrystal {@link TileEntity}
     *
     * @return true if the bound was success
     */
    public boolean setBound(int x, int y, int z);

    /**
     * The bounded crystal for output.
     *
     * @return The bounded crystal for output.
     */
    public ICrystal getBound();

    /**
     * The x position of the crystal tile.
     *
     * @return x position
     */
    public int getXPos();

    /**
     * The y position of the crystal tile.
     *
     * @return y position
     */
    public int getYPos();

    /**
     * The z position of the crystal tile.
     *
     * @return z position
     */
    public int getZPos();
}
