package celestialwizardry.item;

import celestialwizardry.api.energy.EnergyType;
import celestialwizardry.reference.Names;
import celestialwizardry.reference.Resources;
import celestialwizardry.registry.EnergyRegistry;
import celestialwizardry.util.NBTHelper;
import celestialwizardry.util.StringHelper;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemMatrix extends ItemCW
{
    @SideOnly(Side.CLIENT)
    private IIcon icons[];

    private static final float[] MAX_ENERGIES = new float[]{1000f, 10000f, 100000f, 1000000f};

    public ItemMatrix()
    {
        super();
        this.setUnlocalizedName(Names.Items.MATRIX);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
    }

    public static Map<EnergyType, Float> getEnergies(ItemStack stack)
    {
        Map<EnergyType, Float> ret = null;

        if (stack.stackTagCompound != null && NBTHelper.hasTag(stack, Names.NBT.ENERGIES))
        {
            ret = new HashMap<EnergyType, Float>();
            NBTTagCompound energies = stack.getTagCompound().getCompoundTag(Names.NBT.ENERGIES);

            for (Object o : energies.func_150296_c())
            {
                ret.put(EnergyRegistry.getEnergyType((String) o), energies.getFloat((String) o));
            }
        }

        return ret;
    }

    public static void appendEnergy(ItemStack stack, EnergyType type, float amount)
    {
        String name = type.getEnergyName();

        if (stack.stackTagCompound == null)
        {
            stack.setTagCompound(new NBTTagCompound());
        }

        if (!NBTHelper.hasTag(stack, Names.NBT.ENERGIES))
        {
            stack.getTagCompound().setTag(Names.NBT.ENERGIES, new NBTTagCompound());
        }

        NBTTagCompound energyTagCompound = stack.getTagCompound().getCompoundTag(Names.NBT.ENERGIES);
        float old = 0f;

        if (energyTagCompound.hasKey(name))
        {
            old = energyTagCompound.getFloat(name);
        }

        float total = 0f;

        for (float f : getEnergies(stack).values())
        {
            total = total + f;
        }

        float check = total + amount;

        if (check <= getMaxEnergy(stack))
        {
            if (check < 0)
            {
                energyTagCompound.setFloat(name, 0f);
            }
            else
            {
                energyTagCompound.setFloat(name, old + amount);
            }
        }
        else if (check > getMaxEnergy(stack))
        {
            float add = getMaxEnergy(stack) - total;

            energyTagCompound.setFloat(name, add);
        }
    }

    public static void decreaseEnergy(ItemStack stack, EnergyType type, float amount)
    {
        appendEnergy(stack, type, -amount);
    }

    public static float getMaxEnergy(ItemStack stack)
    {
        return MAX_ENERGIES[stack.getItemDamage()];
    }

    public static int getTier(ItemStack stack)
    {
        return stack.getItemDamage() + 1;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getItemStackDisplayName(ItemStack stack)
    {
        return StringHelper.LIGHT_BLUE + super.getItemStackDisplayName(stack) + StringHelper.END;
    }

    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("unchecked")
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean adv)
    {
        list.add(StringHelper.RED + "TODO" + StringHelper.END);

        super.addInformation(stack, player, list, adv);

        // Proper tooltips start here

        /* if (!StringHelper.isShiftKeyDown())
        {
            list.add(StringHelper.getShiftText());
            return;
        } */

        // list.add(StringHelper.localize("tooltip." + Resources.RESOURCE_PREFIX + "tier") + " " + getTier(stack));

        /* float total = 0f;

        for (float f : getEnergies(stack).values())
        {
            total = total + f;
        }

        list.add(StringHelper.localize("tooltip." + Resources.RESOURCE_PREFIX + "total") + ": " + total + "/"
                         + getMaxEnergy(stack));

        for (EnergyType type : getEnergies(stack).keySet())
        {
            list.add(type.getEnergyName() + ": " + getEnergies(stack).get(type) + "/" + getMaxEnergy(stack));
        } */
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("item.%s%s", Resources.RESOURCE_PREFIX, Names.Items.MATRIX);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return String
                .format("item.%s%s.%s.%s", Resources.RESOURCE_PREFIX, Names.Items.MATRIX, "tier", getTier(itemStack));
    }

    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("unchecked")
    public void getSubItems(Item item, CreativeTabs creativeTab, List list)
    {
        for (int meta = 0; meta < MAX_ENERGIES.length; ++meta)
        {
            list.add(new ItemStack(this, 1, meta));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int meta)
    {
        return icons[meta]; // icons[MathHelper.clamp_int(meta, 0, MAX_ENERGIES.length - 1)];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        icons = new IIcon[MAX_ENERGIES.length];

        for (int i = 0; i < MAX_ENERGIES.length; i++)
        {
            icons[i] = iconRegister
                    .registerIcon(Resources.RESOURCE_PREFIX + Names.Items.MATRIX + "." + "tier." + (i + 1));
        }
    }
}
