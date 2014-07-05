package celestialwizardry.crystal.proxy;

import celestialwizardry.crystal.reference.CrystalNames;
import celestialwizardry.crystal.tileentity.TileEntityCrystalNetwork;
import celestialwizardry.crystal.tileentity.TileEntityCrystalNetworkConductiveWeak;
import celestialwizardry.crystal.tileentity.TileEntityCrystalNetworkSolarWeak;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public abstract class CommonCrystalProxy implements ICrystalProxy
{
    @Override
    public void registerEventHandlers()
    {
        FMLCommonHandler.instance().bus().register(new TileEntityCrystalNetwork.CrystalNetworkEventHandler());
    }

    public void registerTileEntities()
    {
        GameRegistry.registerTileEntity(TileEntityCrystalNetworkConductiveWeak.class,
                                        "tile." + CrystalNames.Blocks.CRYSTAL_CONDUCTIVE_WEAK);
        GameRegistry.registerTileEntity(TileEntityCrystalNetworkSolarWeak.class,
                                        "tile." + CrystalNames.Blocks.CRYSTAL_SOLAR_WEAK);
    }
}
