package wang.mh.designPatterns.strategy;

public class FlayNoWay implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("I can't fly");
    }
}
