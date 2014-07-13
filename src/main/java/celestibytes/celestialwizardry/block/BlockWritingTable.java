package celestibytes.celestialwizardry.block;

import celestibytes.celestialwizardry.CelestialWizardry;
import celestibytes.celestialwizardry.client.render.RenderOBJBlock;
import celestibytes.celestialwizardry.item.ItemMagicalInk;
import celestibytes.celestialwizardry.item.ItemPage;
import celestibytes.celestialwizardry.reference.GuiIds;
import celestibytes.celestialwizardry.reference.Names;
import celestibytes.celestialwizardry.tileentity.TileEntityWritingTable;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBook;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockWritingTable extends BlockCW implements ITileEntityProvider
{
//    @SideOnly(Side.CLIENT)
//    private IIcon sideIcon;
//
//    @SideOnly(Side.CLIENT)
//    private IIcon topIcon;
//
//    @SideOnly(Side.CLIENT)
//    private IIcon bottomIcon;

    public BlockWritingTable()
    {
        super(Material.wood);
        this.setHardness(2.0f);
        this.setBlockName(Names.Blocks.WRITING_TABLE);
    }

//    @Override
//    @SideOnly(Side.CLIENT)
//    public void registerBlockIcons(IIconRegister iconRegister)
//    {
//        sideIcon = iconRegister.registerIcon(
//                String.format("%s_%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName()), "side"));
//        topIcon = iconRegister.registerIcon(
//                String.format("%s_%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName()), "top"));
//        bottomIcon = iconRegister.registerIcon(
//                String.format("%s_%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName()), "bottom"));
//    }

//    @Override
//    @SideOnly(Side.CLIENT)
//    public IIcon getIcon(int side, int meta)
//    {
//        if (side == ForgeDirection.DOWN.ordinal())
//        {
//            return bottomIcon;
//        }
//        else if (side == ForgeDirection.UP.ordinal())
//        {
//            return topIcon;
//        }
//        else
//        {
//            return sideIcon;
//        }
//    }

    // TODO: Cleanup!
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7,
                                    float par8, float par9)
    {
        System.out.println("Click!");
        if (player.isSneaking())
        {
            return false;
        }
        else
        {
            if (!world.isRemote)
            {
                TileEntity te = world.getTileEntity(x, y, z);
                if (te instanceof TileEntityWritingTable)
                {
                    ItemStack currentItem = player.getCurrentEquippedItem();
                    if (currentItem == null)
                    {
                        if (((TileEntityWritingTable) te).getStackInSlot(TileEntityWritingTable.MIDDLE_INVENTORY_INDEX)
                                != null)
                        {
                            player.setCurrentItemOrArmor(0, ((TileEntityWritingTable) te)
                                    .getStackInSlot(TileEntityWritingTable.MIDDLE_INVENTORY_INDEX).copy());
                            ((TileEntityWritingTable) te)
                                    .setInventorySlotContents(TileEntityWritingTable.MIDDLE_INVENTORY_INDEX, null);
                            return true;
                        }
                        player.openGui(CelestialWizardry.instance, GuiIds.WRITING_TABLE, world, x, y, z);
                        return true;
                    }
                    if (putItemInMainSlot((TileEntityWritingTable) te, currentItem))
                    {
                        return true;
                    }

                    if (player.getCurrentEquippedItem().getItem() instanceof ItemMagicalInk)
                    {
                        ItemStack slot = ((TileEntityWritingTable) te)
                                .getStackInSlot(TileEntityWritingTable.INK_INVENTORY_INDEX);

                        if (slot == null)
                        {
                            ((TileEntityWritingTable) te)
                                    .setInventorySlotContents(TileEntityWritingTable.INK_INVENTORY_INDEX,
                                                              currentItem.copy());
                            player.destroyCurrentEquippedItem();
                        }
                        else
                        {
                            player.openGui(CelestialWizardry.instance, GuiIds.WRITING_TABLE, world, x, y, z);
                        }
                    }
                }


            }

            return true;
        }
    }

//    @Override
//	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitx, float hity, float hitz,
// int metadata) {
//		System.out.println();
//		return super.onBlockPlaced(world, x, y, z, side, hitx, hity, hitz, metadata);
//	}

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemStack)
    {
        if (!world.isRemote)
        {
            int dir = Math.abs(Math.round(entityLiving.rotationYaw / 90));
            dir = dir == 4 ? 0 : dir;

            switch (dir)
            {
                case 0:
                    world.setBlockMetadataWithNotify(x, y, z, 2, 0);
                    break;
                case 1:
                    world.setBlockMetadataWithNotify(x, y, z, 5, 0);
                    break;
                case 2:
                    world.setBlockMetadataWithNotify(x, y, z, 3, 0);
                    break;
                case 3:
                    world.setBlockMetadataWithNotify(x, y, z, 4, 0);
                    break;
            }

//			System.out.println("Real:" + ForgeDirection.getOrientation(world.getBlockMetadata(x, y, z)).toString());
//			for(int i=0;i<6;i++) {
//				System.out.println("FDIR " + i + " - " + ForgeDirection.getOrientation(i).toString());
//			}
//			System.out.println(ForgeDirection.getOrientation(dir).toString());
        }
        super.onBlockPlacedBy(world, x, y, z, entityLiving, itemStack);
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderType()
    {
        return RenderOBJBlock.ID;
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     *
     * @param world the world to create the tile entity
     * @param meta  the metadata of the block
     */
    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new TileEntityWritingTable();
    }

    private boolean putItemInMainSlot(TileEntityWritingTable te, ItemStack stack)
    {
        System.out.println("setConts");
        if (stack.getItem() instanceof ItemBook || stack.getItem() instanceof ItemPage)
        {
            te.setInventorySlotContents(TileEntityWritingTable.MIDDLE_INVENTORY_INDEX, stack.copy());

            return true;
        }

        return false;
    }
}
