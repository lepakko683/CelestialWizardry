package celestialwizardry.item;

import celestialwizardry.CelestialWizardry;
import celestialwizardry.api.energy.EnergyType;
import celestialwizardry.reference.GuiIds;
import celestialwizardry.reference.Names;
import celestialwizardry.reference.Resources;
import celestialwizardry.reference.Settings;
import celestialwizardry.registry.EnergyRegistry;
import celestialwizardry.util.EnergyHelper;
import celestialwizardry.util.NBTHelper;
import celestialwizardry.util.StringHelper;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemMatrix extends ItemSingle
{
    @SideOnly(Side.CLIENT)
    private IIcon icons[];

    private static final float[] MAX_ENERGIES = new float[]{1000f, 10000f, 100000f, 1000000f};

    public ItemMatrix()
    {
        super();
        this.setMaxStackSize(1);
        this.setUnlocalizedName(Names.Items.MATRIX);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
    }

    public static EnergyType getEnergyType(ItemStack stack)
    {
        if (stack.stackTagCompound != null && NBTHelper.hasTag(stack, Names.NBT.ENERGY))
        {
            String name = stack.stackTagCompound.getString(Names.NBT.ENERGY);
            return EnergyRegistry.getEnergyType(name);
        }

        return null;
    }

    public static float getEnergyStored(ItemStack stack)
    {
        if (stack.stackTagCompound != null && NBTHelper.hasTag(stack, Names.NBT.ENERGY))
        {
            String name = stack.stackTagCompound.getString(Names.NBT.ENERGY);

            if (NBTHelper.hasTag(stack, name))
            {
                return stack.stackTagCompound.getFloat(name);
            }
        }

        return 0f;
    }

    public static boolean appendEnergy(ItemStack stack, EnergyType type, float amount, boolean transform)
    {
        String name = type.getEnergyName();

        if (stack.stackTagCompound == null)
        {
            stack.setTagCompound(new NBTTagCompound());
        }

        if (!NBTHelper.hasTag(stack, Names.NBT.ENERGY))
        {
            NBTHelper.setString(stack, Names.NBT.ENERGY, type.getEnergyName());
            NBTHelper.setFloat(stack, Names.NBT.ENERGY_STORED, amount <= getMaxEnergy(stack) ? (amount < 0f ? 0f : amount) : getMaxEnergy(stack));
            return true;
        }

        if (NBTHelper.getString(stack, Names.NBT.ENERGY).equals(name))
        {
            float energyStored = NBTHelper.getFloat(stack, Names.NBT.ENERGY_STORED);
            amount = amount + energyStored;
            NBTHelper.setFloat(stack, Names.NBT.ENERGY_STORED, amount <= getMaxEnergy(stack) ? (amount < 0f ? 0f : amount) : getMaxEnergy(stack));
            return true;
        }
        else
        {
            String energyName = NBTHelper.getString(stack, Names.NBT.ENERGY);
            float energyStored = NBTHelper.getFloat(stack, Names.NBT.ENERGY_STORED);
            EnergyType energyType = EnergyRegistry.getEnergyType(energyName);

            if (transform && EnergyHelper.canTransform(type, energyType))
            {
                float ratio = EnergyHelper.getTransformRatio(type, energyType);
                amount = amount * ratio;
                amount = amount + energyStored;
                NBTHelper.setFloat(stack, Names.NBT.ENERGY_STORED, amount <= getMaxEnergy(stack) ? (amount < 0f ? 0f : amount) : getMaxEnergy(stack));
                return true;
            }
        }

        return false;
    }

    public static boolean decreaseEnergy(ItemStack stack, EnergyType type, float amount)
    {
        return appendEnergy(stack, type, -amount, false);
    }

    public static float getMaxEnergy(ItemStack stack)
    {
        return MAX_ENERGIES[stack.getItemDamage()];
    }

    public static int getTier(ItemStack stack)
    {
        return stack.getItemDamage() + 1;
    }

    public static String getOwner(ItemStack stack)
    {
        return NBTHelper.getString(stack, Names.NBT.OWNER);
    }

    public static void setOwner(ItemStack stack, EntityPlayer player)
    {
        NBTHelper.setString(stack, Names.NBT.OWNER, player.getDisplayName());
    }

    public static boolean hasOwner(ItemStack stack)
    {
        return NBTHelper.hasTag(stack, Names.NBT.OWNER) && !getOwner(stack).equals("");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getItemStackDisplayName(ItemStack stack)
    {
        StringBuilder ret = new StringBuilder();

        /* if (getTier(stack) == 1)
        {
            // NO-OP
        }
        else */
        if (getTier(stack) == 2)
        {
            ret.append(StringHelper.YELLOW);
        }
        else if (getTier(stack) == 3)
        {
            ret.append(StringHelper.BRIGHT_BLUE);
        }
        else if (getTier(stack) == 4)
        {
            ret.append(StringHelper.PINK);
        }

        ret.append(super.getItemStackDisplayName(stack));
        ret.append(StringHelper.END);

        return ret.toString();
    }

    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("unchecked")
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean adv)
    {
        list.add(StringHelper.RED + "TODO" + StringHelper.END);

        super.addInformation(stack, player, list, adv);

        // Proper tooltips start here

        if (Settings.shiftForDetails && !StringHelper.isShiftKeyDown())
        {
            list.add(StringHelper.getShiftText());
        }

        if (!StringHelper.isShiftKeyDown())
        {
            return;
        }

        list.add(StringHelper.localize("tooltip." + Resources.RESOURCE_PREFIX + "tier") + " " + getTier(stack));

        if (stack.stackTagCompound == null)
        {
            list.add(StringHelper.localize("tooltip." + Resources.RESOURCE_PREFIX + "maxStorage") + ": " + String.valueOf(getMaxEnergy(stack)));
            list.add(StringHelper.localize("tooltip." + Resources.RESOURCE_PREFIX + "noOwner"));
        }
        else
        {
            if (getEnergyStored(stack) == 0)
            {
                list.add(StringHelper.localize("tooltip." + Resources.RESOURCE_PREFIX + "maxStorage") + ": " + String.valueOf(getMaxEnergy(stack)));
            }
            else
            {
                list.add(StringHelper.localize("tooltip." + Resources.RESOURCE_PREFIX + "energyType") + ": " + getEnergyType(stack).getEnergyName());
                list.add(StringHelper.localize("tooltip." + Resources.RESOURCE_PREFIX + "energyStored") + ": " + getEnergyStored(stack) + '/' + String.valueOf(getMaxEnergy(stack)));
            }

            if (hasOwner(stack))
            {
                if (getOwner(stack).equals(player.getDisplayName()))
                {
                    list.add(StringHelper.localize("tooltip." + Resources.RESOURCE_PREFIX + "yourMatrix"));
                }
                else
                {
                    list.add(StringHelper.localize("tooltip." + Resources.RESOURCE_PREFIX + "owner") + ": " + getOwner(stack));
                }
            }
            else
            {
                list.add(StringHelper.localize("tooltip." + Resources.RESOURCE_PREFIX + "noOwner"));
            }
        }
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        if (!NBTHelper.hasTag(stack, Names.NBT.OWNER))
        {
            setOwner(stack, player);
        }

        if (getOwner(stack).equals(player.getDisplayName()))
        {
            // TODO Some stuff later
        }
        else
        {
            player.addChatComponentMessage(new ChatComponentText(String.format(StringHelper.localize("message." + Resources.RESOURCE_PREFIX + "stolenMatrix"), player.getDisplayName())));

            NBTTagCompound openers = new NBTTagCompound();

            if (NBTHelper.hasTag(stack, Names.NBT.OPENERS))
            {
                openers = stack.stackTagCompound.getCompoundTag(Names.NBT.OPENERS);
            }

            int times = 0;

            if (openers.hasKey(player.getDisplayName()))
            {
                times = openers.getInteger(player.getDisplayName());
            }

            times = times + 1;

            openers.setInteger(player.getDisplayName(), times);
        }

        return stack;
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
