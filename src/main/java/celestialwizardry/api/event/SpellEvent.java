package celestialwizardry.api.event;

import celestialwizardry.api.spell.ISpellContainer;
import celestialwizardry.api.spell.Spell;
import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.entity.player.EntityPlayer;

@Cancelable
public class SpellEvent extends Event
{
    public final Spell spell;
    public final EntityPlayer player;
    public final ISpellContainer item;

    public SpellEvent(Spell spell, EntityPlayer player, ISpellContainer item)
    {
        this.spell = spell;
        this.player = player;
        this.item = item;
    }

    public static class CastingStartEvent extends SpellEvent
    {
        public CastingStartEvent(Spell spell, EntityPlayer player, ISpellContainer item)
        {
            super(spell, player, item);
        }
    }

    @Cancelable
    public static class PreCastEvent extends SpellEvent
    {
        public PreCastEvent(Spell spell, EntityPlayer player, ISpellContainer item)
        {
            super(spell, player, item);
        }
    }

    @Cancelable
    public static class CastEvent extends SpellEvent
    {
        public CastEvent(Spell spell, EntityPlayer player, ISpellContainer item)
        {
            super(spell, player, item);
        }
    }

    @Cancelable
    public static class PostCastEvent extends SpellEvent
    {
        public PostCastEvent(Spell spell, EntityPlayer player, ISpellContainer item)
        {
            super(spell, player, item);
        }
    }
}
