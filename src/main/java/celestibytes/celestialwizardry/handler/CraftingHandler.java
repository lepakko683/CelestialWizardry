package celestibytes.celestialwizardry.handler;

import celestibytes.celestialwizardry.item.crafting.RecipesVanilla;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;

public class CraftingHandler
{
    public static void init()
    {
        // Initialize vanilla crafting recipes
        RecipesVanilla.init();
    }

    @SubscribeEvent
    public void onItemCraftedEvent(PlayerEvent.ItemCraftedEvent event)
    {

    }
}
