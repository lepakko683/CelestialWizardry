package celestibytes.core.mod;

import net.minecraft.client.Minecraft;

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
}
