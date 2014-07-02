package celestialwizardry.crystal.init;

import celestialwizardry.crystal.item.ItemCrystal;
import celestialwizardry.crystal.item.ItemMatrix;
import celestialwizardry.crystal.reference.CrystalNames;
import celestialwizardry.init.ModItems;
import celestialwizardry.reference.Names;

import net.minecraft.item.ItemStack;

public class CrystalItems
{
    public static final ItemCrystal crystal = new ItemCrystal();

    public static final ItemStack stoneCrystal = crystal.addItem(0, CrystalNames.Items.CRYSTALS[0]);
    public static final ItemStack mysticalCrystal = crystal.addItem(1, CrystalNames.Items.CRYSTALS[1]);
    public static final ItemStack pureCrystal = crystal.addItem(2, CrystalNames.Items.CRYSTALS[2]);
    public static final ItemStack celestialCrystal = crystal.addItem(3, CrystalNames.Items.CRYSTALS[3]);
    public static final ItemStack perfectCrystal = crystal.addItem(4, CrystalNames.Items.CRYSTALS[4]);

    public static final ItemMatrix matrix = new ItemMatrix();

    public static void init()
    {
        ModItems.register(crystal, CrystalNames.Items.CRYSTAL);
        ModItems.register(matrix, CrystalNames.Items.MATRIX);
    }
}
