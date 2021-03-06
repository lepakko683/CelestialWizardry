package celestibytes.celestialwizardry.client.render;

import celestibytes.celestialwizardry.client.model.OBJModels;
import celestibytes.celestialwizardry.entity.EntityBell;
import celestibytes.celestialwizardry.reference.Resources;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Tuple;
import cpw.mods.fml.client.FMLClientHandler;

import org.lwjgl.opengl.GL11;

public class RenderEntityBell extends Render
{

    public void doRender(EntityBell ent, double x, double y, double z, float var8, float var9)
    {
//		System.out.println("var8: " + var8 + " var9: " + var9);
        GL11.glPushMatrix();
        GL11.glTranslated(x, y, z);
//		Tuple rots = getXZrotsFromVelocity((float) ent.motionX, (float) ent.motionY, (float)ent.motionZ);
//		
//		if(rots != null) {
//			System.out.println();
//		}

        FMLClientHandler.instance().getClient().renderEngine.bindTexture(Resources.Models.TEXTURE_BELL);
        GL11.glDisable(GL11.GL_CULL_FACE);
        OBJModels.modelBell.renderAll();
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glPopMatrix();
    }

    private Tuple getXZrotsFromVelocity(float dx, float dy, float dz)
    {

        return null;
    }


    @Override
    protected ResourceLocation getEntityTexture(Entity ent)
    {
        return Resources.Models.TEXTURE_BELL;
    }


    @Override
    public void doRender(Entity ent, double x, double y, double z, float var8, float var9)
    {
        doRender((EntityBell) ent, x, y, z, var8, var9);
    }

}
