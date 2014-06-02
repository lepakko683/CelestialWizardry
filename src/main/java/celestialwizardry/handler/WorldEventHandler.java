package celestialwizardry.handler;

import net.minecraftforge.event.world.WorldEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class WorldEventHandler {
	
	@SubscribeEvent
	public void onWorldSave(WorldEvent.Save event) {
//		GlobalRuneConfigurationHandler.saveConfigIfNeeded();
	}
}
