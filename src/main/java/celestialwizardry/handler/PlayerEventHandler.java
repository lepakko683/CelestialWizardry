package celestialwizardry.handler;

import celestialwizardry.CelestialWizardry;
import celestialwizardry.entity.ModEntityProperties;
import celestialwizardry.init.ModItems;
import celestialwizardry.reference.Settings;
import celestialwizardry.util.SpawnHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;

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

            if (event.player.getDisplayName().toLowerCase().equals("pizzana"))
            {
                ItemStack stack = new ItemStack(Items.spider_eye);

                NBTTagCompound tagCompound = new NBTTagCompound();
                tagCompound.setTag("display", new NBTTagCompound());
                tagCompound.getCompoundTag("display").setString("Name", "\u00A7f" + "PizzAna' Seeing Stuff");
                NBTTagList list = new NBTTagList();
                list.appendTag(new NBTTagString("\u00A72\u00A7o" + "He is everywhere, he sees you"));
                list.appendTag(new NBTTagString("\u00A72\u00A7o" + "and what you do!"));
                tagCompound.getCompoundTag("display").setTag("Lore", list);
                stack.setTagCompound(tagCompound);

                SpawnHelper.spawnItemAtPlayer(event.player, stack);
            }
        }
    }
}
