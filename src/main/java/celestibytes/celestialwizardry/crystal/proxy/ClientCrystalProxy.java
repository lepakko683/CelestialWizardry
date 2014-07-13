package celestibytes.celestialwizardry.crystal.proxy;

import celestibytes.celestialwizardry.crystal.client.render.RenderCrystalBlock;
import celestibytes.celestialwizardry.crystal.client.render.RenderCrystalSimple;
import celestibytes.celestialwizardry.crystal.reference.CrystalResources;
import celestibytes.celestialwizardry.crystal.tileentity.TileEntityCrystalNetworkConductiveWeak;
import celestibytes.celestialwizardry.crystal.tileentity.TileEntityCrystalNetworkSolarWeak;

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

        ClientRegistry
                .bindTileEntitySpecialRenderer(TileEntityCrystalNetworkConductiveWeak.class, new RenderCrystalSimple(
                        CrystalResources.Models.TEXTURE_CRYSTAL_CONDUCTIVE_WEAK));
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCrystalNetworkSolarWeak.class, new RenderCrystalSimple(
                CrystalResources.Models.TEXTURE_CRYSTAL_SOLAR_WEAK));
    }
}
