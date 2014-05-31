package celestialwizardry.handler;

import celestialwizardry.client.gui.spellbookold.GuiSpellBookOld;
import celestialwizardry.client.gui.inventory.GuiSpellBookInventory;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

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

            if (gui instanceof GuiSpellBookOld || gui instanceof GuiSpellBookInventory)
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
