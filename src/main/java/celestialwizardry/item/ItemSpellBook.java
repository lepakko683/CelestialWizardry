package celestialwizardry.item;

import celestialwizardry.CelestialWizardry;
import celestialwizardry.reference.GuiIds;
import celestialwizardry.reference.Names;
import celestialwizardry.util.NBTHelper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSpellBook extends ItemCW
{
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

        if (!player.isSneaking())
        {
            player.openGui(CelestialWizardry.instance, GuiIds.SPELL_BOOK, player.worldObj, 0, 0, 0);
        }

        return stack;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getItemStackDisplayName(ItemStack stack)
    {
        return super.getItemStackDisplayName(stack);
    }

    protected static void initSpellBook(ItemStack stack)
    {
        NBTHelper.setUUID(stack);
    }
}
