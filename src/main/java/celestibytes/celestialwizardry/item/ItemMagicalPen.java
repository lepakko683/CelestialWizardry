package celestibytes.celestialwizardry.item;

import celestibytes.celestialwizardry.api.writing.IWriter;
import celestibytes.celestialwizardry.reference.Names;

public class ItemMagicalPen extends ItemSingle implements IWriter
{
    // TODO set to have damage value
    public ItemMagicalPen()
    {
        super();
        this.setMaxStackSize(1);
        this.setUnlocalizedName(Names.Items.MAGICAL_PEN);
    }
}
