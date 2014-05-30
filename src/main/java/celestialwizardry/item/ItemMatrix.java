package celestialwizardry.item;

import celestialwizardry.reference.Names;
import celestialwizardry.util.StringHelper;

import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

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
        return StringHelper.LIGHT_BLUE + super.getItemStackDisplayName(stack) + StringHelper.END + StringHelper.RED
                + StringHelper.localize("TODO") + StringHelper.END;
    }
}
