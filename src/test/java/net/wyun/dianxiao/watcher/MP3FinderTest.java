package net.wyun.dianxiao.watcher;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import net.wyun.dianxiao.watcher.FileFinder;

import org.junit.Test;

public class MP3FinderTest {
	
	String startingDirStr = "/opt/tmk/record";
	

	@Test
	public void test() throws IOException {

		Path startingDir = Paths.get(startingDirStr);
		String pattern = "*.mp3";

		FileFinder finder = new FileFinder(pattern);
		Files.walkFileTree(startingDir, finder);
		List<Path> list = finder.done();
		for(Path p:list){
			System.out.println(p);
		}

	}

}
