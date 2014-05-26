package celestialwizardry.block;

import celestialwizardry.CelestialWizardry;
import celestialwizardry.client.render.RenderOBJblock;
import celestialwizardry.item.ItemMagicalInk;
import celestialwizardry.reference.GuiIds;
import celestialwizardry.reference.Names;
import celestialwizardry.tileentity.TileEntityWritingTable;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
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

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7,
                                    float par8, float par9)
    {
        if (player.isSneaking())
        {
            return false;
        }
        else
        {
            if (!world.isRemote)
            {
                if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem()
                        .getItem() instanceof ItemMagicalInk)
                {
                    ItemStack ink = player.getCurrentEquippedItem();

                    if (world.getTileEntity(x, y, z) instanceof TileEntityWritingTable)
                    {
                        TileEntityWritingTable table = (TileEntityWritingTable) world.getTileEntity(x, y, z);

                        ItemStack slot = table.getStackInSlot(TileEntityWritingTable.INK_INVENTORY_INDEX);

                        if (slot == null)
                        {
                            table.setInventorySlotContents(TileEntityWritingTable.INK_INVENTORY_INDEX, ink.copy());
                            player.destroyCurrentEquippedItem();
                        }
                        else
                        {
                            player.openGui(CelestialWizardry.instance, GuiIds.WRITING_TABLE, world, x, y, z);
                        }
                    }
                }
                else
                {
                    if (world.getTileEntity(x, y, z) instanceof TileEntityWritingTable)
                    {
                        player.openGui(CelestialWizardry.instance, GuiIds.WRITING_TABLE, world, x, y, z);
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

    @SideOnly(Side.CLIENT)
    @Override
    public int getRenderType()
    {
        return RenderOBJblock.ID;
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
}
