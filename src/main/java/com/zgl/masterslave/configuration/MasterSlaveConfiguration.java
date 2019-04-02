package com.zgl.masterslave.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.google.common.collect.Maps;
import io.shardingsphere.api.config.MasterSlaveRuleConfiguration;
import io.shardingsphere.shardingjdbc.api.MasterSlaveDataSourceFactory;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Map;

/**
 * @author zgl
 * @date 2019/4/1 下午2:58
 */
@Data
@Slf4j
@Configuration
@EnableConfigurationProperties(ShardingMastSlaveConfiguration.class)
@ConfigurationProperties(prefix = "spring.datasource.druid")
@ConditionalOnProperty({"sharding.jdbc.data-sources.ds_master.url", "sharding.jdbc.master-slave-rule.master-data-source-name"})
public class MasterSlaveConfiguration {

	private Integer maxActive;

	private Integer initialSize;

	private Long maxWait;

	private Integer minIdle;

	private Long timeBetweenEvictionRunsMillis;

	private Long minEvictableIdleTimeMillis;

	private String validationQuery;

	private Boolean testWhileIdle;

	private Boolean testOnBorrow;

	private Boolean testOnReturn;

	private Boolean poolPreparedStatements;

	private Integer maxOpenPreparedStatements;

	private Boolean useGlobalDataSourceStat;

	private String filters;

	@Resource
	private ShardingMastSlaveConfiguration shardingMastSlaveConfiguration;

	@Bean
	public DataSource masterSlaveDataSource() throws SQLException {
		shardingMastSlaveConfiguration.getDataSources().forEach((k, v) -> configDataSource(v));
		Map<String, DataSource> dataSourceMap = Maps.newHashMap();
		dataSourceMap.putAll(shardingMastSlaveConfiguration.getDataSources());
		DataSource dataSource = MasterSlaveDataSourceFactory.createDataSource(dataSourceMap, new MasterSlaveRuleConfiguration(shardingMastSlaveConfiguration
				.getMasterSlaveRule().getName(), shardingMastSlaveConfiguration.getMasterSlaveRule().getMasterDataSourceName()
				,shardingMastSlaveConfiguration.getMasterSlaveRule().getSlaveDataSourceNames()
				,shardingMastSlaveConfiguration.getMasterSlaveRule().getLoadBalanceAlgorithmType().getAlgorithm()), Maps.newHashMap(),null);
		log.info("masterSlaveDataSource config complete");
		return dataSource;
	}

	private void configDataSource(DruidDataSource druidDataSource) {
		druidDataSource.setMaxActive(maxActive);
		druidDataSource.setInitialSize(initialSize);
		druidDataSource.setMaxWait(maxWait);
		druidDataSource.setMinIdle(minIdle);
		druidDataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
		druidDataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
		druidDataSource.setValidationQuery(validationQuery);
		druidDataSource.setTestWhileIdle(testWhileIdle);
		druidDataSource.setTestOnBorrow(testOnBorrow);
		druidDataSource.setTestOnReturn(testOnReturn);
		druidDataSource.setPoolPreparedStatements(poolPreparedStatements);
		druidDataSource.setMaxOpenPreparedStatements(maxOpenPreparedStatements);
		druidDataSource.setUseGlobalDataSourceStat(useGlobalDataSourceStat);
		try {
			druidDataSource.setFilters(filters);
		} catch (SQLException e) {
			log.error("druid configuration initialization filter", e);
		}
	}
}