package celestialwizardry.item;

import celestialwizardry.CelestialWizardry;
import celestialwizardry.reference.GuiIds;
import celestialwizardry.reference.Names;
import celestialwizardry.util.NBTHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemSpellBook extends ItemCW
{
    public static final String[] modes = {"guide", "inventory", "spell"};

    public ItemSpellBook()
    {
        super();
        this.setUnlocalizedName(Names.Items.SPELL_BOOK);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        if (!world.isRemote)
        {
            // Set a UUID, if one doesn't exist already
            initSpellBook(stack);

            if (player.isSneaking())
            {
                changeMode(stack);
            }
            else
            {
                NBTHelper.setBoolean(stack, Names.NBT.SPELL_BOOK_GUI_OPEN, true);
                player.openGui(CelestialWizardry.instance, GuiIds.SPELL_BOOK_INVENTORY, player.worldObj, (int) player.posX, (int) player.posY, (int) player.posZ);
            }
        }

        return stack;
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
    }

    public static void setMode(ItemStack stack, int mode)
    {
        NBTHelper.setInteger(stack, Names.NBT.MODE, mode);
    }

    public static int getMode(ItemStack stack)
    {
        return NBTHelper.getInt(stack, Names.NBT.MODE);
    }
}
