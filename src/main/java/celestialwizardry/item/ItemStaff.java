package celestialwizardry.item;

import celestialwizardry.api.IStaff;
import celestialwizardry.reference.Names;

public class ItemStaff extends ItemSingle implements IStaff
{
    public ItemStaff()
    {
        super();
        this.setMaxStackSize(1);
        this.setUnlocalizedName(Names.Items.STAFF);
    }
}
