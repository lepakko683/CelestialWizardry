package celestialwizardry.crystal.item.crafting;

import celestialwizardry.crystal.init.CrystalItems;
import celestialwizardry.init.ModBlocks;
import celestialwizardry.init.ModItems;
import celestialwizardry.reference.Settings;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public class RecipesCrystalVanilla
{
    static boolean hc = Settings.hardcoreRecipes;

    public static void init()
    {
        // Initialize block crafting recipes
        initBlockRecipes();

        // Initialize item crafting recipes
        initItemRecipes();
    }

    private static void initBlockRecipes()
    {
    }

    private static void initItemRecipes()
    {
        /* ======================================== CRYSTAL START ===================================== */

        // TODO These aren't expensive enough >:)

        // Tier 1
        add(CrystalItems.stoneCrystal, "scs", "cec", "scs", 's', ModItems.magicalPebble, 'c',
            hc ? Items.diamond : ModBlocks.magicalStone, 'e', hc ? Items.emerald : Items.diamond);

        // Tier 2
        add(CrystalItems.mysticalCrystal, "scs", "cec", "scs", 's', ModBlocks.magicalStone, 'c',
            CrystalItems.stoneCrystal, 'e', Items.emerald);

        // Tier 3
        add(CrystalItems.pureCrystal, "scs", "cec", "scs", 's', CrystalItems.stoneCrystal, 'c',
            CrystalItems.mysticalCrystal, 'e', Blocks.emerald_block);

        // Tier 4
        add(CrystalItems.celestialCrystal, "scs", "cec", "scs", 's', CrystalItems.mysticalCrystal, 'c',
            CrystalItems.pureCrystal, 'e', Items.nether_star);

        // Tier 5
        add(CrystalItems.perfectCrystal, "scs", "cec", "scs", 's', CrystalItems.pureCrystal, 'c',
            CrystalItems.celestialCrystal, 'e', ModItems.netherPearl);

        /* ======================================== CRYSTAL END ===================================== */

        /* ======================================== MATRIX START ===================================== */

        // Tier 1
        add(new ItemStack(CrystalItems.matrix, 1, 0), "scs", "cec", "scs", 's', ModItems.magicalPebble, 'c',
            CrystalItems.stoneCrystal, 'e', Items.ender_pearl);

        // Tier 2
        add(new ItemStack(CrystalItems.matrix, 1, 1), "scs", "cmc", "scs", 's', ModBlocks.magicalStone, 'c',
            CrystalItems.mysticalCrystal, 'm', new ItemStack(CrystalItems.matrix, 1, 0));

        // Tier 3
        add(new ItemStack(CrystalItems.matrix, 1, 2), "scs", "cmc", "scs", 's', CrystalItems.mysticalCrystal, 'c',
            CrystalItems.pureCrystal, 'm', new ItemStack(CrystalItems.matrix, 1, 1));

        // Tier 4

        // Tier 5

        /* ======================================== MATRIX END ===================================== */
    }

    private static void add(ItemStack output, Object... input)
    {
        GameRegistry.addRecipe(new ShapedOreRecipe(output, input));
    }

    private static void addShapeless(ItemStack output, Object... input)
    {
        GameRegistry.addRecipe(new ShapelessOreRecipe(output, input));
    }

    private static void addCompress(ItemStack big, ItemStack small)
    {
        addShapeless(big, small, small, small, small, small, small, small, small, small);
        addShapeless(new ItemStack(small.getItem(), 9, small.getItemDamage()), big);
    }
}
