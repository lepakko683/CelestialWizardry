package celestialwizardry.api.spellgrammar;

import java.util.List;

public class RuneVariable extends RuneCategory {

	public RuneVariable(float complexity, boolean takesAttribute, String runeId) {
		super(complexity, takesAttribute, runeId);
	}

	@Override
	public String getCategoryIDString() {
		return "variable";
	}

	@Override
	public List validRuneAttributeTypes() {
		return null;
	}

}
