package celestialwizardry.api.spellgrammar;

import java.util.List;

public class RuneLocation extends RuneCategory {

	public RuneLocation(float complexity, boolean takesAttribute) {
		super(complexity, takesAttribute, null);
	}

	@Override
	public String getCategoryIDString() {
		return "location";
	}

	@Override
	public List validRuneAttributeTypes() {
		return null;
	}
	
}
