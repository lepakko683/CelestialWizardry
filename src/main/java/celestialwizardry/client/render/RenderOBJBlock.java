package celestialwizardry.client.render;

import celestialwizardry.block.BlockBell;
import celestialwizardry.block.BlockWritingTable;
import celestialwizardry.client.model.OBJModels;
import celestialwizardry.reference.Resources;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderOBJBlock implements ISimpleBlockRenderingHandler
{

    public static final int ID = RenderingRegistry.getNextAvailableRenderId();

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
    {
        if (modelID == ID)
        {
            GL11.glPushMatrix();

            if (block instanceof BlockWritingTable)
            {
                FMLClientHandler.instance().getClient().renderEngine
                        .bindTexture(Resources.Models.TEXTURE_WRITING_TABLE);
                GL11.glTranslated(0, -0.5, 0);
                GL11.glScalef(1f, 1f, 1f);
                OBJModels.modelWritingTable.renderAll();
            }
            if (block instanceof BlockBell)
            {
            	FMLClientHandler.instance().getClient().renderEngine.bindTexture(Resources.Models.TEXTURE_BELL);
            	GL11.glTranslatef(0f, -1.3f, 0f);
            	GL11.glScalef(2f, 2f, 2f);
//            	OBJModels.modelBell.renderPart("dinger_Planee");
            	GL11.glDisable(GL11.GL_CULL_FACE);
            	OBJModels.modelBell.renderAll();
            	GL11.glEnable(GL11.GL_CULL_FACE);
            }

            GL11.glPopMatrix();
        }
        else
        {
            //RenderUtility.renderNormalBlockAsItem(block, metadata, renderer);
            System.out.println("renderNormalBlockAsItem");
        }
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess iBlockAccess, int x, int y, int z, Block block, int modelID,
                                    RenderBlocks renderer)
    {
    	return false;
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId)
    {
        return true;
    }

    @Override
    public int getRenderId()
    {
        return ID;
    }

}
