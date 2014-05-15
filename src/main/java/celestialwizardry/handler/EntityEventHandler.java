package celestialwizardry.handler;

import celestialwizardry.entity.ModEntityProperties;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityEvent;

public class EntityEventHandler
{
    @SubscribeEvent
    public void onEntityConstructing(EntityEvent.EntityConstructing event)
    {
        if (event.entity instanceof EntityPlayer && ModEntityProperties.get((EntityPlayer) event.entity) == null)
        {
            ModEntityProperties.register((EntityPlayer) event.entity);
        }
    }
}
