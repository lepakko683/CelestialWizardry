package celestialwizardry.item.crafting;

import celestialwizardry.init.ModBlocks;
import celestialwizardry.init.ModItems;
import celestialwizardry.reference.Settings;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public class RecipesVanilla
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
        // Writing table
        add(new ItemStack(ModBlocks.writingTable), " l ", "bpb", "s s", 'l', Items.leather, 'b', "slabWood", 'p',
            "plankWood", 's', "stairWood");
    }

    private static void initItemRecipes()
    {
        // Scroll
        add(new ItemStack(ModItems.scroll), "p", "p", "p", 'p', Items.paper);

        // Magical Ink
        addShapeless(new ItemStack(ModItems.magicalInk), new ItemStack(Items.dye, 1, 4), "dyeBlack", "dyeBlack",
                     new ItemStack(Items.potionitem, 1, 0));

        // Magical Stone <-> Magical Pebble
        addCompress(new ItemStack(ModBlocks.magicalStone), ModItems.magicalPebble);

        // Nether Pearl
        add(ModItems.netherPearl, " s ", "sts", " s ", 's', hc ? Items.nether_star : Items.ghast_tear, 't', hc ? Items.ghast_tear : Items.nether_star);

        /* ======================================== CRYSTAL START ===================================== */

        // TODO These aren't expensive enough >:)

        // Tier 1
        add(ModItems.stoneCrystal, "scs", "cec", "scs", 's', ModItems.magicalPebble, 'c', hc ? Items.diamond : ModBlocks.magicalStone, 'e', hc ? Items.emerald : Items.diamond);

        // Tier 2
        add(ModItems.mysticalCrystal, "scs", "cec", "scs", 's', ModBlocks.magicalStone, 'c', ModItems.stoneCrystal, 'e', Items.emerald);

        // Tier 3
        add(ModItems.pureCrystal, "scs", "cec", "scs", 's', ModItems.stoneCrystal, 'c', ModItems.mysticalCrystal, 'e', Blocks.emerald_block);

        // Tier 4
        add(ModItems.celestialCrystal, "scs", "cec", "scs", 's', ModItems.mysticalCrystal, 'c', ModItems.pureCrystal, 'e', Items.nether_star);

        // Tier 5
        add(ModItems.perfectCrystal, "scs", "cec", "scs", 's', ModItems.pureCrystal, 'c', ModItems.celestialCrystal, 'e', ModItems.netherPearl);

        /* ======================================== CRYSTAL END ===================================== */

        /* ======================================== MATRIX START ===================================== */

        // Tier 1
        add(new ItemStack(ModItems.matrix, 1, 0), "scs", "cec", "scs", 's', ModItems.magicalPebble, 'c', ModItems.stoneCrystal, 'e', Items.ender_pearl);

        // Tier 2
        add(new ItemStack(ModItems.matrix, 1, 1), "scs", "cmc", "scs", 's', ModBlocks.magicalStone, 'c', ModItems.mysticalCrystal, 'm', new ItemStack(ModItems.matrix, 1, 0));

        // Tier 3
        add(new ItemStack(ModItems.matrix, 1, 2), "scs", "cmc", "scs", 's', ModItems.mysticalCrystal, 'c', ModItems.pureCrystal, 'm', new ItemStack(ModItems.matrix, 1, 1));

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
