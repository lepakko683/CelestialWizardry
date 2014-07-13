package celestibytes.celestialwizardry.item;

import celestibytes.celestialwizardry.CelestialWizardry;
import celestibytes.celestialwizardry.api.ILockedItem;
import celestibytes.celestialwizardry.reference.GuiIds;
import celestibytes.celestialwizardry.reference.Names;
import celestibytes.celestialwizardry.util.CWStringHelper;
import celestibytes.core.util.NBTHelper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSpellBook extends ItemSingle implements ILockedItem
{
    public ItemSpellBook()
    {
        super();
        this.setMaxStackSize(1);
        this.setUnlocalizedName(Names.Items.SPELL_BOOK);
    }

    /* ======================================== Item START   ===================================== */

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        // Set a UUID, if one doesn't exist already
        initSpellBook(stack, player);

        if (getOwner(stack).equals(player.getDisplayName()))
        {
            if (!player.isSneaking())
            {
                player.openGui(CelestialWizardry.instance, GuiIds.SPELL_BOOK, player.worldObj, 0, 0, 0);
            }
        }
        else
        {
            player.addChatComponentMessage(new ChatComponentText(
                    String.format(CWStringHelper.getMessage("stolenBook"), player.getDisplayName())));

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

    protected void initSpellBook(ItemStack stack, EntityPlayer player)
    {
        NBTHelper.setUUID(stack);

        if (!hasOwner(stack))
        {
            setOwner(stack, player);
        }
    }

    /* ======================================== Item END   ===================================== */

    /* ======================================== ILockedItem START   ===================================== */

    /**
     * Gives the owner of this item.
     *
     * @param stack the {@link net.minecraft.item.ItemStack} to check the owner from
     *
     * @return the display name of the owner ({@link net.minecraft.entity.player.EntityPlayer})
     */
    @Override
    public String getOwner(ItemStack stack)
    {
        return NBTHelper.getString(stack, Names.NBT.OWNER);
    }

    /**
     * Sets the owner for this item.
     *
     * @param stack  the {@link net.minecraft.item.ItemStack} to set the owner to
     * @param player the {@link net.minecraft.entity.player.EntityPlayer} that is set as owner
     */
    @Override
    public void setOwner(ItemStack stack, EntityPlayer player)
    {
        NBTHelper.setString(stack, Names.NBT.OWNER, player.getDisplayName());
    }

    /**
     * Checks does this item have owner.
     *
     * @param stack the {@link net.minecraft.item.ItemStack} to check the owner from
     *
     * @return true if this item has owner
     */
    @Override
    public boolean hasOwner(ItemStack stack)
    {
        return NBTHelper.hasTag(stack, Names.NBT.OWNER) && !getOwner(stack).equals("");
    }

    /* ======================================== ILockedItem END   ===================================== */
}
