package celestialwizardry.item;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.Tessellator;
import celestialwizardry.client.render.IRenderable;
import celestialwizardry.reference.Names;
import celestialwizardry.reference.Resources;
import celestialwizardry.spellgrammar.ITextContainer;
import celestialwizardry.util.RenderHelper;

public class ItemPage extends ItemSingle implements IRenderable, ITextContainer
{
    public ItemPage()
    {
        super();
        this.setMaxStackSize(1);
        this.setUnlocalizedName(Names.Items.PAGE);
    }

	@Override
	public void render(double x, double y, double z, Object[] extraData) {
		Tessellator tes = Tessellator.instance;
		RenderHelper.bindTexture(Resources.Textures.PAGE);
		if(extraData != null) {
			if(extraData.length >= 1 && extraData[0] instanceof Boolean) {
				if((Boolean)extraData[0]) {//Render on writingTable?
					GL11.glScalef(.9f, .9f, .9f);
					GL11.glTranslatef(.09f, .010f, 0f);
					GL11.glRotatef(90f, 0f, 1f, 0f);
					GL11.glRotatef(20f, 1f, 0f, 0f);
				}
			}
		}
		
		tes.startDrawingQuads();
		tes.addVertexWithUV(-.4d, 0d, .4d, 0d, 0d);
		tes.addVertexWithUV(.4d, 0d, .4d, 1d, 0d);
		tes.addVertexWithUV(.4d, 0d, -.4d, 1d, 1d);
		tes.addVertexWithUV(-.4d, 0d, -.4d, 0d, 1d);
		tes.draw();
	}

	@Override
	public boolean isRunic() {
		return false;
	}

	@Override
	public int[] getText() {
		return null;
	}
}
