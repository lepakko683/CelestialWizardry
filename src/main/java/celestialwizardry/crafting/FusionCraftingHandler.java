package celestialwizardry.crafting;

import net.minecraft.item.ItemStack;

import java.util.List;

import celestialwizardry.api.energy.EnergyType;

public class FusionCraftingHandler
{
    private static List<FusionCraftingRecipe> recipes[];

    public static void addRecipe(ItemStack result, Object inputItems[], Object inputEnergy[])
    {

    }
    
    public static boolean enoughEnergy(FusionCraftingRecipe rec, EnergyType eTypes[], int eAmounts[]) {
    	return false;
    }
}
