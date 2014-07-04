package celestialwizardry.crystal.init;

import celestialwizardry.crystal.block.BlockCrystal;
import celestialwizardry.crystal.block.BlockCrystalConductiveWeak;
import celestialwizardry.crystal.reference.CrystalNames;
import celestialwizardry.init.ModBlocks;
import celestialwizardry.item.ItemBlockCW;

public class CrystalBlocks
{
    public static final BlockCrystal crystalConductiveWeak = new BlockCrystalConductiveWeak();

    public static void init()
    {
        ModBlocks.register(crystalConductiveWeak, CrystalNames.Blocks.CRYSTAL_CONDUCTIVE_WEAK, ItemBlockCW.class);
    }
}
