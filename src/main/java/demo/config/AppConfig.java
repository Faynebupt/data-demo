package demo.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.transaction.TransactionManager;
import javax.xml.ws.Service;
import java.util.Arrays;
import java.util.Properties;

/**
 * Created by 张少昆 on 2017/7/26.
 */
@Configuration
// @ComponentScan( basePackages = {"demo.config","demo.entity"} )
// @ComponentScan( basePackages = {"demo"} ,useDefaultFilters = false ,includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,classes ={Service.} ) })
@EnableJpaRepositories( basePackages = "demo.repo", entityManagerFactoryRef = "entityManagerFactory",
                        transactionManagerRef = "transactionManager" )
@EnableTransactionManagement
@DependsOn( "dataSourceFineConfig" )
@PropertySource( "classpath:application.properties" )
public class AppConfig {
    @Value( "${jdbc.url}" )
    private String url;
    @Value( "${jdbc.username}" )
    private String user;
    @Value( "${jdbc.password}" )
    private String password;
    @Value( "${jdbc.driverClass}" )
    private String driverClassName;

    @Value( "${hibernate.ejb.naming_strategy}" )
    private String namingStrategy;
    @Value( "${hibernate.dialect}" )
    private String dialect;
    @Value( "${hibernate.format_sql}" )
    private String formatSql;
    @Value( "${hibernate.show_sql}" )
    private String showSql;
    @Value( "${hibernate.hbm2ddl.auto}" )
    private String hbm2ddlAuto;

    @Autowired
    private Filter statFilter;
    @Autowired
    private Filter logFilter;

    @Bean( initMethod = "init", destroyMethod = "close" )
    public DruidDataSource dataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        // 自动提交
        dataSource.setDefaultAutoCommit(true);
        // 配置初始化大小、最小、最大
        dataSource.setInitialSize(2);
        dataSource.setMinIdle(2);
        dataSource.setMaxActive(100);
        // 查询\事务超时时间（半小时）
        // dataSource.setQueryTimeout(1800000);
        // 配置获取连接等待超时的时间
        dataSource.setMaxWait(60 * 1000);
        // 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        dataSource.setTimeBetweenEvictionRunsMillis(60000);
        // 配置一个连接在池中最小生存的时间，单位是毫秒
        dataSource.setMinEvictableIdleTimeMillis(1800000);
        dataSource.setValidationQuery("SELECT 1 FROM DUAL");
        dataSource.setValidationQueryTimeout(3000);
        dataSource.setTestWhileIdle(true);
        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(false);
        // 打开PSCache，并且指定每个连接上PSCache的大小
        dataSource.setPoolPreparedStatements(true);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(20);
        // 配置监控统计拦截的 filters
        dataSource.setProxyFilters(Arrays.asList(statFilter, logFilter));
        // 合并多个DruidDataSource的监控数据
        dataSource.setUseGlobalDataSourceStat(true);
        // 配置这个属性的意义在于，如果存在多个数据源，监控的时候可以通过名字来区分开来
        dataSource.setName("mainDataSource");

        return dataSource;
    }

    // /**
    //  * 声明式事务  使用JPA，不能用这个事务
    //  */
    // @Bean
    // @Order( 20 )
    // public DataSourceTransactionManager transactionManager(){
    //     TransactionManager tx;
    //     DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
    //     dataSourceTransactionManager.setDataSource(dataSource());
    //     return dataSourceTransactionManager;
    // }

    @Bean
    public Properties jpaProperties(){
        Properties properties = new Properties();
        properties.setProperty("hibernate.ejb.naming_strategy", namingStrategy);
        properties.setProperty("hibernate.dialect", dialect);
        properties.setProperty("hibernate.format_sql", formatSql);
        properties.setProperty("hibernate.show_sql", showSql);
        properties.setProperty("hibernate.hbm2ddl.auto", hbm2ddlAuto);

        return properties;
    }

    // 奇怪的问题，为什么配置entityManagerFactoryBean可以，但配置entityManagerFactory就不行？？
    @Bean("entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(){
        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setDataSource(dataSource());
        bean.setPackagesToScan("demo");
        bean.setJpaProperties(jpaProperties());
        bean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        return bean;
    }

    @Bean
    public JpaTransactionManager transactionManager(){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setDataSource(dataSource());
        transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
        return transactionManager;
    }

}
