package celestialwizardry.client.render;


import org.lwjgl.opengl.GL11;

import celestialwizardry.block.BlockMagicalStone;
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

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
		
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
//		IIcon icon = block.getIcon(world.getBlockMetadata(x, y, z), 0);
		IIcon inner = null;
		if(block instanceof BlockMagicalStone) {
			inner = ((BlockMagicalStone)block).getInnerIcon();
		} else {
			inner = Blocks.lava.getIcon(0, 0);
		}
		Tessellator tes = Tessellator.instance;
		renderer.renderStandardBlock(block, x, y, z);
		tes.setBrightness(200);
		tes.setColorOpaque_F(.89f, .89f, .89f);
		
		tes.addVertexWithUV(x+.0001d, y+.9999d, z+.9999d, inner.getMinU(), inner.getMinV());
		tes.addVertexWithUV(x+.0001d, y+.0001d, z+.9999d, inner.getMinU(), inner.getMaxV());
		tes.addVertexWithUV(x+.9999d, y+.0001d, z+.9999d, inner.getMaxU(), inner.getMaxV());
		tes.addVertexWithUV(x+.9999d, y+.9999d, z+.9999d, inner.getMaxU(), inner.getMinV());
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
