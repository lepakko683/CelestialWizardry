package celestialwizardry.client.gui;

import net.minecraft.entity.player.InventoryPlayer;

public class GuiSpellBookMain extends GuiSpellBook {

	public GuiSpellBookMain(InventoryPlayer player) {
		super(player, "");
	}

	@Override
	protected boolean isIndex() {
		return false;
	}

	@Override
	protected BookState getState() {
		return null;
	}

	@Override
	protected boolean customSecondPage() {
		return false;
	}

}
