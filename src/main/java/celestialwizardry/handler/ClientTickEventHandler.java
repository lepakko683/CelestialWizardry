package celestialwizardry.handler;

import celestialwizardry.client.gui.GuiSpellBook;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class ClientTickEventHandler
{
    /**
     * Used to animate the opening of the book
     */
    public static int ticksWithBookOpen = 0;
    /**
     * Used to animate page flip
     */
    public static int pageFlipTicks = 0;
    /**
     * Used to determine the direction which the page is flipped
     */
    private static boolean flipleft = true;

    @SubscribeEvent
    public void tickEnd(TickEvent.ClientTickEvent event)
    {
        if (event.phase == TickEvent.Phase.END && event.type == TickEvent.Type.CLIENT)
        {
            GuiScreen gui = Minecraft.getMinecraft().currentScreen;
            int ticksToOpen = 10; // the number of ticks it takes to open the book
            if (gui instanceof GuiSpellBook)
            {
                if (ticksWithBookOpen < 0)
                {
                    ticksWithBookOpen = 0;
                }

                if (ticksWithBookOpen < ticksToOpen)
                {
                    ticksWithBookOpen++;
                }
                if (flipleft)
                {
                    if (pageFlipTicks > 0)
                    {
                        pageFlipTicks--;
                    }
                }
                else if (!flipleft)
                {
                    if (pageFlipTicks < 5)
                    {
                        pageFlipTicks++;
                    }
                    else
                    {
                        flipleft = true;
                        pageFlipTicks = 0;
                    }
                }

            }
            else
            {
                pageFlipTicks = 0;
                flipleft = true;

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

    public static void notifyPageChange(boolean flipleftv)
    {
        if (flipleftv && pageFlipTicks == 0)
        {
            // left
            flipleft = true;
            pageFlipTicks = 5;
            return;
        }
        if (pageFlipTicks == 0)
        {
            // right
            flipleft = false;
        }
//        if (pageFlipTicks == 0)
//        {
//            pageFlipTicks = 5;
//        }
    }
}
