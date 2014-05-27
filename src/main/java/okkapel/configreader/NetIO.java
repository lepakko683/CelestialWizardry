package okkapel.configreader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import okkapel.configreader.Source;
import okkapel.configreader.ConfigOptions;

public class NetIO implements IIO {
	public List<String> readlines(Source s, boolean ignoreComments) {
		BufferedReader reader = null;
		if(s==null){
			return null;
		}
		if(!s.isRemote()) {
			System.err.println("Source wasn't remote, can't read with NetIO, use FileIO instead!");
			return null;
		}
		URLConnection conn = null;
		try {
			conn = new URL(s.getLocation()).openConnection();
		} catch (IOException e) {
			System.err.println("Connection to \"" + s.getLocation() + "\" failed!");
			e.printStackTrace();
			return null;
		}
		conn.setRequestProperty("Accept-Charset", "UTF-8");
		
		InputStream resp = null;
		try {
			resp = conn.getInputStream();
			reader = new BufferedReader(new InputStreamReader(resp));
		}catch(IOException ioe) {
			System.err.println("Couldn't open InputStream");
			ioe.printStackTrace();
		}
		
		
		if(reader != null){
			List<String> ret = new ArrayList<String>(ConfigOptions.ESSENTIAL_OPTION_COUNT);
			String line = null;
			try{
				while((line = reader.readLine()) != null) {
					line = line.trim();
					if(!line.startsWith("#") || !ignoreComments) {
						if(ignoreComments){
							ret.add(line.substring(0, line.indexOf("#")));
						}else {
							ret.add(line);
						}
					}
				}
				reader.close();
			}catch(IOException ioe){
				ioe.printStackTrace();
				System.err.println("Couldn't read remote config file at \"" + s.getLocation() + "\"");
				return null;
			}
			return ret != null ? ret : null;
		}
		return null;
	}
}
