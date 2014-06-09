package celestialwizardry.item;

import java.util.List;

import celestialwizardry.reference.Names;
import celestialwizardry.reference.Resources;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemDummy extends ItemCW {
	
	
	private IIcon itemIcons[];
	
	public ItemDummy() {
		this.setMaxStackSize(64);
		this.setTextureName("texture-dummyitemCW");
		this.setHasSubtypes(true);
		this.setUnlocalizedName(Names.Items.DUMMY_ITEM);
	}
	
	@Override
    public String getUnlocalizedName()
    {
        return String.format("item.%s%s", Resources.RESOURCE_PREFIX, Names.Items.DUMMY_ITEM);
    }
	
	@Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return String.format("item.%s%s", Resources.RESOURCE_PREFIX, Names.Items.DUMMY_ITEMS[MathHelper.clamp_int(itemStack.getItemDamage(), 0, Names.Items.DUMMY_ITEMS.length-1)]);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int par1)
    {
        int j = MathHelper.clamp_int(par1, 0, Names.Items.DUMMY_ITEMS.length-1);
        return this.itemIcons[j];
    }

    @Override
	@SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister)
    {
        this.itemIcons = new IIcon[Names.Items.DUMMY_ITEMS.length];

        for (int i = 0; i < itemIcons.length; ++i)
        {
            this.itemIcons[i] = par1IconRegister.registerIcon(Resources.RESOURCE_PREFIX + Names.Items.DUMMY_ITEMS[i]);
        }
    }

    @Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < Names.Items.DUMMY_ITEMS.length; i++) {
            list.add(new ItemStack(item, 1, i));
        }
    }
}
