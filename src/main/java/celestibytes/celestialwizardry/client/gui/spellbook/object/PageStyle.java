package celestibytes.celestialwizardry.client.gui.spellbook.object;

public class PageStyle {
	
	private final PageStyleType type;
	private final PageObject.PageObjectType[] targets;
	
	public PageStyle(PageStyleType type, PageObject.PageObjectType... targets) {
		this.type = type;
		this.targets = targets;
	}
	
	public PageStyleType getType() {
		return this.type;
	}
	
	public enum PageStyleType {
		COLOR,
		SIZE,
		OTHER;
	}
	
	public class PageStyleColor extends PageStyle {

		public final int mcColorId;
		
		public PageStyleColor(int mcColorId) {
			super(PageStyle.PageStyleType.COLOR, PageObject.PageObjectType.TEXT);
			this.mcColorId = mcColorId;
		}
	}
	
	public class PageStyleSize extends PageStyle {

		public final int width, height;
		
		public PageStyleSize(int w, int h) {
			super(PageStyle.PageStyleType.SIZE, PageObject.PageObjectType.IMAGE, PageObject.PageObjectType.RECIPE);
			this.width = w;
			this.height = h;
		}
	}
}
