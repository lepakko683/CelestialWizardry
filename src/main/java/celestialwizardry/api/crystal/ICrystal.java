package celestialwizardry.api.crystal;

import celestialwizardry.api.energy.EnergyType;

import net.minecraft.block.Block;
import net.minecraft.world.World;

import java.util.List;

/**
 * An interface implemented by all crystals.
 */
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
     * The {@link Block} instance that implements {@link ICrystal}.
     *
     * @return the {@link Block}
     */
    public Block getBlock();

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
     * Bounds the blockCrystal {@link net.minecraft.tileentity.TileEntity} to other blockCrystal {@link net.minecraft.tileentity.TileEntity} for input.
     *
     * @param x the x coordinate of the other blockCrystal {@link net.minecraft.tileentity.TileEntity}
     * @param y the y coordinate of the other blockCrystal {@link net.minecraft.tileentity.TileEntity}
     * @param z the z coordinate of the other blockCrystal {@link net.minecraft.tileentity.TileEntity}
     *
     * @return true if the bound was success
     */
    public boolean setInputBound(int x, int y, int z);

    /**
     * Bounds the blockCrystal {@link net.minecraft.tileentity.TileEntity} to other blockCrystal {@link net.minecraft.tileentity.TileEntity} for output.
     *
     * @param x the x coordinate of the other blockCrystal {@link net.minecraft.tileentity.TileEntity}
     * @param y the y coordinate of the other blockCrystal {@link net.minecraft.tileentity.TileEntity}
     * @param z the z coordinate of the other blockCrystal {@link net.minecraft.tileentity.TileEntity}
     *
     * @return true if the bound was success
     */
    public boolean setOutputBound(int x, int y, int z);

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
