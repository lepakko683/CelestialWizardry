package celestialwizardry.crystal.handler;

import net.minecraftforge.event.world.WorldEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class CrystalWorldEventHandler
{
    @SubscribeEvent
    public void onSave(WorldEvent.Save event)
    {
        // TODO Save crystal network here event.world.getWorldInfo().getNBTTagCompound().
    }
}
