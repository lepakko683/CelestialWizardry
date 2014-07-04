package celestialwizardry.crystal.client.render;

import celestialwizardry.crystal.block.BlockCrystalConductiveWeak;
import celestialwizardry.crystal.client.model.CrystalModels;
import celestialwizardry.crystal.reference.CrystalResources;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

import org.lwjgl.opengl.GL11;

public class RenderCrystalBlock implements ISimpleBlockRenderingHandler
{
    public static final int ID = RenderingRegistry.getNextAvailableRenderId();

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
    {
        if (modelID == ID)
        {
            GL11.glPushMatrix();

            if (block instanceof BlockCrystalConductiveWeak)
            {
                FMLClientHandler.instance().getClient().renderEngine
                        .bindTexture(CrystalResources.Models.TEXTURE_CRYSTAL_CONDUCTIVE_WEAK);
                crystalSimpleGL11();
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
        if (modelID == ID)
        {
            Tessellator tes = Tessellator.instance;
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

    protected void crystalSimpleGL11()
    {
        GL11.glTranslatef(0f, -1f, 0f); // TODO: tweak to render correctly in inventory & in hand
        GL11.glScalef(.7f, .7f, .7f);
        CrystalModels.modelCrystalSimple.renderAll();
    }

    protected void crystalComplexGL11()
    {

    }
}
