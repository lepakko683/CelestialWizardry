package celestialwizardry.handler;

import celestialwizardry.api.spell.Spell;
import celestialwizardry.util.Tuple;
import net.minecraft.entity.EntityLiving;

public class SpellCastingHandler {
	
	/* When the caster presses the cast button down (or activates the casting of a spell if not player) */
	public boolean onPreCasting(EntityLiving caster, Spell spell) {
		return false;
	}
	
	/* When the caster releases the cast button */
	/**
	 * @param magicEnergy (EnergyType(type) - Double(amount)) 
	 * */
	public boolean onCasting(EntityLiving caster, Spell spell, Tuple magicEnergy) {
		if(!(magicEnergy.getB() instanceof Double)) {
			return false;
		}
		
		if((Double)(magicEnergy.getB()) < spell.getCost()) {
			handleUnusedEnergy(caster, spell, magicEnergy);
			return false;
		}
		
		
		// TODO: handle casting!
		
		
		
		
		
		return true;
	}
	
	/* Do something with this? */
	public boolean onPostCasting(EntityLiving caster, Spell spell) {
		return true;
	}
	
	// TODO: better method name?
	public void handleUnusedEnergy(EntityLiving caster, Spell spell, Tuple magicEnergy) {
		
	}
}
