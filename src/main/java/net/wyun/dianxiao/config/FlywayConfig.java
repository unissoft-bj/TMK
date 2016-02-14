package net.wyun.dianxiao.config;

import javax.sql.DataSource;

import net.wyun.dianxiao.service.primary.MP3FileHandlerImpl;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class FlywayConfig {

	private static final Logger logger = LoggerFactory.getLogger(FlywayConfig.class);
	
	@Autowired
	@Qualifier(PrimaryDataSourceConfig.PREFIX_PRIMARY)
	private DataSource primaryDataSource;
	
	@Autowired
	@Qualifier(SecondaryDataSourceConfig.PREFIX_SECONDARY)
	private DataSource secondaryDataSource;
	
	@Autowired
	private FlywayConfiguration flywayConfiguration;

	
	@ConditionalOnProperty(prefix = "flyway", name = "enabled", matchIfMissing = true)
	@Bean(initMethod = "migrate")
	@FlywayDataSource
	@Primary
	public Flyway primaryFlyway() {
		logger.debug("generate primary flyway here");
		Flyway flyway = new Flyway();
		
		flyway.setDataSource(primaryDataSource);
		flyway.setLocations(flywayConfiguration.getPrimary().getLocation());
		flyway.setEncoding("GBK");
		logger.debug("primary flyway generated here");
		return flyway;
	}
	
	@Bean(initMethod = "migrate")
	@FlywayDataSource
	public Flyway secondaryFlyway() {
		Flyway flyway = new Flyway();
		flyway.setDataSource(secondaryDataSource);
		flyway.setLocations(flywayConfiguration.getSecondary().getLocation());
		return flyway;
	}

}
