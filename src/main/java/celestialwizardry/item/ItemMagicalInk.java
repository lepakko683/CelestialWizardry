package celestialwizardry.item;

import celestialwizardry.api.writing.IInk;
import celestialwizardry.reference.Names;

public class ItemMagicalInk extends ItemCW implements IInk
{
    public ItemMagicalInk()
    {
        super();
        this.setMaxStackSize(64);
        this.setUnlocalizedName(Names.Items.MAGICAL_INK);
    }
}
