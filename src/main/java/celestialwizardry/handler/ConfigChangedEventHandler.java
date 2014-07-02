package celestialwizardry.handler;

import celestialwizardry.CelestialWizardry;
import celestialwizardry.config.SettingHandler;
import celestialwizardry.reference.Reference;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ConfigChangedEventHandler
{
    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (event.modID.equalsIgnoreCase(Reference.MOD_ID))
        {
            SettingHandler.sync();
        }
    }
}
