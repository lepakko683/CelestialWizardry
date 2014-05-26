package celestialwizardry.client;

import celestialwizardry.client.handler.ClientTickEventHandler;
import celestialwizardry.reference.Resources;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBook;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ItemRendererSpellBook implements IItemRenderer
{
    ModelBook modelBook = new ModelBook();

    /**
     * Checks if this renderer should handle a specific item's render type
     *
     * @param item The item we are trying to render
     * @param type A render type to check if this renderer handles
     *
     * @return true if this renderer should handle the given render type, otherwise false
     */
    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type)
    {
        return type == ItemRenderType.EQUIPPED_FIRST_PERSON;
    }

    /**
     * Checks if certain helper functionality should be executed for this renderer. See ItemRendererHelper for more
     * info
     *
     * @param type   The render type
     * @param item   The ItemStack being rendered
     * @param helper The type of helper functionality to be ran
     *
     * @return True to run the helper functionality, false to not.
     */
    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
                                         ItemRendererHelper helper)
    {
        return false;
    }

    /**
     * Called to do the actual rendering, see ItemRenderType for details on when specific types are run, and what extra
     * data is passed into the data parameter.
     *
     * @param type The render type
     * @param item The ItemStack being rendered
     * @param data Extra Type specific data
     */
    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
        GL11.glPushMatrix();
        Minecraft mc = Minecraft.getMinecraft();
        mc.renderEngine.bindTexture(Resources.Models.SPELL_BOOK);

        float opening;
        float pageFlip;

        int ticks = ClientTickEventHandler.ticksWithBookOpen;
        GL11.glTranslatef(0.3F + 0.02F * ticks, 0.475F + 0.01F * ticks, -0.2F - 0.01F * ticks);
        GL11.glRotatef(95F + ticks * 5, 0F, 1F, 0F);
        GL11.glRotatef(ticks * 2.5F, 0F, 0F, 1F);
        GL11.glScalef(0.9F, 0.9F, 0.9F);
        opening = ticks / 12F;
        pageFlip = ClientTickEventHandler.pageFlipTicks / 5F;

        modelBook.render(null, 0F, 0F, pageFlip, opening, 0F, 1F / 16F);

        GL11.glPopMatrix();
    }
}
