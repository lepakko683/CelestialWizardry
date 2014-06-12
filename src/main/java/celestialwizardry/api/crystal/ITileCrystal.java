package celestialwizardry.api.crystal;

import net.minecraft.tileentity.TileEntity;

/**
 * An interface implemented by all crystal {@link TileEntity}.
 */
public interface ITileCrystal extends ICrystal
{
    /**
     * Bounds the crystal {@link TileEntity} to other crystal {@link TileEntity} for input.
     *
     * @param x the x coordinate of the other crystal {@link TileEntity}
     * @param y the y coordinate of the other crystal {@link TileEntity}
     * @param z the z coordinate of the other crystal {@link TileEntity}
     *
     * @return true if the bound was success
     */
    public boolean setInputBound(int x, int y, int z);

    /**
     * Bounds the crystal {@link TileEntity} to other crystal {@link TileEntity} for output.
     *
     * @param x the x coordinate of the other crystal {@link TileEntity}
     * @param y the y coordinate of the other crystal {@link TileEntity}
     * @param z the z coordinate of the other crystal {@link TileEntity}
     *
     * @return true if the bound was success
     */
    public boolean setOutputBound(int x, int y, int z);
}
