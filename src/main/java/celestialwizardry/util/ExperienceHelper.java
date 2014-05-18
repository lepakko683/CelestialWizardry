package celestialwizardry.util;

import celestialwizardry.entity.ModEntityProperties;

import net.minecraft.entity.player.EntityPlayer;

public final class ExperienceHelper
{
    public static void incrementExp(EntityPlayer player, int amount)
    {
        ModEntityProperties properties = ModEntityProperties.get(player);

        int exp = properties.exp;

        properties.exp = exp + amount;
    }
}
