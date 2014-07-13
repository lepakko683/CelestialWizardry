package celestibytes.core.mod;

public interface IMod
{
    public String getId();

    public String getName();

    public String getVersion();

    public String getMinecraftVersion();

    public boolean allowVersionNote();
}
