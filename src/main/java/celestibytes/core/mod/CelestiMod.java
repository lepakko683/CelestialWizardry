package celestibytes.core.mod;

import net.minecraft.client.Minecraft;

public abstract class CelestiMod implements IMod
{
    @Override
    public String getMinecraftVersion()
    {
        return "1.7.10";
    }
}
