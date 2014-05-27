package celestialwizardry.crafting;

import celestialwizardry.api.energy.IEnergy;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class FusionCraftingRecipe {
	private ItemStack items[];
	private IEnergy energy[];
	
	public FusionCraftingRecipe() {
		
	}

	public IEnergy[] getRequiredEnergyTypes() {
		return null;
	}
	
	public float[] getRequiredEnergyAmounts() {
		return null;
	}
	
	public Object[] getRequiredItems() {
		return null;
	}
}
