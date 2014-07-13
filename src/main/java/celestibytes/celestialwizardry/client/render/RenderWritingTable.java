package celestibytes.celestialwizardry.client.render;

import celestibytes.celestialwizardry.client.model.OBJModels;
import celestibytes.celestialwizardry.reference.Resources;
import celestibytes.celestialwizardry.tileentity.TileEntityWritingTable;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderWritingTable extends TileEntitySpecialRenderer
{

    public void renderTileEntityAt(TileEntityWritingTable te, double x, double y, double z, float partTick)
    {

        //FDIR 0 - DOWN
        //FDIR 1 - UP
        //FDIR 2 - NORTH
        //FDIR 3 - SOUTH
        //FDIR 4 - WEST
        //FDIR 5 - EAST
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(Resources.Models.TEXTURE_WRITING_TABLE);

        IRenderable mainObject = te.getMainObject();

        boolean renderBook = te.getMainObjectType().equalsIgnoreCase("book");

        GL11.glPushMatrix();
        GL11.glTranslated(x + .5, y, z + .5);

        float rot = 0f;

        switch (te.getBlockMetadata())
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

        if (mainObject != null)
        {
            GL11.glPushMatrix();

            GL11.glTranslated(x + .5d, y + .88d, z + .5d);
            GL11.glRotatef(rot - 90f, 0f, 1f, 0f);
            mainObject.render(x, y, z, new Object[]{true});

            GL11.glPopMatrix();
        }
//		GL11.glPushMatrix();
//		GL11.glTranslated(x+.5d, y+.88d, z+.5d);
//		
//		GL11.glRotatef(rot-90f, 0f, 1f, 0f);
//		GL11.glTranslatef(.08f, 0f, 0f);
//		GL11.glRotatef(340f, 0f, 0f, 1f);
//		
//		((IRenderable)ModItems.page).render(x, y, z);
//		GL11.glPopMatrix();
    }

    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float partTick)
    {
        this.renderTileEntityAt((TileEntityWritingTable) te, x, y, z, partTick);
    }

}
