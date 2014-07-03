package celestialwizardry.client.render;

import celestialwizardry.block.BlockBell;
import celestialwizardry.block.BlockContainedCrystal;
import celestialwizardry.crystal.block.BlockCrystalConductive;
import celestialwizardry.block.BlockWritingTable;
import celestialwizardry.client.model.OBJModels;
import celestialwizardry.reference.Resources;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
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
    private static IIcon ic = Blocks.stone.getIcon(0, 0);
    private static IIcon ic2 = Blocks.diamond_block.getIcon(0, 0);

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
            else if (block instanceof BlockBell)
            {
                FMLClientHandler.instance().getClient().renderEngine.bindTexture(Resources.Models.TEXTURE_BELL);
                GL11.glTranslatef(0f, -1.3f, 0f);
                GL11.glScalef(2f, 2f, 2f);
//            	OBJModels.modelBell.renderPart("dinger_Planee");
                GL11.glDisable(GL11.GL_CULL_FACE);
                OBJModels.modelBell.renderAll();
                GL11.glEnable(GL11.GL_CULL_FACE);
            }
            if (block instanceof BlockContainedCrystal)
            {
                FMLClientHandler.instance().getClient().renderEngine
                        .bindTexture(Resources.Models.TEXTURE_CONTAINED_CRYSTAL);
                GL11.glTranslatef(0f, -.5f, 0f);
//            	GL11.glScalef(2f, 2f, 2f);
                OBJModels.modelCrystalContained.renderAll();
                FMLClientHandler.instance().getClient().renderEngine.bindTexture(Resources.Models.TEXTURE_BELL);
                OBJModels.modelCrystalSimpleQ.renderAll();
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
    public boolean renderWorldBlock(IBlockAccess worlda, int x, int y, int z, Block block, int modelID,
                                    RenderBlocks renderer)
    {
    	if(modelID == ID) {
    		Tessellator tes = Tessellator.instance;
    		if(block instanceof BlockContainedCrystal) {
    			tes.setBrightness(block.getMixedBrightnessForBlock(worlda, x, y, z));
    			int l = block.colorMultiplier(worlda, x, y, z);
    			float red = (l >> 16) & 0xFF;
    			float green = (l >> 8) & 0xFF;
    			float blue = l & 0xFF;
    			tes.setColorOpaque_F(red, green, blue);
    			
    			OBJModels.modelCrystalContained.tessellateAllWAS(tes, ic, x+.5d, y, z+.5d);
    			
//    			tes.setColorOpaque_F(1f, 0f, 1f);
    			
    			OBJModels.modelCrystalSimpleQ.tessellateAllWAS(tes, Blocks.lava.getIcon(0, 0), x+.5d, y, z+.5d);
    			
    			return true;
    		}
    	}
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
