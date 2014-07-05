package celestialwizardry.crystal.tileentity;

import celestialwizardry.crystal.api.crystal.EnergyPacket;
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

    /**
     * Sends a {@link EnergyPacket} to the target {@link ICrystal}
     */
    @Override
    public abstract void sendPacket();

    /**
     * Called when this {@link ICrystal} sends a {@link celestialwizardry.crystal.api.crystal.EnergyPacket}.
     *
     * @param packet the sent {@link celestialwizardry.crystal.api.crystal.EnergyPacket}
     */
    @Override
    public void onPacketSent(EnergyPacket packet)
    {

    }

    /**
     * Called when this {@link ICrystal} receives a {@link celestialwizardry.crystal.api.crystal.EnergyPacket}.
     *
     * @param packet the received {@link celestialwizardry.crystal.api.crystal.EnergyPacket}
     */
    @Override
    public void onPacketReceived(EnergyPacket packet)
    {

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
        LogHelper.debug("Added " + crystal.toString() + " to crystal list.");
    }

    public static void removeCrystal(ICrystal crystal)
    {
        crystals.remove(crystal);
        LogHelper.debug("Removed " + crystal.toString() + " from crystal list.");
    }
}
