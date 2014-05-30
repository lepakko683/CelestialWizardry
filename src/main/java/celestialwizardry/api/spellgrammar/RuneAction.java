package celestialwizardry.api.spellgrammar;

import java.util.List;

public class RuneAction extends Rune
{
	//SPAWN, TELEPORT, ACCELERATE
	@Override
	public int getRuneType() {
		return 0;
	}

	@Override
	public float getRuneComplexity() {
		return 0;
	}

	@Override
	public List validRuneAttributeTypes() {
		return null;
	}
   
}
