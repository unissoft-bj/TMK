package net.wyun.dianxiao.service.primary;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import net.wyun.dianxiao.config.TMKProperties;
import net.wyun.dianxiao.model.CallDirection;
import net.wyun.dianxiao.watcher.ActivityPersistor;
import net.wyun.dianxiao.watcher.ActivityPersistorDBImpl;
import net.wyun.dianxiao.watcher.ActivityPersistorExoImpl;
import net.wyun.dianxiao.watcher.FileProcessUtil;

@Component
public class MP3FileHandlerImpl implements MP3FileHandler {
	
	@Autowired
	TMKProperties tmkProperties;
	
	@Autowired
	ActivityPersistor activityPersistorDBImpl;
	
	@Autowired
	ActivityPersistorExoImpl activityPersistorExoImpl;

	private static final Logger logger = LoggerFactory.getLogger(MP3FileHandlerImpl.class);

	/**
	 * file path
	 */
	@Override
	public void onEvent(Path path) {

		// we have a mp3 file
		// 1. remove those internal calls
		String fileName = FileProcessUtil.extractFileName(path.toString());
		List<String> list = FileProcessUtil.process(fileName);
		CallDirection direction = activityPersistorDBImpl.getCallDirection(list);
		
		if (direction == CallDirection.NOTSET) {
			logger.info("internal call");
			//deleteFile(path);
			//return;
		}

		// either IN or OUT, an activity should be recorded
		// move audio to target dir
		String source = path.toString();
		String target = source.replace(tmkProperties.getSrcDir(), tmkProperties.getTargetDir());
		
		list.add(target);
		
		try {
			TimeUnit.SECONDS.sleep(5);
			int duration = this.estimateDuration(path);
			logger.debug("duration estimation(sec): " + duration);
			list.add("" + duration);
			FileProcessUtil.move(source, target);
			logger.info("moving file done. file now at: " + target);
			
            //persist data to database
			logger.info("persist activity to DB now ...");
			activityPersistorDBImpl.persist(direction, list);
			logger.info("persist activity to DB done.");
			
			//persist data to eXo
			logger.info("persist activity to eXo now ...");
			activityPersistorExoImpl.persist(direction, list);
			logger.info("persist activity to eXo done.");
		} catch (IOException e) {
			logger.error("", e);
		} catch (InterruptedException e) {
			logger.error("", e);
			
		}

	}
	
    int estimateDuration(Path path){
		//here get the file size
		long fileSize = path.toFile().length();
		int duration = (int) (fileSize/1000);
		return duration;
	}


	private void deleteFile(Path path) {

		// remove audio file
		try {
			Files.delete(path);
		} catch (IOException e) {
			logger.error(e.getLocalizedMessage());
			e.printStackTrace();
		}
		return;
	}

}
