package celestibytes.celestialwizardry.handler;

import celestibytes.celestialwizardry.crystal.init.CrystalItems;
import celestibytes.celestialwizardry.crystal.item.ItemMatrix;
import celestibytes.celestialwizardry.entity.ModEntityProperties;
import celestibytes.celestialwizardry.init.ModItems;
import celestibytes.celestialwizardry.reference.Names;
import celestibytes.celestialwizardry.reference.Settings;
import celestibytes.celestialwizardry.util.CWStringHelper;
import celestibytes.celestialwizardry.util.LogHelper;
import celestibytes.core.util.PlayerHelper;
import celestibytes.core.util.SpawnHelper;

import net.minecraft.entity.player.EntityPlayerMP;
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

        System.out.println("PLAYER LOGIN!!!");

        if (entityProperties != null)
        {
            entityProperties.saveNBTData(event.player.getEntityData());
        }

        LogHelper.info("Player logged in! Name: " + event.player.getDisplayName());

        ModEntityProperties properties = ModEntityProperties.get(event.player);

        if (!properties.spellBook)
        {
            if (Settings.spawnSpellBook)
            {
                ItemStack stack = new ItemStack(ModItems.spellBook);

                LogHelper.info("Giving spellBook to " + event.player.getDisplayName());

                if (PlayerHelper.isPizzAna(event.player))
                {
                    NBTTagCompound tagCompound = new NBTTagCompound();
                    String name = CWStringHelper.ORANGE + "PizzAna's Spell Book" + CWStringHelper.END;
                    String lore1 = CWStringHelper.RED + CWStringHelper.ITALIC + "You should not touch this!";

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
                    String name = CWStringHelper.WHITE + "Forge Developer's Spell Book" + CWStringHelper.END;

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
                String name = CWStringHelper.WHITE + "PizzAna's Eye" + CWStringHelper.END;
                String lore1 = CWStringHelper.GREEN + CWStringHelper.ITALIC + "He is everywhere, he sees you"
                        + CWStringHelper.END;
                String lore2 = CWStringHelper.GREEN + CWStringHelper.ITALIC + "and what you do!" + CWStringHelper.END;

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
                String name = CWStringHelper.WHITE + "Feather Of a Phoenix Disguised As a Chicken Aka a Chicken's Feather"
                        + CWStringHelper.END;
                String lore1 = CWStringHelper.GREEN + CWStringHelper.ITALIC + "Nothing special, really.";

                NBTTagCompound tagCompound = new NBTTagCompound();
                tagCompound.setTag(Names.NBT.DISPLAY, new NBTTagCompound());
                tagCompound.getCompoundTag(Names.NBT.DISPLAY).setString(Names.NBT.NAME, name);

                NBTTagList list = new NBTTagList();

                list.appendTag(new NBTTagString(lore1));
                tagCompound.getCompoundTag(Names.NBT.DISPLAY).setTag(Names.NBT.LORE, list);

                stack.setTagCompound(tagCompound);

                SpawnHelper.spawnItemAtPlayer(event.player, stack);
            }

            properties.spellBook = true;
        }

        if (!properties.hasIntelligence)
        {
            properties.hasIntelligence = true;
            properties.intelligence = Settings.startingIntelligence;

            LogHelper.info("Initialized intelligence (" + properties.intelligence + ") for player " + event.player
                    .getDisplayName());
        }

        if (!properties.hasExp)
        {
            properties.hasExp = true;
            properties.exp = 0;

            LogHelper.info("Initialized experience (" + properties.intelligence + ") for player " + event.player
                    .getDisplayName());
        }

        if (!properties.fun)
        {
            ItemMatrix matrix = CrystalItems.matrix;

            if (PlayerHelper.isPizzAna(event.player))
            {
                ItemStack stack = new ItemStack(CrystalItems.matrix, 1, matrix.getDamageFromTier(5));
                String name = CWStringHelper.BRIGHT_BLUE + "PizzAna's Matrix" + CWStringHelper.END;

                NBTTagCompound tagCompound = new NBTTagCompound();

                tagCompound.setTag(Names.NBT.DISPLAY, new NBTTagCompound());
                tagCompound.getCompoundTag(Names.NBT.DISPLAY).setString(Names.NBT.NAME, name);

                stack.setTagCompound(tagCompound);
                matrix.setOwner(stack, event.player);

                matrix.setFull(stack);

                SpawnHelper.spawnItemAtPlayer(event.player, stack);
            }

            if (PlayerHelper.isForgeDevName(event.player))
            {
                ItemStack stack = new ItemStack(CrystalItems.matrix, 1, matrix.getDamageFromTier(1));
                String name = CWStringHelper.WHITE + "ForgeDeveloper's Matrix" + CWStringHelper.END;

                NBTTagCompound tagCompound = new NBTTagCompound();

                tagCompound.setTag(Names.NBT.DISPLAY, new NBTTagCompound());
                tagCompound.getCompoundTag(Names.NBT.DISPLAY).setString(Names.NBT.NAME, name);

                stack.setTagCompound(tagCompound);
                matrix.setOwner(stack, event.player);

                matrix.setFull(stack);

                SpawnHelper.spawnItemAtPlayer(event.player, stack);
            }

            properties.fun = true;
        }

        if (event.player instanceof EntityPlayerMP && !event.player.worldObj.isRemote)
        {
            ServerRuneConfigurationHandler.sendRuneConfigTo((EntityPlayerMP) event.player);
        }
    }
}
