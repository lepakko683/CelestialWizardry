package celestibytes.core.mod;

import celestibytes.core.mod.version.Channel;

public abstract class CelestiMod implements IMod
{
    protected boolean registered = false;

    @Override
    public String getMinecraftVersion()
    {
        return "1.7.10";
    }

    @Override
    public void setRegistered(boolean registered)
    {
        this.registered = registered;
    }

    @Override
    public boolean isRegistered()
    {
        return registered;
    }

    public Channel getChannel()
    {
        return Channel.getChannelFromString(channel());
    }

    protected abstract String channel();

    @Override
    public Channel getUpdateChannel()
    {
        return Channel.getChannelFromString(updateChannel());
    }

    protected abstract String updateChannel();
}
