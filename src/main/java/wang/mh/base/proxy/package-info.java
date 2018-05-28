package wang.mh.base.proxy;

/**
 * 不管是那种 AOP 实现，不论是 AspectJ,还是 Spring AOP，它们都需要动态地生成一个 AOP 代理类,
 * 区别只是生成 AOP 代理类的时机不同：AspectJ 采用编译时生成 AOP 代理类,因此具有更好的性能,但需要使用特定的编译器进行处理;
 * 而 Spring AOP 则采用运行时生成 AOP 代理类,因此无需使用特定编译器进行处理.
 * 由于 Spring AOP 需要在每次运行时生成 AOP 代理,因此性能略差一些.
 * Spring AOP会根据目标类是否实现接口而选择JDK动态代理或CGLIB
 */