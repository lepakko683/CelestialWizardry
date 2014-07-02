package celestialwizardry.crystal.proxy;

import celestialwizardry.crystal.init.CrystalBlocks;
import celestialwizardry.crystal.reference.CrystalNames;
import celestialwizardry.crystal.tileentity.TileEntityCrystalConductive;
import celestialwizardry.reference.Names;

import cpw.mods.fml.common.registry.GameRegistry;

public abstract class CommonCrystalProxy implements ICrystalProxy
{
    public void registerTileEntities()
    {
        GameRegistry.registerTileEntity(TileEntityCrystalConductive.class, "tile." + CrystalNames.Blocks.CRYSTAL_CONDUCTIVE);
    }
}
