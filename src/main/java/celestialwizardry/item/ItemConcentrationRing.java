package celestialwizardry.item;

import celestialwizardry.reference.Names;
import celestialwizardry.reference.Resources;
import celestialwizardry.util.StringHelper;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import baubles.common.container.InventoryBaubles;
import baubles.common.lib.PlayerHandler;

import java.util.List;

public class ItemConcentrationRing extends ItemSingle implements IBauble
{
    private IIcon itemIcons[];

    public ItemConcentrationRing()
    {
        super();
        this.setMaxStackSize(1);
        this.setUnlocalizedName(Names.Items.CONCENTRATION_RING);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int meta)
    {
        int j = MathHelper.clamp_int(meta, 0, Names.Items.CONCENTRATION_RING_SUBTYPES.length - 1);
        return this.itemIcons[j];
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("item.%s%s", Resources.RESOURCE_PREFIX, Names.Items.CONCENTRATION_RING);
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister)
    {
        this.itemIcons = new IIcon[Names.Items.CONCENTRATION_RING_SUBTYPES.length];

        for (int i = 0; i < itemIcons.length; ++i)
        {
            this.itemIcons[i] = par1IconRegister.registerIcon(
                    Resources.RESOURCE_PREFIX + Names.Items.CONCENTRATION_RING + "."
                            + Names.Items.CONCENTRATION_RING_SUBTYPES[i]);
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return String.format("item.%s%s.%s", Resources.RESOURCE_PREFIX, Names.Items.CONCENTRATION_RING,
                             Names.Items.CONCENTRATION_RING_SUBTYPES[MathHelper.clamp_int(
                                     itemStack.getItemDamage(), 0,
                                     Names.Items.CONCENTRATION_RING_SUBTYPES.length - 1)]
                            );
    }

    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("unchecked")
    public void getSubItems(Item item, CreativeTabs tabs, List list)
    {
        for (int meta = 0; meta < Names.Items.CONCENTRATION_RING_SUBTYPES.length; meta++) // Maybe ++meta
        {
            list.add(new ItemStack(this, 1, meta));
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean adv)
    {
        super.addInformation(stack, player, list, adv);

        int meta = stack.getItemDamage();

        // if meta == 0
        if (meta == 1)
        {
            list.add(StringHelper.BLUE + StringHelper.getTooltip("lunar") + StringHelper.END);
        }
        else if (meta == 2)
        {
            list.add(StringHelper.YELLOW + StringHelper.getTooltip("solar") + StringHelper.END);
        }
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        if (!world.isRemote)
        {
            InventoryBaubles baubles = PlayerHandler.getPlayerBaubles(player);

            for (int i = 0; i < baubles.getSizeInventory(); i++)
            {
                if (baubles.getStackInSlot(i) == null && baubles.isItemValidForSlot(i, stack))
                {
                    baubles.setInventorySlotContents(i, stack.copy());

                    if (!player.capabilities.isCreativeMode)
                    {
                        player.inventory
                                .setInventorySlotContents(player.inventory.currentItem, null);
                    }

                    onEquipped(stack, player);
                    break;
                }
            }
        }

        return stack;
    }

    /**
     * This method return the type of bauble this is. Type is used to determine the slots it can go into.
     *
     * @param itemstack
     */
    @Override
    public BaubleType getBaubleType(ItemStack itemstack)
    {
        return BaubleType.RING;
    }

    /**
     * This method is called once per tick if the bauble is being worn by a player
     *
     * @param itemstack
     * @param player
     */
    @Override
    public void onWornTick(ItemStack itemstack, EntityLivingBase player)
    {

    }

    /**
     * This method is called when the bauble is equipped by a player
     *
     * @param itemstack
     * @param player
     */
    @Override
    public void onEquipped(ItemStack itemstack, EntityLivingBase player)
    {

    }

    /**
     * This method is called when the bauble is unequipped by a player
     *
     * @param itemstack
     * @param player
     */
    @Override
    public void onUnequipped(ItemStack itemstack, EntityLivingBase player)
    {
        player.attackEntityFrom(DamageSource.magic, 1.1f);
    }

    /**
     * can this bauble be placed in a bauble slot
     *
     * @param itemstack
     * @param player
     */
    @Override
    public boolean canEquip(ItemStack itemstack, EntityLivingBase player)
    {
        return true;
    }

    /**
     * Can this bauble be removed from a bauble slot
     *
     * @param itemstack
     * @param player
     */
    @Override
    public boolean canUnequip(ItemStack itemstack, EntityLivingBase player)
    {
        return true;
    }
}
