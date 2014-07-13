package celestibytes.celestialwizardry.proxy;

import celestibytes.celestialwizardry.client.ItemRendererSpellBook;
import celestibytes.celestialwizardry.client.render.RenderBell;
import celestibytes.celestialwizardry.client.render.RenderEntityBell;
import celestibytes.celestialwizardry.client.render.RenderMagicalStone;
import celestibytes.celestialwizardry.client.render.RenderOBJBlock;
import celestibytes.celestialwizardry.client.render.RenderWritingTable;
import celestibytes.celestialwizardry.client.settings.KeyBindings;
import celestibytes.celestialwizardry.entity.EntityBell;
import celestibytes.celestialwizardry.init.ModItems;
import celestibytes.celestialwizardry.reference.EventHandlers;
import celestibytes.celestialwizardry.reference.Settings;
import celestibytes.celestialwizardry.registry.RuneRegistry;
import celestibytes.celestialwizardry.tileentity.TileEntityBell;
import celestibytes.celestialwizardry.tileentity.TileEntityWritingTable;
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

        // Register client tick handler
        FMLCommonHandler.instance().bus().register(EventHandlers.Client.CLIENT_TICK_EVENT_HANDLER);

        // Register key input handler
        FMLCommonHandler.instance().bus().register(EventHandlers.Client.KEY_INPUT_EVENT_HANDLER);

        // Register client render tick handler
        FMLCommonHandler.instance().bus().register(EventHandlers.Client.CLIENT_RENDER_TICK_EVENT_HANDLER);
    }

    @Override
    public void registerKeyBindings()
    {
        ClientRegistry.registerKeyBinding(KeyBindings.cast);
        ClientRegistry.registerKeyBinding(KeyBindings.change);
    }

    @Override
    public void registerRenderer()
    {
        if (Settings.spellBook3dModel)
        {
            MinecraftForgeClient.registerItemRenderer(ModItems.spellBook, new ItemRendererSpellBook());
        }

        RenderingRegistry.registerBlockHandler(new RenderOBJBlock());
        RenderingRegistry.registerBlockHandler(new RenderMagicalStone());

        RenderingRegistry.registerEntityRenderingHandler(EntityBell.class, new RenderEntityBell());
//        RenderingRegistry.registerEntityRenderingHandler(EntityLivingOre.class, new RenderOreGolem());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWritingTable.class, new RenderWritingTable());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBell.class, new RenderBell());
    }
    
    public void initializeRuneRegistries(){
    	super.initializeRuneRegistries();
    	RuneRegistry.initClientSide();
    }

}
