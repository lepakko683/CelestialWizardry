package celestibytes.celestialwizardry.client.model;

import celestibytes.core.util.RenderHelper;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelFormatException;
import net.minecraftforge.client.model.obj.Face;
import net.minecraftforge.client.model.obj.GroupObject;
import net.minecraftforge.client.model.obj.WavefrontObject;

import java.io.InputStream;

public class OBJModelBlock extends WavefrontObject
{
    // WAS = With Atlas Sprite

    public OBJModelBlock(ResourceLocation resource) throws ModelFormatException
    {
        super(resource);
    }

    public OBJModelBlock(String filename, InputStream inputStream) throws ModelFormatException
    {
        super(filename, inputStream);
    }

    public void tessellateAllWAS(Tessellator tes, double x1, double y1, double x2, double y2, double x, double y,
                                 double z)
    {
        for (GroupObject go : groupObjects)
        {
            renderGroupObjectWAS(tes, go, x1, y1, x2, y2, x, y, z);
        }
    }

    public void tessellateAllWAS(Tessellator tes, IIcon icon, double x, double y, double z)
    {
        tessellateAllWAS(tes, icon.getMinU(), icon.getMinV(), icon.getMaxU(), icon.getMaxV(), x, y, z);
    }

    private void renderGroupObjectWAS(Tessellator tes, GroupObject go, double x1, double y1, double x2, double y2,
                                      double x, double y, double z)
    {
        if (go.faces.size() > 0)
        {
            for (Face f : go.faces)
            {
                addFaceForRenderWAS(tes, x1, y1, x2, y2, f, 0.0005F, x, y, z);
            }
        }
    }

    private void addFaceForRenderWAS(Tessellator tes, double x1, double y1, double x2, double y2, Face f, float texOffs,
                                     double x, double y, double z)
    {

        // Following code mostly copied from net.minecraftforge.client.model.obj.Face
        if (f.faceNormal == null)
        {
            f.faceNormal = f.calculateFaceNormal();
        }

        tes.setNormal(f.faceNormal.x, f.faceNormal.y, f.faceNormal.z);

        float averageU = 0F;
        float averageV = 0F;

        if ((f.textureCoordinates != null) && (f.textureCoordinates.length > 0))
        {
            for (int i = 0; i < f.textureCoordinates.length; ++i)
            {
                averageU += f.textureCoordinates[i].u;
                averageV += f.textureCoordinates[i].v;
            }

            averageU = averageU / f.textureCoordinates.length;
            averageV = averageV / f.textureCoordinates.length;
        }

        float offsetU, offsetV;

        for (int i = 0; i < f.vertices.length; ++i)
        {

            if ((textureCoordinates != null) && (f.textureCoordinates.length > 0))
            {
                offsetU = texOffs;
                offsetV = texOffs;

                if (f.textureCoordinates[i].u > averageU)
                {
                    offsetU = -offsetU;
                }
                if (f.textureCoordinates[i].v > averageV)
                {
                    offsetV = -offsetV;
                }
//                System.out.println("U: " + RenderHelper.scaleUVCoord(f.textureCoordinates[i].u + offsetU, x1,
// x2) + " V: " + RenderHelper.scaleUVCoord(f.textureCoordinates[i].v + offsetV, y1, y2));
                tes.addVertexWithUV(x + f.vertices[i].x, y + f.vertices[i].y, z + f.vertices[i].z,
                                    RenderHelper.scaleUVCoord(f.textureCoordinates[i].u + offsetU, x1, x2),
                                    RenderHelper.scaleUVCoord(f.textureCoordinates[i].v + offsetV, y1, y2));
            }
            else
            {
                tes.addVertex(x + f.vertices[i].x, y + f.vertices[i].y, z + f.vertices[i].z);
            }
        }
    }

}
