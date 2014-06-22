package celestialwizardry.reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import celestialwizardry.client.handler.ClientRenderTickEventHandler;
import celestialwizardry.client.handler.KeyInputEventHandler;
import celestialwizardry.handler.ClientTickEventHandler;
import celestialwizardry.handler.EntityEventHandler;
import celestialwizardry.handler.ItemExpireEventHandler;
import celestialwizardry.handler.PlayerEventHandler;
import celestialwizardry.handler.WorldEventHandler;

public class EventHandlers
{
	@SideOnly(Side.CLIENT)
	public static class Client {
		public static final ClientTickEventHandler CLIENT_TICK_EVENT_HANDLER = new ClientTickEventHandler();
	    public static final ClientRenderTickEventHandler CLIENT_RENDER_TICK_EVENT_HANDLER = new ClientRenderTickEventHandler();
	    public static final KeyInputEventHandler KEY_INPUT_EVENT_HANDLER = new KeyInputEventHandler();
	}
	
	public static class Common {
		public static final PlayerEventHandler PLAYER_EVENT_HANDLER = new PlayerEventHandler();
	    public static final EntityEventHandler ENTITY_EVENT_HANDLER = new EntityEventHandler();
	    public static final WorldEventHandler WORLD_EVENT_HANDLER = new WorldEventHandler();
	    public static final ItemExpireEventHandler ITEM_EXPIRE_EVENT_HANDLER = new ItemExpireEventHandler();
	}
}
