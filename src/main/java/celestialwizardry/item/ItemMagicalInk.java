package celestialwizardry.item;

import celestialwizardry.api.writing.IWriter;
import celestialwizardry.reference.Names;

public class ItemMagicalInk extends ItemSingle implements IWriter
{
    // TODO set to have damage value
    public ItemMagicalInk()
    {
        super();
        this.setMaxStackSize(1);
        this.setUnlocalizedName(Names.Items.MAGICAL_INK);
    }
}
