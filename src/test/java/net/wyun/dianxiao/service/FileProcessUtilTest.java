package net.wyun.dianxiao.service;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.wyun.dianxiao.watcher.FileProcessUtil;

import org.junit.Test;

public class FileProcessUtilTest {
	
	FileProcessUtil processor;

	@Test
	public void testFileNameParser() {
			
	    String fileName = "230_18833500052_20151218-102537_24578_cg";
	    List<String> ls = FileProcessUtil.process(fileName);
	    for(String s:ls){
	    	System.out.println(s);
	    }
	    assertEquals("230", ls.get(0));
	    assertEquals("18833500052", ls.get(1));
	    
	    assertEquals("20151218", ls.get(2));
	    assertEquals("102537", ls.get(3));
	    
	    assertEquals("24578", ls.get(4));
	    assertEquals("cg", ls.get(5));

	}
	
	@Test
	public void testDateFormatter(){
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	    Date date;
	    try {
	        date = dateFormat.parse("20151218");
	        System.out.println(date.toString()); // Wed Dec 04 00:00:00 CST 2013

	        String output = dateFormat.format(date); 
	        System.out.println(output); // 2013-12-04
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
	}
	
	@Test
	public void testDateTimeFormatter(){
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss");
	    Date date;
	    try {
	        date = dateFormat.parse("20151218-102537");
	        System.out.println(date.toString()); // 

	        String output = dateFormat.format(date); 
	        System.out.println(output); // 
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
	}
	
	@Test
	public void testExtractFileName(){
		String filePathName = "/usr/share/record/pbxrecord/dysoft/20151218/230_18833500052_20151218-102537_24578_cg.mp3";
		String fileName = FileProcessUtil.extractFileName(filePathName);
		System.out.println("fileName without suffix: " + fileName);
		assertEquals("230_18833500052_20151218-102537_24578_cg", fileName);
	}
	
	//@Test
	public void moveFile() throws IOException{
		String filePathName = "/usr/share/record/pbxrecord/dysoft/20151218/230_18833500052_20151218-102537_24578_cg.mp3";
		String targetPath = "/opt/tmk/record/dysoft/20151218/230_18833500052_20151218-102537_24578_cg.mp3";
		Files.move(Paths.get(filePathName), Paths.get(targetPath));
	}
	
	//@Test
	public void testMove() throws IOException{
		String filePathName = "/usr/share/record/pbxrecord/dysoft/20151218/230_18833500052_20151218-102537_24578_cg.mp3";
		String targetPath = "/opt/tmk/record/dysoft/20151218/230_18833500052_20151218-102537_24578_cg.mp3";
		FileProcessUtil.move(filePathName, targetPath);
		File file = new File(targetPath);
		assertTrue(file.exists());
	}
	
	@Test
	public void testGetTargetFilePath(){
		String filePathName = "/usr/share/record/pbxrecord/dysoft/20151218/230_18833500052_20151218-102537_24578_cg.mp3";
		String target = filePathName.replace("/usr/share/record/pbxrecord", "/opt/tmk/record");
		assertEquals("/opt/tmk/record/dysoft/20151218/230_18833500052_20151218-102537_24578_cg.mp3", target);
	}
	
	@Test
	public void testGetParentPath(){
		File file = new File("/opt/tmk/record/dysoft/20151218/230_18833500052_20151218-102537_24578_cg.mp3");
		String parentPath = file.getAbsoluteFile().getParent();
		System.out.println(parentPath);
	}
	  
	
	
}
