package com.centrin.ciyun;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 */
@SpringBootApplication
//@EnableEurekaClient // 服务启动时,自动注册到eureka服务中
public class Application {

	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	@Bean
	public CountDownLatch closeLatch() {
		return new CountDownLatch(1);
	}

	public static void main(String[] args) throws InterruptedException {

		ApplicationContext ctx = new SpringApplicationBuilder().sources(Application.class).web(true).run(args);
		CountDownLatch closeLatch = ctx.getBean(CountDownLatch.class);

		logger.info("ciyun_rpt_miniprogram Server Startup success");
		logger.info("=======================ciyun_rpt_miniprogram Server Startup success!=========================");

		closeLatch.await();

	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		RestTemplate template = new RestTemplate();
		SimpleClientHttpRequestFactory factory = (SimpleClientHttpRequestFactory) template.getRequestFactory();
		factory.setConnectTimeout(3000);
		factory.setReadTimeout(3000);
		return template;
	}
}
