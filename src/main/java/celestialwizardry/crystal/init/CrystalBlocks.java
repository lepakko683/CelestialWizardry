package celestialwizardry.crystal.init;

import celestialwizardry.crystal.block.BlockCrystalConductiveWeak;
import celestialwizardry.crystal.block.BlockCrystalSolarWeak;
import celestialwizardry.crystal.item.ItemBlockCrystal;
import celestialwizardry.crystal.reference.CrystalNames;
import celestialwizardry.init.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class CrystalBlocks
{
    public static final BlockCrystalConductiveWeak crystalConductiveWeak = new BlockCrystalConductiveWeak();
    public static final BlockCrystalSolarWeak crystalSolarWeak = new BlockCrystalSolarWeak();

    public static void init()
    {
        register(crystalConductiveWeak, CrystalNames.Blocks.CRYSTAL_CONDUCTIVE_WEAK);
        register(crystalSolarWeak, CrystalNames.Blocks.CRYSTAL_SOLAR_WEAK);
    }

    private static void register(Block block, String name)
    {
        register(block, name, ItemBlockCrystal.class);
    }

    private static void register(Block block, String name, Class<? extends ItemBlock> item)
    {
        ModBlocks.register(block, name, item);
    }
}
