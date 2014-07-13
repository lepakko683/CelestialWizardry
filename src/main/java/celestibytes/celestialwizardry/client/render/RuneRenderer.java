package celestibytes.celestialwizardry.client.render;

import celestibytes.core.util.Colour;
import celestibytes.core.util.RenderHelper;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RuneRenderer implements IResourceManagerReloadListener
{

    private final ResourceLocation currentRuneTexture;

    private String[] modidRuneTexLocs = null;

    private Colour currColor;

    public RuneRenderer(ResourceLocation texLoc)
    {
        this.currentRuneTexture = texLoc;
    }

    public int getRuneWidth(int runeId)
    {
        return 5;
    }

    public int getWidthOfRunes(int runes[])
    {
        return -1;
    }

    /**
     * Draw a string of runes at the specified coordinates with scale and color.
     */
    public void renderRunes(int runeids[], int posX, int posY, float scale, Colour color)
    {
        Tessellator tes = Tessellator.instance;
        RenderHelper.bindTexture(currentRuneTexture);
        tes.startDrawingQuads();
        float offset = 0f;
        for (int i = 0; i </*runeids.length*/6; i++)
        {
            offset += 20f;
            renderSingleRune(/*runeids[i]*/0, (double) posX, (double) posY, 0d, offset, tes);
        }

        tes.draw();
    }

    /**
     * Draw a string of runes at the specified coordinates with scale(relative to 1f) using black color.
     */
    public void renderRunes(int runeids[], int posX, int posY, float scale)
    {
        renderRunes(runeids, posX, posY, scale, Colour.WHITE);
    }

    private void renderSingleRune(int runeId, double posX, double posY, double posZ, float offset, Tessellator tes)
    {
        double lll = 1f / 16f;
        double xMinTex = lll * 2; // TODO: get offset for rune
        double yMinTex = 0d; // TODO: get offset for rune
        float size = 20f;

        if (runeId == -1)
        { // Set the unknown-rune - texture
            xMinTex = 0d;
            yMinTex = 0d;
        }

        tes.addVertexWithUV(offset + posX, posY + size, posZ, xMinTex, yMinTex + lll);
        tes.addVertexWithUV(offset + posX + size, posY + size, posZ, xMinTex + lll, yMinTex + lll);
        tes.addVertexWithUV(offset + posX + size, posY, posZ, xMinTex + lll, yMinTex);
        tes.addVertexWithUV(offset + posX, posY, posZ, xMinTex, yMinTex);
    }

    @Override //TODO: reload rune texture(s).
    public void onResourceManagerReload(IResourceManager var1)
    {

    }

}
