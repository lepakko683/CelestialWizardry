package celestialwizardry.crystal.block;

import celestialwizardry.api.IStaff;
import celestialwizardry.api.crystal.ICrystal;
import celestialwizardry.block.BlockCW;
import celestialwizardry.client.render.RenderOBJBlock;
import celestialwizardry.reference.Names;
import celestialwizardry.util.NBTHelper;
import celestialwizardry.util.StringHelper;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public abstract class BlockCrystal extends BlockCW implements ITileEntityProvider
{
    public static final List<BlockCrystal> crystalList = new ArrayList<BlockCrystal>();

    public BlockCrystal()
    {
        super(Material.glass);
        this.setHardness(6.0F);

        crystalList.add(this);
    }

    /* ======================================== Block START ===================================== */

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7,
                                    float par8, float par9)
    {
        if (player.getCurrentEquippedItem() != null)
        {
            if (player.getCurrentEquippedItem().getItem() instanceof IStaff)
            {
                if (NBTHelper.getBoolean(player.getCurrentEquippedItem(), Names.NBT.BOUNDING))
                {
                    NBTHelper.setBoolean(player.getCurrentEquippedItem(), Names.NBT.BOUNDING, false);
                    int cX = NBTHelper.getInt(player.getCurrentEquippedItem(), Names.NBT.BOUND_X);
                    int cY = NBTHelper.getInt(player.getCurrentEquippedItem(), Names.NBT.BOUND_Y);
                    int cZ = NBTHelper.getInt(player.getCurrentEquippedItem(), Names.NBT.BOUND_Z);

                    if (world.getTileEntity(cX, cY, cZ) instanceof ICrystal)
                    {
                        ICrystal crystal = (ICrystal) world.getTileEntity(cX, cY, cZ);

                        if (world.getTileEntity(x, y, z) instanceof ICrystal)
                        {
                            if (crystal.setBound(x, y, z))
                            {
                                player.addChatComponentMessage(
                                        new ChatComponentText(StringHelper.getMessage("crystalsBound")));
                                return true;
                            }
                        }
                    }

                    // TODO Add case specific messages
                    player.addChatComponentMessage(new ChatComponentText(StringHelper.getMessage("crystalsBoundFail")));
                }
                else
                {
                    if (world.getTileEntity(x, y, z) instanceof ICrystal)
                    {
                        NBTHelper.setBoolean(player.getCurrentEquippedItem(), Names.NBT.BOUNDING, true);

                        NBTHelper.setInteger(player.getCurrentEquippedItem(), Names.NBT.BOUND_X, x);
                        NBTHelper.setInteger(player.getCurrentEquippedItem(), Names.NBT.BOUND_Y, y);
                        NBTHelper.setInteger(player.getCurrentEquippedItem(), Names.NBT.BOUND_Z, z);

                        return true;
                    }
                }
            }
        }
        else
        {
            // NO-OP
        }

        return super.onBlockActivated(world, x, y, z, player, par6, par7, par8, par9);
    }

    @Override
    public boolean isBlockSolid(IBlockAccess world, int x, int y, int z, int side)
    {
        return false;
    }

    @Override
    public int getRenderType()
    {
        return RenderOBJBlock.ID;
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

    /* ======================================== Block END ===================================== */
}
