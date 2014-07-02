package celestialwizardry.crystal.proxy;

import celestialwizardry.crystal.reference.CrystalEventHandlers;
import celestialwizardry.crystal.reference.CrystalNames;
import celestialwizardry.crystal.tileentity.TileEntityCrystalConductive;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public abstract class CommonCrystalProxy implements ICrystalProxy
{
    @Override
    public void registerEventHandlers()
    {
        // Register world event handler
        FMLCommonHandler.instance().bus().register(CrystalEventHandlers.Common.CRYSTAL_WORLD_EVENT_HANDLER);
    }

    public void registerTileEntities()
    {
        GameRegistry.registerTileEntity(TileEntityCrystalConductive.class,
                                        "tile." + CrystalNames.Blocks.CRYSTAL_CONDUCTIVE);
    }
}
