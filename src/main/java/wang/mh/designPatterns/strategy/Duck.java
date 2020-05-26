package wang.mh.designPatterns.strategy;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class Duck {

    private FlyBehavior flyBehavior;

    private QuackBehavior quackBehavior;

    public abstract void display();

    public void swim() {
        System.out.println("I can swim");
    }

    public void performFly() {
        flyBehavior.fly(); //委托行为类
    }

    public void performQuack() {
        quackBehavior.quack();
    }
}
