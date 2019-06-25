# 绑定

### 类名绑定 
bind(OrderService.class).to(OrderServiceImpl.class);

### 实例绑定
bind(OrderService.class).toInstance(new OrderServiceImpl());

### 连接绑定
bind(OrderService.class).to(OrderServiceImpl.class);
bind(OrderServiceImpl.class).toInstance(new OrderServiceImpl(){
	@Override // 还可以换掉绑定对象
	public long getPrice() {
		return 456L;
	}
});

### Provider绑定
@Provides @SessionId Long generateSessionId() {
		return System.currentTimeMillis();
	}
// 这种方式可以带参数	
@Provides List<String> getSupportedCurrencies(
		PriceService priceService) {
	return priceService.getSupportedCurrencies();
}
// 比较复杂时可以自己实现一个provider
bind(PaymentService.class).toProvider(PaymentServiceProvider.class);

### 命名绑定
@Provides @Named("supportedCurrencies") List<String> getSupportedCurrencies(
		PriceService priceService) {
	return priceService.getSupportedCurrencies();
}

命名绑定也可以写在module里面，但是这样每次拿到的就是同一个值
bind(Long.class).annotatedWith(SessionId.class)
	.toInstance(System.currentTimeMillis());
bind(Long.class).annotatedWith(Names.named("appId"))
	.toInstance(456L);

### 泛型绑定
上面的Provider绑定里已经出现过，@Provides List<String> getSupportedCurrencies
但是那是写在Provider函数里，也可以写在module里
bind(new TypeLiteral<List<String>>(){})
			.annotatedWith(Names.named("supportedCurrencies"))
			.toInstance(Arrays.asList("CNY", "EUR", "USD"));

### 集合绑定
不同的module向同一个集合绑定
Multibinder.newSetBinder(binder(), String.class)
	.addBinding().toInstance("CNY");
Multibinder<String> currencyBinder = 
			Multibinder.newSetBinder(binder(), String.class);
		currencyBinder.addBinding().toInstance("EUR");
		currencyBinder.addBinding().toInstance("USD");
		
# Module之间的关系

### 并列
Guice.createInjector(module1, module2, ...)

### 嵌套
install(module)

### 覆盖
Modules.override(module1).with(module2)

Guice.createInjector(Modules.override(new ServiceModule())
	.with(new AbstractModule() {
		@Override
		protected void configure() {
			bind(PriceService.class)
				.to(PriceServiceMock.class);
		}
	}))
.injectMembers(this);

override和with里的module可以是多个，只有具体的bind语句冲突才会覆盖，而不是module直接覆盖

# Guice的启动 系统初始化
Injector injector = Guice.createInjector(new ServerModule());
OrderService orderService = injector.getInstance(OrderService.class);

### module何时被运行？
Module里存放了很多表达式
Module不被运行
Guice.createInjector()时记录所有表达式

### 系统何时初始化？
没有初始化概念，没有Spring的Configuration Time
injector.getInstance()时根据表达式调用构造函数

# 作用域

### 什么都不做，默认是不同的

### singleton 方式
类上面加注解@Singleton
@Provides 方式提供
Module里用in绑定
singleton的对象要保证线程安全性

		