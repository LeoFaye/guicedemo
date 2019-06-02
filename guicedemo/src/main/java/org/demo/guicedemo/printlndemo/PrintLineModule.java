package org.demo.guicedemo.printlndemo;

import org.demo.guicedemo.Applets;
import org.demo.guicedemo.helloworlddemo.StringWritingApplet;

import com.google.inject.AbstractModule;

public class PrintLineModule extends AbstractModule {

	@Override
	protected void configure() {
		// 包装成不会Guice的人也能看得懂的方式
		Applets.register(binder()).named("println")
		.to(StringWritingApplet.class);
		
//		MapBinder.newMapBinder(
//				binder(), String.class, MyApplet.class)
//			.addBinding("println").to(PrintLineApplet.class);
		
//		bind(MyApplet.class).annotatedWith(Names.named("println"))
//			.to(PrintLineApplet.class);
	}

}
