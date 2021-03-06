package org.demo.guicedemo.server.impl;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;

public class ChinaModule extends AbstractModule {

	@Override
	protected void configure() {
		// Adds CNY supported
		Multibinder.newSetBinder(binder(), String.class)
			.addBinding().toInstance("CNY");
		// MapBinder.newMapBinder(binder, keyType, valueType)
	}

}
