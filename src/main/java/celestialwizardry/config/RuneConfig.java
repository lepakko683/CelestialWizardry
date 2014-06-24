package celestialwizardry.config;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.minecraft.nbt.NBTTagCompound;

public class RuneConfig {
	
	private Map<String, Integer> data = new HashMap<String, Integer>();
	private int lastFreeIndex = 0;
	
	
	public RuneConfig() {
		
	}
	
	public int getNumId(String strId) {
		if(!isEmpty() && !isStrIdFree(strId)) {
			return data.get(strId);
		}
		return -1;
	}
	
	public Set<String> getRuneNames() {
		if(!isEmpty()) {
			return data.keySet();
		}
		return null;
	}
	
	public boolean isEmpty() {
		return data==null ? true : data.isEmpty();
	}
	
	/**Warning: missing method body!*/
	private String getStrId(int numId) {
		if(!isEmpty() && !isNumIdFree(numId)) {
//			return;
		}
		return null;
	}
	
	private boolean isNumIdFree(int id) {
		return !data.containsValue(id);
	}
	
	private boolean isStrIdFree(String id) {
		return !data.containsKey(id);
	}
	
	/**
	 * @return Next free numeric rune id, -1 on error.
	 * */
	private int getNextFreeNumId() {
		if(data != null && data.size()>0) {
			for(int i=lastFreeIndex+1;i<1024;i++) { // Rune hard limit: 1024
				if(isNumIdFree(i)) {
					lastFreeIndex = i;
					return i;
				}
			}
		}
		return -1;
		
	}
	
	public void setNumId(String key, int nid) {
		if(this.data.containsKey(key) && this.data.get(key) == 0 && nid != 0) {
			this.data.put(key, nid);
		}
	}
	
	/**@return the numeric id for the rune, -1 if it fails*/
	public int setNumIdAutoFor(String key) {
		if(this.data.containsKey(key) && this.data.get(key) == 0) {
			int numId = getNextFreeNumId();
			this.data.put(key,numId);
			return numId;
		}
		return -1;
		
	}
	
	/**
	 * @return the numeric id of the newly added option, -1 if it fails for some reason.
	 * */
	public int addEntryAuto(String strId) {
		int numId = getNextFreeNumId();
		if(addEntry(strId, numId)) {
			return numId;
		}
		return -1;
	}
	
	public NBTTagCompound toNBT() {
		if(!isEmpty()) {
			NBTTagCompound ret = new NBTTagCompound();
			
			Iterator<Entry<String, Integer>> iter = this.data.entrySet().iterator();
			Entry<String, Integer> entr = null;
			while(iter.hasNext()) {
				entr = iter.next();
				ret.setInteger(entr.getKey(), entr.getValue());
			}
			return ret;
		}
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	public static RuneConfig fromNBT(NBTTagCompound nbt) {
		if(nbt != null && !nbt.hasNoTags()) {
			RuneConfig ret = new RuneConfig();
			Iterator iter = nbt.func_150296_c().iterator(); // gets the iterator of the key set.
			Object key = null;
			while(iter.hasNext()) {
				key = iter.next();
				ret.addEntry((String)key, nbt.getInteger((String)key));
			}
			return ret;
		}
		return null;
		
	}
	
	public boolean addEntry(String strId, int numId) {
		if(isNumIdFree(numId) && isStrIdFree(strId)) {
			data.put(strId, numId);
			return true;
		}
		return false;
	}
	
	public String[] getAsStringArray() {
		if(data.size()==0) {
			return null;
		}
		String[] ret = new String[data.size()];
		Iterator<Entry<String, Integer>> iter = this.data.entrySet().iterator();
		Entry<String, Integer> ent = null;
		int i = 0;
		while(iter.hasNext()) {
			ent = iter.next();
			ret[i] = (ent.getKey() + "=" + ent.getValue());
			i++;
		}
		return ret;
	}
	
	public int getRuneCount() {
		return data.size();
	}
	
	public static RuneConfig buildConfigFromStringArray(String[] lines) {
		if(lines == null || lines.length == 0) {
			return null;
		}
		
		RuneConfig ret = new RuneConfig();
		String[] parts = null;
		
		for(String s : lines) {
			parts = s.split("=");
			
			if(parts.length == 2) {
				ret.addEntry(parts[0], Integer.parseInt(parts[1]));
			}
		}
		
		return ret;
	}
}
