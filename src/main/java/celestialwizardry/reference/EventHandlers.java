package celestialwizardry.reference;

import celestialwizardry.client.handler.ClientTickEventHandler;
import celestialwizardry.spell.handler.SpellCastingEventHandler;

public class EventHandlers
{
    public static final SpellCastingEventHandler spellCastingEventHandler = new SpellCastingEventHandler();

    public static final ClientTickEventHandler clientTickEventHandler = new ClientTickEventHandler();
}
