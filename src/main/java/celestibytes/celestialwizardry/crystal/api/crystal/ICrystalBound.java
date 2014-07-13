package celestibytes.celestialwizardry.crystal.api.crystal;

import net.minecraft.world.World;

public interface ICrystalBound extends ICrystal
{
    /**
     * Can this {@link ICrystal} be bounded to given {@link ICrystal}.
     *
     * @param world   the {@link net.minecraft.world.World} this {@link ICrystal} is
     * @param crystal the {@link ICrystal} this {@link ICrystal} is going to be bounded with
     *
     * @return can this {@link ICrystal} to the {@link ICrystal}
     */
    public boolean canBoundTo(World world, ICrystal crystal);

    /**
     * Bounds the blockCrystal {@link net.minecraft.tileentity.TileEntity} to other blockCrystal {@link
     * net.minecraft.tileentity.TileEntity} for output.
     *
     * @param x the x coordinate of the other blockCrystal {@link net.minecraft.tileentity.TileEntity}
     * @param y the y coordinate of the other blockCrystal {@link net.minecraft.tileentity.TileEntity}
     * @param z the z coordinate of the other blockCrystal {@link net.minecraft.tileentity.TileEntity}
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
}
