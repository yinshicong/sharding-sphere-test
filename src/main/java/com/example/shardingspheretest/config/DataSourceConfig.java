package com.example.shardingspheretest.config;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.infra.config.algorithm.ShardingSphereAlgorithmConfiguration;
import org.apache.shardingsphere.sharding.algorithm.keygen.SnowflakeKeyGenerateAlgorithm;
import org.apache.shardingsphere.sharding.api.config.ShardingRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.rule.ShardingTableRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.sharding.StandardShardingStrategyConfiguration;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dataSource() throws SQLException {
        //数据库
        Map<String, DataSource> dataSourceMap = createDateSourceMap();
        ShardingRuleConfiguration shardingRuleConfig = createShardingRuleConfig();
        DataSource dataSource = ShardingSphereDataSourceFactory.createDataSource(dataSourceMap, Collections.singleton(shardingRuleConfig), new Properties());
        return dataSource;
    }

    /**
     * 获取数据源
     *
     * @return
     */
    private Map<String, DataSource> createDateSourceMap() {

        //0号库
        DruidDataSource db0 = DruidDataSourceBuilder.create().build();
        db0.setUrl("jdbc:mysql://47.100.188.176:3306/sharding00");
        db0.setDriverClassName("com.mysql.cj.jdbc.Driver");
        db0.setUsername("root");
        db0.setPassword("y*scong()");

        //第一个数据库
        Map<String, DataSource> dataSourceMap = new HashMap<>(3);
        DruidDataSource db1 = DruidDataSourceBuilder.create().build();
        db1.setUrl("jdbc:mysql://47.100.188.176:3306/sharding01");
        db1.setDriverClassName("com.mysql.cj.jdbc.Driver");
        db1.setUsername("root");
        db1.setPassword("y*scong()");

        //第二个数据库
        DruidDataSource db2 = DruidDataSourceBuilder.create().build();
        db2.setUrl("jdbc:mysql://47.100.188.176:3306/sharding02");
        db2.setDriverClassName("com.mysql.cj.jdbc.Driver");
        db2.setUsername("root");
        db2.setPassword("y*scong()");

        dataSourceMap.put("sharding00", db0);
        dataSourceMap.put("sharding01", db1);
        dataSourceMap.put("sharding02", db2);

        return dataSourceMap;
    }

    /**
     * 配置分库分表算法
     *
     * @return
     */
    public ShardingRuleConfiguration createShardingRuleConfig() {

        // 配置 t_order 表规则
        ShardingTableRuleConfiguration orderTableRuleConfig = new ShardingTableRuleConfiguration("t_user", "sharding0${0..2}.t_user_0${0..2}");
        // 配置分库策略
        orderTableRuleConfig.setDatabaseShardingStrategy(new StandardShardingStrategyConfiguration("id", "dbShardingAlgorithm"));
//      // 配置分表策略
        orderTableRuleConfig.setTableShardingStrategy(new StandardShardingStrategyConfiguration("id", "tableShardingAlgorithm"));
        // 配置分片规则
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTables().add(orderTableRuleConfig);

        // 配置分库算法
        Properties dbShardingAlgorithmrProps = new Properties();
        dbShardingAlgorithmrProps.setProperty("algorithm-expression", "sharding0${id % 3}");
        shardingRuleConfig.getShardingAlgorithms().put("dbShardingAlgorithm", new ShardingSphereAlgorithmConfiguration("INLINE", dbShardingAlgorithmrProps));


        // 配置分表算法
//        Properties tableShardingAlgorithmrProps = new Properties();
//        tableShardingAlgorithmrProps.setProperty("algorithm-expression", "t_user_0${id % 3}");
//        shardingRuleConfig.getShardingAlgorithms().put("tableShardingAlgorithm", new ShardingSphereAlgorithmConfiguration("INLINE", tableShardingAlgorithmrProps));
//        shardingRuleConfig.setKeyGenerators();
        return shardingRuleConfig;
    }

//    @Bean("firstDataSource")
//    public DataSource firstDataSource() {
//        DruidDataSource first = DruidDataSourceBuilder.create().build();
//        first.setUrl("jdbc:mysql://47.100.188.176:3306/test");
//        first.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        first.setUsername("root");
//        first.setPassword("y*scong()");
//        return first;
//    }

//    @Bean("secondDataSource")
//    public DataSource secondDataSource() {
//        DruidDataSource second = DruidDataSourceBuilder.create().build();
//        second.setUrl("jdbc:mysql://47.100.188.176:3306/test1");
//        second.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        second.setUsername("root");
//        second.setPassword("y*scong()");
//        return second;
//    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(@Autowired DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:com/example/shardingspheretest/dal" +
                "/mapper/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

}
