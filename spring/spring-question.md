
## IOC和DI是什么？  
    IOC是控制反转，
        控制反转是就是应用本身不负责依赖对象的创建和维护,依赖对象的创建及维护是由外部容器负责的,
        这样控制权就有应用转移到了外部容器,控制权的转移就是控制反转。
    DI是指依赖注入,
        依赖注入是指:在程序运行期间,由外部容器动态地将依赖对象注入到组件中
        如：一般，通过构造函数注入或者setter注入。
        
---
## Spring IOC 的理解，其初始化过程？
    1. Resource资源定位,这个Resouce指的是BeanDefinition的资源定位,这个过程就是容器找数据的过程。
    2. BeanDefinition的载入过程,这个载入过程是把用户定义好的Bean表示成Ioc容器内部的数据结构，而这个容器内部的数据结构就是BeanDefition。
    3. 向IOC容器注册这些BeanDefinition的过程,这个过程就是将前面的BeanDefition保存到HashMap中的过程。
    
    绝大多数BeanDefinition是由invokeBeanFactoryPostProcessors方法创建并加载到ApplicationContext的DefaultListableBeanFactory中去的,
    然后由registerBeanPostProcessors方法加载创建对象的后处理器BeanPostProcessors。
    BeanDefinitionMap的数据结构是concurrentHashMap, 而存储BeanPostProcessors的数据结构是CopyOnWriteList。
    最后由finishBeanFactoryInitialization方法实例化所以non lazy-init非懒加载的Bean, 
    所有SingletonObject单例对象都缓存在DefaultListableBeanFactory的singletonObjects中，而ProtoType的对象不会被缓存。
    
    上面提到的过程一般是不包括Bean的依赖注入的实现，Bean的载入和依赖注入是两个独立的过程，依赖注入是发生在应用第一次调用getBean向容器所要Bean时。
    
---
## BeanFactory 和 FactoryBean的区别？
    BeanFactory是个Factory，也就是IOC容器或对象工厂，在Spring中，所有的Bean都是由BeanFactory(也就是IOC容器)来进行管理的，提供了实例化对象和拿对象的功能。
    FactoryBean是个Bean，这个Bean不是简单的Bean，而是一个能生产或者修饰对象生成的工厂Bean,它的实现与设计模式中的工厂模式和修饰器模式类似
    
---
## BeanFactory和ApplicationContext的区别？
    BeanFactory
    是Spring里面最低层的接口，提供了最简单的容器的功能，只提供了实例化对象和拿对象的功能。
    
    两者装载bean的区别
    BeanFactory：在启动的时候不会去实例化Bean，中有从容器中拿Bean的时候才会去实例化；
    ApplicationContext：在启动的时候就把所有的Bean全部实例化了。它还可以为Bean配置lazy-init=true来让Bean延迟实例化；
    
    
    BeanFactory 延迟实例化的优点：
    
    应用启动的时候占用资源很少，对资源要求较高的应用，比较有优势；
    
    缺点：速度会相对来说慢一些。而且有可能会出现空指针异常的错误，而且通过bean工厂创建的bean生命周期会简单一些
    
    ApplicationContext 不延迟实例化的优点：
    
    所有的Bean在启动的时候都加载，系统运行的速度快；
    在启动的时候所有的Bean都加载了，我们就能在系统启动的时候，尽早的发现系统中的配置问题
    建议web应用，在启动的时候就把所有的Bean都加载了。
    缺点：把费时的操作放到系统启动中完成，所有的对象都可以预加载，缺点就是消耗服务器的内存
    
    ApplicationContext其他特点
    除了提供BeanFactory所支持的所有功能外，ApplicationContext还有额外的功能
    
    默认初始化所有的Singleton，也可以通过配置取消预初始化。
    继承MessageSource，因此支持国际化。
    资源访问，比如访问URL和文件（ResourceLoader）；
    事件机制，（有继承关系）上下文 ，使得每一个上下文都专注于一个特定的层次，比如应用的web层；
    同时加载多个配置文件。
    消息发送、响应机制（ApplicationEventPublisher）；
    以声明式方式启动并创建Spring容器。
    由于ApplicationContext会预先初始化所有的Singleton Bean，于是在系统创建前期会有较大的系统开销，但一旦ApplicationContext初始化完成，程序后面获取Singleton Bean实例时候将有较好的性能。
    
---
## ApplicationContext 上下文的生命周期？
    1. 实例化一个Bean，也就是我们通常说的new；
    2. 按照Spring上下文对实例化的Bean进行配置，也就是IOC注入
    3. 如果这个Bean实现了BeanNameAware接口，会调用它实现的setBeanName(String beanId)方法，此处传递的是Spring配置文件中Bean的ID；
    4. 如果这个Bean实现了BeanFactoryAware接口，会调用它实现的setBeanFactory()，传递的是Spring工厂本身（可以用这个方法获取到其他Bean）；
    5. 如果这个Bean实现了ApplicationContextAware接口，会调用setApplicationContext(ApplicationContext)方法，传入Spring上下文，该方式同样可以实现步骤4，但比4更好，以为ApplicationContext是BeanFactory的子接口，有更多的实现方法；
    6. 如果这个Bean关联了BeanPostProcessor接口，将会调用postProcessBeforeInitialization(Object obj, String s)方法，BeanPostProcessor经常被用作是Bean内容的更改，并且由于这个是在Bean初始化结束时调用After方法，也可用于内存或缓存技术；
    7. 如果这个Bean在Spring配置文件中配置了init-method属性会自动调用其配置的初始化方法；
    8. 如果这个Bean关联了BeanPostProcessor接口，将会调用postAfterInitialization(Object obj, String s)方法；
    
---
## Spring Bean 的生命周期？
    1. Bean的建立， 由BeanFactory读取Bean定义文件，并生成各个实例;
    2. Setter注入，执行Bean的属性依赖注入;
    3. BeanNameAware的setBeanName(), 如果实现该接口，则执行其setBeanName方法;
    4. BeanFactoryAware的setBeanFactory()，如果实现该接口，则执行其setBeanFactory方法;
    5. BeanPostProcessor的processBeforeInitialization()，如果有关联的processor，则在Bean初始化之前都会执行这个实例的processBeforeInitialization()方法;
    6. InitializingBean的afterPropertiesSet()，如果实现了该接口，则执行其afterPropertiesSet()方法;
    7. Bean定义文件中定义init-method;
    8. BeanPostProcessors的processAfterInitialization()，如果有关联的processor，则在Bean初始化之前都会执行这个实例的processAfterInitialization()方法;
    9. DisposableBean的destroy()，在容器关闭时，如果Bean类实现了该接口，则执行它的destroy()方法;
    10. Bean定义文件中定义destroy-method，在容器关闭时，可以在Bean定义文件中使用“destory-method”定义的方法;

## Spring AOP的实现原理？
    Spring AOP使用的动态代理，所谓的动态代理就是说AOP框架不会去修改字节码，而是在内存中临时为方法生成一个AOP对象，这个AOP对象包含了目标对象的全部方法，并且在特定的切点做了增强处理，并回调原对象的方法。
    
    Spring AOP中的动态代理主要有两种方式，JDK动态代理和CGLIB动态代理。JDK动态代理通过反射来接收被代理的类，并且要求被代理的类必须实现一个接口。JDK动态代理的核心是InvocationHandler接口和Proxy类。
    
    如果目标类没有实现接口，那么Spring AOP会选择使用CGLIB来动态代理目标类。CGLIB（Code Generation Library），是一个代码生成的类库，可以在运行时动态的生成某个类的子类，注意，CGLIB是通过继承的方式做的动态代理，因此如果某个类被标记为final，那么它是无法使用CGLIB做动态代理的。
    
    1）通过AspectJAutoProxyBeanDefinitionParser类将AnnotationAwareAspectJAutoProxyCreator注册到Spring容器中
    2）AnnotationAwareAspectJAutoProxyCreator类的postProcessAfterInitialization()方法将所有有advice的bean重新包装成proxy
    3）调用bean方法时通过proxy来调用，proxy依次调用增强器的相关方法，来实现方法切入
    
    Spring AOP将advise转换成interceptor，执行proxy代理类的invoke方法，过程中会递归调用ReflectiveMethodInvocation的invoke方法来执行interceptor chain。
---
## Spring 是如何管理事务的，事务管理机制？
    编程式事务管理：Spring推荐使用TransactionTemplate，实际开发中使用声明式事务较多。
    声明式事务管理：将我们从复杂的事务处理中解脱出来，获取连接，关闭连接、事务提交、回滚、异常处理等这些操作都不用我们处理了，Spring都会帮我们处理。
    声明式事务管理使用了AOP面向切面编程实现的，本质就是在目标方法执行前后进行拦截。在目标方法执行前加入或创建一个事务，在执行方法执行后，根据实际情况选择提交或是回滚事务。
    
---        
## Spring 的不同事务传播行为有哪些，干什么用的？
    1. ==PROPAGATION_REQUIRED==: 如果存在一个事务，则支持当前事务，如果没有事务则开启。
    2. ==PROPAGATION_REQUIRES_NEW==: 总是开启一个新的事务，如果一个事务已经存在，则将这个存在的事务挂起。
    3. PROPAGATION_SUPPORTS: 如果存在一个事务，支持当前事务。如果没有事务，则非事务的执行
    4. PROPAGATION_MANDATORY: 如果已经存在一个事务，支持当前事务。如果没有一个活动的事务，则抛出异常。
    5. PROPAGATION_NOT_SUPPORTED: 总是非事务地执行，并挂起任何存在的事务。
    6. PROPAGATION_NEVER: 总是非事务地执行，如果存在一个活动事务，则抛出异常
    7. PROPAGATION_NESTED：如果一个活动的事务存在，则运行在一个嵌套的事务中. 如果没有活动事务, 则按TransactionDefinition.PROPAGATION_REQUIRED 属性执行

---
## Spring 中用到了那些设计模式？
    代理模式—在AOP和remoting中被用的比较多。
    单例模式—在spring配置文件中定义的bean默认为单例模式。
    模板方法—用来解决代码重复的问题。比如. RestTemplate, JmsTemplate, JpaTemplate。
    工厂模式—BeanFactory用来创建对象的实例。
    适配器--spring aop
    装饰器--spring data hashmapper
    观察者-- spring 时间驱动模型
    回调--Spring ResourceLoaderAware回调接口
    
---
## Spring MVC 的工作原理？
    1、  用户发送请求至前端控制器DispatcherServlet。
    2、  DispatcherServlet收到请求调用HandlerMapping处理器映射器。
    3、  处理器映射器找到具体的处理器(可以根据xml配置、注解进行查找)，生成处理器对象及处理器拦截器(如果有则生成)一并返回给DispatcherServlet。
    4、  DispatcherServlet调用HandlerAdapter处理器适配器。
    5、  HandlerAdapter经过适配调用具体的处理器(Controller，也叫后端控制器)。
    6、  Controller执行完成返回ModelAndView。
    7、  HandlerAdapter将controller执行结果ModelAndView返回给DispatcherServlet。
    8、  DispatcherServlet将ModelAndView传给ViewReslover视图解析器。
    9、  ViewReslover解析后返回具体View。
    10、DispatcherServlet根据View进行渲染视图（即将模型数据填充至视图中）。
    11、 DispatcherServlet响应用户。
    
    前端控制器（DispatcherServlet）：接收请求，响应结果，相当于电脑的CPU。
    处理器映射器（HandlerMapping）：根据URL去查找处理器
    处理器（Handler）：（需要程序员去写代码处理逻辑的）
    处理器适配器（HandlerAdapter）：会把处理器包装成适配器，这样就可以支持多种类型的处理器，类比笔记本的适配器（适配器模式的应用）    
    视图解析器（ViewResovler）：进行视图解析，多返回的字符串，进行处理，可以解析成对应的页面
    
---    
## Spring如何解决循环依赖？
    一、构造器循环依赖：表示通过构造器注入构成的循环依赖，此依赖是无法解决的，只能抛出
    二、setter循环依赖：表示通过setter注入方式构成的循环依赖。
    对于setter注入造成的依赖是通过Spring容器提前暴露刚完成构造器注入但未完成其他步骤（如setter注入）的Bean来完成的，而且只能解决单例作用域的Bean循环依赖。
    
---
## Spring 如何保证 Controller 并发的安全？
    Spring 多线程请求过来调用的Controller对象都是一个，而不是一个请求过来就创建一个Controller对象。
    
    并发的安全？
    原因就在于Controller对象是单例的，那么如果不小心在类中定义了类变量，那么这个类变量是被所有请求共享的，这可能会造成多个请求修改该变量的值，出现与预期结果不符合的异常
    Scope, 在一次request的scope中都是单例的