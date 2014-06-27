package celestialwizardry.proxy;

import net.minecraft.world.World;

public interface IProxy
{
    public void registerEventHandlers();

    public void registerTileEntities();

    public void registerKeyBindings();

    public void registerRenderer();
}
