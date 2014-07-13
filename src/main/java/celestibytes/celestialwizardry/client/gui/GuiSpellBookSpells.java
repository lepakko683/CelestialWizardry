package celestibytes.celestialwizardry.client.gui;

import net.minecraft.entity.player.InventoryPlayer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiSpellBookSpells extends GuiSpellBook
{
    public GuiSpellBookSpells(InventoryPlayer player)
    {
        super(player, "Spells");
    }

    @Override
    protected boolean isIndex()
    {
        return false;
    }

    @Override
    protected BookState getState()
    {
        return BookState.SPELLS;
    }

    @Override
    protected boolean customSecondPage()
    {
        return false; // TODO
    }
}
