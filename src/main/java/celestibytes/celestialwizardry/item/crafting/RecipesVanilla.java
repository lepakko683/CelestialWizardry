package celestibytes.celestialwizardry.item.crafting;

import celestibytes.celestialwizardry.init.ModBlocks;
import celestibytes.celestialwizardry.init.ModItems;
import celestibytes.celestialwizardry.reference.Settings;

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
        add(ModItems.netherPearl, " s ", "sts", " s ", 's', hc ? Items.nether_star : Items.ghast_tear, 't',
            hc ? Items.ghast_tear : Items.nether_star);
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
