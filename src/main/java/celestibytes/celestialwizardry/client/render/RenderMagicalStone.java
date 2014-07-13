package celestibytes.celestialwizardry.client.render;


import celestibytes.celestialwizardry.block.BlockMagicalStone;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

import org.lwjgl.opengl.GL11;

public class RenderMagicalStone implements ISimpleBlockRenderingHandler
{
	// Note: No mods were decompiled in the creation of this rendering code -OkkapeL

    public static final int ID = RenderingRegistry.getNextAvailableRenderId();

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer)
    {
        IIcon inner = null;

        if (block instanceof BlockMagicalStone)
        {
            inner = ((BlockMagicalStone) block).getInnerIcon();
        }
        else
        {
            inner = Blocks.lava.getIcon(0, 0);
        }
        double minv = 0.0d;
        double maxv = 1.0d;
        double x = 0d;
        double y = 0d;
        double z = 0d;
        Tessellator tes = Tessellator.instance;
        GL11.glPushMatrix();
        GL11.glTranslatef(-.5f, -.5f, -.5f);
        tes.startDrawingQuads();
//		tes.setBrightness(200);
        GL11.glDisable(GL11.GL_LIGHTING);
//		Colour.resetGLColor();
        GL11.glColor3f(.8f, .8f, .8f);
//		tes.setColorOpaque_F(.89f, .89f, .89f);
        tes.addVertexWithUV(x + minv, y + minv, z + maxv, inner.getMinU(), inner.getMaxV());
        tes.addVertexWithUV(x + minv, y + minv, z + minv, inner.getMinU(), inner.getMinV());
        tes.addVertexWithUV(x + maxv, y + minv, z + minv, inner.getMaxU(), inner.getMinV());
        tes.addVertexWithUV(x + maxv, y + minv, z + maxv, inner.getMaxU(), inner.getMaxV());

        tes.addVertexWithUV(x + maxv, y + maxv, z + maxv, inner.getMaxU(), inner.getMaxV());
        tes.addVertexWithUV(x + maxv, y + maxv, z + minv, inner.getMaxU(), inner.getMinV());
        tes.addVertexWithUV(x + minv, y + maxv, z + minv, inner.getMinU(), inner.getMinV());
        tes.addVertexWithUV(x + minv, y + maxv, z + maxv, inner.getMinU(), inner.getMaxV());

        tes.addVertexWithUV(x + minv, y + maxv, z + minv, inner.getMinU(), inner.getMinV());
        tes.addVertexWithUV(x + maxv, y + maxv, z + minv, inner.getMaxU(), inner.getMinV());
        tes.addVertexWithUV(x + maxv, y + minv, z + minv, inner.getMaxU(), inner.getMaxV());
        tes.addVertexWithUV(x + minv, y + minv, z + minv, inner.getMinU(), inner.getMaxV());

        tes.addVertexWithUV(x + minv, y + maxv, z + maxv, inner.getMinU(), inner.getMinV());
        tes.addVertexWithUV(x + minv, y + minv, z + maxv, inner.getMinU(), inner.getMaxV());
        tes.addVertexWithUV(x + maxv, y + minv, z + maxv, inner.getMaxU(), inner.getMaxV());
        tes.addVertexWithUV(x + maxv, y + maxv, z + maxv, inner.getMaxU(), inner.getMinV());

        tes.addVertexWithUV(x + minv, y + maxv, z + maxv, inner.getMaxU(), inner.getMinV());
        tes.addVertexWithUV(x + minv, y + maxv, z + minv, inner.getMinU(), inner.getMinV());
        tes.addVertexWithUV(x + minv, y + minv, z + minv, inner.getMinU(), inner.getMaxV());
        tes.addVertexWithUV(x + minv, y + minv, z + maxv, inner.getMaxU(), inner.getMaxV());

        tes.addVertexWithUV(x + maxv, y + maxv, z + maxv, inner.getMaxU(), inner.getMinV());
        tes.addVertexWithUV(x + maxv, y + minv, z + maxv, inner.getMaxU(), inner.getMaxV());
        tes.addVertexWithUV(x + maxv, y + minv, z + minv, inner.getMinU(), inner.getMaxV());
        tes.addVertexWithUV(x + maxv, y + maxv, z + minv, inner.getMinU(), inner.getMinV());

        // OUTER

        IIcon icon = block.getIcon(0, metadata);
        tes.addVertexWithUV(x + minv, y + minv, z + maxv, icon.getMinU(), icon.getMaxV());
        tes.addVertexWithUV(x + minv, y + minv, z + minv, icon.getMinU(), icon.getMinV());
        tes.addVertexWithUV(x + maxv, y + minv, z + minv, icon.getMaxU(), icon.getMinV());
        tes.addVertexWithUV(x + maxv, y + minv, z + maxv, icon.getMaxU(), icon.getMaxV());

        icon = block.getIcon(1, metadata);
        tes.addVertexWithUV(x + maxv, y + maxv, z + maxv, icon.getMaxU(), icon.getMaxV());
        tes.addVertexWithUV(x + maxv, y + maxv, z + minv, icon.getMaxU(), icon.getMinV());
        tes.addVertexWithUV(x + minv, y + maxv, z + minv, icon.getMinU(), icon.getMinV());
        tes.addVertexWithUV(x + minv, y + maxv, z + maxv, icon.getMinU(), icon.getMaxV());

        icon = block.getIcon(2, metadata);
        tes.addVertexWithUV(x + minv, y + maxv, z + minv, icon.getMinU(), icon.getMinV());
        tes.addVertexWithUV(x + maxv, y + maxv, z + minv, icon.getMaxU(), icon.getMinV());
        tes.addVertexWithUV(x + maxv, y + minv, z + minv, icon.getMaxU(), icon.getMaxV());
        tes.addVertexWithUV(x + minv, y + minv, z + minv, icon.getMinU(), icon.getMaxV());

        icon = block.getIcon(3, metadata);
        tes.addVertexWithUV(x + minv, y + maxv, z + maxv, icon.getMinU(), icon.getMinV());
        tes.addVertexWithUV(x + minv, y + minv, z + maxv, icon.getMinU(), icon.getMaxV());
        tes.addVertexWithUV(x + maxv, y + minv, z + maxv, icon.getMaxU(), icon.getMaxV());
        tes.addVertexWithUV(x + maxv, y + maxv, z + maxv, icon.getMaxU(), icon.getMinV());

        icon = block.getIcon(4, metadata);
        tes.addVertexWithUV(x + minv, y + maxv, z + maxv, icon.getMaxU(), icon.getMinV());
        tes.addVertexWithUV(x + minv, y + maxv, z + minv, icon.getMinU(), icon.getMinV());
        tes.addVertexWithUV(x + minv, y + minv, z + minv, icon.getMinU(), icon.getMaxV());
        tes.addVertexWithUV(x + minv, y + minv, z + maxv, icon.getMaxU(), icon.getMaxV());

        icon = block.getIcon(5, metadata);
        tes.addVertexWithUV(x + maxv, y + maxv, z + maxv, icon.getMaxU(), icon.getMinV());
        tes.addVertexWithUV(x + maxv, y + minv, z + maxv, icon.getMaxU(), icon.getMaxV());
        tes.addVertexWithUV(x + maxv, y + minv, z + minv, icon.getMinU(), icon.getMaxV());
        tes.addVertexWithUV(x + maxv, y + maxv, z + minv, icon.getMinU(), icon.getMinV());
        tes.draw();
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
                                    RenderBlocks renderer)
    {
//		IIcon icon = block.getIcon(world.getBlockMetadata(x, y, z), 0);
        IIcon inner = null;
        if (block instanceof BlockMagicalStone)
        {
            inner = ((BlockMagicalStone) block).getInnerIcon();
        }
        else
        {
            inner = Blocks.lava.getIcon(0, 0);
        }
        double minv = 0.0d;
        double maxv = 1.0d;
        Tessellator tes = Tessellator.instance;

        tes.setBrightness(200);
        tes.setColorOpaque_F(.89f, .89f, .89f);
        if (renderer.renderAllFaces || block.shouldSideBeRendered(world, x, y - 1, z, 0))
        {
            tes.addVertexWithUV(x + minv, y + minv, z + maxv, inner.getMinU(), inner.getMaxV());
            tes.addVertexWithUV(x + minv, y + minv, z + minv, inner.getMinU(), inner.getMinV());
            tes.addVertexWithUV(x + maxv, y + minv, z + minv, inner.getMaxU(), inner.getMinV());
            tes.addVertexWithUV(x + maxv, y + minv, z + maxv, inner.getMaxU(), inner.getMaxV());
        }
        if (renderer.renderAllFaces || block.shouldSideBeRendered(world, x, y + 1, z, 1))
        {
            tes.addVertexWithUV(x + maxv, y + maxv, z + maxv, inner.getMaxU(), inner.getMaxV());
            tes.addVertexWithUV(x + maxv, y + maxv, z + minv, inner.getMaxU(), inner.getMinV());
            tes.addVertexWithUV(x + minv, y + maxv, z + minv, inner.getMinU(), inner.getMinV());
            tes.addVertexWithUV(x + minv, y + maxv, z + maxv, inner.getMinU(), inner.getMaxV());
        }
        if (renderer.renderAllFaces || block.shouldSideBeRendered(world, x, y, z - 1, 2))
        {
            tes.addVertexWithUV(x + minv, y + maxv, z + minv, inner.getMinU(), inner.getMinV());
            tes.addVertexWithUV(x + maxv, y + maxv, z + minv, inner.getMaxU(), inner.getMinV());
            tes.addVertexWithUV(x + maxv, y + minv, z + minv, inner.getMaxU(), inner.getMaxV());
            tes.addVertexWithUV(x + minv, y + minv, z + minv, inner.getMinU(), inner.getMaxV());
        }
        if (renderer.renderAllFaces || block.shouldSideBeRendered(world, x, y, z + 1, 3))
        {
            tes.addVertexWithUV(x + minv, y + maxv, z + maxv, inner.getMinU(), inner.getMinV());
            tes.addVertexWithUV(x + minv, y + minv, z + maxv, inner.getMinU(), inner.getMaxV());
            tes.addVertexWithUV(x + maxv, y + minv, z + maxv, inner.getMaxU(), inner.getMaxV());
            tes.addVertexWithUV(x + maxv, y + maxv, z + maxv, inner.getMaxU(), inner.getMinV());
        }
        if (renderer.renderAllFaces || block.shouldSideBeRendered(world, x - 1, y, z, 4))
        {//
            tes.addVertexWithUV(x + minv, y + maxv, z + maxv, inner.getMaxU(), inner.getMinV());
            tes.addVertexWithUV(x + minv, y + maxv, z + minv, inner.getMinU(), inner.getMinV());
            tes.addVertexWithUV(x + minv, y + minv, z + minv, inner.getMinU(), inner.getMaxV());
            tes.addVertexWithUV(x + minv, y + minv, z + maxv, inner.getMaxU(), inner.getMaxV());//
        }
        if (renderer.renderAllFaces || block.shouldSideBeRendered(world, x + 1, y, z, 5))
        {
            tes.addVertexWithUV(x + maxv, y + maxv, z + maxv, inner.getMaxU(), inner.getMinV());
            tes.addVertexWithUV(x + maxv, y + minv, z + maxv, inner.getMaxU(), inner.getMaxV());
            tes.addVertexWithUV(x + maxv, y + minv, z + minv, inner.getMinU(), inner.getMaxV());
            tes.addVertexWithUV(x + maxv, y + maxv, z + minv, inner.getMinU(), inner.getMinV());
        }
        renderer.renderStandardBlock(block, x, y, z);
        return true;
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
