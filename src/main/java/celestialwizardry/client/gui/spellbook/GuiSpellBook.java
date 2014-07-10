package celestialwizardry.client.gui.spellbook;

import java.util.List;

import celestialwizardry.handler.ClientTickEventHandler;
import celestialwizardry.inventory.ContainerSpellBook;
import celestialwizardry.reference.Resources;
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
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

@SideOnly(Side.CLIENT)
public class GuiSpellBook extends GuiContainer {
	
	public InventoryPlayer playerInv;
	
	private static final Colour COLOR_GUIDES = new Colour(1f, 0f, 0f);
    private static final Colour COLOR_NOTES = new Colour(0f, 1f, 0f);
    private static final Colour COLOR_SPELLS = new Colour(.1f, .1f, 1f);
	
	public static final int LEFT = 0;
	public static final int RIGHT= 1;
	
	private final int bmarkWidth = 48;
	private final int bmarkHeight = 19;
	
	protected PageRenderer pageRenderer;
	
	/**The current page opened on the left, -1 if there is no page on the left*/
	protected int currentPageIndex = -1;
	
	private static Page[] allPages;
	
	/**The pages that are contained in this spellbook*/
	private List<Integer> containedPages;
	
	protected GuiButton guide;
	protected GuiButton notes;
	protected GuiButton spells;
	

	public GuiSpellBook(InventoryPlayer player) {
		super(new ContainerSpellBook(player));
		this.playerInv = player;
//		pageRenderer = PageRenderer.getInstance();
		
        xSize = 216;
        ySize = 255;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void initGui() {
		super.initGui();
		buttonList.clear();
		
		guide = new ButtonBookmark(100, StringHelper.localize("bookmark." + Resources.RESOURCE_PREFIX + "guide"), BookmarkType.GUIDE, Direction.LEFT);
		buttonList.add(guide);
		
		notes = new ButtonBookmark(100, StringHelper.localize("bookmark." + Resources.RESOURCE_PREFIX + "notes"), BookmarkType.NOTES, Direction.LEFT);
		buttonList.add(notes);
		
		spells = new ButtonBookmark(100, StringHelper.localize("bookmark." + Resources.RESOURCE_PREFIX + "spells"), BookmarkType.SPELLS, Direction.LEFT);
		buttonList.add(spells);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partTick, int mouseX, int mouseY) {
		Colour.resetGLColor();
		this.mc.getTextureManager().bindTexture(Resources.Textures.GUI_SPELL_BOOK);
		this.drawTexturedModalRect(guiLeft, guiTop + 166, 0, 166, 197, ySize - 165);
		
		this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, 160);
		
//		renderBookmark(BookmarkType.GUIDE, Direction.LEFT, false);
//		renderBookmark(BookmarkType.NOTES, Direction.LEFT, false);
//		renderBookmark(BookmarkType.SPELLS, Direction.LEFT, false);
//		
//		renderBookmark(BookmarkType.GUIDE, Direction.RIGHT, false);
//		renderBookmark(BookmarkType.NOTES, Direction.RIGHT, false);
//		renderBookmark(BookmarkType.SPELLS, Direction.RIGHT, false);
		
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
		if(allPages == null || containedPages == null) {
			LogH.err("Array allPages and/or ArrayList containedPages is null");
			return;
		}
		if(currentPageIndex < -1) {
			LogH.err("Current page index out of bounds!!!");
			return;
		}
		if(currentPageIndex > -1) {
			renderPage(Direction.LEFT, allPages[containedPages.get(currentPageIndex).intValue()]);
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
	
	protected void goToPage(int index) {
		
	}
	
	protected void flipPage(int dir) {
		switch(dir) {
		case LEFT:
			ClientTickEventHandler.notifyPageChange(true);
			break;
		case RIGHT:
			ClientTickEventHandler.notifyPageChange(false);
		}
	}
	
	private void renderaButtons(int mouseX, int mouseY, boolean outlines) {

        for (int i=0; i < this.buttonList.size(); i++) {
    		if(this.buttonList.get(i) instanceof ButtonBookmark) {
    			if(!outlines) {
        			((ButtonBookmark)this.buttonList.get(i)).drawButton_b(this.mc, mouseX, mouseY);
            	} else {
            		((ButtonBookmark)this.buttonList.get(i)).renderOutlines();
            	}
    		}
        }
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
    
    public class ButtonBookmark extends GuiButton {
    	
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
    				

}
