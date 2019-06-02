package org.demo.guicedemo.server.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.inject.Singleton;

import com.google.common.cache.AbstractCache;

@Singleton
public class GuiceDemoCache extends AbstractCache<String, String> {
	
	// singleton的对象需要保证线程安全，所以使用这个map
	private final Map<String, String> keyValues =
			new ConcurrentHashMap<>();

	@Override
	public String getIfPresent(Object key) {
		return keyValues.get(key);
	}

	@Override
	public void put(String key, String value) {
		keyValues.put(key, value);
	}
}
