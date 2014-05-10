package celestialwizardry.api.event;

import celestialwizardry.api.spell.Spell;
import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.entity.player.EntityPlayer;

@Cancelable
public class SpellCastingEvent extends Event
{
    public final EntityPlayer entityPlayer;

    public SpellCastingEvent(Spell spell, EntityPlayer player)
    {
        entityPlayer = player;
    }
}
