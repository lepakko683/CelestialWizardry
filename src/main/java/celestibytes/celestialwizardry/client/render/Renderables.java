package celestibytes.celestialwizardry.client.render;

import net.minecraft.client.model.ModelBook;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class Renderables
{

    public static void init()
    {
        renderBookOnWritingTable = new RenderBookOnWritingTable();
    }

    public static RenderBookOnWritingTable renderBookOnWritingTable;


    public static class RenderBookOnWritingTable implements IRenderable
    {

        private static final ResourceLocation bookTextureTemp = new ResourceLocation(
                "textures/entity/enchanting_table_book.png");
        private static ModelBook book = new ModelBook();

        @Override
        public void render(double x, double y, double z, Object[] extraData)
        {
            FMLClientHandler.instance().getClient().renderEngine.bindTexture(bookTextureTemp);

            GL11.glTranslatef(.14f, 0f, 0f);
            GL11.glRotatef(70f, 0f, 0f, 1f);

            GL11.glScalef(.15f, .15f, .15f);
            book.render((Entity) null, 5f, 1f, 0f, 1.2f, 0f, 6f / 16f);
        }

    }
}
