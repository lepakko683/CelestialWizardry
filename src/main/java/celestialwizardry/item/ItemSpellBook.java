package celestialwizardry.item;

import celestialwizardry.reference.Names;
import celestialwizardry.util.NBTHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemSpellBook extends ItemCW
{
    public ItemSpellBook()
    {
        super();
        this.setUnlocalizedName(Names.Items.SPELL_BOOK);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer)
    {
        if (!world.isRemote)
        {
            // Set a UUID, if one doesn't exist already
            NBTHelper.setUUID(itemStack);
            NBTHelper.setBoolean(itemStack, Names.NBT.SPELL_BOOK_GUI_OPEN, true);
            // entityPlayer.openGui(CelestialWizardry.instance, GuiIds.SPELL_BOOK, entityPlayer.worldObj,
            // (int) entityPlayer.posX, (int) entityPlayer.posY, (int) entityPlayer.posZ);
        }

        return itemStack;
    }
}
