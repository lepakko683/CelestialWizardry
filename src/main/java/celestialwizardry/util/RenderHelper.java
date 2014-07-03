package celestialwizardry.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

import java.util.List;

@SideOnly(Side.CLIENT)
public final class RenderHelper
{
    public static void renderTooltip(int x, int y, List<String> tooltipData)
    {
        int color = 0x505000ff;
        int color2 = 0xf0100010;

        renderTooltip(x, y, tooltipData, color, color2);
    }

    public static void renderTooltip(int x, int y, List<String> tooltipData, int color, int color2)
    {
        boolean lighting = GL11.glGetBoolean(GL11.GL_LIGHTING);

        if (lighting)
        {
            net.minecraft.client.renderer.RenderHelper.disableStandardItemLighting();
        }

        if (!tooltipData.isEmpty())
        {
            int var5 = 0;
            int var6;
            int var7;
            FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;

            for (var6 = 0; var6 < tooltipData.size(); ++var6)
            {
                var7 = fontRenderer.getStringWidth(tooltipData.get(var6));

                if (var7 > var5)
                {
                    var5 = var7;
                }
            }

            var6 = x + 12;
            var7 = y - 12;
            int var9 = 8;

            if (tooltipData.size() > 1)
            {
                var9 += 2 + (tooltipData.size() - 1) * 10;
            }

            float z = 300F;

            drawGradientRect(var6 - 3, var7 - 4, z, var6 + var5 + 3, var7 - 3, color2, color2);
            drawGradientRect(var6 - 3, var7 + var9 + 3, z, var6 + var5 + 3, var7 + var9 + 4, color2, color2);
            drawGradientRect(var6 - 3, var7 - 3, z, var6 + var5 + 3, var7 + var9 + 3, color2, color2);
            drawGradientRect(var6 - 4, var7 - 3, z, var6 - 3, var7 + var9 + 3, color2, color2);
            drawGradientRect(var6 + var5 + 3, var7 - 3, z, var6 + var5 + 4, var7 + var9 + 3, color2, color2);

            int var12 = (color & 0xFFFFFF) >> 1 | color & -16777216;

            drawGradientRect(var6 - 3, var7 - 3 + 1, z, var6 - 3 + 1, var7 + var9 + 3 - 1, color, var12);
            drawGradientRect(var6 + var5 + 2, var7 - 3 + 1, z, var6 + var5 + 3, var7 + var9 + 3 - 1, color, var12);
            drawGradientRect(var6 - 3, var7 - 3, z, var6 + var5 + 3, var7 - 3 + 1, color, color);
            drawGradientRect(var6 - 3, var7 + var9 + 2, z, var6 + var5 + 3, var7 + var9 + 3, var12, var12);

            GL11.glDisable(GL11.GL_DEPTH_TEST);

            for (int var13 = 0; var13 < tooltipData.size(); ++var13)
            {
                String var14 = tooltipData.get(var13);
                fontRenderer.drawStringWithShadow(var14, var6, var7, -1);

                if (var13 == 0)
                {
                    var7 += 2;
                }

                var7 += 10;
            }

            GL11.glEnable(GL11.GL_DEPTH_TEST);
        }

        if (!lighting)
        {
            net.minecraft.client.renderer.RenderHelper.disableStandardItemLighting();
        }

        GL11.glColor4f(1F, 1F, 1F, 1F);
    }

    public static void drawGradientRect(int x1, int y1, float z, int x2, int y2, int colorA, int colorB)
    {
        float alphaA = (colorA >> 24 & 255) / 255F;
        float redA = (colorA >> 16 & 255) / 255F;
        float greenA = (colorA >> 8 & 255) / 255F;
        float blueA = (colorA & 255) / 255F;

        float alphaB = (colorB >> 24 & 255) / 255F;
        float redB = (colorB >> 16 & 255) / 255F;
        float greenB = (colorB >> 8 & 255) / 255F;
        float blueB = (colorB & 255) / 255F;

        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glShadeModel(GL11.GL_SMOOTH);

        Tessellator tes = Tessellator.instance;

        tes.startDrawingQuads();
        tes.setColorRGBA_F(redA, greenA, blueA, alphaA);
        tes.addVertex(x2, y1, z);
        tes.addVertex(x1, y1, z);
        tes.setColorRGBA_F(redB, greenB, blueB, alphaB);
        tes.addVertex(x1, y2, z);
        tes.addVertex(x2, y2, z);
        tes.draw();

        GL11.glShadeModel(GL11.GL_FLAT);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
    }

    public static double scaleUVCoord(double coord, double c1, double c2)
    {
        return Math.min(c1, c2) + (Math.abs(c2 - c1) * coord);
    }

    public static void bindTexture(ResourceLocation rl)
    {
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(rl);
    }

    public static void setupRenderingForBillboard(double yaw, double pitch)
    {
        System.out.println("Yaw: " + yaw + " pitch: " + pitch);
    }
}
