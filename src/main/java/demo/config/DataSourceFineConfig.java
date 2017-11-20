package demo.config;

import com.alibaba.druid.filter.logging.LogFilter;
import com.alibaba.druid.filter.logging.Slf4jLogFilter;
import com.alibaba.druid.filter.stat.StatFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 数据库配置
 */
@Configuration
public class DataSourceFineConfig {
	/**
	 * 记录超过1秒钟 的SQL
	 */
	@Bean
	public StatFilter statFilter(){
		StatFilter statFilter = new StatFilter();
		statFilter.setSlowSqlMillis(1000);
		statFilter.setLogSlowSql(true);
		return statFilter;
	}

	/**
	 * 日志输出配置
	 */
	@Bean
	public LogFilter logFilter(){
		Slf4jLogFilter logFilter = new Slf4jLogFilter();
		// 关闭错误日志输出
		logFilter.setStatementLogErrorEnabled(false);
		return logFilter;
	}
}
