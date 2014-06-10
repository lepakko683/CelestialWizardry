package celestialwizardry.item;

import celestialwizardry.CelestialWizardry;
import celestialwizardry.reference.GuiIds;
import celestialwizardry.reference.Names;
import celestialwizardry.reference.Resources;
import celestialwizardry.util.NBTHelper;
import celestialwizardry.util.StringHelper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSpellBook extends ItemSingle
{
    public ItemSpellBook()
    {
        super();
        this.setMaxStackSize(1);
        this.setUnlocalizedName(Names.Items.SPELL_BOOK);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        // Set a UUID, if one doesn't exist already
        initSpellBook(stack, player);

        if (NBTHelper.getString(stack, Names.NBT.OWNER).equals(player.getDisplayName()))
        {
            if (!player.isSneaking())
            {
                player.openGui(CelestialWizardry.instance, GuiIds.SPELL_BOOK, player.worldObj, 0, 0, 0);
            }
        }
        else
        {
            player.addChatComponentMessage(new ChatComponentText(String.format(StringHelper.localize("message." + Resources.RESOURCE_PREFIX + "stolenBook"), player.getDisplayName())));

            NBTTagCompound openers = new NBTTagCompound();

            if (NBTHelper.hasTag(stack, Names.NBT.OPENERS))
            {
                openers = stack.stackTagCompound.getCompoundTag(Names.NBT.OPENERS);
            }

            int times = 0;

            if (openers.hasKey(player.getDisplayName()))
            {
                times = openers.getInteger(player.getDisplayName());
            }

            times = times + 1;

            openers.setInteger(player.getDisplayName(), times);
        }

        return stack;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getItemStackDisplayName(ItemStack stack)
    {
        return super.getItemStackDisplayName(stack);
    }

    protected static void initSpellBook(ItemStack stack, EntityPlayer player)
    {
        NBTHelper.setUUID(stack);

        if (!NBTHelper.hasTag(stack, Names.NBT.OWNER))
        {
            NBTHelper.setString(stack, Names.NBT.OWNER, player.getDisplayName());
        }
    }
}
