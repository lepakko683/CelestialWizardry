package celestibytes.core.mod;

public interface IMod
{
    public String getId();

    public String getName();

    public String getTargetLog();

    public String getVersion();

    public String getMinecraftVersion();

    public boolean allowVersionCheck();

    public boolean allowVersionNote();

    public void setRegistered(boolean registered);

    public boolean isRegistered();
}
