package celestialwizardry.block;

import celestialwizardry.api.IStaff;
import celestialwizardry.api.crystal.ICrystal;
import celestialwizardry.client.render.RenderOBJBlock;
import celestialwizardry.client.render.crystal.RenderCrystal;
import celestialwizardry.client.render.crystal.RenderCrystalComplex;
import celestialwizardry.client.render.crystal.RenderCrystalSimple;
import celestialwizardry.reference.Names;
import celestialwizardry.tileentity.TileEntityCrystal;
import celestialwizardry.util.NBTHelper;
import celestialwizardry.util.StringHelper;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

public abstract class BlockCrystal extends BlockCW implements ITileEntityProvider
{
    public static final List<BlockCrystal> crystalList = new ArrayList<BlockCrystal>();

    protected final ResourceLocation texture;
    protected final RenderStyle renderStyle;
    protected final TileEntityCrystal tileCrystal;

    public BlockCrystal(ResourceLocation texture, RenderStyle renderStyle, TileEntityCrystal tileCrystal)
    {
        super(Material.glass);
        this.setHardness(6.0F);

        this.texture = texture;
        this.renderStyle = renderStyle;
        this.tileCrystal = tileCrystal;

        crystalList.add(this);
    }

    public BlockCrystal(String texture, RenderStyle renderStyle, TileEntityCrystal tileCrystal)
    {
        this(new ResourceLocation(texture), renderStyle, tileCrystal);
    }

    /* ======================================== Block START ===================================== */

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7,
                                    float par8, float par9)
    {
        if (player.getCurrentEquippedItem().getItem() instanceof IStaff)
        {
            if (NBTHelper.getBoolean(player.getCurrentEquippedItem(), Names.NBT.BOUNDING))
            {
                NBTHelper.setBoolean(player.getCurrentEquippedItem(), Names.NBT.BOUNDING, false);
                int oldX = NBTHelper.getInt(player.getCurrentEquippedItem(), Names.NBT.BOUND_IN_X);
                int oldY = NBTHelper.getInt(player.getCurrentEquippedItem(), Names.NBT.BOUND_IN_Y);
                int oldZ = NBTHelper.getInt(player.getCurrentEquippedItem(), Names.NBT.BOUND_IN_Z);

                if (world.getTileEntity(oldX, oldY, oldZ) instanceof ICrystal)
                {
                    ICrystal oldCrystal = (ICrystal) world.getTileEntity(oldX, oldY, oldZ);

                    if (world.getTileEntity(x, y, z) instanceof ICrystal)
                    {
                        ICrystal crystal = (ICrystal) world.getTileEntity(x, y, z);

                        if (crystal.setInputBound(oldX, oldY, oldZ) && oldCrystal.setOutputBound(x, y, z))
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

                    NBTHelper.setInteger(player.getCurrentEquippedItem(), Names.NBT.BOUND_IN_X, x);
                    NBTHelper.setInteger(player.getCurrentEquippedItem(), Names.NBT.BOUND_IN_Y, y);
                    NBTHelper.setInteger(player.getCurrentEquippedItem(), Names.NBT.BOUND_IN_Z, z);

                    return true;
                }
            }
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

    /* ======================================== ITileEntityProvider START ===================================== */

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     *
     * @param world
     * @param var2
     */
    @Override
    public TileEntity createNewTileEntity(World world, int var2)
    {
        return tileCrystal;
    }

    /* ======================================== ITileEntityProvider END ===================================== */

    public ResourceLocation getTexture()
    {
        return texture;
    }


    public RenderStyle getRenderStyle()
    {
        return renderStyle;
    }

    public TileEntityCrystal getTileCrystal()
    {
        return tileCrystal;
    }

    @SideOnly(Side.CLIENT)
    public RenderCrystal getRender()
    {
        if (renderStyle == RenderStyle.SIMPLE)
        {
            return new RenderCrystalSimple(this);
        }
        else if (renderStyle == RenderStyle.COMPLEX)
        {
            return new RenderCrystalComplex(this);
        }

        return null;
    }

    public static enum RenderStyle
    {
        SIMPLE,
        COMPLEX
    }
}
