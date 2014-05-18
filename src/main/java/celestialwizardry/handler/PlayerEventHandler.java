package celestialwizardry.handler;

import celestialwizardry.CelestialWizardry;
import celestialwizardry.entity.ModEntityProperties;
import celestialwizardry.init.ModItems;
import celestialwizardry.reference.Names;
import celestialwizardry.reference.Settings;
import celestialwizardry.util.PlayerHelper;
import celestialwizardry.util.SpawnHelper;
import celestialwizardry.util.StringHelper;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;

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

            if (Settings.spawnSpellBook)
            {
                ItemStack stack = new ItemStack(ModItems.spellBook);

                CelestialWizardry.log.info("Giving spellBook to " + event.player.getDisplayName());

                if (PlayerHelper.isPizzAna(event.player))
                {
                    NBTTagCompound tagCompound = new NBTTagCompound();
                    String name = StringHelper.ORANGE + "PizzAna's Spell Book" + StringHelper.END;
                    String lore1 = StringHelper.RED + StringHelper.ITALIC + "You should not touch this!";

                    tagCompound.setTag(Names.NBT.DISPLAY, new NBTTagCompound());
                    tagCompound.getCompoundTag(Names.NBT.DISPLAY).setString(Names.NBT.NAME, name);

                    NBTTagList list = new NBTTagList();

                    list.appendTag(new NBTTagString(lore1));
                    tagCompound.getCompoundTag(Names.NBT.DISPLAY).setTag(Names.NBT.LORE, list);

                    tagCompound.setBoolean(Names.NBT.IS_CUSTOM, true);
                    tagCompound.setString(Names.NBT.BACKUP_NAME, name);

                    stack.setTagCompound(tagCompound);
                }

                if (PlayerHelper.isForgeDevName(event.player))
                {
                    NBTTagCompound tagCompound = new NBTTagCompound();
                    String name = StringHelper.WHITE + "Forge Developer's Spell Book" + StringHelper.END;

                    tagCompound.setTag(Names.NBT.DISPLAY, new NBTTagCompound());
                    tagCompound.getCompoundTag(Names.NBT.DISPLAY).setString(Names.NBT.NAME, name);

                    tagCompound.setBoolean(Names.NBT.IS_CUSTOM, true);
                    tagCompound.setString(Names.NBT.BACKUP_NAME, name);

                    stack.setTagCompound(tagCompound);
                }

                if (!event.player.inventory.addItemStackToInventory(stack))
                {
                    SpawnHelper.spawnItemAtPlayer(event.player, stack);
                }
            }

            if (PlayerHelper.isPizzAna(event.player))
            {
                ItemStack stack = new ItemStack(Items.spider_eye);
                String name = StringHelper.WHITE + "PizzAna's Eye" + StringHelper.END;
                String lore1 = StringHelper.GREEN + StringHelper.ITALIC + "He is everywhere, he sees you"
                        + StringHelper.END;
                String lore2 = StringHelper.GREEN + StringHelper.ITALIC + "and what you do!" + StringHelper.END;

                NBTTagCompound tagCompound = new NBTTagCompound();

                tagCompound.setTag(Names.NBT.DISPLAY, new NBTTagCompound());
                tagCompound.getCompoundTag(Names.NBT.DISPLAY).setString(Names.NBT.NAME, name);

                NBTTagList list = new NBTTagList();

                list.appendTag(new NBTTagString(lore1));
                list.appendTag(new NBTTagString(lore2));
                tagCompound.getCompoundTag(Names.NBT.DISPLAY).setTag(Names.NBT.LORE, list);

                stack.setTagCompound(tagCompound);

                SpawnHelper.spawnItemAtPlayer(event.player, stack);
            }

            if (PlayerHelper.isLepakko683(event.player))
            {
                ItemStack stack = new ItemStack(Items.feather);
                String name = StringHelper.WHITE + "Feather Of a Phoenix Disguised As a Chicken Aka a Chicken's Feather"
                        + StringHelper.END;
                String lore1 = StringHelper.GREEN + StringHelper.ITALIC + "Nothing special, really.";

                NBTTagCompound tagCompound = new NBTTagCompound();
                tagCompound.setTag(Names.NBT.DISPLAY, new NBTTagCompound());
                tagCompound.getCompoundTag(Names.NBT.DISPLAY).setString(Names.NBT.NAME, name);

                NBTTagList list = new NBTTagList();

                list.appendTag(new NBTTagString(lore1));
                tagCompound.getCompoundTag(Names.NBT.DISPLAY).setTag(Names.NBT.LORE, list);

                stack.setTagCompound(tagCompound);

                SpawnHelper.spawnItemAtPlayer(event.player, stack);
            }
        }

        if (!properties.hasIntelligence)
        {
            properties.hasIntelligence = true;
            properties.intelligence = Settings.startingIntelligence;

            CelestialWizardry.log.info("Initialized intelligence (" + properties.intelligence + ") for player " + event.player.getDisplayName());
        }
    }
}
