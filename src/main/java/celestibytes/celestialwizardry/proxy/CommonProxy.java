package celestibytes.celestialwizardry.proxy;

import celestibytes.celestialwizardry.crystal.Crystals;
import celestibytes.celestialwizardry.reference.EventHandlers;
import celestibytes.celestialwizardry.reference.Names;
import celestibytes.celestialwizardry.registry.RuneRegistry;
import celestibytes.celestialwizardry.tileentity.TileEntityBell;
import celestibytes.celestialwizardry.tileentity.TileEntityWritingTable;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public abstract class CommonProxy implements IProxy
{
    @Override
    public void registerEventHandlers()
    {
        // Register entity event handler
        FMLCommonHandler.instance().bus().register(EventHandlers.Common.ENTITY_EVENT_HANDLER);
        MinecraftForge.EVENT_BUS.register(EventHandlers.Common.ENTITY_EVENT_HANDLER);

        // Register player event handler
        FMLCommonHandler.instance().bus().register(EventHandlers.Common.PLAYER_EVENT_HANDLER);
        MinecraftForge.EVENT_BUS.register(EventHandlers.Common.PLAYER_EVENT_HANDLER);

        // Register world event handler
        FMLCommonHandler.instance().bus().register(EventHandlers.Common.WORLD_EVENT_HANDLER);
        MinecraftForge.EVENT_BUS.register(EventHandlers.Common.WORLD_EVENT_HANDLER);

        // Register item expire event handler
        FMLCommonHandler.instance().bus().register(EventHandlers.Common.ITEM_EXPIRE_EVENT_HANDLER);
        MinecraftForge.EVENT_BUS.register(EventHandlers.Common.ITEM_EXPIRE_EVENT_HANDLER);

        // Register disconnect event handler (handles both server and client)
        FMLCommonHandler.instance().bus().register(EventHandlers.Common.DISCONNECT_EVENT_HANDLER);
        MinecraftForge.EVENT_BUS.register(EventHandlers.Common.DISCONNECT_EVENT_HANDLER);

        // Register config change event handler
        FMLCommonHandler.instance().bus().register(EventHandlers.Common.CONFIG_CHANGED_EVENT_HANDLER);

        // Register Crystal Event Handlers
        Crystals.proxy.registerEventHandlers();
    }

    @Override
    public void registerTileEntities()
    {
        GameRegistry.registerTileEntity(TileEntityWritingTable.class, "tile." + Names.Blocks.WRITING_TABLE);
        GameRegistry.registerTileEntity(TileEntityBell.class, "tile." + Names.Blocks.BELL);

        // Register crystal tile entities
        Crystals.proxy.registerTileEntities();
    }
    
    public void initializeRuneRegistries(){
    	RuneRegistry.initServerSide();
    }
}
