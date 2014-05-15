package celestialwizardry.handler;

import celestialwizardry.CelestialWizardry;
import celestialwizardry.entity.ModEntityProperties;
import celestialwizardry.init.ModItems;
import celestialwizardry.reference.Settings;
import celestialwizardry.util.SpawnHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.item.ItemStack;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerEventHandler
{
    public ConcurrentHashMap<UUID, ModEntityProperties> propertiesConcurrentHashMap
            = new ConcurrentHashMap<UUID, ModEntityProperties>();

    @SubscribeEvent
    public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event)
    {
        ModEntityProperties entityProperties = propertiesConcurrentHashMap.remove(event.player.getPersistentID());

        if (entityProperties != null)
        {
            entityProperties.saveNBTData(event.player.getEntityData());
        }

        CelestialWizardry.log.info("Player logged in! Name: " + event.player.getDisplayName());

        ModEntityProperties properties = ModEntityProperties.get(event.player);

        if (!properties.spellBook)
        {
            properties.spellBook = true;

            if (Settings.spawnBook)
            {
                ItemStack stack = new ItemStack(ModItems.spellBook);

                CelestialWizardry.log.info("Giving spellBook to " + event.player.getDisplayName());

                if (!event.player.inventory.addItemStackToInventory(stack))
                {
                    SpawnHelper.spawnItemAtPlayer(event.player, stack);
                }
            }
        }
    }
}
