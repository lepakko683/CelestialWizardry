package celestibytes.celestialwizardry.client.gui;

import net.minecraft.entity.player.InventoryPlayer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiSpellBookMain extends GuiSpellBook
{

    public GuiSpellBookMain(InventoryPlayer player)
    {
        super(player, "");
    }

    @Override
    protected boolean isIndex()
    {
        return false;
    }

    @Override
    protected BookState getState()
    {
        return BookState.MAIN;
    }

    @Override
    protected boolean customSecondPage()
    {
        return false;
    }

}
