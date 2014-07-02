package celestialwizardry.crystal.handler;

import celestialwizardry.crystal.CrystalNetwork;
import celestialwizardry.crystal.CrystalSession;
import celestialwizardry.crystal.Crystals;
import celestialwizardry.crystal.reference.CrystalNames;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.world.WorldEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class CrystalWorldEventHandler
{
    @SubscribeEvent
    public void onLoad(WorldEvent.Load event)
    {
        NBTTagCompound info = event.world.getWorldInfo().getNBTTagCompound();

        CrystalNetwork network;

        if (info.hasKey(CrystalNames.NBT.HAS_CNETWORK))
        {
            network = CrystalNetwork.getFromWorld(event.world);
        }
        else
        {
            network = CrystalNetwork.create(event.world);
        }

        Crystals.session = new CrystalSession(network);
    }

    @SubscribeEvent
    public void onSave(WorldEvent.Save event)
    {
        // TODO Save crystal network here event.world.getWorldInfo().getNBTTagCompound().
    }
}
