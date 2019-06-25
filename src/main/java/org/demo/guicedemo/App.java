package org.demo.guicedemo;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class App {
	/**
	 * bootstrap:
	 * parse command line
	 * set up environment
	 * kick off main logic
	 * @param args
	 */
	public static void main(String[] args) {
		
		Injector injector = Guice.createInjector(
				new MainModule(),
				new CommandLineModule(args));
		
		Applets.get(injector, args[0]).run();
		
//		Map<String, MyApplet> applets = 
//		Guice.createInjector(
//			new MainModule(),
//			new CommandLineModule(args))
//			.getInstance(Key.get(new TypeLiteral<
//					Map<String, MyApplet>>(){}));
//		if (args.length == 0 || !applets.containsKey(args[0])) {
//			System.out.println("Unable to find applet. "
//					+ "Valid applets are "
//					+ Joiner.on(", ").join(applets.keySet()));
//			return;
//		}
//		applets.get(args[0]).run();
		
//		根据命令行传入的名字使用不同的实现
//		MyApplet mainApplet = 
//				Guice.createInjector(
//						new MainModule(),
//						new CommandLineModule(args))
//						.getInstance(Key.get(
//								MyApplet.class, 
//								Names.named(args[0])));
//		mainApplet.run();
	}

}
