package celestibytes.celestialwizardry.client.handler;

import celestibytes.celestialwizardry.client.settings.KeyBindings;
import celestibytes.celestialwizardry.reference.Key;

import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class KeyInputEventHandler
{
    @SubscribeEvent
    public void handleKeyInputEvent(InputEvent.KeyInputEvent event)
    {
        if (FMLClientHandler.instance().getClient().inGameHasFocus)
        {
            if (FMLClientHandler.instance().getClientPlayerEntity() != null)
            {
                EntityPlayer entityPlayer = FMLClientHandler.instance().getClientPlayerEntity();

                // TODO Get current selected spell or change to next spell
            }
        }
    }

    private static Key getPressedKeyBinding()
    {
        if (KeyBindings.cast.isPressed())
        {
            return Key.CAST;
        }
        else if (KeyBindings.change.isPressed())
        {
            return Key.CHANGE;
        }

        return Key.UNKNOWN;
    }
}
