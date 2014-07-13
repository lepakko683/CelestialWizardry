package celestibytes.celestialwizardry.init;

import celestibytes.celestialwizardry.block.BlockBell;
import celestibytes.celestialwizardry.block.BlockContainedCrystal;
import celestibytes.celestialwizardry.block.BlockLivingOre;
import celestibytes.celestialwizardry.block.BlockMagicalStone;
import celestibytes.celestialwizardry.block.BlockWritingTable;
import celestibytes.celestialwizardry.item.ItemBlockBell;
import celestibytes.celestialwizardry.item.ItemBlockCW;
import celestibytes.celestialwizardry.reference.Names;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Just a class containing all mod blocks
 */
public class ModBlocks
{
    public static final BlockWritingTable writingTable = new BlockWritingTable();
    public static final BlockLivingOre livingOre = new BlockLivingOre();
    public static final BlockBell bell = new BlockBell();
    public static final BlockMagicalStone magicalStone = new BlockMagicalStone();
    public static final BlockContainedCrystal containedCrystal = new BlockContainedCrystal();

    public static void init()
    {
        register(writingTable, Names.Blocks.WRITING_TABLE);
        register(livingOre, Names.Blocks.LIVING_ORE);
        register(bell, Names.Blocks.BELL, ItemBlockBell.class);
        register(magicalStone, Names.Blocks.MAGICAL_STONE);
        register(containedCrystal, Names.Blocks.CONTAINED_CRYSTAL);
    }

    private static void register(Block block, String name)
    {
        register(block, name, ItemBlockCW.class);
    }

    public static void register(Block block, String name, Class<? extends ItemBlock> item)
    {
        GameRegistry.registerBlock(block, item, "tile." + name);
    }
}
