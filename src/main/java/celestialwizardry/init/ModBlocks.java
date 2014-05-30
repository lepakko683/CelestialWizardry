package celestialwizardry.init;

import celestialwizardry.block.BlockCW;
import celestialwizardry.block.BlockLivingOre;
import celestialwizardry.block.BlockWritingTable;
import celestialwizardry.item.ItemBlockCW;
import celestialwizardry.reference.Names;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Just a class containing all mod blocks
 */
public class ModBlocks
{
    public static final BlockCW writingTable = new BlockWritingTable();
    public static final BlockCW livingOre = new BlockLivingOre();

    public static void init()
    {
        register(writingTable, Names.Blocks.WRITING_TABLE);
        register(livingOre, Names.Blocks.LIVING_ORE);
    }

    private static void register(Block block, String name)
    {
        register(block, name, ItemBlockCW.class);
    }

    private static void register(Block block, String name, Class<? extends ItemBlock> item)
    {
        GameRegistry.registerBlock(block, item, "tile." + name);
    }
}
