# Spring MVC分享(1)

###模块一、Spring MVC 配置文件

###模块二、Spring MVC 自身的创建过程

---

提起Spring MVC 脑子里出现的就是这张图，这是Spring MVC的核心架构图，这次分享，我们将从创建项目到项目运行来详细解释我们开发时配置的信息是怎么运行成程序并可以成功请求。
![](http://5b0988e595225.cdn.sohucs.com/images/20180712/90ea82ce7e544363be5b17671725f10e.jpeg)

首先我们要创建一个简单的Spring MVC的demo，我们首先从配置文件讲起

# 1、配置文件

一个简单的Sping MVC项目中用到的配置文件主要有**pom.xml、web.xml、*-servlet.xml** 这三类

- pom.xml
- 
> 在pom文件中引入servlet和spring mvc的依赖
>
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


- web.xml
- 
> web.xml文件是用来初始化配置信息：比如Welcome页面、servlet、servlet-mapping、filter、listener、启动加载级别等。
> 在配置文件中我们可以指定SpringMVC的入口程序DispatcherServlet，同时指定了一个或多个核心配置文件（[servlet-name]-servlet.xml）。Spring官方文档上推荐的默认的文件名是[servlet-name]-servlet.xml文件。
> 
> **SpringMVC配置文件的解析就是在DispatcherServlet初始化时进行的** DispatcherServlet是继承自FrameworkServlet，而FrameworkServlet又继承自HttpServletBean，init()方法，就是在HttpServletBean.java中执行的。这个我们在后面会提到。
> 
	<!-- 自动启动，mapping 所有的请求 spring mvc 的入口-->
	<!-- servlet 配置区域 -->
	<servlet>
		<servlet-name>dispatcher</servlet-name>
		<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
	<init-param>
		<param-name>contextConfigLocation</param-name>
		<param-value>WEB-INF/dispatcher-servlet.xml</param-value>
	</init-param>
	<load-on-startup>1</load-on-startup>
	</servlet>
		<servlet-mapping>
		<servlet-name>dispatcher</servlet-name>
		<url-pattern>/</url-pattern>
	</servlet-mapping>
除了配置sevlet外，我们还可以配置过滤，监听等
>
	<!--设置过滤器：比如设置一个编码过滤器，过滤所有资源-->
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
	<!--设置监听器-->
	<listener>
	    <listener-class>org.apache.tiles.extras.complete.CompleteAutoloadTilesListener</listener-class>
	</listener>

- *-servlet.xml
- 
> 在DispatcherServlet初始化时，会根据配置的参数获取相应的配置文件并解析。DispatcherServlet通过configureAndRefreshWebApplicationContext配置文件解析，这个在后面会讲到，这儿先一笔带过。
> 
> 在这个配置文件中可以配置相应的映射器和解析器


- **配置文件详解**
- 
>在每个配置文件头部中我们可以看到这样一坨的url,这一坨url是命名空间声明和协议位置属性指定
>
		xmlns="http://www.springframework.org/schema/beans"
		xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
		xmlns:p="http://www.springframework.org/schema/p"
		xmlns:context="http://www.springframework.org/schema/context"
		xmlns:aop="http://www.springframework.org/schema/aop"
		xmlns:tx="http://www.springframework.org/schema/tx"
		xsi:schemaLocation="http://www.springframework.org/schema/beans
		http://www.springframework.org/schema/beans/spring-beans-4.3.xsd
		http://www.springframework.org/schema/context
		http://www.springframework.org/schema/context/spring-context-4.3.xsd
		http://www.springframework.org/schema/aop
		http://www.springframework.org/schema/aop/spring-aop-4.3.xsd
		http://www.springframework.org/schema/tx
		http://www.springframework.org/schema/tx/spring-tx-4.3.xsd"

>官方说明：
>
>XML Schema语言也就是XSD。XML Schema描述了XML文档的结构。 
可以用一个指定的XML Schema来验证某个XML文档，以检查该XML文档是否符合其要求。文档设计者可以通过XML Schema指定一个XML文档所允许的结构和内容，并可据此检查一个XML文档是否是有效的。XML Schema本身是一个XML文档，它符合XML语法结构。可以用通用的XML解析器解析它。
一个XML Schema会定义：文档中出现的元素、文档中出现的属性、子元素、子元素的数量、子元素的顺序、元素是否为空、元素和属性的数据类型、元素或属性的默认 和固定值。
那解析配置文件，系统是怎么根据标签元素查询到命名空间的？sax或者dom解析器首先将xml文件转换成内存中的文档document，这个时候会把标签对应的命名空间解析出来并保存到Node中，后面根据node.getNamespaceURI()就可以获取标签对应的命名空间。
>#####xmlns 是xml 命名空间(xml name space)， 用来标识唯一的文件，防止重复或者指向不清晰，命名空间的声明语法是，xmlns : 别名 = namespace的统一资源标识符(URI)：
>
	xmlns:namespace-aliases="namespaceURI"
>
>例如上述命名空间声明中:
>
		<!-- 没有别名，是默认的命名空间-->
		xmlns="http://www.springframework.org/schema/beans"
		xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
		<!-- 别名是context-->
		xmlns:context="http://www.springframework.org/schema/context"
>所以别名只作为该命名空间的标识，是可以自定义， 例如：
>
	xmlns:ttt="http://www.springframework.org/schema/context"

>相应的当使用该命名空间时也要进行对应的更改：
>
	<!--包扫描-->
	<ttt:component-scan base-package="com.calm.login.dao"/>

>####xmlns:xsi
>大家注意到下面的两条有点不同
>
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
>
	xsi:schemaLocation="http://www.springframework.org/schema/beans
	http://www.springframework.org/schema/beans/spring-beans-4.3.xsd
	http://www.springframework.org/schema/context
	http://www.springframework.org/schema/context/spring-context-4.3.xsd"
>
xsi也是一个别名，只是这个别名约定俗成，语意性强，大家容易看懂。是（xml schema instance）的缩写，指定schema资源文件里定义的元素所准守的标准和规范。所以xsi需要明确通过属性schemaLocation来指定这些xml协议定义的具体文件。
>
	xsi:schemaLocation="http://www.springframework.org/schema/beans
	http://www.springframework.org/schema/beans/spring-beans-4.3.xsd"

>**xsi:schemaLocation**指定协议具体的路径，因为一个命名空间下还会存在多个版本的xsd(Xml Schema Definition 协议定义)文件

上述是Demo的配置文件，至此完成简单的配置工作，在项目里增加Controller，page即可通过URL访问成功，那么项目是如何编译运行让url可以解析到类的，之前那说的Dispathcher如何初始化，怎样解析文件？下面我们继续

# 2、Spring MVC自身的创建过程

刚刚说到DispatcherServlet是SpringMVC的入口程序，先看一下这个类的的关系图

