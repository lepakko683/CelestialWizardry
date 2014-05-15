package celestialwizardry.reference;

import celestialwizardry.client.handler.ClientTickEventHandler;
import celestialwizardry.handler.EntityEventHandler;
import celestialwizardry.handler.PlayerEventHandler;
import celestialwizardry.spell.handler.SpellCastingEventHandler;

public class EventHandlers
{
    public static final SpellCastingEventHandler SPELL_CASTING_EVENT_HANDLER = new SpellCastingEventHandler();
    public static final ClientTickEventHandler CLIENT_TICK_EVENT_HANDLER = new ClientTickEventHandler();
    public static final PlayerEventHandler PLAYER_EVENT_HANDLER = new PlayerEventHandler();
    public static final EntityEventHandler ENTITY_EVENT_HANDLER = new EntityEventHandler();
}
