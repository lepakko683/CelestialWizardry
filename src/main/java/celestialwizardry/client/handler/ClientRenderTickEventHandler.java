package celestialwizardry.client.handler;

import celestialwizardry.client.DebugOverLay;
import celestialwizardry.util.Colour;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class ClientRenderTickEventHandler {
	
	@SubscribeEvent
	public void onRenderTick(TickEvent.RenderTickEvent event) {
		if(event.phase == TickEvent.Phase.END) {
			Gui currGui = Minecraft.getMinecraft().currentScreen;
			
			if(currGui == null) {
				Gui.drawRect(0, 0, 50, 50, Colour.packARGB(90, 255, 0, 255));
				
			}
		}
//		System.out.println("client render!");
	}
}
