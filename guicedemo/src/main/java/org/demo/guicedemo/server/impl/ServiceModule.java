package org.demo.guicedemo.server.impl;

import javax.inject.Singleton;

import org.demo.guicedemo.server.OrderService;
import org.demo.guicedemo.server.PaymentService;
import org.demo.guicedemo.server.PriceService;

import com.google.common.cache.Cache;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.matcher.Matchers;

public class ServiceModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new ChinaModule());
		install(new GlobalModule());
		bind(OrderService.class).to(OrderServiceImpl.class);
		bind(PriceService.class).to(PriceServiceImpl.class);
		bind(PaymentService.class).to(PaymentServiceImpl.class);
		
		
//		bind(new TypeLiteral<Cache<String, String>>(){})
//			.to(GuiceDemoCache.class).in(Singleton.class);
		
		LoggingInterceptor loggingInterceptor = new LoggingInterceptor();
		requestInjection(loggingInterceptor);
		bindInterceptor(Matchers.any(), 
				Matchers.annotatedWith(Logged.class), 
				loggingInterceptor);
	}

	@Provides @SessionId Long generateSessionId() {
		return System.currentTimeMillis();
	}
	
	/*
	 * @Provides List<String> getSupportedCurrencies( PriceService priceService) {
	 * return priceService.getSupportedCurrencies(); }
	 */
	
	
	
	  @Provides @Singleton Cache<String, String> getCache() { return new
	  GuiceDemoCache(); }
	 
}
