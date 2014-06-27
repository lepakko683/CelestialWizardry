package celestialwizardry.item;

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
		System.out.println("rgfgfg");
		Tessellator tes = Tessellator.instance;
		RenderHelper.bindTexture(Resources.Textures.PAGE);
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
