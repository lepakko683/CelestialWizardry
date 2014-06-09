package celestialwizardry.item;

import celestialwizardry.creativetab.CreativeTab;
import celestialwizardry.reference.Messages;
import celestialwizardry.reference.Resources;
import celestialwizardry.reference.Settings;
import celestialwizardry.util.IconRegistry;
import celestialwizardry.util.ItemHelper;
import celestialwizardry.util.StringHelper;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import gnu.trove.map.TMap;
import gnu.trove.map.hash.THashMap;

import java.util.ArrayList;
import java.util.List;

public abstract class ItemCW extends Item
{
    public TMap<Integer, ItemEntry> itemMap = new THashMap<Integer, ItemEntry>();
    public ArrayList<Integer> itemList = new ArrayList<Integer>();

    public boolean hasTextures = true;
    public String modName = Resources.RESOURCE_PREFIX.replace(":", "");

    public ItemCW()
    {
        super();
        this.maxStackSize = 64;
        this.setHasSubtypes(true);
        this.setCreativeTab(CreativeTab.CW_TAB);
        this.setNoRepair();
    }

    public ItemCW(String modName)
    {
        this();
        this.modName = modName;
    }

    public ItemCW setHasTextures(boolean hasTextures)
    {
        this.hasTextures = hasTextures;
        return this;
    }

    public ItemStack addItem(int number, String name, int rarity, boolean register)
    {
        if (itemMap.containsKey(number))
        {
            return null;
        }

        itemMap.put(number, new ItemEntry(name, rarity));
        itemList.add(number);

        ItemStack item = new ItemStack(this, 1, number);

        if (register)
        {
            GameRegistry.registerCustomItemStack(name, item);
        }

        return item;
    }

    public ItemStack addItem(int number, String name, int rarity)
    {
        return addItem(number, name, rarity, true);
    }

    public ItemStack addItem(int number, String name)
    {
        return addItem(number, name, 0);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int i)
    {
        if (!itemMap.containsKey(i))
        {
            return null;
        }

        return IconRegistry.getIcon(itemMap.get(i).name);
    }

    @Override
    public EnumRarity getRarity(ItemStack stack)
    {
        int i = stack.getItemDamage();

        if (!itemMap.containsKey(i))
        {
            return EnumRarity.common;
        }

        return EnumRarity.values()[itemMap.get(stack.getItemDamage()).rarity];
    }

    @Override
    @SuppressWarnings("unchecked")
    public void getSubItems(Item item, CreativeTabs tab, List list)
    {
        for (Integer anItemList : itemList)
        {
            list.add(new ItemStack(item, 1, anItemList));
        }
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("item.%s%s", Resources.RESOURCE_PREFIX,
                             getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        int i = ItemHelper.getItemDamage(stack);

        if (!itemMap.containsKey(i))
        {
            return "item." + Resources.RESOURCE_PREFIX + "invalid";
        }

        return getUnlocalizedName() + '.' + itemMap.get(i).name;
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister ir)
    {
        if (!hasTextures)
        {
            return;
        }

        for (Integer i : itemList)
        {
            ItemEntry item = itemMap.get(i);
            IconRegistry.addIcon(item.name,
                                 this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1) + "."
                                         + item.name, ir);
        }
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

    public class ItemEntry
    {
        public String name;
        public int rarity = 0;
        public int maxDamage = 0;

        public ItemEntry(String name, int rarity, int maxDamage)
        {
            this.name = name;
            this.rarity = rarity;
            this.maxDamage = maxDamage;
        }

        public ItemEntry(String name, int rarity)
        {
            this.name = name;
            this.rarity = rarity;
        }

        public ItemEntry(String name)
        {
            this.name = name;
        }
    }
}
