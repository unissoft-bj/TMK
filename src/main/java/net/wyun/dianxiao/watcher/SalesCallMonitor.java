/**
 * 
 */
package net.wyun.dianxiao.watcher;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.WatchEvent.Kind;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import net.wyun.dianxiao.model.CallDirection;
import net.wyun.dianxiao.service.secondary.SalesAgentLookUpService;

import java.nio.file.Files;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sentaca.watcher.DirectoryWatchService;
import com.sentaca.watcher.DirectoryEventListenerAdapter;

/**
 * @author michael
 *
 */
@Service
public class SalesCallMonitor {

	@Value("${dianxiao.record.directory}")
	private String directory;

	@Value("${dianxiao.target.directory}")
	private String targetDir;

	DirectoryWatchService directoryWatchService;
	private static final Logger logger = LoggerFactory
			.getLogger(SalesCallMonitor.class);

	@Autowired
	ActivityPersistor activityPersistor;

	public SalesCallMonitor() {
		logger.info("create SalesCallMonitor.");

	}

	private void createDirWatcher() {
		directoryWatchService = new DirectoryWatchService(
				new DirectoryEventListenerAdapter() {

					@Override
					public void onEvent(Kind<Path> kind, Path path) {
						// System.out.println(kind + " " + path);
						logger.info(kind + ", " + path + ", is absolute path: "	+ path.isAbsolute());
						if(!kind.toString().equals("ENTRY_CREATE")) return;
						if (!FileProcessUtil.isFile(path)) 			return;
						if (!path.toString().endsWith(".mp3"))		return;

						// we have a mp3 file
						// 1. remove those internal calls
						String fileName = FileProcessUtil.extractFileName(path
								.toString());
						List<String> list = FileProcessUtil.process(fileName);
						CallDirection direction = activityPersistor.getCallDirection(list);
						
						if (direction == CallDirection.INTERNAL) {
							// remove audio file
							try {
								Files.delete(path);
							} catch (IOException e) {
								logger.error(e.getLocalizedMessage());
								e.printStackTrace();
							}
							return;

						}

						// either IN or OUT, an activity should be recorded
						// move audio to target dir
						String source = path.toString();
						String target = source.replace(directory, targetDir);
						
						//first persist data to database
						list.add(target);
						logger.info("persist activity now ...");
						activityPersistor.persist(direction, list);
						logger.info("persist activity done.");
						
						try {
							TimeUnit.SECONDS.sleep(4);
						} catch (InterruptedException e1) {		}
						
						try {
							FileProcessUtil.move(source, target);
							logger.info("moving file done. file now at: " + target);
						} catch (IOException e) {
							logger.error(e.getLocalizedMessage());
							e.printStackTrace();
						}
						

					}

					@Override
					public void onNewWatcher(String dir) {
						logger.info("New watcher for " + dir);
					}

					@Override
					public void onWatcherClosed(String dir) {
						logger.info("Watcher removed: " + dir);
					}
				}, this.directory);
	}

	public void startWatching() throws Exception {

		directoryWatchService.watch();

		// Thread.sleep(10000000);
		logger.info("monitoring directory: " + directory);
		// TimeUnit.SECONDS.sleep(60);
	}

	@PostConstruct
	public void init() throws Exception {
		this.createDirWatcher();
		startWatching();
	}

}
