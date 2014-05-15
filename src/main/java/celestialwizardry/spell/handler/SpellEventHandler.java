package celestialwizardry.spell.handler;

import celestialwizardry.api.event.SpellEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class SpellEventHandler
{
    @SubscribeEvent
    public void onPreCast(SpellEvent.PreCastEvent event)
    {
        // TODO Something here
    }

    @SubscribeEvent
    public void onCast(SpellEvent.CastEvent event)
    {
        // TODO The actual spell here
    }

    @SubscribeEvent
    public void onPostCast(SpellEvent.PostCastEvent event)
    {
        // TODO Post stuff (decrease mana, etc.) here
    }
}
