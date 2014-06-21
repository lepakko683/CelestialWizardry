package celestialwizardry.proxy;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.SERVER)
public class ServerProxy extends CommonProxy
{
    @Override
    public void registerKeyBindings()
    {
        // NO-OP
    }

    @Override
    public void registerRenderer()
    {
        // NO-OP
    }

	@Override
	public void setupClientRuneconfig() {
		// Do nothing
		
	}

	@Override
	public void setupServerRuneconfig() {
		// TODO Do Something
		
	}
    
}
