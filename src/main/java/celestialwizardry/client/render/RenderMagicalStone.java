package celestialwizardry.client.render;


import org.lwjgl.opengl.GL11;

import celestialwizardry.util.ResourceLocationHelper;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class RenderMagicalStone implements ISimpleBlockRenderingHandler {
	
	public static final int ID = RenderingRegistry.getNextAvailableRenderId();
	private static final ResourceLocation innerTex = ResourceLocationHelper.getResourceLocation("textures/blocks/magicalStoneOLD.png");

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
		
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
//		IIcon icon = block.getIcon(world.getBlockMetadata(x, y, z), 0);
		IIcon icon2 = Blocks.lava.getIcon(0, 0);
		Tessellator tes = Tessellator.instance;
		
		renderer.renderStandardBlock(block, x, y, z);
		tes.addVertexWithUV(x+.0001d, y+.9999d, z+.9999d, icon2.getMinU(), icon2.getMinV());
		tes.addVertexWithUV(x+.0001d, y+.0001d, z+.9999d, icon2.getMinU(), icon2.getMaxV());
		tes.addVertexWithUV(x+.9999d, y+.0001d, z+.9999d, icon2.getMaxU(), icon2.getMaxV());
		tes.addVertexWithUV(x+.9999d, y+.9999d, z+.9999d, icon2.getMaxU(), icon2.getMinV());
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return false;
	}

	@Override
	public int getRenderId() {
		return ID;
	}

}
