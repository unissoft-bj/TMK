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

import net.wyun.dianxiao.config.TMKProperties;
import net.wyun.dianxiao.model.CallDirection;
import net.wyun.dianxiao.service.primary.MP3FileHandler;
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

	@Autowired
	TMKProperties tmkProperties;
	
	@Autowired
	MP3FileHandler mp3FileHandler;

	DirectoryWatchService directoryWatchService;
	private static final Logger logger = LoggerFactory.getLogger(SalesCallMonitor.class);

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

						logger.info("new CREATE event, Processing ...");
						mp3FileHandler.onEvent(path);
					}

					@Override
					public void onNewWatcher(String dir) {
						logger.info("New watcher for " + dir);
					}

					@Override
					public void onWatcherClosed(String dir) {
						logger.info("Watcher removed: " + dir);
					}
				}, this.tmkProperties.getSrcDir());
	}

	public void startWatching() throws Exception {

		directoryWatchService.watch();

		// Thread.sleep(10000000);
		logger.info("monitoring directory: " + tmkProperties.getSrcDir());
		// TimeUnit.SECONDS.sleep(60);
	}

	@PostConstruct
	public void init() throws Exception {
		this.createDirWatcher();
		startWatching();
	}

}
