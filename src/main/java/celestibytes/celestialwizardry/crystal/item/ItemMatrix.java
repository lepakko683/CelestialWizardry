package celestibytes.celestialwizardry.crystal.item;

import celestibytes.celestialwizardry.api.ILockedItem;
import celestibytes.celestialwizardry.api.energy.EnergyType;
import celestibytes.celestialwizardry.crystal.api.matrix.IMatrix;
import celestibytes.celestialwizardry.crystal.api.matrix.internal.ICWMatrix;
import celestibytes.celestialwizardry.crystal.reference.CrystalNames;
import celestibytes.celestialwizardry.item.ItemSingle;
import celestibytes.celestialwizardry.reference.Names;
import celestibytes.celestialwizardry.reference.Resources;
import celestibytes.celestialwizardry.reference.Settings;
import celestibytes.celestialwizardry.util.CWStringHelper;
import celestibytes.celestialwizardry.util.EnergyHelper;
import celestibytes.core.util.KeyboardHelper;
import celestibytes.core.util.NBTHelper;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.List;

public class ItemMatrix extends ItemSingle implements IMatrix, ICWMatrix, ILockedItem
{
    @SideOnly(Side.CLIENT)
    private IIcon icons[];

    private static final float[] MAX_ENERGIES = new float[]{1000f, 10000f, 100000f, 1000000f, 1000000000f};

    public ItemMatrix()
    {
        super();
        this.setMaxStackSize(1);
        this.setUnlocalizedName(CrystalNames.Items.MATRIX);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
    }

    /* ======================================== IMatrix START ===================================== */

    /**
     * Checks the {@link celestibytes.celestialwizardry.api.energy.EnergyType} stored inside this matrix.
     *
     * @param stack the {@link net.minecraft.item.ItemStack} to check the {@link celestibytes.celestialwizardry.api.energy
     *              .EnergyType} from
     *
     * @return the {@link celestibytes.celestialwizardry.api.energy.EnergyType} stored inside the {@link net.minecraft.item
     * .ItemStack}
     */
    @Override
    public EnergyType getEnergyType(ItemStack stack)
    {
        if (stack.stackTagCompound != null && NBTHelper.hasTag(stack, CrystalNames.NBT.ENERGY))
        {
            String name = stack.stackTagCompound.getString(CrystalNames.NBT.ENERGY);
            // TODO return EnergyRegistry.getEnergyType(name);
        }

        return EnergyType.DEFAULT_ENERGY;
    }

    /**
     * Checks the amount of {@link celestibytes.celestialwizardry.api.energy.EnergyType} stored inside this matrix.
     *
     * @param stack the {@link net.minecraft.item.ItemStack} to check the amount from
     *
     * @return the amount of {@link celestibytes.celestialwizardry.api.energy.EnergyType} stored inside the {@link net.minecraft
     * .item.ItemStack} as {@link Float}
     */
    @Override
    public float getEnergyStored(ItemStack stack)
    {
        if (stack.stackTagCompound != null && NBTHelper.hasTag(stack, CrystalNames.NBT.ENERGY))
        {
            String name = stack.stackTagCompound.getString(CrystalNames.NBT.ENERGY);

            if (NBTHelper.hasTag(stack, name))
            {
                return stack.stackTagCompound.getFloat(name);
            }
        }

        return 0f;
    }

    /**
     * Tries to add given amount to {@link celestibytes.celestialwizardry.api.energy.EnergyType} stored in this matrix.
     *
     * @param stack     the {@link net.minecraft.item.ItemStack} to receive the {@link celestibytes.celestialwizardry.api.energy
     *                  .EnergyType}
     * @param type      the {@link celestibytes.celestialwizardry.api.energy.EnergyType} that is being added
     * @param amount    the amount of {@link celestibytes.celestialwizardry.api.energy.EnergyType} to add
     * @param transform if there is already another {@link celestibytes.celestialwizardry.api.energy.EnergyType} stored, making this
     *                  true allows matrix to try to transform the new energy into existing one
     *
     * @return returns true if the {@link celestibytes.celestialwizardry.api.energy.EnergyType} was successfully stored
     */
    @Override
    public boolean appendEnergy(ItemStack stack, EnergyType type, float amount, boolean transform)
    {
        String name = type.getEnergyName();

        if (stack.stackTagCompound == null)
        {
            stack.setTagCompound(new NBTTagCompound());
        }

        if (!NBTHelper.hasTag(stack, CrystalNames.NBT.ENERGY))
        {
            return setEnergy(stack, amount, type);
        }

        if (getEnergyType(stack).getEnergyName().equals(name))
        {
            return setEnergy(stack, getEnergyStored(stack) + amount);
        }
        else
        {
            EnergyType energyType = getEnergyType(stack);

            if (transform && EnergyHelper.canTransformInto(type, energyType))
            {
                amount = amount * EnergyHelper.getTransformRatio(type, energyType);
                return setEnergy(stack, getEnergyStored(stack) + amount);
            }
        }

        return false;
    }

    /**
     * Tries to add given amount of already existing {@link celestibytes.celestialwizardry.api.energy.EnergyType} to {@link
     * celestibytes.celestialwizardry.api.energy.EnergyType} stored in this matrix.
     *
     * @param stack  the {@link net.minecraft.item.ItemStack} to receive the {@link celestibytes.celestialwizardry.api.energy
     *               .EnergyType}
     * @param amount the amount of {@link celestibytes.celestialwizardry.api.energy.EnergyType} to add
     *
     * @return returns true if the {@link celestibytes.celestialwizardry.api.energy.EnergyType} was successfully stored
     */
    @Override
    public boolean appendEnergy(ItemStack stack, float amount)
    {
        return appendEnergy(stack, getEnergyType(stack), amount, false);
    }

    /**
     * Tries to decrease given amount of {@link celestibytes.celestialwizardry.api.energy.EnergyType} stored in this matrix.
     *
     * @param stack  the {@link net.minecraft.item.ItemStack} to lose the {@link celestibytes.celestialwizardry.api.energy
     *               .EnergyType}
     * @param type   the {@link celestibytes.celestialwizardry.api.energy.EnergyType} that is being decreased
     * @param amount the amount of {@link celestibytes.celestialwizardry.api.energy.EnergyType} to decrease
     *
     * @return returns true if the {@link celestibytes.celestialwizardry.api.energy.EnergyType} was successfully decreased
     */
    @Override
    public boolean decreaseEnergy(ItemStack stack, EnergyType type, float amount)
    {
        return appendEnergy(stack, type, -amount, false);
    }

    /**
     * Tries to decrease given amount of already existing {@link celestibytes.celestialwizardry.api.energy.EnergyType} stored in this
     * matrix.
     *
     * @param stack  the {@link net.minecraft.item.ItemStack} to lose the {@link celestibytes.celestialwizardry.api.energy
     *               .EnergyType}
     * @param amount the amount of {@link celestibytes.celestialwizardry.api.energy.EnergyType} to decrease
     *
     * @return returns true if the {@link celestibytes.celestialwizardry.api.energy.EnergyType} was successfully decreased
     */
    @Override
    public boolean decreaseEnergy(ItemStack stack, float amount)
    {
        return decreaseEnergy(stack, getEnergyType(stack), amount);
    }

    /**
     * Tries to set the matrix full of already existing {@link celestibytes.celestialwizardry.api.energy.EnergyType}
     *
     * @param stack the {@link net.minecraft.item.ItemStack} to be set full
     *
     * @return returns true if the {@link celestibytes.celestialwizardry.api.energy.EnergyType} was successfully filled
     */
    @Override
    public boolean setFull(ItemStack stack)
    {
        return setFull(stack, getEnergyType(stack));
    }

    /**
     * Tries to set the matrix full of given {@link celestibytes.celestialwizardry.api.energy.EnergyType}
     *
     * @param stack the {@link net.minecraft.item.ItemStack} to be set full
     * @param type  the {@link celestibytes.celestialwizardry.api.energy.EnergyType} that the {@link net.minecraft.item.ItemStack} is
     *              being filled with
     *
     * @return returns true if the {@link celestibytes.celestialwizardry.api.energy.EnergyType} was successfully filled
     */
    @Override
    public boolean setFull(ItemStack stack, EnergyType type)
    {
        return setEnergy(stack, getMaxEnergy(stack), type);
    }

    /**
     * Removes all {@link celestibytes.celestialwizardry.api.energy.EnergyType} that is stored in the {@link
     * celestibytes.celestialwizardry.crystal.api.matrix.IMatrix}
     *
     * @param stack the {@link celestibytes.celestialwizardry.crystal.api.matrix.IMatrix} {@link net.minecraft.item.ItemStack}
     *
     * @return returns true if the {@link celestibytes.celestialwizardry.api.energy.EnergyType} was successfully emptied
     */
    @Override
    public boolean setEmpty(ItemStack stack)
    {
        return setEnergy(stack, 0f);
    }

    /**
     * Tries to set the matrix {@link celestibytes.celestialwizardry.api.energy.EnergyType} to certain amount
     *
     * @param stack  the {@link net.minecraft.item.ItemStack} to get {@link celestibytes.celestialwizardry.api.energy.EnergyType}
     *               set
     * @param amount the amount of {@link celestibytes.celestialwizardry.api.energy.EnergyType} to set
     *
     * @return returns true if the {@link celestibytes.celestialwizardry.api.energy.EnergyType} was successfully set
     */
    @Override
    public boolean setEnergy(ItemStack stack, float amount)
    {
        return setEnergy(stack, amount, getEnergyType(stack));
    }

    /**
     * Tries to set the matrix {@link celestibytes.celestialwizardry.api.energy.EnergyType} to certain amount
     *
     * @param stack  the {@link net.minecraft.item.ItemStack} to get {@link celestibytes.celestialwizardry.api.energy.EnergyType}
     *               set
     * @param amount the amount of {@link celestibytes.celestialwizardry.api.energy.EnergyType} to set
     * @param type   the {@link celestibytes.celestialwizardry.api.energy.EnergyType} that the {@link net.minecraft.item.ItemStack}
     *               {@link celestibytes.celestialwizardry.api.energy.EnergyType} is being set to
     *
     * @return returns true if the {@link celestibytes.celestialwizardry.api.energy.EnergyType} was successfully set
     */
    @Override
    public boolean setEnergy(ItemStack stack, float amount, EnergyType type)
    {
        NBTHelper.setString(stack, CrystalNames.NBT.ENERGY, type.getEnergyName());
        NBTHelper.setFloat(stack, CrystalNames.NBT.ENERGY_STORED,
                           celestibytes.core.util.MathHelper.clampZero_float(amount,
                                                                             getMaxEnergy(
                                                                                     stack)));
        return true;
    }

    /**
     * Returns the maximum amount of {@link celestibytes.celestialwizardry.api.energy.EnergyType} that can be stored inside this
     * matrix
     *
     * @param stack the {@link net.minecraft.item.ItemStack} to check the maximum amount of {@link celestibytes.celestialwizardry
     *              .api.energy.EnergyType} from
     *
     * @return returns the maximum amount of {@link celestibytes.celestialwizardry.api.energy.EnergyType} that can be stored inside
     * this matrix
     */
    @Override
    public float getMaxEnergy(ItemStack stack)
    {
        return MAX_ENERGIES[stack.getItemDamage()];
    }

    /* ======================================== IMatrix END   ===================================== */

    /* ======================================== ICWMatrix START   ===================================== */

    @Override
    public int getTier(ItemStack stack)
    {
        return stack.getItemDamage() + 1;
    }

    @Override
    public int getDamageForTier(int tier)
    {
        return getDamageFromTier(tier);
    }

    @Override
    public int getDamageFromTier(int tier)
    {
        return tier - 1;
    }

    /* ======================================== ICWMatrix END   ===================================== */

    /* ======================================== ILockedItem START   ===================================== */

    /**
     * Gives the owner of this item.
     *
     * @param stack the {@link net.minecraft.item.ItemStack} to check the owner from
     *
     * @return the display name of the owner ({@link net.minecraft.entity.player.EntityPlayer})
     */
    @Override
    public String getOwner(ItemStack stack)
    {
        return NBTHelper.getString(stack, Names.NBT.OWNER);
    }

    /**
     * Sets the owner for this item.
     *
     * @param stack  the {@link net.minecraft.item.ItemStack} to set the owner to
     * @param player the {@link net.minecraft.entity.player.EntityPlayer} that is set as owner
     */
    @Override
    public void setOwner(ItemStack stack, EntityPlayer player)
    {
        NBTHelper.setString(stack, Names.NBT.OWNER, player.getDisplayName());
    }

    /**
     * Checks does this item have owner.
     *
     * @param stack the {@link net.minecraft.item.ItemStack} to check the owner from
     *
     * @return true if this item has owner
     */
    @Override
    public boolean hasOwner(ItemStack stack)
    {
        return NBTHelper.hasTag(stack, Names.NBT.OWNER) && !getOwner(stack).equals("");
    }

    /* ======================================== ILockedItem END   ===================================== */

    /* ======================================== Item START   ===================================== */

    @Override
    @SideOnly(Side.CLIENT)
    public String getItemStackDisplayName(ItemStack stack)
    {
        StringBuilder ret = new StringBuilder();

        if (getTier(stack) == 1)
        {
            // NO-OP
        }
        else if (getTier(stack) == 2)
        {
            ret.append(CWStringHelper.YELLOW);
        }
        else if (getTier(stack) == 3)
        {
            ret.append(CWStringHelper.BRIGHT_BLUE);
        }
        else if (getTier(stack) == 4)
        {
            ret.append(CWStringHelper.PINK);
        }

        ret.append(super.getItemStackDisplayName(stack));
        ret.append(CWStringHelper.END);
        ret.append(" (WIP)");

        return ret.toString();
    }

    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean adv)
    {
        list.add(CWStringHelper.RED + "WIP" + CWStringHelper.END); // TODO Remove

        super.addInformation(stack, player, list, adv);

        if (Settings.shiftForDetails && !KeyboardHelper.isShiftKeyDown())
        {
            list.add(CWStringHelper.getShiftText());
        }

        if (!KeyboardHelper.isShiftKeyDown())
        {
            return;
        }

        list.add(CWStringHelper.getTooltip("tier") + " " + getTier(stack));

        if (stack.stackTagCompound == null)
        {
            list.add(CWStringHelper.getTooltip("maxStorage") + ": " + String.valueOf(getMaxEnergy(stack)));
            list.add(CWStringHelper.getTooltip("noOwner"));
        }
        else
        {
            if (getEnergyStored(stack) == 0)
            {
                list.add(CWStringHelper.getTooltip("maxStorage") + ": " + String.valueOf(getMaxEnergy(stack)));
            }
            else
            {
                list.add(CWStringHelper.getTooltip("energyType") + ": " + getEnergyType(stack).getEnergyName());
                list.add(CWStringHelper.getTooltip("energyStored") + ": " + getEnergyStored(stack) + '/' + String
                        .valueOf(getMaxEnergy(stack)));
            }

            if (hasOwner(stack))
            {
                if (getOwner(stack).equalsIgnoreCase("PizzAna"))
                {
                    list.add(CWStringHelper.getTooltip("pizzanaMatrix"));
                    list.add(CWStringHelper.BRIGHT_GREEN + CWStringHelper.ITALIC + CWStringHelper
                            .getTooltip("bestWizards")
                                     + CWStringHelper.END);
                }
                else if (getOwner(stack).equalsIgnoreCase("ForgeDevName"))
                {
                    list.add(CWStringHelper.getTooltip("forgeMatrix"));
                    list.add(CWStringHelper.WHITE + CWStringHelper.ITALIC + CWStringHelper.getTooltip("devWizards")
                                     + CWStringHelper.END);
                }
                else
                {
                    if (getOwner(stack).equals(player.getDisplayName()))
                    {
                        list.add(CWStringHelper.getTooltip("yourMatrix"));
                    }
                    else
                    {
                        list.add(CWStringHelper.getTooltip("owner") + ": " + getOwner(stack));
                    }
                }
            }
            else
            {
                list.add(CWStringHelper.getTooltip("noOwner"));
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
            player.addChatComponentMessage(new ChatComponentText(
                    String.format(CWStringHelper.getMessage("stolenMatrix"), player.getDisplayName())));

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
        return String.format("item.%s%s", Resources.RESOURCE_PREFIX, CrystalNames.Items.MATRIX);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return String
                .format("item.%s%s.%s.%s", Resources.RESOURCE_PREFIX, CrystalNames.Items.MATRIX, "tier",
                        getTier(itemStack));
    }

    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings({"unchecked", "rawtypes"})
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
        return icons[MathHelper.clamp_int(meta, 0, MAX_ENERGIES.length - 1)];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        icons = new IIcon[MAX_ENERGIES.length];

        for (int i = 0; i < MAX_ENERGIES.length; i++)
        {
            icons[i] = iconRegister
                    .registerIcon(Resources.RESOURCE_PREFIX + CrystalNames.Items.MATRIX + "." + "tier." + (i + 1));
        }
    }

    /* ======================================== Item END   ===================================== */
}
