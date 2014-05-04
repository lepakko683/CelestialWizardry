package celestialwizardry.item;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import baubles.common.container.InventoryBaubles;
import baubles.common.lib.PlayerHandler;
import celestialwizardry.reference.Names;
import celestialwizardry.reference.Settings;
import celestialwizardry.util.HolidayHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemSeasonRing extends ItemCW implements IBauble
{
    private int count = 0;

    public ItemSeasonRing()
    {
        super();
        this.setUnlocalizedName(Names.Items.SEASON_RING);
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
     * This method return the type of bauble this is.
     * Type is used to determine the slots it can go into.
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
        if (Settings.enableSeasonal && HolidayHelper.isBirthday())
        {
            if (count >= 100)
            {
                player.worldObj.spawnEntityInWorld(
                        new EntityItem(player.worldObj, player.posX, player.posY + 2, player.posZ,
                                       new ItemStack(Items.cake)));
            }

            count++;

            if (count >= 140)
            {
                count = 0;
            }
        }
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
        return !(Settings.enableSeasonal && HolidayHelper.isBirthday());

    }
}
