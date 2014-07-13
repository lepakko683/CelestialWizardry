package celestibytes.core.registry;

import cpw.mods.fml.common.FMLCommonHandler;

import celestibytes.core.mod.version.VersionManager;

public class VersionManagerRegistry
{
    public static void registerVersionManager(VersionManager manager)
    {
        FMLCommonHandler.instance().bus().register(manager);
    }

    public static void unregisterVersionManager(VersionManager manager)
    {
        FMLCommonHandler.instance().bus().unregister(manager);
    }
}
