package celestibytes.celestialwizardry.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import celestibytes.celestialwizardry.CelestialWizardry;
import celestibytes.celestialwizardry.api.IStaff;
import celestibytes.celestialwizardry.reference.GuiIds;
import celestibytes.celestialwizardry.reference.Names;

public class ItemStaff extends ItemSingle implements IStaff
{
    public ItemStaff()
    {
        super();
        this.setMaxStackSize(1);
        this.setUnlocalizedName(Names.Items.STAFF);
    }
    
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
    	if (!player.isSneaking()) {
            player.openGui(CelestialWizardry.instance, GuiIds.SPELL_SWITCHER, player.worldObj, 0, 0, 0);
        }
    	return stack;
    }
}
