package celestibytes.celestialwizardry.crystal.tileentity;

import celestibytes.celestialwizardry.crystal.api.crystal.EnergyPacket;
import celestibytes.celestialwizardry.crystal.api.crystal.ICrystal;
import celestibytes.celestialwizardry.crystal.api.crystal.ICrystalNetwork;
import celestibytes.celestialwizardry.crystal.event.CrystalEvent;
import celestibytes.celestialwizardry.crystal.reference.CrystalNames;
import celestibytes.celestialwizardry.util.LogHelper;

import net.minecraft.nbt.NBTTagCompound;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

public abstract class TileEntityCrystalNetwork extends TileEntityCrystal implements ICrystalNetwork
{
    protected static List<ICrystal> crystals = new ArrayList<ICrystal>();
    protected int cooldown = 0;
    protected ICrystalNetwork dest = null;

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
     * Called when this {@link ICrystal} sends a {@link celestibytes.celestialwizardry.crystal.api.crystal.EnergyPacket}.
     *
     * @param packet the sent {@link celestibytes.celestialwizardry.crystal.api.crystal.EnergyPacket}
     */
    @Override
    public void onPacketSent(EnergyPacket packet)
    {
        if (cooldown > -1)
        {
            cooldown = defaultCooldown();
        }
    }

    /**
     * Called when this {@link ICrystal} receives a {@link celestibytes.celestialwizardry.crystal.api.crystal.EnergyPacket}.
     *
     * @param packet the received {@link celestibytes.celestialwizardry.crystal.api.crystal.EnergyPacket}
     */
    @Override
    public void onPacketReceived(EnergyPacket packet)
    {
        LogHelper.debug(toString() + " received a EnergyPacket " + packet.toString() + " from " + packet.getCompiler().toString());
    }

    public boolean canSend()
    {
        return cooldown == 0;
    }

    public abstract int defaultCooldown();

    public abstract void setDest(int x, int y, int z);

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

    @Override
    public void updateEntity()
    {
        super.updateEntity();

        if (!worldObj.isRemote)
        {
            if (cooldown > 0)
            {
                cooldown--;
            }
        }
    }

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

    public static class CrystalNetworkEventHandler
    {
        @SubscribeEvent
        public void onCrystalBreakEvent(CrystalEvent.CrystalBreakEvent event)
        {
            ((ICrystalNetwork) event.crystal).onRemoved();
        }

        @SubscribeEvent
        public void onCrystalPlacedEvent(CrystalEvent.CrystalPlacedEvent event)
        {
            ((ICrystalNetwork) event.crystal).onAdded();
        }
    }
}
