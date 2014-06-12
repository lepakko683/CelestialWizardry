package celestialwizardry.block;

import celestialwizardry.api.IStaff;
import celestialwizardry.api.crystal.ICrystal;
import celestialwizardry.api.crystal.ITileCrystal;
import celestialwizardry.reference.Names;
import celestialwizardry.util.NBTHelper;
import celestialwizardry.util.StringHelper;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public abstract class BlockCrystal extends BlockCW implements ITileEntityProvider, ICrystal
{
    public BlockCrystal()
    {
        super(Material.glass);
        this.setHardness(6.0F);
    }

    /* ======================================== Block START ===================================== */

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        if (player.getCurrentEquippedItem().getItem() instanceof IStaff)
        {
            if (NBTHelper.getBoolean(player.getCurrentEquippedItem(), Names.NBT.BOUNDING))
            {
                NBTHelper.setBoolean(player.getCurrentEquippedItem(), Names.NBT.BOUNDING, false);
                int oldX = NBTHelper.getInt(player.getCurrentEquippedItem(), Names.NBT.BOUND_IN_X);
                int oldY = NBTHelper.getInt(player.getCurrentEquippedItem(), Names.NBT.BOUND_IN_Y);
                int oldZ = NBTHelper.getInt(player.getCurrentEquippedItem(), Names.NBT.BOUND_IN_Z);

                if (world.getTileEntity(oldX, oldY, oldZ) instanceof ITileCrystal)
                {
                    ITileCrystal oldCrystal = (ITileCrystal) world.getTileEntity(oldX, oldY, oldZ);

                    if (world.getTileEntity(x, y, z) instanceof ITileCrystal)
                    {
                        ITileCrystal crystal = (ITileCrystal) world.getTileEntity(x, y, z);

                        if (crystal.setInputBound(oldX, oldY, oldZ) && oldCrystal.setOutputBound(x, y, z))
                        {
                            player.addChatComponentMessage(new ChatComponentText(StringHelper.getMessage("crystalsBound")));
                            return true;
                        }
                    }
                }

                // TODO Add case specific messages
                player.addChatComponentMessage(new ChatComponentText(StringHelper.getMessage("crystalsBoundFail")));
            }
            else
            {
                if (world.getTileEntity(x, y, z) instanceof ITileCrystal)
                {
                    NBTHelper.setBoolean(player.getCurrentEquippedItem(), Names.NBT.BOUNDING, true);

                    NBTHelper.setInteger(player.getCurrentEquippedItem(), Names.NBT.BOUND_IN_X, x);
                    NBTHelper.setInteger(player.getCurrentEquippedItem(), Names.NBT.BOUND_IN_Y, y);
                    NBTHelper.setInteger(player.getCurrentEquippedItem(), Names.NBT.BOUND_IN_Z, z);

                    return true;
                }
            }
        }

        return super.onBlockActivated(world, x, y, z, player, par6, par7, par8, par9);
    }

    /* ======================================== Block END ===================================== */

    /* ======================================== ICrystal START ===================================== */

    /**
     * The {@link Block} instance that implements {@link ICrystal}.
     *
     * @return the {@link Block}
     */
    @Override
    public Block getBlock()
    {
        return this;
    }

    /* ======================================== ICrystal END ===================================== */
}
