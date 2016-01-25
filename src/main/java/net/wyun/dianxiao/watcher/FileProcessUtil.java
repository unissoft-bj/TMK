/**
 * 
 */
package net.wyun.dianxiao.watcher;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * @author michael
 *
 */
public class FileProcessUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(FileProcessUtil.class);
	//230_18833500052_20151218-102537_24578_cg.mp3
	//Need to extract  
	
	public static List<String> process(String fileName){
		//OCLG oclg = new OCLG();
		String[] ss = fileName.split("_");
		List<String> parts = new ArrayList<String>(Arrays.asList(ss));
		
		String dateTime = parts.remove(2);
		String[] dt = dateTime.split("-");
		parts.add(2, dt[0]); //date: 20151218
		parts.add(3, dt[1]); //time 102537
		
		return parts;
	}
	
	public static boolean isFile(Path path){
		return path.toFile().isFile();
	}
	
	public static String extractFileName( String filePathName ){
	    if ( filePathName == null )
	      return null;

	    int dotPos = filePathName.lastIndexOf( '.' );
	    int slashPos = filePathName.lastIndexOf( '\\' );
	    if ( slashPos == -1 )
	      slashPos = filePathName.lastIndexOf( '/' );

	    if ( dotPos > slashPos )
	    {
	      return filePathName.substring( slashPos > 0 ? slashPos + 1 : 0,
	          dotPos );
	    }

	    return filePathName.substring( slashPos > 0 ? slashPos + 1 : 0 );
	  }
	
	public static void move(String srcFile, String targetFile) throws IOException{
		String dir = targetFile.substring(0, targetFile.lastIndexOf(File.separator));
		File dirFile = new File(dir);
		if(!dirFile.exists()){
			boolean dirCreated = dirFile.mkdirs();
			if(!dirCreated){
				logger.error(dir + " creation fails!!! Moving file fails too.");
				return;
			}
		}
		Files.move(Paths.get(srcFile), Paths.get(targetFile));
		logger.info("moving file success: " + srcFile);
	}

}
