package celestialwizardry.handler;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import celestialwizardry.api.event.CWRuneconfigResetEvent;
import celestialwizardry.config.RuneConfig;
import celestialwizardry.network.message.MessageRuneConfig;
import celestialwizardry.registry.RuneRegistry;


@SideOnly(Side.CLIENT)
public class ClientRuneConfigurationHandler {
	private static List<String> runeConfBuffer;
	private static RuneConfig runeConfigFromServer;
	
	public static void setupRuneConfig(RuneConfig rc) {
		RuneRegistry.setupNumIds(rc);
	}
	
	public static void addEntriesToBuffer(MessageRuneConfig msg) {
		if(runeConfBuffer == null) {
			runeConfBuffer = new ArrayList<String>();
		}
		for(int i=0;i<msg.entryLines.length;i++) {
			runeConfBuffer.add(msg.entryLines[i]);
		}
		
	}
	
	private static void buildRuneConfig() {
		runeConfigFromServer = RuneConfig.buildConfigFromStringArray((String[])runeConfBuffer.toArray());
	}
	
	public static void reset() {
		runeConfBuffer.clear();
		
	}
	
}
