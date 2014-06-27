package celestialwizardry.init;

import celestialwizardry.block.BlockBell;
import celestialwizardry.block.BlockContainedCrystal;
import celestialwizardry.block.BlockCrystal;
import celestialwizardry.block.BlockCrystalConductive;
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
    public static final BlockWritingTable writingTable = new BlockWritingTable();
    public static final BlockLivingOre livingOre = new BlockLivingOre();
    public static final BlockBell bell = new BlockBell();
    public static final BlockMagicalStone magicalStone = new BlockMagicalStone();
    public static final BlockCrystal crystalConductive = new BlockCrystalConductive();
    public static final BlockContainedCrystal containedCrystal = new BlockContainedCrystal();

    public static void init()
    {
        register(writingTable, Names.Blocks.WRITING_TABLE);
        register(livingOre, Names.Blocks.LIVING_ORE);
        register(bell, Names.Blocks.BELL, ItemBlockBell.class);
        register(magicalStone, Names.Blocks.MAGICAL_STONE);
        register(crystalConductive, Names.Blocks.CRYSTAL_CONDUCTIVE);
        register(containedCrystal, Names.Blocks.CONTAINED_CRYSTAL);
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
