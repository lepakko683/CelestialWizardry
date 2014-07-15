package celestibytes.core.registry;

import cpw.mods.fml.common.FMLCommonHandler;

import celestibytes.core.mod.IMod;
import celestibytes.core.mod.version.VersionManager;

public final class VersionManagerRegistry
{
    private VersionManagerRegistry()
    {

    }

    public static void registerVersionManager(VersionManager manager, IMod mod)
    {
        if (mod.allowVersionCheck())
        {
            FMLCommonHandler.instance().bus().register(manager);
            mod.setRegistered(true);
        }
    }

    public static void unregisterVersionManager(VersionManager manager, IMod mod)
    {
        if (mod.isRegistered())
        {
            FMLCommonHandler.instance().bus().unregister(manager);
            mod.setRegistered(false);
        }
    }
}
