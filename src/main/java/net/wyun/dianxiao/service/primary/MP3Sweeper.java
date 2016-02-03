/**
 * 
 */
package net.wyun.dianxiao.service.primary;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import javax.annotation.PostConstruct;

import net.wyun.dianxiao.watcher.FileFinder;
import net.wyun.util.SysInfoUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author michael
 *
 */
@Service
@EnableScheduling
public class MP3Sweeper {

	private static final Logger logger = LoggerFactory.getLogger(MP3Sweeper.class);
	
	@Value("${dianxiao.record.directory}")
	private String directory;
	private final static String pattern = "*.mp3";
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
	
	@Autowired
	MP3FileHandler mp3FileHandler;
	
	@Scheduled(fixedDelayString = "${tmk.mp3.sweeper.interval}")
	public void processMP3Files() {
		
		logger.info("### [MP3 Sweeper] on " + dateFormat.format(new Date()));
		logger.info("sys info: " + SysInfoUtil.getSysInfo());
		Path startingDir = Paths.get(directory);
		FileFinder finder = new FileFinder(pattern);
		try {
			Files.walkFileTree(startingDir, finder);
		} catch (IOException e) {
			logger.error("file finding error", e);
			e.printStackTrace();
		}
		List<Path> list = finder.done();
		for(Path p:list){
			long lastModified = p.toFile().lastModified();
			if(isOneMinuteOld(lastModified)){
				mp3FileHandler.onEvent(p);
			}
			
		}
		
	}
	
	private static long ONE_MINUTE = 60 * 1000;
	private boolean isOneMinuteOld(long last){
		return (System.currentTimeMillis() - last ) > ONE_MINUTE;
	}
	
	@PostConstruct
    public void init() {
		logger.info("mp3 sweeper scheduler: initialising ...");
		logger.info("record directory: " + this.directory);
	}

	
}