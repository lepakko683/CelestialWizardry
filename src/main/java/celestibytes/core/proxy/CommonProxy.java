package celestibytes.core.proxy;

import cpw.mods.fml.common.FMLCommonHandler;

import celestibytes.core.reference.EventHandlers;

public abstract class CommonProxy implements IProxy
{
    @Override
    public void registerEventHandlers()
    {
        // Register config change event handler
        FMLCommonHandler.instance().bus().register(EventHandlers.Common.CONFIG_CHANGED_EVENT_HANDLER);
    }
}
