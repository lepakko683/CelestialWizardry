package celestibytes.celestialwizardry.client.gui.spellbookold;

import celestibytes.celestialwizardry.reference.Resources;
import celestibytes.celestialwizardry.util.CWStringHelper;
import celestibytes.core.util.RenderHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

import java.util.Arrays;

@SideOnly(Side.CLIENT)
@Deprecated
public class GuiButtonPage extends GuiButton
{
    boolean right;

    public GuiButtonPage(int par1, int par2, int par3, boolean right)
    {
        super(par1, par2, par3, 18, 10, "");
        this.right = right;
    }

    @Override
    public void drawButton(Minecraft mc, int par2, int par3)
    {
        if (enabled)
        {
            field_146123_n = par2 >= xPosition && par3 >= yPosition && par2 < xPosition + width
                    && par3 < yPosition + height;
            int k = getHoverState(field_146123_n);

            mc.renderEngine.bindTexture(Resources.Textures.GUI_SPELL_BOOK_OLD);
            GL11.glColor4f(1F, 1F, 1F, 1F);
            drawTexturedModalRect(xPosition, yPosition, k == 2 ? 18 : 0, right ? 180 : 190, 18, 10);

            if (k == 2)
            {
                RenderHelper.renderTooltip(par2, par3, Arrays.asList(CWStringHelper.localize(
                        right ? "misc." + Resources.RESOURCE_PREFIX + "nextPage"
                                : "misc." + Resources.RESOURCE_PREFIX + "prevPage"
                                                                                            )));
            }
        }
    }
}
