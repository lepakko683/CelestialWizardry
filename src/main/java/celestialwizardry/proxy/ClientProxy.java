package celestialwizardry.proxy;

import celestialwizardry.client.ItemRendererSpellBook;
import celestialwizardry.client.render.RenderOBJBlock;
import celestialwizardry.client.render.RenderWritingTable;
import celestialwizardry.client.settings.Keybindings;
import celestialwizardry.init.ModItems;
import celestialwizardry.reference.EventHandlers;
import celestialwizardry.reference.Settings;
import celestialwizardry.tileentity.TileEntityWritingTable;

import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy
{
    @Override
    public void registerEventHandlers()
    {
        super.registerEventHandlers();

        FMLCommonHandler.instance().bus().register(EventHandlers.CLIENT_TICK_EVENT_HANDLER);
    }

    @Override
    public void registerKeyBindings()
    {
        ClientRegistry.registerKeyBinding(Keybindings.cast);
        ClientRegistry.registerKeyBinding(Keybindings.change);
    }

    @Override
    public void registerRenderer()
    {
        if (Settings.spellBook3dModel)
        {
            MinecraftForgeClient.registerItemRenderer(ModItems.spellBook, new ItemRendererSpellBook());
        }

        RenderingRegistry.registerBlockHandler(new RenderOBJBlock());

//        RenderingRegistry.registerEntityRenderingHandler(EntityLivingOre.class, new RenderOreGolem());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWritingTable.class, new RenderWritingTable());
    }
}
