package celestialwizardry.item;

import celestialwizardry.reference.Names;
import celestialwizardry.util.StringHelper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.List;

public class ItemMatrix extends ItemCW
{
    public ItemMatrix()
    {
        super();
        this.setUnlocalizedName(Names.Items.MATRIX);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getItemStackDisplayName(ItemStack stack)
    {
        return StringHelper.LIGHT_BLUE + super.getItemStackDisplayName(stack) + StringHelper.END;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean adv)
    {
        list.add(StringHelper.RED + "TODO" + StringHelper.END);

        super.addInformation(stack, player, list, adv);
    }
}
