package celestialwizardry.crystal.proxy;

import celestialwizardry.crystal.client.render.RenderCrystalBlock;
import celestialwizardry.crystal.client.render.RenderCrystalSimple;
import celestialwizardry.crystal.reference.CrystalResources;
import celestialwizardry.crystal.tileentity.TileEntityCrystalConductive;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientCrystalProxy extends CommonCrystalProxy
{
    @Override
    public void registerEventHandlers()
    {
        // NO-OP
    }

    @Override
    public void registerRenderer()
    {
        RenderingRegistry.registerBlockHandler(new RenderCrystalBlock());

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCrystalConductive.class, new RenderCrystalSimple(
                CrystalResources.Models.TEXTURE_CRYSTAL_CONDUCTIVE));
    }
}
