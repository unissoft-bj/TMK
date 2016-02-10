/**
 * 
 */
package net.wyun.dianxiao.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.
ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * @author michael
 *
 */
@Component
@ConfigurationProperties("tmk")
public class TMKProperties {
	
	@Value("${tmk.source.directory}")
	private String srcDir;

	@Value("${tmk.target.directory}")
	private String targetDir;

	public String getSrcDir() {
		return srcDir;
	}

	public void setSrcDir(String srcDir) {
		this.srcDir = srcDir;
	}

	public String getTargetDir() {
		return targetDir;
	}

	public void setTargetDir(String targetDir) {
		this.targetDir = targetDir;
	}
	
	

	
}
