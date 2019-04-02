package com.zgl.masterslave.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import io.shardingsphere.core.yaml.masterslave.YamlMasterSlaveRuleConfiguration;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zgl
 * @date 2019/4/1 上午10:59
 */
@Data
@ConfigurationProperties(prefix = "sharding.jdbc")
public class ShardingMastSlaveConfiguration {

    private Map<String, DruidDataSource> dataSources = new HashMap<>();

    private YamlMasterSlaveRuleConfiguration masterSlaveRule;
}