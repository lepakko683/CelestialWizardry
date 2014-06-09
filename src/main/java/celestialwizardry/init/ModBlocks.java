package celestialwizardry.init;

import celestialwizardry.block.BlockBell;
import celestialwizardry.block.BlockCW;
import celestialwizardry.block.BlockLivingOre;
import celestialwizardry.block.BlockMagicalStone;
import celestialwizardry.block.BlockWritingTable;
import celestialwizardry.item.ItemBlockBell;
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
    public static final BlockCW bell = new BlockBell();
    public static final BlockCW magicalStone = new BlockMagicalStone();

    public static void init()
    {
        register(writingTable, Names.Blocks.WRITING_TABLE);
        register(livingOre, Names.Blocks.LIVING_ORE);
        register(bell, Names.Blocks.BELL, ItemBlockBell.class);
        register(magicalStone, Names.Blocks.MAGICAL_STONE);
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
