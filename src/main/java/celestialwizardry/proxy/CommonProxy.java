package celestialwizardry.proxy;

import celestialwizardry.reference.EventHandlers;
import celestialwizardry.reference.Names;
import celestialwizardry.tileentity.TileEntityBell;
import celestialwizardry.tileentity.TileEntityWritingTable;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public abstract class CommonProxy implements IProxy
{
    @Override
    public void registerEventHandlers()
    {
        // Register entity event handler
        FMLCommonHandler.instance().bus().register(EventHandlers.ENTITY_EVENT_HANDLER);
        MinecraftForge.EVENT_BUS.register(EventHandlers.ENTITY_EVENT_HANDLER);

        // Register player event handler
        FMLCommonHandler.instance().bus().register(EventHandlers.PLAYER_EVENT_HANDLER);
        MinecraftForge.EVENT_BUS.register(EventHandlers.PLAYER_EVENT_HANDLER);
        
        // Register world event handler
        FMLCommonHandler.instance().bus().register(EventHandlers.WORLD_EVENT_HANDLER);
        MinecraftForge.EVENT_BUS.register(EventHandlers.WORLD_EVENT_HANDLER);
        
        // Register item expire event handler
        FMLCommonHandler.instance().bus().register(EventHandlers.ITEM_EXPIRE_EVENT_HANDLER);
        MinecraftForge.EVENT_BUS.register(EventHandlers.ITEM_EXPIRE_EVENT_HANDLER);
    }

    public void registerTileEntities()
    {
        GameRegistry.registerTileEntity(TileEntityWritingTable.class, "tile." + Names.Blocks.WRITING_TABLE);
        GameRegistry.registerTileEntity(TileEntityBell.class, "tile." + Names.Blocks.BELL);
    }
}
