package celestialwizardry.client.handler;

import celestialwizardry.client.gui.GuiSpellBook;
import celestialwizardry.client.gui.inventory.GuiSpellBookInventory;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

@SideOnly(Side.CLIENT)
public class ClientTickEventHandler
{
    public static int ticksWithBookOpen = 0;
    public static int pageFlipTicks = 0;

    @SubscribeEvent
    public void tickEnd(TickEvent.ClientTickEvent event)
    {
        if (event.phase == TickEvent.Phase.END && event.type == TickEvent.Type.CLIENT)
        {
            GuiScreen gui = Minecraft.getMinecraft().currentScreen;
            int ticksToOpen = 10;

            if (gui instanceof GuiSpellBook || gui instanceof GuiSpellBookInventory)
            {
                if (ticksWithBookOpen < 0)
                {
                    ticksWithBookOpen = 0;
                }

                if (ticksWithBookOpen < ticksToOpen)
                {
                    ticksWithBookOpen++;
                }

                if (pageFlipTicks > 0)
                {
                    pageFlipTicks--;
                }
            }
            else
            {
                pageFlipTicks = 0;

                if (ticksWithBookOpen > 0)
                {
                    if (ticksWithBookOpen > ticksToOpen)
                    {
                        ticksWithBookOpen = ticksToOpen;
                    }

                    ticksWithBookOpen--;
                }
            }
        }
    }

    public static void notifyPageChange()
    {
        if (pageFlipTicks == 0)
        {
            pageFlipTicks = 5;
        }
    }
}
