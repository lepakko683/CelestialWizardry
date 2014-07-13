package celestibytes.celestialwizardry.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent.ClientDisconnectionFromServerEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent.ServerDisconnectionFromClientEvent;

public class DisconnectEHandler
{

    @SubscribeEvent
    public void onEventClient(ClientDisconnectionFromServerEvent event)
    {
//		FMLCommonHandler.instance().bus().post(new CWRuneconfigResetEvent(Side.CLIENT));

        ClientRuneConfigurationHandler.reset();
        System.out.println("Client runeconfig resetting...");
    }

    @SubscribeEvent
    public void onEventServer(ServerDisconnectionFromClientEvent event)
    {
//		FMLCommonHandler.instance().bus().post(new CWRuneconfigResetEvent(Side.SERVER));
//		ServerRuneConfigurationHandler.reset();
    }
}
