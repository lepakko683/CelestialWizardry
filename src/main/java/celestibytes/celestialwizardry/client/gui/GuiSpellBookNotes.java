package celestibytes.celestialwizardry.client.gui;

import net.minecraft.entity.player.InventoryPlayer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiSpellBookNotes extends GuiSpellBook
{
    public GuiSpellBookNotes(InventoryPlayer player)
    {
        super(player, "Notes");
    }

    @Override
    protected boolean isIndex()
    {
        return false;
    }

    @Override
    protected BookState getState()
    {
        return BookState.NOTES;
    }

    @Override
    protected boolean customSecondPage()
    {
        return false; // TODO
    }
}
