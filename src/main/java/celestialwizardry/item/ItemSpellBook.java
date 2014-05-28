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
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSpellBook extends ItemCW
{
    public static final String[] modes = {"guide", "inventory"};

    public ItemSpellBook()
    {
        super();
        this.setUnlocalizedName(Names.Items.SPELL_BOOK);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        // Set a UUID, if one doesn't exist already
        initSpellBook(stack);

        if (player.isSneaking())
        {
            changeMode(stack);
        }
        else
        {
            if (getMode(stack) == 0) // GUIDE
            {
                player.openGui(CelestialWizardry.instance, GuiIds.SPELL_BOOK, player.worldObj, 0, 0, 0);
            }
            else if (getMode(stack) == 1) // INVENTORY
            {
                NBTHelper.setBoolean(stack, Names.NBT.SPELL_BOOK_INVENTORY_OPEN, true);
                player.openGui(CelestialWizardry.instance, GuiIds.SPELL_BOOK_INVENTORY, player.worldObj,
                               (int) player.posX, (int) player.posY, (int) player.posZ);
            }
        }

        return stack;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getItemStackDisplayName(ItemStack stack)
    {
        String open = " (";
        String close = ")";

        return NBTHelper.hasUUID(stack) ? super.getItemStackDisplayName(stack) + open + getLocalizedModeWithColor(stack)
                + close : super.getItemStackDisplayName(stack);
    }

    protected static void initSpellBook(ItemStack stack)
    {
        if (!NBTHelper.hasUUID(stack))
        {
            setMode(stack, 0);
        }

        NBTHelper.setUUID(stack);
    }

    public static void changeMode(ItemStack stack)
    {
        int mode = getMode(stack);

        if (mode < modes.length - 1)
        {
            setMode(stack, mode + 1);
        }
        else
        {
            setMode(stack, 0);
        }

        if (NBTHelper.hasTag(stack, Names.NBT.DISPLAY))
        {
            NBTTagCompound tagCompound = stack.getTagCompound();
            String name = tagCompound.getString(Names.NBT.BACKUP_NAME);

            String open = "(";
            String close = ")";
            String append = " " + open + getLocalizedModeWithColor(stack) + close;

            tagCompound.getCompoundTag(Names.NBT.DISPLAY).setString(Names.NBT.NAME, name + append);

            stack.setTagCompound(tagCompound);
        }
    }

    public static void setMode(ItemStack stack, int mode)
    {
        NBTHelper.setInteger(stack, Names.NBT.MODE, mode);
    }

    public static int getMode(ItemStack stack)
    {
        return NBTHelper.getInt(stack, Names.NBT.MODE);
    }

    public static String getLocalizedMode(ItemStack stack)
    {
        return StringHelper.localize("mode." + Resources.RESOURCE_PREFIX + modes[getMode(stack)]);
    }

    public static String getLocalizedModeWithColor(ItemStack stack)
    {
        return getLocalizedMode(stack); // TODO
    }
}
