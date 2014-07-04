package celestialwizardry.crystal.tileentity;

import celestialwizardry.api.energy.EnergyRegistry;
import celestialwizardry.api.energy.EnergyType;
import celestialwizardry.crystal.api.crystal.ICrystal;
import celestialwizardry.crystal.reference.CrystalNames;
import celestialwizardry.crystal.util.PacketBuilder;
import celestialwizardry.tileentity.TileEntityCW;
import celestialwizardry.util.LogHelper;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public abstract class TileEntityCrystal extends TileEntityCW implements ICrystal
{
    protected int boundX;
    protected int boundY;
    protected int boundZ;

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

    @Override
    public float getEnergyYieldMultiplier()
    {
        return 1f;
    }

    /**
     * Can this {@link celestialwizardry.crystal.api.crystal.ICrystal} be bounded to given {@link celestialwizardry.api
     * .blockCrystal.ICrystal}.
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
        return true;
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
    public boolean setBound(int x, int y, int z)
    {
        if (worldObj.getTileEntity(x, y, z) instanceof ICrystal)
        {
            ICrystal crystal = (ICrystal) worldObj.getTileEntity(x, y, z);

            if (canBoundTo(worldObj, crystal))
            {
                boundX = x;
                boundY = y;
                boundZ = z;

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
    public ICrystal getBound()
    {
        return ((worldObj.getTileEntity(boundX, boundY, boundZ) != null) && (worldObj
                .getTileEntity(boundX, boundY, boundZ) instanceof ICrystal)) ? (ICrystal) worldObj
                .getTileEntity(boundX, boundY, boundZ) : null;
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
        LogHelper.info("Reading " + toString() + " from NBT");
        boundX = nbtTagCompound.getInteger(CrystalNames.NBT.BOUND_X);
        boundY = nbtTagCompound.getInteger(CrystalNames.NBT.BOUND_Y);
        boundZ = nbtTagCompound.getInteger(CrystalNames.NBT.BOUND_Z);
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);
        LogHelper.info("Writing " + toString() + " to NBT");
        nbtTagCompound.setInteger(CrystalNames.NBT.BOUND_X, boundX);
        nbtTagCompound.setInteger(CrystalNames.NBT.BOUND_Y, boundY);
        nbtTagCompound.setInteger(CrystalNames.NBT.BOUND_Z, boundZ);
    }

    /* ======================================== TileEntity END ===================================== */

    public abstract PacketBuilder getBuilder();

    @Override
    public String toString()
    {
        return this.getClass().getSimpleName() + ".x:" + getXPos() + ".y:" + getYPos() + ".z:" + getZPos();
    }
}
