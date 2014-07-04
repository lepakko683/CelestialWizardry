package celestialwizardry.crystal.tileentity;

import celestialwizardry.crystal.api.crystal.ICrystal;
import celestialwizardry.crystal.api.crystal.ICrystalNetwork;
import celestialwizardry.crystal.reference.CrystalNames;
import celestialwizardry.util.LogHelper;

import net.minecraft.nbt.NBTTagCompound;

import java.util.ArrayList;
import java.util.List;

public abstract class TileEntityCrystalNetwork extends TileEntityCrystal implements ICrystalNetwork
{
    protected static List<ICrystal> crystals = new ArrayList<ICrystal>();

    /* ======================================== INetworkCrystal START ===================================== */

    @Override
    public void onAdded()
    {
        addCrystal(this);
    }

    @Override
    public void onRemoved()
    {
        removeCrystal(this);
    }

    /* ======================================== INetworkCrystal END ===================================== */

    /* ======================================== TileEntity START ===================================== */

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);
        crystals.add(nbtTagCompound.getInteger(CrystalNames.NBT.INDEX), this);
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);
        nbtTagCompound.setInteger(CrystalNames.NBT.INDEX, crystals.indexOf(this));
    }

    /* ======================================== TileEntity END ===================================== */

    public static void addCrystal(ICrystal crystal)
    {
        crystals.add(crystal);
        LogHelper.info("Added " + crystal.toString() + " to crystal list.");
    }

    public static void removeCrystal(ICrystal crystal)
    {
        crystals.remove(crystal);
        LogHelper.info("Removed " + crystal.toString() + " from crystal list.");
    }
}
