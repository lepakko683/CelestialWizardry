package celestialwizardry.item;

import celestialwizardry.creativetab.CreativeTab;
import celestialwizardry.reference.Messages;
import celestialwizardry.reference.Resources;
import celestialwizardry.reference.Settings;
import celestialwizardry.util.StringHelper;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.List;

public class ItemCW extends Item
{
    public ItemCW()
    {
        super();
        this.maxStackSize = 1;
        this.setCreativeTab(CreativeTab.CW_TAB);
        this.setNoRepair();
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("item.%s%s", Resources.RESOURCE_PREFIX,
                             getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return String.format("item.%s%s", Resources.RESOURCE_PREFIX,
                             getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        itemIcon = iconRegister
                .registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean adv)
    {
        if (Settings.debugMode)
        {
            if (!StringHelper.isControlKeyDown())
            {
                list.add(StringHelper.getControlText());
                return;
            }

            if (stack.hasTagCompound())
            {
                list.add(StringHelper.localize(Messages.NBT));
                NBTTagCompound tagCompound = stack.getTagCompound();

                for (Object o : tagCompound.func_150296_c())
                {
                    String s = String.valueOf(o) + ":" + tagCompound.getTag(String.valueOf(o)).toString();
                    list.add(s);
                }
            }
            else
            {
                list.add(StringHelper.localize(Messages.NO_NBT));
            }
        }
    }
}
