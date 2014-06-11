package celestialwizardry.item.crafting;

import celestialwizardry.init.ModBlocks;
import celestialwizardry.init.ModItems;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public class RecipesVanilla
{
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

        // Crystals
        add(ModItems.stoneCrystal, "scs", "cec", "scs", 's', ModBlocks.magicalStone, 'c', Items.diamond, 'e', Items.emerald);
        add(ModItems.mysticalCrystal, "scs", "cec", "scs", 's', ModBlocks.magicalStone, 'c', ModItems.stoneCrystal, 'e', Items.emerald);
        add(ModItems.pureCrystal, "scs", "cec", "scs", 's', ModItems.stoneCrystal, 'c', ModItems.mysticalCrystal, 'e', Items.emerald);
        add(ModItems.celestialCrystal, "scs", "cec", "scs", 's', ModItems.mysticalCrystal, 'c', ModItems.pureCrystal, 'e', Items.emerald);
        add(ModItems.perfectCrystal, "scs", "cec", "scs", 's', ModItems.pureCrystal, 'c', ModItems.celestialCrystal, 'e', Items.emerald);

        // Matrix Tier 1
        add(new ItemStack(ModItems.matrix, 1, 0), "sps", "pdp", "sps", 's', ModBlocks.magicalStone, 'd', Items.diamond,
            'p', ModItems.magicalPebble);
        add(new ItemStack(ModItems.matrix, 1, 0), "sps", "pep", "sps", 's', ModBlocks.magicalStone, 'e', Items.emerald,
            'p', ModItems.magicalPebble);
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
