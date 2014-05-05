package celestialwizardry.item.crafting;

import celestialwizardry.init.ModBlocks;
import celestialwizardry.init.ModItems;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

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
        addShapeless(new ItemStack(ModItems.magicalInk), new ItemStack(Items.dye, 1, 4), "dyeBlack", "dyeBlack", new ItemStack(Items.potionitem, 1, 0));
    }

    private static void add(ItemStack output, Object... input)
    {
        GameRegistry.addRecipe(new ShapedOreRecipe(output, input));
    }

    private static void addShapeless(ItemStack output, Object... input)
    {
        GameRegistry.addRecipe(new ShapelessOreRecipe(output, input));
    }
}
