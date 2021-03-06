## JVM数据

### 数据类型
java虚拟机中，数据类型可以分为两类：基本类型和引用类型。

基本类型的变量保存原始值，即：它代表的值就是数值本身，而引用类型的变量保存引用值。

“引用值”代表了某个对象的引用，而不是对象本身，对象本身存放在这个引用值所表示的地址的位置。

基本类型包括：byte、short、int、long、char、float、double、boolean

引用类型包括：类类型、接口类型和数组

| 类型 | 大小 | 范围 | 默认值 |
| --- | --- | --- | --- |
| byte | 1B(8位) | -128 ~ 127 | 0 |
| short | 2B(16位) | -215  ~ 215-1 | 0 |
| int | 4B(32位) | -2^31 ~ 2^31-1 | 0 |
| long | 8B(64位) | -2^63 ~ 2^63-1 | 0 |
| char | 2B(16位) | 0 ~ 2^16-1 | \U0000 |
| float | 4B(32位) | 1.4013E-45 ~3.4028E+38 | 0.0F |
| double | 8B(64位) | 4.9E-324 ~1.7977E+308 | 0.0D |
| boolean | 1B(8位) | True, false | false |

### 堆（heap）与栈（stack）
在java中，Main函数就是栈的起始点，也是程序的起始点。程序要运行总是有一个起点的（程序执行的入口）。

疑问一：为什么要把堆和栈区分出来呢？栈中不是也可以存储数据吗？

     1. 从软件设计的角度看，栈代表了处理逻辑，而堆代表了数据。这样分开，使得处理逻辑更为清晰。分而治之的思想。

        这种隔离、模块化的思想在软件设计的方方面面都有体现。

     2.堆与栈的分离，使得堆中的内容可以被多个栈共享（也可以理解为多个线程访问同一个对象）。

        好处:  a.提供了一种有效的数据交互方式（如：共享内存）

                 b.堆中的共享常量和缓存可以被所有栈访问，节省了空间。

     3. 栈因为运行时的需要，比如保存系统运行的上下文，需要进行地址段的划分。

        由于栈只能向上增长，因此就会限制住栈存储内容的能力，

        而堆不同，堆中的对象是可以根据需要动态增长的，

        因此栈和堆的拆分使得动态增长成为可能，相应栈中只需记录堆中的一个地址即可。

     4. 面向对象就是堆和栈的完美结合。

        其实，面向对象方式的程序与以前结构化的程序在执行上没有任何区别。

        但是，面向对象的引入，使得对待问题的思考方式发生了改变，而更接近于自然方式的思考。

        当我们把对象拆开，你会发现，对象的属性其实就是数据，存放在堆中；

        而对象的行为（方法），就是运行逻辑，放在栈中。

        我们在编写对象的时候，其实就是编写了数据结构，也编写了处理数据的逻辑。不得不承认，面向对象的设计，确实很美。
        
疑问二：  堆中存什么？栈中存什么？

     1. 栈存储的信息都是跟当前线程（或程序）相关的信息。(局部变量、程序运行状态、方法、方法返回值)等，

         栈中存的是基本数据类型和堆中对象的引用。一个对象的大小是不可估计的，或者说是可以动态变化的，但是

         在栈中，一个对象只对应了一个4byte的引用（堆栈分离的好处）。

     2. 堆只负责存储对象信息。
     
疑问三：  为什么不把基本类型放堆中呢？

     1. 其占用的空间一般是1~8个字节---需要空间比较少，

     2.而且因为是基本类型，所以不会出现动态增长的情况---长度固定，因此栈中存储就够了，如果把它存在堆中是没有什么意义的（还会浪费空间，后面说明??）。

疑问四：  java中的参数传递是传值呢？还是传引用？

     对象传递是引用值传递，原始类型数据传递是值传递

     实际上这个传入函数的值是对象引用的拷贝，即传递的是引用的地址值，所以还是按值传递

     tips：

      堆和栈中，栈是程序运行最根本的东西。程序运行可以没有堆，但是不能没有栈。

      而堆是为栈进行数据存储服务的，说白了堆就是一块共享的内存。

      不过，正是因为堆和栈的分离的思想，才使得java的垃圾回收成为可能。

      java中，栈的大小通过-Xss来设置，当栈中存储的数据比较多时，需要适当调大这个值，否则会出现 java.lang.StackOverflowError异常。

      常见的出现这个异常的是无法返回的递归，因为此时栈中保存的信息都是方法返回的记录点。
      
疑问五：  java对象的大小如何计算？

      基本数据类型的大小是固定的，这里就不多说了，对于非基本类型的java对象，其大小就值得商讨。
    
      在java中，一个空Object对象的大小是8byte，这个大小只是保存堆中一个没有任何属性的对象的大小。看看下面语句：
    
           Object  ob = new  Object();
    
      这样在程序中完成了一个java对象的声明，但是它所占的空间为：4byte+8byte。
    
     （4byte是上面部分所说的java栈中保存引用的所需要空间，而那8byte则是java堆中对象的信息）。
    
      因为所有的java非基本类型的对象都需要默认继承Object对象，因此不论什么样的java对象，其大小都必须是大于8byte。
    
      有了Object对象的大小，我们就可以计算其他对象的大小了。
      
      
    
      其大小为：空对象大小(8byte)+int大小(4byte)+Boolea大小(1byte)+空Object引用的大小(4byte)=17byte。
    
      但是因为java在对对象内存分配时都是以8的整数倍来分的，因此大于17byte的最接近8的整数倍的是24，因此此对象的大小为24byte。
    
      这里需要注意一下基本类型的包装类型的大小。因为这种包装类型已经成为对象了，因此需要把它们作为对象来看待。
    
      包装类型的大小至少是12byte(声明一个空Object至少需要的空间)，而且12byte没有包含任何有效信息，
    
      同时，因为java对象大小是8的整数倍，因此一个基本类型包装类的大小至少是16byte。
    
      这个内存占用是很恐怖的，它是使用基本类型的N倍(N>2)，这些类型的内存占用更是夸张。因此，可能的话应尽量少使用包装类。
    
      在JDK5.0以后，因为加入了自动类型装换，因此，java虚拟机会在存储方面进行相应的优化。

### 引用类型
对象引用类型分为强引用、软引用、弱引用和虚引用

强引用：我们一般声明对象时虚拟机生成的引用，

    强引用环境下，垃圾回收时需要严格判断当前对象是否被强引用，如果被强引用，则不会被垃圾回收。

    eg:  Sample sample = new Sample(); 

    创建一个对象，new出来的对象都是分配在java堆中的

软引用：一般被作为缓存来使用。

    与强引用的区别是，软引用在垃圾回收时，虚拟机会根据当前系统的剩余内存来决定是否对软引用进行回收。

    如果剩余内存比较紧张，则虚拟机会回收软引用所引用的空间，如果剩余内存相对富裕，则不会进行回收。

    换句话说，虚拟机在发生OutOfMemory时，肯定是没有软引用存在的。

弱引用：弱引用与软引用类似，都是作为缓存来使用。

    但与软引用不同，弱引用在进行垃圾回收时，是一定会被回收掉的，

    因此其生命周期只存在于一个垃圾回收周期内。

虚引用 ：顾名思义，就是形同虚设，与其他几种引用都不同，虚引用并不会决定对象的生命周期。   
    
    如果一个对象仅持有虚引用，那么它就和没有任何引用一样，在任何时候都可能被垃圾回收器回收。
    
    虚引用主要用来跟踪对象被垃圾回收器回收的活动。

    虚引用与软引用和弱引用的一个区别在于：
    
    虚引用必须和引用队列（ReferenceQueue）联合使用。
    
    当垃圾回收器准备回收一个对象时，如果发现它还有虚引用，就会在回收对象的内存之前，把这个虚引用加入到与之关联的引用队列中。
