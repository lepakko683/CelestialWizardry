package celestialwizardry.event;

import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.entity.player.EntityPlayer;

@Cancelable
public class SpellCastingEvent extends Event
{
    public final EntityPlayer entityPlayer;

    public SpellCastingEvent(EntityPlayer player)
    {
        entityPlayer = player;
    }
}
