package celestialwizardry.api.spellgrammar;

import java.util.List;


public abstract class Rune
{
	public class RuneConsts
	{
		public static final String RUNE_ACTION_TELEPORT = "teleport";
		public static final String RUNE_ACTION_ACCELERATE = "accelerate";
		public static final String RUNE_ACTION_SPAWN = "spawn";
	}
	
	private String runeid = null;
	/**The id of the mod that owns(/has added) this rune*/
	private String modId = null;
	private boolean requiresPostfix;
	private boolean requiresAttribute; 
	
	private int numbericID = -1;
	
    public Rune(float complexity, boolean takesAttribute)
    {
    }
	
    public abstract List validRuneAttributeTypes();

	public String getRuneID()
	{
		return runeid;
	}
	
	public Rune setRequiresPostfix() {
		this.requiresPostfix = true;
		return this;
	}
	
	public Rune setRequiresAttribute() {
		this.requiresAttribute = true;
		return this;
	}
	
    /**Returns -1 if rune config haven't been loaded yet*/
    public int getRuneNumbericID()
    {
    	return numbericID;
    }
    
    /**
     * id must be the full "extend path" in lower case Example: "Teleport Rune" would be "rune.action.teleport".
     */
    public void setRuneID(String id)
    {
        System.out.println(this.getClass().toString());
        if (this.runeid == null)
        {
            this.runeid = id.toLowerCase();
        }
    }
    
	public boolean subRuneOf(Rune r)
	{
		return false; //TODO 
	}
	
	public static String getFullIdOf(Rune r) {
		if(r instanceof RuneCategory) {
			return "rune." + ((RuneCategory)r).getCategoryIDString() + (r.getRuneID() != null && r.getRuneID().length()>0 ? "." + r.getRuneID() : "");
		}
		if(r.getRuneID() != null && r.getRuneID().length()>0) {
			return "rune." + r.getRuneID();
		}
		return null;
	}
}
