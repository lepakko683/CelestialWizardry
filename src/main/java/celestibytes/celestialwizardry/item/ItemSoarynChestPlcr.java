package celestibytes.celestialwizardry.item;

import celestibytes.celestialwizardry.reference.Names;
import celestibytes.core.util.MiscHelpers;
import celestibytes.core.util.Triple;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;

public class ItemSoarynChestPlcr extends ItemSingle
{

    public ItemSoarynChestPlcr()
    {
        this.setMaxStackSize(16);
        this.setUnlocalizedName(Names.Items.SOARYN_CHEST_PLCR);
    }

    @Override
    public boolean onItemUse(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int side,
                             float par8, float par9, float par10)
    {
        if (!world.isRemote)
        {
            Triple<Integer, Integer, Integer> placeCrds = MiscHelpers.getAdjacenantBlockCrdsFromSide(x, y, z, side);
            if (Blocks.chest.canPlaceBlockAt(world, placeCrds.getA(), placeCrds.getB(), placeCrds.getC()) && world
                    .isAirBlock(placeCrds.getA(), placeCrds.getB(), placeCrds.getC()))
            {
                world.setBlock(placeCrds.getA(), placeCrds.getB(), placeCrds.getC(), Blocks.chest);
                ItemStack placer = new ItemStack(Blocks.chest);
                placer.setStackDisplayName("Soaryn Chest");
                Blocks.chest
                        .onBlockPlacedBy(world, placeCrds.getA(), placeCrds.getB(), placeCrds.getC(), player, placer);
                TileEntity te = world.getTileEntity(placeCrds.getA(), placeCrds.getB(), placeCrds.getC());
                if (te instanceof TileEntityChest)
                {
                    for (int i = 0; i < (9 * 3); i++)
                    {
                        if (player.inventory.getStackInSlot(i + 9) != null)
                        {
                            ((TileEntityChest) te)
                                    .setInventorySlotContents(i, player.inventory.getStackInSlot(i + 9).copy());
                            if (!player.capabilities.isCreativeMode)
                            {
                                player.inventory.setInventorySlotContents(i + 9, null);
                                // TODO: decrease stack size!!!
                            }
                        }
                    }
                }


                return true;
            }


        }
        return false;
    }

}
