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
    public final ISpellContainer container;

    public SpellEvent(Spell spell, EntityPlayer player, ISpellContainer container)
    {
        this.spell = spell;
        this.player = player;
        this.container = container;
    }

    @Cancelable
    public static class PreCastEvent extends SpellEvent
    {
        public PreCastEvent(Spell spell, EntityPlayer player, ISpellContainer container)
        {
            super(spell, player, container);
        }
    }

    @Cancelable
    public static class CastEvent extends SpellEvent
    {
        public CastEvent(Spell spell, EntityPlayer player, ISpellContainer container)
        {
            super(spell, player, container);
        }
    }

    @Cancelable
    public static class PostCastEvent extends SpellEvent
    {
        public PostCastEvent(Spell spell, EntityPlayer player, ISpellContainer container)
        {
            super(spell, player, container);
        }
    }
}
