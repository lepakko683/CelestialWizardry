package celestialwizardry.crystal.proxy;

import celestialwizardry.client.render.crystal.RenderCrystalSimple;
import celestialwizardry.crystal.tileentity.TileEntityCrystalConductive;
import celestialwizardry.reference.Resources;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientCrystalProxy extends CommonCrystalProxy
{
    @Override
    public void registerRenderer()
    {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCrystalConductive.class, new RenderCrystalSimple(
                Resources.Models.Crystals.TEXTURE_CRYSTAL_CONDUCTIVE));
    }
}
