package celestibytes.core.reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import celestibytes.core.handler.ConfigChangedEventHandler;

public class EventHandlers
{
    @SideOnly(Side.CLIENT)
    public static class Client
    {

    }

    public static class Common
    {
        public static final ConfigChangedEventHandler CONFIG_CHANGED_EVENT_HANDLER = new ConfigChangedEventHandler();
    }
}
