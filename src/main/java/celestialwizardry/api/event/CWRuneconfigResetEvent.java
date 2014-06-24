package celestialwizardry.api.event;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.relauncher.Side;

public class CWRuneconfigResetEvent extends Event {
	
	public final Side side;
	
	public CWRuneconfigResetEvent(Side side) {
		this.side = side;
	}

}
