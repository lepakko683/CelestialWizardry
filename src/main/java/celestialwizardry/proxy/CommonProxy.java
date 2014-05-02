package celestialwizardry.proxy;

import celestialwizardry.reference.Names;
import celestialwizardry.tileentity.TileEntityWritingTable;
import cpw.mods.fml.common.registry.GameRegistry;

public abstract class CommonProxy implements IProxy
{
    public void registerTileEntities()
    {
        GameRegistry.registerTileEntity(TileEntityWritingTable.class, "tile." + Names.Blocks.WRITING_TABLE);
    }
}
