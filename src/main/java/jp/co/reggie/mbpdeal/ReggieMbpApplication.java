package jp.co.reggie.mbpdeal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import lombok.extern.slf4j.Slf4j;

/**
 * ArkamaHozota
 *
 * @author Administrator
 * @since 1.00beta
 */
@Slf4j
@SpringBootApplication
@ServletComponentScan
@EnableTransactionManagement
public class ReggieMbpApplication {
	public static void main(final String[] args) {
		SpringApplication.run(ReggieMbpApplication.class, args);
		log.info("本工程啓動成功......");
	}
}
