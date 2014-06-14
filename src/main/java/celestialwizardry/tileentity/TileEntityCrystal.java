package celestialwizardry.tileentity;

import celestialwizardry.api.crystal.ICrystal;
import celestialwizardry.api.energy.EnergyType;
import celestialwizardry.block.BlockCrystal;
import celestialwizardry.reference.Names;
import celestialwizardry.registry.EnergyRegistry;
import celestialwizardry.util.MathHelper;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public abstract class TileEntityCrystal extends TileEntityCW implements ICrystal
{
    protected final BlockCrystal blockCrystal;
    protected int boundInX;
    protected int boundInY;
    protected int boundInZ;
    protected int boundOutX;
    protected int boundOutY;
    protected int boundOutZ;
    protected float buffer;

    public TileEntityCrystal(BlockCrystal blockCrystal)
    {
        this.blockCrystal = blockCrystal;
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
     * The current amount of {@link celestialwizardry.api.energy.EnergyType} that is stored in the {@link
     * celestialwizardry.api.crystal.ICrystal}.
     *
     * @return The current buffer size
     */
    @Override
    public float getCurrentBuffer()
    {
        return buffer;
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

    /**
     * Can this {@link celestialwizardry.api.crystal.ICrystal} be bounded to given {@link celestialwizardry.api
     * .blockCrystal.ICrystal}.
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
        return true;
    }

    /**
     * Bounds the blockCrystal {@link TileEntity} to other blockCrystal {@link TileEntity} for input.
     *
     * @param x the x coordinate of the other blockCrystal {@link TileEntity}
     * @param y the y coordinate of the other blockCrystal {@link TileEntity}
     * @param z the z coordinate of the other blockCrystal {@link TileEntity}
     *
     * @return true if the bound was success
     */
    @Override
    public boolean setInputBound(int x, int y, int z)
    {
        if (worldObj.getTileEntity(x, y, z) instanceof ICrystal)
        {
            ICrystal crystal = (ICrystal) worldObj.getTileEntity(x, y, z);

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
     * The bounded crystal for input.
     *
     * @return The bounded crystal for input.
     */
    @Override
    public ICrystal getInputBound()
    {
        return ((worldObj.getTileEntity(boundInX, boundInY, boundInZ) != null) && (worldObj
                .getTileEntity(boundInX, boundInY, boundInZ) instanceof ICrystal)) ? (ICrystal) worldObj
                .getTileEntity(boundInX, boundInY, boundInZ) : null;
    }

    /**
     * Bounds the blockCrystal {@link TileEntity} to other blockCrystal {@link TileEntity} for output.
     *
     * @param x the x coordinate of the other blockCrystal {@link TileEntity}
     * @param y the y coordinate of the other blockCrystal {@link TileEntity}
     * @param z the z coordinate of the other blockCrystal {@link TileEntity}
     *
     * @return true if the bound was success
     */
    @Override
    public boolean setOutputBound(int x, int y, int z)
    {
        if (worldObj.getTileEntity(x, y, z) instanceof ICrystal)
        {
            ICrystal crystal = (ICrystal) worldObj.getTileEntity(x, y, z);

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

    /**
     * The bounded crystal for output.
     *
     * @return The bounded crystal for output.
     */
    @Override
    public ICrystal getOutputBound()
    {
        return ((worldObj.getTileEntity(boundOutX, boundOutY, boundOutZ) != null) && (worldObj
                .getTileEntity(boundOutX, boundOutY, boundOutZ) instanceof ICrystal)) ? (ICrystal) worldObj
                .getTileEntity(boundOutX, boundOutY, boundOutZ) : null;
    }

    /**
     * The x position of the crystal tile.
     *
     * @return x position
     */
    @Override
    public int getXPos()
    {
        return xCoord;
    }

    /**
     * The y position of the crystal tile.
     *
     * @return y position
     */
    @Override
    public int getYPos()
    {
        return yCoord;
    }

    /**
     * The z position of the crystal tile.
     *
     * @return z position
     */
    @Override
    public int getZPos()
    {
        return zCoord;
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
        buffer = nbtTagCompound.getFloat(Names.NBT.BUFFER);
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
        nbtTagCompound.setFloat(Names.NBT.BUFFER, buffer);
    }

    /* ======================================== TileEntity END ===================================== */
}
