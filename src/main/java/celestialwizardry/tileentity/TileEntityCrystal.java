package celestialwizardry.tileentity;

import celestialwizardry.api.crystal.ICrystal;
import celestialwizardry.api.crystal.ITileCrystal;
import celestialwizardry.api.energy.EnergyType;
import celestialwizardry.block.BlockCrystal;
import celestialwizardry.reference.Names;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityCrystal extends TileEntityCW implements ITileCrystal
{
    protected final BlockCrystal crystal;
    protected int boundInX;
    protected int boundInY;
    protected int boundInZ;
    protected int boundOutX;
    protected int boundOutY;
    protected int boundOutZ;

    public TileEntityCrystal(BlockCrystal crystal)
    {
        this.crystal = crystal;
    }

    /* ======================================== ITileCrystal START ===================================== */

    /**
     * Bounds the crystal {@link TileEntity} to other crystal {@link TileEntity} for input.
     *
     * @param x the x coordinate of the other crystal {@link TileEntity}
     * @param y the y coordinate of the other crystal {@link TileEntity}
     * @param z the z coordinate of the other crystal {@link TileEntity}
     *
     * @return true if the bound was success
     */
    @Override
    public boolean setInputBound(int x, int y, int z)
    {
        if (worldObj.getTileEntity(x, y, z) instanceof ITileCrystal)
        {
            ITileCrystal crystal = (ITileCrystal) worldObj.getTileEntity(x, y, z);

            if (canBoundTo(worldObj, crystal))
            {
                boundInX = x;
                boundInY = y;
                boundInZ = z;

                return true;
            }
        }

        return false;
    }

    /**
     * Bounds the crystal {@link TileEntity} to other crystal {@link TileEntity} for output.
     *
     * @param x the x coordinate of the other crystal {@link TileEntity}
     * @param y the y coordinate of the other crystal {@link TileEntity}
     * @param z the z coordinate of the other crystal {@link TileEntity}
     *
     * @return true if the bound was success
     */
    @Override
    public boolean setOutputBound(int x, int y, int z)
    {
        if (worldObj.getTileEntity(x, y, z) instanceof ITileCrystal)
        {
            ITileCrystal crystal = (ITileCrystal) worldObj.getTileEntity(x, y, z);

            if (canBoundTo(worldObj, crystal))
            {
                boundOutX = x;
                boundOutY = y;
                boundOutZ = z;

                return true;
            }
        }

        return false;
    }

    /* ======================================== ITileCrystal END ===================================== */

    /* ======================================== ICrystal START ===================================== */

    /**
     * Get the {@link celestialwizardry.api.energy.EnergyType}s this {@link ICrystal} can handle.
     *
     * @param world the {@link net.minecraft.world.World} this {@link ICrystal} is
     *
     * @return An array of {@link celestialwizardry.api.energy.EnergyType} this {@link ICrystal} can handle
     */
    @Override
    public EnergyType[] acceptableEnergies(World world)
    {
        return crystal.acceptableEnergies(world);
    }

    /**
     * The {@link net.minecraft.block.Block} instance that implements {@link ICrystal}.
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

    /* ======================================== TileEntity START ===================================== */

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);
        boundInX = nbtTagCompound.getInteger(Names.NBT.BOUND_IN_X);
        boundInY = nbtTagCompound.getInteger(Names.NBT.BOUND_IN_Y);
        boundInZ = nbtTagCompound.getInteger(Names.NBT.BOUND_IN_Z);
        boundOutX = nbtTagCompound.getInteger(Names.NBT.BOUND_OUT_X);
        boundOutY = nbtTagCompound.getInteger(Names.NBT.BOUND_OUT_Y);
        boundOutZ = nbtTagCompound.getInteger(Names.NBT.BOUND_OUT_Z);
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);
        nbtTagCompound.setInteger(Names.NBT.BOUND_IN_X, boundInX);
        nbtTagCompound.setInteger(Names.NBT.BOUND_IN_Y, boundInY);
        nbtTagCompound.setInteger(Names.NBT.BOUND_IN_Z, boundInZ);
        nbtTagCompound.setInteger(Names.NBT.BOUND_OUT_X, boundOutX);
        nbtTagCompound.setInteger(Names.NBT.BOUND_OUT_Y, boundOutY);
        nbtTagCompound.setInteger(Names.NBT.BOUND_OUT_Z, boundOutZ);
    }

    /* ======================================== TileEntity END ===================================== */
}
