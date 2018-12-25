##配置Spring MVC
1. 在pom文件中引入servlet和spring mvc的依赖
2. 创建web文件'
3. '


 mvc 设计模式|思想


- spring mvc 和struts2的比较
- 

	实现机制：
	
	struts2是基于过滤器实现的。
	
	springmvc是基于servlet实现的。

	运行速度：
	
	因为过滤器底层是servlet,所以springmvc的运行速度会稍微比structs2快。
	
	struts2是多例的           
	
	springmvc单例的    
	
	参数封装：
	
	struts2参数封装是基于属性进行封装。
	
	springmvc是基于方法封装。颗粒度更细。
- spring mvc 工作原理图
- spring mvc具体流程步骤
- 
	⑴ 用户发送请求至DispatcherServlet。
	
	⑵ DispatcherServlet收到请求调用HandlerMapping查询具体的Handler。
	
	⑶ HandlerMapping找到具体的处理器(具体配置的是哪个处理器的实现类)，生成处理器对象及处理器拦截器(HandlerExcutorChain包含了Handler以及拦截器集合)返回给DispatcherServlet。
	
	⑷ DispatcherServlet接收到HandlerMapping返回的HandlerExcutorChain后，调用HandlerAdapter请求执行具体的Handler(Controller)。
	
	⑸ HandlerAdapter经过适配调用具体的Handler(Controller即后端控制器)。
	
	⑹ Controller执行完成返回ModelAndView(其中包含逻辑视图和数据)给HandlerAdaptor。
	
	⑺ HandlerAdaptor再将ModelAndView返回给DispatcherServlet。
	
	⑻ DispatcherServlet请求视图解析器ViewReslover解析ModelAndView。
	
	⑼ ViewReslover解析后返回具体View(物理视图)到DispatcherServlet。
	
	⑽ DispatcherServlet请求渲染视图(即将模型数据填充至视图中) 根据View进行渲染视图。
	
	⑾ 将渲染后的视图返回给DispatcherServlet。
	
	⑿ DispatcherServlet将响应结果返回给用户。
	
- spring mvc 核心组件
- 
	⑴ 用户发送请求至DispatcherServlet。
	
	⑵ DispatcherServlet收到请求调用HandlerMapping查询具体的Handler。
	
	⑶ HandlerMapping找到具体的处理器(具体配置的是哪个处理器的实现类)，生成处理器对象及处理器拦截器(HandlerExcutorChain包含了Handler以及拦截器集合)返回给DispatcherServlet。
	
	⑷ DispatcherServlet接收到HandlerMapping返回的HandlerExcutorChain后，调用HandlerAdapter请求执行具体的Handler(Controller)。
	
	⑸ HandlerAdapter经过适配调用具体的Handler(Controller即后端控制器)。
	
	⑹ Controller执行完成返回ModelAndView(其中包含逻辑视图和数据)给HandlerAdaptor。
	
	⑺ HandlerAdaptor再将ModelAndView返回给DispatcherServlet。
	
	⑻ DispatcherServlet请求视图解析器ViewReslover解析ModelAndView。
	
	⑼ ViewReslover解析后返回具体View(物理视图)到DispatcherServlet。
	
	⑽ DispatcherServlet请求渲染视图(即将模型数据填充至视图中) 根据View进行渲染视图。
	
	⑾ 将渲染后的视图返回给DispatcherServlet。
	
	⑿ DispatcherServlet将响应结果返回给用户。
	
- 配置解析
- 常用注解
- DispatcherServlet
- 
	应用了"Front Controller"模式，是所有spring mvc 请求的中枢
	继承自HttpServlet,是一个Servlet，由WebApplicationContext加载
	如果不做其他配置，会加载默认组件

- HandlerMapping
-  
	将web请求映射到正确的处理器(handler)上，通常是一个Controller
	不需要自定义处理器映射，已经内置了很多处理器映射策略
	在处理器映射中通过配置拦截器(包括处理器执行前、后、或者执行前后运行拦截器)将使其功能更强大
	BeanNameUrlHandlerMapping
	SimpleUrlHandlerMapping
- Controller控制器
- Interceptors拦截器
- 九大组件
- 
	protected void initStrategies(ApplicationContext context) {
	//用于处理上传请求。处理方法是将普通的request包装成MultipartHttpServletRequest，后者可以直接调用getFile方法获取File.
	initMultipartResolver(context);
	
	//SpringMVC主要有两个地方用到了Locale：一是ViewResolver视图解析的时候；二是用到国际化资源或者主题的时候。
	initLocaleResolver(context); 
	
	//用于解析主题。SpringMVC中一个主题对应一个properties文件，里面存放着跟当前主题相关的所有资源、
	//如图片、css样式等。SpringMVC的主题也支持国际化， 
	initThemeResolver(context);
	
	//用来查找Handler的。
	initHandlerMappings(context);
	
	//从名字上看，它就是一个适配器。Servlet需要的处理方法的结构却是固定的，都是以request和response为参数的方法。
	//如何让固定的Servlet处理方法调用灵活的Handler来进行处理呢？这就是HandlerAdapter要做的事情
	initHandlerAdapters(context);
	
	//其它组件都是用来干活的。在干活的过程中难免会出现问题，出问题后怎么办呢？
	//这就需要有一个专门的角色对异常情况进行处理，在SpringMVC中就是HandlerExceptionResolver。
	initHandlerExceptionResolvers(context);
	
	//有的Handler处理完后并没有设置View也没有设置ViewName，这时就需要从request获取ViewName了，
	//如何从request中获取ViewName就是RequestToViewNameTranslator要做的事情了。
	initRequestToViewNameTranslator(context);
	
	//ViewResolver用来将String类型的视图名和Locale解析为View类型的视图。
	//View是用来渲染页面的，也就是将程序返回的参数填入模板里，生成html（也可能是其它类型）文件。
	initViewResolvers(context);
	
	//用来管理FlashMap的，FlashMap主要用在redirect重定向中传递参数。
	initFlashMapManager(context); 
	}


url:http://www.sohu.com/a/279548742_100123073
源码：https://baijiahao.baidu.com/s?id=1565723643035966&wfr=spider&for=pc



###FrameworkServlet --> initWebApplicationContext()

初始化WebApplicationContext
- 
	- 获取spring的根容器rootContext
	- 设置webApplicationContext 并根据情况调用onRefresh方法
	- 将webApplicationContext 设置到ServletContext

	configureAndRefreshWebApplicationContext（）
	createWebApplicationContext(ApplicationContext parent)

###HttpServletBean --> init()

	将Servlet中配置的参数设置到相应的属性
	
	- 将servlet中配置参数使用**BeanWrapper**设置DispatcherServlet的相关属性
	- 提供模板方法initBeanWrapper()，子类可通过该方法初始化

###DispatcherServlet --> onRefresh()

初始化了自身的9个组件

- initStrategies()初始化9个组件
- 
	this.initMultipartResolver(context);
	this.initLocaleResolver(context);
	this.initThemeResolver(context);
	this.initHandlerMappings(context);
	this.initHandlerAdapters(context);
	this.initHandlerExceptionResolvers(context);
	this.initRequestToViewNameTranslator(context);
	this.initViewResolvers(context);
	this.initFlashMapManager(context);