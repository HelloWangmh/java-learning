package wang.mh.designPatterns.strategy;

/**
 *  策略模式定义了算法族,分别封装起来,让他们之间相互替换,此模式让算法的变化独立于使用算法的客户端.
 *  多用组合,少用继承,"有一个"可能比"是一个"更好.
 *  不仅可以将算法族封装为类,还可以在运行时动态的更改行为.
 */
public class StrategyMain {
    public static void main(String[] args) {
        Duck mallardDuck = new MallardDuck();
        mallardDuck.setQuackBehavior(new Quack());
        mallardDuck.setFlyBehavior(new FlyWithWings());

        mallardDuck.performFly();//委托给该对象具体的FlyBehavior对象来处理
        mallardDuck.performQuack();


        Duck modelDuck = new ModelDuck();
        modelDuck.setFlyBehavior(new FlayNoWay());//运行时动态的更改行为
        modelDuck.setQuackBehavior(new MuteQuack());

        modelDuck.performFly();
        mallardDuck.performQuack();

    }
}
