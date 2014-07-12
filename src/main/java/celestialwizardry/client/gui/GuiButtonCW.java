package celestialwizardry.client.gui;

import celestialwizardry.util.Colour;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class GuiButtonCW extends GuiButton {
	
	private boolean DEBUG = false;
	
	protected int guiTop_i = 0;
	protected int guiLeft_i = 0;

	public GuiButtonCW(int id, int x, int y, int width, int height, String text) {
		super(id, x, y, width, height, text);
	}
	
	public GuiButtonCW enableDebug() {
		this.DEBUG = true;
		return this;
	}
	
	public GuiButtonCW setGuiPos(int left, int top) {
		this.guiLeft_i = left;
		this.guiTop_i = top;
		return this;
	}
	
	public void setGuiPosVoid(int left, int top) {
		this.guiLeft_i = left;
		this.guiTop_i = top;
	}
	
	public void enableDebugVoid() {
		this.DEBUG = true;
	}
	
	public void drawButton_b(Minecraft mc, int mouseX, int mouseY) {
//		super.drawButton(mc, mouseX, mouseY);
		if(this.DEBUG) {
			renderOutlines();
		}
	}

	public void renderOutlines() {
		if(!this.DEBUG) {
			return;
		}
		this.drawHorizontalLine(this.guiLeft_i+this.xPosition, this.guiLeft_i+this.xPosition+this.width, this.guiTop_i+this.yPosition, Colour.GREEN.getPackedARGB());
		
		this.drawHorizontalLine(this.guiLeft_i+this.xPosition, this.guiLeft_i+this.xPosition+this.width, this.guiTop_i+this.yPosition+this.height, Colour.GREEN.getPackedARGB());
		
		this.drawVerticalLine(this.guiLeft_i+this.xPosition, this.guiTop_i+this.yPosition, this.guiTop_i+this.yPosition+this.height, Colour.GREEN.getPackedARGB());
		
		this.drawVerticalLine(this.guiLeft_i+this.xPosition+this.width, this.guiTop_i+this.yPosition, this.guiTop_i+this.yPosition+this.height, Colour.GREEN.getPackedARGB());
	}
}
