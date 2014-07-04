package celestialwizardry.crystal.tileentity;

import celestialwizardry.api.energy.EnergyRegistry;
import celestialwizardry.api.energy.EnergyType;
import celestialwizardry.crystal.api.crystal.ICrystal;
import celestialwizardry.crystal.api.crystal.INetworkCrystal;
import celestialwizardry.crystal.block.BlockCrystal;
import celestialwizardry.crystal.reference.CrystalNames;
import celestialwizardry.crystal.util.PacketBuilder;
import celestialwizardry.tileentity.TileEntityCW;
import celestialwizardry.util.LogHelper;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public abstract class TileEntityCrystal extends TileEntityCW implements INetworkCrystal
{
    protected static final CrystalNetwork NETWORK = new CrystalNetwork();
    protected int boundX;
    protected int boundY;
    protected int boundZ;

    public TileEntityCrystal()
    {
        if (!NETWORK.initialized)
        {
            NETWORK.setWorld(worldObj);
        }
    }

    public static CrystalNetwork getNetwork()
    {
        return NETWORK;
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

    /* ======================================== INetworkCrystal START ===================================== */

    @Override
    public void onAdded()
    {
        NETWORK.addCrystal(this);
    }

    @Override
    public void onRemoved()
    {
        NETWORK.removeCrystal(this);
    }

    /* ======================================== INetworkCrystal END ===================================== */

    /* ======================================== TileEntity START ===================================== */

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);
        boundX = nbtTagCompound.getInteger(CrystalNames.NBT.BOUND_X);
        boundY = nbtTagCompound.getInteger(CrystalNames.NBT.BOUND_Y);
        boundZ = nbtTagCompound.getInteger(CrystalNames.NBT.BOUND_Z);

        LogHelper.info("Reading " + toString() + " from NBT");

        NETWORK.readCrystal(nbtTagCompound, this);
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);
        nbtTagCompound.setInteger(CrystalNames.NBT.BOUND_X, boundX);
        nbtTagCompound.setInteger(CrystalNames.NBT.BOUND_Y, boundY);
        nbtTagCompound.setInteger(CrystalNames.NBT.BOUND_Z, boundZ);

        LogHelper.info("Writing " + toString() + " to NBT");

        NETWORK.writeCrystal(nbtTagCompound, this);
    }

    /* ======================================== TileEntity END ===================================== */

    public abstract PacketBuilder getBuilder();

    @Override
    public String toString()
    {
        return this.getClass().getSimpleName() + ".x:" + getXPos() + ".y:" + getYPos() + ".z:" + getZPos();
    }

    public static final class CrystalNetwork
    {
        private List<ICrystal> crystals = new ArrayList<ICrystal>();

        private World world;
        private boolean initialized = false;

        private CrystalNetwork()
        {
        }

        public void setWorld(World world)
        {
            this.world = world;
            initialized = true;
        }

        public void addCrystal(ICrystal crystal)
        {
            crystals.add(crystal);
            LogHelper.info("Added " + crystal.toString() + " to crystal list.");
        }

        public void removeCrystal(ICrystal crystal)
        {
            crystals.remove(crystal);
            LogHelper.info("Removed " + crystal.toString() + " from crystal list.");
        }

        public void readCrystal(NBTTagCompound tagCompound, ICrystal crystal)
        {
            crystals.add(tagCompound.getInteger(CrystalNames.NBT.INDEX), crystal);
            LogHelper.info("Reading crystal " + crystal.toString() + " from NBT");
        }

        public void writeCrystal(NBTTagCompound tagCompound, ICrystal crystal)
        {
            tagCompound.setInteger(CrystalNames.NBT.INDEX, crystals.indexOf(crystal));
            LogHelper.info("Writing crystal " + crystal.toString() + " to NBT");
        }

        public World getWorld()
        {
            return world;
        }

        public List getCrystals()
        {
            return crystals;
        }

        public void crystalDebugDump()
        {
            for (ICrystal crystal : crystals)
            {
                LogHelper.debug(crystal.toString());
            }
        }
    }
}
