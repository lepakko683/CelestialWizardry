package celestialwizardry.client.render;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import celestialwizardry.client.model.OBJmodels;
import celestialwizardry.client.model.WritingTableB;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;

public class RenderWritingTable extends TileEntitySpecialRenderer {

	public static final ResourceLocation texture = new ResourceLocation("celestialwizardry", "models/writTableTest.png");
//	public static final ResourceLocation texture2 = new ResourceLocation("celestialwizardry", "textures/blocks/writingTable_side.png");
//	private static final WritingTableB model = new WritingTableB();
	
	@Override
	public void renderTileEntityAt(TileEntity var1, double var2, double var4, double var6, float var8) {

		//FDIR 0 - DOWN
		//FDIR 1 - UP
		//FDIR 2 - NORTH
		//FDIR 3 - SOUTH
		//FDIR 4 - WEST
		//FDIR 5 - EAST
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(texture);
		
		GL11.glPushMatrix();
		GL11.glTranslated(var2+.5, var4, var6+.5);
//		GL11.glRotatef(45F, 0.0F, 1.0F, 0.0F);
//		GL11.glScalef(3f, 3f, 3f);
//		model.render2(var8);
//		GL11.glColor3f(1f, 1f, 1f);
		switch(var1.getBlockMetadata()) {
		case 2:
//			System.out.println("north");
			GL11.glRotatef(180F, 0.0F, 1.0F, 0.0F);
			break;
		case 3:
//			System.out.println("south");
			break;
		case 4:
//			System.out.println("west");
			GL11.glRotatef(270F, 0.0F, 1.0F, 0.0F);
			break;
		case 5:
//			System.out.println("east");
			GL11.glRotatef(90F, 0.0F, 1.0F, 0.0F);
			break;
		default:
//			System.out.println("Something unexpected happened while rendering WritingTable. Please report to Okkapel(@Le683)!");
			break;
		}
		OBJmodels.modelWritingTable.renderAll();
//		System.out.println(var8);
		
		GL11.glPopMatrix();
	}

}
