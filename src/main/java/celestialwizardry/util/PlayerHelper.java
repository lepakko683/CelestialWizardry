package celestialwizardry.util;

import net.minecraft.entity.player.EntityPlayer;

public final class PlayerHelper
{
    public static boolean isPizzAna(EntityPlayer player)
    {
        return player.getDisplayName().toLowerCase().equals("PizzAna".toLowerCase());
    }

    public static boolean isForgeDevName(EntityPlayer player)
    {
        return player.getDisplayName().toLowerCase().equals("ForgeDevName".toLowerCase());
    }

    public static boolean isLepakko683(EntityPlayer player)
    {
        return player.getDisplayName().toLowerCase().equals("lepakko683".toLowerCase());
    }
}
