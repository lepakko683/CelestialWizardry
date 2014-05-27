package celestialwizardry.init;

import net.minecraft.entity.EntityList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.obj.WavefrontObject;
import celestialwizardry.CelestialWizardry;
import celestialwizardry.entity.EntityLivingOre;
import cpw.mods.fml.common.registry.EntityRegistry;

public class ModEntities {
	
	public static void init() {
		EntityRegistry.registerModEntity(EntityLivingOre.class, "Living Ore", 0, CelestialWizardry.instance, 32, 5, true);
//		EntityList.addMapping(par0Class, par1Str, par2, par3, par4);
	}
	
	//I'll leave the creation of convenience methods to PizzAna
}
