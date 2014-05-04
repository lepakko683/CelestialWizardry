package celestialwizardry.item;

import celestialwizardry.api.writing.IWriter;
import celestialwizardry.reference.Names;

public class ItemMagicalPen extends ItemCW implements IWriter
{
    // TODO set to have damage value
    public ItemMagicalPen()
    {
        super();
        this.setUnlocalizedName(Names.Items.MAGICAL_PEN);
    }
}
