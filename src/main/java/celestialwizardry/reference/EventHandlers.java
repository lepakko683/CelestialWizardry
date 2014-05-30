package celestialwizardry.reference;

import celestialwizardry.handler.ClientTickEventHandler;
import celestialwizardry.handler.EntityEventHandler;
import celestialwizardry.handler.PlayerEventHandler;
import celestialwizardry.spell.handler.SpellEventHandler;

public class EventHandlers
{
    public static final SpellEventHandler SPELL_EVENT_HANDLER = new SpellEventHandler();
    public static final ClientTickEventHandler CLIENT_TICK_EVENT_HANDLER = new ClientTickEventHandler();
    public static final PlayerEventHandler PLAYER_EVENT_HANDLER = new PlayerEventHandler();
    public static final EntityEventHandler ENTITY_EVENT_HANDLER = new EntityEventHandler();
}
