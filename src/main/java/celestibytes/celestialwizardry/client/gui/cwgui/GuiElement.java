package celestibytes.celestialwizardry.client.gui.cwgui;

import org.lwjgl.opengl.GL11;


public abstract class GuiElement
{

    private float zLevel = 0f;
    protected int elementWidth = 80;
    protected int elementHeight = 40;

    public GuiElement()
    {

    }


    protected void drawElement(BaseGui base, int x, int y)
    {

    }

    protected void draw(BaseGui base)
    {
        GL11.glPushMatrix();
        GL11.glTranslatef(0f, 0f, zLevel);
        drawElement(base, 0, 0);
        GL11.glPopMatrix();
    }

}
