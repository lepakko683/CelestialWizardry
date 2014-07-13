package celestibytes.celestialwizardry.crystal.handler;

import celestibytes.celestialwizardry.crystal.item.crafting.RecipesCrystalVanilla;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;

public class CrystalCraftingHandler
{
    public static void init()
    {
        // Initialize vanilla crafting recipes
        RecipesCrystalVanilla.init();
    }

    @SubscribeEvent
    public void onItemCraftedEvent(PlayerEvent.ItemCraftedEvent event)
    {

    }
}
