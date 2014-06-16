package celestialwizardry.proxy;

import celestialwizardry.client.ItemRendererSpellBook;
import celestialwizardry.client.particle.EntityWispFX;
import celestialwizardry.client.particle.FXEntitySparkle;
import celestialwizardry.client.render.RenderBell;
import celestialwizardry.client.render.RenderEntityBell;
import celestialwizardry.client.render.RenderMagicalStone;
import celestialwizardry.client.render.RenderOBJBlock;
import celestialwizardry.client.render.RenderWritingTable;
import celestialwizardry.client.render.crystal.RenderCrystalSimple;
import celestialwizardry.client.settings.KeyBindings;
import celestialwizardry.entity.EntityBell;
import celestialwizardry.init.ModItems;
import celestialwizardry.reference.EventHandlers;
import celestialwizardry.reference.Resources;
import celestialwizardry.reference.Settings;
import celestialwizardry.tileentity.TileEntityBell;
import celestialwizardry.tileentity.TileEntityCrystalConductive;
import celestialwizardry.tileentity.TileEntityWritingTable;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
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
        FMLCommonHandler.instance().bus().register(EventHandlers.CLIENT_TICK_EVENT_HANDLER);

        // Register key input handler
        FMLCommonHandler.instance().bus().register(EventHandlers.KEY_INPUT_EVENT_HANDLER);

        // Register client render tick handler
        FMLCommonHandler.instance().bus().register(EventHandlers.CLIENT_RENDER_TICK_EVENT_HANDLER);
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

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCrystalConductive.class, new RenderCrystalSimple(
                Resources.Models.Crystals.TEXTURE_CRYSTAL_CONDUCTIVE));
    }

    @Override
    public void sparkleFX(World world, double x, double y, double z, float r, float g, float b, float size, int m)
    {
        sparkleFX(world, x, y, z, r, g, b, size, m, false);
    }

    @Override
    public void sparkleFX(World world, double x, double y, double z, float r, float g, float b, float size, int m,
                          boolean fake)
    {
        FXEntitySparkle sparkle = new FXEntitySparkle(world, x, y, z, size, r, g, b, m);
        sparkle.fake = sparkle.noClip = fake;
        Minecraft.getMinecraft().effectRenderer.addEffect(sparkle);
    }

    private static boolean distanceLimit = true;

    @Override
    public void setWispFXDistanceLimit(boolean limit)
    {
        distanceLimit = limit;
    }

    @Override
    public void wispFX(World world, double x, double y, double z, float r, float g, float b, float size)
    {
        wispFX(world, x, y, z, r, g, b, size, 0F);
    }

    @Override
    public void wispFX(World world, double x, double y, double z, float r, float g, float b, float size, float gravity)
    {
        wispFX(world, x, y, z, r, g, b, size, gravity, 1F);
    }

    @Override
    public void wispFX(World world, double x, double y, double z, float r, float g, float b, float size, float gravity,
                       float maxAgeMul)
    {
        wispFX(world, x, y, z, r, g, b, size, 0, -gravity, 0, maxAgeMul);
    }

    @Override
    public void wispFX(World world, double x, double y, double z, float r, float g, float b, float size, float motionx,
                       float motiony, float motionz)
    {
        wispFX(world, x, y, z, r, g, b, size, motionx, motiony, motionz, 1F);
    }

    @Override
    public void wispFX(World world, double x, double y, double z, float r, float g, float b, float size, float motionx,
                       float motiony, float motionz, float maxAgeMul)
    {
        EntityWispFX wisp = new EntityWispFX(world, x, y, z, size, r, g, b, distanceLimit, maxAgeMul);
        wisp.motionX = motionx;
        wisp.motionY = motiony;
        wisp.motionZ = motionz;

        Minecraft.getMinecraft().effectRenderer.addEffect(wisp);
    }

}
