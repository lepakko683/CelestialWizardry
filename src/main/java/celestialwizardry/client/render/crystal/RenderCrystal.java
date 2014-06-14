package celestialwizardry.client.render.crystal;

import celestialwizardry.block.BlockCrystal;
import celestialwizardry.util.ActiveNumber;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelCustom;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public abstract class RenderCrystal extends TileEntitySpecialRenderer
{
    protected final IModelCustom model;
    protected final BlockCrystal crystal;
    protected ActiveNumber rotation = new ActiveNumber(ActiveNumber.MODE_KEEP_WITHIN_BOUNDS).setBounds(0D, 359D);
    protected ActiveNumber hover = new ActiveNumber(ActiveNumber.MODE_OSCILLATE).setBounds(0D, 2D)
            .setDirection(ActiveNumber.DIR_DEC);

    public RenderCrystal(IModelCustom model, BlockCrystal crystal)
    {
        this.model = model;
        this.crystal = crystal;
    }

    @Override
    public void renderTileEntityAt(TileEntity var1, double var2, double var4, double var6, float var8)
    {
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(crystal.getTexture());

        GL11.glPushMatrix();
        GL11.glTranslated(0D, 3D + hover.update(0.01D), 0D);
        GL11.glRotated(rotation.update(.5D), 0D, 1D, 0D);
        model.renderAll();
        GL11.glPopMatrix();
    }

    public ResourceLocation getTexture()
    {
        return crystal.getTexture();
    }

    public IModelCustom getModel()
    {
        return model;
    }

    public BlockCrystal getCrystal()
    {
        return crystal;
    }
}
