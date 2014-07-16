package celestibytes.celestialwizardry.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.List;

public abstract class ItemSingle extends ItemCW
{
    public ItemSingle()
    {
        super();
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return super.getUnlocalizedName();
    }

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void getSubItems(Item item, CreativeTabs tab, List list)
    {
        list.add(new ItemStack(item, 1, 0));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        itemIcon = iconRegister.registerIcon(
                this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int i)
    {
        return itemIcon;
    }
}
