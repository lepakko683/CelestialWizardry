package celestibytes.celestialwizardry.block;

import celestibytes.celestialwizardry.creativetab.CreativeTab;
import celestibytes.celestialwizardry.reference.Resources;
import celestibytes.celestialwizardry.tileentity.TileEntityCW;

import celestibytes.core.block.BlockBase;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.Random;

public abstract class BlockCW extends BlockBase
{
    public BlockCW()
    {
        this(Material.rock);
    }

    public BlockCW(Material material)
    {
        super(material, Resources.RESOURCE_PREFIX.replace(":", ""));
        this.setCreativeTab(CreativeTab.CW_TAB);
    }
}
