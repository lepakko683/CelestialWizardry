package celestialwizardry.client.render;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import celestialwizardry.client.model.OBJModels;
import celestialwizardry.reference.Resources;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class RenderBell extends TileEntitySpecialRenderer {

	@Override
	public void renderTileEntityAt(TileEntity ent, double x, double y, double z, float partick)
	{
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(Resources.Models.TEXTURE_BELL);
		GL11.glPushMatrix();
		GL11.glTranslated(x+.5d, y, z+.5d);
//		System.out.println("Culling enabled: " + GL11.glIsEnabled(GL11.GL_CULL_FACE));
		GL11.glDisable(GL11.GL_CULL_FACE);
		
		OBJModels.modelBell.renderAll();
		
		GL11.glEnable(GL11.GL_CULL_FACE);
		
		GL11.glPopMatrix();
	}

}
