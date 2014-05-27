package celestialwizardry.client.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import celestialwizardry.client.model.ModelOreGolem;
import celestialwizardry.entity.EntityLivingOre;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderOreGolem extends RenderLiving {
	//TODO: make it possible to add custom oregolems via api 
	private static final ResourceLocation oregolemCoal = new ResourceLocation("celestialwizardry","textures/entity/oregolem/oregolem_coal.png");
	private static final ResourceLocation oregolemLapis = new ResourceLocation("celestialwizardry","textures/entity/oregolem/oregolem_lapis.png");
	private static final ResourceLocation oregolemIron = new ResourceLocation("celestialwizardry","textures/entity/oregolem/oregolem_iron.png");
	private static final ResourceLocation oregolemGold = new ResourceLocation("celestialwizardry","textures/entity/oregolem/oregolem_gold.png");
	private static final ResourceLocation oregolemRedstone = new ResourceLocation("celestialwizardry","textures/entity/oregolem/oregolem_redstone.png");
	private static final ResourceLocation oregolemDiamond = new ResourceLocation("celestialwizardry","textures/entity/oregolem/oregolem_diamond.png");

	public RenderOreGolem() {
		super(new ModelOreGolem(), 0.5f);
	}

	
	protected ResourceLocation getEntityTexture(EntityLivingOre ent) {
		switch(ent.getOreType()) {
		case EntityLivingOre.OreType.ORE_COAL:
			return oregolemCoal;
		case EntityLivingOre.OreType.ORE_LAPIS:
			return oregolemLapis;
		case EntityLivingOre.OreType.ORE_IRON:
			return oregolemIron;
		case EntityLivingOre.OreType.ORE_GOLD:
			return oregolemGold;
		case EntityLivingOre.OreType.ORE_REDSTONE:
			return oregolemRedstone;
		case EntityLivingOre.OreType.ORE_DIAMOND:
			return oregolemDiamond;
		}
		return oregolemCoal;
		
	}
	
	@Override
	protected ResourceLocation getEntityTexture(Entity var1) {
		return this.getEntityTexture((EntityLivingOre)var1);
	}
	
}
