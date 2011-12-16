package me.kalmanolah.extras;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.logging.Logger;

public class OKUpdater{

	@SuppressWarnings("unused")
	public static File update(String name, String ver, String checkloc, String dlloc, Logger log, String prefix){
		try {
			String latestver = null;
			log.info(prefix + "Initiating auto-update...");
			latestver = OKReader.main(checkloc + "?id=" + name);
			if(latestver != null){
				String[] halve = latestver.split("\\&");
				String[] newver = halve[0].split("\\.");
				String[] oldver = ver.split("\\.");
				int g = 0;
				int h = 0;
				for (String s: newver){
					g++;
				}
				for (String r: oldver){
					h++;
				}
				String isnew = null;
				if(g > h){
					isnew = "yes";
				}else if((g == h) || (g < h)){
					int z = 0;
					while((z < (g - 1)) || (z == (g - 1)) && isnew == null){
						if(Integer.parseInt(newver[z]) > Integer.parseInt(oldver[z])){
							isnew = "yes";
						}
						if(Integer.parseInt(newver[z]) < Integer.parseInt(oldver[z])){
							isnew = "no";
						}
						z++;
					}
				}
				if(isnew == null){
					isnew = "no";
				}
				if(isnew.equals("yes")){
					log.info(prefix + "A new version of " + name + ", v" + halve[0] + " is available.");
					new File("plugins" + File.separator + "ClearInv").mkdir();
					new File("plugins" + File.separator + "ClearInv" + File.separator + "update").mkdir();
					File file = new File("plugins" + File.separator + "ClearInv" + File.separator + "update" + File.separator + name + "-" + halve[0] + "-" + halve[1]);
					if(!file.exists()){
						log.info(prefix + "Starting download of " + name + " v" + halve[0] + "...");
						FileOutputStream fos;
						URL url = new URL(dlloc + "?id=" + name + "&ver=" + halve[0] + "&mc=1");
						ReadableByteChannel rbc = Channels.newChannel(url.openStream());
						fos = new FileOutputStream("plugins" + File.separator + "ClearInv" + File.separator + "update" + File.separator + name + "-" + halve[0] + "-" + halve[1]);
						fos.getChannel().transferFrom(rbc, 0, 1 << 24);
						fos.close();
						log.info(prefix + name + "-" + halve[0] + "-" + halve[1] + " downloaded to " + File.separator + "plugins" + File.separator + "ClearInv" + File.separator + "update" + File.separator + ".");
						return file;
					}else{
						log.info(prefix + "You already have the latest version of " + name + " in your /plugins/ClearInv/update/ folder.");
						return null;
					}
				}else{
					log.info(prefix + "You already have the latest version of " + name + ".");
					return null;
				}
			}
		} catch (Exception e) {
			log.info(prefix + "Error while checking for latest version.");
			return null;
		}
		return null;
	}
}
