package celestialwizardry.client.render;

import org.lwjgl.opengl.GL11;

import celestialwizardry.block.BlockWritingTable;
import celestialwizardry.client.model.OBJmodels;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.SideOnly;
import cpw.mods.fml.relauncher.Side;

@SideOnly(Side.CLIENT)
public class RenderOBJblock implements ISimpleBlockRenderingHandler {
	
	public static final int ID = RenderingRegistry.getNextAvailableRenderId();

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
	{
		if (modelID == ID) {
			GL11.glPushMatrix();
		//	FMLClientHandler.instance().getClient().renderEngine.bindTexture(RenderCoercionDeriver.TEXTURE_ON);
			
			if(block instanceof BlockWritingTable) {
				FMLClientHandler.instance().getClient().renderEngine.bindTexture(RenderWritingTable.texture);
				GL11.glTranslated(0, -0.5, 0);
//				GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
				GL11.glScalef(1f, 1f, 1f);
				OBJmodels.modelWritingTable.renderAll();
			}
		
			GL11.glPopMatrix();
			} else {
		//		RenderUtility.renderNormalBlockAsItem(block, metadata, renderer);
				System.out.println("renderNormalBlockAsItem");
			}
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess iBlockAccess, int x, int y, int z, Block block, int modelID, RenderBlocks renderer)
	{
		return false;
	}
	
	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}

	@Override
	public int getRenderId()
	{
		return ID;
	}

}
