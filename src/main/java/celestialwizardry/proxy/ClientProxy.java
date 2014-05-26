package celestialwizardry.proxy;

import celestialwizardry.client.ItemRendererSpellBook;
import celestialwizardry.client.render.RenderOBJblock;
import celestialwizardry.client.render.RenderWritingTable;
import celestialwizardry.init.ModItems;
import celestialwizardry.reference.EventHandlers;
import celestialwizardry.reference.Settings;
import celestialwizardry.tileentity.TileEntityWritingTable;

import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;

public class ClientProxy extends CommonProxy
{
    @Override
    public void registerKeys()
    {

    }

    @Override
    public void registerRenderTickHandler()
    {
        FMLCommonHandler.instance().bus().register(EventHandlers.CLIENT_TICK_EVENT_HANDLER);
    }

    @Override
    public void registerRenderer()
    {
        if (Settings.spellBook3dModel)
        {
            MinecraftForgeClient.registerItemRenderer(ModItems.spellBook, new ItemRendererSpellBook());
        }
        RenderingRegistry.registerBlockHandler(new RenderOBJblock());

//        RenderingRegistry.registerEntityRenderingHandler(EntityLivingOre.class, new RenderOreGolem());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWritingTable.class, new RenderWritingTable());
    }
}
