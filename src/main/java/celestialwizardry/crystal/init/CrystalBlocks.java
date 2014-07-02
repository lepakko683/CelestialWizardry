package celestialwizardry.crystal.init;

import celestialwizardry.crystal.block.BlockCrystal;
import celestialwizardry.crystal.block.BlockCrystalConductive;
import celestialwizardry.crystal.reference.CrystalNames;
import celestialwizardry.init.ModBlocks;
import celestialwizardry.item.ItemBlockCW;
import celestialwizardry.reference.Names;

public class CrystalBlocks
{
    public static final BlockCrystal crystalConductive = new BlockCrystalConductive();

    public static void init()
    {
        ModBlocks.register(crystalConductive, CrystalNames.Blocks.CRYSTAL_CONDUCTIVE, ItemBlockCW.class);
    }
}
