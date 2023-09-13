package jp.co.reggie.mbpdeal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;

/**
 * MBP分頁攔截器配置
 *
 * @author Administrator
 */
@Configuration
public class MyBatisPlusConfig {

	/**
	 * configuration of MBPlus pagination intercepter
	 *
	 * @return mybatisPlusInterceptor
	 */
	@Bean
	protected MybatisPlusInterceptor mybatisPlusInterceptor() {
		final MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
		final PaginationInnerInterceptor innerInterceptor = new PaginationInnerInterceptor();
		innerInterceptor.setDbType(DbType.POSTGRE_SQL);
		innerInterceptor.setOverflow(true);
		mybatisPlusInterceptor.addInnerInterceptor(innerInterceptor);
		return mybatisPlusInterceptor;
	}
}