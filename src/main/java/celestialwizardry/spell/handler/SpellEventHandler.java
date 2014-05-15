package celestialwizardry.spell.handler;

import celestialwizardry.api.event.SpellEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class SpellEventHandler
{
    @SubscribeEvent
    public void onCastingStart(SpellEvent.CastingStartEvent event)
    {
        event.spell.cast(event.item, event.player);
    }

    @SubscribeEvent
    public void onPreCast(SpellEvent.PreCastEvent event)
    {
        // TODO Something here
    }

    @SubscribeEvent
    public void onCast(SpellEvent.CastEvent event)
    {
        // TODO Something here
    }

    @SubscribeEvent
    public void onPostCast(SpellEvent.PostCastEvent event)
    {
        // TODO Post stuff (decrease mana, etc.) here
    }
}
