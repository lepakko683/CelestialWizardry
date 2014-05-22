package celestialwizardry.spellgrammar;

import java.util.List;

public interface IRune {
	public int getRuneType(); //TODO: return type likely not final
	
	public float getRuneComplexity();
	
	public List validRuneAttributeTypes();
}
