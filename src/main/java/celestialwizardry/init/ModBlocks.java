package celestialwizardry.init;

import celestialwizardry.block.BlockCW;
import celestialwizardry.block.BlockWritingTable;
import celestialwizardry.reference.Names;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Just a class containing all mod blocks
 */
public class ModBlocks
{
    public static final BlockCW writingTable = new BlockWritingTable();

    public static void init()
    {
        GameRegistry.registerBlock(writingTable, "tile." + Names.Blocks.WRITING_TABLE);
    }
}
