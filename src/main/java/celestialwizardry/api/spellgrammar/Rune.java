package celestialwizardry.api.spellgrammar;

import java.util.List;


public abstract class Rune
{
	
	private String runeid = null;
	
	private int numbericID = -1;
	
    public Rune()
    {
    }
	
    public abstract int getRuneType(); //TODO: return type likely not final

    public abstract float getRuneComplexity();

    public abstract List validRuneAttributeTypes();
    

	public String getRuneID()
	{
		return runeid;
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
}
