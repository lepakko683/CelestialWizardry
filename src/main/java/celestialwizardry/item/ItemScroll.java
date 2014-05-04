package celestialwizardry.item;

import celestialwizardry.api.scroll.IScroll;
import celestialwizardry.reference.Names;

public class ItemScroll extends ItemCW implements IScroll
{
    public ItemScroll()
    {
        super();
        this.setUnlocalizedName(Names.Items.SCROLL);
    }
}
