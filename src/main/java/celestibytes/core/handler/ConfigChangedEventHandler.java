package celestibytes.core.handler;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

import celestibytes.core.config.SettingHandler;
import celestibytes.core.reference.Reference;

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
