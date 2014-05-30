package celestialwizardry.client.render;

import celestialwizardry.client.model.OBJModels;
import celestialwizardry.reference.Resources;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderWritingTable extends TileEntitySpecialRenderer
{
    @Override
    public void renderTileEntityAt(TileEntity var1, double var2, double var4, double var6, float var8)
    {

        //FDIR 0 - DOWN
        //FDIR 1 - UP
        //FDIR 2 - NORTH
        //FDIR 3 - SOUTH
        //FDIR 4 - WEST
        //FDIR 5 - EAST
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(Resources.Models.TEXTURE_WRITING_TABLE);

        GL11.glPushMatrix();
        GL11.glTranslated(var2 + .5, var4, var6 + .5);
        switch (var1.getBlockMetadata())
        {
            case 2: //NORTH
                GL11.glRotatef(180F, 0.0F, 1.0F, 0.0F);
                break;
            case 3: //SOUTH
                break;
            case 4: //WEST
                GL11.glRotatef(270F, 0.0F, 1.0F, 0.0F);
                break;
            case 5: //EAST
                GL11.glRotatef(90F, 0.0F, 1.0F, 0.0F);
                break;
            default:
                break;
        }
        OBJModels.modelWritingTable.renderAll();

        GL11.glPopMatrix();
    }

}
