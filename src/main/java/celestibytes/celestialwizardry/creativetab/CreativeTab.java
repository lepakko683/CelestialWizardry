package celestibytes.celestialwizardry.creativetab;

import celestibytes.celestialwizardry.init.ModItems;
import celestibytes.celestialwizardry.reference.Reference;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CreativeTab
{
    public static final CreativeTabs CW_TAB = new CreativeTabs(Reference.MOD_ID.toLowerCase())
    {
        @Override
        public Item getTabIconItem()
        {
            return ModItems.magicalInk;
        }
    };
}
