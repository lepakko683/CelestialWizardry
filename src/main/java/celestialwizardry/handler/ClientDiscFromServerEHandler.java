package celestialwizardry.handler;

import celestialwizardry.api.event.CWRuneconfigResetEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent.ClientDisconnectionFromServerEvent;
import cpw.mods.fml.relauncher.Side;

public class ClientDiscFromServerEHandler {
	
	@SubscribeEvent
	public void onEvent(ClientDisconnectionFromServerEvent event) {
		FMLCommonHandler.instance().bus().post(new CWRuneconfigResetEvent(Side.CLIENT));
	}
}
