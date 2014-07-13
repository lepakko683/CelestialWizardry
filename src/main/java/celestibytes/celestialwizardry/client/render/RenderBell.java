package celestibytes.celestialwizardry.client.render;

import celestibytes.celestialwizardry.client.model.OBJModels;
import celestibytes.celestialwizardry.reference.Resources;
import celestibytes.core.util.ActiveNumber;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.client.FMLClientHandler;

import org.lwjgl.opengl.GL11;

public class RenderBell extends TileEntitySpecialRenderer
{

    private ActiveNumber rotation = new ActiveNumber(ActiveNumber.MODE_KEEP_WITHIN_BOUNDS, 0d).setBounds(0d, 359d);
    private ActiveNumber hover = new ActiveNumber(ActiveNumber.MODE_OSCILLATE_SMOOTH, 1d).
            setBounds(0d, 1d).setDirection(ActiveNumber.DIR_DEC);

    @Override
    public void renderTileEntityAt(TileEntity ent, double x, double y, double z, float partick)
    {
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(Resources.Models.TEXTURE_BELL);
        GL11.glPushMatrix();

        GL11.glTranslated(x + .5d, y, z + .5d);
        GL11.glDisable(GL11.GL_CULL_FACE);

        OBJModels.modelBell.renderAll();

        GL11.glEnable(GL11.GL_CULL_FACE);
        // BEGIN
        GL11.glTranslated(0d, 3d + hover.update(0.02d), 0d);
        GL11.glRotated(rotation.update(.5d), 0d, 1d, 0d);

        OBJModels.modelCrystalItemHolder.renderAll();

        // END
        GL11.glPopMatrix();
    }

}
