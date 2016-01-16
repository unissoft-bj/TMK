/**
 * 
 */
package net.wyun.dianxiao.watcher;

import java.nio.file.Path;
import java.nio.file.WatchEvent.Kind;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

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
	
	@Value("${dianxiao.call.directory}")
	private String directory;
	
	DirectoryWatchService directoryWatchService;
	
	public SalesCallMonitor(){
		System.out.println("create SalesCallMonitor.");
		
	}
	
	private void createDirWatcher(){
		directoryWatchService = new DirectoryWatchService(new DirectoryEventListenerAdapter() {

		      @Override
		      public void onEvent(Kind<Path> kind, Path path) {
		        System.out.println(kind + " " + path);
		      }

		      @Override
		      public void onNewWatcher(String dir) {
		        System.out.println("New watcher for " + dir);
		      }

		      @Override
		      public void onWatcherClosed(String dir) {
		        System.out.println("Watcher removed: " + dir);
		      }
		    }, this.directory);
	}
	
	public void startWatching() throws Exception {
		
		directoryWatchService.watch();

	    //Thread.sleep(10000000);
	    System.out.println("waiting for user input to create event at /tmp/root!");
	    //TimeUnit.SECONDS.sleep(60);
	  }
	
	@PostConstruct
    public void init() throws Exception {
		this.createDirWatcher();
		startWatching();
	}
	
	

}
