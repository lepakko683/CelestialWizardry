package celestialwizardry.api.spellgrammar;

import java.util.List;

public class AttributeRune extends RuneCategory implements IAttributeRune {

	private Object attrData;
	
	public AttributeRune(float complexity, Object attrData, boolean takesAttribute) {
		super(complexity, takesAttribute);
		this.attrData = attrData;
	}

	@Override
	public Object getAttributeData() {
		return null;
	}

	@Override
	public String getCategoryIDString() {
		return "attribute";
	}

	@Override
	public List validRuneAttributeTypes() {
		return null;
	}
	
}
