#Spring MVC 


## 1、配置文件
---
SpringMVC的核心配置文件是构成SpringMVC应用程序的必要元素之一。
SpringMVC的核心配置文件是架起DispatcherServlet与WebApplicationContext之间的桥梁。
SpringMVC的核心配置文件是SpringMVC中所有组件的定义窗口，通过它我们可以指定整个SpringMVC的行为方式。


- **pom.xml**

> 在pom文件中引入servlet和spring mvc的依赖

    <!--spring mvc 引入-->
    <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>javax.servlet-api</artifactId>
      <version>3.0.1</version>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-webmvc</artifactId>
      <version>3.2.17.RELEASE</version>
    </dependency>
 ![](https://github.com/Mr-seventeen/deam-springmvc/blob/master/projectFile/img/pom1-img.png)



- **web.xml**
> web.xml文件是用来初始化配置信息：比如Welcome页面、servlet、servlet-mapping、filter、listener、启动加载级别等。
> 
> web.xml中指定SpringMVC的入口程序DispatcherServlet，同时指定了一个或多个核心配置文件（[servlet-name]-servlet.xml）。Spring官方文档上推荐的默认的文件名是[servlet-name]-servlet.xml文件
> 当然，我们也可以明确指定这个核心配置文件的位置。DispatcherServlet负责对WebApplicationContext进行初始化，而初始化的依据，就是这个SpringMVC的核心配置文件。所以，SpringMVC的核心配置文件的内容解读将揭开整个SpringMVC初始化主线的全部秘密。
> 
>
> 
> 每个xml文件都有定义它书写规则的Schema文件。web.xml的模式文件是由Sun 公司定义的，每个web.xml文件的根元素为<web-app>中，必须标明这个web.xml使用的是哪个模式文件。

IMG插入 web.xml图片

1、命名与定制URL。我们可以为Servlet和JSP文件命名并定制URL,其中定制URL是依赖命名的，命名必须在定制URL前。下面拿serlet来举例：
 
    <!-- 自动启动，mapping 所有的请求 spring mvc 的入口-->
    <!-- 为Servlet命名 -->
    <servlet>
    	<servlet-name>dispatcher</servlet-name>
    	<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    	<init-param>
    	  <param-name>contextConfigLocation</param-name>
    	  <param-value>WEB-INF/dispatcher-servlet.xml</param-value>
    	</init-param>
    	<load-on-startup>1</load-on-startup>
    </servlet>
    <!-- 为Servlet定制URL -->
    <servlet-mapping>
    	<servlet-name>dispatcher</servlet-name>
    	<url-pattern>/</url-pattern>
    </servlet-mapping>


	<context-param>
		<param-name>contextConfigLocation</param-name>
		<!-- <param-value>classpath*:config/applicationContext.xml</param-value> -->
		<param-value>/WEB-INF/applicationContext*.xml</param-value>
	</context-param>

2、设置过滤器：比如设置一个编码过滤器，过滤所有资源 

    <filter>
    	<filter-name>encodingFilter</filter-name>
    	<filter-class>com.boloni.framework.web.filter.SetCharacterEncodingFilter</filter-class>
    	<init-param>
    		<param-name>encoding</param-name>
    		<param-value>UTF-8</param-value>
    	</init-param>
    </filter>
    <filter-mapping>
    	<filter-name>encodingFilter</filter-name>
    	<url-pattern>/*</url-pattern>
    </filter-mapping>

3、设置监听器

	<listener>
	    <listener-class>org.apache.tiles.extras.complete.CompleteAutoloadTilesListener</listener-class>
	</listener>	

- ***-servlet.xml**

>  在这里完成bean的配置， 这些bean将由Spring容器管理。

    <!--spring mvc 提供的一键式配置方法，此标签会让spring mvc 帮我们自动做一些注册组件 -->
    <!--扫描对应的控制器-->
    <context:component-scan base-package="com.example"/>
    <!--开启注解驱动，简化配置-->
    <!-- 添加后将不会全部使用默认配置 AnnotationDrivenBeanDefinitionParser -->
    <mvc:annotation-driven/>

- **配置文件详解**
 
1、命名空间声明

       xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
       xmlns:p="http://www.springframework.org/schema/p"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:tx="http://www.springframework.org/schema/tx"
2、协议位置属性指定

		xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans-4.3.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context-4.3.xsd
        http://www.springframework.org/schema/aop
        http://www.springframework.org/schema/aop/spring-aop-4.3.xsd
        http://www.springframework.org/schema/tx
        http://www.springframework.org/schema/tx/spring-tx-4.3.xsd"

#####xmlns 是xml 命名空间(xml name space)， 用来标识唯一的文件，防止重复或者指向不清晰，命名空间的声明语法是，xmlns : 别名 = namespace的统一资源标识符(URI)：

	xmlns:namespace-aliases="namespaceURI"

例如上述命名空间声明中:

		<!-- 没有别名，是默认的命名空间-->
		xmlns="http://www.springframework.org/schema/beans"
		xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
		<!-- 别名是context-->
		xmlns:context="http://www.springframework.org/schema/context"
所以别名只作为该命名空间的标识，是可以自定义， 例如：

	xmlns:ttt="http://www.springframework.org/schema/context"

相应的当使用该命名空间时也要进行对应的更改：

	<!--包扫描-->
	<ttt:component-scan base-package="com.calm.login.dao"/>

####xmlns:xsi
大家注意到下面的两条有点不同

	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 

	xsi:schemaLocation="http://www.springframework.org/schema/beans
	http://www.springframework.org/schema/beans/spring-beans-4.3.xsd
	http://www.springframework.org/schema/context
	http://www.springframework.org/schema/context/spring-context-4.3.xsd"

xsi也是一个别名，只是这个别名约定俗成，语意性强，大家容易看懂。是（xml schema instance）的缩写，指定schema资源文件里定义的元素所准守的标准和规范。所以xsi需要明确通过属性schemaLocation来指定这些xml协议定义的具体文件。

	xsi:schemaLocation="http://www.springframework.org/schema/beans
	http://www.springframework.org/schema/beans/spring-beans-4.3.xsd"

**xsi:schemaLocation**指定协议具体的路径，因为一个命名空间下还会存在多个版本的xsd(Xml Schema Definition 协议定义)文件的需要进行具体的指定如：

IMG插入 servlet.xml图片

>#注解和配置文件的区别：

>在Spring MVC中涉及到的处理器映射器和处理器适配器分为注解处理器映射器和处理器适配器与非注解处理器映射器和处理器适配器，

>> 注意：如果设置metadata-complete="true"，会在启动时不扫描注解（annotation）。如果不扫描注解的话，用注解进行的配置就无法生效，例如：@WebServlet

>非注解映射器和非注解适配器：
>
	<bean id="urlMapping"
		class="org.springframework.web.servlet.handler.SimpleUrlHandlerMapping">
		<property name="interceptors">
			<list>
				<bean class="com.boloni.framework.web.request.RequestTokenInterceptor" />
				<bean class="com.boloni.framework.web.view.ViewModelRenderInterceptor" />
			</list>
		</property>
		<property name="mappings">
			<props>
				<!-- Log manager -->
				<prop key="/admin/log/loggers">logMiscController</prop>
				<prop key="/admin/log/logFile">logMiscController</prop>
				<prop key="/changeLoggerLevel">logMiscController</prop>
			</props>
    	</property>
	</bean>
>注解映射器和注解适配器
>
	注解映射器:org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping 
	注解适配器:org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter
>
>> 使用<mvc:annotation-driven/>代替上面的注解映射器和注解适配器配置，<mvc:annotation-driven/>默认加载很多的参数绑定方法，比如json转换，使用<mvc:annotation-driven/>就可以不用配置上面两个了
>> 如果controller很多，我们可以用component-scan来扫描


---
---
至此完成简单的配置工作，在项目里增加Controller，page即可通过URL访问成功，那么项目是如何编译运行让url可以解析到类的，这个其实之前已经简单的提过了，我们随后做更详细的描述：

# 2、URL访问类的路径，即spring mvc核心，Spring MVC自身的创建过程
---
如下图所示，项目在启动编译的时候根据我们之前配置的xml文件解析
> 此处图片一张，画出项目启动访问的类及类的方法

> 此处图片一张，DispatcherServlet类图
> 可以看到在Servlet的继承结构中有一共有5个类，GenericServlet和HttpServlet在java中，剩下的三个类HttpServletBean、FrameworkServlet和DispatcherServlet是Spring MVC中的，这三个类直接实现三个接口：EnvironmentCapable、EnvironmentAware和ApplicationContextAware。
> 
> **XXXWare**在Spring里表示对XXX可以感知，通俗点解释就是：如果在某个类里面想要使用spring的一些东西，就可以通过实现XXXWare接口告诉Spring，Spring看到后就会给你送过来，而接收的方式是通过实现接口唯一的方法set-XXX。
> 
> **EnvironmentCapable**，顾名思义，当然就是具有Environment的能力，也就是提供Environment，所以EnvironmentCapable唯一的方法是Environment getEnvironment()，用于实现EnvironmentCapable接口的类，就是告诉Spring它可以提供Environment，当Spring需要Environment的时候就会调用其getEnvironment方法跟它要。
断点调试Environment,截图显示


1. **HttpServletBean**

> 可以看到，在HttpServletBean的init中，首先将Servlet中配置对参数使用BeanWrapper设置到DispatcherServlet的相关属性，然后调用模版方法initServletBean，子类就通过这个方法初始化。


	    public abstract class HttpServletBean extends HttpServlet implements EnvironmentCapable, EnvironmentAware {
    	...
	    	@Override
	    	public final void init() throws ServletException {
		    	if (logger.isDebugEnabled()) {
		    		logger.debug("Initializing servlet '" + getServletName() + "'");
		    	}
		    	
		    	// Set bean properties from init parameters.
		    	try {
			    	//将Servlet中配置的参数封装到pvs变量中，requiredProperties为必需参数，如果没有配置将报异常
			    	PropertyValues pvs = new ServletConfigPropertyValues(getServletConfig(), this.requiredProperties);
			    	BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(this);
			    	ResourceLoader resourceLoader = new ServletContextResourceLoader(getServletContext());
			    	bw.registerCustomEditor(Resource.class, new ResourceEditor(resourceLoader, getEnvironment()));
			    	//模版方法，可以在子类调用，做一些初始化工作。bw代表DispatcherServlet
			    	initBeanWrapper(bw);
			    	//将配置对初始化值（如contextConfigLocation）设置DispatcherServlet
			    	bw.setPropertyValues(pvs, true);
		    	}
		    	catch (BeansException ex) {
			    	if (logger.isErrorEnabled()) {
			    		logger.error("Failed to set bean properties on servlet '" + getServletName() + "'", ex);
			    	}
			    	throw ex;
		    	}
		    	
		    	// Let subclasses do whatever initialization they like.
		    	//模版方法，子类初始化对入口方法
		    	initServletBean();
		    	
		    	if (logger.isDebugEnabled()) {
		    		logger.debug("Servlet '" + getServletName() + "' configured successfully");
		    	}
	    	}
    	...
    	}

？？BeanWrapper是什么，怎么用


2. **FrameworkServlet**

>可以看到这里的核心代码只有两句：一句用于初始化WebApplicationContext，另一句用于初始化FrameworkServlet，而且initFrameworkServlet方法是模版方法，子类可以覆盖然后在里面做一些初始化的工作，但子类并没有使用它。



		public abstract class FrameworkServlet extends HttpServletBean implements ApplicationContextAware {
		    ...
		    @Override
		    protected final void initServletBean() throws ServletException {
		        getServletContext().log("Initializing Spring FrameworkServlet '" + getServletName() + "'");
		        if (this.logger.isInfoEnabled()) {
		            this.logger.info("FrameworkServlet '" + getServletName() + "': initialization started");
		        }
		        long startTime = System.currentTimeMillis();
		
		        try {
		            this.webApplicationContext = initWebApplicationContext();
		            initFrameworkServlet();
		        }
		        catch (ServletException ex) {
		            this.logger.error("Context initialization failed", ex);
		            throw ex;
		        }
		        catch (RuntimeException ex) {
		            this.logger.error("Context initialization failed", ex);
		            throw ex;
		        }
		
		        if (this.logger.isInfoEnabled()) {
		            long elapsedTime = System.currentTimeMillis() - startTime;
		            this.logger.info("FrameworkServlet '" + getServletName() + "': initialization completed in " +
		                    elapsedTime + " ms");
		        }
		    }
		    ...
		}
		
>可见FrameworkServlet在构建的过程中到主要作用就是初始化了WebApplication。下面来看一下initWebApplicationContext方法。


		protected WebApplicationContext initWebApplicationContext() {
		    //获取rootContext
		    WebApplicationContext rootContext =
		            WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		    WebApplicationContext wac = null;
		    //如果已经通过构造方法设置了WebApplicationContext
		    if (this.webApplicationContext != null) {
		        // A context instance was injected at construction time -> use it
		        wac = this.webApplicationContext;
		        if (wac instanceof ConfigurableWebApplicationContext) {
		            ConfigurableWebApplicationContext cwac = (ConfigurableWebApplicationContext) wac;
		            if (!cwac.isActive()) {
		                // The context has not yet been refreshed -> provide services such as
		                // setting the parent context, setting the application context id, etc
		                if (cwac.getParent() == null) {
		                    // The context instance was injected without an explicit parent -> set
		                    // the root application context (if any; may be null) as the parent
		                    cwac.setParent(rootContext);
		                }
		                configureAndRefreshWebApplicationContext(cwac);
		            }
		        }
		    }
		    if (wac == null) {
		        // No context instance was injected at construction time -> see if one
		        // has been registered in the servlet context. If one exists, it is assumed
		        // that the parent context (if any) has already been set and that the
		        // user has performed any initialization such as setting the context id
		        //当webApplicationContext已经存在ServletContext中时，通过配置在servlet中的contextAttribute参数获取
		        wac = findWebApplicationContext();
		    }
		    if (wac == null) {
		        // No context instance is defined for this servlet -> create a local one
		        //如果webApplicationContext还没有创建，则创建一个
		        wac = createWebApplicationContext(rootContext);
		    }
		
		    if (!this.refreshEventReceived) {
		        // Either the context is not a ConfigurableApplicationContext with refresh
		        // support or the context injected at construction time had already been
		        // refreshed -> trigger initial onRefresh manually here.
		        //当ContextRefresh事件没有触发时调用此方法，模版方法，可以在子类重写
		        onRefresh(wac);
		    }
		
		    if (this.publishContext) {
		        // Publish the context as a servlet context attribute.
		        String attrName = getServletContextAttributeName();
		        getServletContext().setAttribute(attrName, wac);
		        if (this.logger.isDebugEnabled()) {
		            this.logger.debug("Published WebApplicationContext of servlet '" + getServletName() +
		                    "' as ServletContext attribute with name [" + attrName + "]");
		        }
		    }
		
		    return wac;
		}


initWebApplicationContext方法做了三件事：

- 获取spring的根容器rootContext。
- 设置webApplication并根据情况调用onRefresh方法。
- 将webApplicationContext设置到ServletContext中。


>将webApplicationContext设置到ServletContext中
 最后会根据publishContext标志判断是否将创建出来的webApplicationContext设置到ServletContext的属性中，publishContext标志可以在配置Servlet时通过init-param参数进行设置，HttpServletBean初始化时会将其设置到publishContext参数。之所以将创建出来的webApplicationContext设置到ServletContext的属性中，主要是为了方便获取，在前面获取RootApplicationContext的时候已经介绍过。
 前面介绍了配置Servlet时可以设置的一些初始化参数，总结如下：


> contextAttribute：在ServletContext的属性中，要用作WebApplicationContext的属性名称。
> 
> contextClass：创建WebApplicationContext的类型。
>
>contextConfigLocation：Spring MVC配置文件的位置。
>
>publishContext：是否将webApplicationContext设置到ServletContext的属性。




3. **DispatcherServlet**

>onRefresh方法是DispatcherServlet的入口方法。onRefresh中简单地调用了initStrategies，在initStrategies中调用了9个初始化方法：
	
	public class DispatcherServlet extends FrameworkServlet {
	    ...
	    /**
	     * This implementation calls {@link #initStrategies}.
	     */
	    @Override
	    protected void onRefresh(ApplicationContext context) {
	        initStrategies(context);
	    }
	
	    /**
	     * Initialize the strategy objects that this servlet uses.
	     * <p>May be overridden in subclasses in order to initialize further strategy objects.
	     */
	    protected void initStrategies(ApplicationContext context) {
	        initMultipartResolver(context);
	        initLocaleResolver(context);
	        initThemeResolver(context);
	        initHandlerMappings(context);
	        initHandlerAdapters(context);
	        initHandlerExceptionResolvers(context);
	        initRequestToViewNameTranslator(context);
	        initViewResolvers(context);
	        initFlashMapManager(context);
	    }
	    ...
	}


小结
---
----

Spring MVC中Servlet一共三个层次，分别是HttpServletBean、FrameworkServlet和DispatcherServlet。HttpServletBean直接继承自Java的HttpServlet，其作用是将Servlet中配置的参数设置到相应的属性：FrameworkServlet初始化了WebApplicationContext，DispatcherServlet初始化了自身的9个组件。

FrameworkServlet初始化WebApplicationContext一共有三种方式，过程中使用了Servlet中配置的一些参数。
 整体结构非常简单---分三个层次做了三件事，但具体实现过程还是有点复杂的，这其实也是spring的特点：结构简单，实现复杂。结构简单主要是顶层设计好，实现复杂的主要是提供的功能比较多，可配置的地方也非常多。当然，正是因为实现复杂，才让Spring MVC使用起来更加灵活，这一点在后面会有深刻多体会。

