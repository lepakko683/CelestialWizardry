package celestibytes.celestialwizardry.client.handler;

import celestibytes.celestialwizardry.client.render.RuneRenderer;
import celestibytes.celestialwizardry.util.ResourceLocationHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class ClientRenderTickEventHandler
{

    private static ResourceLocation runeSheet = ResourceLocationHelper
            .getResourceLocation("textures/runes/runeSheet01.png");
    private static RuneRenderer runeRend = new RuneRenderer(runeSheet);

    @SubscribeEvent
    public void onRenderTick(TickEvent.RenderTickEvent event)
    {
        if (event.phase == TickEvent.Phase.END)
        {
            Gui currGui = Minecraft.getMinecraft().currentScreen;

            if (currGui == null)
            {
//				Gui.drawRect(0, 0, 50, 50, Colour.packARGB(90, 255, 0, 255));

//				GL11.glEnable(GL11.GL_BLEND);
//				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
//				runeRend.renderRunes(null, 10, 10, 1f);
//				GL11.glDisable(GL11.GL_BLEND);

            }
        }
//		System.out.println("client render!");
    }
}
