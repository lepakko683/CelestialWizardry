package celestialwizardry.client.gui.cwgui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseGui extends GuiContainer
{

    private List<GuiElement> elements = new ArrayList<GuiElement>();

    public BaseGui(Container par1Container)
    {
        super(par1Container);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
    {
        for (GuiElement ge : elements)
        {
            ge.draw(this);
        }
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
    }

}
