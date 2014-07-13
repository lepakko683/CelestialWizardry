package celestibytes.celestialwizardry.proxy;

public interface IProxy
{
    public void registerEventHandlers();

    public void registerTileEntities();

    public void registerKeyBindings();

    public void registerRenderer();
    
    public void initializeRuneRegistries();
}
