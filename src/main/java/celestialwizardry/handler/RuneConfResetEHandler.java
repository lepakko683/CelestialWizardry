package celestialwizardry.handler;

import celestialwizardry.api.event.CWRuneconfigResetEvent;
import celestialwizardry.registry.RuneRegistry;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;

public class RuneConfResetEHandler
{

    @SubscribeEvent
    public void onEvent(CWRuneconfigResetEvent event)
    {
        if (event.side == Side.CLIENT)
        {
            RuneRegistry.reset();
            ClientRuneConfigurationHandler.reset();
            return;
        }

        if (event.side == Side.SERVER)
        {

        }
    }
}
