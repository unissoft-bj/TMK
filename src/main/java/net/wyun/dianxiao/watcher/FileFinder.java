/**
 * 
 */
package net.wyun.dianxiao.watcher;

/**
 * @author michael
 *
 */
import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.FileVisitResult.*;
import static java.nio.file.FileVisitOption.*;

public class FileFinder extends SimpleFileVisitor<Path> {

		private final PathMatcher matcher;
		private int numMatches = 0;
		private List<Path> fileList;

		public FileFinder(String pattern) {
			matcher = FileSystems.getDefault().getPathMatcher("glob:" + pattern);
			fileList = new ArrayList<Path>();
		}

		// Compares the glob pattern against
		// the file or directory name.
		void find(Path file) {
			Path name = file.getFileName();
			if (name != null && matcher.matches(name)) {
				numMatches++;
				System.out.println(file);
				fileList.add(file);
			}
		}

		// Prints the total number of
		// matches to standard out.
		public List<Path> done() {
			System.out.println("Matched: " + numMatches);
			return fileList;
		}

		// Invoke the pattern matching
		// method on each file.
		@Override
		public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
			find(file);
			return CONTINUE;
		}

		// Invoke the pattern matching
		// method on each directory.
		@Override
		public FileVisitResult preVisitDirectory(Path dir,
				BasicFileAttributes attrs) {
			find(dir);
			return CONTINUE;
		}

		@Override
		public FileVisitResult visitFileFailed(Path file, IOException exc) {
			System.err.println(exc);
			return CONTINUE;
		}

}
