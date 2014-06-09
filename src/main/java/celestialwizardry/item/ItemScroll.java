package celestialwizardry.item;

import celestialwizardry.api.scroll.IScroll;
import celestialwizardry.reference.Names;

public class ItemScroll extends ItemSingle implements IScroll
{
    public ItemScroll()
    {
        super();
        this.setMaxStackSize(1);
        this.setUnlocalizedName(Names.Items.SCROLL);
    }
}
