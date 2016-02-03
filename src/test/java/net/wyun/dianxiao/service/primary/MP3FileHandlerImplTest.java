package net.wyun.dianxiao.service.primary;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

public class MP3FileHandlerImplTest {

	@Test
	public void testEstDuration() {
		MP3FileHandlerImpl impl = new MP3FileHandlerImpl();
		String s = "src/test/resources/229_013701272752_20160129-094134_32849_cd.mp3";
		Path p = Paths.get(s);
		int d = impl.estimateDuration(p);
		
		String s_d = "" + d;
		int sec = Integer.parseInt(s_d);
		double minute = (double) (sec/60.0);
		
		String s_min = "" + minute;
		double min = Double.parseDouble(s_min);
		
		BigDecimal bd_min = new BigDecimal(min);
		
		System.out.println(d);
		System.out.println(minute);
		System.out.println(min);
		
		System.out.println("BigDecimal value: " + bd_min);
	}

}
