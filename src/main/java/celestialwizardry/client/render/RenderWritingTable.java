package celestialwizardry.client.render;

import celestialwizardry.client.model.OBJModels;
import cpw.mods.fml.client.FMLClientHandler;

import celestialwizardry.reference.Resources;
import net.minecraft.client.model.ModelBook;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import celestialwizardry.tileentity.TileEntityWritingTable;

import cpw.mods.fml.relauncher.Side;

import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderWritingTable extends TileEntitySpecialRenderer
{
	
	private static ModelBook book = new ModelBook();
	private static final ResourceLocation bookTextureTemp = new ResourceLocation("textures/entity/enchanting_table_book.png");
	
    public void renderTileEntityAt(TileEntityWritingTable var1, double var2, double var4, double var6, float var8)
    {

    	//FDIR 0 - DOWN
		//FDIR 1 - UP
		//FDIR 2 - NORTH
		//FDIR 3 - SOUTH
		//FDIR 4 - WEST
		//FDIR 5 - EAST
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(Resources.Models.TEXTURE_WRITING_TABLE);
		
		boolean renderBook = var1.getMainObjectType().equalsIgnoreCase("book");
		
		GL11.glPushMatrix();
		GL11.glTranslated(var2+.5, var4, var6+.5);
		
		float rot = 0f;
		
		switch (var1.getBlockMetadata())
        {
            case 2: //NORTH
                rot = 180f;
                break;
            case 3: //SOUTH
                break;
            case 4: //WEST
                rot = 270f;
                break;
            case 5: //EAST
                rot = 90f;
                break;
            default:
                break;
        }
		
		GL11.glRotatef(rot, 0f, 1f, 0f);
		OBJModels.modelWritingTable.renderAll();
		GL11.glPopMatrix();
		
		//Render book
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(bookTextureTemp);
		GL11.glPushMatrix();
		GL11.glTranslated(var2+.5d, var4+.88d, var6+.5d);

		GL11.glRotatef(rot-90f, 0f, 1f, 0f);
		GL11.glTranslatef(.14f, 0f, 0f);
		GL11.glRotatef(70f, 0f, 0f, 1f);

		GL11.glScalef(.15f, .15f, .15f);
		book.render((Entity)null, 5f, 1f, 0f, 1.2f, 0f, 6f / 16f);
		GL11.glPopMatrix();
    }
    
    @Override
    public void renderTileEntityAt(TileEntity var1, double var2, double var4, double var6, float var8)
    {
    	this.renderTileEntityAt((TileEntityWritingTable)var1, var2, var4, var6, var8);
    }

}
