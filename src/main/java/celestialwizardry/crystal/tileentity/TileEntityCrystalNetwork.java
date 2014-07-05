package celestialwizardry.crystal.tileentity;

import celestialwizardry.crystal.api.crystal.EnergyPacket;
import celestialwizardry.crystal.api.crystal.ICrystal;
import celestialwizardry.crystal.api.crystal.ICrystalNetwork;
import celestialwizardry.crystal.event.CrystalEvent;
import celestialwizardry.crystal.reference.CrystalNames;
import celestialwizardry.util.LogHelper;

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
     * Called when this {@link ICrystal} sends a {@link celestialwizardry.crystal.api.crystal.EnergyPacket}.
     *
     * @param packet the sent {@link celestialwizardry.crystal.api.crystal.EnergyPacket}
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
     * Called when this {@link ICrystal} receives a {@link celestialwizardry.crystal.api.crystal.EnergyPacket}.
     *
     * @param packet the received {@link celestialwizardry.crystal.api.crystal.EnergyPacket}
     */
    @Override
    public void onPacketReceived(EnergyPacket packet)
    {
        // TODO Debug level
        LogHelper.info(toString() + " received a EnergyPacket " + packet.toString() + " from " + packet.getCompiler().toString());
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
        LogHelper.debug("Added " + crystal.toString() + " to crystal list.");
    }

    public static void removeCrystal(ICrystal crystal)
    {
        crystals.remove(crystal);
        LogHelper.debug("Removed " + crystal.toString() + " from crystal list.");
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
