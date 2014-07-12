package celestialwizardry.client.gui.spellbook;

import java.util.LinkedList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import celestialwizardry.client.gui.GuiButtonCW;
import celestialwizardry.handler.ClientTickEventHandler;
import celestialwizardry.inventory.ContainerSpellBook;
import celestialwizardry.reference.Resources;
import celestialwizardry.registry.SpellBookPages;
import celestialwizardry.util.Colour;
import celestialwizardry.util.LogH;
import celestialwizardry.util.StringHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiLabel;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

@SideOnly(Side.CLIENT)
public class GuiSpellBook extends GuiContainer {
	
	private final int BUTTON_ID_GUIDE = 100;
	private final int BUTTON_ID_NOTES = 101;
	private final int BUTTON_ID_SPELLS = 102;
	private final int BUTTON_ID_BACKWARD = 103;
	private final int BUTTON_ID_FORWARD = 104;
	private final int BUTTON_ID_MEMORIZATION_L = 105;
	private final int BUTTON_ID_REMOVE_PAGE_L = 106;
	private final int BUTTON_ID_MEMORIZATION_R = 107;
	private final int BUTTON_ID_REMOVE_PAGE_R = 108;
	
	public InventoryPlayer playerInv;
	
	private static final Colour COLOR_GUIDES = new Colour(1f, 0f, 0f);
    private static final Colour COLOR_NOTES = new Colour(0f, 1f, 0f);
    private static final Colour COLOR_SPELLS = new Colour(.1f, .1f, 1f);
	
	public static final int LEFT = 0;
	public static final int RIGHT= 1;
	
	private static final int bmarkWidth = 48;
	private static final int bmarkHeight = 19;
	
	
	private static final int arrowX = 198;
	private static final int arrowY = 226;
	private static final int arrowWidth = 16;
	
	protected PageRenderer pageRenderer;
	
	/**The current page opened on the left, -1 if there is no page on the left. This doesn't actually represent pages but PageData - objects, to be exact*/
	protected static int currentPageIndex = -1;
	
	
	/**The pages that are contained in this spellbook*/
	private static LinkedList<PageData> containedPages = new LinkedList<PageData>(); // Hehe, no splitscreen with this code :P
	private static final PageData[] buffer = new PageData[2];
	private int[] contPages;
	
	protected GuiButton guide;
	protected GuiButton notes;
	protected GuiButton spells;
	
	protected GuiButton backward;
	protected GuiButton forward;
	
	protected GuiButton memorization_l;
	protected GuiButton remove_page_l;
	protected GuiButton memorization_r;
	protected GuiButton remove_page_r;
	
	private int essPageObjects;
	private int containedPageCount;

	public GuiSpellBook(EntityPlayer player) {
		super(new ContainerSpellBook(player.inventory));
		this.playerInv = player.inventory;
//		pageRenderer = PageRenderer.getInstance();
		containedPageCount = containedPages.size();
		essPageObjects = SpellBookPages.getEssentialPageObjectCount();
		
		if(SpellBookPages.allPages != null && SpellBookPages.essentialPageObjectv != null) {
			buffer[0] = getPageObject(currentPageIndex);
			buffer[1] = getPageObject(currentPageIndex+1);
		} else {
			LogH.err("Couldn't initialize GuiSpellBook, allPages or essentialPageObjectv was null...");
		}
        xSize = 216;
        ySize = 255;
	}
	
	public PageData getPageObject(int index) {
		if(index < 0) {
			 return null;
		}
		if(index < essPageObjects) {
			return SpellBookPages.essentialPageObjectv[index];
		}
		if(index-essPageObjects < containedPageCount) {
			return containedPages.get(index-essPageObjects);
		}
		return null;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void initGui() { // Called after window resizing
		super.initGui();
		buttonList.clear();
		
		guide = new ButtonBookmark(BUTTON_ID_GUIDE, StringHelper.localize("bookmark." + Resources.RESOURCE_PREFIX + "guide"), BookmarkType.GUIDE, Direction.LEFT);
		buttonList.add(guide);
		
		notes = new ButtonBookmark(BUTTON_ID_NOTES, StringHelper.localize("bookmark." + Resources.RESOURCE_PREFIX + "notes"), BookmarkType.NOTES, Direction.LEFT);
		buttonList.add(notes);
		
		spells = new ButtonBookmark(BUTTON_ID_SPELLS, StringHelper.localize("bookmark." + Resources.RESOURCE_PREFIX + "spells"), BookmarkType.SPELLS, Direction.LEFT);
		buttonList.add(spells);
		
		backward = new ButtonFlipPage(BUTTON_ID_BACKWARD, Direction.LEFT, "").setGuiPos(this.guiLeft, this.guiTop);
		buttonList.add(backward);
		
		forward = new ButtonFlipPage(BUTTON_ID_FORWARD, Direction.RIGHT, "").setGuiPos(this.guiLeft, this.guiTop);
		buttonList.add(forward);
	}
	
	public void addPagesToGui() {}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partTick, int mouseX, int mouseY) {
		Colour.resetGLColor();
		this.mc.getTextureManager().bindTexture(Resources.Textures.GUI_SPELL_BOOK);
		this.drawTexturedModalRect(guiLeft, guiTop + 166, 0, 166, 197, ySize - 165);
		
		this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, 160);
		
		renderaButtons(mouseX, mouseY, false);
		
		Colour.resetGLColor();
		this.mc.getTextureManager().bindTexture(Resources.Textures.GUI_SPELL_BOOK_PAGES);

        renderPage(Direction.LEFT, null);
        renderPage(Direction.RIGHT, null);
        
        // For debug - renders green boxes around all ButtonBookmarks
        renderaButtons(mouseX, mouseY, true);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)  {
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
//		drawCenteredString(fontRendererObj, this.title, 28, 22, 0xFF00FF);
		fontRendererObj.drawString("Spellbook", 16, 22, 0xFF00FF, false);
	}
	
	public void renderPages() {
		if(SpellBookPages.allPages == null || containedPages == null) {
			LogH.err("Array allPages and/or LinkedlistList containedPages is null");
			return;
		}
		if(currentPageIndex < -1 || currentPageIndex-essPageObjects >= containedPageCount) {
			LogH.err("Current page index out of bounds!!!");
			currentPageIndex = -1; // Beginning of the book
			return;
		}
		if(currentPageIndex > -1) {
//			renderPage(Direction.LEFT, SpellBookPages.allPages[containedPages.get(currentPageIndex).intValue()]); // TODO: fix
		}
	}
	
	public void renderPage(Direction side, Page page) {
//		if(page == null) {
//			return; // TODO
//		}
		int xOffset = guiLeft + 11;
		int uOffset = 11;
		if(side == Direction.RIGHT) {
			xOffset += 196/2;
			uOffset += 196/2;
		}
		// Left page
//        this.drawTexturedModalRect(guiLeft+ 11, guiTop + 14, 11, 13, 196/2, 141);
        
        // Right page
//        this.drawTexturedModalRect(guiLeft+ 11 + 196/2, guiTop + 14, 11+196/2, 13, 196/2, 141);
		
		this.drawTexturedModalRect(xOffset, guiTop + 14, uOffset, 13, 196/2, 141);
		
//		page.renderPage(null); // TODO
		
	}
	
	public void renderBookmark(BookmarkType type, Direction side, boolean mousedOver) {
		renderBookmark(mousedOver ? type.color.getCopy().lighten(.2f) : type.color, guiLeft + (side == Direction.LEFT ? -35 : 205), guiTop+22+type.heightIndex*(bmarkHeight+2), side != Direction.LEFT);
	}
	
	private void renderBookmark(Colour color, int x, int y, boolean flip) {
		this.mc.getTextureManager().bindTexture(Resources.Textures.GUI_SPELL_BOOK);
		int xMin = 200;
		int yMin = 190;
		int xMax = xMin + bmarkWidth;
		int yMax = yMin + bmarkHeight;
		
		if(flip) {
			xMin = xMax;
			xMax -= bmarkWidth;
		}
		color.setGLColor();
		Tessellator tes = Tessellator.instance;
		tes.startDrawingQuads();
		tes.addVertexWithUV(x, y+bmarkHeight, this.zLevel, (double)xMin/256d, (double)yMax/256d);
		tes.addVertexWithUV(x+bmarkWidth, y+bmarkHeight, this.zLevel, (double)xMax/256d, (double)yMax/256d);
		tes.addVertexWithUV(x+bmarkWidth, y, this.zLevel, (double)xMax/256d, (double)yMin/256d);
		tes.addVertexWithUV(x, y, this.zLevel, (double)xMin/256d, (double)yMin/256d);
		tes.draw();
	}
	
	private void renderPageFlipper(Colour color, int x, int y, Direction side, final boolean hover) {
		this.mc.getTextureManager().bindTexture(Resources.Textures.GUI_SPELL_BOOK);
		int xMin = arrowX;
		int yMin = arrowY;
		int xMax = xMin + 8;
		int yMax = yMin + 8;
		
		if(side == Direction.RIGHT) {
			xMin = xMax;
			xMax -= 8;
		}
		if(!hover) {
			GL11.glEnable(GL11.GL_BLEND);
			color.setGLColor();
			GL11.glBlendFunc(GL11.GL_SRC_COLOR, GL11.GL_DST_COLOR);
		} else {
			Colour.resetGLColor();
		}
		Tessellator tes = Tessellator.instance;
		tes.startDrawingQuads();
		tes.addVertexWithUV(x, y+arrowWidth, this.zLevel, (double)xMin/256d, (double)yMax/256d);
		tes.addVertexWithUV(x+arrowWidth, y+arrowWidth, this.zLevel, (double)xMax/256d, (double)yMax/256d);
		tes.addVertexWithUV(x+arrowWidth, y, this.zLevel, (double)xMax/256d, (double)yMin/256d);
		tes.addVertexWithUV(x, y, this.zLevel, (double)xMin/256d, (double)yMin/256d);
		tes.draw();
		if(!hover) {
			GL11.glDisable(GL11.GL_BLEND);
		}
	}
	
	protected void updatePageBuffer() {
		buffer[0]=getPageObject(currentPageIndex);
		buffer[1]=getPageObject(currentPageIndex+1);
	}
	
	protected void goToPage(int index) {
		if(index >= -1 && index-essPageObjects < containedPageCount) {
			currentPageIndex = index;
			updatePageBuffer();
		}
	}
	
	protected void flipPage(int dir) {
		switch(dir) {
		case LEFT:
			currentPageIndex = currentPageIndex > -1 ? currentPageIndex-- : currentPageIndex;
			ClientTickEventHandler.notifyPageChange(true);
			break;
		case RIGHT:
			// TODO: Make sure to test!!!
			currentPageIndex = (currentPageIndex >= essPageObjects && currentPageIndex+1-essPageObjects < containedPageCount ? currentPageIndex+1 : currentPageIndex);
			ClientTickEventHandler.notifyPageChange(false);
		}
	}
	
	private void renderaButtons(int mouseX, int mouseY, boolean outlines) {

        for (int i=0; i < this.buttonList.size(); i++) {
    		if(this.buttonList.get(i) instanceof GuiButtonCW && !(this.buttonList.get(i) instanceof ButtonFlipPage)) {
    			if(!outlines) {
        			((GuiButtonCW)this.buttonList.get(i)).drawButton_b(this.mc, mouseX, mouseY);
            	} else {
            		((GuiButtonCW)this.buttonList.get(i)).renderOutlines();
            	}
    		}
        }
	}
	
	@Override
	protected void actionPerformed(GuiButton button) {
		
		super.actionPerformed(button);
	}
	
    @Override
    public boolean doesGuiPauseGame()
    {
        return false;
    }
    
    public static enum Direction {
    	LEFT,
    	RIGHT,
    	BOTH;
    }
    
    public static enum BookmarkType {
    	GUIDE(COLOR_GUIDES, 0),
    	NOTES(COLOR_NOTES, 1),
    	SPELLS(COLOR_SPELLS, 2);
    	
    	public final Colour color;
    	public final int heightIndex;
    	
    	private BookmarkType(Colour color, int heightIndex) {
    		this.color = color;
    		this.heightIndex = heightIndex;
    	}
    }
    
    public class ButtonBookmark extends GuiButtonCW {
    	
    	private final boolean DEBUG = false;
    	private final BookmarkType type;
    	private Direction side;

		public ButtonBookmark(int id, String str, BookmarkType type, Direction side) {
			super(id, guiLeft + (side == Direction.LEFT ? -35 : 205), guiTop+22+type.heightIndex*(bmarkHeight+2), bmarkWidth, bmarkHeight, str);
			this.type = type;
			this.side = side;
		}
		
		@Override
		public void drawButton(Minecraft mc, int mouseX, int mouseY) {} // don't render anything
		
		public void drawButton_b(Minecraft mc, int mouseX, int mouseY) {
			field_146123_n = mouseX >= xPosition && mouseY >= yPosition && mouseX < xPosition + width && mouseY < yPosition + height;
	        int hoverState = getHoverState(field_146123_n);
			renderBookmark(type, side, hoverState==2);
			fontRendererObj.drawString(this.displayString, this.xPosition+8, this.yPosition+7, 0xFF00FF, false);
		}
		
		public void renderOutlines() {
			if(!this.DEBUG) {
				return;
			}
			this.drawHorizontalLine(this.xPosition, this.xPosition+this.width, this.yPosition, Colour.GREEN.getPackedARGB());
			
			this.drawHorizontalLine(this.xPosition, this.xPosition+this.width, this.yPosition+this.height, Colour.GREEN.getPackedARGB());
			
			this.drawVerticalLine(this.xPosition, this.yPosition, this.yPosition+this.height, Colour.GREEN.getPackedARGB());
			
			this.drawVerticalLine(this.xPosition+this.width, this.yPosition, this.yPosition+this.height, Colour.GREEN.getPackedARGB());
		}
    	
    }
    
    public class ButtonFlipPage extends GuiButtonCW {
    	
    	private Direction side;
    	
		public ButtonFlipPage(int id, Direction side, String text) {
			super(id, side == Direction.LEFT ? 13 : 189, 134, arrowWidth, arrowWidth, text);
			this.side = side;
		}
		
		@Override
		public void drawButton(Minecraft mc, int mouseX, int mouseY) {
			drawButton_b(mc, mouseX, mouseY);
		}
		
		@Override
		public void drawButton_b(Minecraft mc, int mouseX, int mouseY) {
			field_146123_n = mouseX >= guiLeft_i+xPosition && mouseY >= guiTop_i+yPosition && mouseX < guiLeft_i+xPosition + width && mouseY < guiTop_i+yPosition + height;
	        int hoverState = getHoverState(field_146123_n);
			renderPageFlipper(COLOR_GUIDES.getCopy().darken(.3f), guiLeft_i+this.xPosition, guiTop_i+this.yPosition, side, hoverState == 2);
			super.drawButton_b(mc, mouseX, mouseY);
		}
    }
    
    public class ButtonExtra extends GuiButtonCW {

		public ButtonExtra(int id, int x, int y, int width, int height, String text) {
			super(id, x, y, width, height, text);
		}
    	
		@Override
		public void drawButton(Minecraft mc, int mouseX, int mouseY) {}
		
		@Override
		public void drawButton_b(Minecraft mc, int mouseX, int mouseY) {
			super.drawButton_b(mc, mouseX, mouseY);
		}
    }

    	
}
