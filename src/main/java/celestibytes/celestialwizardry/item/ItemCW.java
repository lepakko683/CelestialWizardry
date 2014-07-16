package celestibytes.celestialwizardry.item;

import celestibytes.celestialwizardry.creativetab.CreativeTab;
import celestibytes.celestialwizardry.reference.Resources;

import celestibytes.core.item.ItemBase;

public abstract class ItemCW extends ItemBase
{
    public ItemCW()
    {
        super(Resources.RESOURCE_PREFIX.replace(":", ""));
        setCreativeTab(CreativeTab.CW_TAB);
    }
}
