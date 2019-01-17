启动配置中加入-XX:+TraceClassLoading参数可以跟踪显示类加载机制

ClassLoader的loadClass()加载
1. 同一时间同一个类,只允许一个线程来加载
2. 只有没有被加载过的类,才可以被加载

双亲委派原则: