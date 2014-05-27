package okkapel.configreader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okkapel.configreader.Source;
import okkapel.configreader.ConfigOptions;

public class FileIO implements IIO{
	public List<String> readlines(Source s, boolean ignoreComments) {
		BufferedReader reader = null;
		if(s==null){
			return null;
		}
		if(s.isRemote()) {
			System.err.println("Source was remote, can't read with FileIO, use NetIO instead!");
			return null;
		}
		File f = new File(s.getLocation());
		
		try {
			if(f.exists() && f.isFile()) {
				reader = new BufferedReader(new FileReader(f));
				
			}
		}catch(FileNotFoundException fnfe) {
			System.err.println("File \"" + f.getName() + "\" was not found!!!");
			fnfe.printStackTrace();
			return null;
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
				System.err.println("Couldn't read config file \"" + f.getName() + "\"");
				return null;
			}
			return ret != null ? ret : null;
		}
		return null;
	}
}
