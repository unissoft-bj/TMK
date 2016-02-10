package net.wyun.dianxiao.config;

import static org.junit.Assert.*;
import net.wyun.dianxiao.BaseSpringTestRunner;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TMKPropertiesTest extends BaseSpringTestRunner{

	@Autowired
    TMKProperties tmkProp;
	
	@Test
	public void testGetSrcDir() {
		String srcDir = tmkProp.getSrcDir();
		String targetDir = tmkProp.getTargetDir();
		System.out.println("mp3 source dir " + srcDir + ", target dir " + targetDir);
	}

}
